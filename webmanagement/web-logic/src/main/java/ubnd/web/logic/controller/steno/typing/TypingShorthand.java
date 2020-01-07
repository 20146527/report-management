package ubnd.web.logic.controller.steno.typing;

import org.jsoup.Jsoup;
import ubnd.common.utils.EncodingUtils;
import ubnd.common.utils.FileUtils;
import ubnd.common.utils.SessionUtils;
import ubnd.common.utils.TimeUtils;
import ubnd.core.dto.MeetingDto;
import ubnd.core.dto.StenographyDto;
import ubnd.core.dto.UserDto;
import ubnd.core.web.common.WebConstants;
import ubnd.core.web.utils.SingletonServiceUtil;
import ubnd.web.logic.utils.StenoUtils;
import ubnd.web.logic.utils.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/typing-shorthand.html")
public class TypingShorthand extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String type = request.getParameter("type");
            if (type.equals("save")) {
                request.setAttribute("save", "save");
            }
        } catch (NullPointerException e) {
            request.setCharacterEncoding("UTF-8");
            UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);

            //Ghi lại file dict bộ luật cho cá nhân
            Utils.writeDict(request, userDto);
            String pathPerson = EncodingUtils.base64Encode(FileUtils.getPersonDict(userDto));
            request.setAttribute("dict_person", pathPerson);

            //get list meeting
            Map<String, Object> mapProperty = new HashMap<>();
            mapProperty.put("status", 0);
            List<MeetingDto> meetingDtoList = SingletonServiceUtil.getMeetingServiceInstance().findByProperty(mapProperty, null, null, null, null);
            request.setAttribute("meetingList", meetingDtoList);


            String fileNameStenoStructure = Utils.getDto("fileNameStenoStructure").getValue();
            if (!fileNameStenoStructure.equals("1")) {
                request.setAttribute("fileNameStenoStructure", fileNameStenoStructure);
                if (fileNameStenoStructure.equals("2")) {
                    request.setAttribute("name", userDto.getUserName());
                } else if (fileNameStenoStructure.equals("3")) {
                    request.setAttribute("name", userDto.getUserId());
                }
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/steno/typing/typing-shorthand.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);

        String pathSteno = Utils.getDto("pathSteno").getValue();
        String fileNameStenoStructure = Utils.getDto("fileNameStenoStructure").getValue();

        String data_typing = request.getParameter("data-typing");
        String time = String.valueOf(System.currentTimeMillis());

        String sessionId = request.getParameter("sessionId");
        String meetingId = request.getParameter("meetingId");
        if (sessionId == null || sessionId.equals("")) {
            sessionId = "1";
        }

        String path = FileUtils.templatePath(request) + File.separator + pathSteno.replace("/", "\\") + File.separator + meetingId + "_meeting" + File.separator + sessionId + "_session" + File.separator + time;
        String name;
        if (fileNameStenoStructure.equals("1"))
            name = request.getParameter("file-name");
        else
            name = request.getParameter("name-file");

        String name_file = path + File.separator + "text_" + name + ".txt";

        FileUtils.writeNewFile(name_file, data_typing);
        String textConvert = Jsoup.parse(data_typing).text();

        String name_file_steno = path + File.separator + "steno_" + name + ".txt";

        FileUtils.writeNewFile(name_file_steno, StenoUtils.wordToSteno(request, textConvert));

        StenographyDto dto = new StenographyDto();
        Timestamp timestamp = TimeUtils.getTimestamp(time);
        dto.setName(name);
        dto.setContent(pathSteno + "/" + meetingId + "_meeting/" + sessionId + "_session/" + time);
        dto.setCreDate(timestamp);
        dto.setStatus(0);
        dto.setStenoId(null);
        dto.setModDate(timestamp);
        dto.setCreUID(userDto.getUserId());
        dto.setModUID(userDto.getUserId());
        dto.setSessionDto(SingletonServiceUtil.getSessionServiceInstance().findByID(Integer.valueOf(sessionId)));

        SingletonServiceUtil.getStenographyService().save(dto);
        response.sendRedirect("/typing-shorthand.html?type=save");
    }

}
