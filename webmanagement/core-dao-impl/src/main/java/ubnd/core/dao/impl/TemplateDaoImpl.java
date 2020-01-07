package ubnd.core.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ubnd.common.utils.HibernateUtils;
import ubnd.core.dao.TemplateDao;
import ubnd.core.data.daoimpl.AbstractDao;
import ubnd.core.persistence.data.entity.TemplateEntity;


public class TemplateDaoImpl extends AbstractDao<Integer, TemplateEntity> implements TemplateDao {

    public int delete(Integer id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int result = 0;
        try {
            Query query = session.createQuery("UPDATE TemplateEntity SET status = 1 WHERE templateId = :templateId");
            query.setParameter("templateId", id);
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
