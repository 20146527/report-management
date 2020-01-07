package ubnd.web.logic.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import ubnd.common.utils.FileUtils;
import ubnd.core.data.obj.DictPerson;
import ubnd.core.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ReadDictUtils {


    /**
     * Get object list person dict in folder person
     *
     * @param req     HttpServletRequest req
     * @param userDto Object information user (DTO)
     * @param index   position dict
     * @return Object dict among date create and content dict
     * @throws IOException file Exception
     */
    public static Object[] getDataPersonDict(HttpServletRequest req, UserDto userDto, int index) throws IOException {
        List<String> listFileDict;
        ArrayList<DictPerson> arrayList;
        String date = "";

        String folderVersion = FileUtils.getPathFolderVersionPersonDict(req, userDto);

        listFileDict = FileUtils.getListFileInFolder(folderVersion);
        arrayList = new ArrayList<>();

        if (listFileDict.size() > 0) {
            String data = FileUtils.readContentFile(folderVersion + File.separator + listFileDict.get(index));
            try {
                JSONObject object = new JSONObject(data);
                JSONArray array = object.getJSONArray("dict");
                date = object.getString("date");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject dict = array.getJSONObject(i);
                    String key = dict.getString("key");
                    String value = dict.getString("value");
                    int status = dict.getInt("status");
                    int id = dict.getInt("idCode");
                    Timestamp modeDate = Timestamp.valueOf(dict.getString("modeDate"));
                    Timestamp creDate = Timestamp.valueOf(dict.getString("creDate"));
                    if (status == 1) {
                        arrayList.add(new DictPerson(id, key, value, status, creDate, modeDate));
                    } else {
                        arrayList.add(new DictPerson(id, key, value, status, creDate, modeDate));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return new Object[]{date, arrayList};
    }

    /**
     * write new dict default
     *
     * @param content content file want write
     * @param req     HttpServletRequest
     * @param userDto Object information user (DTO)
     * @param array   array dict
     */
    public static void writerPersonDict(String content, HttpServletRequest req, UserDto userDto, JSONArray array) {
        FileUtils.writeNewFile(FileUtils.getPathNewVersionDict(req, userDto), content);

        File filePersonDict = new File(FileUtils.getPathFilePersonDict(req, userDto));
        if (filePersonDict.exists()) {
            if (filePersonDict.delete()) {
                System.out.println();
            }
        }
        JSONObject objPersonDict = new JSONObject();
        for (int i = 0; i < array.length(); i++) {
            JSONObject objDataDict = array.getJSONObject(i);
            int status = objDataDict.getInt("status");
            if (status == 0) {
                String key = objDataDict.getString("key");
                String value = objDataDict.getString("value");
                objPersonDict.put(key, value);
            }
        }

        FileUtils.writeNewFile(FileUtils.getPathFilePersonDict(req, userDto), objPersonDict.toString());

    }

    /**
     * push data from DictPerson to JSONObject
     *
     * @param person DictPerson
     * @return JSONObject
     */
    public static JSONObject pushDataToObject(DictPerson person) {
        JSONObject dictObj = new JSONObject();
        dictObj.put("key", person.getCodeSteno());
        dictObj.put("value", person.getWord());
        dictObj.put("status", person.getStatus());
        dictObj.put("creDate", person.getCreDate());
        dictObj.put("idCode", person.getIdCode());
        dictObj.put("modeDate", person.getModUpdate());
        return dictObj;
    }

    /**
     * Get JSONObject dict by path file
     *
     * @param pathFile path file
     * @return JSONObject
     * @throws IOException file Exception
     */
    public static JSONObject getObjDict(String pathFile) throws IOException {
        FileInputStream inputStream = new FileInputStream(pathFile);
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String str;
        StringBuilder data = new StringBuilder();

        while ((str = in.readLine()) != null) {
            data.append(str);
        }
        return new JSONObject(data.toString());
    }

    /**
     * get path person dict using
     *
     * @param request HttpServletRequest
     * @param userDto data person information
     * @return String path
     */
    public static JSONObject getDefaultDictPerson(HttpServletRequest request, UserDto userDto) throws IOException {
        String folderVersion = FileUtils.getPathFolderVersionPersonDict(request, userDto);
        List<String> listFileDict = FileUtils.getListFileInFolder(folderVersion);
        String dict = listFileDict.get(0);
        String pathFile = folderVersion + File.separator + dict;
        return getObjDict(pathFile);
    }
}
