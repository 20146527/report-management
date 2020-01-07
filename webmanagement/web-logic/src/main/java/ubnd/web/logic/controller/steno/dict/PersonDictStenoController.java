package ubnd.web.logic.controller.steno.dict;

import org.json.JSONArray;
import org.json.JSONObject;
import ubnd.common.utils.*;
import ubnd.core.data.obj.DictPerson;
import ubnd.core.dto.UserDto;
import ubnd.core.web.common.WebConstants;
import ubnd.web.logic.utils.ReadDictUtils;
import ubnd.web.logic.utils.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static ubnd.web.logic.utils.ReadDictUtils.pushDataToObject;

@WebServlet("/manager-person-dict-steno.html")
public class PersonDictStenoController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);
        Object[] objects = ReadDictUtils.getDataPersonDict(request, userDto, 0);

        //thứ tự bàn phím steno
        String oder = WebConstants.getOderLayout(request);

        @SuppressWarnings("unchecked")
        ArrayList<DictPerson> arrayList = (ArrayList<DictPerson>) objects[1];
        String date = (String) objects[0];

        String type = request.getParameter("type");
        if (type != null) {
            int pos = Integer.parseInt(EncodingUtils.base64Decode(request.getParameter("index")));
            for (DictPerson personObj : arrayList) {
                if (personObj.getIdCode() == pos) {
                    if (type.equals("disable")) {
                        personObj.setStatus(2);
                    } else if (type.equals("enable")) {
                        personObj.setStatus(0);
                    } else {
                        personObj.setStatus(1);
                    }
                    personObj.setModUpdate(TimeUtils.getCurrentTimeStamp());
                    arrayList.set(pos, personObj);
                    break;
                }
            }

            JSONObject object = new JSONObject();
            object.put("date", date);
            object.put("update", TimeUtils.getCurrentTimeStamp());
            object.put("createBy", userDto.getUserName());
            JSONArray array = new JSONArray();
            for (DictPerson person : arrayList) {
                array.put(pushDataToObject(person));
            }
            object.put("dict", array);
            String newDict = object.toString();

            ReadDictUtils.writerPersonDict(newDict, request, userDto, array);

            response.sendRedirect("/manager-person-dict-steno.html");
        } else {
            ArrayList<DictPerson> listRemover = new ArrayList<>();
            for (DictPerson person : arrayList) {
                if (person.getStatus() == 1) {
                    listRemover.add(person);
                }
            }

            for (DictPerson person : listRemover) {
                arrayList.remove(person);
            }

            Utils.writeDict(request, userDto);
            String pathPerson = EncodingUtils.base64Encode(FileUtils.getPersonDict(userDto));
            request.setAttribute("dict_person", pathPerson);
            request.setAttribute("oder", oder);
            request.setAttribute("list", arrayList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/steno/dict/person-dict-steno.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);
        String type = request.getParameter("type");

        if (type != null && type.equals("add-new-person-dict")) {
            String dataPerson = StringUtils.formatStringJson(request.getParameter(WebConstants.DATA_PERSON_DICT));
            Object[] objects = ReadDictUtils.getDataPersonDict(request, userDto, 0);
            String date = (String) objects[0];
            @SuppressWarnings("unchecked")
            ArrayList<DictPerson> arrayList = (ArrayList<DictPerson>) objects[1];
            JSONObject jsonObject = new JSONObject(dataPerson);
            Iterator<?> keys = jsonObject.keys();
            JSONObject object = new JSONObject();
            JSONArray array = new JSONArray();
            List<Integer> listID = new ArrayList<>();
            Timestamp timeUpdate = TimeUtils.getCurrentTimeStamp();
            if (arrayList.size() > 0) {
                object.put("date", date);
                for (DictPerson person : arrayList) {
                    listID.add(person.getIdCode());
                    array.put(pushDataToObject(person));
                }
            } else {
                listID.add(-1);
                object.put("date", timeUpdate);
            }

            Collections.sort(listID);
            int maxID = listID.get(listID.size() - 1);
            object.put("update", timeUpdate);
            object.put("createBy", userDto.getUserName());

            while (keys.hasNext()) {
                maxID++;
                String key = (String) keys.next();
                String value = jsonObject.getString(key);
                JSONObject dictObj = new JSONObject();
                dictObj.put("key", key.toUpperCase());
                dictObj.put("idCode", maxID);
                dictObj.put("value", value);
                dictObj.put("status", 0);
                dictObj.put("creDate", timeUpdate);
                dictObj.put("modeDate", timeUpdate);
                array.put(dictObj);
            }
            object.put("dict", array);

            String newDict = object.toString();
            ReadDictUtils.writerPersonDict(newDict, request, userDto, array);
        }else if (type != null && type.equals("edit")) {
            String stenoCode = request.getParameter(WebConstants.STENO_CODE);
            String value = request.getParameter(WebConstants.VALUE);
            int data = Integer.parseInt(EncodingUtils.base64Decode(request.getParameter(WebConstants.DATA)));

            Timestamp timeUpdate = TimeUtils.getCurrentTimeStamp();
            Object[] objects = ReadDictUtils.getDataPersonDict(request, userDto, 0);
            String date = (String) objects[0];

            @SuppressWarnings("unchecked")
            ArrayList<DictPerson> arrayList = (ArrayList<DictPerson>) objects[1];

            for (DictPerson dictPerson : arrayList) {
                if (dictPerson.getIdCode() == data) {
                    dictPerson.setCodeSteno(stenoCode);
                    dictPerson.setWord(value);
                    dictPerson.setModUpdate(timeUpdate);
                    break;
                }
            }

            JSONObject object = new JSONObject();
            JSONArray array = new JSONArray();

            object.put("date", date);
            for (DictPerson person : arrayList) {
                array.put(pushDataToObject(person));
            }
            object.put("update", timeUpdate);
            object.put("createBy", userDto.getUserName());
            object.put("dict", array);
            String newDict = object.toString();
            ReadDictUtils.writerPersonDict(newDict, request, userDto, array);
        }
    }
}
