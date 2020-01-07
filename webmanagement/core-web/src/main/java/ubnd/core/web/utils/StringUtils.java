package ubnd.core.web.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class StringUtils {
    public static String stringToSHA256(String originalString) {
        String sha256hex = DigestUtils.sha256Hex(originalString);
        return sha256hex;
    }
}
