package ubnd.web.logic.controller.report;

import org.json.JSONArray;
import org.json.JSONObject;
import ubnd.common.utils.SessionUtils;
import ubnd.core.dto.*;
import ubnd.core.web.common.WebConstants;
import ubnd.core.web.utils.FormUtils;
import ubnd.core.web.utils.SingletonServiceUtil;
import ubnd.core.web.utils.WebCommonUtil;
import ubnd.web.logic.command.ReportCommand;
import ubnd.web.logic.utils.GetMeetingSessionAjaxUtil;
import ubnd.web.logic.utils.TimeUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@WebServlet(urlPatterns = {"/report-list.html", "/report-edit.html", "/ajax-report-edit.html"})
public class ManagerReportController extends HttpServlet {
    ResourceBundle bundle = ResourceBundle.getBundle("ResourcesBundle");
    private final String TAGS = "tags";
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        ReportCommand command = FormUtils.populate(ReportCommand.class, request);
        ReportDto pojo = command.getPojo();
        if(command.getUrlType() != null){
            Map<String, Object> mapProperty = new HashMap<>();
            if(command.getUrlType().equals(WebConstants.URL_LIST)){
                List<ReportDto> reportDtoList = new ArrayList<>();
                Timestamp timeStart = null, timeEnd = null;
                if(command.getTimeStart() != null && !command.getTimeStart().equals("")){
                    timeStart = TimeUtils.datetimePicker2Timestamp(command.getTimeStart());
                }
                if(command.getTimeEnd() != null && !command.getTimeEnd().equals("")){
                    timeEnd = TimeUtils.datetimePicker2Timestamp(command.getTimeEnd());
                }

                if(command.getMeetingId() != null && command.getSessionId() != null){
                    //get report by meeting
                    if(command.getMeetingId() != 0 && (command.getSessionId() == 0)){
                        mapProperty.put("status", 0);
                        mapProperty.put("meetingEntity", command.getMeetingId());
                        List<SessionDto> sessionDtoList = SingletonServiceUtil.getSessionServiceInstance().findByProperty(mapProperty, null, null, null, null);
                        for(SessionDto item: sessionDtoList){
                            List<ReportDto> reportListSession = SingletonServiceUtil.getReportServiceInstance().searchReport(item.getSessionId(), pojo.getName(), timeStart, timeEnd, 0);
                            if(reportListSession.size() != 0){
                                for(ReportDto dto: reportListSession){
                                    reportDtoList.add(dto);
                                }
                            }
                        }
                    }else {
                        reportDtoList = SingletonServiceUtil.getReportServiceInstance().searchReport(command.getSessionId(), pojo.getName(), timeStart, timeEnd, 0);
                    }
                } else if(command.getSpeakerId() != null){
                    //get report by preside
                    if(command.getSpeakerId() != 0 && (command.getSessionId() == null || command.getSessionId() == 0)){
                        mapProperty.put("dutyEntity", 1);
                        mapProperty.put("speakerAttendeesEntity", command.getSpeakerId());
                        List<AttendeesDto> attendeesDtoList = SingletonServiceUtil.getAttendeesServiceInstance().findByProperty(mapProperty, null, null, null, null);
                        for (AttendeesDto item : attendeesDtoList) {
                            List<ReportDto> reportDtoListSpeaker = SingletonServiceUtil.getReportServiceInstance().searchReport(item.getSessionDto().getSessionId(), pojo.getName(), timeStart, timeEnd, 0);
                            if(reportDtoListSpeaker.size() != 0){
                                for(ReportDto dto: reportDtoListSpeaker){
                                    reportDtoList.add(dto);
                                }
                            }
                        }
                    }else {
                        reportDtoList = SingletonServiceUtil.getReportServiceInstance().searchReport(command.getSessionId(), pojo.getName(), timeStart, timeEnd, 0);
                    }
                }
                else {
                    reportDtoList = SingletonServiceUtil.getReportServiceInstance().searchReport(command.getSessionId(), pojo.getName(), timeStart, timeEnd, 0);
                }

                List<ReportDto> list = new ArrayList<>();
                for(ReportDto item: reportDtoList){
                    item.setTag(createHTMLSpanTag(item.getTag()));
                    list.add(item);
                }
                request.setAttribute(WebConstants.FORM_ITEM, command);
                request.setAttribute(WebConstants.LIST_ITEMS, list);

                if(command.getCrudaction() != null){
                    Map<String, String> mapMessage = buidMapRedirectMessage();
                    WebCommonUtil.addRedirectMessage(request, command.getCrudaction(), mapMessage);
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/report/list-report.jsp");
                dispatcher.forward(request, response);
            }
            if(command.getUrlType().equals(WebConstants.URL_EDIT)){
                if(pojo.getReportId() != null){
                    command.setPojo(SingletonServiceUtil.getReportServiceInstance().findById(pojo.getReportId()));
                    request.setAttribute(WebConstants.FORM_ITEM, command);
                }else {
                    command.setPojo(SingletonServiceUtil.getReportServiceInstance().findById(1));
                    request.setAttribute(WebConstants.FORM_ITEM, command);
                }
                //get tag available
                mapProperty.put("title", "tag");
                List<ConfigDto> configDtos = SingletonServiceUtil.getConfigServiceInstance().findByProperty(mapProperty, null, null, null, null);
                if(configDtos != null && configDtos.size() > 0){
                    ConfigDto configDto = configDtos.get(0);
                    String value = configDto.getValue();
                    String[] listTag = value.split(", ");
                    JSONArray array = new JSONArray();
                    for(String item: listTag){
                        array.put(item);
                    }
                    request.setAttribute(TAGS, array);
                }else {
                    JSONArray array = new JSONArray();
                    array.put("Ô nhiễm");
                    array.put("Giao thông");
                    array.put("Y tế");
                    request.setAttribute(TAGS, array);
                }

                RequestDispatcher dispatcher = request.getRequestDispatcher("views/report/edit-report.jsp");
                dispatcher.forward(request, response);
            }
            if(command.getUrlType().equals("ajax_meeting_session")){
                String json = GetMeetingSessionAjaxUtil.getJSONMeetingSession(request);
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write(json);
            }
            if(command.getUrlType().equals("ajax_preside")){
                String json = GetMeetingSessionAjaxUtil.getJSONPreside(request);
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write(json);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        request.setCharacterEncoding("UTF-8");
        ReportCommand command = FormUtils.populate(ReportCommand.class, request);
        ReportDto pojo = command.getPojo();
        //get User Session
        UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);
        TimeUtils timeUtils = new TimeUtils();
        if(command.getUrlType() != null){
            if(command.getUrlType().equals(WebConstants.URL_EDIT)){
                //set DTOs
                SessionDto sessionDto;
                TemplateDto templateDto = new TemplateDto();
                RecordDto recordDto;
                StenographyDto stenographyDto = new StenographyDto();
                if(command.getSessionId() != null){
                    sessionDto = SingletonServiceUtil.getSessionServiceInstance().findByID(command.getSessionId());
                }else {
                    sessionDto = SingletonServiceUtil.getSessionServiceInstance().findByID(1);
                }
                if(command.getTemplateId() != null){
                    templateDto.setTemplateId(command.getTemplateId());
                }else {
                    templateDto.setTemplateId(1);
                }
                if(command.getRecordId() != null){
                    recordDto = SingletonServiceUtil.getRecordServiceInstance().findById(command.getRecordId());
                }else {
                    recordDto = SingletonServiceUtil.getRecordServiceInstance().findById(1);
                }
                if(command.getStenoId() != null){
                    stenographyDto.setStenoId(command.getStenoId());
                    stenographyDto.setSessionDto(sessionDto);
                }else {
                    stenographyDto.setStenoId(1);
                }
                String tag = getValueTag(pojo.getTag());
                pojo.setTag(tag);
                pojo.setSessionDto(sessionDto);
                pojo.setTemplateDto(templateDto);
                pojo.setRecordDto(recordDto);
                pojo.setStenographyDto(stenographyDto);
                pojo.setCreUID(userDto.getUserId());
                pojo.setCreDate(timeUtils.getCurrentTimestamp());
                pojo.setModUID(userDto.getUserId());
                pojo.setModDate(timeUtils.getCurrentTimestamp());

                ReportDto dto = SingletonServiceUtil.getReportServiceInstance().updateReport(pojo);
                response.sendRedirect("/report-list.html?urlType=url_list&crudaction=redirect_update");
            }
            if(command.getUrlType().equals(WebConstants.URL_DELETE)){
                String reportId = request.getParameter("reportId");
                if(reportId != null){
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write("Remove Success");
                }
            }
        }

    }
    private Map<String,String> buidMapRedirectMessage() {
        Map<String, String> mapMessage = new HashMap<>();
        mapMessage.put(WebConstants.REDIRECT_INSERT, "Tạo mới biên bản thành công");
        mapMessage.put(WebConstants.REDIRECT_UPDATE, "Chỉnh sửa biên bản thành công");
        mapMessage.put(WebConstants.REDIRECT_DELETE, "Xóa biên bản thành công");
        mapMessage.put(WebConstants.REDIRECT_ERROR, "Có lỗi xảy ra");
        return mapMessage;
    }

    private String createHTMLSpanTag(String tag){
        StringBuilder span = new StringBuilder();
        String[] list = tag.split(", ");
        for (String item: list){
            span.append("<span class='badge badge-secondary'>#").append(item).append("</span>").append("\n");
        }
        return span.toString();
    }

    private String getValueTag(String tagValue){
        StringBuilder value = new StringBuilder();
        JSONArray jsonArray = new JSONArray(tagValue);
        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject object = jsonArray.getJSONObject(i);
            if(value.toString().equals("")){
                value.append(object.getString("value"));
            }else {
                value.append(", ").append(object.getString("value"));
            }
        }
        return value.toString();
    }
}
