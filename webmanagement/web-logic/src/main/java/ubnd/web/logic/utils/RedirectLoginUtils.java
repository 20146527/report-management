package ubnd.web.logic.utils;

import ubnd.core.dto.UserDto;
import ubnd.core.web.common.WebConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RedirectLoginUtils {
    private HttpServletRequest request;
    private RedirectLogin redirectLogin;

    public RedirectLoginUtils(HttpServletRequest request, RedirectLogin redirectLogin) throws IOException, ServletException {
        this.request = request;
        this.redirectLogin = redirectLogin;
        this.setRedirectLogin();
    }

    private void setRedirectLogin() throws IOException, ServletException {
        HttpSession session = request.getSession(true);
        UserDto dto = (UserDto) session.getAttribute(WebConstants.LOGIN);
        if (dto != null) {
            if (dto.getAvaPath()!=null) {
                request.setAttribute("imagePath", dto.getAvaPath());
            }else {
                request.setAttribute("imagePath", "1.jpg");
            }
            redirectLogin.isLogin();
        } else {
            redirectLogin.noLogin();
        }

    }

    public interface RedirectLogin {
        void isLogin() throws IOException, ServletException;

        void noLogin() throws ServletException, IOException;
    }
}
