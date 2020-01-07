package ubnd.core.service.impl;

import ubnd.core.dao.DictStenoDao;
import ubnd.core.dao.impl.DictStenoDaoImpl;
import ubnd.core.data.obj.DictObject;
import ubnd.core.dto.DictStenoDTO;
import ubnd.core.service.DictStenoService;
import ubnd.core.utlis.DictStenoBeanUtils;

import java.util.List;

public class DictStenoServiceImpl implements DictStenoService {


    /**
     * Update dict default by ID
     *
     * @param dictId ID dict
     */
    public void updateDefaultDist(int dictId) {
        DictStenoDao dao = new DictStenoDaoImpl();
        dao.updateDefaultDict(dictId);
    }

    /**
     * Get list dict in database
     *
     * @return list DictObject
     */
    public List<DictObject> listDict() {
        DictStenoDao dao = new DictStenoDaoImpl();
        return dao.listDict();
    }

    /**
     * get object dict default
     *
     * @return DictObject
     */
    public DictObject getDefaultDict() {
        DictStenoDao dao = new DictStenoDaoImpl();
        return dao.getDefaultDict();
    }

    @Override
    public DictStenoDTO update(DictStenoDTO dto) {
        DictStenoDao dao = new DictStenoDaoImpl();
        return DictStenoBeanUtils.entityToDTO(dao.update(DictStenoBeanUtils.dtoToEntity(dto)));
    }

    @Override
    public void save(DictStenoDTO dto) {
        DictStenoDao dao = new DictStenoDaoImpl();
        dao.save(DictStenoBeanUtils.dtoToEntity(dto));
    }
}
