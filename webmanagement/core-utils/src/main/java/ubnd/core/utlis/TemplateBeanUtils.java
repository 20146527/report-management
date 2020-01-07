package ubnd.core.utlis;

import ubnd.core.dto.TemplateDto;
import ubnd.core.persistence.data.entity.TemplateEntity;

public class TemplateBeanUtils {
    public static TemplateEntity dtoToEntity(TemplateDto dto){
        TemplateEntity entity = new TemplateEntity();
        entity.setTemplateId(dto.getTemplateId());
        entity.setName(dto.getName());
        entity.setFontSize(dto.getFontSize());
        entity.setFont(dto.getFont());
        entity.setContent(dto.getContent());
        entity.setStatus(dto.getStatus());
        entity.setCreUID(dto.getCreUID());
        entity.setCreDate(dto.getCreDate());
        entity.setModUID(dto.getModUID());
        entity.setModDate(dto.getModDate());
        return entity;
    }
    public static TemplateDto entityToDTO(TemplateEntity entity){
        TemplateDto dto = new TemplateDto();
        dto.setTemplateId(entity.getTemplateId());
        dto.setName(entity.getName());
        dto.setFontSize(entity.getFontSize());
        dto.setFont(entity.getFont());
        dto.setContent(entity.getContent());
        dto.setStatus(entity.getStatus());
        dto.setCreUID(entity.getCreUID());
        dto.setCreDate(entity.getCreDate());
        dto.setModUID(entity.getModUID());
        dto.setModDate(entity.getModDate());
        return dto;
    }


}
