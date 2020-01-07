package ubnd.core.service;


import ubnd.core.dto.MeetingDto;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface MeetingService {
    List<MeetingDto> findAll();
    List<MeetingDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    MeetingDto findById(Integer meetingId);
    MeetingDto update(MeetingDto dto);
    MeetingDto save(MeetingDto dto);
    int delete(Integer meetingId);
    List<MeetingDto> searchMeeting (String name, Timestamp timeStart, Timestamp timeEnd, Integer status);
}
