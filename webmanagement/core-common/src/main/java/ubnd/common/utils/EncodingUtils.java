package ubnd.common.utils;


import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncodingUtils {

    /**
     * Encode String to Base64 string byte
     *
     * @param token String input
     * @return Base64 string byte out put
     */
    public static String base64Encode(String token) {

        return Base64.getEncoder().encodeToString(token.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Decode Base64 string byte to String UTF-8
     *
     * @param token Base64 string byte
     * @return String UTF-8
     */
    public static String base64Decode(String token) {
        byte[] decodedBytes = Base64.getDecoder().decode(token);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }

    /**
     * convert from internal Java String format -> UTF-8
     *
     * @param s String input
     * @return String UTF-8 output
     */
    public String convertToUTF8(String s) {
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        return new String(bytes, StandardCharsets.UTF_8);

    }

    /**
     * Decode UTF-8 to unicode
     *
     * @param s String UTF-8 input
     * @return String unicode output
     */
    public String decodeUtf8(String s) {
        return new String(s.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }
}
