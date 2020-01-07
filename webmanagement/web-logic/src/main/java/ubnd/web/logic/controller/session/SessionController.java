package ubnd.web.logic.controller.session;

import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import ubnd.common.constant.CoreConstant;
import ubnd.common.utils.ExcelPoiUtil;
import ubnd.common.utils.FileUtils;
import ubnd.common.utils.SessionUtils;
import ubnd.common.utils.UploadUtils;
import ubnd.core.dto.*;
import ubnd.core.web.common.WebConstants;
import ubnd.core.web.utils.FormUtils;
import ubnd.core.web.utils.SingletonServiceUtil;
import ubnd.core.web.utils.WebCommonUtil;
import ubnd.web.logic.command.MeetingCommand;
import ubnd.web.logic.command.SessionCommand;
import ubnd.web.logic.utils.DownloadUtil;
import ubnd.web.logic.utils.TimeUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@WebServlet(urlPatterns = {"/manager-session-list.html", "/manager-session-detail.html", "/ajax-session-edit.html", "/manager-session-import.html"})
public class SessionController extends HttpServlet {
    ResourceBundle bundle = ResourceBundle.getBundle("ResourcesBundle");
    private final String READ_EXCEL_SESSION = "read_excel_session";
    private final String LIST_SESSION_IMPORT = "list_session_import";
    private final String IMPORT_SESSION_DATA = "import_session_data";
    private final String URL_IMPORT_SESSION = "url_import_session";
    private final String URL_VALIDATE_SESSION = "url_validate_session";

    private final String URL_IMPORT_ATTENDEES = "url_import_attendees";
    private final String READ_EXCEL_ATTENDEES = "read_excel_attendees";
    private final String LIST_ATTENDEES_IMPORT = "list_attendees_import";
    private final String IMPORT_ATTENDEES_DATA = "import_attendees_data";
    private final String URL_VALIDATE_ATTENDEES = "url_validate_attendees";

    private final String URL_GET_MEMBER = "url_get_member";
    private final String URL_ADD_MEMBER = "url_add_member";
    private final String URL_REMOVE_MEMBER = "url_remove_member";
    private final String URL_ATTENDANCE_MEMBER = "url_attendance_member";

    private final String MEETING_LIST = "meetingList";
    private final String MEETING_INFO = "meetingInfo";
    private final String ROOM_LIST = "roomList";

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        MeetingCommand meetingCommand = new MeetingCommand();
        SessionCommand command = FormUtils.populate(SessionCommand.class, request);
        SessionDto pojo = command.getPojo();

        if(command.getUrlType() != null){
            if (command.getUrlType().equals(WebConstants.URL_LIST)) {
                String meetingId = request.getParameter("meetingId");
                Integer meetingSearchId = command.getMeetingSearchId();
//                Map<String, Object> mapProperty = new HashMap<>();
                if (meetingId != null && !meetingId.equals("")) {
//                    mapProperty.put("meetingEntity", meetingId);
                    meetingSearchId = Integer.valueOf(meetingId);
                    Integer id = Integer.valueOf(meetingId);
                    MeetingDto meetingDto = SingletonServiceUtil.getMeetingServiceInstance().findById(id);
                    meetingCommand.setPojo(meetingDto);
                    meetingCommand.setTimeStart(TimeUtils.timestamp2DatetimePicker(meetingDto.getTimeStart().toString()));
                    meetingCommand.setTimeEnd(TimeUtils.timestamp2DatetimePicker(meetingDto.getTimeEnd().toString()));
                    request.setAttribute(MEETING_INFO, meetingCommand);
                }else {
                    Map<String, Object> mapProperty = new HashMap<>();
                    mapProperty.put("status", 0);
                    List<MeetingDto> meetingDtoList = SingletonServiceUtil.getMeetingServiceInstance().findByProperty(mapProperty, null, null, null, null);
                    request.setAttribute(MEETING_LIST, meetingDtoList);
                    List<RoomDto> roomDtoList = SingletonServiceUtil.getRoomServiceInstance().findAll();
                    request.setAttribute(ROOM_LIST, roomDtoList);
                }
//                mapProperty.put("status", "0");
//                Object[] sessionObjects = SingletonServiceUtil.getSessionServiceInstance().findByProperty(mapProperty, command.getSortExpression(), command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
//                List<SessionDto> sessionDtoList = (List<SessionDto>) sessionObjects[1];
                Timestamp timeStart = null, timeEnd = null;
                if(command.getTimeStart() != null || command.getTimeEnd() != null){
                    if (!command.getTimeStart().equals("")) {
                        timeStart = TimeUtils.datetimePicker2Timestamp(command.getTimeStart());
                    }
                    if (!command.getTimeEnd().equals("")) {
                        timeEnd = TimeUtils.datetimePicker2Timestamp(command.getTimeEnd());
                    }
                }
                List<SessionDto> sessionDtoList = SingletonServiceUtil.getSessionServiceInstance().searchSession(meetingSearchId, command.getRoomId(), pojo.getName(), pojo.getDescription(), timeStart, timeEnd, 0);
                request.setAttribute(WebConstants.FORM_ITEM, command);
                request.setAttribute(WebConstants.LIST_ITEMS, sessionDtoList);
                if (command.getCrudaction() != null) {
                    Map<String, String> mapMessage = buildRedirectionMessageMap();
                    WebCommonUtil.addRedirectMessage(request, command.getCrudaction(), mapMessage);
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/session/list-session.jsp");
                dispatcher.forward(request, response);
            }
            if (command.getUrlType().equals(WebConstants.URL_EDIT)) {
                String meetingId = request.getParameter("meetingId");
                if (pojo != null && pojo.getSessionId() != null) {
                    SessionDto sessionDto = SingletonServiceUtil.getSessionServiceInstance().findByID(pojo.getSessionId());
//                    LocalDateTime timeStart = timeUtils.getLocalDateByTimestamp(sessionDto.getTimeStart());
//                    LocalDateTime timeEnd = timeUtils.getLocalDateByTimestamp(sessionDto.getTimeEnd());
                    String timeStart = TimeUtils.timestamp2DatetimePicker(sessionDto.getTimeStart().toString());
                    String timeEnd = TimeUtils.timestamp2DatetimePicker(sessionDto.getTimeEnd().toString());
                    command.setTimeStart(timeStart);
                    command.setTimeEnd(timeEnd);
                    command.setPojo(sessionDto);
                }
                if(meetingId != null && !meetingId.equals("")){
                    request.setAttribute("meetingId", meetingId);
                }else {
                    Map<String, Object> mapProperty = new HashMap<>();
                    mapProperty.put("status", 0);
                    List<MeetingDto> meetingDtoList = SingletonServiceUtil.getMeetingServiceInstance().findByProperty(mapProperty, null, null, null, null);
                    request.setAttribute(MEETING_LIST, meetingDtoList);
                }
                List<RoomDto> roomDtoList = SingletonServiceUtil.getRoomServiceInstance().findAll();
                request.setAttribute(ROOM_LIST, roomDtoList);
                request.setAttribute(WebConstants.FORM_ITEM, command);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/session/edit-session.jsp");
                dispatcher.forward(request, response);
            }
            if (command.getUrlType().equals(WebConstants.URL_DETAIL)) {
                String sessionId = request.getParameter("sessionId");
                request.setAttribute("sessionId", sessionId);
                if(sessionId != null){
                    //get session info
                    SessionDto sessionDto = SingletonServiceUtil.getSessionServiceInstance().findByID(Integer.valueOf(sessionId));
                    command.setPojo(sessionDto);
                    command.setTimeStart(TimeUtils.timestamp2DatetimePicker(sessionDto.getTimeStart().toString()));
                    command.setTimeEnd(TimeUtils.timestamp2DatetimePicker(sessionDto.getTimeEnd().toString()));
                }
                request.setAttribute(WebConstants.FORM_ITEM, command);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/session/detail-session.jsp");
                dispatcher.forward(request, response);
            }
            if(command.getUrlType().equals(URL_GET_MEMBER)){
                String sessionId = request.getParameter("sessionId");
                List<AttendeesDto> attendeesDtoList = new ArrayList<>();
                Map<String, Object> mapProperty = new HashMap<>();
                mapProperty.put("status", 0);
                List<SpeakerDto> speakerDtoList = SingletonServiceUtil.getSpeakerServiceInstance().findByProperty(mapProperty, null, null, null, null);
                if (sessionId != null) {
                    //get list attendees
                    Map<String, Object> mapPropertyAttendees = new HashMap<>();
                    mapPropertyAttendees.put("sessionAttendeesEntity", sessionId);
                    attendeesDtoList = SingletonServiceUtil.getAttendeesServiceInstance().findByProperty(mapPropertyAttendees, null, null, null, null);
                }

                //filter Speaker added AttendeesList
                for(AttendeesDto attendeesDto: attendeesDtoList){
                    for(SpeakerDto speakerDto: speakerDtoList){
                        if(speakerDto.getSpeakerId().equals(attendeesDto.getSpeakerDto().getSpeakerId())){
                            speakerDtoList.remove(speakerDto);
                            break;
                        }
                    }
                }

                Object[] objects = new Object[]{attendeesDtoList, speakerDtoList};
                Gson gson = new Gson();
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write(gson.toJson(objects));
            }
            if (command.getUrlType().equals(WebConstants.URL_DELETE)) {
                request.setAttribute(WebConstants.FORM_ITEM, command);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/session/remove-session.jsp");
                dispatcher.forward(request, response);
            }
            if (command.getUrlType().equals(URL_IMPORT_SESSION)) {
                //Hien thi giao dien nhap file excel phien hop
                String meetingId = request.getParameter("meetingId");
                request.setAttribute("meetingId", meetingId);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/session/import-session.jsp");
                dispatcher.forward(request, response);
            }
            if (command.getUrlType().equals(URL_VALIDATE_SESSION)) {
                //Tra ket qua doc du lieu tu file excel phien hop
                String meetingId = request.getParameter("meetingId");
                request.setAttribute("meetingId", meetingId);
                List<SessionImportDto> sessionImportDtos = (List<SessionImportDto>) SessionUtils.getInstance().getValue(request, LIST_SESSION_IMPORT);
                request.setAttribute(WebConstants.LIST_ITEMS, sessionImportDtos);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/session/import-session.jsp");
                dispatcher.forward(request, response);
            }
            if (command.getUrlType().equals(URL_IMPORT_ATTENDEES)) {
                //Hien thi giao dien nhap file excel nguoi tham du
                String sessionId = request.getParameter("sessionId");
                request.setAttribute("sessionId", sessionId);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/session/import-attendees.jsp");
                dispatcher.forward(request, response);
            }
            if (command.getUrlType().equals(URL_VALIDATE_ATTENDEES)) {
                //View data attendees get from excel
                String sessionId = request.getParameter("sessionId");
                request.setAttribute("sessionId", sessionId);
                List<AttendeesImportDto> attendeesImportDtos = (List<AttendeesImportDto>) SessionUtils.getInstance().getValue(request, LIST_ATTENDEES_IMPORT);
                request.setAttribute(WebConstants.LIST_ITEMS, attendeesImportDtos);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/session/import-attendees.jsp");
                dispatcher.forward(request, response);
            }
            if(command.getUrlType().equals(URL_REMOVE_MEMBER)){
                String attendeesId = request.getParameter("attendeesId");
                String fullName = request.getParameter("fullName");
                String sessionId = request.getParameter("sessionId");
                if(attendeesId != null){
                    request.setAttribute("attendeesId", attendeesId);
                }
                if(fullName != null){
                    request.setAttribute("fullName", fullName);
                }
                if(sessionId != null){
                    request.setAttribute("sessionId", sessionId);
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/session/remove-attendees.jsp");
                dispatcher.forward(request, response);
            }
            if(command.getUrlType().equals("download_template_attendees")){
                String address = FileUtils.getRealPath(request, CoreConstant.FOLDER_FILE);
                String filePath = address + File.separator + WebConstants.EXCEL + File.separator + "template" + File.separator + "danh-sach-nguoi-tham-du-template.xlsx";
                String name = "danh-sach-nguoi-tham-du-template.xlsx";
                DownloadUtil util = new DownloadUtil();
                util.downloadFile(response, name, filePath);
            }
            if(command.getUrlType().equals("download_template_session")){
                String address = FileUtils.getRealPath(request, CoreConstant.FOLDER_FILE);
                String filePath = address + File.separator + WebConstants.EXCEL + File.separator + "template" + File.separator + "danh-sach-phien-hop-template.xlsx";
                String name = "danh-sach-phien-hop-template.xlsx";
                DownloadUtil util = new DownloadUtil();
                util.downloadFile(response, name, filePath);
            }
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get file from input form
        request.setCharacterEncoding("UTF-8");
        UploadUtils uploadUtils = new UploadUtils();
        Set<String> value = new HashSet<>();
        value.add("urlType");
        value.add("id");
        Object[] objects = uploadUtils.writeOrUpdateFile(request, value, WebConstants.EXCEL);

        //get User Session
        UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);

        try {
            //session command
            SessionCommand sessionCommand = FormUtils.populate(SessionCommand.class, request);
            SessionDto sessionPojo = sessionCommand.getPojo();

            if(sessionCommand.getUrlType() != null){
                if (sessionCommand.getUrlType().equals(WebConstants.URL_EDIT)) {
                    if (sessionCommand.getCrudaction() != null && sessionCommand.getCrudaction().equals(WebConstants.INSERT_UPDATE)) {
                        Integer meetingId = sessionCommand.getMeetingId();
                        MeetingDto meetingDto = new MeetingDto();
                        meetingDto.setMeetingId(meetingId);

                        Integer roomId = sessionCommand.getRoomId();
                        RoomDto roomDto = new RoomDto();
                        roomDto.setRoomId(roomId);
                        //set time start
                        if (sessionCommand.getTimeStart().trim().equals("") || sessionCommand.getTimeStart() == null) {
                            sessionCommand.setTimeStart(null);
                        } else {
                            sessionPojo.setTimeStart(TimeUtils.datetimePicker2Timestamp(sessionCommand.getTimeStart()));
                        }
                        //set time end
                        if (sessionCommand.getTimeEnd().trim().equals("") || sessionCommand.getTimeEnd() == null) {
                            sessionCommand.setTimeEnd(null);
                        } else {
                            sessionPojo.setTimeEnd(TimeUtils.datetimePicker2Timestamp(sessionCommand.getTimeEnd()));
                        }
                        //insert and update session
                        if (sessionPojo != null && sessionPojo.getSessionId() != null) {
                            sessionPojo.setRoomDto(roomDto);
                            sessionPojo.setMeetingDto(meetingDto);
                            sessionPojo.setStatus(0);
                            sessionPojo.setMeetingDto(meetingDto);
                            sessionPojo.setModUID(userDto.getUserId());
                            sessionPojo.setModDate(TimeUtils.getCurrentTimestamp());
                            SessionDto sessionDto = SingletonServiceUtil.getSessionServiceInstance().update(sessionPojo);
                            //request.setAttribute(WebConstants.MESSAGE_RESPONSE, WebConstants.REDIRECT_UPDATE);
                            String direct = "/manager-session-list.html?urlType=url_list&meetingId="+meetingId+"&crudaction=redirect_update";
                            response.sendRedirect(direct);
                        } else {
                            sessionPojo.setRoomDto(roomDto);
                            sessionPojo.setMeetingDto(meetingDto);
                            sessionPojo.setStatus(0);
                            sessionPojo.setCreUID(userDto.getUserId());
                            sessionPojo.setCreDate(TimeUtils.getCurrentTimestamp());
                            sessionPojo.setModUID(userDto.getUserId());
                            sessionPojo.setModDate(TimeUtils.getCurrentTimestamp());
                            SingletonServiceUtil.getSessionServiceInstance().save(sessionPojo);
                            //request.setAttribute(WebConstants.MESSAGE_RESPONSE, WebConstants.REDIRECT_INSERT);
                            String direct = "/manager-session-list.html?urlType=url_list&meetingId="+meetingId+"&crudaction=redirect_insert";
                            response.sendRedirect(direct);
                        }
                    }
                }
                if (sessionCommand.getUrlType().equals(URL_ADD_MEMBER)) {
                    AttendeesDto attendeesDto = new AttendeesDto();
                    String speakerId = request.getParameter("speakerID");
                    String sessionId = request.getParameter("sessionID");
                    String dutyId = request.getParameter("dutyID");
                    //create session
                    SessionDto sessionDto = SingletonServiceUtil.getSessionServiceInstance().findByID(Integer.valueOf(sessionId));
                    //create speaker
                    SpeakerDto speakerDto = new SpeakerDto();
                    speakerDto.setSpeakerId(Integer.valueOf(speakerId));
                    //create duty
                    DutyDto dutyDto = new DutyDto();
                    dutyDto.setDutyId(Integer.valueOf(dutyId));
                    //set data
                    attendeesDto.setSessionDto(sessionDto);
                    attendeesDto.setSpeakerDto(speakerDto);
                    attendeesDto.setDutyDto(dutyDto);
                    attendeesDto.setStatus(0);
                    attendeesDto.setCreUID(userDto.getUserId());
                    attendeesDto.setCreDate(TimeUtils.getCurrentTimestamp());
                    attendeesDto.setModUID(userDto.getUserId());
                    attendeesDto.setModDate(TimeUtils.getCurrentTimestamp());
                    SingletonServiceUtil.getAttendeesServiceInstance().save(attendeesDto);
                    response.setContentType("text/plain");
                    response.getWriter().write("redirect_update");

                }
                if (sessionCommand.getUrlType().equals(WebConstants.URL_DELETE)) {
                    //xu ly xoa phien hop
                    String sessionId = request.getParameter("sessionId");
                    if(sessionId != null){
                        sessionPojo = SingletonServiceUtil.getSessionServiceInstance().findByID(Integer.valueOf(sessionId));
                        sessionPojo.setStatus(1);
                        sessionPojo.setModUID(userDto.getUserId());
                        sessionPojo.setModDate(TimeUtils.getCurrentTimestamp());
                        SessionDto sessionDto = SingletonServiceUtil.getSessionServiceInstance().update(sessionPojo);
                        request.setAttribute(WebConstants.MESSAGE_RESPONSE, WebConstants.REDIRECT_DELETE);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("views/session/remove-session.jsp");
                        dispatcher.forward(request, response);
                    }
                }
                if (sessionCommand.getUrlType().equals(IMPORT_SESSION_DATA)) {
                    //save data session from excel
                    String id = request.getParameter("id");
                    Integer meetingId = Integer.valueOf(id);
                    List<SessionImportDto> sessionImportDtos = (List<SessionImportDto>) SessionUtils.getInstance().getValue(request, LIST_SESSION_IMPORT);
                    //MeetingDto meetingDto = SingletonServiceUtil.getMeetingServiceInstance().findById(1);
                    //SingletonServiceUtil.getSessionServiceInstance().saveSessionDataImport(sessionImportDtos, meetingDto, userDto.getUserId(), timeUtils.getCurrentTimestamp());
                    String direct = "/manager-session-list.html?urlType=url_list&meetingId" + meetingId + "&crudaction=redirect_insert";
                    response.sendRedirect(direct);
//                response.setContentType("text/plain");
//                response.getWriter().write("redirect_update");
                }
                if (sessionCommand.getUrlType().equals(IMPORT_ATTENDEES_DATA)) {
                    //save data attendees from excel
                    String id = request.getParameter("id");
                    Integer sessionId = Integer.valueOf(id);
                    List<SpeakerDto> speakerDtos = SingletonServiceUtil.getSpeakerServiceInstance().findAll();
                    List<DutyDto> dutyDtos = SingletonServiceUtil.getDutyServiceInstance().findAll();
                    SessionDto sessionDto = SingletonServiceUtil.getSessionServiceInstance().findByID(sessionId);
                    List<AttendeesImportDto> attendeesImportDtos = (List<AttendeesImportDto>) SessionUtils.getInstance().getValue(request, LIST_ATTENDEES_IMPORT);
                    SingletonServiceUtil.getSessionServiceInstance().saveAttendeesDataImport(attendeesImportDtos, sessionDto, speakerDtos, dutyDtos, userDto.getUserId(), TimeUtils.getCurrentTimestamp());
                    String direct = "/manager-session-detail.html?urlType=url_detail&sessionId=" + sessionId + "&crudaction=redirect_insert";
                    response.sendRedirect(direct);
//                response.setContentType("text/plain");
//                response.getWriter().write("redirect_update");
                }
                if(sessionCommand.getUrlType().equals(URL_REMOVE_MEMBER)){
                    String attendeesId = request.getParameter("attendeesId");
                    List<Integer> ids = new ArrayList<>();
                    ids.add(Integer.parseInt(attendeesId));
                    SingletonServiceUtil.getAttendeesServiceInstance().deleteMember(ids);
                    response.setContentType("text/plain");
                    response.getWriter().write("redirect_delete");
                }
                if(sessionCommand.getUrlType().equals(URL_ATTENDANCE_MEMBER)){
                    String attendeesId = request.getParameter("attendeesId");
                    if(attendeesId != null){
                        AttendeesDto attendeesDto = SingletonServiceUtil.getAttendeesServiceInstance().findById(Integer.valueOf(attendeesId));
                        if(attendeesDto.getStatus().equals(0)){
                            attendeesDto.setStatus(2);
                        }else {
                            attendeesDto.setStatus(0);
                        }
                        SingletonServiceUtil.getAttendeesServiceInstance().update(attendeesDto);
                        response.setContentType("text/plain");
                        response.getWriter().write("Success");
                    }else {
                        response.setContentType("text/plain");
                        response.getWriter().write("Error");
                    }

                }
            }


            //read excel
            String address = FileUtils.getRealPath(request, CoreConstant.FOLDER_FILE);
            if (objects != null) {
                String urlType = null;
                String id = null;
                Map<String, String> mapValue = (Map<String, String>) objects[3];
                for (Map.Entry<String, String> item : mapValue.entrySet()) {
                    if (item.getKey().equals("urlType")) {
                        urlType = item.getValue();
                    } else if (item.getKey().equals("id")) {
                        id = item.getValue();
                    }
                }
                if (urlType != null && urlType.equals(READ_EXCEL_SESSION)) {
                    //read excel session
                    String fileLocation = address + File.separator + objects[1].toString();
                    String fileName = objects[2].toString();
                    List<SessionImportDto> excelValues = returnValueFromExcel(fileName, fileLocation);
//                    validateData(excelValues);
                    SessionUtils.getInstance().putValue(request, LIST_SESSION_IMPORT, excelValues);
                    String direct = "/manager-session-import.html?urlType=url_validate_session&meetingId=" + id;
                    response.sendRedirect(direct);
                } else if (urlType != null && urlType.equals(READ_EXCEL_ATTENDEES)) {
                    //read excel attendees list
                    String fileLocation = address + File.separator + objects[1].toString();
                    String fileName = objects[2].toString();
                    List<AttendeesImportDto> excelValues = returnValueAttendeesFromExcel(fileName, fileLocation);
                    //validate
                    List<SpeakerDto> speakerDtoList = SingletonServiceUtil.getSpeakerServiceInstance().findAll();
                    excelValues = ValidateAttendeesImport.validateData(excelValues, speakerDtoList);
                    String direct = "/manager-session-detail.html?urlType=url_validate_attendees&sessionId=" + id;
                    SessionUtils.getInstance().putValue(request, LIST_ATTENDEES_IMPORT, excelValues);
                    response.sendRedirect(direct);
                }
            }
        } catch (Exception e) {
            request.setAttribute(WebConstants.MESSAGE_RESPONSE, WebConstants.REDIRECT_ERROR);
        }
    }

    private Map<String, String> buildRedirectionMessageMap() {
        Map<String, String> mapMessage = new HashMap<String, String>();
        mapMessage.put(WebConstants.REDIRECT_INSERT, "Thêm thành công");
        mapMessage.put(WebConstants.REDIRECT_UPDATE, "Chỉnh sửa phiên thành công");
        mapMessage.put(WebConstants.REDIRECT_DELETE, "Xóa thành công");
        mapMessage.put(WebConstants.REDIRECT_ERROR, "Có lỗi xảy ra");
        return mapMessage;
    }

    private void validateData(List<SessionImportDto> excelValues) {
        Set<String> stringSet = new HashSet<>();
        for (SessionImportDto item : excelValues) {
            validateRequireField(item);
            validateDuplicate(item, stringSet);
        }
        SingletonServiceUtil.getSessionServiceInstance().validateImportSession(excelValues);

    }

    private void validateDuplicate(SessionImportDto item, Set<String> stringSet) {
        String message = item.getError();
        if (!stringSet.contains(item.getName())) {
            stringSet.add(item.getName());
        } else {
            if (item.isValid()) {
                message += "<br/>";
                message += "Trung ten phien hop";
            }
        }
        if (StringUtils.isNotBlank(message)) {
            item.setValid(false);
            item.setError(message);
        }
    }

    private void validateRequireField(SessionImportDto item) {
        String message = "";
        if (StringUtils.isBlank(item.getName())) {
            message += "<br/>";
            message += "Ten khong duoc bo trong";
        }
        item.setError(message);
    }

    private List<SessionImportDto> returnValueFromExcel(String fileName, String fileLocation) throws IOException {
        Workbook workbook = ExcelPoiUtil.getWorkBook(fileName, fileLocation);
        Sheet sheet = workbook.getSheetAt(0);
        List<SessionImportDto> excelValues = new ArrayList<>();
        for (int i = 2; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            SessionImportDto sessionImportDto = readDataFromExcel(row);
            excelValues.add(sessionImportDto);
        }
        return excelValues;
    }

    private SessionImportDto readDataFromExcel(Row row) {
        SessionImportDto sessionImportDto = new SessionImportDto();
        sessionImportDto.setName(ExcelPoiUtil.getCellValue(row.getCell(1)));
        sessionImportDto.setAddress(ExcelPoiUtil.getCellValue(row.getCell(2)));
        sessionImportDto.setTimeStart(ExcelPoiUtil.getCellValue(row.getCell(3)));
        sessionImportDto.setTimeEnd(ExcelPoiUtil.getCellValue(row.getCell(4)));
        sessionImportDto.setDescription(ExcelPoiUtil.getCellValue(row.getCell(5)));
        return sessionImportDto;
    }

    /**
     * Read data and return data in excel file
     *
     * @param fileName:     name file excel
     * @param fileLocation: patch file
     * @return list attendees
     * @throws IOException
     */
    private List<AttendeesImportDto> returnValueAttendeesFromExcel(String fileName, String fileLocation) throws IOException {
        Workbook workbook = ExcelPoiUtil.getWorkBook(fileName, fileLocation);
        Sheet sheet = workbook.getSheetAt(0);
        List<AttendeesImportDto> excelValues = new ArrayList<>();
        for (int i = 2; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            AttendeesImportDto attendeesImportDto = readDataAttendeesFromExcel(row);
            excelValues.add(attendeesImportDto);
        }
        return excelValues;
    }

    /**
     * Read data in row
     *
     * @param row in file excel
     * @return Data in row
     */
    private AttendeesImportDto readDataAttendeesFromExcel(Row row) {
        AttendeesImportDto attendeesImportDto = new AttendeesImportDto();
        attendeesImportDto.setFullName(ExcelPoiUtil.getCellValue(row.getCell(1)));
        attendeesImportDto.setPhone(ExcelPoiUtil.getCellValue(row.getCell(2)));
        attendeesImportDto.setEmail(ExcelPoiUtil.getCellValue(row.getCell(3)));
        attendeesImportDto.setDuty(ExcelPoiUtil.getCellValue(row.getCell(4)));
        return attendeesImportDto;
    }

}
