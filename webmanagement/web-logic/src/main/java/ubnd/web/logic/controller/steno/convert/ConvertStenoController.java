package ubnd.web.logic.controller.steno.convert;

import ubnd.common.utils.SessionUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/steno-convert.html")
public class ConvertStenoController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionUtils.getInstance().remove(request, "ContentConvert");
        SessionUtils.getInstance().remove(request, "option");
        SessionUtils.getInstance().remove(request, "typeFile");
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/steno/convert/steno-convert.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        if (type != null && type.equals("setContentConvert")) {
            String data = request.getParameter("content");
            String option = request.getParameter("option");
            String typeFile = request.getParameter("typeFile");
            SessionUtils.getInstance().putValue(request, "ContentConvert", data);
            SessionUtils.getInstance().putValue(request, "option", option);
            SessionUtils.getInstance().putValue(request, "typeFile", typeFile);
        }

    }
}
