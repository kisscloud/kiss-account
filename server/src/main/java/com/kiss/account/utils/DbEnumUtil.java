package com.kiss.account.utils;

import java.util.HashMap;
import java.util.Map;

public class DbEnumUtil {
    public static Map<String,Map<String,String>> dbEnumsDao = new HashMap<>();

    public static String getValue(String key,String option) {
        String lang = ApplicationUtil.getHttpServletRequest().getHeader("X-LANGUAGE");
        return dbEnumsDao.get(lang == null ? "zh-CN" : lang).get(key + option);
    }

}
