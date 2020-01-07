package ubnd.core.data.daoimpl;

import org.apache.log4j.Logger;
import org.hibernate.*;
import ubnd.common.constant.CoreConstant;
import ubnd.common.utils.HibernateUtils;
import ubnd.core.data.dao.GenericDao;

import java.awt.*;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AbstractDao<ID extends Serializable, T> implements GenericDao<ID, T> {
    private final Logger log = Logger.getLogger(this.getClass());
    private Class<T> persistenceClass;

    public AbstractDao() {
        this.persistenceClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    private String getPersistenceClassName() {
        return persistenceClass.getSimpleName();
    }


    public List<T> findAll() {
        List<T> list;
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("from " + this.getPersistenceClassName());
            list = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            session.close();
        }
        return list;
    }

    public T update(T entity) {
        T results;
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Object obj = session.merge(entity);
            results = (T) obj;
            transaction.commit();
        } catch (HeadlessException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return results;
    }

    public void save(T entity) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {

            session.persist(entity);
            transaction.commit();

        } catch (HeadlessException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public T findByID(ID id) {
        T results;
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            results = (T) session.get(persistenceClass, id);
            if (results == null) {
                throw new ObjectNotFoundException("NOT FUOND " + id, null);
            }
            transaction.commit();
        } catch (HeadlessException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return results;
    }

    public Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit, String whereClause) {
        List<T> list;
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Object totalItem = 0;
        Object[] nameQuery = HibernateUtils.buildNameQuery(property);
        try {
            StringBuilder sql1 = new StringBuilder("from ");
            sql1.append(getPersistenceClassName()).append(" where 1=1 ").append(nameQuery[0]);
            if (sortExpression != null && sortDirection != null) {
                sql1.append(" order by ").append(sortExpression);
                sql1.append(" " +(sortDirection.equals(CoreConstant.SORT_ASC)?"asc":"desc"));
            }
            if (whereClause != null) {
                sql1.append(whereClause);
            }
            Query query1 = session.createQuery(sql1.toString());
            setParameterToQuery(nameQuery, query1);
            if (offset != null && offset >= 0) {
                query1.setFirstResult(offset);
            }
            //cho nay de max page (tam thoi bo vi khong dung displaytag)
//            if (limit != null && limit > 0) {
//                query1.setMaxResults(limit);
//            }
            list = query1.list();
//            StringBuilder sql2 = new StringBuilder("select count(*) from ");
//            sql2.append(getPersistenceClassName()).append(" where 1=1 ").append(nameQuery[0]);
//            if (whereClause != null) {
//                sql2.append(whereClause);
//            }
//            Query query2 = session.createQuery(sql2.toString());
//            setParameterToQuery(nameQuery, query2);
//            totalItem = query2.list().get(0);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            session.close();
        }
        return new Object[]{totalItem, list};
    }

    private void setParameterToQuery(Object[] nameQuery, Query query) {
        if (nameQuery.length == 3) {
            String[] params = (String[]) nameQuery[1];
            Object[] values = (Object[]) nameQuery[2];
            for (int i2 = 0; i2 < params.length ; i2++) {
                query.setParameter(params[i2], values[i2]);
            }
        }
    }

    public Integer delete(List<ID> ids) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Integer count = 0;
        try {
            for (ID item : ids) {
                T t = (T) session.get(persistenceClass, item);
                session.delete(t);
                count++;
            }
            transaction.commit();
        } catch (HeadlessException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return count;
    }

//    public T findEqualUnique(String property, Object value) {
//        return null;
//    }
}
