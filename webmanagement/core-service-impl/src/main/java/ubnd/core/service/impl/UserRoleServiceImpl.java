package ubnd.core.service.impl;


import ubnd.core.dto.UserDto;
import ubnd.core.dto.UserRoleDto;
import ubnd.core.persistence.data.entity.UserRoleEntity;
import ubnd.core.service.UserRoleService;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.utlis.UserRoleBeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class UserRoleServiceImpl implements UserRoleService {

    @Override
    public List<UserRoleDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = SingletonDaoUtil.getUserRoleDaoInstance().findByProperty(property, sortExpression, sortDirection, offset, limit, null);
        List<UserRoleDto> dtos = new ArrayList<>();
        for(UserRoleEntity item: (List<UserRoleEntity>)objects[1]){
            UserRoleDto dto = UserRoleBeanUtils.entityToDto(item);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public void addRole(UserRoleDto userRoleDto) {
        SingletonDaoUtil.getUserRoleDaoInstance().save(UserRoleBeanUtils.dtoToEntity(userRoleDto));
    }

    @Override
    public void deleteRole(Integer id) {
        List<Integer> list = new ArrayList<>();
        list.add(id);
        SingletonDaoUtil.getUserRoleDaoInstance().delete(list);
    }
}
