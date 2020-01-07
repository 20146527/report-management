package ubnd.core.dao;

import ubnd.core.data.dao.GenericDao;
import ubnd.core.persistence.data.entity.SessionEntity;

import java.sql.Timestamp;
import java.util.List;


public interface SessionDao extends GenericDao<Integer, SessionEntity> {
    List<SessionEntity> validateDuplicateTime(Timestamp timeStart, Timestamp timeEnd, Integer roomId);
}
