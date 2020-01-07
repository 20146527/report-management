package ubnd.core.service;

import ubnd.core.dto.RoleModuleDto;

import java.util.List;
import java.util.Map;

public interface RoleModuleService {
    List<RoleModuleDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    void addRole(RoleModuleDto roleModuleDto);
    void deleteRole(Integer id);
}
