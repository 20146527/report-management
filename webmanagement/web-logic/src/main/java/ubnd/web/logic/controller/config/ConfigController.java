package ubnd.web.logic.controller.config;


import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import ubnd.common.utils.FileUtils;
import ubnd.common.utils.SessionUtils;
import ubnd.core.dto.ConfigDto;
import ubnd.core.dto.UserDto;
import ubnd.core.web.common.WebConstants;
import ubnd.core.web.utils.FormUtils;
import ubnd.core.web.utils.SingletonServiceUtil;
import ubnd.web.logic.command.ConfigCommand;
import ubnd.web.logic.utils.TimeUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/setting.html", "/ajax-setting.html"})
public class ConfigController extends HttpServlet {
    private final String CONFIG = "config";
    private final String TAG = "tag";
    private final String FOLDER_TREE = "folder_tree";
    private final String TYPE = "type";

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        Map<String, Object> mapProperty = new HashMap<String, Object>();
        mapProperty.put("status", 0);
        mapProperty.put("modUID", 1);
        List<ConfigDto> configList = SingletonServiceUtil.getConfigServiceInstance().findByProperty(mapProperty, null, null, null, null);
        Gson gson = new Gson();
        String data = gson.toJson(configList);
        String tag = getConfigTag(configList).getValue();
        String folderTree = FileUtils.getObjectFolderFile(request);
        request.setAttribute(FOLDER_TREE, folderTree);
        request.setAttribute(TAG, tag);
        request.setAttribute(CONFIG, data);
        String configType = (String) SessionUtils.getInstance().getValue(request, "configType");
        if(configType != null && !configType.equals("")){
            SessionUtils.getInstance().remove(request, "configType");
            request.setAttribute(TYPE, configType);
        }else {
            SessionUtils.getInstance().remove(request, "configType");
            request.setAttribute(TYPE, "custom");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/config/list-config.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        ConfigCommand command = FormUtils.populate(ConfigCommand.class, request);

        TimeUtils timeUtils = new TimeUtils();

        String name_folder = request.getParameter("name-folder");
        String path = request.getParameter("data-path");
        String configType = request.getParameter("configType");
        if (name_folder != null && path != null) {
            String pathFile, url;
            if(configType != null && !configType.equals("")){
                SessionUtils.getInstance().putValue(request, "configType", configType);
            }
            if (path.equals("")) {
                pathFile = FileUtils.templatePath(request) + File.separator + name_folder;
                url = "/setting.html#file";
            } else {
                pathFile = FileUtils.templatePath(request) + File.separator + path + File.separator + name_folder;
                url = "/setting.html?con#file%2F" + path.replace("\\", "%2F");
            }
            FileUtils.createFolder(pathFile);
            response.sendRedirect(url);
        }else if(command.getConfig() != null ){
            if(command.getConfig().equals("custom")){
                //config custom
            }else {
                //config audio, steno, report, attendees
                JSONArray jsonArray = new JSONArray(command.getData());
                SessionUtils.getInstance().putValue(request, "configType", command.getConfig());
                SingletonServiceUtil.getConfigServiceInstance().updateConfig(1, jsonArray, timeUtils.getCurrentTimestamp());
                String time = timeUtils.getCurrentTimestamp().toString();
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write(time);
            }
        }else {
            response.sendRedirect("/setting.html");
        }
    }

    private Map<String, String> buidMapRedirectMessage() {
        Map<String, String> mapMessage = new HashMap<String, String>();
        mapMessage.put(WebConstants.REDIRECT_UPDATE, "Chỉnh sửa cấu hình thành công");
        mapMessage.put(WebConstants.REDIRECT_ERROR, "Có lỗi xảy ra");
        return mapMessage;
    }

    private String[] getArrayTag(List<ConfigDto> configList){
        ConfigDto tagObj = getConfigTag(configList);
        String value = tagObj.getValue();
        return value.split(", ");
    }

    private ConfigDto getConfigTag(List<ConfigDto> configList){
        ConfigDto dto = new ConfigDto();
        for (ConfigDto item: configList){
            if(item.getTitle().equals("tag")){
                return item;
            }
        }
        return dto;
    }


}