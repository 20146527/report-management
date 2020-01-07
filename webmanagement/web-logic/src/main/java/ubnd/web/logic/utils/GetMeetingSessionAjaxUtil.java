package ubnd.web.logic.utils;

import com.google.gson.Gson;
import ubnd.core.dto.AttendeesDto;
import ubnd.core.dto.MeetingDto;
import ubnd.core.dto.SessionDto;
import ubnd.core.dto.SpeakerDto;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.web.utils.SingletonServiceUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetMeetingSessionAjaxUtil {

    public static String getJSONMeetingSession(HttpServletRequest request) {
        String meetingId = request.getParameter("meetingId");
        String speakerId = request.getParameter("speakerId");
        Map<String, Object> mapProperty = new HashMap<>();
        mapProperty.put("status", 0);
//        if (speakerId != null) {
//            //get list session and meeting by preside (speakerId)
//            //get list session
//            Gson gson = new Gson();
//            mapProperty.put("dutyEntity", 1);
//            mapProperty.put("speakerAttendeesEntity", Integer.valueOf(speakerId));
//            Object[] objects = SingletonServiceUtil.getAttendeesServiceInstance().findByProperty(mapProperty, null, null, null, null);
//            List<SessionDto> sessionDtoList = new ArrayList<>();
//            for (AttendeesDto item : (List<AttendeesDto>) objects[1]) {
//                sessionDtoList.add(item.getSessionDto());
//            }
//            String arraySession = gson.toJson(sessionDtoList);
//            //get list meeting
//            List<MeetingDto> meetingDtoList = getMeetingListBySessionList(sessionDtoList);
//            String arrayMeeting = gson.toJson(meetingDtoList);
//            return "[" + arrayMeeting + "," + arraySession + "]";
//        } else
        if (meetingId != null) {
            //get list session
            mapProperty.put("meetingEntity", Integer.valueOf(meetingId));
            List<SessionDto> sessionDtoList = SingletonServiceUtil.getSessionServiceInstance().findByProperty(mapProperty, null, null, null, null);
            Gson gson = new Gson();
            return gson.toJson(sessionDtoList);
        } else {
            //get list meeting
            List<MeetingDto> meetingDtoList = SingletonServiceUtil.getMeetingServiceInstance().findByProperty(mapProperty, null, null, null, null);
            Gson gson = new Gson();
            return gson.toJson(meetingDtoList);
        }

    }

    public static String getJSONPreside(HttpServletRequest request) {
        Integer sessionId = Integer.valueOf(request.getParameter("sessionId"));
        List<AttendeesDto> attendeesDtoList;
        if(sessionId != 0){
            Map<String, Object> mapProperty = new HashMap<>();
            mapProperty.put("status", 0);
            mapProperty.put("dutyEntity", 1);
            mapProperty.put("sessionAttendeesEntity", sessionId);
            attendeesDtoList = SingletonServiceUtil.getAttendeesServiceInstance().findByProperty(mapProperty, null, null, null, null);
        }else {
            attendeesDtoList = SingletonServiceUtil.getAttendeesServiceInstance().findPreside(1);
        }

        Gson gson = new Gson();
        return gson.toJson(attendeesDtoList);
    }

    private static List<MeetingDto> getMeetingListBySessionList(List<SessionDto> sessionDtoList) {
        List<MeetingDto> meetingDtoList = new ArrayList<>();
        for (SessionDto item : sessionDtoList) {
            if (meetingDtoList.size() == 0) {
                meetingDtoList.add(item.getMeetingDto());
            } else {
                if (!checkMeetingExist(meetingDtoList, item.getMeetingDto())) {
                    meetingDtoList.add(item.getMeetingDto());
                }
            }
        }
        return meetingDtoList;
    }

    private static boolean checkMeetingExist(List<MeetingDto> meetingDtoList, MeetingDto meetingDto) {
        for (MeetingDto item : meetingDtoList) {
            if (item.getMeetingId().equals(meetingDto.getMeetingId())) {
                return true;
            }
        }
        return false;
    }
}
