package ubnd.core.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ubnd.common.utils.HibernateUtils;
import ubnd.core.dao.ReportDao;
import ubnd.core.data.daoimpl.AbstractDao;
import ubnd.core.persistence.data.entity.ReportEntity;
import ubnd.core.persistence.data.entity.SessionEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class ReportDaoImpl extends AbstractDao<Integer, ReportEntity> implements ReportDao {
    public List<ReportEntity> searchByHQL(Integer sessionId, String name, Timestamp timeStart, Timestamp timeEnd, Integer status){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<ReportEntity> reportEntityList = new ArrayList<>();
        List list;
        try{
            Query query = null;
            if(timeStart == null && timeEnd == null){
                StringBuilder sql = new StringBuilder("FROM ReportEntity WHERE str(sessionReportEntity) LIKE :sessionId AND name LIKE :name AND status = :status");
                query = setParamQuery(query, sql, session, sessionId, name, status);
            }else if(timeStart != null && timeEnd == null){
                StringBuilder sql = new StringBuilder("FROM ReportEntity WHERE str(sessionReportEntity) LIKE :sessionId AND name LIKE :name AND creDate >= :timeStart AND status = :status");
                query = setParamQuery(query, sql, session, sessionId, name, status);
                query.setParameter("timeStart", timeStart);
            }else if(timeStart == null && timeEnd != null){
                StringBuilder sql = new StringBuilder("FROM ReportEntity WHERE str(sessionReportEntity) LIKE :sessionId AND name LIKE :name AND creDate <= :timeEnd AND status = :status");
                query = setParamQuery(query, sql, session, sessionId, name, status);
                query.setParameter("timeEnd", timeEnd);
            }else {
                StringBuilder sql = new StringBuilder("FROM ReportEntity WHERE str(sessionReportEntity) LIKE :sessionId AND name LIKE :name AND creDate >= :timeStart AND creDate <= :timeEnd AND status = :status");
                query = setParamQuery(query, sql, session, sessionId, name, status);
                query.setParameter("timeStart", timeStart);
                query.setParameter("timeEnd", timeEnd);
            }
            list = query.list();

            for(int i = 0; i < list.size(); i++){
                ReportEntity entity = (ReportEntity) list.get(i);
                reportEntityList.add(entity);;
            }
            transaction.commit();
        }catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return reportEntityList;
    }

    private Query setParamQuery (Query query, StringBuilder sql, Session session, Integer sessionId, String name, Integer status){
        query = session.createQuery(sql.toString());
        if(sessionId == null || sessionId == 0){
            query.setParameter("sessionId", "%%");
        }else {
            query.setParameter("sessionId", "%"+sessionId+"%");
        }

        if(name == null){
            query.setParameter("name", "%%");
        }else {
            query.setParameter("name", "%"+name+"%");
        }

        query.setParameter("status", status);
        return query;
    }
}
