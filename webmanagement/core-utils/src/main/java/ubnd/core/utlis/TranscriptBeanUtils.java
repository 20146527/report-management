package ubnd.core.utlis;


import ubnd.core.dto.TranscriptDto;
import ubnd.core.persistence.data.entity.TranscriptEntity;

public class TranscriptBeanUtils {
    public static TranscriptDto entityToDTO(TranscriptEntity entity) {
        TranscriptDto dto = new TranscriptDto();
        dto.setTranscriptId(entity.getTranscriptId());
        dto.setRecordDto(RecordBeanUtils.entityToDTO(entity.getRecordTranscriptEnity()));
        dto.setSpeakerId(entity.getSpeakerId());
        dto.setAudioPath(entity.getAudioPath());
        dto.setContent(entity.getContent());
        dto.setJsonPath(entity.getJsonPath());
        dto.setXmlPath(entity.getXmlPath());
        return dto;
    }

    public static TranscriptEntity dtoToEntity(TranscriptDto dto) {
        TranscriptEntity entity = new TranscriptEntity();
        entity.setTranscriptId(dto.getTranscriptId());
        entity.setRecordTranscriptEnity(RecordBeanUtils.dtoToEntity(dto.getRecordDto()));
        entity.setSpeakerId(dto.getSpeakerId());
        entity.setAudioPath(dto.getAudioPath());
        entity.setContent(dto.getContent());
        entity.setJsonPath(dto.getJsonPath());
        entity.setXmlPath(dto.getXmlPath());
        return entity;
    }

}
