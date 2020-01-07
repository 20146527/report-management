package ubnd.core.web.utils;

import org.apache.commons.lang.StringUtils;
import ubnd.core.web.common.WebConstants;


import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Admin on 27/8/2017.
 */
public class WebCommonUtil {
    public static void addRedirectMessage(HttpServletRequest request, String crudaction, Map<String, String> mapMessage) {
        if (StringUtils.isNotBlank(crudaction) && crudaction.equals(WebConstants.REDIRECT_INSERT)) {
            request.setAttribute(WebConstants.ALERT, WebConstants.TYPE_SUCCESS);
            request.setAttribute(WebConstants.MESSAGE_RESPONSE, mapMessage.get(WebConstants.REDIRECT_INSERT));
        } else if (StringUtils.isNotBlank(crudaction) && crudaction.equals(WebConstants.REDIRECT_UPDATE)) {
            request.setAttribute(WebConstants.ALERT, WebConstants.TYPE_WARNING);
            request.setAttribute(WebConstants.MESSAGE_RESPONSE, mapMessage.get(WebConstants.REDIRECT_UPDATE));
        } else if (StringUtils.isNotBlank(crudaction) && crudaction.equals(WebConstants.REDIRECT_DELETE)) {
            request.setAttribute(WebConstants.ALERT, WebConstants.TYPE_ERROR);
            request.setAttribute(WebConstants.MESSAGE_RESPONSE, mapMessage.get(WebConstants.REDIRECT_DELETE));
        } else if (StringUtils.isNotBlank(crudaction) && crudaction.equals(WebConstants.REDIRECT_ERROR)) {
            request.setAttribute(WebConstants.ALERT, WebConstants.TYPE_ERROR);
            request.setAttribute(WebConstants.MESSAGE_RESPONSE, mapMessage.get(WebConstants.REDIRECT_ERROR));
        }
    }
}
