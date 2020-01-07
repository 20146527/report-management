package ubnd.core.service;


import ubnd.core.dto.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface SessionService {
    List<SessionDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    SessionDto findByID(Integer sessionId);
    SessionDto update(SessionDto dto);
    void save(SessionDto dto);
    void saveAttendeesDataImport(List<AttendeesImportDto> attendeesImportDtos, SessionDto sessionDto, List<SpeakerDto> speakerDtos, List<DutyDto> dutyDtos, Integer userId, Timestamp timestamp);
    void validateImportSession(List<SessionImportDto> sessionImportDtos);
    List<SessionDto> searchSession (Integer meetingId, Integer roomId, String name, String description, Timestamp timeStart, Timestamp timeEnd, Integer status);
    SessionDto findSessionUpload(Integer meetingId, Integer sessionId);
}
