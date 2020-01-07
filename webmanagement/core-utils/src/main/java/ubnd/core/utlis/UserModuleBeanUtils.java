package ubnd.core.utlis;

import ubnd.core.dto.UserModuleDto;
import ubnd.core.persistence.data.entity.UserModuleEntity;

public class UserModuleBeanUtils {

    public static UserModuleEntity dtoToEntity(UserModuleDto dto){
        UserModuleEntity entity = new UserModuleEntity();
        entity.setUserModuleID(dto.getUserModuleID());
        entity.setModuleEntity(ModuleBeanUtils.dtoToEntity(dto.getModuleDto()));
        entity.setUserModuleEntity(UserBeanUtils.dtoToEntity(dto.getUserDto()));
        return entity;
    }

    public static UserModuleDto entityToDTO(UserModuleEntity entity){
        UserModuleDto dto = new UserModuleDto();
        dto.setUserModuleID(entity.getUserModuleID());
        dto.setModuleDto(ModuleBeanUtils.entityToDTO(entity.getModuleEntity()));
        dto.setUserDto(UserBeanUtils.entityToDTO(entity.getUserModuleEntity()));
        return dto;
    }
}
