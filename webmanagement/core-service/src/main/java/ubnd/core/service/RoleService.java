package ubnd.core.service;

import ubnd.core.dto.RoleDto;

import java.util.List;

public interface RoleService {
    List<RoleDto> findAll();

    RoleDto findById(Integer id);

    int deleteRole(Integer id);

    void save(RoleDto dto);

}
