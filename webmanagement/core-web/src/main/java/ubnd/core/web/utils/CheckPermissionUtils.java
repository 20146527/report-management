//package ubnd.core.web.utils;
//
//import ubnd.common.utils.SessionUtils;
//import ubnd.core.web.common.WebConstants;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.*;
//
//public class CheckPermissionUtils {
//    public boolean checkDoFilter(HttpServletRequest request, Integer roleID, String urlString) {
//        boolean check = false;
//        ArrayList<String> listURL = getListURLServletByRoleID(request, roleID);
//        for (String url : listURL) {
//            if (urlString.contains(url)) {
//                return true;
//            }
//        }
//        return check;
//    }
//
//    @SuppressWarnings("unchecked")
//    private ArrayList<String> getListURLServletByRoleID(HttpServletRequest request, Integer roleID) {
//        ArrayList<String> listURL = (ArrayList<String>) SessionUtils.getInstance().getValue(request, WebConstants.LIST_URL + roleID);
//        List<FunctionDto> functionDtoList = new ArrayList<>();
//        if (listURL == null) {
//            Map<String, Object> mapProperty = new HashMap<>();
//            mapProperty.put("rolePermissionEntity", roleID);
//            Object[] objects = SingletonServiceUtil.getPermissionRoleServiceInstance().findByProperty(mapProperty, null, null, null, null);
//            List<PermissionRoleDto> permissionRoleDtoList = (List<PermissionRoleDto>) objects[1];
//            for (PermissionRoleDto item : permissionRoleDtoList) {
//                functionDtoList.add(item.getFunctionDto());
//            }
//            listURL = getListURLByListFunction(functionDtoList);
//            SessionUtils.getInstance().putValue(request, WebConstants.LIST_URL + roleID, listURL);
//        }
//        return listURL;
//    }
//
//    private ArrayList<String> getListURLByListFunction(List<FunctionDto> functionList) {
//        ArrayList<String> listURL = new ArrayList<>();
//        for (FunctionDto item : functionList) {
//            String[] listStringURL = item.getFunctionDescription().split(",");
//            if (listStringURL.length != 0) {
//                Collections.addAll(listURL, listStringURL);
//            }
//        }
//        return listURL;
//
//    }
//}
