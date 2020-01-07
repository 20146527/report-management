package ubnd.web.logic.controller.steno.convert;

import org.jsoup.Jsoup;
import ubnd.common.utils.EncodingUtils;
import ubnd.common.utils.FileUtils;
import ubnd.common.utils.SessionUtils;
import ubnd.web.logic.utils.DownloadUtil;
import ubnd.web.logic.utils.StenoUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/steno-display-convert.html")
public class DisplayConvertStenoController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String data = (String) SessionUtils.getInstance().getValue(request, "ContentConvert");
        int option = Integer.parseInt(SessionUtils.getInstance().getValue(request, "option").toString());
        int type = Integer.parseInt(SessionUtils.getInstance().getValue(request, "typeFile").toString());
        try {
            if (type == 2) {
                data = EncodingUtils.base64Decode(data);
            }

            if (option == 1) {
                data = StenoUtils.wordToSteno(request, Jsoup.parse(data).text());
            } else if (option == 2) {
                data = StenoUtils.stenoToWord(request, data);
            } else {
                data = Jsoup.parse(data).text();
            }
            request.setAttribute("ContentConvert", data);
        } catch (NullPointerException e) {
            request.setAttribute("error", "File đầu vào không hợp lệ");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/steno/convert/steno-convert.jsp");
            dispatcher.forward(request, response);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/steno/convert/display-convert.jsp");
        dispatcher.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String namefile = request.getParameter("name-file") + ".txt";
        String content = request.getParameter("data-convert");
        String check = request.getParameter("check-decode");
        if (check != null && check.equals("on")) {
            content = EncodingUtils.base64Encode(content);
        }
        String path = FileUtils.getTemptPath(request) + File.separator + namefile;
        FileUtils.writeNewFile(path, content);
        DownloadUtil util = new DownloadUtil();
        util.downloadFile(response, namefile, path);
        File file = new File(path);
        if (file.delete()) {
            System.out.println("delete file tempt");
        }
    }
}
