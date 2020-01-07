package ubnd.core.utlis;

import ubnd.core.dto.MeetingDto;
import ubnd.core.persistence.data.entity.MeetingEntity;

public class MeetingBeanUtils {
    public static MeetingEntity dtoToEntity(MeetingDto dto){
        MeetingEntity entity = new MeetingEntity();
        entity.setMeetingId(dto.getMeetingId());
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
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
    public static MeetingDto entityToDTO(MeetingEntity entity){
        MeetingDto dto = new MeetingDto();
        dto.setMeetingId(entity.getMeetingId());
        dto.setName(entity.getName());
        dto.setAddress(entity.getAddress());
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
