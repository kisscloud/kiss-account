package com.kiss.account.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据库常量工具类
 * 在 dbEnums 数据表中配置多语言，通过此工具类调用，通常在数据表的类型、状态等数字状态取对应文案的时候使用。
 */
public class DbEnumUtil {
    public static Map<String, Map<String, String>> dbEnumsDao = new HashMap<>();

    public static String getValue(String key, String option) {
        String lang = ApplicationUtil.getHttpServletRequest().getHeader("X-LANGUAGE");
        return dbEnumsDao.get(lang == null ? "zh-CN" : lang).get(key + option);
    }

}
