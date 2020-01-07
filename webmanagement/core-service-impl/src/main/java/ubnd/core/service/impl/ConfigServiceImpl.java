package ubnd.core.service.impl;


import org.json.JSONArray;
import org.json.JSONObject;
import ubnd.core.dto.ConfigDto;
import ubnd.core.persistence.data.entity.ConfigEntity;
import ubnd.core.service.ConfigService;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.utlis.ConfigBeanUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ConfigServiceImpl implements ConfigService {

    @Override
    public List<ConfigDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = SingletonDaoUtil.getConfigDaoInstance().findByProperty(property, sortExpression, sortDirection, offset, limit, null);
        List<ConfigDto> configDtoList = new ArrayList<>();
        for(ConfigEntity item: (List<ConfigEntity>)objects[1]){
            ConfigDto dto = ConfigBeanUtils.entityToDTO(item);
            configDtoList.add(dto);
        }
        return configDtoList;
    }

    @Override
    public void initNewConfig(Integer userId) {

    }

    @Override
    public void updateConfig(Integer userId, JSONArray data, Timestamp modDate) {
        for(int i = 0; i < data.length(); i++){
            JSONObject object = data.getJSONObject(i);
            String title = object.getString("title");
            String value = object.getString("value");
            if(title.equals("tag")){
                value = getValueTagConfig(value);
                System.out.println(value);
            }else if(title.equals("pathSteno")){
                value = value.replace("\\", "/");
            }
            SingletonDaoUtil.getConfigDaoInstance().updateConfigByType(userId, title, value, modDate);
        }
    }

    private String getValueTagConfig(String tagValue){
        String value = "";
        JSONArray jsonArray = new JSONArray(tagValue);
        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject object = jsonArray.getJSONObject(i);
            if(value.equals("")){
                value += object.getString("value");
            }else {
                value += ", " + object.getString("value");
            }
        }
        return value;
    }


}
