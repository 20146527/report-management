package ubnd.web.logic.controller.ajax;

import com.google.gson.Gson;
import ubnd.common.utils.SessionUtils;
import ubnd.core.dto.NotificationDto;
import ubnd.core.dto.UserDto;
import ubnd.core.web.common.WebConstants;
import ubnd.core.web.utils.SingletonServiceUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet("/ajax-notification.html")
public class AjaxNotification extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String type = request.getParameter("type");
        if(type != null){
            if(type.equals("clear")){
                Integer id = Integer.valueOf(request.getParameter("id"));
                String link = request.getParameter("link");
                List<Integer> list = new ArrayList<>();
                list.add(id);
                SingletonServiceUtil.getNotificationServiceInstance().deleteNotify(list);
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write(link);
            }if(type.equals("clearAll")){
                Gson gson = new Gson();
                String ids = request.getParameter("ids");
                String[] listNotiID = ids.split(";");
                List<Integer> list = new ArrayList<>();
                for(String item: listNotiID){
                    list.add(Integer.valueOf(item));
                }
                SingletonServiceUtil.getNotificationServiceInstance().deleteNotify(list);
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("Success");
            }
        }else {
            //get User Session
            UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);
            Map<String, Object> mapProperty = new HashMap<>();
            Gson gson = new Gson();

            mapProperty.put("userId", userDto.getUserId());
            List<NotificationDto> notificationDtoList = SingletonServiceUtil.getNotificationServiceInstance().findByProperty(mapProperty, null, null, null, null);

            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(gson.toJson(notificationDtoList));
        }

    }

}
