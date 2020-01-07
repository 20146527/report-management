package ubnd.web.logic.controller.login;

import ubnd.common.utils.SessionUtils;
import ubnd.core.dto.UserDto;
import ubnd.core.web.common.WebConstants;
import ubnd.core.web.utils.FormUtils;
import ubnd.core.web.utils.SingletonServiceUtil;
import ubnd.web.logic.command.UserCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login.html")
public class LoginController extends HttpServlet {

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/login/login.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserCommand command = FormUtils.populate(UserCommand.class, request);
        UserDto pojo = command.getPojo();
        try {
            UserDto dto = SingletonServiceUtil.getUserServiceInstance().isUserExist(pojo);
            if (dto != null) {
//                if (dto.getAvaPath().equals("") || dto.getAvaPath() == null) {
//                    if (dto.getGender() == 1)
//                        dto.setAvaPath("1.jpg");
//                    else
//                        dto.setAvaPath("8.jpg");
//                }
                dto.setAvaPath("8.jpg");
                SingletonServiceUtil.getPermissionServiceInstance().getAllUserPermission(request, dto);
                SessionUtils.getInstance().putValue(request, WebConstants.LOGIN, dto);
                response.sendRedirect("/home.html");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            request.setAttribute(WebConstants.ALERT, WebConstants.TYPE_ERROR);
            request.setAttribute(WebConstants.MESSAGE_RESPONSE, "Tài khoản hoặc mật khẩu không đúng");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/login/login.jsp");
            dispatcher.forward(request, response);
        }
    }

}
