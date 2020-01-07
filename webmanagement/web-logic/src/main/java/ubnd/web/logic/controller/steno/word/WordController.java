package ubnd.web.logic.controller.steno.word;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import ubnd.common.utils.FileUtils;
import ubnd.common.utils.SessionUtils;
import ubnd.common.utils.TimeUtils;
import ubnd.common.utils.UploadUtils;
import ubnd.core.data.obj.ListWord;
import ubnd.core.dto.UserDto;
import ubnd.core.web.common.WebConstants;
import ubnd.web.logic.utils.DownloadUtil;
import ubnd.web.logic.utils.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/word-manager.html")
public class WordController extends HttpServlet {
    private JSONObject newObj;
    private JSONArray array;
    private JSONArray newJsonArray;
    private String timeUpdate;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);
        String type = request.getParameter("type");
        if (type != null && type.equals("download")) {
            String filePath = FileUtils.pathFileTemplateWord(request);
            String name = "template_from-nhap-vao-du-lieu-am-tiet-Tieng-Viet.xlsx";
            DownloadUtil util = new DownloadUtil();
            util.downloadFile(response, name, filePath);
        } else if (type != null && type.equals("default")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                setData(request);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    JSONObject object1 = new JSONObject();
                    int idVersion = obj.getInt("id");
                    String userCreate = obj.getString("userCreate");
                    String timeCreateVersion = obj.getString("timeCreate");
                    int length = obj.getInt("length");
                    String nameFile = obj.getString("nameFile");
                    String userUpdate;
                    String time;
                    int status;
                    if (idVersion == id) {
                        userUpdate = userDto.getUserName();
                        time = timeUpdate;
                        status = 0;
                    } else {
                        userUpdate = obj.getString("userUpdate");
                        time = obj.getString("timeUpdate");
                        status = 1;
                    }

                    object1.put("id", idVersion);
                    object1.put("userCreate", userCreate);
                    object1.put("userUpdate", userUpdate);
                    object1.put("timeCreate", timeCreateVersion);
                    object1.put("timeUpdate", time);
                    object1.put("length", length);
                    object1.put("status", status);
                    object1.put("nameFile", nameFile);
                    newJsonArray.put(object1);
                }
                newObj.put("version", newJsonArray);

                writeFile(request, newObj);

            } catch (NullPointerException e) {
                e.getMessage();
            }

            response.sendRedirect("/word-manager.html");

        } else {
            List<ListWord> list = Utils.getListWord(request);
            request.setAttribute("tab", 0);
            request.setAttribute("list", list);
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/steno/word/word-manger.jsp");
            dispatcher.forward(request, response);
        }


    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);
        int error = -1;
        UploadUtils utils = new UploadUtils();
        String path = utils.writeOrUpdateFile(request);
        FileInputStream excelFile = new FileInputStream(new File(path));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet datatypeSheet = workbook.getSheetAt(0);
        Row row = datatypeSheet.getRow(1);
        int index = 0;
        for (Cell cell : row) {
            if (cell.getStringCellValue().equals(WebConstants.WORD)) {
                index = cell.getColumnIndex();
                break;
            }
        }
        if (index == 0) {
            error = 1;
        } else {
            StringBuilder data = new StringBuilder();
            int length = 0;
            for (Row r : datatypeSheet) {
                if (r.getRowNum() < 2) continue;
                Cell cell = r.getCell(index);
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_NUMERIC:
                        length++;
                        if (length == 1)
                            data.append(cell.getNumericCellValue());
                        else
                            data.append("\n").append(cell.getNumericCellValue());
                        break;
                    case Cell.CELL_TYPE_STRING:
                        if (cell.getStringCellValue().contains(" ")) {
                            error = 3;
                        } else {
                            length++;
                            if (length == 1)
                                data.append(cell.getStringCellValue());
                            else
                                data.append("\n").append(cell.getStringCellValue());
                        }

                        break;
                    default:
                        error = 2;
                        break;
                }
            }

            if (error == -1) {

                String time = String.valueOf(System.currentTimeMillis());
                String fileName = userDto.getUserName() + "_" + time + ".txt";
                String pathFile = FileUtils.getPathListWord(request) + File.separator + fileName;
                FileUtils.writeNewFile(pathFile, data.toString());

                setData(request);

                int max = 0;
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object1 = array.getJSONObject(i);
                    JSONObject obj = new JSONObject();
                    int id = object1.getInt("id");
                    obj.put("id", id);
                    obj.put("userCreate", object1.getString("userCreate"));
                    obj.put("userUpdate", object1.getString("userUpdate"));
                    obj.put("timeCreate", object1.getString("timeCreate"));
                    obj.put("timeUpdate", object1.getString("timeUpdate"));
                    obj.put("length", object1.getInt("length"));
                    obj.put("status", object1.getInt("status"));
                    obj.put("nameFile", object1.getString("nameFile"));
                    newJsonArray.put(obj);
                    if (max < id) {
                        max = id;
                    }
                }

                JSONObject obj = new JSONObject();
                obj.put("id", (max + 1));
                obj.put("userCreate", userDto.getUserName());
                obj.put("userUpdate", userDto.getUserName());
                obj.put("timeCreate", TimeUtils.getTimestamp(time));
                obj.put("timeUpdate", TimeUtils.getTimestamp(time));
                obj.put("length", length);
                obj.put("status", 1);
                obj.put("nameFile", fileName);
                newJsonArray.put(obj);
                newObj.put("version", newJsonArray);
                writeFile(request, newObj);
            }
        }

        FileUtils.deleteAllFile(FileUtils.getPathTemptWord(request));

        PrintWriter out = response.getWriter();
        if (error != -1) {
            switch (error) {
                case 1:
                    out.write("1");
//                    request.setAttribute("error", "File nhập vào không đúng định dạng vui lòng tải xuống mẫu");
                    break;
                case 2:
                    out.write("2");
//                    request.setAttribute("error", "Dữ liệu không hợp lệ. Vui lòng kiểm tra lại!");
                    break;
                case 3:
                    out.write("3");
//                    request.setAttribute("error", "Dữ liệu nhập vào chỉ được phép là âm tiết đơn. Vui lòng kiểm tra lại!");
                    break;
            }
        } else {
            out.write("-1");
//            response.sendRedirect("/word-manager.html");
        }

    }



    private void writeFile(HttpServletRequest request, JSONObject object) {
        File file = new File(FileUtils.getPathVersionWord(request));
        if (file.exists()) {
            file.delete();
        }
        FileUtils.writeNewFile(FileUtils.getPathVersionWord(request), object.toString());
    }

    private void setData(HttpServletRequest request) throws IOException {
        String dataFile = FileUtils.readContentFile(FileUtils.getPathVersionWord(request));
        JSONObject object = new JSONObject(dataFile);
        String timeCreate = object.getString("timeCreate");
        timeUpdate = String.valueOf(TimeUtils.getCurrentTimeStamp());
        boolean inherit = object.getBoolean("inherit");
        array = object.getJSONArray("version");
        newObj = new JSONObject();
        newObj.put("timeCreate", timeCreate);
        newObj.put("timeUpdate", timeUpdate);
        newObj.put("inherit", inherit);
        newJsonArray = new JSONArray();
    }

}

