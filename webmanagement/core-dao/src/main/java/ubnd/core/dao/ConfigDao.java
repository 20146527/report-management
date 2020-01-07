package ubnd.core.dao;

import ubnd.core.data.dao.GenericDao;
import ubnd.core.persistence.data.entity.ConfigEntity;

import java.sql.Timestamp;

public interface ConfigDao extends GenericDao<Integer, ConfigEntity> {
    int updateConfigByType(Integer userId, String type, String value, Timestamp modDat);
}
