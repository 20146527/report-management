package ubnd.core.service.impl;


import ubnd.common.constant.CoreConstant;
import ubnd.common.utils.SessionUtils;
import ubnd.core.dao.impl.ExtendPermissionUserDaoImpl;
import ubnd.core.dto.*;
import ubnd.core.service.PermissionService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionServiceImpl implements PermissionService {
    private static RolePermissionServiceImpl rolePermissionService = null;
    private static UserRoleServiceImpl userRoleService = null;
    private static ExtendPermissionUserServiceImpl extendPermissionUserService = null;

    private RolePermissionServiceImpl getRolePermissionServiceInstance(){
        if(rolePermissionService == null){
            rolePermissionService = new RolePermissionServiceImpl();
        }
        return rolePermissionService;
    }

    private UserRoleServiceImpl getUserRoleService(){
        if(userRoleService == null){
            userRoleService = new UserRoleServiceImpl();
        }
        return userRoleService;
    }

    private ExtendPermissionUserServiceImpl getExtendPermissionUserService(){
        if(extendPermissionUserService == null){
            extendPermissionUserService = new ExtendPermissionUserServiceImpl();
        }
        return extendPermissionUserService;
    }

    private List<UserRoleDto> getUserRoleDtos(Integer userId){
        Map<String, Object> property = new HashMap<>();
        property.put("userRoleEntity", userId);
        UserRoleServiceImpl userRoleService = new UserRoleServiceImpl();
        List<UserRoleDto> userRoleDtos = userRoleService.findByProperty(property, null, null, null, null);

        return userRoleDtos;
    }


    @Override
    public void getAllUserPermission(HttpServletRequest request, UserDto userDto) {
        //get role permission
        Map<String, Object> property = new HashMap<>();
//        property.put("userRoleEntity", userDto.getUserId());
//        UserRoleServiceImpl userRoleService = new UserRoleServiceImpl();
//        List<UserRoleDto> userRoleDtos = userRoleService.findByProperty(property, null, null, null, null);
        List<RolePermissionDto> rolePermissionList = new ArrayList<>();
        for(UserRoleDto item: getUserRoleDtos(userDto.getUserId())){
            rolePermissionList = findRolePermissionByRole(rolePermissionList, item.getRoleDto().getRoleId());
        }
        SessionUtils.getInstance().putValue(request, CoreConstant.ROLE_PERMISSION_LIST, rolePermissionList);
        //get extend permission
        property.clear();
        property.put("userExtendPermissionEntity", userDto.getUserId());
        property.put("requestTag", 0);
        List<ExtendPermissionUserDto> extendPermissionUserList = getExtendPermissionUserService().findByProperty(property, null, null, null, null);
        SessionUtils.getInstance().putValue(request, CoreConstant.EXTEND_PERMISSION_LIST, extendPermissionUserList);

        List<String> urlList = getUrlAllowedList(request);
        SessionUtils.getInstance().putValue(request, CoreConstant.URL_ALLOWED_LIST, urlList);
    }

    @Override
    public OperatorDto checkPermission(HttpServletRequest request, UserDto userDto, Integer type, Integer linkId) {
        OperatorDto operatorDto = null;
        //check file permission in role permission
        for(UserRoleDto item: getUserRoleDtos(userDto.getUserId())){
            operatorDto = findOperatorInRolePermissionList(request, item.getRoleDto().getRoleId(), type, linkId);
            if(operatorDto != null){
                return operatorDto;
            }
        }
        //check file permission in extend permission
        operatorDto = findOperatorInExtendPermissionList(request, userDto.getUserId(), type, linkId);
        if(operatorDto != null){
            return operatorDto;
        }
        return null;
    }


    private List<RolePermissionDto> findRolePermissionByRole(List<RolePermissionDto> rolePermissionList, Integer roleId){
        Map<String, Object> property = new HashMap<>();
        property.put("rolePermissionEntity", roleId);
//        Object[] objects = getRolePermissionServiceInstance().findByProperty(property, null, null, null, null);
        rolePermissionList.addAll(getRolePermissionServiceInstance().findByProperty(property, null, null, null, null));
        return rolePermissionList;
    }

    /**
     * Get all url allowed in role_permission and extend_permission_user
     * @return
     */
    private List<String> getUrlAllowedList(HttpServletRequest request){
        List<String> urlList = new ArrayList<>();
        //get url from role permission
        List<RolePermissionDto> rolePermissionList = (List<RolePermissionDto>) SessionUtils.getInstance().getValue(request, CoreConstant.ROLE_PERMISSION_LIST);
        for(RolePermissionDto item: rolePermissionList){
            urlList = getUrlFromDesc(urlList, item.getObjectDto().getDescription());
        }
        //get url from extend permission
        List<ExtendPermissionUserDto> extendPermissionUserList = (List<ExtendPermissionUserDto>) SessionUtils.getInstance().getValue(request, CoreConstant.EXTEND_PERMISSION_LIST);
        for(ExtendPermissionUserDto item: extendPermissionUserList){
            urlList = getUrlFromDesc(urlList, item.getObjectDto().getDescription());
        }
        return urlList;
    }

    /**
     * get urls from split description in object
     * @param urlList
     * @param description
     * @return
     */
    private List<String> getUrlFromDesc(List<String> urlList, String description){
        String[] urls = description.split(",");
        for(String url: urls){
            if(!url.equals("")){
                if(!checkExistUrlAllowed(urlList, url.trim())){
                    urlList.add(url.trim());
                }
            }
        }
        return urlList;
    }

    /**
     * Check exit url allowed in url list
     * @param urlList
     * @param url
     * @return true is exist
     */
    private Boolean checkExistUrlAllowed(List<String> urlList, String url){
        for(String item: urlList){
            if(item.equals(url)){
                return true;
            }
        }
        return false;
    }

    private OperatorDto findOperatorInRolePermissionList(HttpServletRequest request, Integer roleId, Integer type, Integer linkId){
        OperatorDto operatorDto = null;
        List<RolePermissionDto> rolePermissionList = (List<RolePermissionDto>) SessionUtils.getInstance().getValue(request, CoreConstant.ROLE_PERMISSION_LIST);
        for(RolePermissionDto item: rolePermissionList){
            if(item.getRoleDto().getRoleId().equals(roleId)){
                if(item.getObjectDto().getTypeObject().equals(type) && item.getObjectDto().getLinkId().equals(linkId)){
                    return item.getOperatorDto();
                }
            }
        }
        return operatorDto;
    }

    private OperatorDto findOperatorInExtendPermissionList(HttpServletRequest request, Integer userId, Integer type, Integer linkId){
        OperatorDto operatorDto = null;
        List<ExtendPermissionUserDto> extendPermissionUserList = (List<ExtendPermissionUserDto>) SessionUtils.getInstance().getValue(request, CoreConstant.EXTEND_PERMISSION_LIST);
        for(ExtendPermissionUserDto item: extendPermissionUserList){
            if(item.getUserDto().getUserId().equals(userId)){
                if (item.getObjectDto().getTypeObject().equals(type) && item.getObjectDto().getLinkId().equals(linkId)) {
                    return item.getOperatorDto();
                }
            }
        }
        return operatorDto;
    }
}
