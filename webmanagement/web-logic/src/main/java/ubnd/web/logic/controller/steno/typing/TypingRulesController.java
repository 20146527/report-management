package ubnd.web.logic.controller.steno.typing;

import org.json.JSONArray;
import org.json.JSONObject;
import ubnd.common.utils.FileUtils;
import ubnd.common.utils.SessionUtils;
import ubnd.common.utils.TimeUtils;
import ubnd.common.utils.UploadUtils;
import ubnd.core.data.obj.VersionRule;
import ubnd.core.dto.UserDto;
import ubnd.core.web.common.WebConstants;
import ubnd.web.logic.utils.DownloadUtil;
import ubnd.web.logic.utils.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/typing-rules.html")
public class TypingRulesController extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(req, WebConstants.LOGIN);
        String pathRule = FileUtils.templatePath(req) + File.separator + "steno" + File.separator + "rule" + File.separator + "rule.json";
        String data = FileUtils.readContentFile(pathRule);
        JSONObject obj = new JSONObject(data);
        String title = obj.getString("title");
        JSONArray array = obj.getJSONArray("version");
        Timestamp timestamp = TimeUtils.getCurrentTimeStamp();
        if (type != null) {
            JSONObject newObj = new JSONObject();
            JSONArray newArray = new JSONArray();
            int id;
            switch (type) {
                case "download":
                    String path = FileUtils.templatePath(req) + File.separator + "steno" + File.separator + "template" + File.separator + "template_quy_tac_goc_toc_ky.xlsx";
                    DownloadUtil util = new DownloadUtil();
                    util.downloadFile(resp, "template_quy_tac_goc_toc_ky.xlsx", path);
                    break;
                case "default":
                    id = Integer.parseInt(req.getParameter("id"));
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject objVersion = array.getJSONObject(i);
                        JSONObject object = new JSONObject();
                        object.put("id", objVersion.getInt("id"));
                        object.put("timeCreate", objVersion.getString("timeCreate"));
                        object.put("createBy", objVersion.getString("createBy"));
                        object.put("folder", objVersion.getString("folder"));
                        object.put("name", objVersion.getString("name"));
                        if (id == objVersion.getInt("id")) {
                            object.put("timeUpdate", timestamp);
                            object.put("updateBy", userDto.getUserName());
                            object.put("status", 0);
                        } else {
                            if (objVersion.getInt("status") != 1) {
                                object.put("timeUpdate", objVersion.getString("timeUpdate"));
                                object.put("updateBy", objVersion.getString("updateBy"));
                                object.put("status", 2);
                            } else {
                                object.put("timeUpdate", objVersion.getString("timeUpdate"));
                                object.put("updateBy", objVersion.getString("updateBy"));
                                object.put("status", objVersion.getInt("status"));
                            }
                        }
                        newArray.put(object);
                    }
                    newObj.put("title", title);
                    newObj.put("version", newArray);
                    writeFileRule(pathRule, newObj.toString());
                    resp.sendRedirect("/typing-rules.html");
                    break;
                case "delete":
                    id = Integer.parseInt(req.getParameter("id"));
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject objVersion = array.getJSONObject(i);
                        JSONObject object = new JSONObject();
                        object.put("id", objVersion.getInt("id"));
                        object.put("timeCreate", objVersion.getString("timeCreate"));
                        object.put("createBy", objVersion.getString("createBy"));
                        object.put("folder", objVersion.getString("folder"));
                        object.put("name", objVersion.getString("name"));
                        if (id == objVersion.getInt("id")) {
                            object.put("timeUpdate", timestamp);
                            object.put("updateBy", userDto.getUserName());
                            object.put("status", 1);
                        } else {
                            object.put("timeUpdate", objVersion.getString("timeUpdate"));
                            object.put("updateBy", objVersion.getString("updateBy"));
                            object.put("status", objVersion.getInt("status"));
                        }
                        newArray.put(object);
                    }
                    newObj.put("title", title);
                    newObj.put("version", newArray);
                    writeFileRule(pathRule, newObj.toString());
                    resp.sendRedirect("/typing-rules.html");
                    break;
            }
        } else {
            List<VersionRule> listRule = Utils.getListVerRule(req);
            req.setAttribute("dataRule", listRule);
            RequestDispatcher dispatcher = req.getRequestDispatcher("views/steno/typing/typing-rules.jsp");
            dispatcher.forward(req, resp);
        }


    }

    private void writeFileRule(String pathRule, String content) {
        File file = new File(pathRule);
        if (file.exists())
            file.delete();
        FileUtils.writeNewFile(pathRule, content);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);
        if (type != null) {
            if (type.equals("upload")) {
                UploadUtils utils = new UploadUtils();
                String path = utils.writeOrUpdateFile(request);
                File fileTempt = new File(path);
                if (fileTempt.exists()) {
                    // Cập nhật file dữ liệu chung
                    Timestamp timestamp = TimeUtils.getCurrentTimeStamp();
                    String pathRule = FileUtils.templatePath(request) + File.separator + "steno" + File.separator + "rule" + File.separator + "rule.json";
                    String data = FileUtils.readContentFile(pathRule);
                    JSONObject obj = new JSONObject(data);
                    String title = obj.getString("title");
                    JSONObject newOBj = new JSONObject();
                    newOBj.put("title", title);
                    JSONArray array = obj.getJSONArray("version");
                    JSONObject newObj = new JSONObject();
                    newObj.put("id", array.length() + 1);
                    newObj.put("name", timestamp);
                    newObj.put("createBy", userDto.getUserName());
                    newObj.put("folder", userDto.getUserName() + TimeUtils.convertStringDateToTimeMillis(timestamp.toString()));
                    newObj.put("timeCreate", timestamp);
                    newObj.put("timeUpdate", timestamp);
                    newObj.put("updateBy", userDto.getUserName());
                    newObj.put("status", 2);
                    array.put(newObj);
                    newOBj.put("version", array);
                    File filepathRule = new File(pathRule);
                    if (filepathRule.exists())
                        filepathRule.delete();
                    FileUtils.writeNewFile(pathRule, newOBj.toString());

                    String timeMili = TimeUtils.convertStringDateToTimeMillis(timestamp.toString());
                    String pathFoder = FileUtils.templatePath(request) + File.separator + "steno" + File.separator + "rule" + File.separator + userDto.getUserName() + timeMili;
                    if (new File(pathFoder).mkdir()) {

                        JSONArray firstArray = new JSONArray();
                        Utils.putData(path, 0, firstArray, userDto, timestamp);

                        JSONArray mainArray = new JSONArray();
                        Utils.putData(path, 1, mainArray, userDto, timestamp);

                        JSONArray lastArray = new JSONArray();
                        Utils.putData(path, 2, lastArray, userDto, timestamp);

                        JSONObject object = new JSONObject();
                        object.put("first", firstArray);
                        object.put("main", mainArray);
                        object.put("last", lastArray);

                        FileUtils.writeNewFile(pathFoder + File.separator + timeMili + ".json", object.toString());
                        fileTempt.delete();
                    }

                }

            }
        }
        response.sendRedirect("/typing-rules.html");

    }
}
