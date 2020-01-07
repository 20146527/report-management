package ubnd.web.logic.controller.report;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import ubnd.common.constant.CoreConstant;
import ubnd.common.utils.FileUtils;
import ubnd.common.utils.SessionUtils;
import ubnd.core.dto.*;
import ubnd.core.web.common.WebConstants;
import ubnd.core.web.utils.FormUtils;
import ubnd.core.web.utils.SingletonServiceUtil;
import ubnd.core.web.utils.WebCommonUtil;
import ubnd.web.logic.command.ReportCommand;
import ubnd.web.logic.command.TemplateCommand;
import ubnd.web.logic.utils.TimeUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/report-create.html", "/ajax-create-report.html"})
public class CreateReportController extends HttpServlet {
    ResourceBundle bundle = ResourceBundle.getBundle("ResourcesBundle");
    private final String TAGS = "tags";
    private final String DATA_REPORT_INSERT = "data_report_insert";
    private final String REPORT = "report";
    private final String TRANSCRIPT = "transcript";
    private final String SESSION_ID = "sessionId";
    private final String RECORD_ID = "recordId";
    private final String STENO_ID = "stenoId";
    private final String TEMPLATE_ID = "templateId";
    private final String MEETING_LIST = "meetingList";
    private final String TEMPLATE_LIST = "templateList";


    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        ReportCommand command = FormUtils.populate(ReportCommand.class, request);
        ReportDto pojo = command.getPojo();
        UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);
        TimeUtils timeUtils = new TimeUtils();
        if (command.getStep() != null) {
            if (command.getStep() == 1) {
                //Step 1
                if (command.getMeetingId() != null) {
                    if (command.getSessionId() != null) {
                        //get list audio
                        Map<String, Object> mapAudioProperty = new HashMap<>();
                        mapAudioProperty.put("sessionRecordEntity", command.getSessionId());
                        List<RecordDto> recordDtoList = SingletonServiceUtil.getRecordServiceInstance().findByProperty(mapAudioProperty, null, null, null, null);
                        Gson gson = new Gson();
                        String audioJson = gson.toJson(recordDtoList);
                        //get list steno
                        Map<String, Object> mapStenoProperty = new HashMap<>();
                        mapStenoProperty.put("sessionStenoEntity", command.getSessionId());
                        List<StenographyDto> stenographyDtoList = SingletonServiceUtil.getStenographyService().findByProperty(mapStenoProperty, null, null, null, null);
                        String stenoJson = gson.toJson(stenographyDtoList);
                        String json = "[" + audioJson + "," + stenoJson + "]";
                        response.setContentType("text/html;charset=UTF-8");
                        response.getWriter().write(json);
                    } else {
                        //get list session
                        Map<String, Object> mapProperty = new HashMap<>();
                        mapProperty.put("status", 0);
                        mapProperty.put("meetingEntity", command.getMeetingId());
                        List<SessionDto> sessionDtoList = SingletonServiceUtil.getSessionServiceInstance().findByProperty(mapProperty, null, null, null, null);
                        Gson gson = new Gson();
                        String json = gson.toJson(sessionDtoList);
                        response.setContentType("text/html;charset=UTF-8");
                        response.getWriter().write(json);
                    }
                } else {
                    //get list meeting, list template
                    Map<String, Object> mapProperty = new HashMap<>();
                    mapProperty.put("status", 0);
                    List<MeetingDto> meetingDtoList = SingletonServiceUtil.getMeetingServiceInstance().findByProperty(mapProperty, null, null, null, null);
                    request.setAttribute(MEETING_LIST, meetingDtoList);
                    List<TemplateDto> templateDtoList = SingletonServiceUtil.getTemplateServiceInstance().findByProperty(mapProperty, null, null, null, null);
                    request.setAttribute(TEMPLATE_LIST, templateDtoList);

                    RequestDispatcher dispatcher = request.getRequestDispatcher("views/report/create-report-step-1.jsp");
                    dispatcher.forward(request, response);
                }
            } else if (command.getStep() == 2) {
                //Step 2
                if(command.getRecordId() != null){
                    Map<String, Object> mapTranscriptProperty = new HashMap<>();
                    mapTranscriptProperty.put("recordTranscriptEnity", command.getRecordId());
                    mapTranscriptProperty.put("speakerId", "all");
                    List<TranscriptDto> transcriptDtoList = SingletonServiceUtil.getTranscriptServiceInstance().findByProperty(mapTranscriptProperty, null, null, null, null);
                    if(transcriptDtoList != null){
                        TranscriptDto transcriptDto = transcriptDtoList.get(0);
                        if(transcriptDto.getContent() != null &&!transcriptDto.getContent().equals("")){
                            String address = FileUtils.getRealPath(request, CoreConstant.FOLDER_FILE);
                            String[] contentPath = transcriptDto.getContent().split(";");
                            String content = FileUtils.readContentFile(address + File.separator + contentPath[contentPath.length - 1]);
                            transcriptDto.setContent(content);
                        }
                        request.setAttribute(TRANSCRIPT, transcriptDto);
                    }
                    request.setAttribute(RECORD_ID, command.getRecordId());
                }
                if(command.getSessionId() != null){
                    request.setAttribute(SESSION_ID, command.getSessionId());
                }
                if(command.getStenoId() != null){
                    request.setAttribute(STENO_ID, command.getStenoId());
                }
                if(command.getTemplateId() != null){
                    request.setAttribute(TEMPLATE_ID, command.getTemplateId());
                }

                RequestDispatcher dispatcher = request.getRequestDispatcher("views/report/create-report-step-2.jsp");
                dispatcher.forward(request, response);

            } else if (command.getStep() == 3) {
                //Step 3
                //set template default
                TemplateDto templateDto;
                if(command.getTemplateId() != null){
                    templateDto = SingletonServiceUtil.getTemplateServiceInstance().findById(command.getTemplateId());
                }else {
                    templateDto = SingletonServiceUtil.getTemplateServiceInstance().findById(1);
                }

                pojo.setContent(templateDto.getContent());
                //set name default
                String nameDefault = "Report_" + userDto.getUserName() + "_" + timeUtils.getCurrentTime();
                pojo.setName(nameDefault);
                request.setAttribute(REPORT, command);
                //data raw
                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("template1", "Chu toa");
//                jsonObject.put("template2", "Thu ky");
//                jsonObject.put("template3", "Thoi gian hop"); //
//                jsonObject.put("template4", "Dia diem hop"); //
                jsonObject.put("template5", "Báo cáo ĐATN");
//                jsonObject.put("template6", "So nguoi tham du");
//                jsonObject.put("template7", "Đây là phần ghi nội dung họp sau khi ghép");

                if(command.getSessionId() != null){
                    SessionDto sessionDto = SingletonServiceUtil.getSessionServiceInstance().findByID(command.getSessionId());
                    String room = sessionDto.getRoomDto().getRoomName() + " " + sessionDto.getRoomDto().getRoomDescription();
                    String time = sessionDto.getTimeStart().toString();
                    jsonObject.put("template3", time); //
                    jsonObject.put("template4", room); //

                    Map<String, Object> mapAttendeesProperty = new HashMap<>();
                    mapAttendeesProperty.put("status", 2);
                    mapAttendeesProperty.put("status", 2);
                    mapAttendeesProperty.put("sessionAttendeesEntity", sessionDto.getSessionId());
                    List<AttendeesDto> attendeesDtoList = SingletonServiceUtil.getAttendeesServiceInstance().findByProperty(mapAttendeesProperty, null, null, null, null);
                    jsonObject.put("template6", attendeesDtoList.size());
                    for(AttendeesDto item: attendeesDtoList){
                        if(item.getDutyDto().getDutyId().equals(1)){
                            jsonObject.put("template1", item.getSpeakerDto().getFullName());
                        }
                        if(item.getDutyDto().getDutyId().equals(2)){
                            jsonObject.put("template2", item.getSpeakerDto().getFullName());
                        }

                    }
                    request.setAttribute(SESSION_ID, command.getSessionId());
                }
                if(command.getRecordId() != null){
//                    String content = "";
//                    Map<String, Object> mapTranscriptProperty = new HashMap<>();
//                    mapTranscriptProperty.put("recordTranscriptEnity", command.getRecordId());
//                    mapTranscriptProperty.put("speakerId", "all");
//                    Object[] objects = SingletonServiceUtil.getTranscriptServiceInstance().findByProperty(mapTranscriptProperty, null, null, null, null);
//                    List<TranscriptDto> transcriptDtoList = (List<TranscriptDto>) objects[1];
//                    if(transcriptDtoList != null){
//                        TranscriptDto transcriptDto = transcriptDtoList.get(0);
//                        String address = FileUtils.getRealPath(request, CoreConstant.FOLDER_FILE);
//                        String pathXML = address + File.separator + transcriptDto.getXmlPath();
//                        List<ParagraphTranscriptDto> paragraphList = SingletonServiceUtil.getTranscriptServiceInstance().getParagraphTransByXML(pathXML);
//                        for(ParagraphTranscriptDto item: paragraphList){
//                            content += item.getText() + " ";
//                        }
//                        jsonObject.put("template7", content);
//                    }
                    request.setAttribute(RECORD_ID, command.getRecordId());
                }
//                //Get Cookie
//                Cookie[] cookies = null;
//                cookies = request.getCookies();
//                if(cookies != null){
//                    for(Cookie item: cookies){
//                        if(item.getName().equals("reportContent")){
//                            jsonObject.put("template7", item.getValue());
//                        }
//                    }
//                }
                jsonObject.put("template7", WebConstants.REPORT_CONTENT);
                if(command.getStenoId() != null){
                    request.setAttribute(STENO_ID, command.getStenoId());
                }
                request.setAttribute(DATA_REPORT_INSERT, jsonObject);
                //get tag available
                Map<String, Object> mapTagProperty = new HashMap<>();
                mapTagProperty.put("title", "tag");
//                mapTagProperty.put("modUID", userDto.getUserId());
                List<ConfigDto> configDtos = SingletonServiceUtil.getConfigServiceInstance().findByProperty(mapTagProperty, null, null, null, null);
                if(configDtos != null){
                    ConfigDto configDto = configDtos.get(0);
                    String value = configDto.getValue();
                    String[] listTag = value.split(", ");
                    JSONArray array = new JSONArray();
                    for(String item: listTag){
                        array.put(item);
                    }
                    request.setAttribute(TAGS, array);
                }
                if(command.getCrudaction() != null){
                    Map<String, String> mapMessage = buidMapRedirectMessage();
                    WebCommonUtil.addRedirectMessage(request, command.getCrudaction(), mapMessage);
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/report/create-report-step-3.jsp");
                dispatcher.forward(request, response);
            } else if (command.getStep() == 4) {
                //Step 4
                String address = FileUtils.getRealPath(request, CoreConstant.FOLDER_FILE);
                String path = address + File.separator + WebConstants.RESULT_24 + File.separator + "kq-vnist.json";
                //List<TextTranscriptDto> textTranscriptDtoList = SingletonServiceUtil.getReportServiceInstance().getListTextTranscription(path);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/report/create-report-step-4.jsp");
                dispatcher.forward(request, response);
            } else if (command.getStep() == 5) {
                //set template default
                TemplateDto templateDto = SingletonServiceUtil.getTemplateServiceInstance().findById(1);
                pojo.setContent(templateDto.getContent());
                //set name default
                String nameDefault = "Report_" + userDto.getUserName() + "_" + timeUtils.getCurrentTime();
                pojo.setName(nameDefault);
                request.setAttribute(REPORT, command);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/report/create-report-step-5.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect("/report-create.html?step=1");
            }
        } else {
            response.sendRedirect("/report-create.html?step=1");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ReportCommand command = FormUtils.populate(ReportCommand.class, request);
        ReportDto pojo = command.getPojo();
        UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);
        TimeUtils timeUtils = new TimeUtils();
        if (command.getStep() != null) {
            if (command.getStep() == 1) {

            } else if (command.getStep() == 2) {
                if(command.getUrlType().equals("url_next_step")){
                    String transcriptContent = request.getParameter("transcriptContent");
                    WebConstants.REPORT_CONTENT = Jsoup.parse(transcriptContent).text();
                    String stenoContent = request.getParameter("stenoContent");

//                    Cookie reportCookie = new Cookie("reportContent", "Hùng");
//                    reportCookie.setMaxAge(60*60*8);
//                    response.addCookie(reportCookie);CoreConstant.REPORT_CONTENT =

                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write("success");

                }

            } else if (command.getStep() == 3) {
                System.out.println(command.getPojo().getContent());
                //set DTOs
                SessionDto sessionDto = new SessionDto();
                TemplateDto templateDto = new TemplateDto();
                RecordDto recordDto = new RecordDto();
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

                SingletonServiceUtil.getReportServiceInstance().saveReport(pojo);
                response.sendRedirect("/report-list.html?urlType=url_list&crudaction=redirect_insert");
//                request.setAttribute(WebConstants.MESSAGE_RESPONSE, WebConstants.REDIRECT_INSERT);
//                RequestDispatcher dispatcher = request.getRequestDispatcher("views/report/create-report-step-3.jsp");
//                dispatcher.forward(request, response);


            } else if (command.getStep() == 4) {

            } else if (command.getStep() == 5) {

            }
        }
    }

    private Map<String,String> buidMapRedirectMessage() {
        Map<String, String> mapMessage = new HashMap<>();
        mapMessage.put(WebConstants.REDIRECT_INSERT, "Lưu biên bản thành công");
        mapMessage.put(WebConstants.REDIRECT_UPDATE, "Chỉnh sửa thành công");
        mapMessage.put(WebConstants.REDIRECT_DELETE, "Xóa thành công");
        mapMessage.put(WebConstants.REDIRECT_ERROR, "Có lỗi xảy ra");
        return mapMessage;
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
