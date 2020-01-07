package ubnd.core.service.impl;

import ubnd.core.dto.RoleModuleDto;
import ubnd.core.dto.RolePermissionDto;
import ubnd.core.persistence.data.entity.RoleModuleEntity;
import ubnd.core.persistence.data.entity.RolePermissionEntity;
import ubnd.core.service.RoleModuleService;
import ubnd.core.service.RolePermissionService;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.utlis.RoleModuleBeanUtils;
import ubnd.core.utlis.RolePermissionBeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class RoleModuleServiceImpl implements RoleModuleService {

    @Override
    public List<RoleModuleDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = SingletonDaoUtil.getRoleModuleDaoInstance().findByProperty(property, sortExpression, sortDirection, offset, limit, null);
        List<RoleModuleDto> dtos = new ArrayList<>();
        for(RoleModuleEntity item: (List<RoleModuleEntity>)objects[1]){
            RoleModuleDto dto = RoleModuleBeanUtils.entityToDto(item);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public void addRole(RoleModuleDto roleModuleDto) {
        SingletonDaoUtil.getRoleModuleDaoInstance().save(RoleModuleBeanUtils.dtoToEntity(roleModuleDto));
    }

    @Override
    public void deleteRole(Integer id) {
        List<Integer> list = new ArrayList<>();
        list.add(id);
        SingletonDaoUtil.getRoleModuleDaoInstance().delete(list);
    }
}
