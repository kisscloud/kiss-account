package com.kiss.account.utils;

import java.util.HashMap;
import java.util.Map;

public class DbEnumsUtil {
    public static Map<String,Map<String,String>> dbEnumsDao = new HashMap<>();

    public static String getStatusValue(String lang,String identificatedCode) {
        return dbEnumsDao.get(lang).get(identificatedCode);
    }

}
