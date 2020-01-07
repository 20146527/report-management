package ubnd.core.dao;

import ubnd.core.data.dao.GenericDao;
import ubnd.core.persistence.data.entity.RoleEntity;

import java.util.List;

public interface RoleDao extends GenericDao<Integer, RoleEntity> {
    List<RoleEntity> findByRoles(List<String> roles);
}
