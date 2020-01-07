package ubnd.web.logic.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import ubnd.common.utils.FileUtils;
import ubnd.common.utils.ObjectUtils;
import ubnd.core.data.obj.*;
import ubnd.core.dto.ConfigDto;
import ubnd.core.dto.UserDto;
import ubnd.core.service.DictStenoService;
import ubnd.core.service.impl.DictStenoServiceImpl;
import ubnd.core.web.utils.SingletonServiceUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

public class Utils {
    // Function to remove duplicates from an ArrayList
    public static <T> void removeDuplicates(List<T> list) {
        HashSet<T> hashSet = new HashSet<>(list);
        // Clear the list
        list.clear();

        // add the elements of set
        // with no duplicates to the list
        list.addAll(hashSet);
    }

    public static ConfigDto getDto(String title) {
        Map<String, Object> mapProperty = new HashMap<>();
        mapProperty.put("type", "steno");
        mapProperty.put("title", title);
        List<ConfigDto> configDtoList = SingletonServiceUtil.getConfigServiceInstance().findByProperty(mapProperty, null, null, null, null);
        return configDtoList.get(0);
    }

    public static void writeDict(HttpServletRequest request, UserDto userDto) throws IOException {

        //Dict default
        DictStenoService service = new DictStenoServiceImpl();
        DictObject object = service.getDefaultDict();
        String nameDefaultDict = object.getDto().getContent();


        //Write file dict for person
        String checkDictCustom = Utils.getDto("checkDictCustom").getValue();
        String path = FileUtils.getPathFilePersonDictFolder(request, userDto) + File.separator + "dict";
        String pathDefaultDict = FileUtils.getPathDict(request) + File.separator + nameDefaultDict;
        String contentFileDictDefault = FileUtils.readContentFile(pathDefaultDict);
        if (checkDictCustom.equals("off")) {
            FileUtils.writeNewFile(path + File.separator + "dict.json", contentFileDictDefault);
        } else {
            String pathDictPerson = FileUtils.getPathFilePersonDict(request, userDto);
            String contentPersonDict = FileUtils.readContentFile(pathDictPerson);
            String dictPriority = Utils.getDto("dictPriority").getValue();
            String data = "{}";
            if (dictPriority.equals("1")) {
                data = ObjectUtils.merger(contentPersonDict, contentFileDictDefault);
            } else if (dictPriority.equals("2")) {
                data = ObjectUtils.merger(contentFileDictDefault, contentPersonDict);
            }
            FileUtils.writeNewFile(path + File.separator + "dict.json", data);
        }
    }


    public static JSONObject getContentRule(String path, int numberSheet) throws IOException {
        FileInputStream excelFile = new FileInputStream(new File(path));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet datatypeSheet = workbook.getSheetAt(numberSheet);
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

        JSONObject object = new JSONObject();
        for (int i = 0; i < list.size(); i++) {
            object.put(list.get(i), list1.get(i));
        }
        return object;
    }

    public static List<VersionRule> getListVerRule(HttpServletRequest request) throws IOException {
        List<VersionRule> listRule = new ArrayList<>();
        String path = FileUtils.templatePath(request) + File.separator + "steno" + File.separator + "rule" + File.separator + "rule.json";
        JSONObject object = new JSONObject(FileUtils.readContentFile(path));
        JSONArray array = object.getJSONArray("version");
        for (int i = 0; i < array.length(); i++) {
            JSONObject object1 = array.getJSONObject(i);
            if (object1.getInt("status") != 1) {
                listRule.add(new VersionRule(
                        object1.getInt("id"),
                        object1.getString("name"),
                        object1.getString("createBy"),
                        object1.getString("folder"),
                        object1.getString("timeCreate"),
                        object1.getString("timeUpdate"),
                        object1.getString("updateBy"),
                        object1.getInt("status")
                ));
            }
        }
        listRule.sort((Comparator.comparingInt(VersionRule::getStatus)));
        return listRule;
    }

    public static List<StenoObject> listRule(String s) throws IOException {
        String data = FileUtils.readContentFile(s);
        List<StenoObject> list = new ArrayList<>();
        JSONObject object = new JSONObject(data);
        Iterator<String> iterator = object.keys();
        while (iterator.hasNext()) {
            String value = iterator.next();
            String key = object.getString(value);
            list.add(new StenoObject(key, value));
        }

        return list;
    }

    public static void putData(String path, int numberSheet, JSONArray array, UserDto userDto, Timestamp timestamp) throws IOException {
        JSONObject content = getContentRule(path, numberSheet);
        Iterator<String> iterator = content.keys();
        int id = 0;
        while (iterator.hasNext()) {
            String value = iterator.next();
            String key = content.getString(value);
            JSONObject objF = new JSONObject();
            objF.put("creDate", timestamp);
            objF.put("idCode", id);
            objF.put("modeDate", timestamp);
            objF.put("value", value);
            objF.put("key", key);
            objF.put("status", 0);
            objF.put("creUser", userDto.getUserName());
            objF.put("modUser", userDto.getUserName());
            array.put(objF);
            id++;
        }
    }

    public static List<StenoRuleObject> getStenoRule(JSONArray array) {
        List<StenoRuleObject> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            if (object.getInt("status") != 1) {
                list.add(new StenoRuleObject(
                        object.getInt("idCode"),
                        object.getString("key"),
                        object.getString("value"),
                        object.getString("creDate"),
                        object.getString("modUser"),
                        object.getString("creUser"),
                        object.getString("modeDate"),
                        object.getInt("status")));
            }
        }
        return list;
    }

    public static JSONArray updateKeySteno(JSONObject objectRule, int idCode, String keySteno, UserDto userDto, Timestamp timeUpdate, int type) {
        String name = "";
        if (type == 1) {
            name = "first";
        } else if (type == 2) {
            name = "main";
        } else if (type == 3) {
            name = "last";
        }
        List<StenoRuleObject> list = Utils.getStenoRule(objectRule.getJSONArray(name));
        JSONArray array = new JSONArray();
        for (StenoRuleObject object : list) {
            JSONObject obj = new JSONObject();
            if (idCode == object.getId()) {
                obj.put("idCode", object.getId());
                obj.put("key", keySteno);
                obj.put("value", object.getValue());
                obj.put("creDate", object.getCreDate());
                obj.put("modUser", userDto.getUserName());
                obj.put("creUser", object.getCreUser());
                obj.put("modeDate", timeUpdate);
                obj.put("status", object.getStatus());
            } else {
                obj.put("idCode", object.getId());
                obj.put("key", object.getKey());
                obj.put("value", object.getValue());
                obj.put("creDate", object.getCreDate());
                obj.put("modUser", object.getModUser());
                obj.put("creUser", object.getCreUser());
                obj.put("modeDate", object.getModeDate());
                obj.put("status", object.getStatus());
            }
            array.put(obj);
        }
        return array;
    }


    public static JSONArray toggleRule(JSONObject objectRule, int idCode, UserDto userDto, Timestamp timeUpdate, int type) {
        String name = "";
        if (type == 1) {
            name = "first";
        } else if (type == 2) {
            name = "main";
        } else if (type == 3) {
            name = "last";
        }
        List<StenoRuleObject> list = Utils.getStenoRule(objectRule.getJSONArray(name));
        JSONArray array = new JSONArray();
        for (StenoRuleObject object : list) {
            JSONObject obj = new JSONObject();
            if (idCode == object.getId()) {
                obj.put("idCode", object.getId());
                obj.put("key", object.getKey());
                obj.put("value", object.getValue());
                obj.put("creDate", object.getCreDate());
                obj.put("modUser", userDto.getUserName());
                obj.put("creUser", object.getCreUser());
                obj.put("modeDate", timeUpdate);
                if (object.getStatus() == 0) {
                    obj.put("status", 2);
                } else if (object.getStatus() == 2) {
                    obj.put("status", 0);
                }
            } else {
                obj.put("idCode", object.getId());
                obj.put("key", object.getKey());
                obj.put("value", object.getValue());
                obj.put("creDate", object.getCreDate());
                obj.put("modUser", object.getModUser());
                obj.put("creUser", object.getCreUser());
                obj.put("modeDate", object.getModeDate());
                obj.put("status", object.getStatus());
            }
            array.put(obj);
        }
        return array;
    }

    public static JSONArray deleteRule(JSONObject objectRule, int idCode, UserDto userDto, Timestamp timeUpdate, int type) {
        String name = "";
        if (type == 1) {
            name = "first";
        } else if (type == 2) {
            name = "main";
        } else if (type == 3) {
            name = "last";
        }
        List<StenoRuleObject> list = Utils.getStenoRule(objectRule.getJSONArray(name));
        JSONArray array = new JSONArray();
        for (StenoRuleObject object : list) {
            JSONObject obj = new JSONObject();
            if (idCode == object.getId()) {
                obj.put("idCode", object.getId());
                obj.put("key", object.getKey());
                obj.put("value", object.getValue());
                obj.put("creDate", object.getCreDate());
                obj.put("modUser", userDto.getUserName());
                obj.put("creUser", object.getCreUser());
                obj.put("modeDate", timeUpdate);
                obj.put("status", 1);

            } else {
                obj.put("idCode", object.getId());
                obj.put("key", object.getKey());
                obj.put("value", object.getValue());
                obj.put("creDate", object.getCreDate());
                obj.put("modUser", object.getModUser());
                obj.put("creUser", object.getCreUser());
                obj.put("modeDate", object.getModeDate());
                obj.put("status", object.getStatus());
            }
            array.put(obj);
        }
        return array;
    }

    public static List<ListWord> getListWord(HttpServletRequest request) throws IOException {
        String content = FileUtils.readContentFile(FileUtils.getPathVersionWord(request));
        JSONObject object = new JSONObject(content);
        JSONArray jsonArray = object.getJSONArray("version");
        List<ListWord> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            int id = obj.getInt("id");
            String userCreate = obj.getString("userCreate");
            String userUpdate = obj.getString("userUpdate");
            String timeCreate = obj.getString("timeCreate");
            String timeUpdate = obj.getString("timeUpdate");
            int length = obj.getInt("length");
            int status = obj.getInt("status");
            String nameFile = obj.getString("nameFile");
            list.add(new ListWord(id, userCreate, userUpdate, timeCreate, timeUpdate, length, status, nameFile));

        }
        list.sort((Comparator.comparingInt(ListWord::getStatus)));
        return list;
    }

    public static String getConstructWord(String input, List<StenoRuleObject> data) {
        input = input.toLowerCase();
        StringBuilder result = new StringBuilder();
        StringBuilder tempt = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char s = input.charAt(i);
            tempt.append(s);
            for (StenoRuleObject object : data) {
                if (object.getValue().equals(tempt.toString())) {
                    result = new StringBuilder();
                    result.append(tempt.toString());
                }
            }
        }
        return result.toString();
    }

    public static List<String> listStructWord(String word, List<StenoRuleObject> amdau, List<StenoRuleObject> amgiua) {
        word = word.trim().replace("\n", "");
        String dau = getConstructWord(word, amdau);
        word = word.substring(dau.length());
        String giua = getConstructWord(word, amgiua);
        String cuoi = word.substring(giua.length());
        List<String> list = new ArrayList<>();
        list.add(dau);
        list.add(giua);
        list.add(cuoi);
        return list;
    }

    public static String getPathRule(HttpServletRequest request, int id) throws IOException {
        List<VersionRule> listRule = Utils.getListVerRule(request);
        VersionRule obj = new VersionRule();
        for (VersionRule rule : listRule) {
            if (rule.getId() == id) {
                obj = rule;
                break;
            }
        }
        return FileUtils.templatePath(request) + File.separator + "steno" + File.separator + "rule" + File.separator + obj.getFolder();
    }

    public static String getKeySteno(String word, List<StenoRuleObject> data) throws IOException {
        String result = "";
        for (StenoRuleObject object : data) {
            if (object.getValue().equals(word)) {
                result = object.getKey();
            }
        }
        return result;

    }
}
