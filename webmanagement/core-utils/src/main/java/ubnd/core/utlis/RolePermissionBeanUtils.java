package ubnd.core.utlis;

import ubnd.core.dto.RolePermissionDto;
import ubnd.core.persistence.data.entity.RolePermissionEntity;

public class RolePermissionBeanUtils {
    public static RolePermissionEntity dtoToEntity(RolePermissionDto dto) {
        RolePermissionEntity entity = new RolePermissionEntity();
        entity.setRolePermissionId(dto.getRolePermissionId());
        entity.setRolePermissionEntity(RoleBeanUtils.dtoToEntity(dto.getRoleDto()));
        entity.setObjectPermissionEntity(ObjectBeanUtils.dtoToEntity(dto.getObjectDto()));
        entity.setOperatorPermissionEntity(OperatorBeanUtils.dtoToEntity(dto.getOperatorDto()));
        return entity;
    }

    public static RolePermissionDto entityToDto(RolePermissionEntity entity) {
        RolePermissionDto dto = new RolePermissionDto();
        dto.setRolePermissionId(entity.getRolePermissionId());
        dto.setRoleDto(RoleBeanUtils.entityToDTO(entity.getRolePermissionEntity()));
        dto.setObjectDto(ObjectBeanUtils.entityToDTO(entity.getObjectPermissionEntity()));
        dto.setOperatorDto(OperatorBeanUtils.entityToDto(entity.getOperatorPermissionEntity()));
        return dto;
    }

}
