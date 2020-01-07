package ubnd.web.logic.controller.ajax;

import com.google.gson.Gson;
import ubnd.common.utils.SessionUtils;
import ubnd.core.dto.ObjectDto;
import ubnd.core.dto.OperatorDto;
import ubnd.core.dto.UserDto;
import ubnd.core.web.common.WebConstants;
import ubnd.core.web.utils.SingletonServiceUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet("/ajax-permission.html")
public class AjaxPermission extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);
        String type = request.getParameter("type");
        if(type != null){
            if(type.equals("checkFile")){
                Integer typeFile = Integer.valueOf(request.getParameter("typeFile"));
                Integer linkId = Integer.valueOf(request.getParameter("fileId"));
                OperatorDto operatorDto = null;
                if(!linkId.equals(0)){
                    operatorDto = SingletonServiceUtil.getPermissionServiceInstance().checkPermission(request, userDto, typeFile, linkId);
                }
                if(operatorDto != null){
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write("Accept");
                }else {
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write("Reject");
                }
            }
            if(type.equals("checkFunction")){
                Gson gson = new Gson();
                Integer linkId = Integer.valueOf(request.getParameter("functionId"));
                OperatorDto operatorDto = SingletonServiceUtil.getPermissionServiceInstance().checkPermission(request, userDto, 1, linkId);
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write(gson.toJson(operatorDto));
            }
            if(type.equals("requestFilePermission")){
                Integer typeFile = Integer.valueOf(request.getParameter("typeFile"));
                Integer linkId = Integer.valueOf(request.getParameter("fileId"));
                Map<String, Object> mapProperty = new HashMap<>();
                mapProperty.put("typeObject", typeFile);
                mapProperty.put("linkId", linkId);
                List<ObjectDto> objectDtoList = SingletonServiceUtil.getObjectService().findByProperty(mapProperty, null, null, null, null);
                if(objectDtoList.size() != 0){
                    SingletonServiceUtil.getExtendPermissionUserServiceInstance().requestExtendPermissionUser(userDto, objectDtoList.get(0));
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write("Success");
                }else {
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write("Error");
                }
            }
            if(type.equals("updatePermission")){
                SingletonServiceUtil.getPermissionServiceInstance().getAllUserPermission(request, userDto);
                response.sendRedirect("/home.html");
            }
        }else {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("Error");
        }

    }

}
