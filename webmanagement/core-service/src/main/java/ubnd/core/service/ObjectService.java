package ubnd.core.service;

import ubnd.core.dto.ObjectDto;

import java.util.List;
import java.util.Map;

public interface ObjectService {
    List<ObjectDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    List<ObjectDto> findAll();
    ObjectDto findByID(Integer id);
    void saveObject(ObjectDto dto);
    void deleteObject(Integer id);
}
