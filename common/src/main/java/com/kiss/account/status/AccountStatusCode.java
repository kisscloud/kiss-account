package com.kiss.account.status;


import java.lang.reflect.Field;

public class AccountStatusCode extends status.Code {

    public static final Integer PERMISSION_MODULE_NOT_EXIST = 1101;
    public static final Integer ACCOUNT_EXIST = 1210;
    public static final Integer ACCOUNT_GROUP_EXIST = 1203;
    public static final Integer ACCOUNT_GROUP_NOT_EXIST = 1004;
    public static final Integer ACCOUNT_GROUP_NAME_EXIST = 1004;
    public static final Integer PUT_ACCOUNT_FAILED = 1005;
    public static final Integer ACCOUNT_NAME_EXIST = 1200;
    public static final Integer PUT_ACCOUNT_GROUP_FAILED = 1006;
    public static final Integer PUT_ACCOUNT_PASSWORD_FAILED = 1007;
    public static final Integer PUT_ACCOUNT_STATUS_FAILED = 1008;
    public static final Integer ROLE_EXIST = 1008;
    public static final Integer PUT_ROLE_FAILED = 1009;
    public static final Integer DELETE_ROLE_FAILED = 1010;
    public static final Integer PERMISSION_EXIST = 1011;
    public static final Integer PUT_PERMISSION_FAILD = 1012;
    public static final Integer PUT_PERMISSION_MODULE_FAILD = 1013;
    public static final Integer DELETE_PERMISSION_FAILED = 1014;
    public static final Integer DELETE_PERMISSION_MODULE_FAILED = 1015;
    public static final Integer PERMISSION_MODULE_EXIST = 1016;
    public static final Integer PERMISSION_MODULE_IS_NOT_EMPTY = 1017;
    public static final Integer USERNAME_EXIST = 1018;
    public static final Integer EMAIL_EXIST = 1019;
    public static final Integer MOBILE_EXIST = 1020;
    public static final Integer Account_EXIST = 1021;
    public static final Integer REQUEST_PARAMETER_ERROR = 2001;
    public static final Integer PERMISSION_CODE_IS_NOT_EMPTY = 2002;
    public static final Integer MODULE_ID_IS_NOT_NULL = 2003;
    public static final Integer SERVICE_ERROR = 3001;

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
//        Class<?> account = AccountStatusCode.class.getClass();
        Field field = AccountStatusCode.class.getDeclaredField("PERMISSION_CODE_IS_NOT_EMPTY");
        System.out.println(field.get(field.getName()));

    }
}
