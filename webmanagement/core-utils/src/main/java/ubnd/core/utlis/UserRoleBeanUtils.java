package ubnd.core.utlis;

import ubnd.core.dto.RolePermissionDto;
import ubnd.core.dto.UserRoleDto;
import ubnd.core.persistence.data.entity.RolePermissionEntity;
import ubnd.core.persistence.data.entity.UserRoleEntity;

public class UserRoleBeanUtils {
    public static UserRoleEntity dtoToEntity(UserRoleDto dto) {
        UserRoleEntity entity = new UserRoleEntity();
        entity.setUserRoleId(dto.getUserRoleId());
        entity.setUserRoleEntity(UserBeanUtils.dtoToEntity(dto.getUserDto()));
        entity.setRoleUserEntity(RoleBeanUtils.dtoToEntity(dto.getRoleDto()));
        return entity;
    }

    public static UserRoleDto entityToDto(UserRoleEntity entity) {
        UserRoleDto dto = new UserRoleDto();
        dto.setUserRoleId(entity.getUserRoleId());
        dto.setUserDto(UserBeanUtils.entityToDTO(entity.getUserRoleEntity()));
        dto.setRoleDto(RoleBeanUtils.entityToDTO(entity.getRoleUserEntity()));
        return dto;
    }

}
