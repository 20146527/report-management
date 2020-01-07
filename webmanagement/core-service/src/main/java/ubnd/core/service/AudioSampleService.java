package ubnd.core.service;

import ubnd.core.dto.AudioSampleDto;
import ubnd.core.dto.SpeakerDto;

import java.util.List;
import java.util.Map;

public interface AudioSampleService {
    List<AudioSampleDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    AudioSampleDto updateAudioSample(AudioSampleDto audioSampleDto);
    void saveAudioSample(AudioSampleDto audioSampleDto);

}
