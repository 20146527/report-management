package ubnd.core.service;

import ubnd.core.dto.SpeakerDto;
import ubnd.core.dto.TemplateDto;

import java.util.List;
import java.util.Map;

public interface TemplateService {
    List<TemplateDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    TemplateDto findById(Integer id);
    TemplateDto updateTemplate(TemplateDto dto);
    void saveTemplate(TemplateDto dto);
    int deleteTemplate(Integer id);
}
