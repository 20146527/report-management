package ubnd.core.service;

import ubnd.core.dto.UserRoleDto;

import java.util.List;
import java.util.Map;

public interface UserRoleService {
    List<UserRoleDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    void addRole(UserRoleDto userRoleDto);
    void deleteRole(Integer id);
}
