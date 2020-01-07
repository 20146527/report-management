package ubnd.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.codec.digest.DigestUtils;

public class StringUtils {

    /**
     * Convert string to string SHA256
     *
     * @param originalString String input
     * @return String SHA256 output
     */
    public static String stringToSHA256(String originalString) {
        return DigestUtils.sha256Hex(originalString);
    }

    /**
     * Format string json
     *
     * @param content String json
     * @return string json formatted
     */
    public static String formatStringJson(String content) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(content);
        return gson.toJson(je);
    }

}
