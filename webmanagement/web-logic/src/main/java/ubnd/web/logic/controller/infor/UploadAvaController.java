package ubnd.web.logic.controller.infor;

import org.apache.commons.fileupload.FileUploadException;
import ubnd.common.utils.UploadUtils;
import ubnd.core.dao.UserDao;
import ubnd.core.dao.impl.UserDaoImpl;
import ubnd.core.dto.UserDto;
import ubnd.core.utlis.UserBeanUtils;
import ubnd.core.web.common.WebConstants;
import ubnd.common.utils.SessionUtils;
import ubnd.web.logic.command.UserCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/upload-ava.html")
public class UploadAvaController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
//        SessionUtils sessionUtils = new SessionUtils(request);
//        UserDto dto = sessionUtils.getUserDTO();
//        UploadUtils utils = new UploadUtils();
//        try {
//            utils.writeOrUpdateFile(request);
//            dto.setAvaPath(dto.getUserName());
//            UserDao userDao = new UserDaoImpl();
//            userDao.update(UserBeanUtils.dtoToEntity(dto));
//            session.setAttribute(WebConstants.LOGIN, dto);
//
//        } catch (FileUploadException e) {
//        } catch (Exception e) {
//        }


    }
}
