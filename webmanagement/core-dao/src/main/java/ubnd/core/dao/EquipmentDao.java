package ubnd.core.dao;

import ubnd.core.data.dao.GenericDao;
import ubnd.core.persistence.data.entity.EquipmentEntity;

public interface EquipmentDao extends GenericDao<Integer, EquipmentEntity> {
    int enableDisable(Integer equipmentId, Integer status);
}
