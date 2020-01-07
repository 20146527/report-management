package ubnd.common.utils;

import javax.servlet.http.HttpServletRequest;

public class WebUriUtils {

    /**
     * Get current URL link
     *
     * @param request HttpServletRequest request
     * @return String current URL link
     */
    public static String getCurrentUrlWithoutParams(HttpServletRequest request) {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        return scheme + "://" + serverName + ":" + serverPort;
    }
}
