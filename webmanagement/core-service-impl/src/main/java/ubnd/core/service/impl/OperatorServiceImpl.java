package ubnd.core.service.impl;

import ubnd.core.dto.OperatorDto;
import ubnd.core.persistence.data.entity.OperatorEntity;
import ubnd.core.service.OperatorService;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.utlis.OperatorBeanUtils;

import java.util.ArrayList;
import java.util.List;

public class OperatorServiceImpl implements OperatorService {
    @Override
    public List<OperatorDto> findAll() {
        List<OperatorDto> list = new ArrayList<>();
        List<OperatorEntity> entityList = SingletonDaoUtil.getOperatorDaoInstance().findAll();
        for (OperatorEntity entity: entityList){
            if (entity.getLocked() !=1){
                list.add(OperatorBeanUtils.entityToDto(entity));
            }
        }
        return list;
    }

    @Override
    public OperatorDto findByID(Integer id) {
        return OperatorBeanUtils.entityToDto(SingletonDaoUtil.getOperatorDaoInstance().findByID(id));
    }
}
