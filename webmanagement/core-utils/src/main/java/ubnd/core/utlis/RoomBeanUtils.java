package ubnd.core.utlis;

import ubnd.core.dto.EquipmentDto;
import ubnd.core.dto.RoomDto;
import ubnd.core.persistence.data.entity.EquipmentEntity;
import ubnd.core.persistence.data.entity.RoomEntity;

import java.util.ArrayList;
import java.util.List;

public class RoomBeanUtils {
    public static RoomDto entityToDTO(RoomEntity entity) {
        RoomDto dto = new RoomDto();
        dto.setRoomId(entity.getRoomId());
        dto.setRoomName(entity.getRoomName());
        dto.setRoomDescription(entity.getRoomDescription());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public static RoomEntity dtoToEntity(RoomDto dto) {
        RoomEntity entity = new RoomEntity();
        entity.setRoomId(dto.getRoomId());
        entity.setRoomName(dto.getRoomName());
        entity.setRoomDescription(dto.getRoomDescription());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    private List<EquipmentDto> getListEquipDto(List<EquipmentEntity> entities){
        List<EquipmentDto> dtos = new ArrayList<EquipmentDto>();
        for(EquipmentEntity item: entities){
            EquipmentDto dto = EquipmentBeanUtils.entityToDTO(item);
            dtos.add(dto);
        }
        return dtos;
    }
}
