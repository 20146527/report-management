package ubnd.core.utlis;

import ubnd.core.dto.ExtendPermissionUserDto;
import ubnd.core.dto.RolePermissionDto;
import ubnd.core.persistence.data.entity.ExtendPermissionUserEntity;
import ubnd.core.persistence.data.entity.RolePermissionEntity;

public class ExtendPermissionUserBeanUtils {
    public static ExtendPermissionUserEntity dtoToEntity(ExtendPermissionUserDto dto) {
        ExtendPermissionUserEntity entity = new ExtendPermissionUserEntity();
        entity.setExtendPermissionUserId(dto.getExtendPermissionUserId());
        entity.setUserExtendPermissionEntity(UserBeanUtils.dtoToEntity(dto.getUserDto()));
        entity.setObjectExtendPermissionEntity(ObjectBeanUtils.dtoToEntity(dto.getObjectDto()));
        entity.setOperatorExtendPermissionEntity(OperatorBeanUtils.dtoToEntity(dto.getOperatorDto()));
        entity.setCount(dto.getCount());
        entity.setRequestTag(dto.getRequestTag());
        entity.setCreUID(dto.getCreUID());
        entity.setCreDate(dto.getCreDate());
        return entity;
    }

    public static ExtendPermissionUserDto entityToDto(ExtendPermissionUserEntity entity) {
        ExtendPermissionUserDto dto = new ExtendPermissionUserDto();
        dto.setExtendPermissionUserId(entity.getExtendPermissionUserId());
        dto.setUserDto(UserBeanUtils.entityToDTO(entity.getUserExtendPermissionEntity()));
        dto.setObjectDto(ObjectBeanUtils.entityToDTO(entity.getObjectExtendPermissionEntity()));
        dto.setOperatorDto(OperatorBeanUtils.entityToDto(entity.getOperatorExtendPermissionEntity()));
        dto.setCount(entity.getCount());
        dto.setRequestTag(entity.getRequestTag());
        dto.setCreUID(entity.getCreUID());
        dto.setCreDate(entity.getCreDate());
        return dto;
    }

}
