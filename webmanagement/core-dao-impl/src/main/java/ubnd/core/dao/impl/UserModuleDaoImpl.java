package ubnd.core.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ubnd.common.utils.HibernateUtils;
import ubnd.core.dao.UserModuleDao;
import ubnd.core.data.daoimpl.AbstractDao;
import ubnd.core.dto.UserModuleDto;
import ubnd.core.persistence.data.entity.UserEntity;
import ubnd.core.persistence.data.entity.UserModuleEntity;

import java.util.ArrayList;
import java.util.List;

public class UserModuleDaoImpl extends AbstractDao<Integer, UserModuleEntity> implements UserModuleDao {
    @Override
    public List<UserModuleEntity> findByModuleID(Integer id) {
        List<UserModuleEntity> list = new ArrayList<>();
        Transaction transaction;
        Session session = HibernateUtils.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("FROM UserModuleEntity us WHERE us.moduleEntity.moduleID= :moduleEntity");
            query.setParameter("moduleEntity", id);
            list = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
}
