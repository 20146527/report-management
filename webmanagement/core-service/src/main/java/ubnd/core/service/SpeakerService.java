package ubnd.core.service;

import ubnd.core.dto.DictStenoDTO;
import ubnd.core.dto.SpeakerDto;

import java.util.List;
import java.util.Map;

public interface SpeakerService {
    List<SpeakerDto> findAll();
    List<SpeakerDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    SpeakerDto findById(Integer speakerId);
    SpeakerDto updateSpeaker(SpeakerDto speakerDto);
    void saveSpeaker(SpeakerDto speakerDto);

}
