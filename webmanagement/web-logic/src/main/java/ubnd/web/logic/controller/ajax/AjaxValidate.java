package ubnd.web.logic.controller.ajax;

import ubnd.core.persistence.data.entity.SessionEntity;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.web.logic.utils.TimeUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;


@WebServlet("/ajax-validate.html")
public class AjaxValidate extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String type = request.getParameter("type");
        if(type.equals("validate_room_session")){
            String inputTimeStart = request.getParameter("timeStart");
            String inputTimeEnd = request.getParameter("timeEnd");
            String roomId = request.getParameter("roomId");
            Timestamp timeStart = TimeUtils.datetimePicker2Timestamp(inputTimeStart);
            Timestamp timeEnd = TimeUtils.datetimePicker2Timestamp(inputTimeEnd);
            List<SessionEntity> entityList = SingletonDaoUtil.getSessionDaoInstance().validateDuplicateTime(timeStart, timeEnd, Integer.valueOf(roomId));
            if(entityList.size() != 0){
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("Accept");
            }else {
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("Reject");
            }
        }
    }

}
