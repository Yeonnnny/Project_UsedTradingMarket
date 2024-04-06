package net.kdigital.market.util.Base64;

import java.util.Base64;

public class Base64util {
    /**
     * Base64로 인코딩하는 함수
     * 
     * @param content
     * @return
     */
    public static String encode(byte[] content) {
        return Base64.getEncoder().encodeToString(content);
    }

    /**
     * Base64로 디코딩하는 함수
     * 
     * @param base64String
     * @return
     */
    public static byte[] decode(String base64String) {
        return Base64.getDecoder().decode(base64String);
    }
}
