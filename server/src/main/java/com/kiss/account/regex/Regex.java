package com.kiss.account.regex;

import java.util.regex.Pattern;

public class Regex {

    public static final String INTEFACE_PERMISSION = "^(post|get|put|delete)@.+";
    public static final String PAGE_PERMISSION = "^page@[a-zA-Z0-9_\\-\\/&]+";
    public static final String COMPONENT_PERMISSION = "^component@[a-zA-Z0-9_\\-\\/&]+";
}
