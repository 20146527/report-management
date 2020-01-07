package ubnd.core.service.impl;

import ubnd.core.dto.SpeakerDto;
import ubnd.core.persistence.data.entity.SpeakerEntity;
import ubnd.core.service.SpeakerService;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.utlis.SpeakerBeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SpeakerServiceImpl implements SpeakerService {
    public List<SpeakerDto> findAll() {
        List<SpeakerEntity> entityList = SingletonDaoUtil.getSpeakerDaoInstance().findAll();
        List<SpeakerDto> dtoList = new ArrayList<SpeakerDto>();
        for(SpeakerEntity item: entityList){
           SpeakerDto dto = SpeakerBeanUtils.entityToDTO(item);
           dtoList.add(dto);
        }
        return dtoList;
    }

    public List<SpeakerDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = SingletonDaoUtil.getSpeakerDaoInstance().findByProperty(property, sortExpression, sortDirection, offset, limit, null);
        List<SpeakerDto> speakerDtoList = new ArrayList<SpeakerDto>();
        for(SpeakerEntity item: (List<SpeakerEntity>)objects[1]){
            SpeakerDto speakerDto = SpeakerBeanUtils.entityToDTO(item);
            speakerDtoList.add(speakerDto);
        }
        return speakerDtoList;
    }


    public SpeakerDto findById(Integer speakerId){
        SpeakerEntity entity = SingletonDaoUtil.getSpeakerDaoInstance().findByID(speakerId);
        SpeakerDto dto = SpeakerBeanUtils.entityToDTO(entity);
        return dto;
    }

    public SpeakerDto updateSpeaker(SpeakerDto speakerDto){
        SpeakerEntity entity = SpeakerBeanUtils.dtoToEntity(speakerDto);
        entity = SingletonDaoUtil.getSpeakerDaoInstance().update(entity);
        speakerDto = SpeakerBeanUtils.entityToDTO(entity);
        return speakerDto;
    }

    public void saveSpeaker(SpeakerDto speakerDto){
        SpeakerEntity entity = SpeakerBeanUtils.dtoToEntity(speakerDto);
        SingletonDaoUtil.getSpeakerDaoInstance().save(entity);
    }
}
