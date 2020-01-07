package ubnd.core.utlis;

import ubnd.core.dto.ConfigDto;
import ubnd.core.dto.SpeakerDto;
import ubnd.core.persistence.data.entity.ConfigEntity;
import ubnd.core.persistence.data.entity.SpeakerEntity;

public class ConfigBeanUtils {
    public static ConfigEntity dtoToEntity(ConfigDto dto){
        ConfigEntity entity = new ConfigEntity();
        entity.setConfigId(dto.getConfigId());
        entity.setType(dto.getType());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setValue(dto.getValue());
        entity.setStatus(dto.getStatus());
        entity.setCreUID(dto.getCreUID());
        entity.setCreDate(dto.getCreDate());
        entity.setModUID(dto.getModUID());
        entity.setModDate(dto.getModDate());
        return entity;
    }
    public static ConfigDto entityToDTO(ConfigEntity entity){
        ConfigDto dto = new ConfigDto();
        dto.setConfigId(entity.getConfigId());
        dto.setType(entity.getType());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setValue(entity.getValue());
        dto.setStatus(entity.getStatus());
        dto.setCreUID(entity.getCreUID());
        dto.setCreDate(entity.getCreDate());
        dto.setModUID(entity.getModUID());
        dto.setModDate(entity.getModDate());
        return dto;
    }


}
