package ubnd.core.service;

import ubnd.core.dto.StenographyDto;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface StenographyService {
    List<StenographyDto> findAll();
    List<StenographyDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    int deleteStenography(Integer id);
    void save(StenographyDto dto);
    List<StenographyDto> searchFileSteno(int meetingId, int sessionId, String nameFileSteno, int createUser, Timestamp timeStart, Timestamp timeEnd);
}
