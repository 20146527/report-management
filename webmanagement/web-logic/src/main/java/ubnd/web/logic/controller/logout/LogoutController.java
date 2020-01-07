package ubnd.web.logic.controller.logout;

import ubnd.common.utils.SessionUtils;
import ubnd.core.web.common.WebConstants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout.html")
public class LogoutController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionUtils.getInstance().remove(request, WebConstants.LOGIN);
        response.sendRedirect("/login.html");
    }
}
