package ubnd.core.utlis;

import javax.servlet.http.HttpServletRequest;

public class WebUriUtils {

    public static String getCurrentUrlWithoutParams(HttpServletRequest request) {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String url = scheme + "://" + serverName + ":" + serverPort;
        return url;
    }
}
