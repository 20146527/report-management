package ubnd.core.web.common;

import org.json.JSONObject;
import ubnd.common.utils.FileUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class WebConstants {
    public static final String MESSAGE_RESPONSE = "messageResponse";
    public static final String FORM_ITEM = "item";
    public static final String LIST_ITEMS = "items";
    public static final String ALERT = "alert";
    public static final String TYPE_SUCCESS = "success";
    public static final String TYPE_WARNING = "warning";
    public static final String TYPE_ERROR = "danger";
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";
    public static final String URL_LIST = "url_list";
    public static final String URL_EDIT = "url_edit";
    public static final String URL_CREATE = "url_create";
    public static final String URL_UPLOAD = "url_upload";
    public static final String URL_SEARCH = "url_search";
    public static final String URL_DELETE = "url_delete";
    public static final String URL_DETAIL = "url_detail";
    public static final String INSERT_UPDATE = "insert_update";
    public static final String REDIRECT_INSERT = "redirect_insert";
    public static final String REDIRECT_UPDATE = "redirect_update";
    public static final String REDIRECT_DELETE = "redirect_delete";
    public static final String REDIRECT_ERROR = "redirect_error";
    public static final String LOGIN_NAME = "login_name";
    public static final String LOGIN = "login";
    public static final String LOGOUT = "logout";
    public static final String AUDIO_SAMPLE = "audiosample";
    public static final String AUDIO_RECORD = "audiorecord";
    public static final String RESULT_24 = "result24";
    public static final String EXCEL = "excel";
    public static final String DOC = "doc";
    public static final String AVA_PATH = "imagePath";
    public static final String DTO = "dto";
    public static final String OLD_PASS = "oldPassword";
    public static final String NEW_PASS = "newPassword";
    public static final String RE_NEW_PASS = "renewPassword";
    public static final String DATA_PERSON_DICT = "dataPersonDict";
    public static final String STENO_CODE = "stenoCode";
    public static final String VALUE = "value";
    public static final String DATA = "dataEdit";
    public static final String ID = "id";
    public static final String STATUS = "status";
    public static final String WORD = "Âm tiết";
    public static final String LIST_URL = "list_URL";
    public static String REPORT_CONTENT;

    public static String getOderLayout(HttpServletRequest request) throws IOException {
        String s = "";
        String path = FileUtils.templatePath(request) + File.separator + "keycode" + File.separator + "binaryToSteno.json";
        JSONObject object = new JSONObject(FileUtils.readContentFile(path));
        StringBuilder data = new StringBuilder();
        for (int i = 1; i < 23; i++) {
            data.append(object.getString(String.valueOf((int) (Math.pow(2, i)))).replace("-", ""));
        }
        return data.toString();
    }
}
