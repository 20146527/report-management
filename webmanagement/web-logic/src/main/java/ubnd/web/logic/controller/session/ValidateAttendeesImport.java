package ubnd.web.logic.controller.session;

import org.apache.commons.lang.StringUtils;
import ubnd.core.dto.AttendeesImportDto;
import ubnd.core.dto.SpeakerDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidateAttendeesImport {

    public static List<AttendeesImportDto> validateData(List<AttendeesImportDto> excelValues, List<SpeakerDto> speakerDtoList) {
        Set<String> stringSet = new HashSet<String>();
        for (AttendeesImportDto item : excelValues) {
            validateRequireField(item);
            validateDuplicate(item, stringSet);
            validateExistAttendees(item, speakerDtoList);
            if(item.getError() == null || item.getError().equals("")){
                item.setError("<span class=\"badge badge-success\">Hợp lệ</span>");
            }
        }
        return excelValues;

    }

    private static void validateRequireField(AttendeesImportDto item) {
        String message = "";
        if (StringUtils.isBlank(item.getFullName())) {
            message += "Họ và tên không được bỏ trống!";
            message += "<br/>";
        }
        item.setError(message);
    }

    private static void validateDuplicate(AttendeesImportDto item, Set<String> stringSet) {
        String message = item.getError();
        if (!stringSet.contains(item.getFullName())) {
            stringSet.add(item.getFullName());
        } else {
            if (item.isValid()) {
                message += "Trùng tên người tham gia";
                message += "<br/>";
            }
        }
        if (StringUtils.isNotBlank(message)) {
            item.setValid(false);
            item.setError(message);
        }
    }

    private static void validateExistAttendees(AttendeesImportDto item, List<SpeakerDto> speakerList){
        String fullName = item.getFullName();
        String message = item.getError();
        if(!checkExistAttendees(fullName, speakerList)){
            message += "Người họp không tồn tại";
            message += "<br/>";
        }
        if(StringUtils.isNotBlank(message)){
            item.setError(message);
            item.setValid(false);
        }

    }

    private static Boolean checkExistAttendees(String fullName, List<SpeakerDto> speakerList){
        Boolean check = false;
        for(SpeakerDto item: speakerList){
            if(item.getFullName().equals(fullName)){
                return true;
            }
        }
        return check;
    }




}
