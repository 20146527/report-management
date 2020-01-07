package ubnd.common.utils;

import org.json.JSONObject;

import java.util.Iterator;

public class ObjectUtils {

    public static JSONObject mergerObject(String... strings) {
        JSONObject[] objs = new JSONObject[strings.length];
        JSONObject merged = new JSONObject();
        int i = 0;

        for (String s : strings) {
            JSONObject object = new JSONObject(s);
            objs[i] = object;
            i++;
        }

        for (JSONObject obj : objs) {
            Iterator it = obj.keys();
            while (it.hasNext()) {
                String key = (String) it.next();
                merged.put(key, obj.get(key));
            }
        }

        return merged;
    }


    public static String merger(String s1, String s2) {
        JSONObject object1 = new JSONObject(s1);
        JSONObject object2 = new JSONObject(s2);
        if (object1.length() == 0 && object2.length() > 0) {
            return s2;
        } else if (object2.length() == 0 && object1.length() > 0) {
            return s1;
        } else {
            return s1.substring(0, s1.length() - 1) + "," + s2.substring(1);
        }

    }

}
