package ubnd.core.utlis;

import ubnd.core.dto.SpeakerDto;
import ubnd.core.persistence.data.entity.SpeakerEntity;

public class SpeakerBeanUtils {
    public static SpeakerEntity dtoToEntity(SpeakerDto dto){
        SpeakerEntity entity = new SpeakerEntity();
        entity.setSpeakerId(dto.getSpeakerId());
        entity.setFullName(dto.getFullName());
        entity.setOtherName(dto.getOtherName());
        entity.setGender(dto.getGender());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setBirthday(dto.getBirthday());
        entity.setRegency(dto.getRegency());
        entity.setStatus(dto.getStatus());
        entity.setCreUID(dto.getCreUID());
        entity.setCreDate(dto.getCreDate());
        entity.setModUID(dto.getModUID());
        entity.setModDate(dto.getModDate());
        return entity;
    }
    public static SpeakerDto entityToDTO(SpeakerEntity entity){
        SpeakerDto dto = new SpeakerDto();
        dto.setSpeakerId(entity.getSpeakerId());
        dto.setFullName(entity.getFullName());
        dto.setOtherName(entity.getOtherName());
        dto.setGender(entity.getGender());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setBirthday(entity.getBirthday());
        dto.setRegency(entity.getRegency());
        dto.setStatus(entity.getStatus());
        dto.setCreUID(entity.getCreUID());
        dto.setCreDate(entity.getCreDate());
        dto.setModUID(entity.getModUID());
        dto.setModDate(entity.getModDate());
        return dto;
    }


}
