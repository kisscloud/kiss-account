package com.kiss.account.status;

public class AccountStatusCode extends status.CodeEnums {

    public static final Integer ACCOUNT_NOT_EXIST = 1001;
    public static final Integer PUT_ACCOUNT_FAILED = 1002;
    public static final Integer ACCOUNT_PASSWORD_ERROR = 1003;
    public static final Integer PUT_ACCOUNT_PASSWORD_FAILED = 1004;
    public static final Integer PUT_ACCOUNT_STATUS_FAILED = 1005;

    public static final Integer ACCOUNT_GROUP_NAME_EXIST = 1201;
    public static final Integer PUT_ACCOUNT_GROUP_FAILED = 1202;

    public static final Integer NOT_EMPTY_GROUP = 2001;

    public static final Integer PUT_ROLE_FAILED = 4001;
    public static final Integer DELETE_ROLE_FAILED = 4002;

    public static final Integer PUT_PERMISSION_FAILD = 5001;
    public static final Integer DELETE_PERMISSION_FAILED = 5002;

    public static final Integer PUT_PERMISSION_MODULE_FAILD = 6001;
    public static final Integer DELETE_PERMISSION_MODULE_FAILED = 6002;
    public static final Integer PERMISSION_MODULE_IS_NOT_EMPTY = 6003;

    public static final Integer ROLE_DATA_PERMISSION_PATTERN_ERROR = 7001;

    public static final Integer CREATE_CLIENT_FAILED = 8001;
    public static final Integer UPDATE_CLIENT_FAILED = 8002;
    public static final Integer DELETE_CLIENT_FAILED = 8003;
    public static final Integer CLIENT_IS_NOT_EXIST = 8004;
    public static final Integer CLIENT_ID_ERROR = 8005;
    public static final Integer CLIENT_SECRET_ERROR = 8006;
    public static final Integer CLIENT_AUTHORIZATION_NOT_ENOUGH = 8006;

    public static final Integer CREATE_CLIENT_MODULE_FAILED = 9001;

    public static final Integer SERVICE_ERROR = 3001;
    public static final Integer LOGIN_STATUS_INVALID = 3002;

}
