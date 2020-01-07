package ubnd.web.logic.controller.audio;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import ubnd.common.constant.CoreConstant;
import ubnd.common.utils.FileUtils;
import ubnd.common.utils.SessionUtils;
import ubnd.core.dto.*;
import ubnd.core.web.common.WebConstants;
import ubnd.core.web.utils.FormUtils;
import ubnd.core.web.utils.SingletonServiceUtil;
import ubnd.web.logic.command.TranscriptCommand;
import ubnd.web.logic.utils.TimeUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/report-transcript.html", "/ajax-transcript.html"})
public class TranscriptController extends HttpServlet {
    ResourceBundle bundle = ResourceBundle.getBundle("ResourcesBundle");
    private final String RECORD = "record";
    private final String TRANSCRIPTS = "transcripts";
    private final String TRANSCRIPT = "transcript";
    private final String ATTENDEES = "attendees";

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        TranscriptCommand command = FormUtils.populate(TranscriptCommand.class, request);
        TranscriptDto pojo;
        UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);
        TimeUtils timeUtils = new TimeUtils();
        if (command.getStep() != null) {
            if (command.getStep() == 1) {
                //Step 1
                if (command.getMeetingId() != null) {
                    Map<String, Object> mapProperty = new HashMap<>();
                    if (command.getSessionId() != null) {
                        //get list audio
                        mapProperty.put("sessionRecordEntity", command.getSessionId());
                        List<RecordDto> recordDtoList = SingletonServiceUtil.getRecordServiceInstance().findByProperty(mapProperty, null, null, null, null);
                        Gson gson = new Gson();
                        String json = gson.toJson(recordDtoList);
                        response.setContentType("text/html;charset=UTF-8");
                        response.getWriter().write(json);
                    } else {
                        //get list session
                        mapProperty.put("status", 0);
                        mapProperty.put("meetingEntity", command.getMeetingId());
                        List<SessionDto> sessionDtoList = SingletonServiceUtil.getSessionServiceInstance().findByProperty(mapProperty, null, null, null, null);
                        Gson gson = new Gson();
                        String json = gson.toJson(sessionDtoList);
                        response.setContentType("text/html;charset=UTF-8");
                        response.getWriter().write(json);
                    }
                } else {
                    //get list meeting
                    Map<String, Object> mapProperty = new HashMap<>();
                    mapProperty.put("status", 0);
                    List<MeetingDto> meetingDtoList = SingletonServiceUtil.getMeetingServiceInstance().findByProperty(mapProperty, null, null, null, null);
                    request.setAttribute("meetingList", meetingDtoList);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("views/transcript/transcript-report-step-1.jsp");
                    dispatcher.forward(request, response);
                }
            } else if (command.getStep() == 2) {
                //Step 2
                if (command.getRecordId() != null && !command.getRecordId().equals(0)) {
                    RecordDto recordDto = SingletonServiceUtil.getRecordServiceInstance().findById(command.getRecordId());
                    recordDto.setLength(recordDto.getLength()/1024/1024);
                    request.setAttribute(RECORD, recordDto);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("views/transcript/transcript-report-step-2.jsp");
                    dispatcher.forward(request, response);
                } else {
                    response.sendRedirect("/report-transcript.html?step=1");
                }
            } else if (command.getStep() == 3) {
                //Step 3
                if (command.getRecordId() != null && !command.getRecordId().equals(0)) {
                    //get list transcript
                    Map<String, Object> mapProperty = new HashMap<>();
                    mapProperty.put("recordTranscriptEnity", command.getRecordId());
                    List<TranscriptDto> transcriptDtoList = SingletonServiceUtil.getTranscriptServiceInstance().findByProperty(mapProperty, null, null, null, null);
                    //get list attendees
                    RecordDto recordDto = SingletonServiceUtil.getRecordServiceInstance().findById(command.getRecordId());
                    Map<String, Object> mapPropertyAttendees = new HashMap<>();
                    mapPropertyAttendees.put("sessionAttendeesEntity", recordDto.getSessionDto().getSessionId());
                    List<AttendeesDto> attendeesDtoList = SingletonServiceUtil.getAttendeesServiceInstance().findByProperty(mapPropertyAttendees, null, null, null, null);
                    if (command.getSpeaker() != null) {
                        if (command.getSpeaker().equals("all") || command.getSpeaker().equals("")) {
                            request.setAttribute("speaker", 0);
                            pojo = getTranscriptBySpeaker("all", transcriptDtoList);
                        } else {
                            request.setAttribute("speaker", command.getSpeaker());
                            pojo = getTranscriptBySpeaker(command.getSpeaker(), transcriptDtoList);
                        }
                    } else {
                        request.setAttribute("speaker", 0);
                        pojo = getTranscriptBySpeaker("all", transcriptDtoList);
                    }
                    transcriptDtoList = setNameSpeaker(transcriptDtoList, attendeesDtoList);
                    Gson gson = new Gson();
                    String jsonTranscripts = gson.toJson(transcriptDtoList);
                    String jsonAttendees = gson.toJson(attendeesDtoList);
                    if (pojo.getContent() != null) {
                        if (!pojo.getContent().equals("")) {
                            String address = FileUtils.getRealPath(request, CoreConstant.FOLDER_FILE);
                            String[] contentPath = pojo.getContent().split(";");
                            String content = FileUtils.readContentFile(address + File.separator + contentPath[contentPath.length - 1]);
                            pojo.setContent(content);
                        }
                    }
                    request.setAttribute(TRANSCRIPT, pojo);
                    request.setAttribute(TRANSCRIPTS, transcriptDtoList);
                    request.setAttribute(ATTENDEES, jsonAttendees);
                    request.setAttribute("recordId", command.getRecordId());
                    request.setAttribute("jsonTranscripts", jsonTranscripts);
                    request.setAttribute("arrayAnnotations", getListTranscriptTextByFile(request, pojo.getJsonPath()));
                    RequestDispatcher dispatcher = request.getRequestDispatcher("views/transcript/transcript-report-step-3.jsp");
                    dispatcher.forward(request, response);
                } else {
                    response.sendRedirect("/report-transcript.html?step=1");
                }
            } else {
                response.sendRedirect("/report-transcript.html?step=1");
            }
        } else {
            response.sendRedirect("/report-transcript.html?step=1");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        TranscriptCommand command = FormUtils.populate(TranscriptCommand.class, request);
        TranscriptDto pojo = command.getPojo();
        UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);

        if (command.getStep() != null) {
            if (command.getStep() == 1) {

            } else if (command.getStep() == 2) {
                String complete = request.getParameter("complete");
                if (command.getRecordId() != null && complete == null) {
                    String path = request.getParameter("pathAudio");
                    String address = FileUtils.getRealPath(request, CoreConstant.FOLDER_FILE);
                    String fullPath = address + File.separator + path;
                    String uploadAudio = SingletonServiceUtil.getRecordServiceInstance().uploadAudio(fullPath);

                    //update status process
                    RecordDto recordDto = SingletonServiceUtil.getRecordServiceInstance().findById(command.getRecordId());
                    recordDto.setStatus("4");
                    recordDto.setProcessingInfo(uploadAudio);
                    SingletonServiceUtil.getRecordServiceInstance().updateRecord(recordDto);

                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write(uploadAudio);
                }
                if (complete != null) {
                    //get full transcript from 24
                    String data = request.getParameter("data");
                    String pathAudio = request.getParameter("pathAudio");
                    String recordId = request.getParameter("recordId");
                    JSONObject json = new JSONObject(data);
                    String meetingId = json.getString("meetingId");
                    String token = json.getString("accessToken");
                    String fullTranscription = SingletonServiceUtil.getRecordServiceInstance().getFullTranscription(token, meetingId);
                    //write file xml and json
                    String filename = TimeUtils.getCurrentTimestamp().toString();
                    filename = filename.replace(":", "-");
                    filename = filename.replace(".", "");
                    String folderName = "result24" + File.separator + "Record" + recordId;
                    String nameFileXML = filename + ".xml";
                    String xmlPath = SingletonServiceUtil.getTranscriptServiceInstance().createXML(request, folderName, nameFileXML, fullTranscription).replace("\\", "/");
                    String nameFileJSON = filename + ".json";
                    String jsonPath = SingletonServiceUtil.getTranscriptServiceInstance().writeJSONContent(request, folderName, nameFileJSON, fullTranscription).replace("\\", "/");
                    //save in db
                    RecordDto recordDto = SingletonServiceUtil.getRecordServiceInstance().findById(Integer.valueOf(recordId));

                    TranscriptDto transcriptDto = new TranscriptDto();
                    transcriptDto.setRecordDto(recordDto);
                    transcriptDto.setSpeakerId("all");
                    transcriptDto.setAudioPath(pathAudio);
                    transcriptDto.setContent("");
                    transcriptDto.setJsonPath(jsonPath);
                    transcriptDto.setXmlPath(xmlPath);
                    SingletonServiceUtil.getTranscriptServiceInstance().save(transcriptDto);

                    recordDto.setStatus("5");
                    SingletonServiceUtil.getRecordServiceInstance().updateRecord(recordDto);

                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write("success");
                }
            } else if (command.getStep() == 3) {
                String content = request.getParameter("content");
                Integer transcriptId = Integer.valueOf(request.getParameter("transcriptId"));
                String folderName = "result24" + File.separator + "Session1" + File.separator + "Record1";
                String filename = TimeUtils.getCurrentTimestamp().toString();
                filename = filename.replace(":", "-");
                filename = filename.replace(".", "");
                filename = filename + ".txt";
                content = SingletonServiceUtil.getTranscriptServiceInstance().writeJSONContent(request, folderName, filename, content).replace("\\", "/");
                if (transcriptId != null) {
                    pojo = SingletonServiceUtil.getTranscriptServiceInstance().findById(transcriptId);
                    if (pojo.getContent().equals("")) {
                        pojo.setContent(content);
                    } else {
                        content = pojo.getContent() + ";" + content;
                        pojo.setContent(content);
                    }
                    SingletonServiceUtil.getTranscriptServiceInstance().update(pojo);
                }

                String timeSave = TimeUtils.getCurrentTimestamp().toString();
                timeSave = TimeUtils.timestamp2DatetimePicker(timeSave);
//                response.setContentType("text/html;charset=UTF-8");
//                response.getWriter().write(timeSave);

                request.setAttribute(WebConstants.MESSAGE_RESPONSE, WebConstants.REDIRECT_UPDATE);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/transcript/transcript-report-step-3.jsp");
//                String transcriptId = request.getParameter("transcriptId");
//                if(transcriptId != null){
//                    String xmlPath = request.getParameter("xmlPath");
//                    String content = request.getParameter("content");
//                    String speaker = request.getParameter("speaker");
//                    SingletonServiceUtil.getTranscriptServiceInstance().updateXML(request, xmlPath, content, speaker);
//                    String time = TimeUtils.getCurrentTimestamp().toString();
//                    response.setContentType("text/html;charset=UTF-8");
//                    response.getWriter().write(time);
//                }
            }
        }
    }

    private List<TranscriptDto> setNameSpeaker(List<TranscriptDto> transcriptList, List<AttendeesDto> attendeesList) {
        List<TranscriptDto> list = new ArrayList<>();
        for (TranscriptDto item : transcriptList) {
            TranscriptDto dto = new TranscriptDto();
            dto.setTranscriptId(item.getTranscriptId());
            dto.setRecordDto(item.getRecordDto());
            dto.setSpeakerId(item.getSpeakerId());
            if (item.getSpeakerId().equals("all")) {
                dto.setSpeaker("Tất cả mọi người");
            } else {
                AttendeesDto attendeesDto = getSpeakerByID(Integer.valueOf(item.getSpeakerId()), attendeesList);
                if (attendeesDto != null) {
                    dto.setSpeaker(attendeesDto.getSpeakerDto().getFullName());
                } else {
                    dto.setSpeaker("Không có thông tin");
                }
            }
            dto.setAudioPath(item.getAudioPath());
            dto.setContent(item.getContent());
            dto.setJsonPath(item.getJsonPath());
            dto.setXmlPath(item.getXmlPath());
            list.add(dto);
        }
        return list;
    }

    private AttendeesDto getSpeakerByID(Integer id, List<AttendeesDto> attendeesList) {
        for (AttendeesDto item : attendeesList) {
            if (item.getSpeakerDto().getSpeakerId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    private TranscriptDto getTranscriptBySpeaker(String speaker, List<TranscriptDto> transcriptList) {
        for (TranscriptDto item : transcriptList) {
            if (item.getSpeakerId().equals(speaker)) {
                return item;
            }
        }
        return null;
    }

    private JSONArray getListTranscriptTextByFile(HttpServletRequest request, String path) {
        String address = FileUtils.getRealPath(request, CoreConstant.FOLDER_FILE);
        String fileLocation = address + File.separator + path;
        try {
            String data = FileUtils.readContentFile(fileLocation);
            JSONObject objectFile = new JSONObject(data);
            JSONArray arrayData = objectFile.getJSONArray("data");
            JSONObject objectData = arrayData.getJSONObject(0);
            JSONArray jsonArray = objectData.getJSONArray("transcript_info");

            return jsonArray;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
