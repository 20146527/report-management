package ubnd.core.data.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericDao<ID extends Serializable, T> {
    List<T> findAll();

    T update(T entity);

    void save(T entity);

    T findByID(ID id);

    Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit, String whereClause);

    Integer delete(List<ID> ids);

//    T findEqualUnique(String property, Object value);

}
