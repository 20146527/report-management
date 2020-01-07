package ubnd.web.logic.controller.steno.layout;

import org.json.JSONObject;
import ubnd.common.utils.FileUtils;
import ubnd.core.data.obj.KeyCode;
import ubnd.core.data.obj.LayoutObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@WebServlet("/manager-layout.html")
public class LayoutMangerController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path_dict = FileUtils.getRealPath(request, "file") + File.separator + "keycode";
        String path_keyCodeToQwerty = path_dict + File.separator + "keyCodeToQwerty.json";
        String path_keyCodeToSteno = path_dict + File.separator + "keyCodeToSteno.json";

        JSONObject keyCodeToQwerty = new JSONObject(FileUtils.readContentFile(path_keyCodeToQwerty));
        JSONObject keyCodeToSteno = new JSONObject(FileUtils.readContentFile(path_keyCodeToSteno));
        List<LayoutObject> list = new ArrayList<>();
        List<KeyCode> listKeyCodeToQwerty = new ArrayList<>();
        Iterator<String> iterator = keyCodeToQwerty.keys();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = keyCodeToQwerty.getString(key);
            listKeyCodeToQwerty.add(new KeyCode(Integer.parseInt(key), value));
        }

        List<KeyCode> listKeyCodeToSteno = new ArrayList<>();

        Iterator<String> iteratorSteno = keyCodeToSteno.keys();
        while (iteratorSteno.hasNext()) {
            String key = iteratorSteno.next();
            String value = keyCodeToSteno.getString(key);
            listKeyCodeToSteno.add(new KeyCode(Integer.parseInt(key), value));
        }

        for (KeyCode keyCode : listKeyCodeToQwerty) {
            for (KeyCode code : listKeyCodeToSteno) {
                if (keyCode.getKey() == code.getKey()) {
                    list.add(new LayoutObject(keyCode.getKey(), keyCode.getValue(), code.getValue()));
                }
            }
        }

        list.sort(Comparator.comparing(LayoutObject::getKeyQwerty));

        request.setAttribute("list", list);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/steno/layouts/layout-manager.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String content = request.getParameter("content");
        String path_dict = FileUtils.getRealPath(request, "file") + File.separator + "keycode";
        String path_keyCodeToSteno = path_dict + File.separator + "keyCodeToSteno.json";
        String path_keyCodeToStenoNumber = path_dict + File.separator + "keyCodeToStenoNumber.json";
        JSONObject objKeyCode = new JSONObject(FileUtils.readContentFile(path_keyCodeToSteno));
        JSONObject object = new JSONObject(content);
        JSONObject newObj = new JSONObject();
        Iterator<String> iterator = objKeyCode.keys();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value;
            try {
                value = object.getString(key);
            } catch (Exception e) {
                value = objKeyCode.getString(key);
            }
            newObj.put(key, value);
        }

        JSONObject objNumber = new JSONObject(FileUtils.readContentFile(path_keyCodeToStenoNumber));
        Iterator<String> iteratorNumber = objNumber.keys();
        while (iteratorNumber.hasNext()) {
            String key = iteratorNumber.next();
            String value = objNumber.getString(key);
            newObj.put(key, value);
        }
        File file = new File(path_keyCodeToSteno);
        if (file.exists()) {
            file.delete();
        }
        FileUtils.writeNewFile(path_keyCodeToSteno, newObj.toString());
    }
}
