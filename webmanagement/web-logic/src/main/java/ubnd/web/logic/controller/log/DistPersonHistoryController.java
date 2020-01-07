package ubnd.web.logic.controller.log;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import ubnd.common.utils.FileUtils;
import ubnd.common.utils.SessionUtils;
import ubnd.common.utils.TimeUtils;
import ubnd.core.data.obj.DictPerson;
import ubnd.core.data.obj.LogDictPerson;
import ubnd.core.dto.UserDto;
import ubnd.core.web.common.WebConstants;
import ubnd.web.logic.utils.DownloadUtil;
import ubnd.web.logic.utils.ReadDictUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/dict-person-history.html")
public class DistPersonHistoryController extends HttpServlet {

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);
        String type = request.getParameter("type");
        if (type != null) {
            String nameFile = request.getParameter("nameFile");
            String pathFile = FileUtils.getPathFolderVersionPersonDict(request, userDto) + File.separator + nameFile;
            JSONObject object = ReadDictUtils.getObjDict(pathFile);
            JSONArray array = object.getJSONArray("dict");
            switch (type) {
                case "download":
                    JSONObject objPersonDict = new JSONObject();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject dict = array.getJSONObject(i);
                        int status = dict.getInt("status");
                        if (status == 0) {
                            String value = dict.getString("value");
                            String key = dict.getString("key");
                            objPersonDict.put(key, value);
                        }
                    }
                    String filePath = FileUtils.getTemptFile(request, userDto);
                    FileUtils.writeNewFile(filePath, objPersonDict.toString());
                    File f = new File(filePath);
                    if (f.exists()) {
                        DownloadUtil util = new DownloadUtil();
                        util.downloadFile(response, nameFile, filePath);
                        f.delete();
                    }
                    break;
                case "restore":
                    ReadDictUtils.writerPersonDict(object.toString(), request, userDto, array);
                    response.sendRedirect("/dict-person-history.html");
                    break;
            }
        } else {
            String folderVersion = FileUtils.getPathFolderVersionPersonDict(request, userDto);
            List<String> listFileDict = FileUtils.getListFileInFolder(folderVersion);
            ArrayList<LogDictPerson> arrayList = new ArrayList<>();
            int i = 0;
            for (String s : listFileDict) {
                i++;
                FileInputStream inputStream = new FileInputStream(folderVersion + File.separator + s);
                BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                String str;
                StringBuilder data = new StringBuilder();

                while ((str = in.readLine()) != null) {
                    data.append(str);
                }
                try {
                    JSONObject object = new JSONObject(data.toString());
                    String time = object.getString("update");
                    arrayList.add(new LogDictPerson(time, s));
                } catch (Exception e) {
                    e.printStackTrace();
                    inputStream.close();
                } finally {
                    inputStream.close();
                }

            }

            request.setAttribute("list", arrayList);
            request.setAttribute("user", userDto.getUserName());
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/log/dict-person-history.jsp");
            dispatcher.forward(request, response);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);
        String type = request.getParameter("type");
        if (type != null && type.equals("findDictByID")) {
            String id = request.getParameter(WebConstants.ID);
            String folderVersion = FileUtils.getPathFolderVersionPersonDict(request, userDto);
            List<String> listFileDict = FileUtils.getListFileInFolder(folderVersion);
            ArrayList<DictPerson> dict = new ArrayList<>();
            for (int i = 0; i < listFileDict.size(); i++) {
                Object[] objects = ReadDictUtils.getDataPersonDict(request, userDto, i);
                if (objects[1] instanceof ArrayList) {

                    @SuppressWarnings("unchecked")
                    ArrayList<DictPerson> arrayList = (ArrayList<DictPerson>) objects[1];
                    for (DictPerson person : arrayList) {
                        if (Integer.valueOf(person.getIdCode()).equals(Integer.valueOf(id))) {
                            boolean bol = true;
                            for (DictPerson dictPerson : dict) {
                                if (dictPerson.getModUpdate().equals(person.getModUpdate())) {
                                    bol = false;
                                    break;
                                }
                            }
                            if (bol) {
                                dict.add(person);
                            }
                            break;
                        }
                    }
                }
            }


            dict.sort((o1, o2) -> {
                Date d1 = new Date(o1.getModUpdate().getTime());
                Date d2 = new Date(o2.getModUpdate().getTime());
                return d2.compareTo(d1);
            });


            Gson gson = new Gson();
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(gson.toJson(dict));
            out.flush();
            out.close();

        } else if (type != null && (type.equals("restoreByID") || type.equals("restoreOnePer"))) {
            int id = Integer.parseInt(request.getParameter("id"));
            JSONObject object = ReadDictUtils.getDefaultDictPerson(request, userDto);
            JSONObject newDict = new JSONObject();
            String date = object.getString("date");
            String createBy = object.getString("date");
            String update = object.getString("date");
            JSONArray arr = object.getJSONArray("dict");
            newDict.put("date", date);
            newDict.put("createBy", createBy);
            newDict.put("update", update);
            JSONArray a = new JSONArray();
            for (int i = 0; i < arr.length(); i++) {
                JSONObject d = new JSONObject();
                JSONObject obj = arr.getJSONObject(i);
                String creDate = obj.getString("creDate");
                Integer idCode = obj.getInt("idCode");
                String value = obj.getString("value");
                String key = obj.getString("key");
                int status;
                String modeDate;
                if (idCode.equals(id)) {
                    if (type.equals("restoreByID")) {
                        status = 0;
                    } else {
                        status = Integer.parseInt(request.getParameter(WebConstants.STATUS));
                    }

                    modeDate = TimeUtils.getCurrentTimeStamp().toString();
                } else {
                    modeDate = obj.getString("modeDate");
                    status = obj.getInt("status");
                }
                d.put("creDate", creDate);
                d.put("idCode", idCode);
                d.put("modeDate", modeDate);
                d.put("value", value);
                d.put("key", key);
                d.put("status", status);
                a.put(d);
            }
            newDict.put("dict", a);
            ReadDictUtils.writerPersonDict(newDict.toString(), request, userDto, a);
        }
    }

}
