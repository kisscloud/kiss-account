package com.kiss.account.utils;

import java.util.HashMap;
import java.util.Map;

public class ServiceStatusUtil {
    public static Map<String,Map<String,String>> statusMap = new HashMap<>();

    public static String getStatusValue(String lang,String identificatedCode) {
        return statusMap.get(lang).get(identificatedCode);
    }

}
