package ubnd.core.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ubnd.common.utils.HibernateUtils;
import ubnd.core.dao.MeetingDao;
import ubnd.core.data.daoimpl.AbstractDao;
import ubnd.core.persistence.data.entity.MeetingEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MeetingDaoImpl extends AbstractDao<Integer, MeetingEntity> implements MeetingDao {

    public List<MeetingEntity> searchByHQL(String name, Timestamp timeStart, Timestamp timeEnd, Integer status) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<MeetingEntity> meetingEntityList = new ArrayList<>();
        List list;
        try{
            Query query;
            if(timeStart == null && timeEnd == null){
                StringBuilder sql = new StringBuilder("FROM MeetingEntity WHERE name LIKE :name AND status = :status");
                query = session.createQuery(sql.toString());
                query.setParameter("name", "%"+name+"%");
                query.setParameter("status", status);
            }else if(timeStart != null && timeEnd == null){
                StringBuilder sql = new StringBuilder("FROM MeetingEntity WHERE name LIKE :name AND status = :status AND timeStart >= :timeStart");
                query = session.createQuery(sql.toString());
                query.setParameter("name", "%"+name+"%");
                query.setParameter("timeStart", timeStart);
                query.setParameter("status", status);
            }else if(timeStart == null && timeEnd != null){
                StringBuilder sql = new StringBuilder("FROM MeetingEntity WHERE name LIKE :name AND status = :status AND timeEnd <= :timeEnd");
                query = session.createQuery(sql.toString());
                query.setParameter("name", "%"+name+"%");
                query.setParameter("timeEnd", timeEnd);
                query.setParameter("status", status);
            }else {
                StringBuilder sql = new StringBuilder("FROM MeetingEntity WHERE name LIKE :name AND status = :status AND timeStart >= :timeStart AND timeEnd <= :timeEnd");
                query = session.createQuery(sql.toString());
                query.setParameter("name", "%"+name+"%");
                query.setParameter("timeStart", timeStart);
                query.setParameter("timeEnd", timeEnd);
                query.setParameter("status", status);
            }
            list = query.list();

            for(int i = 0; i < list.size(); i++){
                MeetingEntity entity = (MeetingEntity) list.get(i);
                meetingEntityList.add(entity);;
            }
            transaction.commit();
        }catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return meetingEntityList;
    }

    public int delete(Integer id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int result = 0;
        try {
            Query query = session.createQuery("UPDATE MeetingEntity SET status = 1 WHERE meetingId = :meetingId");
            query.setParameter("meetingId", id);
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
