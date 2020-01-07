package ubnd.core.service.impl;

import ubnd.common.utils.TimeUtils;
import ubnd.core.dto.*;
import ubnd.core.persistence.data.entity.ExtendPermissionUserEntity;
import ubnd.core.persistence.data.entity.NotificationEntity;
import ubnd.core.service.ExtendPermissionUserService;
import ubnd.core.service.NotificationService;
import ubnd.core.service.RolePermissionService;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.utlis.ExtendPermissionUserBeanUtils;
import ubnd.core.utlis.NotificationBeanUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NotificationServiceImpl implements NotificationService {

    @Override
    public List<NotificationDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = SingletonDaoUtil.getNotificationDaoInstance().findByProperty(property, sortExpression, sortDirection, offset, limit, null);
        List<NotificationDto> dtos = new ArrayList<>();
        for (NotificationEntity item : (List<NotificationEntity>) objects[1]) {
            NotificationDto dto = NotificationBeanUtils.entityToDTO(item);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public void createNotify(NotificationDto dto) {
        NotificationEntity entity = NotificationBeanUtils.dtoToEntity(dto);
        SingletonDaoUtil.getNotificationDaoInstance().save(entity);
    }

    @Override
    public void deleteNotify(List<Integer> list) {
        SingletonDaoUtil.getNotificationDaoInstance().delete(list);
    }

    @Override
    public void createRequestPermissionNotify(UserDto userDto, ObjectDto objectDto) {
        Map<String, Object> mapProperty = new HashMap<>();
        mapProperty.put("objectPermissionEntity", 29);
        RolePermissionServiceImpl rolePermissionService = new RolePermissionServiceImpl();
        List<RolePermissionDto> rolePermissionDtos = rolePermissionService.findByProperty(mapProperty, null, null, null, null);
        rolePermissionDtos = filterRolePermissionInModule(rolePermissionDtos, objectDto.getModuleDto().getModuleID());
        //notification recipient list
        List<Integer> listUserId = new ArrayList<>();
        for (RolePermissionDto item : rolePermissionDtos) {
            listUserId = getListUserIDBy(listUserId, item.getRoleDto().getRoleId());
        }
        //create list notification
        for (Integer userId : listUserId) {
            NotificationDto dto = new NotificationDto();
            dto.setUserId(userId);
            dto.setTitle("Yêu cầu cấp quyền");
            dto.setContent(userDto.getFullName() + " yêu cầu truy cập: " + objectDto.getNameObject() + " - " + objectDto.getDescription());
            dto.setLink("/manager-extend-permission.html");
            dto.setType(51);
            dto.setLinkId(objectDto.getLinkId());
            dto.setCreDate(TimeUtils.getCurrentTimeStamp());
            createNotify(dto);
        }
    }

    /**
     * Get list user id from roleid
     * @param listUserId
     * @param roleId
     * @return
     */
    private List<Integer> getListUserIDBy(List<Integer> listUserId, Integer roleId) {
        Map<String, Object> mapProperty = new HashMap<>();
        mapProperty.put("roleUserEntity", roleId);
        UserRoleServiceImpl userRoleService = new UserRoleServiceImpl();
        List<UserRoleDto> userRoleDtos = userRoleService.findByProperty(mapProperty, null, null, null, null);
        for (UserRoleDto item : userRoleDtos) {
            listUserId.add(item.getUserDto().getUserId());
        }
        return listUserId;
    }

    /**
     * Filter Filter out role with the same module as the object
     * @param rolePermissionList
     * @param moduleId
     * @return filtered list
     */
    private List<RolePermissionDto> filterRolePermissionInModule(List<RolePermissionDto> rolePermissionList, Integer moduleId){
        Map<String, Object> mapProperty = new HashMap<>();
        mapProperty.put("moduleRoleEntity", moduleId);
        RoleModuleServiceImpl roleModuleService = new RoleModuleServiceImpl();
        List<RoleModuleDto> roleModuleDtoList = roleModuleService.findByProperty(mapProperty, null, null, null, null);
        for(RolePermissionDto item: rolePermissionList){
            if(!checkRolePermissionSampleModule(roleModuleDtoList, item.getRoleDto().getRoleId())){
                rolePermissionList.remove(item);
                break;
            }
        }
        return  rolePermissionList;
    }

    /**
     * Check the role in the module
     * @param roleModuleList
     * @param roleId
     * @return true - role in module
     */
    private Boolean checkRolePermissionSampleModule(List<RoleModuleDto> roleModuleList, Integer roleId){
        for(RoleModuleDto item: roleModuleList){
            if(roleId.equals(item.getRoleDto().getRoleId())){
                return true;
            }
        }
        return false;
    }
}
