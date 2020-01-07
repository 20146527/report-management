package ubnd.core.dao;

import ubnd.core.data.dao.GenericDao;
import ubnd.core.dto.UserModuleDto;
import ubnd.core.persistence.data.entity.UserModuleEntity;

import java.util.List;

public interface UserModuleDao extends GenericDao<Integer, UserModuleEntity> {
    List<UserModuleEntity> findByModuleID(Integer id);
}
