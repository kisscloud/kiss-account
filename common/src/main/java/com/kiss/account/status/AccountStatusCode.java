package com.kiss.account.status;

public class AccountStatusCode extends status.CodeEnums {

    public static final Integer ACCOUNT_NOT_EXIST = 1001;
    public static final Integer PUT_ACCOUNT_FAILED = 1002;


    public static final Integer ACCOUNT_GROUP_NAME_EXIST = 1001;
    public static final Integer PUT_ACCOUNT_GROUP_FAILED = 1006;
    public static final Integer NOT_EMPTY_GROUP = 1009;
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
    public static final Integer ACCOUNT_PASSWORD_ERROR = 1021;
    public static final Integer REQUEST_PARAMETER_ERROR = 2001;
    public static final Integer PERMISSION_CODE_IS_NOT_EMPTY = 2002;
    public static final Integer MODULE_ID_IS_NOT_NULL = 2003;
    public static final Integer SERVICE_ERROR = 3001;
    public static final Integer LOGIN_STATUS_INVALID = 3002;
    public static final Integer ROLE_DATA_PERMISSION_PATTERN_ERROR = 4001;
    public static final Integer ROLE_DATA_PERMISSION_ADD_FAILED = 4002;

    public static final Integer CREATE_CLIENT_FAILED = 5001;
    public static final Integer UPDATE_CLIENT_FAILED = 5002;
    public static final Integer DELETE_CLIENT_FAILED = 5003;
    public static final Integer CLIENT_IS_NOT_EXIST = 5004;
    public static final Integer CREATE_CLIENT_MODULE_FAILED = 5005;
    public static final Integer UPDATE_CLIENT_MODULE_FAILED = 5006;
    public static final Integer CLIENT_ID_ERROR = 5007;
    public static final Integer CLIENT_SECRET_ERROR = 5007;

}
