package ubnd.web.logic.controller.audio;

import ubnd.common.utils.UploadUtils;
import ubnd.core.dto.MeetingDto;
import ubnd.core.dto.RecordDto;
import ubnd.core.dto.SessionDto;
import ubnd.core.web.common.WebConstants;
import ubnd.core.web.utils.FormUtils;
import ubnd.core.web.utils.SingletonServiceUtil;
import ubnd.core.web.utils.WebCommonUtil;
import ubnd.web.logic.command.RecordCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/manager-upload-record.html", "/ajax-upload-record.html"})
public class UploadRecordController extends HttpServlet {
    private static final String MEETING_LIST = "meetingList";

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        RecordCommand command = FormUtils.populate(RecordCommand.class, request);
        Map<String, Object> mapMeetingProperty = new HashMap<>();
        mapMeetingProperty.put("status", 0);
        List<MeetingDto> meetingDtoList = SingletonServiceUtil.getMeetingServiceInstance().findByProperty(mapMeetingProperty, null, null, null, null);
        request.setAttribute(MEETING_LIST, meetingDtoList);
        if (command.getCrudaction() != null) {
            Map<String, String> mapMessage = buildRedirectionMessageMap();
            WebCommonUtil.addRedirectMessage(request, command.getCrudaction(), mapMessage);
        }
        if(command.getUrlType() != null){
            if(command.getUrlType().equals(WebConstants.URL_UPLOAD)){
                SingletonServiceUtil.getSessionServiceInstance().findSessionUpload(25,null);
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/audio/upload-record.jsp");
        dispatcher.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        UploadUtils uploadUtils = new UploadUtils();
        Set<String> value = new HashSet<>();
        value.add("urlType");
        value.add("audioName");
        value.add("sessionId");

        Object[] objects = uploadUtils.writeOrUpdateFile(request, value, WebConstants.AUDIO_RECORD);

        String urlType, audioName = "";
        Integer sessionId = null;
        RecordDto recordDto = new RecordDto();
        Map<String, String> mapValue = (Map<String, String>) objects[3];
        for (Map.Entry<String, String> item : mapValue.entrySet()) {
            if (item.getKey().equals("urlType")) {
                urlType = item.getValue();
            }
            if(item.getKey().equals("audioName")){
                audioName = item.getValue();
            }
            if(item.getKey().equals("sessionId")){
                sessionId = Integer.valueOf(item.getValue());
            }
        }
        SessionDto sessionDto;
        if(sessionId != null){
            sessionDto = SingletonServiceUtil.getSessionServiceInstance().findByID(sessionId);
        }else {
            sessionDto = SingletonServiceUtil.getSessionServiceInstance().findByID(1);
        }
        recordDto.setSessionDto(sessionDto);
        recordDto.setName(audioName);
        recordDto.setLength(Float.valueOf((Long) objects[4]));
        recordDto.setPath(((String) objects[1]).replace("\\", "/"));
        recordDto.setStatus("1");
        SingletonServiceUtil.getRecordServiceInstance().saveRecord(recordDto);
        response.sendRedirect("/manager-upload-record.html?urlType=url_list&crudaction=redirect_insert");
    }

    private Map<String, String> buildRedirectionMessageMap() {
        Map<String, String> mapMessage = new HashMap<>();
        mapMessage.put(WebConstants.REDIRECT_INSERT, "Tải lên thành công");
        mapMessage.put(WebConstants.REDIRECT_UPDATE, "Chỉnh sửa template thành công");
        mapMessage.put(WebConstants.REDIRECT_DELETE, "Xóa template thành công");
        mapMessage.put(WebConstants.REDIRECT_ERROR, "Có lỗi xảy ra");
        return mapMessage;
    }

}