package ubnd.core.dao.impl;

import ubnd.common.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import ubnd.core.dao.UserDao;
import ubnd.core.data.daoimpl.AbstractDao;
import ubnd.core.persistence.data.entity.UserEntity;

public class UserDaoImpl extends AbstractDao<Integer, UserEntity> implements UserDao {

    public UserEntity isUserExist(String userName, String passWord) {
        UserEntity entity = new UserEntity();
        Transaction transaction;
        Session session = HibernateUtils.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("FROM UserEntity WHERE userName= :userName AND passWord= :passWord");
            query.setParameter("userName", userName);
            query.setParameter("passWord", passWord);
            entity = (UserEntity) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return entity;
    }

    public UserEntity updateInfoUser(String userName, String passWord) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("UPDATE UserEntity set passWord= :passWord where userName= :userName");
            query.setParameter("userName", userName);
            query.setParameter("passWord", passWord);
            query.executeUpdate();

        } catch (HibernateException e) {
            transaction.rollback();
        } finally {
            session.close();
        }
        return isUserExist(userName, passWord);
    }


    public int status(Integer id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int result;
        try {
            Query query = session.createQuery("UPDATE UserEntity SET status = 1 WHERE userId = :userId");
            query.setParameter("userId", id);
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
