package ubnd.core.utlis;

import ubnd.core.dto.ReportDto;
import ubnd.core.persistence.data.entity.ReportEntity;

public class ReportBeanUtils {
    public static ReportEntity dtoToEntity(ReportDto dto){
        ReportEntity entity = new ReportEntity();
        entity.setReportId(dto.getReportId());
        entity.setName(dto.getName());
        entity.setSessionReportEntity(SessionBeanUtils.dtoToEntity(dto.getSessionDto()));
        entity.setTemplateReportEntity(TemplateBeanUtils.dtoToEntity(dto.getTemplateDto()));
        entity.setRecordReportEntity(RecordBeanUtils.dtoToEntity(dto.getRecordDto()));
        entity.setStenographyReportEntity(StenographyBeanUtils.dtoToEntity(dto.getStenographyDto()));
        entity.setTag(dto.getTag());
        entity.setContent(dto.getContent());
        entity.setStatus(dto.getStatus());
        entity.setCreUID(dto.getCreUID());
        entity.setCreDate(dto.getCreDate());
        entity.setModUID(dto.getModUID());
        entity.setModDate(dto.getModDate());
        return entity;
    }
    public static ReportDto entityToDTO(ReportEntity entity){
        ReportDto dto = new ReportDto();
        dto.setReportId(entity.getReportId());
        dto.setName(entity.getName());
        dto.setSessionDto(SessionBeanUtils.entityToDTO(entity.getSessionReportEntity()));
        dto.setTemplateDto(TemplateBeanUtils.entityToDTO(entity.getTemplateReportEntity()));
        dto.setRecordDto(RecordBeanUtils.entityToDTO(entity.getRecordReportEntity()));
        dto.setStenographyDto(StenographyBeanUtils.entityToDTO(entity.getStenographyReportEntity()));
        dto.setTag(entity.getTag());
        dto.setContent(entity.getContent());
        dto.setStatus(entity.getStatus());
        dto.setCreUID(entity.getCreUID());
        dto.setCreDate(entity.getCreDate());
        dto.setModUID(entity.getModUID());
        dto.setModDate(entity.getModDate());
        return dto;
    }


}
