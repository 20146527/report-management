package ubnd.web.logic.controller.user;

import com.google.gson.Gson;
import ubnd.common.utils.SessionUtils;
import ubnd.core.dto.*;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.utlis.RoleBeanUtils;
import ubnd.core.utlis.StringUtils;
import ubnd.core.web.common.WebConstants;
import ubnd.core.web.utils.FormUtils;
import ubnd.core.web.utils.SingletonServiceUtil;
import ubnd.core.web.utils.WebCommonUtil;
import ubnd.web.logic.command.UserCommand;
import ubnd.web.logic.utils.TimeUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/manager-user-list.html", "/ajax-user-edit.html"})
public class UserController extends HttpServlet {

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        UserCommand command = FormUtils.populate(UserCommand.class, request);
        UserDto pojo = command.getPojo();
        if (command.getUrlType() != null) {
            Map<String, Object> mapProperty = new HashMap<>();
            if (command.getUrlType().equals(WebConstants.URL_LIST)) {
                mapProperty.put("status", 0);
                //set value search
                if (pojo.getFullName() != null) {
                    mapProperty.put("fullName", pojo.getFullName());
                }
                if (pojo.getEmail() != null) {
                    mapProperty.put("email", pojo.getEmail());
                }
                if (pojo.getPhone() != null) {
                    mapProperty.put("phone", pojo.getPhone());
                }
                //set value input search
                if (pojo.getFullName() == null) {
                    pojo.setFullName("");
                }
                if (pojo.getEmail() == null) {
                    pojo.setEmail("");
                }
                if (pojo.getPhone() == null) {
                    pojo.setPhone("");
                }
                command.setPojo(pojo);

                Object[] objects = SingletonServiceUtil.getUserServiceInstance().findByProperty(mapProperty, command.getSortExpression(), command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
                List<UserDto> userDtoList = (List<UserDto>) objects[1];
                request.setAttribute(WebConstants.FORM_ITEM, command);
                request.setAttribute(WebConstants.LIST_ITEMS, userDtoList);
                if (command.getCrudaction() != null) {
                    Map<String, String> mapMessage = buidMapRedirectMessage();
                    WebCommonUtil.addRedirectMessage(request, command.getCrudaction(), mapMessage);
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/user/list-user.jsp");
                dispatcher.forward(request, response);
            }
            if (command.getUrlType().equals(WebConstants.URL_EDIT)) {
                Gson gson = new Gson();
                List<String> usernameList = new ArrayList<>();
                List<UserDto> userDtoList = SingletonServiceUtil.getUserServiceInstance().findAll();
                if (pojo != null && pojo.getUserId() != null) {
                    UserDto userDto = SingletonServiceUtil.getUserServiceInstance().findById(pojo.getUserId());
                    command.setPojo(userDto);
                    for(UserDto item: userDtoList){
                        if(userDto.getUserId().equals(item.getUserId())){
                            userDtoList.remove(item);
                            break;
                        }
                    }
                }
                usernameList = getAllUsername(usernameList, userDtoList);
                command.setRoles(SingletonServiceUtil.getRoleServiceInstance().findAll());
                request.setAttribute(WebConstants.FORM_ITEM, command);
                request.setAttribute("username_exist", ""+gson.toJson(usernameList));
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/user/edit-user.jsp");
                dispatcher.forward(request, response);
            }
            if (command.getUrlType().equals(WebConstants.URL_DELETE)) {
                request.setAttribute(WebConstants.FORM_ITEM, command);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/user/remove-user.jsp");
                dispatcher.forward(request, response);
            }
            if(command.getUrlType().equals("ajax_list_role")){
                Gson gson = new Gson();
//                List<RoleDto> listALlRole = SingletonServiceUtil.getRoleServiceInstance().findAll();
                List<RoleDto> listALlRole = getAllRoleForUser(command.getUserId());
                if(command.getUserId() != null){
                    mapProperty.put("userRoleEntity", command.getUserId());
                }
                List<UserRoleDto> listUserRole = SingletonServiceUtil.getUserRoleServiceInstance().findByProperty(mapProperty, null, null, null, null);
                //remove role added for user
                for(UserRoleDto userRoleDto: listUserRole){
                    for(RoleDto roleDto: listALlRole){
                        if(userRoleDto.getRoleDto().getRoleId().equals(roleDto.getRoleId())){
                            listALlRole.remove(roleDto);
                            break;
                        }
                    }
                }

                Object[] objects = new Object[]{listALlRole, listUserRole};
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write(gson.toJson(objects));
            }
        }
    }

    private Map<String, String> buidMapRedirectMessage() {
        Map<String, String> mapMessage = new HashMap<>();
        mapMessage.put(WebConstants.REDIRECT_INSERT, "Thêm thành công");
        mapMessage.put(WebConstants.REDIRECT_UPDATE, "Chỉnh sửa thành công");
        mapMessage.put(WebConstants.REDIRECT_DELETE, "Xóa thành công");
        mapMessage.put(WebConstants.REDIRECT_ERROR, "Có lỗi xảy ra");
        return mapMessage;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        UserCommand command = FormUtils.populate(UserCommand.class, request);
        UserDto pojo = command.getPojo();

        //get User Session anh TimeUtil
        UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);
        TimeUtils timeUtils = new TimeUtils();
        if (command.getUrlType() != null) {
            if (command.getUrlType().equals(WebConstants.URL_EDIT)) {
                if (command.getCrudaction() != null && command.getCrudaction().equals(WebConstants.INSERT_UPDATE)) {
                    pojo.setAvaPath("fdfdf");
                    if (pojo != null && pojo.getUserId() != null) {
                        if(pojo.getPassWord().equals(command.getPassword())){
                            pojo.setPassWord(pojo.getPassWord());
                        }else {
                            pojo.setPassWord(StringUtils.stringToSHA256(pojo.getPassWord()));
                        }
                        pojo.setStatus(0);
                        pojo.setModUID(userDto.getUserId());
                        pojo.setModDate(timeUtils.getCurrentTimestamp());
                        SingletonServiceUtil.getUserServiceInstance().updateUser(pojo);
                        response.sendRedirect("/manager-user-list.html?urlType=url_list&crudaction=redirect_update");
                    } else {
                        pojo.setPassWord(StringUtils.stringToSHA256(pojo.getPassWord()));
                        pojo.setStatus(0);
                        pojo.setCreUID(userDto.getUserId());
                        pojo.setCreDate(timeUtils.getCurrentTimestamp());
                        pojo.setModUID(userDto.getUserId());
                        pojo.setModDate(timeUtils.getCurrentTimestamp());
                        SingletonServiceUtil.getUserServiceInstance().saveUser(pojo);
                        response.sendRedirect("/manager-user-list.html?urlType=url_list&crudaction=redirect_insert");
                    }
                }
            }
            if (command.getUrlType().equals(WebConstants.URL_DELETE)) {
//                Integer userId = pojo.getUserId();
                Integer userId = Integer.valueOf(request.getParameter("userId"));

                int result = SingletonServiceUtil.getUserServiceInstance().delete(userId);
                request.setAttribute(WebConstants.MESSAGE_RESPONSE, WebConstants.REDIRECT_DELETE);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/user/remove-user.jsp");
                dispatcher.forward(request, response);

            }
            if(command.getUrlType().equals("ajax_add_role")){
                UserDto user = new UserDto();
                RoleDto role = new RoleDto();
                if(command.getUserId() != null){
                    user.setUserId(command.getUserId());
                }
                if(command.getRoleId() != null){
                    role.setRoleId(command.getRoleId());
                }
                UserRoleDto userRoleDto = new UserRoleDto();
                userRoleDto.setUserDto(user);
                userRoleDto.setRoleDto(role);
                SingletonServiceUtil.getUserRoleServiceInstance().addRole(userRoleDto);
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("success");
            }
            if(command.getUrlType().equals("ajax_delete_role")){
                if(command.getRoleId() != null){
                    SingletonServiceUtil.getUserRoleServiceInstance().deleteRole(command.getRoleId());
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write("success");
                }else {
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write("error");
                }
            }
        }
    }

    /**
     * Lay tat ca Role cho User
     * @param userId: ID nguoi dung
     * @return danh sach Role
     */
    private List<RoleDto> getAllRoleForUser(Integer userId){
        List<RoleDto> roleList = new ArrayList<>();
        Map<String, Object> mapProperty = new HashMap<>();
        mapProperty.put("userModuleEntity", userId);
        List<UserModuleDto> userModuleDtoList = SingletonServiceUtil.getUserModuleService().findByProperty(mapProperty, null, null, null, null);
        for(UserModuleDto item: userModuleDtoList){
            roleList = getRoleByModule(roleList, item.getModuleDto().getModuleID());
        }
        return roleList;
    }

    /**
     * Lay tat ca Role theo Module
     * @param roleList
     * @param moduleId
     * @return danh sach Role
     */
    private List<RoleDto> getRoleByModule(List<RoleDto> roleList, Integer moduleId){
        Map<String, Object> mapProperty = new HashMap<>();
        mapProperty.put("moduleRoleEntity", moduleId);
        List<RoleModuleDto> roleModuleDtoList = SingletonServiceUtil.getRoleModuleServiceInstance().findByProperty(mapProperty, null, null, null, null);
        for(RoleModuleDto item: roleModuleDtoList){
            if(!checkExistRole(roleList, item.getRoleDto())){
                roleList.add(item.getRoleDto());
            }
        }
        return roleList;
    }

    /**
     * Kiem tra xem Role da them vao List chua
     * @param roleList
     * @param dto
     * @return
     */
    private Boolean checkExistRole(List<RoleDto> roleList, RoleDto dto){
        for(RoleDto item: roleList){
            if(item.getRoleId().equals(dto.getRoleId())){
                return true;
            }
        }
        return false;
    }

    /**
     * Lay tat ca username
     * @param list
     * @param userDtoList
     * @return
     */
    private List<String> getAllUsername(List<String> list, List<UserDto> userDtoList){
        for(UserDto item: userDtoList){
            list.add(item.getUserName());
        }
        return list;
    }

}
