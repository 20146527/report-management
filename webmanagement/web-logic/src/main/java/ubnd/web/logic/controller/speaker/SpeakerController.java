package ubnd.web.logic.controller.speaker;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import ubnd.common.utils.ExcelPoiUtil;
import ubnd.common.utils.SessionUtils;
import ubnd.common.utils.UploadUtils;
import ubnd.core.dto.AudioSampleDto;
import ubnd.core.dto.SpeakerDto;
import ubnd.core.dto.SpeakerImportDto;
import ubnd.core.dto.UserDto;
import ubnd.core.web.common.WebConstants;
import ubnd.core.web.utils.FormUtils;
import ubnd.core.web.utils.SingletonServiceUtil;
import ubnd.core.web.utils.WebCommonUtil;
import ubnd.web.logic.command.AudioSampleCommand;
import ubnd.web.logic.command.SpeakerCommand;
import ubnd.web.logic.utils.TimeUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


@WebServlet(urlPatterns = {"/manager-speaker-list.html", "/ajax-speaker-edit.html", "/ajax-audio-sample.html"})
public class SpeakerController extends HttpServlet {
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        SpeakerCommand command = FormUtils.populate(SpeakerCommand.class, request);
        SpeakerDto pojo = command.getPojo();
        UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);

        if (command.getUrlType() != null && command.getUrlType().equals(WebConstants.URL_LIST)) {
            Map<String, Object> mapProperty = new HashMap<>();
            mapProperty.put("status", 0);
            List<SpeakerDto> speakerDtoList = SingletonServiceUtil.getSpeakerServiceInstance().findByProperty(mapProperty, null, null, null, null);
            request.setAttribute(WebConstants.LIST_ITEMS, speakerDtoList);
            if(command.getCrudaction() != null){
                Map<String, String> mapMessage = buidMapRedirectMessage();
                WebCommonUtil.addRedirectMessage(request, command.getCrudaction(), mapMessage);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/speaker/list-speaker.jsp");
            dispatcher.forward(request, response);
        } else if(command.getUrlType() != null && command.getUrlType().equals(WebConstants.URL_SEARCH)){
            Map<String, Object> mapProperty = new HashMap<>();
            mapProperty.put("fullName", pojo.getFullName());
            mapProperty.put("email", pojo.getEmail());
            mapProperty.put("phone", pojo.getPhone());
            mapProperty.put("status", 0);
            List<SpeakerDto> speakerDtoList = SingletonServiceUtil.getSpeakerServiceInstance().findByProperty(mapProperty, null, null, null, null);
            request.setAttribute(WebConstants.LIST_ITEMS, speakerDtoList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/speaker/list-speaker.jsp");
            dispatcher.forward(request, response);
            //System.out.println("Nghia day ne"+pojo.getFullName());

        } else if (command.getUrlType() != null && command.getUrlType().equals(WebConstants.URL_EDIT)) {
            if (pojo != null && pojo.getSpeakerId() != null) {
                command.setPojo(SingletonServiceUtil.getSpeakerServiceInstance().findById(pojo.getSpeakerId()));
            }
            request.setAttribute(WebConstants.FORM_ITEM, command);
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/speaker/edit-speaker.jsp");
            dispatcher.forward(request, response);

        } else if (command.getUrlType()!= null && command.getUrlType().equals(WebConstants.URL_DELETE)){
            String kfdkjf = command.getPojo().getFullName();
            System.out.println("HE NHO" + kfdkjf);
            request.setAttribute(WebConstants.FORM_ITEM, command);
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/speaker/remove-speaker.jsp");
            dispatcher.forward(request, response);

        } else if(command.getUrlType() != null && command.getUrlType().equals("url_as_list")){
            int speakerId = pojo.getSpeakerId();
            Map<String, Object> mapProperty = new HashMap<>();
            mapProperty.put("speakerEntity", speakerId);
            List<AudioSampleDto> audioSampleDtoList = SingletonServiceUtil.getAudioSampleServiceInstance().findByProperty(mapProperty, null, null, null, null);
            int totalItem = audioSampleDtoList.size();
            String nameFile = userDto.getUserName()+"_"+ TimeUtils.getCurrentTime()+"_"+totalItem;
            request.setAttribute("nameFile", nameFile);
            request.setAttribute("speakerId", speakerId);
            request.setAttribute(WebConstants.LIST_ITEMS, audioSampleDtoList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/speaker/audio-sample.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        SpeakerCommand command = FormUtils.populate(SpeakerCommand.class, request);
        SpeakerDto pojo = command.getPojo();
        //get User Session
        UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);
        if(command.getUrlType() != null){
            //insert or update speaker
            if (command.getUrlType().equals(WebConstants.URL_EDIT)) {
                if (command.getCrudaction() != null && command.getCrudaction().equals(WebConstants.INSERT_UPDATE)){
                    if(pojo != null && pojo.getSpeakerId() != null){
                        //update
                        pojo.setStatus(0);
                        pojo.setModUID(userDto.getUserId());
                        pojo.setModDate(TimeUtils.getCurrentTimestamp());
                        SpeakerDto speakerDto = SingletonServiceUtil.getSpeakerServiceInstance().updateSpeaker(pojo);
                        response.sendRedirect("/manager-speaker-list.html?urlType=url_list&crudaction=redirect_update");
                    }else {
                        //insert
                        assert pojo != null;
                        pojo.setCreUID(userDto.getUserId());
                        pojo.setCreDate(TimeUtils.getCurrentTimestamp());
                        pojo.setModUID(userDto.getUserId());
                        pojo.setModDate(TimeUtils.getCurrentTimestamp());
                        SingletonServiceUtil.getSpeakerServiceInstance().saveSpeaker(pojo);
                        response.sendRedirect("/manager-speaker-list.html?urlType=url_list&crudaction=redirect_insert");
                    }
                }
            }
            // upload file audio sample
            if(command.getUrlType().equals(WebConstants.URL_UPLOAD)){
                AudioSampleCommand audioSampleCommand = new AudioSampleCommand();
                UploadUtils uploadUtils = new UploadUtils();
                Set<String> valueTitle = buildSetValueAudioSample();
                Object[] objects = uploadUtils.writeOrUpdateFile(request, valueTitle, WebConstants.AUDIO_SAMPLE);
                boolean checkStatusUpload= (Boolean) objects[0];
                if(!checkStatusUpload){
                    System.out.println("Loi roi neeeee!");
                    response.sendRedirect("/manager-speaker-list.html?urlType=url_list&crudaction=redirect_error");
                }else {
                    AudioSampleDto dto = audioSampleCommand.getPojo();
                    if(org.apache.commons.lang.StringUtils.isNotBlank(objects[1].toString())){
                        dto.setPathAudio(objects[1].toString());
                    }
                    Map<String, String> mapValue = (Map<String, String>) objects[3];
                    Long fileSize = (Long) objects[4];
                    returnValueOfDTO(dto, mapValue);
                    if(dto!=null){
                        float s1 = (Float.valueOf(fileSize) / 1024) / 1024;
                        Float s3 = (float) Math.round(s1 * 100) / 100;
                        System.out.println("sdsdfs"+s3);
                        dto.setSize(s3);
                        dto.setSys(0);
                        dto.setStatus(0);
                        dto.setCreUID(userDto.getUserId());
                        dto.setCreDate(TimeUtils.getCurrentTimestamp());
                        dto.setModUID(userDto.getUserId());
                        dto.setModDate(TimeUtils.getCurrentTimestamp());
                        //save audio sample
                        SingletonServiceUtil.getAudioSampleServiceInstance().saveAudioSample(dto);
                    }
                    response.sendRedirect("/manager-speaker-list.html?urlType=url_list&crudaction=redirect_insert");
                }
            }
            // upload record audio sample
            if(command.getUrlType().equals("url_as_record")){
                AudioSampleCommand audioSampleCommand = new AudioSampleCommand();
                UploadUtils uploadUtils = new UploadUtils();
                Set<String> valueTitle = new HashSet<>();
                Object[] objects = uploadUtils.writeOrUpdateFile(request, valueTitle, WebConstants.AUDIO_SAMPLE);
                boolean checStatusUpload = (Boolean) objects[0];
                if(!checStatusUpload){
                    System.out.println("Loi roi neeeee!");
                    response.sendRedirect("/manager-speaker-list.html?urlType=url_list&crudaction=redirect_error");
                } else {
                    String pathUpload = objects[1].toString();
                    System.out.println("Upload ngon"+pathUpload);
                    response.sendRedirect("/manager-speaker-list.html?urlType=url_list&crudaction=redirect_insert");
                }
                response.sendRedirect("/manager-speaker-list.html?urlType=url_list&crudaction=redirect_insert");
            }
            // delete speaker
            if(command.getUrlType().equals(WebConstants.URL_DELETE)){
                int speakerId = pojo.getSpeakerId();
                pojo = SingletonServiceUtil.getSpeakerServiceInstance().findById(speakerId);
                pojo.setStatus(1);
                pojo.setModUID(userDto.getUserId());
                pojo.setModDate(TimeUtils.getCurrentTimestamp());
                SpeakerDto speakerDto = SingletonServiceUtil.getSpeakerServiceInstance().updateSpeaker(pojo);
                request.setAttribute(WebConstants.MESSAGE_RESPONSE, WebConstants.REDIRECT_DELETE);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/speaker/remove-speaker.jsp");
                dispatcher.forward(request, response);
            }
        }

    }

    private Map<String,String> buidMapRedirectMessage() {
        Map<String, String> mapMessage = new HashMap<>();
        mapMessage.put(WebConstants.REDIRECT_INSERT, "Thêm thành công");
        mapMessage.put(WebConstants.REDIRECT_UPDATE, "Chỉnh sửa thành công");
        mapMessage.put(WebConstants.REDIRECT_DELETE, "Xóa thành công");
        mapMessage.put(WebConstants.REDIRECT_ERROR, "Có lỗi xảy ra");
        return mapMessage;
    }

    private Set<String> buildSetValueAudioSample() {
        Set<String> returnValue = new HashSet<>();
        returnValue.add("pojo.timeIndex");
        returnValue.add("speakerId");
        returnValue.add("nameFile");
        return returnValue;
    }

    private AudioSampleDto returnValueOfDTO(AudioSampleDto dto, Map<String, String> mapValue) {
        for (Map.Entry<String, String> item: mapValue.entrySet()) {
            switch (item.getKey()) {
                case "pojo.timeIndex":
                    dto.setTimeIndex(item.getValue());
                    break;
                case "speakerId":
                    SpeakerDto speakerDto = new SpeakerDto();
                    speakerDto.setSpeakerId(Integer.parseInt(item.getValue()));
                    dto.setSpeakerDto(speakerDto);
                    break;
                case "nameFile":
                    dto.setName(item.getValue());
                    break;
            }
        }
        return dto;
    }

    private List<SpeakerImportDto> returnValueFromExcel(String fileName, String fileLocation) throws IOException {
        Workbook workbook = ExcelPoiUtil.getWorkBook(fileName, fileLocation);
        Sheet sheet = workbook.getSheetAt(0);
        List<SpeakerImportDto> excelValues = new ArrayList<>();
        for(int i = 1; i <= sheet.getLastRowNum(); i++){
            Row row = sheet.getRow(i);
            SpeakerImportDto speakerImportDto = readDataFromExcel(row);
            excelValues.add(speakerImportDto);
        }
        return excelValues;
    }

    private SpeakerImportDto readDataFromExcel(Row row){
        SpeakerImportDto speakerImportDto = new SpeakerImportDto();
        speakerImportDto.setFullName(ExcelPoiUtil.getCellValue(row.getCell(0)));
        speakerImportDto.setOtherName(ExcelPoiUtil.getCellValue(row.getCell(1)));
        speakerImportDto.setGender(ExcelPoiUtil.getCellValue(row.getCell(2)));
        speakerImportDto.setEmail(ExcelPoiUtil.getCellValue(row.getCell(3)));
        speakerImportDto.setPhone(ExcelPoiUtil.getCellValue(row.getCell(4)));
        speakerImportDto.setBirthday(ExcelPoiUtil.getCellValue(row.getCell(5)));
        speakerImportDto.setRegency(ExcelPoiUtil.getCellValue(row.getCell(6)));
        return speakerImportDto;
    }
}