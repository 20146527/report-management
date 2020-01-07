package ubnd.web.logic.controller.meeting;

import org.json.JSONArray;
import org.json.JSONObject;
import ubnd.common.utils.SessionUtils;
import ubnd.core.dto.MeetingDto;
import ubnd.core.dto.UserDto;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.web.common.WebConstants;
import ubnd.core.web.utils.FormUtils;
import ubnd.core.web.utils.SingletonServiceUtil;
import ubnd.core.web.utils.WebCommonUtil;
import ubnd.web.logic.command.MeetingCommand;
import ubnd.web.logic.utils.TimeUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

@WebServlet(urlPatterns = {"/manager-meeting-list.html", "/ajax-meeting-edit.html"})
public class MeetingController extends HttpServlet {
    ResourceBundle bundle = ResourceBundle.getBundle("ResourcesBundle");
    private final String VIEW_TYPE_TABLE = "table";
    private final String VIEW_TYPE_CALENDAR = "calendar";

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        MeetingCommand command = FormUtils.populate(MeetingCommand.class, request);
        MeetingDto pojo = command.getPojo();

        if (command.getUrlType() != null && command.getUrlType().equals(WebConstants.URL_LIST)) {
            String meetingName = "";
            Timestamp timeStart = null, timeEnd = null;
            if(command.getTimeStart() != null || command.getTimeEnd() != null){
                if (!command.getTimeStart().equals("")) {
                    timeStart = TimeUtils.datetimePicker2Timestamp(command.getTimeStart());
                }
                if (!command.getTimeEnd().equals("")) {
                    timeEnd = TimeUtils.datetimePicker2Timestamp(command.getTimeEnd());
                }
            }
            if(pojo.getName() != null){
                meetingName = pojo.getName();
            }

            List<MeetingDto> meetingDtoList = SingletonServiceUtil.getMeetingServiceInstance().searchMeeting(meetingName, timeStart, timeEnd, 0);

            // set action notify message
            if (command.getCrudaction() != null) {
                Map<String, String> mapMessage = buildRedirectionMessageMap();
                WebCommonUtil.addRedirectMessage(request, command.getCrudaction(), mapMessage);
            }
            //set view table or calendar
            if (command.getViewType() == null) {
                request.setAttribute(WebConstants.LIST_ITEMS, meetingDtoList);
                request.setAttribute(WebConstants.FORM_ITEM, command);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/meeting/list-meeting-table.jsp");
                dispatcher.forward(request, response);
            } else {
                if (command.getViewType().equals(VIEW_TYPE_TABLE)) {
                    request.setAttribute(WebConstants.FORM_ITEM, command);
                    request.setAttribute(WebConstants.LIST_ITEMS, meetingDtoList);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("views/meeting/list-meeting-table.jsp");
                    dispatcher.forward(request, response);
                }
                if (command.getViewType().equals(VIEW_TYPE_CALENDAR)) {
                    JSONArray jsonArray = getJsonDate(meetingDtoList);
                    String[] timeCurrent = TimeUtils.getCurrentTimestamp().toString().split(" ");
                    request.setAttribute("data-calendar", jsonArray);
                    request.setAttribute("defaultDate", timeCurrent[0]);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("views/meeting/list-meeting-calendar.jsp");
                    dispatcher.forward(request, response);
                }
            }


        } else if (command.getUrlType() != null && command.getUrlType().equals(WebConstants.URL_CREATE)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/meeting/create-meeting.jsp");
            dispatcher.forward(request, response);
        } else if (command.getUrlType() != null && command.getUrlType().equals(WebConstants.URL_EDIT)) {
            if (pojo.getMeetingId() != null) {
                pojo = SingletonServiceUtil.getMeetingServiceInstance().findById(pojo.getMeetingId());
                String timeStart = TimeUtils.timestamp2DatetimePicker(pojo.getTimeStart().toString());
                String timeEnd = TimeUtils.timestamp2DatetimePicker(pojo.getTimeEnd().toString());
                command.setTimeStart(timeStart);
                command.setTimeEnd(timeEnd);
                command.setPojo(pojo);
            } else {
                if (command.getInputType() != null && command.getInputType().equals("dayGridMonth")) {
                    //du lieu nhap vao tu tab thang
                    if (command.getTimeStart() != null) {
                        command.setTimeStart(TimeUtils.timestamp2DatetimePicker(command.getTimeStart() + " 08:00:00"));
                    }
                    if (command.getTimeEnd() != null) {
                        String timeEndCalc;
                        String[] timeEnd = command.getTimeEnd().split("-");
                        Integer dayEnd = Integer.parseInt(timeEnd[2]) - 1;
                        if (dayEnd.equals(0)) {
                            //neu dayEnd la ngay 01
                            timeEndCalc = command.getTimeEnd() + " 18:00:00";
                        } else {
                            String dayEndStr = String.valueOf(dayEnd);
                            if (dayEndStr.length() == 1) {
                                dayEndStr = "0" + dayEndStr;
                            }
                            timeEndCalc = timeEnd[0] + "-" + timeEnd[1] + "-" + dayEndStr + " 18:00:00";
                        }
                        command.setTimeEnd(TimeUtils.timestamp2DatetimePicker(timeEndCalc));
                    }
                } else {
                    //du lieu nhap vao tu tab ngay or tuan
                    if (command.getTimeStart() != null) {
                        String[] timeStart = command.getTimeStart().split(" ");
                        command.setTimeStart(TimeUtils.timestamp2DatetimePicker(timeStart[0].replace("T", " ")));
                    }
                    if (command.getTimeEnd() != null) {
                        String[] timeEnd = command.getTimeEnd().split(" ");
                        command.setTimeEnd(TimeUtils.timestamp2DatetimePicker(timeEnd[0].replace("T", " ")));
                    }
                }

            }
            request.setAttribute(WebConstants.FORM_ITEM, command);
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/meeting/edit-meeting.jsp");
            dispatcher.forward(request, response);
        } else if (command.getUrlType() != null && command.getUrlType().equals(WebConstants.URL_DELETE)) {
            request.setAttribute(WebConstants.FORM_ITEM, command);
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/meeting/remove-meeting.jsp");
            dispatcher.forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //Meeting command
        MeetingCommand command = FormUtils.populate(MeetingCommand.class, request);
        MeetingDto pojo = command.getPojo();

        //get User Session
        UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);

        if (command.getUrlType() != null && command.getUrlType().equals(WebConstants.URL_EDIT)) {
            System.out.println("Vo day roi ne!!!!");
            if (command.getCrudaction() != null && command.getCrudaction().equals(WebConstants.INSERT_UPDATE)) {
                //set time start
                if (command.getTimeStart().trim().equals("") || command.getTimeStart() == null) {
                    pojo.setTimeStart(null);
                } else {
                    pojo.setTimeStart(TimeUtils.datetimePicker2Timestamp(command.getTimeStart()));
                }

                //set time end
                if (command.getTimeEnd().trim().equals("") || command.getTimeEnd() == null) {
                    pojo.setTimeEnd(null);
                } else {
                    pojo.setTimeEnd(TimeUtils.datetimePicker2Timestamp(command.getTimeEnd()));
                }

                Map<String, Object> mapProperty = new HashMap<>();
                mapProperty.put("status", 0);

                if (pojo != null && pojo.getMeetingId() != null) {
                    //cho nay de update thong tin cuoc hop
                    pojo.setStatus(0);
                    pojo.setModUID(userDto.getUserId());
                    pojo.setModDate(TimeUtils.getCurrentTimestamp());
                    MeetingDto dto;

                    if(validateInsertUpdateMeeting(pojo.getName(), pojo.getTimeStart(), pojo.getTimeEnd())){
                        dto = SingletonServiceUtil.getMeetingServiceInstance().update(pojo);
                        if (command.getViewType().equals(VIEW_TYPE_CALENDAR)) {
                            //response.sendRedirect("/manager-meeting-list.html?urlType=url_list&viewType=calendar&crudaction=redirect_update");
                            response.setContentType("text/html;charset=UTF-8");
                            JSONObject json = new JSONObject();
                            json.put("id", dto.getMeetingId());
                            json.put("title", dto.getName());
                            json.put("start", dto.getTimeStart().toString().replace(" ", "T"));
                            json.put("end", dto.getTimeEnd().toString().replace(" ", "T"));
                            response.getWriter().write(json.toString());
                        } else {
                            //response.sendRedirect("/manager-meeting-list.html?urlType=url_list&viewType=table&crudaction=redirect_update");
                            request.setAttribute(WebConstants.MESSAGE_RESPONSE, WebConstants.REDIRECT_UPDATE);
                            RequestDispatcher dispatcher = request.getRequestDispatcher("views/meeting/edit-meeting.jsp");
                            dispatcher.forward(request, response);
                        }
                    }



                } else {
                    //cho nay de them moi thong tin cuoc hop
                    pojo.setStatus(0);
                    pojo.setCreUID(userDto.getUserId());
                    pojo.setCreDate(TimeUtils.getCurrentTimestamp());
                    pojo.setModUID(userDto.getUserId());
                    pojo.setModDate(TimeUtils.getCurrentTimestamp());
                    MeetingDto dto;

                    if(validateInsertUpdateMeeting(pojo.getName(), pojo.getTimeStart(), pojo.getTimeEnd())){
                        dto = SingletonServiceUtil.getMeetingServiceInstance().save(pojo);
                        if (command.getViewType().equals(VIEW_TYPE_CALENDAR)) {
                            //response.sendRedirect("/manager-meeting-list.html?urlType=url_list&viewType=calendar&crudaction=redirect_insert");
                            response.setContentType("text/html;charset=UTF-8");
                            JSONObject json = new JSONObject();
                            json.put("id", dto.getMeetingId());
                            json.put("title", dto.getName());
                            json.put("start", dto.getTimeStart().toString().replace(" ", "T"));
                            json.put("end", dto.getTimeEnd().toString().replace(" ", "T"));
                            response.getWriter().write(json.toString());
                        } else {
                            //response.sendRedirect("/manager-meeting-list.html?urlType=url_list&viewType=table&crudaction=redirect_insert");
                            request.setAttribute(WebConstants.MESSAGE_RESPONSE, WebConstants.REDIRECT_INSERT);
                            RequestDispatcher dispatcher = request.getRequestDispatcher("views/meeting/edit-meeting.jsp");
                            dispatcher.forward(request, response);
                        }
                    }

                }
            }
        } else if (command.getUrlType() != null && command.getUrlType().equals(WebConstants.URL_DELETE)) {
//            if (pojo.getMeetingId() != null) {
//                int meetingId = pojo.getMeetingId();
//                int abc = SingletonServiceUtil.getMeetingServiceInstance().delete(meetingId);
//                if (command.getViewType().equals(VIEW_TYPE_CALENDAR)) {
//                    response.sendRedirect("/manager-meeting-list.html?urlType=url_list&viewType=calendar&crudaction=redirect_delete");
//                } else {
//                    response.sendRedirect("/manager-meeting-list.html?urlType=url_list&viewType=table&crudaction=redirect_delete");
//                }
//            }

            String meetingId = request.getParameter("meetingId");
            if(meetingId != null){
                SingletonServiceUtil.getMeetingServiceInstance().delete(Integer.valueOf(meetingId));
                if (command.getViewType().equals(VIEW_TYPE_CALENDAR)) {
                    response.sendRedirect("/manager-meeting-list.html?urlType=url_list&viewType=calendar&crudaction=redirect_delete");
                } else {
                    response.sendRedirect("/manager-meeting-list.html?urlType=url_list&viewType=table&crudaction=redirect_delete");
                }
            }
        }
    }

    private Map<String, String> buildRedirectionMessageMap() {
        Map<String, String> mapMessage = new HashMap<>();
        mapMessage.put(WebConstants.REDIRECT_INSERT, "Thêm cuộc họp thành công");
        mapMessage.put(WebConstants.REDIRECT_UPDATE, "Chỉnh sửa cuộc họp thành công");
        mapMessage.put(WebConstants.REDIRECT_DELETE, "Xóa cuộc họp thành công");
        mapMessage.put(WebConstants.REDIRECT_ERROR, "Có lỗi xảy ra");
        return mapMessage;
    }

    private JSONArray getJsonDate(List<MeetingDto> meetingDtos) {
        JSONArray jsonArray = new JSONArray();
        for (MeetingDto item : meetingDtos) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", item.getMeetingId());
            jsonObject.put("title", item.getName());
            jsonObject.put("start", item.getTimeStart().toString().replace(" ", "T"));
            jsonObject.put("end", item.getTimeEnd().toString().replace(" ", "T"));
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }

    private Boolean validateInsertUpdateMeeting(String name, Timestamp timeStart, Timestamp timeEnd){
        Boolean check = true;
        if(name.equals("") || timeStart == null || timeEnd == null){
            check = false;
        }
        if(timeStart != null && timeEnd != null){
            if(timeStart.after(timeEnd)){
                check = false;
            }else {
                check = true;
            }
        }
        return check;
    }

}
