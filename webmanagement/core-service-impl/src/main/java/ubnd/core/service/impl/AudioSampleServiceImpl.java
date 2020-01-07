package ubnd.core.service.impl;

import ubnd.core.dto.AudioSampleDto;
import ubnd.core.persistence.data.entity.AudioSampleEntity;
import ubnd.core.service.AudioSampleService;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.utlis.AudioSampleBeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AudioSampleServiceImpl implements AudioSampleService {
    public List<AudioSampleDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = SingletonDaoUtil.getAudioSampleDaoInstance().findByProperty(property, sortExpression, sortDirection, offset, limit, null);
        List<AudioSampleDto> audioSampleDtoList = new ArrayList<AudioSampleDto>();
        for(AudioSampleEntity item: (List<AudioSampleEntity>)objects[1]){
            AudioSampleDto audioSampleDto = AudioSampleBeanUtils.entityToDTO(item);
            audioSampleDtoList.add(audioSampleDto);
        }
        return audioSampleDtoList;
    }

    public AudioSampleDto updateAudioSample(AudioSampleDto audioSampleDto) {
        AudioSampleEntity enity = AudioSampleBeanUtils.dtoToEntity(audioSampleDto);
        enity = SingletonDaoUtil.getAudioSampleDaoInstance().update(enity);
        audioSampleDto = AudioSampleBeanUtils.entityToDTO(enity);
        return audioSampleDto;
    }

    public void saveAudioSample(AudioSampleDto audioSampleDto) {
        AudioSampleEntity enity = AudioSampleBeanUtils.dtoToEntity(audioSampleDto);
        SingletonDaoUtil.getAudioSampleDaoInstance().save(enity);
    }
}
