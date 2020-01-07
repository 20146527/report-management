package ubnd.api.gateway.service;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/api/demo"})
public class DemoAPI extends HttpServlet {
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("GET-abcdefg");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getHeader("Token");
        String speakerName = request.getParameter("speakerName");
        System.out.println(token + "---" + speakerName);
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("POST-abcdefg");

    }
}