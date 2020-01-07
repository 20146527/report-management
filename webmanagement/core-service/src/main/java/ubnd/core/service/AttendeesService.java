package ubnd.core.service;

import ubnd.core.dto.AttendeesDto;

import java.util.List;
import java.util.Map;

public interface AttendeesService {
    List<AttendeesDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    AttendeesDto findById(Integer id);
    AttendeesDto update(AttendeesDto dto);
    void save(AttendeesDto dto);
    Integer deleteMember(List<Integer> ids);
    List<AttendeesDto> findPreside(Integer presideId);
    void attendance(Integer id);
}
