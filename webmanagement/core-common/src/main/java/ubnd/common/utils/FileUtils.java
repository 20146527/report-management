package ubnd.common.utils;


import org.json.JSONArray;
import org.json.JSONObject;
import ubnd.core.dto.UserDto;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class FileUtils {

    /**
     * Get path folder template in front end
     *
     * @param request HttpServletRequest
     * @return path folder
     */
    public static String templatePath(HttpServletRequest request) {
        return getRealPath(request, "file");
    }


    /**
     * get path folder dict default
     *
     * @param request HttpServletRequest
     * @return string path
     */
    public static String getPathDict(HttpServletRequest request) {
        return templatePath(request) + File.separator + "dict";
    }


    /**
     * get path file save person dict
     *
     * @param request HttpServletRequest
     * @param userDto data person information
     * @return string path
     */
    public static String getPathFilePersonDict(HttpServletRequest request, UserDto userDto) {
        return templatePath(request) + File.separator + "dict" + File.separator + "person" + File.separator + userDto.getUserName() + File.separator + userDto.getUserName() + ".json";
    }

    public static String getPathFilePersonDictFolder(HttpServletRequest request, UserDto userDto) {
        return templatePath(request) + File.separator + "dict" + File.separator + "person" + File.separator + userDto.getUserName();
    }

    /**
     * get path file save person dict
     *
     * @param userDto data person information
     * @return string path
     */
    public static String getPersonDict(UserDto userDto) {
        return "file" + File.separator + "dict" + File.separator + "person" + File.separator + userDto.getUserName() + File.separator + "dict" + File.separator + "dict.json";
    }


    /**
     * get path file tempt when create file download
     *
     * @param request HttpServletRequest
     * @param userDto data person information
     * @return string path
     */
    public static String getTemptFile(HttpServletRequest request, UserDto userDto) {
        String name = "tempt_" + userDto.getUserName() + "_" + System.currentTimeMillis() + ".json";
        return templatePath(request) + File.separator + "dict" + File.separator + "person" + File.separator + userDto.getUserName() + File.separator + "tempt" + File.separator + name;
    }


    /**
     * get path folder save version person dict
     *
     * @param request HttpServletRequest
     * @param userDto data person information
     * @return String path
     */
    public static String getPathFolderVersionPersonDict(HttpServletRequest request, UserDto userDto) {
        return templatePath(request) + File.separator + "dict" + File.separator + "person" + File.separator + userDto.getUserName() + File.separator + "version";
    }

    /**
     * get path file save new version person dict
     *
     * @param request HttpServletRequest
     * @param userDto data person information
     * @return String path
     */
    public static String getPathNewVersionDict(HttpServletRequest request, UserDto userDto) {
        return getPathFolderVersionPersonDict(request, userDto) + File.separator + System.currentTimeMillis() + ".json";
    }


    /**
     * Write file if file dose not exits
     *
     * @param filePath File path save file
     * @param content  Content want to save
     */
    public static void writeNewFile(String filePath, String content) {
        File file = new File(filePath);

        if (!file.getParentFile().exists()) {
            if (file.getParentFile().mkdirs()) {
                System.out.println();
            }
        } else {
            if (file.exists()) {
                if (file.delete()) {
                    System.out.println();
                }
            }
        }


        try {
            if (file.createNewFile()) {
                Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
                out.append(content);
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Get path folder in webapp package in web-front-end module
     *
     * @param request HttpServletRequest
     * @param folder  name folder want to get path
     * @return String path
     */
    public static String getRealPath(HttpServletRequest request, String folder) {
        ServletContext context = request.getServletContext();
        return context.getRealPath(folder);
    }


    /**
     * get list file in the folder
     *
     * @param filePath path folder
     * @return List string path
     */
    public static List<String> getListFileInFolder(String filePath) {
        List<String> list = new ArrayList<>();

        try {
            File folder = new File(filePath);
            File[] listOfFiles = folder.listFiles();

            assert listOfFiles != null;
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    list.add(listOfFile.getName());
                }
            }

            list.sort((o1, o2) -> TimeUtils.convertTime(o2.substring(0, o2.indexOf("."))).compareTo(TimeUtils.convertTime(o1.substring(0, o1.indexOf(".")))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }


    /**
     * Get path folder save word
     *
     * @param request HttpServletRequest
     * @return path folder
     */
    private static String getPathWord(HttpServletRequest request) {
        return templatePath(request) + File.separator + "word";
    }

    /**
     * Get path folder save template word
     *
     * @param request HttpServletRequest
     * @return path folder
     */
    public static String getPathListWord(HttpServletRequest request) {
        return getPathWord(request) + File.separator + "txt";
    }

    public static String getPathTemptWord(HttpServletRequest request) {
        return getPathWord(request) + File.separator + "tempt";
    }

    public static String getPathVersionWord(HttpServletRequest request) {
        return getPathWord(request) + File.separator + "version.json";
    }

    /**
     * Read content file
     *
     * @param path String path file
     * @return content file
     * @throws IOException
     */

    public static String readContentFile(String path) throws IOException {
        StringBuilder data = new StringBuilder();
        FileInputStream inputStream = new FileInputStream(path);
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String str;
            while ((str = in.readLine()) != null) {
                data.append(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
            inputStream.close();
        } finally {
            inputStream.close();
        }

        return data.toString();

    }

    public static String pathFileTemplateWord(HttpServletRequest request) {
        return getPathWord(request) + File.separator + "template" + File.separator +  "template_from-nhap-vao-du-lieu-am-tiet-Tieng-Viet.xlsx";
    }

    /**
     * delete all file in folder
     *
     * @param path String path folder
     */
    public static void deleteAllFile(String path) {
        File file = new File(path);
        File[] list = file.listFiles();
        assert list != null;
        for (File f : list) {
            if (f.delete()) {
                System.out.println("Delete file" + f.getName());
            }
        }
    }

    public static String getPathNonameSteno(HttpServletRequest request) {
        return getRealPath(request, "file") + File.separator + "reportstneo" + File.separator + "noname" + File.separator;
    }

    public static String getStringPathNonameSteno() {
        return "reportstneo" + File.separator + "noname";
    }

    public static String getTemptPath(HttpServletRequest request) {
        return getRealPath(request, "file") + File.separator + "temp";
    }


    /**
     * get tree Json object file in the folder
     *
     * @param request HttpServletRequest
     * @return Json object
     */
    public static String getObjectFolderFile(HttpServletRequest request) {
        String f = FileUtils.getRealPath(request, "file");
        JSONObject object = new JSONObject();
        object.put("name", "file");
        object.put("type", "folder");
        object.put("path", "file");
        JSONArray jsonArray = new JSONArray();
        put(jsonArray, f, "file");
        object.put("items", jsonArray);

        return object.toString();

    }

    /**
     * get list folder in path
     *
     * @param files file path
     * @return list folder
     */
    private static String[] listFile(File files) {
        return files.list((current, name) -> new File(current, name).isDirectory());
    }

    /**
     * get list file in path
     *
     * @param path string file path
     * @return list file
     */
    public static String[] getListFile(String path) {
        File files = new File(path);
        return files.list((current, name) -> new File(current, name).isFile());
    }

    /**
     * put item in array tree folder
     *
     * @param array  JsonArray item folder in parent folder
     * @param f      path file parent folder
     * @param folder parent folder
     */
    private static void put(JSONArray array, String f, String folder) {
        File file = new File(f);
        String[] directories = listFile(file);
        for (String s : directories) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", s);
            jsonObject.put("type", "folder");
            String folderPath = folder + "/" + s;
            jsonObject.put("path", folderPath);
            JSONArray jsonArray = new JSONArray();
            String files = f + File.separator + s;
            put(jsonArray, files, folderPath);
            jsonObject.put("items", jsonArray);
            array.put(jsonObject);
        }
    }

    /**
     * Create new folder
     *
     * @param path path folder want create
     */
    public static void createFolder(String path) {
        File theDir = new File(path);
        // if the directory does not exist, create it
        if (!theDir.exists()) {
            try {
                theDir.mkdir();
            } catch (SecurityException se) {
                se.printStackTrace();
            }
        }

    }

    /**
     * Write file if file dose not exits And update if file exits
     *
     * @param path    full path file
     * @param content Content want to save
     */
    public static void writeOrUpdateFile(String path, String content) {
        File file = new File(path);
        boolean isExist = file.exists();
        if (isExist) {
            if (file.delete())
                writeNewFile(path, content);
        } else {
            writeNewFile(path, content);
        }
    }

    /**
     * get Name file
     *
     * @param s file name include extention
     * @return
     */
    public static String getNameFile(String s) {
        int i = s.lastIndexOf(".");
        return s.substring(0, i);
    }

    /**
     * get name duplicate
     *
     * @param s name file
     * @return name
     */
    public static String checkDuplicateFileName(String s) {
        String name = getNameFile(s);
        int i = name.lastIndexOf(" ");
        String string = name.substring(i + 1);
        String regex = "[(][1-9][)]";
        if (string.matches(regex)) {
            return name.substring(0, i);
        }
        return name;
    }

    public static void renameDuplicateFile(String[] listFile, StringBuilder nameFile) {
        int count = 0;
        for (String s : listFile) {
            if (FileUtils.getNameFile(s).equals(nameFile.toString())) {
                count++;
            } else {
                if (FileUtils.checkDuplicateFileName(s).equals(nameFile.toString())) {
                    count++;
                }
            }
        }
        if (count > 0) {
            nameFile.append(" ").append("(").append(count).append(")");
        }

    }


}
