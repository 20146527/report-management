package ubnd.core.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ubnd.common.utils.HibernateUtils;
import ubnd.core.dao.ConfigDao;
import ubnd.core.data.daoimpl.AbstractDao;
import ubnd.core.persistence.data.entity.ConfigEntity;

import java.sql.Timestamp;


public class ConfigDaoImpl extends AbstractDao<Integer, ConfigEntity> implements ConfigDao {

    @Override
    public int updateConfigByType(Integer userId, String title, String value, Timestamp modDate) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int result = 0;
        try {
            Query query = session.createQuery("UPDATE ConfigEntity SET value = :value , modDate = :modDate WHERE title = :title AND creUID = :creUID");
            query.setParameter("value", value);
            query.setParameter("modDate", modDate);
            query.setParameter("title", title);
            query.setParameter("creUID", userId);
            result = query.executeUpdate();
            transaction.commit();
        }catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }
}

