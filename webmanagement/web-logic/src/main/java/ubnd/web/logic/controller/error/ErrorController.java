package ubnd.web.logic.controller.error;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/error.html")
public class ErrorController extends HttpServlet {

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        Integer statusCode = Integer.valueOf(request.getParameter("error"));
        switch (statusCode) {
            case 400:
                setErrorAttribute(request, 400, "PAGE NOT FOUND");
                break;
            case 403:
                setErrorAttribute(request, 403, "FORBIDDON ERROR!");
                break;
            case 405:
                setErrorAttribute(request, 405, "METHOD NOT ALLOWED");
                break;
            case 500:
                setErrorAttribute(request, 500, "INTERNAL SERVER ERROR !");
                break;
            case 503:
                setErrorAttribute(request, 503, "THIS SITE IS GETTING UP IN FEW MINUTES.");
                break;
            default:
                setErrorAttribute(request, 404, "PAGE NOT FOUND");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/error/error.jsp");
        dispatcher.forward(request, response);
    }

    private void setErrorAttribute(HttpServletRequest request, int error_code, String error_status) {
        request.setAttribute("error_code", error_code);
        request.setAttribute("error_status", error_status);
    }
}
