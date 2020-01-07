package ubnd.core.service;

import ubnd.core.dto.RolePermissionDto;

import java.util.List;
import java.util.Map;

public interface RolePermissionService {
    List<RolePermissionDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    void addPermission(RolePermissionDto rolePermissionDto);
    void deletePermission(Integer id);
}
