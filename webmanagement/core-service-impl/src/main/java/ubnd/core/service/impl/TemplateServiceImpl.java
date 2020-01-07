package ubnd.core.service.impl;

import ubnd.core.dto.TemplateDto;
import ubnd.core.persistence.data.entity.TemplateEntity;
import ubnd.core.service.TemplateService;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.utlis.TemplateBeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TemplateServiceImpl implements TemplateService {

    public List<TemplateDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = SingletonDaoUtil.getTemplateDaoInstance().findByProperty(property, sortExpression, sortDirection, offset, limit, null);
        List<TemplateDto> templateDtoList = new ArrayList<>();
        for(TemplateEntity item: (List<TemplateEntity>)objects[1]){
            TemplateDto templateDto = TemplateBeanUtils.entityToDTO(item);
            templateDtoList.add(templateDto);
        }
        return templateDtoList;
    }

    public TemplateDto findById(Integer id) {
        TemplateEntity entity = SingletonDaoUtil.getTemplateDaoInstance().findByID(id);
        return TemplateBeanUtils.entityToDTO(entity);
    }

    public TemplateDto updateTemplate(TemplateDto dto) {
        TemplateEntity entity = TemplateBeanUtils.dtoToEntity(dto);
        entity = SingletonDaoUtil.getTemplateDaoInstance().update(entity);
        return TemplateBeanUtils.entityToDTO(entity);
    }

    public void saveTemplate(TemplateDto dto) {
        TemplateEntity entity = TemplateBeanUtils.dtoToEntity(dto);
        SingletonDaoUtil.getTemplateDaoInstance().save(entity);
    }

    public int deleteTemplate(Integer id) {
        return SingletonDaoUtil.getTemplateDaoInstance().delete(id);
    }


}
