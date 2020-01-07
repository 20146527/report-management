package ubnd.web.logic.controller.log;

import ubnd.common.constant.CoreConstant;
import ubnd.common.utils.EncodingUtils;
import ubnd.common.utils.FileUtils;
import ubnd.common.utils.SessionUtils;
import ubnd.common.utils.TimeUtils;
import ubnd.core.dao.DictStenoDao;
import ubnd.core.dao.impl.DictStenoDaoImpl;
import ubnd.core.data.obj.DictObject;
import ubnd.core.dto.DictStenoDTO;
import ubnd.core.dto.UserDto;
import ubnd.core.service.DictStenoService;
import ubnd.core.service.impl.DictStenoServiceImpl;
import ubnd.core.utlis.DictStenoBeanUtils;
import ubnd.core.web.common.WebConstants;
import ubnd.web.logic.utils.DownloadUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/dict-history.html")
public class DistHistoryController extends HttpServlet {
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {


        UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);
        DictStenoService service = new DictStenoServiceImpl();
        List<DictObject> list = service.listDict();
        String type = request.getParameter("type");
        if (type != null) {
            DictStenoDTO dto = new DictStenoDTO();
            DictStenoDao dao = new DictStenoDaoImpl();

            switch (type) {
                case "default":
                    for (DictObject dictObject : list) {
                        String name = EncodingUtils.base64Decode(request.getParameter("name"));
                        if (dictObject.getDto().getDictName().equals(name)) {
                            dto = dictObject.getDto();
                            dto.setDictDefault(1);
                            dictObject.setDto(dto);
                            service.updateDefaultDist(-1);
                        }
                    }
                    setCommonDictStenoDTO(dto, userDto, dao, response);
                    break;
                case "restore":
                    String name = EncodingUtils.base64Decode(request.getParameter("name"));
                    for (DictObject dictObject : list) {
                        if (dictObject.getDto().getDictName().equals(name)) {
                            dto = dictObject.getDto();
                            dto.setStatus(0);
                            dictObject.setDto(dto);
                        }
                    }
                    setCommonDictStenoDTO(dto, userDto, dao, response);
                    break;
                case "download":
                    String nameFile = request.getParameter("nameFile");
                    String filePath = FileUtils.templatePath(request) + File.separator + "dict" + File.separator + nameFile;
                    DownloadUtil util = new DownloadUtil();
                    util.downloadFile(response, nameFile, filePath);
                    break;
            }
        } else {
            DictObject default_dict = service.getDefaultDict();
            List<DictObject> listRemover = new ArrayList<>();
            for (DictObject item : list) {
                String nameFile = item.getDto().getDictName() + ".json";
                File filePath = new File(FileUtils.getRealPath(request, CoreConstant.FOLDER_UPLOAD) + File.separator + "dict" + File.separator + nameFile);
                if (!filePath.exists()) {
                    listRemover.add(item);
                }
            }

            for (DictObject item : listRemover) {
                list.remove(item);
            }

            request.setAttribute("list", list);
            request.setAttribute("default_dict", default_dict.getDto().getDictName());

            RequestDispatcher dispatcher = request.getRequestDispatcher("views/log/dict-history.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void setCommonDictStenoDTO(DictStenoDTO dto, UserDto userDto, DictStenoDao dao, HttpServletResponse response) throws IOException {
        dto.setModUID(userDto.getUserId());
        dto.setModDate(TimeUtils.getCurrentTimeStamp());
        dao.update(DictStenoBeanUtils.dtoToEntity(dto));
        response.sendRedirect("/dict-history.html");
    }
}
