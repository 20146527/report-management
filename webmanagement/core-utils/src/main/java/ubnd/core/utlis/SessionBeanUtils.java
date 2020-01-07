package ubnd.core.utlis;

import ubnd.core.dto.SessionDto;
import ubnd.core.persistence.data.entity.SessionEntity;

public class SessionBeanUtils {
    public static SessionEntity dtoToEntity(SessionDto dto){
        SessionEntity entity = new SessionEntity();
        entity.setSessionId(dto.getSessionId());
        entity.setMeetingEntity(MeetingBeanUtils.dtoToEntity(dto.getMeetingDto()));
        entity.setName(dto.getName());
        entity.setRoomSessionEntity(RoomBeanUtils.dtoToEntity(dto.getRoomDto()));
        entity.setTimeStart(dto.getTimeStart());
        entity.setTimeEnd(dto.getTimeEnd());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
        entity.setCreUID(dto.getCreUID());
        entity.setCreDate(dto.getCreDate());
        entity.setModUID(dto.getModUID());
        entity.setModDate(dto.getModDate());
        return entity;
    }
    public static SessionDto entityToDTO(SessionEntity entity){
        SessionDto dto = new SessionDto();
        dto.setSessionId(entity.getSessionId());
        dto.setMeetingDto(MeetingBeanUtils.entityToDTO(entity.getMeetingEntity()));
        dto.setName(entity.getName());
        dto.setRoomDto(RoomBeanUtils.entityToDTO(entity.getRoomSessionEntity()));
        dto.setTimeStart(entity.getTimeStart());
        dto.setTimeEnd(entity.getTimeEnd());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        dto.setCreUID(entity.getCreUID());
        dto.setCreDate(entity.getCreDate());
        dto.setModUID(entity.getModUID());
        dto.setModDate(entity.getModDate());
        return dto;
    }


}
