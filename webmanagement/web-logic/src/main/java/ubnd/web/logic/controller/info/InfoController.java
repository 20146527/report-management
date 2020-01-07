package ubnd.web.logic.controller.info;


import org.zwobble.mammoth.DocumentConverter;
import org.zwobble.mammoth.Result;
import ubnd.common.constant.CoreConstant;
import ubnd.common.utils.FileUtils;
import ubnd.core.dto.ConfigDto;
import ubnd.core.dto.ParagraphTranscriptDto;
import ubnd.core.web.common.WebConstants;
import ubnd.core.web.utils.SingletonServiceUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/info.html", "/qna.html"})
public class InfoController extends HttpServlet {
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        String address = FileUtils.getRealPath(request, CoreConstant.FOLDER_FILE);
        String path = address + File.separator + WebConstants.RESULT_24 + File.separator + "kq-vnist.json";
        String data = FileUtils.readContentFile(path);

        String fileA = address + File.separator + "temp" + File.separator + "template2.docx";

        DocumentConverter converter = new DocumentConverter();
        Result<String> result = converter.convertToHtml(new File(fileA));
        String html = result.getValue();
        Set<String> warnings = result.getWarnings();
        System.out.println(html + "---" + warnings);

//        Map<String, Object> mapProperty = new HashMap<String, Object>();
//        mapProperty.put("status", 0);
//        mapProperty.put("type", "steno");
//        Object[] objects = SingletonServiceUtil.getConfigServiceInstance().findByProperty(mapProperty, null, null, null, null);
//        List<ConfigDto> configList = (List<ConfigDto>) objects[1];

//        JSONObject objectFile = new JSONObject(data);
//        JSONArray arrayData = objectFile.getJSONArray("data");
//        JSONObject objectData = arrayData.getJSONObject(0);
//        JSONArray arrayTranscriptInfo = objectData.getJSONArray("transcript_info");
//
//        String dataTRS = arrayTranscriptInfo.toString();
//        String folderName = "result24" + File.separator + "Session1" + File.separator + "Record1";
//        String nameFileXML = "Nghia.xml";
//        String xmlPath = SingletonServiceUtil.getTranscriptServiceInstance().createXML(request, folderName, nameFileXML, data);


//        String folderName = "result24" + File.separator + "Session1" + File.separator + "Record1";
//        String nameFile = "All.json";
//        String result = SingletonServiceUtil.getTranscriptServiceInstance().writeJSONContent(request, folderName, nameFile, dataTRS);
//        result = result.replace("\\", "/");
//        System.out.println(result);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/info/info.jsp");
        dispatcher.forward(request, response);

    }

}