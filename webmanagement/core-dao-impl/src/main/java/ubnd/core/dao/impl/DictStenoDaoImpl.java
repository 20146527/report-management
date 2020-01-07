package ubnd.core.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ubnd.common.utils.HibernateUtils;
import ubnd.core.dao.DictStenoDao;
import ubnd.core.data.daoimpl.AbstractDao;
import ubnd.core.data.obj.DictObject;
import ubnd.core.dto.DictStenoDTO;
import ubnd.core.persistence.data.entity.DictStenoEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class DictStenoDaoImpl extends AbstractDao<Integer, DictStenoEntity> implements DictStenoDao {

    /**
     * Change file dict default by ID dict
     *
     * @param dictId ID dict
     */
    public void updateDefaultDict(int dictId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            StringBuilder builder = new StringBuilder("UPDATE DictStenoEntity set dictDefault= :dictDefault");
            if (dictId != -1) {
                builder.append("WHERE dictId= :dictId");
            }
            Query query = session.createQuery(builder.toString());
            if (dictId == -1)
                query.setParameter("dictDefault", 1);
            else
                query.setParameter("dictDefault", 0);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    /**
     * Get list dict in database
     *
     * @return list dict
     */
    public List<DictObject> listDict() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<DictObject> list = new ArrayList<>();
        List l;
        try {
            Query query = session.createQuery("SELECT d.dictId, d.dictName, d.length, d.content,d.dictDefault, d.status, d.creUID, d.creDate, d.modUID, d.modDate, u.userName FROM DictStenoEntity d, UserEntity u WHERE d.modUID = u.userId");
            l = query.list();

            for (Object o : l) {
                Object[] objects = (Object[]) o;
                DictStenoDTO dto = new DictStenoDTO();

                int id = Integer.parseInt(objects[0].toString());
                String dictName = objects[1].toString();
                int length = Integer.parseInt(objects[2].toString());
                String content = objects[3].toString();
                int dictDefault = Integer.parseInt(objects[4].toString());
                int status = Integer.parseInt(objects[5].toString());
                int creUID = Integer.parseInt(objects[6].toString());
                String creDate = objects[7].toString();
                int modUID = Integer.parseInt(objects[8].toString());
                String modDate = objects[9].toString();

                dto.setDictId(id);
                dto.setDictName(dictName);
                dto.setLength(length);
                dto.setContent(content);
                dto.setDictDefault(dictDefault);
                dto.setStatus(status);
                dto.setCreUID(creUID);
                dto.setCreDate(Timestamp.valueOf(creDate));
                dto.setModUID(modUID);
                dto.setModDate(Timestamp.valueOf(modDate));

                String modUserName = objects[10].toString();

                list.add(new DictObject(dto, modUserName));
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }


        return list;
    }

    /**
     * Get Object Dict default
     *
     * @return DictObject default
     */
    public DictObject getDefaultDict() {
        List<DictObject> list = listDict();
        DictObject object = null;
        for (DictObject obj : list) {
            if (obj.getDto().getDictDefault() == 1) {
                object = obj;
                break;
            }
        }
        return object;
    }


}
