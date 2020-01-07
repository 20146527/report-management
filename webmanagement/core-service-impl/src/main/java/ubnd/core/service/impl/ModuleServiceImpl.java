package ubnd.core.service.impl;

import ubnd.core.dto.MeetingDto;
import ubnd.core.dto.ModuleDto;
import ubnd.core.persistence.data.entity.ModuleEntity;
import ubnd.core.service.ModuleService;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.utlis.ModuleBeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ModuleServiceImpl implements ModuleService {

    @Override
    public List<ModuleDto> findAll() {
        List<ModuleEntity> list = SingletonDaoUtil.getModuleDaoInstance().findAll();
        List<ModuleDto> moduleDtoList = new ArrayList<>();
        for (ModuleEntity entity : list){
            moduleDtoList.add(ModuleBeanUtils.entityToDTO(entity));
        }
        return moduleDtoList;
    }

    @Override
    public ModuleDto findByID(Integer id) {
        return ModuleBeanUtils.entityToDTO(SingletonDaoUtil.getModuleDaoInstance().findByID(id));
    }
}
