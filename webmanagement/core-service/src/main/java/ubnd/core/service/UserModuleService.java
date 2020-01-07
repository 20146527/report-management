package ubnd.core.service;

import ubnd.core.dto.UserModuleDto;

import java.util.List;
import java.util.Map;

public interface UserModuleService {
    List<UserModuleDto> findAll();
    List<UserModuleDto> findByModuleID(Integer id);
    void save(UserModuleDto dto);
    void delete(Integer id);
    List<UserModuleDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);

}
