package ubnd.common.constant;


import org.json.JSONObject;
import ubnd.common.utils.FileUtils;
import ubnd.core.dto.ExtendPermissionUserDto;
import ubnd.core.dto.RolePermissionDto;
import ubnd.core.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CoreConstant {
    public static final String SORT_ASC = "1";
    public static final String SORT_DESC = "2";
    public static final String FOLDER_UPLOAD = "file";
    public static final String FOLDER_FILE= "file";

    public static final String ROLE_PERMISSION_LIST= "rolePermissionList";
    public static final String EXTEND_PERMISSION_LIST= "extendPermissionList";
    public static final String URL_ALLOWED_LIST= "urlAllowedList";

//    public static List<RolePermissionDto> ROLE_PERMISSION_LIST = null;
//    public static List<ExtendPermissionUserDto> EXTEND_PERMISSION_LIST = null;
//    public static List<String> URL_ALLOWED_LIST = null;
}
