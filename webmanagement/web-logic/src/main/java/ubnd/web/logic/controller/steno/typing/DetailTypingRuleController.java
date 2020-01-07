package ubnd.web.logic.controller.steno.typing;

import org.json.JSONArray;
import org.json.JSONObject;
import ubnd.common.utils.FileUtils;
import ubnd.common.utils.SessionUtils;
import ubnd.common.utils.TimeUtils;
import ubnd.core.data.obj.StenoRuleObject;
import ubnd.core.dto.UserDto;
import ubnd.core.web.common.WebConstants;
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

@WebServlet("/typing-detail.html")
public class DetailTypingRuleController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String oder = WebConstants.getOderLayout(request);

        int id = Integer.parseInt(request.getParameter("id"));
        String path = Utils.getPathRule(request, id);
        List<String> listFile = FileUtils.getListFileInFolder(path);
        String contentFile = FileUtils.readContentFile(path + File.separator + listFile.get(0));
        JSONObject objectRule = new JSONObject(contentFile);
        JSONArray arrayFirst = objectRule.getJSONArray("first");
        JSONArray arrayMain = objectRule.getJSONArray("main");
        JSONArray arrayLast = objectRule.getJSONArray("last");

        List<StenoRuleObject> listFirst = Utils.getStenoRule(arrayFirst);
        List<StenoRuleObject> listMain = Utils.getStenoRule(arrayMain);
        List<StenoRuleObject> listLast = Utils.getStenoRule(arrayLast);
        request.setAttribute("first", listFirst);
        request.setAttribute("main", listMain);
        request.setAttribute("last", listLast);
        request.setAttribute("id", id);
        request.setAttribute("oder", oder);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/steno/typing/detail-typing-rule.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);
        int type = Integer.parseInt(request.getParameter("type"));
        int id = Integer.parseInt(request.getParameter("id"));
        String action = request.getParameter("action");
        int idCode = Integer.parseInt(request.getParameter("idCode"));
        Timestamp timeUpdate = TimeUtils.getCurrentTimeStamp();

        String pathRule = Utils.getPathRule(request, id);
        List<String> listFile = FileUtils.getListFileInFolder(pathRule);
        String contentFile = FileUtils.readContentFile(pathRule + File.separator + listFile.get(0));
        JSONObject objectRule = new JSONObject(contentFile);
        JSONArray arrayFirst = new JSONArray(), arrayMain = new JSONArray(), arrayLast = new JSONArray();

        if (type == 1) {
            arrayMain = objectRule.getJSONArray("main");
            arrayLast = objectRule.getJSONArray("last");
        } else if (type == 2) {
            arrayFirst = objectRule.getJSONArray("first");
            arrayLast = objectRule.getJSONArray("last");
        } else if (type == 3) {
            arrayFirst = objectRule.getJSONArray("first");
            arrayMain = objectRule.getJSONArray("main");
        }


        switch (action) {
            case "update":
                String keySteno = request.getParameter("key");
                //Cập nhật lại bộ quy tắc
                if (type == 1) {
                    arrayFirst = Utils.updateKeySteno(objectRule, idCode, keySteno, userDto, timeUpdate, type);
                } else if (type == 2) {
                    arrayMain = Utils.updateKeySteno(objectRule, idCode, keySteno, userDto, timeUpdate, type);
                } else if (type == 3) {
                    arrayLast = Utils.updateKeySteno(objectRule, idCode, keySteno, userDto, timeUpdate, type);
                }

                break;
            case "toggle":
                if (type == 1) {
                    arrayFirst = Utils.toggleRule(objectRule, idCode, userDto, timeUpdate, type);
                } else if (type == 2) {
                    arrayMain = Utils.toggleRule(objectRule, idCode, userDto, timeUpdate, type);
                } else if (type == 3) {
                    arrayLast = Utils.toggleRule(objectRule, idCode, userDto, timeUpdate, type);
                }
                break;
            case "delete":
                if (type == 1) {
                    arrayFirst = Utils.deleteRule(objectRule, idCode, userDto, timeUpdate, type);
                } else if (type == 2) {
                    arrayMain = Utils.deleteRule(objectRule, idCode, userDto, timeUpdate, type);
                } else if (type == 3) {
                    arrayLast = Utils.deleteRule(objectRule, idCode, userDto, timeUpdate, type);
                }
                break;
        }

        JSONObject newRule = new JSONObject();
        newRule.put("first", arrayFirst);
        newRule.put("main", arrayMain);
        newRule.put("last", arrayLast);

        String timeMili = TimeUtils.convertStringDateToTimeMillis(timeUpdate.toString());
        FileUtils.writeNewFile(pathRule + File.separator + timeMili + ".json", newRule.toString());

        //Cập nhật file dữ liệu chung
        String path = FileUtils.templatePath(request) + File.separator + "steno" + File.separator + "rule" + File.separator + "rule.json";
        String data = FileUtils.readContentFile(path);
        JSONObject obj = new JSONObject(data);
        String title = obj.getString("title");
        JSONArray array = obj.getJSONArray("version");
        JSONObject newOBj = new JSONObject();
        newOBj.put("title", title);
        JSONArray newArr = new JSONArray();
        for (int i = 0; i < array.length(); i++) {
            JSONObject object1 = array.getJSONObject(i);
            JSONObject objData = new JSONObject();
            objData.put("id", object1.getInt("id"));
            objData.put("name", object1.getString("name"));
            objData.put("createBy", object1.getString("createBy"));
            objData.put("folder", object1.getString("folder"));
            objData.put("timeCreate", object1.getString("timeCreate"));
            objData.put("status", object1.getInt("status"));
            if (id == object1.getInt("id")) {
                objData.put("timeUpdate", timeUpdate.toString());
                objData.put("updateBy", userDto.getUserName());
            } else {
                objData.put("timeUpdate", object1.getString("timeUpdate"));
                objData.put("updateBy", object1.getString("updateBy"));
            }
            newArr.put(objData);
        }
        newOBj.put("version", newArr);
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        FileUtils.writeNewFile(path, newOBj.toString());
    }


}
