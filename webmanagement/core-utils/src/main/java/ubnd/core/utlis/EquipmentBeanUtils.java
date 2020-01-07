package ubnd.core.utlis;

import ubnd.core.dto.EquipmentDto;
import ubnd.core.persistence.data.entity.EquipmentEntity;

public class EquipmentBeanUtils {

    public static EquipmentDto entityToDTO(EquipmentEntity entity) {
        EquipmentDto dto = new EquipmentDto();
        dto.setEquipmentId(entity.getEquipmentId());
        dto.setRoomDto(RoomBeanUtils.entityToDTO(entity.getRoomEquipmentEntity()));
        dto.setName(entity.getName());
        dto.setBrand(entity.getBrand());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        dto.setDayStart(entity.getDayStart());
        return dto;
    }

    public static EquipmentEntity dtoToEntity(EquipmentDto dto) {
        EquipmentEntity entity = new EquipmentEntity();
        entity.setEquipmentId(dto.getEquipmentId());
        entity.setRoomEquipmentEntity(RoomBeanUtils.dtoToEntity(dto.getRoomDto()));
        entity.setName(dto.getName());
        entity.setBrand(dto.getBrand());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
        entity.setDayStart(dto.getDayStart());
        return entity;
    }

}
