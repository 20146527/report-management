package ubnd.core.utlis;

import ubnd.core.dto.RoleDto;
import ubnd.core.persistence.data.entity.RoleEntity;

public class RoleBeanUtils {
    public static RoleDto entityToDTO(RoleEntity enity) {
        RoleDto dto = new RoleDto();
        dto.setRoleId(enity.getRoleId());
        dto.setRoleName(enity.getRoleName());
        dto.setRoleDescription(enity.getRoleDescription());
        return dto;
    }

    public static RoleEntity dtoToEntity(RoleDto dto) {
        RoleEntity enity = new RoleEntity();
        enity.setRoleId(dto.getRoleId());
        enity.setRoleName(dto.getRoleName());
        enity.setRoleDescription(dto.getRoleDescription());
        return enity;
    }
}
