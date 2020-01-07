package ubnd.core.utlis;

import ubnd.core.dto.MeetingDto;
import ubnd.core.dto.ModuleDto;
import ubnd.core.persistence.data.entity.MeetingEntity;
import ubnd.core.persistence.data.entity.ModuleEntity;

public class ModuleBeanUtils {
    public static ModuleEntity dtoToEntity(ModuleDto dto) {
        ModuleEntity entity = new ModuleEntity();
        entity.setModuleID(dto.getModuleID());
        entity.setNameModule(dto.getNameModule());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    public static ModuleDto entityToDTO(ModuleEntity entity) {
        ModuleDto dto = new ModuleDto();
        dto.setModuleID(entity.getModuleID());
        dto.setNameModule(entity.getNameModule());
        dto.setDescription(entity.getDescription());
        return dto;
    }
}
