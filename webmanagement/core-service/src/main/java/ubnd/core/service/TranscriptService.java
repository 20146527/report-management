package ubnd.core.service;

import ubnd.core.dto.ParagraphTranscriptDto;
import ubnd.core.dto.TranscriptDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface TranscriptService {
    List<TranscriptDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    List<TranscriptDto> findAll();
    TranscriptDto findById(Integer id);
    void save(TranscriptDto dto);
    TranscriptDto update(TranscriptDto dto);
    String createXML(HttpServletRequest request, String folderName, String fileName, String data);
    String writeJSONContent(HttpServletRequest request, String folderName, String fileName, String data);
    void updateXML(HttpServletRequest request, String xmlPath,  String content, String speaker);
    List<ParagraphTranscriptDto> getParagraphTransByXML(String path);
}
