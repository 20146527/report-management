package ubnd.core.service.impl;

import ubnd.core.dto.ModuleDto;
import ubnd.core.dto.UserModuleDto;
import ubnd.core.persistence.data.entity.UserEntity;
import ubnd.core.persistence.data.entity.UserModuleEntity;
import ubnd.core.service.UserModuleService;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.utlis.ModuleBeanUtils;
import ubnd.core.utlis.UserModuleBeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserModuleServiceImpl implements UserModuleService {
    @Override
    public List<UserModuleDto> findAll() {
        List<UserModuleEntity> list = SingletonDaoUtil.getUserModuleDaoInstance().findAll();
        List<UserModuleDto> moduleDtoList = new ArrayList<>();
        for (UserModuleEntity entity : list) {
            moduleDtoList.add(UserModuleBeanUtils.entityToDTO(entity));
        }
        return moduleDtoList;
    }

    @Override
    public List<UserModuleDto> findByModuleID(Integer id) {
        List<UserModuleEntity> list = SingletonDaoUtil.getUserModuleDaoInstance().findByModuleID(id);
        List<UserModuleDto> userModuleDtoList = new ArrayList<>();
        for (UserModuleEntity entity : list) {
            userModuleDtoList.add(UserModuleBeanUtils.entityToDTO(entity));
        }
        return userModuleDtoList;
    }


    @Override
    public void save(UserModuleDto dto) {
        SingletonDaoUtil.getUserModuleDaoInstance().save(UserModuleBeanUtils.dtoToEntity(dto));
    }

    @Override
    public void delete(Integer id) {
        List<Integer> list = new ArrayList<>();
        list.add(id);
        SingletonDaoUtil.getUserModuleDaoInstance().delete(list);
    }

    @Override
    public List<UserModuleDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = SingletonDaoUtil.getUserModuleDaoInstance().findByProperty(property, sortExpression, sortDirection, offset, limit, null);
        List<UserModuleDto> dtos = new ArrayList<>();
        for(UserModuleEntity item: (List<UserModuleEntity>)objects[1]){
            UserModuleDto dto = UserModuleBeanUtils.entityToDTO(item);
            dtos.add(dto);
        }
        return dtos;
    }
}
