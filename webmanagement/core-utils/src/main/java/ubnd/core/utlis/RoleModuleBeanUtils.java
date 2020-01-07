package ubnd.core.utlis;

import ubnd.core.dto.RoleModuleDto;
import ubnd.core.dto.RolePermissionDto;
import ubnd.core.persistence.data.entity.RoleModuleEntity;
import ubnd.core.persistence.data.entity.RolePermissionEntity;

public class RoleModuleBeanUtils {
    public static RoleModuleEntity dtoToEntity(RoleModuleDto dto) {
        RoleModuleEntity entity = new RoleModuleEntity();
        entity.setRoleModuleId(dto.getRoleModuleId());
        entity.setModuleRoleEntity(ModuleBeanUtils.dtoToEntity(dto.getModuleDto()));
        entity.setRoleModuleEntity(RoleBeanUtils.dtoToEntity(dto.getRoleDto()));
        return entity;
    }

    public static RoleModuleDto entityToDto(RoleModuleEntity entity) {
        RoleModuleDto dto = new RoleModuleDto();
        dto.setRoleModuleId(entity.getRoleModuleId());
        dto.setModuleDto(ModuleBeanUtils.entityToDTO(entity.getModuleRoleEntity()));
        dto.setRoleDto(RoleBeanUtils.entityToDTO(entity.getRoleModuleEntity()));
        return dto;
    }

}
