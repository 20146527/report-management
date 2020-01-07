package ubnd.core.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ubnd.common.utils.HibernateUtils;
import ubnd.core.dao.EquipmentDao;
import ubnd.core.data.daoimpl.AbstractDao;
import ubnd.core.persistence.data.entity.EquipmentEntity;

public class EquipmentDaoImpl extends AbstractDao<Integer, EquipmentEntity> implements EquipmentDao {
    public int enableDisable(Integer equipmentId, Integer status) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int result = 0;
        try {
            Query query = session.createQuery("UPDATE EquipmentEntity SET status = :status WHERE equipmentId = :equipmentId");
            query.setParameter("status", status);
            query.setParameter("equipmentId", equipmentId);
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
