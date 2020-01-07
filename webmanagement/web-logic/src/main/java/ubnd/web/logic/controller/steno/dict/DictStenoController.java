package ubnd.web.logic.controller.steno.dict;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import ubnd.common.utils.*;
import ubnd.core.data.obj.DictObject;
import ubnd.core.data.obj.ListWord;
import ubnd.core.data.obj.StenoRuleObject;
import ubnd.core.data.obj.VersionRule;
import ubnd.core.dto.ConfigDto;
import ubnd.core.dto.DictStenoDTO;
import ubnd.core.dto.UserDto;
import ubnd.core.web.common.WebConstants;
import ubnd.core.web.utils.SingletonServiceUtil;
import ubnd.web.logic.utils.DownloadUtil;
import ubnd.web.logic.utils.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@WebServlet("/manager-dict-steno-list.html")
public class DictStenoController extends HttpServlet {
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);
        String data = request.getParameter("data");
        //tải xuống file
        if (data != null && data.equals("download")) {
            String type = request.getParameter("type");
            String filePath;
            String name;
            if (type.equals("template")) {
                filePath = FileUtils.templatePath(request) + File.separator + "dict" + File.separator + "template" + File.separator + "template_from-nhap-vao-bo-tu-dien-toc-ky.xlsx";
                name = "template_from-nhap-vao-bo-tu-dien-toc-ky.xlsx";
            } else {
                name = request.getParameter("nameFile");
                filePath = FileUtils.templatePath(request) + File.separator + "dict" + File.separator + name;
            }
            DownloadUtil util = new DownloadUtil();
            util.downloadFile(response, name, filePath);
        } else {
            String type = request.getParameter("type");
            List<DictObject> list = SingletonServiceUtil.getDictStenoServiceInstance().listDict();
            if (type != null) {
                String name = EncodingUtils.base64Decode(request.getParameter("name"));
                DictStenoDTO dto = new DictStenoDTO();

                //set bộ từ điển mặc định
                if (type.equals("default")) {
                    for (DictObject dictObject : list) {
                        if (dictObject.getDto().getDictName().equals(name)) {
                            dto = dictObject.getDto();
                            dto.setDictDefault(0);
                            dictObject.setDto(dto);
                            SingletonServiceUtil.getDictStenoServiceInstance().updateDefaultDist(-1);
                        }
                    }
                    // Xóa bộ từ điển
                } else if (type.equals("delete")) {
                    for (DictObject dictObject : list) {
                        if (dictObject.getDto().getDictName().equals(name)) {
                            dto = dictObject.getDto();
                            dto.setStatus(1);
                            dictObject.setDto(dto);
                        }
                    }
                }


                dto.setModUID(userDto.getUserId());
                dto.setModDate(TimeUtils.getCurrentTimeStamp());

                //cập nhật lại bộ từ điển
                SingletonServiceUtil.getDictStenoServiceInstance().update(dto);
                response.sendRedirect("/manager-dict-steno-list.html");

            } else {
                //Lấy danh sách bộ từ điển chưa xóa trong hệ thống

                List<DictObject> listRemover = new ArrayList<>();
                for (DictObject item : list) {
                    String nameFile = item.getDto().getDictName() + ".json";
                    File filePath = new File(FileUtils.getRealPath(request, "file") + File.separator + "dict" + File.separator + nameFile);
                    if (!filePath.exists() || item.getDto().getStatus() == 1) {
                        listRemover.add(item);
                    }
                }

                for (DictObject item : listRemover) {
                    list.remove(item);
                }

                list.sort(Comparator.comparing(o -> o.getDto().getDictDefault()));

                String defaultName = Utils.getDto("fileNameDictDefaultStructure").getValue();
                if (!defaultName.equals("1")) {
                    request.setAttribute("defaultName", defaultName);
                }


                request.setAttribute("list", list);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/steno/dict/list-dict-steno.jsp");
                dispatcher.forward(request, response);
            }
        }

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String type = request.getParameter("type");
        //lấy config của bộ tốc ký
        ConfigDto configDto = Utils.getDto("fileNameDictDefaultStructure");
        // lấy thông tin người dùng
        UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);
        StringBuilder nameDict = new StringBuilder();
        //lấy thời gian hiện tại
        long time = System.currentTimeMillis();
        Timestamp timeCreate = TimeUtils.getCurrentTimeStamp();
        String address = FileUtils.getRealPath(request, "file");
        String filePath = "";

        //Upload file
        if (type.equals("upload")) {
            DictStenoDTO dto = new DictStenoDTO();
            String nameFile = request.getParameter("name_file");
            boolean isDefault = Boolean.parseBoolean(request.getParameter("isDefault"));
            //Nếu tên tạo tự do
            if (configDto.getValue().equals("1")) {
                nameDict.append(nameFile);
                String[] listFile = FileUtils.getListFile(FileUtils.getPathDict(request));
                FileUtils.renameDuplicateFile(listFile, nameDict);

            } else {
                //tạo mặc định với kiểu ten_time
                if (configDto.getValue().equals("2")) {
                    nameDict.append(userDto.getUserName());
                } else if (configDto.getValue().equals("3")) {
                    //tạo mặc định với kiểu id_time
                    nameDict.append(userDto.getUserId());
                }
                nameDict.append("_").append(time);
            }
            dto.setDictName(nameDict.toString());

            //upload file excel lên  thư mục tạm
            UploadUtils uploadUtils = new UploadUtils();
            String path = uploadUtils.writeOrUpdateFile(request);
            File f = new File(path);
            FileInputStream excelFile = new FileInputStream(f);
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            List<String> list = new ArrayList<>();
            List<String> list1 = new ArrayList<>();
            for (Row r : datatypeSheet) {
                Cell cell = r.getCell(0);
                Cell cell1 = r.getCell(1);
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_NUMERIC:
                        list.add(String.valueOf(cell.getNumericCellValue()));
                        break;
                    case Cell.CELL_TYPE_STRING:
                        list.add(cell.getStringCellValue());
                        break;
                }

                switch (cell1.getCellType()) {
                    case Cell.CELL_TYPE_NUMERIC:
                        list1.add(String.valueOf(cell1.getNumericCellValue()));
                        break;
                    case Cell.CELL_TYPE_STRING:
                        list1.add(cell1.getStringCellValue());
                        break;
                }
            }

            PrintWriter out = response.getWriter();
            int status = 0;
            //Nếu độ dài 2 mảng k bằng nhau thì có lỗi trong file nhập vào
            if (list.size() != list1.size()) {
                status = -1;
            } else {
                JSONObject object = new JSONObject();
                for (int i = 0; i < list.size(); i++) {
                    object.put(list.get(i), list1.get(i));
                }
                dto.setLength(object.length());
                if (isDefault) {
                    SingletonServiceUtil.getDictStenoServiceInstance().updateDefaultDist(-1);
                    dto.setDictDefault(0);
                } else {
                    dto.setDictDefault(1);
                }

                //set data cho DTO
                dto.setContent(nameDict + ".json");
                dto.setStatus(0);
                dto.setCreUID(userDto.getUserId());
                dto.setCreDate(timeCreate);
                dto.setModUID(userDto.getUserId());
                dto.setModDate(timeCreate);

                filePath = address + File.separator + "dict" + File.separator + nameDict + ".json";
                SingletonServiceUtil.getDictStenoServiceInstance().save(dto);
                FileUtils.writeNewFile(filePath, object.toString());
            }

            //Xóa file tempt
            if (f.exists()) {
                f.delete();
            }

            out.write(status);

        } else if (type.equals("createDict")) {
            List<ListWord> list = Utils.getListWord(request);
            ListWord objListWordefault = new ListWord();
            for (ListWord obj : list) {
                if (obj.getStatus() == 0) {
                    objListWordefault = obj;
                    break;
                }
            }

            List<String> listWordDefault = new ArrayList<>();
            String pathFile = FileUtils.getPathListWord(request) + File.separator + objListWordefault.getNameFile();

            try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(pathFile)), StandardCharsets.UTF_8))) {
                String line = in.readLine();
                while (line != null && !line.equals(System.lineSeparator())) {
                    listWordDefault.add(line);
                    line = in.readLine();
                }

            }

            List<VersionRule> listRule = Utils.getListVerRule(request);
            VersionRule versionRule = new VersionRule();
            for (VersionRule obj : listRule) {
                if (obj.getStatus() == 0) {
                    versionRule = obj;
                }
            }

            String path = Utils.getPathRule(request, versionRule.getId());
            List<String> listFile = FileUtils.getListFileInFolder(path);
            String contentFile = FileUtils.readContentFile(path + File.separator + listFile.get(0));
            JSONObject objectRule = new JSONObject(contentFile);
            JSONArray arrayFirst = objectRule.getJSONArray("first");
            JSONArray arrayMain = objectRule.getJSONArray("main");
            JSONArray arrayLast = objectRule.getJSONArray("last");

            List<StenoRuleObject> listFirst = Utils.getStenoRule(arrayFirst);
            List<StenoRuleObject> listMain = Utils.getStenoRule(arrayMain);
            List<StenoRuleObject> listLast = Utils.getStenoRule(arrayLast);

            JSONObject object = new JSONObject();

            for (String s : listWordDefault) {
                List<String> listStructWord = Utils.listStructWord(s, listFirst, listMain);
                object.put(Utils.getKeySteno(listStructWord.get(0), listFirst) + Utils.getKeySteno(listStructWord.get(1), listMain) + Utils.getKeySteno(listStructWord.get(2), listLast), s);
            }

            DictStenoDTO dto = new DictStenoDTO();
            dto.setDictDefault(1);


            if (configDto.getValue().equals("3")) {
                nameDict.append(userDto.getUserId());
            } else {
                nameDict.append(userDto.getUserName());
            }
            nameDict.append("_").append(time);


            dto.setContent(nameDict + ".json");
            dto.setLength(object.length());
            dto.setDictName(nameDict.toString());
            dto.setStatus(0);
            dto.setCreUID(userDto.getUserId());
            dto.setCreDate(timeCreate);
            dto.setModUID(userDto.getUserId());
            dto.setModDate(timeCreate);

            SingletonServiceUtil.getDictStenoServiceInstance().save(dto);
            filePath = address + File.separator + "dict" + File.separator + nameDict + ".json";
            FileUtils.writeNewFile(filePath, object.toString());

            response.sendRedirect("/manager-dict-steno-list.html");

        }
    }

}
