package ubnd.core.service;

import ubnd.core.dto.RecordDto;

import java.util.List;
import java.util.Map;

public interface RecordService {
    List<RecordDto> findAll();
    List<RecordDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    RecordDto findById(Integer recordId);
    RecordDto updateRecord(RecordDto recordDto);
    void saveRecord(RecordDto recordDto);
    String uploadAudio(String fullPath);
    String getFullTranscription(String token, String id);

}
