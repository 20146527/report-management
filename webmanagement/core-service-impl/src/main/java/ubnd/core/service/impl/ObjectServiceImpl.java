package ubnd.core.service.impl;

import ubnd.core.dto.ObjectDto;
import ubnd.core.persistence.data.entity.ObjectEntity;
import ubnd.core.service.ObjectService;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.utlis.ObjectBeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ObjectServiceImpl implements ObjectService {
    @Override
    public List<ObjectDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = SingletonDaoUtil.getObjectDaoInstance().findByProperty(property, sortExpression, sortDirection, offset, limit, null);
        List<ObjectDto> dtos = new ArrayList<>();
        for(ObjectEntity item: (List<ObjectEntity>)objects[1]){
            ObjectDto dto = ObjectBeanUtils.entityToDTO(item);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<ObjectDto> findAll() {
        List<ObjectEntity> entityList = SingletonDaoUtil.getObjectDaoInstance().findAll();
        List<ObjectDto> list = new ArrayList<>();
        for (ObjectEntity entity : entityList){
            if (entity.getLocked()!=1){
                list.add(ObjectBeanUtils.entityToDTO(entity));
            }
        }
        return list;
    }

    @Override
    public ObjectDto findByID(Integer id) {
        return ObjectBeanUtils.entityToDTO(SingletonDaoUtil.getObjectDaoInstance().findByID(id));
    }

    @Override
    public void saveObject(ObjectDto dto) {
        ObjectEntity entity = ObjectBeanUtils.dtoToEntity(dto);
        SingletonDaoUtil.getObjectDaoInstance().save(entity);
    }

    @Override
    public void deleteObject(Integer id) {

    }
}
