package ubnd.core.service;

import ubnd.core.dto.ExtendPermissionUserDto;
import ubnd.core.dto.ObjectDto;
import ubnd.core.dto.UserDto;

import java.util.List;
import java.util.Map;

public interface ExtendPermissionUserService {
    List<ExtendPermissionUserDto> findAll();
    List<ExtendPermissionUserDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    void acceptPermission(Integer extendPermissionUserId, Integer count, Integer operatorId, UserDto userDto);
    void rejectPermission(Integer extendPermissionUserId);
    void requestExtendPermissionUser(UserDto userDto, ObjectDto objectDto);
    void cancelExtendPermissionUser(Integer extendPermissionUserId);
}
