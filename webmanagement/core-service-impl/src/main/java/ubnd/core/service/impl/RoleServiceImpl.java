package ubnd.core.service.impl;

import ubnd.core.dto.RoleDto;
import ubnd.core.persistence.data.entity.RoleEntity;
import ubnd.core.service.RoleService;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.utlis.RoleBeanUtils;

import java.util.ArrayList;
import java.util.List;


public class RoleServiceImpl implements RoleService {
    public List<RoleDto> findAll(){
        List<RoleEntity> enities = SingletonDaoUtil.getRoleDaoImpl().findAll();
        List<RoleDto> dtos = new ArrayList<>();
        for (RoleEntity item: enities){
            RoleDto dto = RoleBeanUtils.entityToDTO(item);
            dtos.add(dto);
        }
        return dtos;
    }

    public RoleDto findById(Integer id) {
        RoleEntity entity = SingletonDaoUtil.getRoleDaoImpl().findByID(id);
        return RoleBeanUtils.entityToDTO(entity);
    }

    public int deleteRole(Integer id) {
        List<Integer> listId = new ArrayList<>();
        listId.add(id);
        return SingletonDaoUtil.getRoleDaoImpl().delete(listId);
    }

    public void save(RoleDto dto) {
        RoleEntity entity = RoleBeanUtils.dtoToEntity(dto);
        SingletonDaoUtil.getRoleDaoImpl().save(entity);
    }
}
