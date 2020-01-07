package ubnd.core.service;


import ubnd.core.dto.ReportDto;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface ReportService {
    List<ReportDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    ReportDto findById(Integer id);
    ReportDto updateReport(ReportDto dto);
    void saveReport(ReportDto dto);
    List<ReportDto> searchReport(Integer sessionId, String name, Timestamp timeStart, Timestamp timeEnd, Integer status);

}
