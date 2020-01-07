package ubnd.core.utlis;

import ubnd.core.dto.StenographyDto;
import ubnd.core.persistence.data.entity.StenographyEntity;

public class StenographyBeanUtils {
    public static StenographyEntity dtoToEntity(StenographyDto dto){
        StenographyEntity entity = new StenographyEntity();
        entity.setStenoId(dto.getStenoId());
        entity.setSessionStenoEntity(SessionBeanUtils.dtoToEntity(dto.getSessionDto()));
        entity.setName(dto.getName());
        entity.setContent(dto.getContent());
        entity.setStatus(dto.getStatus());
        entity.setCreUID(dto.getCreUID());
        entity.setCreDate(dto.getCreDate());
        entity.setModUID(dto.getModUID());
        entity.setModDate(dto.getModDate());
        return entity;
    }
    public static StenographyDto entityToDTO(StenographyEntity entity){
        StenographyDto dto = new StenographyDto();
        dto.setStenoId(entity.getStenoId());
        dto.setSessionDto(SessionBeanUtils.entityToDTO(entity.getSessionStenoEntity()));
        dto.setName(entity.getName());
        dto.setContent(entity.getContent());
        dto.setStatus(entity.getStatus());
        dto.setCreUID(entity.getCreUID());
        dto.setCreDate(entity.getCreDate());
        dto.setModUID(entity.getModUID());
        dto.setModDate(entity.getModDate());
        return dto;
    }


}
