package ubnd.core.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ubnd.common.utils.HibernateUtils;
import ubnd.core.dao.AttendeesDao;
import ubnd.core.data.daoimpl.AbstractDao;
import ubnd.core.persistence.data.entity.AttendeesEntity;

import java.util.ArrayList;
import java.util.List;


public class AttendeesDaoImpl extends AbstractDao<Integer, AttendeesEntity> implements AttendeesDao {
    public List<AttendeesEntity> findPreside(Integer presideId){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<AttendeesEntity> attendeesEntityList = new ArrayList<>();
        List list;
        try {
            Query query = session.createQuery("FROM AttendeesEntity WHERE str(dutyEntity) LIKE :dutyId GROUP BY speakerAttendeesEntity");
            query.setParameter("dutyId", presideId.toString());
            list = query.list();

            for (Object o : list) {
                AttendeesEntity entity = (AttendeesEntity) o;
                attendeesEntityList.add(entity);
            }
            transaction.commit();
        }catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return attendeesEntityList;
    }
}
