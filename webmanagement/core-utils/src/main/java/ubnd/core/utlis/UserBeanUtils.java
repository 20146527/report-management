package ubnd.core.utlis;

import ubnd.core.dto.UserDto;
import ubnd.core.persistence.data.entity.UserEntity;

public class UserBeanUtils {

    public static UserDto entityToDTO(UserEntity entity) {
        UserDto dto = new UserDto();
        dto.setUserId(entity.getUserId());
        dto.setUserName(entity.getUserName());
        dto.setPassWord(entity.getPassWord());
        dto.setFullName(entity.getFullName());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setAddress(entity.getAddress());
        dto.setAvaPath(entity.getAvaPath());
        dto.setGender(entity.getGender());
        dto.setStatus(entity.getStatus());
        dto.setCreUID(entity.getCreUID());
        dto.setCreDate(entity.getCreDate());
        dto.setModUID(entity.getModUID());
        dto.setModDate(entity.getModDate());
        return dto;
    }

    public static UserEntity dtoToEntity(UserDto dto) {
        UserEntity entity = new UserEntity();
        entity.setUserId(dto.getUserId());
        entity.setUserName(dto.getUserName());
        entity.setPassWord(dto.getPassWord());
        entity.setFullName(dto.getFullName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setAddress(dto.getAddress());
        entity.setAvaPath(entity.getAvaPath());
        entity.setGender(dto.getGender());
        entity.setStatus(dto.getStatus());
        entity.setCreUID(dto.getCreUID());
        entity.setCreDate(dto.getCreDate());
        entity.setModUID(dto.getModUID());
        entity.setModDate(dto.getModDate());
        return entity;
    }

}
