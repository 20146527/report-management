package ubnd.core.dao;

import ubnd.core.data.dao.GenericDao;
import ubnd.core.data.obj.DictObject;
import ubnd.core.persistence.data.entity.DictStenoEntity;

import java.util.List;

public interface DictStenoDao extends GenericDao<Integer, DictStenoEntity> {
    void updateDefaultDict(int dictId);
    List<DictObject> listDict();
    DictObject getDefaultDict();
}
