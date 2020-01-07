package ubnd.core.service.impl;

import ubnd.core.dto.StenographyDto;
import ubnd.core.persistence.data.entity.StenographyEntity;
import ubnd.core.service.StenographyService;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.utlis.StenographyBeanUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StenographyServiceImpl implements StenographyService {
    @Override
    public List<StenographyDto> findAll() {
        List<StenographyEntity> entities = SingletonDaoUtil.getStenographyDaoImpl().findAll();
        List<StenographyDto> dtoList = new ArrayList<>();
        for (StenographyEntity item : entities) {
            StenographyDto dto = StenographyBeanUtils.entityToDTO(item);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public List<StenographyDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = SingletonDaoUtil.getStenographyDaoImpl().findByProperty(property, sortExpression, sortDirection, offset, limit, null);
        List<StenographyDto> dtoList = new ArrayList<>();
        for (StenographyEntity item : (List<StenographyEntity>) objects[1]) {
            StenographyDto dto = StenographyBeanUtils.entityToDTO(item);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public int deleteStenography(Integer id) {
        return SingletonDaoUtil.getStenographyDaoImpl().delete(id);
    }

    @Override
    public void save(StenographyDto dto) {
        SingletonDaoUtil.getStenographyDaoImpl().save(StenographyBeanUtils.dtoToEntity(dto));
    }

    @Override
    public List<StenographyDto> searchFileSteno(int meetingId, int sessionId, String nameFileSteno, int createUser, Timestamp timeStart, Timestamp timeEnd) {
        List<StenographyDto> dtoList = new ArrayList<>();
        List<StenographyEntity> list = SingletonDaoUtil.getStenographyDaoImpl().searchFileSteno(meetingId, sessionId, nameFileSteno, createUser, timeStart, timeEnd);
        for (StenographyEntity item : list) {
            StenographyDto dto = StenographyBeanUtils.entityToDTO(item);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
