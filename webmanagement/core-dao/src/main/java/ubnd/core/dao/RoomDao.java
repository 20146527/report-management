package ubnd.core.dao;

import ubnd.core.data.dao.GenericDao;
import ubnd.core.persistence.data.entity.RoomEntity;

public interface RoomDao extends GenericDao<Integer, RoomEntity> {
    int edRoom(Integer roomId, Integer status);
}
