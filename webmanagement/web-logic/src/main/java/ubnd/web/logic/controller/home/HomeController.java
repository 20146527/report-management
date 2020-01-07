package ubnd.web.logic.controller.home;

import ubnd.common.utils.FileUtils;
import ubnd.common.utils.SessionUtils;
import ubnd.core.dto.UserDto;
import ubnd.core.web.common.WebConstants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/home.html")
public class HomeController extends HttpServlet {
    protected void doGet(HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);
        String address = FileUtils.getRealPath(request, "file");
        String filePath = address + File.separator + "dict" + File.separator + "person" + File.separator + userDto.getUserName() + File.separator + userDto.getUserName() + ".json";
        File file = new File(filePath);
        if (!file.exists()) {
            FileUtils.writeNewFile(filePath, "{}");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/home/home.jsp");
        dispatcher.forward(request, response);
    }
}
