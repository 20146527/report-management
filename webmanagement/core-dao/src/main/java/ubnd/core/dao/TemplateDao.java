package ubnd.core.dao;

import ubnd.core.data.dao.GenericDao;
import ubnd.core.persistence.data.entity.TemplateEntity;

public interface TemplateDao extends GenericDao<Integer, TemplateEntity> {
    int delete(Integer id);
}
