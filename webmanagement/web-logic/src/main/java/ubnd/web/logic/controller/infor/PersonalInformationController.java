package ubnd.web.logic.controller.infor;

import ubnd.core.dto.UserDto;
import ubnd.core.service.UserService;
import ubnd.core.service.impl.UserServiceImpl;
import ubnd.core.web.common.WebConstants;
import ubnd.core.web.utils.StringUtils;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/personal-information.html")
public class PersonalInformationController extends HttpServlet {
    private UserDto dto = null;

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/personal-info/personal-information.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String oldPass = request.getParameter(WebConstants.OLD_PASS);
//        String newPass = request.getParameter(WebConstants.NEW_PASS);
//        String reNewPass = request.getParameter(WebConstants.RE_NEW_PASS);
//
//        if (oldPass.trim().equals("") || newPass.trim().equals("") || reNewPass.trim().equals("")) {
//            request.setAttribute(WebConstants.ALERT, WebConstants.TYPE_ERROR);
//            request.setAttribute(WebConstants.MESSAGE_RESPONSE, "Không được bỏ trống các trường dữ liệu");
//        } else {
//            if (!StringUtils.stringToSHA256(oldPass).toLowerCase().equals(dto.getPassWord().toLowerCase())) {
//                request.setAttribute(WebConstants.ALERT, WebConstants.TYPE_ERROR);
//                request.setAttribute(WebConstants.MESSAGE_RESPONSE, "Mật khẩu không chính xác");
//            } else if (!newPass.equals(reNewPass)) {
//                request.setAttribute(WebConstants.MESSAGE_RESPONSE, "Mật khẩu mới không khớp");
//            } else {
//                request.setAttribute(WebConstants.MESSAGE_RESPONSE, null);
//                UserService service = new UserServiceImpl();
//                dto.setPassWord(newPass);
//                service.updateUserInfo(dto);
//            }
//        }
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("views/personal-info/personal-information.jsp");
//        dispatcher.forward(request, response);

    }


}