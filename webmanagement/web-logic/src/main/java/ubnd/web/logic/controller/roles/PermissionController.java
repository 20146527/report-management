package ubnd.web.logic.controller.roles;

import com.google.gson.Gson;
import org.apache.xmlbeans.impl.xb.xsdschema.OpenAttrs;
import ubnd.core.dto.*;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.utlis.ObjectBeanUtils;
import ubnd.core.web.common.WebConstants;
import ubnd.core.web.utils.FormUtils;
import ubnd.core.web.utils.SingletonServiceUtil;
import ubnd.web.logic.command.RoleCommand;
import ubnd.web.logic.command.TranscriptCommand;

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

@WebServlet(urlPatterns = {"/manager-permission.html", "/ajax-manager-permission.html"})
public class PermissionController extends HttpServlet {
    private final static String LIST_ROLE = "listRole";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RoleCommand command = FormUtils.populate(RoleCommand.class, request);
        Gson gson = new Gson();
        Map<String, Object> mapProperty = new HashMap<>();
        Integer roleId;
        if (command.getTab() != null) {
            roleId = command.getTab();
        } else {
            roleId = 1;
        }
        if (command.getUrlType() != null) {
            if (command.getUrlType().equals(WebConstants.URL_LIST)) {
                List<ObjectDto> objectList = getAllObjectForRole(roleId);
                mapProperty.put("rolePermissionEntity", roleId);
                List<RolePermissionDto> rolePermissionList = SingletonServiceUtil.getRolePermissionServiceInstance().findByProperty(mapProperty, null, null, null, null);
                //remove permission added role permission
                for (RolePermissionDto rolePermissionDto : rolePermissionList) {
                    for (ObjectDto objectDto : objectList) {
                        if (rolePermissionDto.getObjectDto().getObjectID().equals(objectDto.getObjectID())) {
                            objectList.remove(objectDto);
                            break;
                        }
                    }
                }
                //add budge for list
                rolePermissionList = initBadgeOperatorRolePermission(rolePermissionList);
                Object[] objects = new Object[]{objectList, rolePermissionList};
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write(gson.toJson(objects));
            }
        } else {
            List<RoleDto> listAllRole = SingletonServiceUtil.getRoleServiceInstance().findAll();
            request.setAttribute(LIST_ROLE, listAllRole);
            request.setAttribute("roleId", roleId);
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/role/manager-role-permission.jsp");
            dispatcher.forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoleCommand command = FormUtils.populate(RoleCommand.class, request);
        Integer roleId;
        if (command.getTab() != null) {
            roleId = command.getTab();
        } else {
            roleId = 1;
        }

        if (command.getUrlType() != null) {
            if (command.getUrlType().equals(WebConstants.URL_CREATE)) {
                //create RoleDto
                RoleDto roleDto = new RoleDto();
                roleDto.setRoleId(roleId);
                //create ObjectDto
                ObjectDto objectDto = new ObjectDto();
                if(command.getObjectId() != null){
                    objectDto = SingletonServiceUtil.getObjectService().findByID(command.getObjectId());
                }
                //create OperatorDto
                OperatorDto operatorDto = new OperatorDto();
                if(command.getOperatorId() != null){
                    operatorDto.setOperatorID(command.getOperatorId());
                }else {
                    operatorDto.setOperatorID(1);
                }
                //create RolePermissionDto
                RolePermissionDto rolePermissionDto = new RolePermissionDto();
                rolePermissionDto.setRoleDto(roleDto);
                rolePermissionDto.setObjectDto(objectDto);
                rolePermissionDto.setOperatorDto(operatorDto);

                SingletonServiceUtil.getRolePermissionServiceInstance().addPermission(rolePermissionDto);
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("add success");
            }
            if (command.getUrlType().equals(WebConstants.URL_DELETE)) {
                if (command.getRolePermissionId() != null) {
                    SingletonServiceUtil.getRolePermissionServiceInstance().deletePermission(command.getRolePermissionId());
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write("delete success");
                } else {
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write("delete error");
                }
            }
        }
    }

    private OperatorDto initBadgeOperator(OperatorDto operatorDto) {
        StringBuilder operator = new StringBuilder();
        if (operatorDto.getCreate().equals(1)) {
            operator.append("<span class='badge badge-success'>THÊM</span>").append("\n");
        }
        if (operatorDto.getEdit().equals(1)) {
            operator.append("<span class='badge badge-warning'>SỬA</span>").append("\n");
        }
        if (operatorDto.getView().equals(1)) {
            operator.append("<span class='badge badge-info'>XEM</span>").append("\n");
        }
        if (operatorDto.getDelete().equals(1)) {
            operator.append("<span class='badge badge-danger'>XÓA</span>").append("\n");
        }
        operatorDto.setNameOperator(operator.toString());
        return operatorDto;
    }

    private List<RolePermissionDto> initBadgeOperatorRolePermission(List<RolePermissionDto> list) {
        List<RolePermissionDto> rolePermissionList = new ArrayList<>();
        for (RolePermissionDto item : list) {
            OperatorDto operatorDto = item.getOperatorDto();
            operatorDto = initBadgeOperator(operatorDto);
            item.setOperatorDto(operatorDto);
            rolePermissionList.add(item);
        }
        return rolePermissionList;
    }

    /**
     * Lay tat ca Object theo Role
     * @param roleId
     * @return
     */
    private List<ObjectDto> getAllObjectForRole(Integer roleId){
        List<ObjectDto> objectList = new ArrayList<>();
        Map<String, Object> mapProperty = new HashMap<>();
        mapProperty.put("roleModuleEntity",roleId);
        List<RoleModuleDto> roleModuleDtoList = SingletonServiceUtil.getRoleModuleServiceInstance().findByProperty(mapProperty, null, null, null, null);
        for(RoleModuleDto item: roleModuleDtoList){
            mapProperty.clear();
            mapProperty.put("moduleObjectEntity", item.getModuleDto().getModuleID());
            List<ObjectDto> dtos = SingletonServiceUtil.getObjectService().findByProperty(mapProperty, null, null, null, null);
            objectList.addAll(dtos);
        }
        return objectList;
    }

}
