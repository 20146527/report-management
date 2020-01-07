package ubnd.core.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ubnd.common.utils.HibernateUtils;
import ubnd.core.dao.RoomDao;
import ubnd.core.data.daoimpl.AbstractDao;
import ubnd.core.persistence.data.entity.RoomEntity;

public class RoomDaoImpl extends AbstractDao<Integer, RoomEntity> implements RoomDao {
    public int edRoom(Integer roomId, Integer status) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int result = 0;
        try {
            Query query = session.createQuery("UPDATE RoomEntity SET status = :status WHERE roomId = :roomId");
            query.setParameter("status", status);
            query.setParameter("roomId", roomId);
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
