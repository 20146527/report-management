package ubnd.web.logic.controller.steno.manager;

import com.google.gson.Gson;
import ubnd.core.data.obj.StenographyObject;
import ubnd.core.dto.SessionDto;
import ubnd.core.dto.StenographyDto;
import ubnd.core.web.utils.SingletonServiceUtil;
import ubnd.web.logic.utils.TimeUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.*;

@WebServlet("/steno-file-manager.html")
public class StenoFileManagerController extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        List<StenographyObject> listData = new ArrayList<>();
        List<StenographyDto> stenographyDtoList = new ArrayList<>();
        if (type == null) {
            stenographyDtoList = SingletonServiceUtil.getStenographyService().findAll();
        } else if (type.equals("searchFileSteno")) {
            int meetingId = Integer.parseInt(request.getParameter("meetingId"));
            String sessionIdString = request.getParameter("sessionId");
            int sessionId = 0;
            if (sessionIdString != null) {
                sessionId = Integer.parseInt(sessionIdString);
            }
            String nameFileSteno = request.getParameter("nameFileSteno");
            int createUser = Integer.parseInt(request.getParameter("createUser"));
            Timestamp timeStart = TimeUtils.datetimePicker2Timestamp(request.getParameter("timeStart"));
            Timestamp timeEnd = TimeUtils.datetimePicker2Timestamp(request.getParameter("timeEnd"));
            stenographyDtoList = SingletonServiceUtil.getStenographyService().searchFileSteno(meetingId, sessionId, nameFileSteno, createUser, timeStart, timeEnd);
            request.setAttribute("meetingId", meetingId);
            request.setAttribute("sessionId", sessionId);
            request.setAttribute("createUser", createUser);
            request.setAttribute("nameFileSteno", nameFileSteno);
            if (timeStart != null)
                request.setAttribute("timeStart", TimeUtils.timestamp2DatetimePicker(timeStart.toString()));
            if (timeEnd != null)
                request.setAttribute("timeEnd", TimeUtils.timestamp2DatetimePicker(timeEnd.toString()));
        }
        convertDtoToObject(listData, stenographyDtoList);

        request.setAttribute("listData", listData);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/steno/manager-steno/steno-file-manager.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String type = request.getParameter("type");
        Gson gson = new Gson();
        if (type.equals("getUserCreateInMeeting")) {
            int meetingId = Integer.parseInt(request.getParameter("meetingId"));
            List<SessionDto> listSession;
            List<UserCreate> list = new ArrayList<>();
            if (meetingId != -1) {
                Map<String, Object> mapProperty = new HashMap<>();
                mapProperty.put("meetingEntity", meetingId);
                listSession =  SingletonServiceUtil.getSessionServiceInstance().findByProperty(mapProperty, null, null, null, null);
                for (SessionDto dto : listSession) {
                    getListUserBySessionID(list, dto.getSessionId());
                }
            } else {
                List<StenographyDto> stenographyDtoList = SingletonServiceUtil.getStenographyService().findAll();
                for (StenographyDto dto : stenographyDtoList) {
                    if (dto.getStatus() != 1) {
                        list.add(new UserCreate(dto.getCreUID(), SingletonServiceUtil.getUserServiceInstance().findById(dto.getCreUID()).getUserName()));
                    }
                }
            }
            PrintWriter out = response.getWriter();
            out.print(gson.toJson(list));
        } else if (type.equals("getUserCreateInSession")) {
            int sessionID = Integer.parseInt(request.getParameter("sessionID"));
            List<UserCreate> list = new ArrayList<>();
            getListUserBySessionID(list, sessionID);
            PrintWriter out = response.getWriter();
            out.print(gson.toJson(list));
        }
    }

    private void getListUserBySessionID(List<UserCreate> list, int sessionID) {
        Map<String, Object> map = new HashMap<>();
        map.put("sessionStenoEntity", sessionID);
        List<StenographyDto> listStenography = SingletonServiceUtil.getStenographyService().findByProperty(map, null, null, null, null);
        for (StenographyDto stenographyDto : listStenography) {
            if (stenographyDto.getStatus() != 1) {
                list.add(new UserCreate(stenographyDto.getCreUID(), SingletonServiceUtil.getUserServiceInstance().findById(stenographyDto.getCreUID()).getUserName()));
            }
        }
        new HashSet<>(list);
    }

    private void convertDtoToObject(List<StenographyObject> listData, List<StenographyDto> stenographyDtoList) {
        for (StenographyDto dto : stenographyDtoList) {
            if (dto.getStatus() != 1) {
                String nameFile = dto.getName();
                String nameMeeting = SingletonServiceUtil.getMeetingServiceInstance().findById(dto.getSessionDto().getMeetingDto().getMeetingId()).getName();
                String nameSession = SingletonServiceUtil.getSessionServiceInstance().findByID(dto.getSessionDto().getSessionId()).getName();
                String nameMod = SingletonServiceUtil.getUserServiceInstance().findById(dto.getModUID()).getUserName();
                Timestamp timeMod = dto.getModDate();
                listData.add(new StenographyObject(dto.getStenoId(), nameFile, nameMeeting, nameSession, nameMod, timeMod));
            }
        }
    }

    private static class UserCreate {
        private int id;
        private String userName;

        public UserCreate(int id, String userName) {
            this.id = id;
            this.userName = userName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }


}
