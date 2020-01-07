package ubnd.web.logic.controller.roles;

import com.google.gson.Gson;
import ubnd.core.dto.*;
import ubnd.core.web.utils.SingletonServiceUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/manager-module.html")
public class ModuleController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String moduleId = request.getParameter("module");
        if (moduleId == null)
            moduleId = "1";

        List<ModuleDto> moduleDtoList = SingletonServiceUtil.getModuleService().findAll();

        String data = request.getParameter("data");
        Gson gson = new Gson();
        if (data != null) {
           if (data.equals("user")){
                //tab1
                List<UserDto> userDtoList = SingletonServiceUtil.getUserServiceInstance().findAll();
                List<UserModuleDto> userModuleDtoList = SingletonServiceUtil.getUserModuleService().findByModuleID(Integer.valueOf(moduleId));


                for (UserModuleDto dto : userModuleDtoList) {
                    for (UserDto userDto : userDtoList) {
                        if (dto.getUserDto().getUserId().equals(userDto.getUserId())) {
                            userDtoList.remove(userDto);
                            break;
                        }
                    }
                }

                Object[] objects = new Object[]{userDtoList, userModuleDtoList};
                setResponseAjax(response, gson.toJson(objects));
            }
           if(data.equals("role")){
               //tab2
               List<RoleDto> listAllRole = SingletonServiceUtil.getRoleServiceInstance().findAll();
               Map<String, Object> mapProperty = new HashMap<>();
               mapProperty.put("moduleRoleEntity", moduleId);
               List<RoleModuleDto> listRoleModule = SingletonServiceUtil.getRoleModuleServiceInstance().findByProperty(mapProperty, null, null, null, null);
               //remove role added in module
               for(RoleModuleDto roleModuleDto: listRoleModule){
                   for(RoleDto roleDto: listAllRole){
                       if(roleModuleDto.getRoleDto().getRoleId().equals(roleDto.getRoleId())){
                           listAllRole.remove(roleDto);
                           break;
                       }
                   }
               }
               Object[] objects = new Object[]{listAllRole, listRoleModule};
               setResponseAjax(response, gson.toJson(objects));
           }
        }

        request.setAttribute("moduleDtoList", moduleDtoList);
        request.setAttribute("id", moduleId);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/module/manager-module.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String data = request.getParameter("data");
        String type = request.getParameter("type");
        String moduleId = request.getParameter("module_id");
        if (data.equals("user")) {
            String userId = request.getParameter("user_id");
            if (type.equals("add")) {
                UserModuleDto dto = new UserModuleDto();
                dto.setUserModuleID(0);
                dto.setUserDto(SingletonServiceUtil.getUserServiceInstance().findById(Integer.valueOf(userId)));
                dto.setModuleDto(SingletonServiceUtil.getModuleService().findByID(Integer.valueOf(moduleId)));
                SingletonServiceUtil.getUserModuleService().save(dto);
            } else if (type.equals("delete")) {
                String userModuleId = request.getParameter("userModuleId");
                if(userModuleId != null){
                    SingletonServiceUtil.getUserModuleService().delete(Integer.valueOf(userModuleId));
                }

            }
        } else if (data.equals("role")) {
            if (type.equals("add")) {
                String roleId = request.getParameter("roleId");
                if(roleId != null){
                    //create moduleDto
                    ModuleDto moduleDto = new ModuleDto();
                    moduleDto.setModuleID(Integer.parseInt(moduleId));
                    //create roleDto
                    RoleDto roleDto = new RoleDto();
                    roleDto.setRoleId(Integer.valueOf(roleId));
                    //create roleModuleDto
                    RoleModuleDto roleModuleDto = new RoleModuleDto();
                    roleModuleDto.setModuleDto(moduleDto);
                    roleModuleDto.setRoleDto(roleDto);
                    SingletonServiceUtil.getRoleModuleServiceInstance().addRole(roleModuleDto);
                }
            } else if (type.equals("delete")) {
                String roleModuleId = request.getParameter("roleModuleId");
                if(roleModuleId != null){
                    SingletonServiceUtil.getRoleModuleServiceInstance().deleteRole(Integer.valueOf(roleModuleId));
                }
            }
        }
    }

    private void setResponseAjax(HttpServletResponse response, String data) throws IOException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(data);
        out.flush();
        out.close();
    }
}
