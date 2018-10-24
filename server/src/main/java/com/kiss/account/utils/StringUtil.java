package com.kiss.account.utils;

import java.util.UUID;

public class StringUtil {

    public static String randomUUIDString () {

        return UUID.randomUUID().toString().replace("-","");
    }
}
