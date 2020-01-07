package ubnd.web.logic.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DownloadUtil {
    public DownloadUtil() {
        super();
    }

    /**
     * Download file in server
     *
     * @param response HttpServletResponse response
     * @param nameFile name file want to display when download
     * @param my_file  path file download
     * @throws IOException Exception with file
     */
    public void downloadFile(HttpServletResponse response, String nameFile, String my_file) throws IOException {
        OutputStream out = response.getOutputStream();
        response.setContentType("text/html");
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment; filename=" + nameFile);
        FileInputStream in = new FileInputStream(my_file);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
    }
}
