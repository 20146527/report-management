package ubnd.core.service;


import org.json.JSONArray;
import ubnd.core.dto.ConfigDto;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface ConfigService {
    List<ConfigDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    void initNewConfig(Integer userId);
    void updateConfig(Integer userId, JSONArray data, Timestamp modDate);

}
