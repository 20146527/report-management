package ubnd.core.service;

import ubnd.core.dto.UserDto;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<UserDto> findAll();
    UserDto isUserExist(UserDto dto);
    UserDto updateUserInfo(UserDto dto);
    void updateUser(UserDto dto);
    void saveUser(UserDto userDto);
    UserDto findById(Integer id);
    int delete(Integer userId);
    Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);

}
