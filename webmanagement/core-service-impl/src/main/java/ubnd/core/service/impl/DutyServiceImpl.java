package ubnd.core.service.impl;

import ubnd.core.dto.DutyDto;
import ubnd.core.persistence.data.entity.DutyEntity;
import ubnd.core.service.DutyService;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.utlis.DutyBeanUtils;

import java.util.ArrayList;
import java.util.List;


public class DutyServiceImpl implements DutyService {
    public List<DutyDto> findAll(){
        List<DutyEntity> enities = SingletonDaoUtil.getDutyDaoInstance().findAll();
        List<DutyDto> dtos = new ArrayList<DutyDto>();
        for (DutyEntity item: enities){
            DutyDto dto = DutyBeanUtils.entityToDTO(item);
            dtos.add(dto);
        }
        return dtos;
    }
}
