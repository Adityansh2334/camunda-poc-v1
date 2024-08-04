package com.camundi.process.config;
import java.util.Base64;

public class Base64Utils {
    public static String decode(String base64Encoded) {
        byte[] decodedBytes = Base64.getDecoder().decode(base64Encoded);
        return new String(decodedBytes);
    }
}
