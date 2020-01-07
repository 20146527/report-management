package ubnd.core.service;


import ubnd.core.dto.NotificationDto;
import ubnd.core.dto.ObjectDto;
import ubnd.core.dto.UserDto;

import java.util.List;
import java.util.Map;

public interface NotificationService {
    List<NotificationDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    void deleteNotify(List<Integer> list);
    void createNotify(NotificationDto dto);
    void createRequestPermissionNotify(UserDto userDto, ObjectDto objectDto);
}
