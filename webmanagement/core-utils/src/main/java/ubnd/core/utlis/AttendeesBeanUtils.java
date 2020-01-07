package ubnd.core.utlis;

import ubnd.core.dto.AttendeesDto;
import ubnd.core.persistence.data.entity.AttendeesEntity;

public class AttendeesBeanUtils {
    public static AttendeesDto entityToDTO(AttendeesEntity entity) {
        AttendeesDto dto = new AttendeesDto();
        dto.setAttendeesId(entity.getAttendeesId());
        dto.setSessionDto(SessionBeanUtils.entityToDTO(entity.getSessionEntity()));
        dto.setSpeakerDto(SpeakerBeanUtils.entityToDTO(entity.getSpeakerAttendeesEntity()));
        dto.setDutyDto(DutyBeanUtils.entityToDTO(entity.getDutyEntity()));
        dto.setStatus(entity.getStatus());
        dto.setCreUID(entity.getCreUID());
        dto.setCreDate(entity.getCreDate());
        dto.setModUID(entity.getModUID());
        dto.setModDate(entity.getModDate());
        return dto;
    }

    public static AttendeesEntity dtoToEntity(AttendeesDto dto) {
        AttendeesEntity entity = new AttendeesEntity();
        entity.setAttendeesId(dto.getAttendeesId());
        entity.setSessionEntity(SessionBeanUtils.dtoToEntity(dto.getSessionDto()));
        entity.setSpeakerAttendeesEntity(SpeakerBeanUtils.dtoToEntity(dto.getSpeakerDto()));
        entity.setDutyEntity(DutyBeanUtils.dtoToEntity(dto.getDutyDto()));
        entity.setStatus(dto.getStatus());
        entity.setCreUID(dto.getCreUID());
        entity.setCreDate(dto.getCreDate());
        entity.setModUID(dto.getModUID());
        entity.setModDate(dto.getModDate());
        return entity;
    }
}
