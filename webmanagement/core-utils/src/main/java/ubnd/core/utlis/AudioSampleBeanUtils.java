package ubnd.core.utlis;

import ubnd.core.dto.AudioSampleDto;
import ubnd.core.persistence.data.entity.AudioSampleEntity;

public class AudioSampleBeanUtils {
    public static AudioSampleEntity dtoToEntity(AudioSampleDto dto){
        AudioSampleEntity entity = new AudioSampleEntity();
        entity.setAudioSampleId(dto.getAudioSampleId());
        entity.setName(dto.getName());
        entity.setPathAudio(dto.getPathAudio());
        entity.setTimeIndex(dto.getTimeIndex());
        entity.setSize(dto.getSize());
        entity.setSys(dto.getSys());
        entity.setSpeakerEntity(SpeakerBeanUtils.dtoToEntity(dto.getSpeakerDto()));
        entity.setStatus(dto.getStatus());
        entity.setCreUID(dto.getCreUID());
        entity.setCreDate(dto.getCreDate());
        entity.setModUID(dto.getModUID());
        entity.setModDate(dto.getModDate());
        return entity;
    }
    public static AudioSampleDto entityToDTO(AudioSampleEntity entity){
        AudioSampleDto dto = new AudioSampleDto();
        dto.setAudioSampleId(entity.getAudioSampleId());
        dto.setName(entity.getName());
        dto.setPathAudio(entity.getPathAudio());
        dto.setTimeIndex(entity.getTimeIndex());
        dto.setSize(entity.getSize());
        dto.setSys(entity.getSys());
        dto.setSpeakerDto(SpeakerBeanUtils.entityToDTO(entity.getSpeakerEntity()));
        dto.setStatus(entity.getStatus());
        dto.setCreUID(entity.getCreUID());
        dto.setCreDate(entity.getCreDate());
        dto.setModUID(entity.getModUID());
        dto.setModDate(entity.getModDate());
        return dto;
    }


}
