package com.kiss.account.utils;

import java.util.HashMap;
import java.util.Map;

public class DbEnumsUtil {
    public static Map<String,Map<String,String>> dbEnumsDao = new HashMap<>();

    public static String getStatusValue(String identificatedCode) {
        String lang = ApplicationUtil.getHttpServletRequest().getHeader("X-LANGUAGE");
        return dbEnumsDao.get(lang == null ? "zh-CN" : lang).get(identificatedCode);
    }

}
