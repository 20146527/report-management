package ubnd.web.logic.controller.ajax;

import com.google.gson.Gson;
import ubnd.common.utils.*;
import ubnd.core.dto.MeetingDto;
import ubnd.core.dto.SessionDto;
import ubnd.core.dto.UserDto;
import ubnd.core.persistence.data.entity.SessionEntity;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.web.common.WebConstants;
import ubnd.core.web.utils.SingletonServiceUtil;
import ubnd.web.logic.utils.SingletonSessionUtil;
import ubnd.web.logic.utils.TimeUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet("/ajax-query.html")
public class AjaxQuery extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String type = request.getParameter("type");
        Gson gson = new Gson();
        if(type.equals("get_all_meeting")){
            Map<String, Object> mapMeetingProperty = new HashMap<>();
            mapMeetingProperty.put("status", 0);
            List<MeetingDto> meetingDtoList = SingletonServiceUtil.getMeetingServiceInstance().findByProperty(mapMeetingProperty, null, null, null, null);
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(gson.toJson(meetingDtoList));
        }
        if(type.equals("get_session_by_meetingId")){
            String meetingId = request.getParameter("meetingId");
            Map<String, Object> mapProperty = new HashMap<>();
            mapProperty.put("status", 0);
            mapProperty.put("meetingEntity", meetingId);
            List<SessionDto> sessionDtoList = SingletonServiceUtil.getSessionServiceInstance().findByProperty(mapProperty, null, null, null, null);
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(gson.toJson(sessionDtoList));
        }

    }

}
