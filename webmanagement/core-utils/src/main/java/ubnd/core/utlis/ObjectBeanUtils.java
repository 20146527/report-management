package ubnd.core.utlis;

import ubnd.core.dto.ModuleDto;
import ubnd.core.dto.ObjectDto;
import ubnd.core.persistence.data.entity.ObjectEntity;


public class ObjectBeanUtils {

    public static ObjectEntity dtoToEntity(ObjectDto dto) {
        ObjectEntity entity = new ObjectEntity();
        entity.setObjectID(dto.getObjectID());
        entity.setModuleObjectEntity(ModuleBeanUtils.dtoToEntity(dto.getModuleDto()));
        entity.setNameObject(dto.getNameObject());
        entity.setDescription(dto.getDescription());
        entity.setLocked(dto.getLocked());
        entity.setTypeObject(dto.getTypeObject());
        entity.setLinkId(dto.getLinkId());
        return entity;
    }

    public static ObjectDto entityToDTO(ObjectEntity entity) {
        ObjectDto dto = new ObjectDto();
        dto.setObjectID(entity.getObjectID());
        dto.setModuleDto(ModuleBeanUtils.entityToDTO(entity.getModuleObjectEntity()));
        dto.setNameObject(entity.getNameObject());
        dto.setDescription(entity.getDescription());
        dto.setLocked(entity.getLocked());
        dto.setTypeObject(entity.getTypeObject());
        dto.setLinkId(entity.getLinkId());
        return dto;
    }
}
