package ubnd.core.service.impl;

import ubnd.core.dto.RolePermissionDto;
import ubnd.core.persistence.data.entity.RolePermissionEntity;
import ubnd.core.service.RolePermissionService;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.utlis.RolePermissionBeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class RolePermissionServiceImpl implements RolePermissionService {
    @Override
    public List<RolePermissionDto>findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = SingletonDaoUtil.getRolePermissionDaoInstance().findByProperty(property, sortExpression, sortDirection, offset, limit, null);
        List<RolePermissionDto> dtos = new ArrayList<>();
        for(RolePermissionEntity item: (List<RolePermissionEntity>)objects[1]){
            RolePermissionDto dto = RolePermissionBeanUtils.entityToDto(item);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public void addPermission(RolePermissionDto rolePermissionDto) {
        SingletonDaoUtil.getRolePermissionDaoInstance().save(RolePermissionBeanUtils.dtoToEntity(rolePermissionDto));
    }

    @Override
    public void deletePermission(Integer id) {
        List<Integer> list = new ArrayList<>();
        list.add(id);
        SingletonDaoUtil.getRolePermissionDaoInstance().delete(list);
    }

}
