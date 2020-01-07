package ubnd.core.utlis;

import ubnd.core.dto.DutyDto;
import ubnd.core.persistence.data.entity.DutyEntity;

public class DutyBeanUtils {
    public static DutyDto entityToDTO(DutyEntity entity) {
        DutyDto dto = new DutyDto();
        dto.setDutyId(entity.getDutyId());
        dto.setDutyName(entity.getDutyName());
        dto.setDutyDescription(entity.getDutyDescription());
        return dto;
    }

    public static DutyEntity dtoToEntity(DutyDto dto) {
        DutyEntity entity = new DutyEntity();
        entity.setDutyId(dto.getDutyId());
        entity.setDutyName(dto.getDutyName());
        entity.setDutyDescription(dto.getDutyDescription());
        return entity;
    }
}
