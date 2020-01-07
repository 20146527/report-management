package ubnd.web.logic.controller.roles;

import com.google.gson.Gson;
import ubnd.common.utils.SessionUtils;
import ubnd.core.dto.*;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.web.common.WebConstants;
import ubnd.core.web.utils.FormUtils;
import ubnd.core.web.utils.SingletonServiceUtil;
import ubnd.web.logic.command.ExtendPermissionUserCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/manager-extend-permission.html", "/ajax-extend-permission.html"})
public class ExtendPermissionUserController extends HttpServlet {
    private static final String WAITING_LIST = "waitingList";
    private static final String LICENSED_LIST = "licensedList";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);
        ExtendPermissionUserCommand command = FormUtils.populate(ExtendPermissionUserCommand.class, request);
        Integer tab = command.getTab();
        if(tab == null){
            tab = 1;
        }
        List<ExtendPermissionUserDto> extendPermissionUserAllList = SingletonServiceUtil.getExtendPermissionUserServiceInstance().findAll();
        extendPermissionUserAllList = filterExtendPermissionInListModule(extendPermissionUserAllList, userDto);
        request.setAttribute("tab", tab);
        request.setAttribute(WAITING_LIST, getWaitingListFromAll(extendPermissionUserAllList));
        request.setAttribute(LICENSED_LIST, getLicensedListFromAll(extendPermissionUserAllList));
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/role/extend-permission-user.jsp");
        dispatcher.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);
        ExtendPermissionUserCommand command = FormUtils.populate(ExtendPermissionUserCommand.class, request);
        if(command.getUrlType() != null){
            if(command.getUrlType().equals("url_accept")){
                SingletonServiceUtil.getExtendPermissionUserServiceInstance().acceptPermission(command.getExPermissionId(), command.getCount(), command.getOperatorId(), userDto);
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write(command.getTab());
            }
            if(command.getUrlType().equals("url_reject")){
                SingletonServiceUtil.getExtendPermissionUserServiceInstance().rejectPermission(command.getExPermissionId());
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write(command.getTab());
            }
            if(command.getUrlType().equals("url_cancel")){
                SingletonServiceUtil.getExtendPermissionUserServiceInstance().cancelExtendPermissionUser(command.getExPermissionId());
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write(command.getTab());
            }
        }
    }

    /**
     * Get waiting list permission form all list extend permission user
     * @param listAll :all list extend permission user
     * @return list extend permission user
     */
    private List<ExtendPermissionUserDto> getWaitingListFromAll(List<ExtendPermissionUserDto> listAll){
        List<ExtendPermissionUserDto> waitingList = new ArrayList<>();
        for(ExtendPermissionUserDto item: listAll){
            if (item.getRequestTag().equals(1)){
                waitingList.add(item);
            }
        }
        return waitingList;
    }

    /**
     * Get licensed list permission form all list extend permission user
     * @param listAll :all list extend permission user
     * @return list extend permission user
     */
    private List<ExtendPermissionUserDto> getLicensedListFromAll(List<ExtendPermissionUserDto> listAll){
        List<UserDto> userList = SingletonServiceUtil.getUserServiceInstance().findAll();
        List<ExtendPermissionUserDto> licensedList = new ArrayList<>();
        for(ExtendPermissionUserDto item: listAll){
            if (!item.getRequestTag().equals(1)){
                item.setCreUserName(getUserNameFromId(userList, item.getCreUID()));
                licensedList.add(item);
            }
        }
        return licensedList;
    }

    /**
     * Get Username from user ID
     * @param userList list all user
     * @param id user ID
     * @return username
     */
    private String getUserNameFromId(List<UserDto> userList, Integer id){
        for(UserDto item: userList){
            if(item.getUserId().equals(id)){
                return item.getUserName();
            }
        }
        return "";
    }

    /**
     * Filter extend permission in the module user
     * @param list list all permission user
     * @param userDto user login
     * @return list filtered
     */
    private List<ExtendPermissionUserDto> filterExtendPermissionInListModule(List<ExtendPermissionUserDto> list, UserDto userDto){
        Map<String, Object> mapProperty = new HashMap<>();
        mapProperty.put("userModuleEntity", userDto.getUserId());
        List<UserModuleDto> userModuleList = SingletonServiceUtil.getUserModuleService().findByProperty(mapProperty, null, null, null, null);
        for(ExtendPermissionUserDto item: list){
            if(!checkObjectInModule(userModuleList, item.getObjectDto().getModuleDto().getModuleID())){
                list.remove(item);
                break;
            }
        }
        return list;
    }

    /**
     * Check if the list object is in the module
     * @param list list user module
     * @param moduleId moduleId from object
     * @return true: in the module
     */
    private Boolean checkObjectInModule(List<UserModuleDto> list, Integer moduleId){
        for(UserModuleDto item: list){
            if(moduleId.equals(item.getModuleDto().getModuleID())){
                return true;
            }
        }
        return false;
    }

}
