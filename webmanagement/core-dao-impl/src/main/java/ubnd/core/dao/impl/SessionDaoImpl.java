package ubnd.core.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ubnd.common.utils.HibernateUtils;
import ubnd.core.dao.SessionDao;
import ubnd.core.data.daoimpl.AbstractDao;
import ubnd.core.persistence.data.entity.SessionEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class SessionDaoImpl extends AbstractDao<Integer, SessionEntity> implements SessionDao {
    public List<SessionEntity> searchByHQL(Integer meetingId, Integer roomId, String name, String description, Timestamp timeStart, Timestamp timeEnd, Integer status){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<SessionEntity> sessionEntityList = new ArrayList<>();
        List list;
        try{
            Query query = null;
            if(timeStart == null && timeEnd == null){
                StringBuilder sql = new StringBuilder("FROM SessionEntity WHERE str(meetingEntity) LIKE :meetingId AND str(roomSessionEntity) LIKE :roomId AND name LIKE :name AND description LIKE :description AND status = :status");
                query = setParamQuery(query, sql, session, meetingId, roomId, name, description, status);
            }else if(timeStart != null && timeEnd == null){
                StringBuilder sql = new StringBuilder("FROM SessionEntity WHERE str(meetingEntity) LIKE :meetingId AND str(roomSessionEntity) LIKE :roomId AND name LIKE :name AND description LIKE :description AND timeStart >= :timeStart AND status = :status");
                query = setParamQuery(query, sql, session, meetingId, roomId, name, description, status);
                query.setParameter("timeStart", timeStart);
            }else if(timeStart == null && timeEnd != null){
                StringBuilder sql = new StringBuilder("FROM SessionEntity WHERE str(meetingEntity) LIKE :meetingId AND str(roomSessionEntity) LIKE :roomId AND name LIKE :name AND description LIKE :description AND timeEnd <= :timeEnd AND status = :status");
                query = setParamQuery(query, sql, session, meetingId, roomId, name, description, status);
                query.setParameter("timeEnd", timeEnd);
            }else {
                StringBuilder sql = new StringBuilder("FROM SessionEntity WHERE str(meetingEntity) LIKE :meetingId AND str(roomSessionEntity) LIKE :roomId AND name LIKE :name AND description LIKE :description AND timeStart >= :timeStart AND timeEnd <= :timeEnd AND status = :status");
                query = setParamQuery(query, sql, session, meetingId, roomId, name, description, status);
                query.setParameter("timeStart", timeStart);
                query.setParameter("timeEnd", timeEnd);
            }
            list = query.list();

            for(int i = 0; i < list.size(); i++){
                SessionEntity entity = (SessionEntity) list.get(i);
                sessionEntityList.add(entity);;
            }
            transaction.commit();
        }catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return sessionEntityList;
    }

    private Query setParamQuery (Query query, StringBuilder sql, Session session, Integer meetingId, Integer roomId, String name, String description, Integer status){
        query = session.createQuery(sql.toString());
        if(meetingId == null || meetingId == 0){
            query.setParameter("meetingId", "%%");
        }else {
            query.setParameter("meetingId", "%"+meetingId+"%");
        }

        if(roomId == null || roomId == 0){
            query.setParameter("roomId", "%%");
        }else {
            query.setParameter("roomId", "%"+roomId+"%");
        }

        if(name == null){
            query.setParameter("name", "%%");
        }else {
            query.setParameter("name", "%"+name+"%");
        }

        if(description == null){
            query.setParameter("description", "%%");
        }else {
            query.setParameter("description", "%"+description+"%");
        }

        query.setParameter("status", status);
        return query;
    }

    @Override
    public List<SessionEntity> validateDuplicateTime(Timestamp timeStart, Timestamp timeEnd, Integer roomId) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<SessionEntity> sessionEntityList = new ArrayList<>();
        List list;
        try{
            Query query = null;
            StringBuilder sql = new StringBuilder("FROM SessionEntity WHERE str(roomSessionEntity) LIKE :roomId AND status = :status AND (timeStart > :timeEnd  OR timeEnd < :timeStart)");
            query = session.createQuery(sql.toString());
            query.setParameter("roomId", roomId.toString());
            query.setParameter("timeStart", timeStart);
            query.setParameter("timeEnd", timeEnd);
            query.setParameter("status", 0);
            list = query.list();

            for(int i = 0; i < list.size(); i++){
                SessionEntity entity = (SessionEntity) list.get(i);
                sessionEntityList.add(entity);;
            }
            transaction.commit();
        }catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return sessionEntityList;
    }
}
