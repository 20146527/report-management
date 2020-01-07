package ubnd.core.utlis;

import ubnd.core.dto.RecordDto;
import ubnd.core.persistence.data.entity.RecordEntity;

public class RecordBeanUtils {
    public static RecordEntity dtoToEntity(RecordDto dto){
        RecordEntity entity = new RecordEntity();
        entity.setRecordId(dto.getRecordId());
        entity.setSessionRecordEntity(SessionBeanUtils.dtoToEntity(dto.getSessionDto()));
        entity.setName(dto.getName());
        entity.setLength(dto.getLength());
        entity.setPath(dto.getPath());
        entity.setStatus(dto.getStatus());
        entity.setProcessingInfo(dto.getProcessingInfo());
        return entity;
    }
    public static RecordDto entityToDTO(RecordEntity entity){
        RecordDto dto = new RecordDto();
        dto.setRecordId(entity.getRecordId());
        dto.setSessionDto(SessionBeanUtils.entityToDTO(entity.getSessionRecordEntity()));
        dto.setName(entity.getName());
        dto.setLength(entity.getLength());
        dto.setPath(entity.getPath());
        dto.setStatus(entity.getStatus());
        dto.setProcessingInfo(entity.getProcessingInfo());
        return dto;
    }


}
