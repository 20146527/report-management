package ubnd.core.dao;

import ubnd.core.data.dao.GenericDao;
import ubnd.core.persistence.data.entity.MeetingEntity;

import java.sql.Timestamp;
import java.util.List;


public interface MeetingDao extends GenericDao<Integer, MeetingEntity> {
    List<MeetingEntity> searchByHQL(String name, Timestamp timeStart, Timestamp timeEnd, Integer status);
    int delete(Integer id);
}
