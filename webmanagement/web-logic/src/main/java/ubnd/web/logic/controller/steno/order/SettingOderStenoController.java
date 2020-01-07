package ubnd.web.logic.controller.steno.order;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ubnd.common.utils.FileUtils;
import ubnd.common.utils.SessionUtils;
import ubnd.core.data.obj.StenoObject;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/steno-setting-oder.html")
public class SettingOderStenoController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);
        String pathPersonDict = FileUtils.getPathFilePersonDict(request, userDto);
        String content = FileUtils.readContentFile(pathPersonDict);
        JsonObject jsonObject = new JsonParser().parse(content).getAsJsonObject();
        List<StenoObject> list = new ArrayList<>();
        for (Map.Entry<String, JsonElement> e : jsonObject.entrySet()) {
            String s = e.getKey();
            String value = e.getValue().toString().replace("\"", "");
            list.add(new StenoObject(s, value));
        }
        request.setAttribute("list", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/steno/order/steno-setting-oder.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String data = request.getParameter("data");
        UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);
        String pathPersonDict = FileUtils.getPathFilePersonDict(request, userDto);
        File file = new File(pathPersonDict);
        if (file.delete()) {
            FileUtils.writeNewFile(pathPersonDict, data);
            Utils.writeDict(request, userDto);
        }
    }
}
