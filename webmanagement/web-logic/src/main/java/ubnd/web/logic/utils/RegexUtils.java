package ubnd.web.logic.utils;

import ubnd.common.utils.ConvertUnsigned;
import ubnd.core.data.obj.StenoObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class RegexUtils {
    public static boolean regexSpace(String s) {
        return s.equals(" ");
    }

    public static boolean regexPunctuation(String s) {
        s = String.valueOf(s.charAt(0));
        if (!regexChar(s))
            return !regexNumber(s);
        return false;
    }

    public static boolean regexChar(String s) {
        s = String.valueOf(s.charAt(0));
        ConvertUnsigned unsigned = new ConvertUnsigned();
        String regex = "[a-zA-Z]";
        return unsigned.ConvertString(s).matches(regex);
    }

    public static boolean regexCharUpperCase(String s) {
        s = String.valueOf(s.charAt(0));
        ConvertUnsigned unsigned = new ConvertUnsigned();
        String regex = "[A-Z]";
        return unsigned.ConvertString(s).matches(regex);
    }

    public static boolean regexNumber(String s) {
        s = String.valueOf(s.charAt(0));
        String regex = "[0-9]";
        return s.matches(regex);
    }

    public static String readContentFile(String path) throws IOException {
        StringBuilder data = new StringBuilder();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream(path);
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

    public static String getKeySteno(String s, ArrayList<StenoObject> arrayList) {
        String key = "";
        boolean check = true;
        for (StenoObject o : arrayList) {
            if (o.getValue().equals(s.toLowerCase())) {
                key = o.getKey();
                check = false;
                break;
            }
        }
        if (check) {
            return s;
        } else {
            return key;
        }
    }

    public static String getValueSteno(String s, ArrayList<StenoObject> arrayList) {
        String value = "";
        boolean check = true;
        for (StenoObject o : arrayList) {
            if (o.getKey().equals(s.toUpperCase())) {
                value = o.getValue();
                check = false;
                break;
            }
        }
        if (check) {
            return s;
        } else {
            return value;
        }
    }



    static boolean regexTypeOne(String s) {
        try {
            if (s.substring(0, 2).equals("{^") && s.substring(s.length() - 2).equals("^}")) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }
}
