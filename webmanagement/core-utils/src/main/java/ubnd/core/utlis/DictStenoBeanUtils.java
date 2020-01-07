package ubnd.core.utlis;

import ubnd.core.dto.DictStenoDTO;
import ubnd.core.persistence.data.entity.DictStenoEntity;

public class DictStenoBeanUtils {
    public static DictStenoEntity dtoToEntity(DictStenoDTO dto){
        DictStenoEntity entity = new DictStenoEntity();
        entity.setDictId(dto.getDictId());
        entity.setDictName(dto.getDictName());
        entity.setLength(dto.getLength());
        entity.setContent(dto.getContent());
        entity.setDictDefault(dto.getDictDefault());
        entity.setStatus(dto.getStatus());
        entity.setCreUID(dto.getCreUID());
        entity.setCreDate(dto.getCreDate());
        entity.setModUID(dto.getModUID());
        entity.setModDate(dto.getModDate());
        return entity;
    }
    public static DictStenoDTO entityToDTO(DictStenoEntity entity){
        DictStenoDTO dto = new DictStenoDTO();
        dto.setDictId(entity.getDictId());
        dto.setDictName(entity.getDictName());
        dto.setLength(entity.getLength());
        dto.setContent(entity.getContent());
        dto.setDictDefault(entity.getDictDefault());
        dto.setStatus(entity.getStatus());
        dto.setCreUID(entity.getCreUID());
        dto.setCreDate(entity.getCreDate());
        dto.setModUID(entity.getModUID());
        dto.setModDate(entity.getModDate());
        return dto;
    }


}
