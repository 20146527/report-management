package ubnd.core.service.impl;

import ubnd.core.dto.EquipmentDto;
import ubnd.core.persistence.data.entity.EquipmentEntity;
import ubnd.core.service.EquipmentService;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.utlis.EquipmentBeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class EquipmentServiceImpl implements EquipmentService {

    public List<EquipmentDto> findAll() {
        List<EquipmentEntity> entities = SingletonDaoUtil.getEquipmentDaoInstance().findAll();
        List<EquipmentDto> dtos = new ArrayList<>();
        for(EquipmentEntity item: entities){
            EquipmentDto dto = EquipmentBeanUtils.entityToDTO(item);
            dtos.add(dto);
        }
        return dtos;
    }

    public List<EquipmentDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        return null;
    }

    public EquipmentDto findByID(Integer equipmentId) {
        EquipmentEntity entity = SingletonDaoUtil.getEquipmentDaoInstance().findByID(equipmentId);
        return EquipmentBeanUtils.entityToDTO(entity);
    }

    public EquipmentDto update(EquipmentDto dto) {
        EquipmentEntity entity = EquipmentBeanUtils.dtoToEntity(dto);
        entity = SingletonDaoUtil.getEquipmentDaoInstance().update(entity);
        return EquipmentBeanUtils.entityToDTO(entity);
    }

    public void save(EquipmentDto dto) {
        EquipmentEntity entity = EquipmentBeanUtils.dtoToEntity(dto);
        SingletonDaoUtil.getEquipmentDaoInstance().save(entity);
    }

    public int enableDisable(Integer equipmentId, Integer status) {
        return SingletonDaoUtil.getEquipmentDaoInstance().enableDisable(equipmentId, status);
    }


}
