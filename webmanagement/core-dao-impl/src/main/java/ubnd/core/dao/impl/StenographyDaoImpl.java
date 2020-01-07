package ubnd.core.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ubnd.common.utils.HibernateUtils;
import ubnd.core.dao.SessionDao;
import ubnd.core.dao.StenographyDao;
import ubnd.core.data.daoimpl.AbstractDao;
import ubnd.core.persistence.data.entity.SessionEntity;
import ubnd.core.persistence.data.entity.StenographyEntity;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StenographyDaoImpl extends AbstractDao<Integer, StenographyEntity> implements StenographyDao {
    boolean checkAND = false;
    public int delete(Integer id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int result = 0;
        try {
            Query query = session.createQuery("UPDATE StenographyEntity SET status = 1 WHERE stenoId = :stenoId");
            query.setParameter("stenoId", id);
            result = query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public List<StenographyEntity> searchFileSteno(int meetingId, int sessionId, String nameFileSteno, int createUser, Timestamp timeStart, Timestamp timeEnd) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        List list;
        try {
            StringBuilder query = new StringBuilder("FROM StenographyEntity");
            query.append(" ").append("WHERE");
            if (meetingId == 0) {
                setQuery(query, nameFileSteno, createUser, timeStart, timeEnd);
            } else {
                if (sessionId != 0) {
                    query.append(" ").append("sessionStenoEntity.sessionId LIKE :sessionId");
                    checkAND = true;
                } else {
                    Map<String, Object> mapProperty = new HashMap<>();
                    mapProperty.put("meetingEntity", meetingId);
                    SessionDao sessionDao = new SessionDaoImpl();
                    List<SessionEntity> entityList = (List<SessionEntity>) sessionDao.findByProperty(mapProperty, null, null, null, null, null)[1];
                    int i = 0;
                    for (SessionEntity entity : entityList) {
                        if (entity.getStatus() != 1) {
                            if (i == 0) {
                                query.append(" ").append("sessionStenoEntity.sessionId LIKE")
                                        .append(" ").append(entity.getSessionId());
                            } else {
                                query.append(" ")
                                        .append("OR")
                                        .append(" ")
                                        .append("sessionStenoEntity.sessionId LIKE")
                                        .append(" ")
                                        .append(entity.getSessionId());
                            }
                            checkAND = true;
                            i++;
                        }
                    }
                }

                setQuery(query, nameFileSteno, createUser, timeStart, timeEnd);
            }
            if (checkAND) {
                query.append(" ").append("AND");
            }
            query.append(" ")
                    .append("status = :status");
            Query queryHQL = session.createQuery(query.toString());
            setQueryHQL(queryHQL, sessionId, nameFileSteno, createUser, timeStart, timeEnd);
            list = queryHQL.list();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }

        return (List<StenographyEntity>) list;

    }

    private void setQuery(StringBuilder query, String nameFileSteno, int createUser, Timestamp timeStart, Timestamp timeEnd) {
        if (!nameFileSteno.equals("")) {
            if (checkAND) {
                query.append(" ").append("AND");
            }
            query.append(" ")
                    .append("name LIKE :name");
            checkAND = true;
        }
        if (createUser != 0) {
            if (checkAND) {
                query.append(" ").append("AND");
            }
            query.append(" ")
                    .append("creUID LIKE :creUID");
            checkAND = true;
        }

        if (timeStart != null) {
            if (checkAND) {
                query.append(" ").append("AND");
            }
            query.append(" ")
                    .append("creDate >= :timeStart");
            checkAND = true;
        }

        if (timeEnd != null) {
            if (checkAND) {
                query.append(" ").append("AND");
            }
            query.append(" ")
                    .append("creDate <= :timeEnd");
        }
    }

    private void setQueryHQL(Query query, int sessionId, String nameFileSteno, int createUser, Timestamp timeStart, Timestamp timeEnd) {
        if (sessionId != 0)
            query.setParameter("sessionId", sessionId);

        if (!nameFileSteno.equals(""))
            query.setParameter("name", nameFileSteno);

        if (createUser != 0)
            query.setParameter("creUID", createUser);

        if (timeStart != null)
            query.setParameter("timeStart", timeStart);

        if (timeEnd != null)
            query.setParameter("timeEnd", timeEnd);

        query.setParameter("status", 0);

    }

}


