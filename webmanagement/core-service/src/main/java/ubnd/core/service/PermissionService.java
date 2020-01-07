package ubnd.core.service;

import ubnd.core.dto.OperatorDto;
import ubnd.core.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface PermissionService {
    void getAllUserPermission(HttpServletRequest request, UserDto userDto);
    OperatorDto checkPermission(HttpServletRequest request, UserDto userDto, Integer type, Integer linkId);
}
