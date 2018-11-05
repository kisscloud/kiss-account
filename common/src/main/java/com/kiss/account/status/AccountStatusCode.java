package com.kiss.account.status;

public class AccountStatusCode extends status.CodeEnums {

    public static final Integer ACCOUNT_NOT_EXIST = 1001;
    public static final Integer PUT_ACCOUNT_FAILED = 1002;
    public static final Integer ACCOUNT_PASSWORD_ERROR = 1003;
    public static final Integer PUT_ACCOUNT_PASSWORD_FAILED = 1004;
    public static final Integer PUT_ACCOUNT_STATUS_FAILED = 1005;
    public static final Integer ACCOUNT_USERNAME_NOT_EMPTY = 1006;
    public static final Integer ACCOUNT_USERNAME_EXIST = 1007;
    public static final Integer ACCOUNT_NAME_NOT_EMPTY = 1008;
    public static final Integer ACCOUNT_NAME_EXIST = 1009;
    public static final Integer ACCOUNT_MOBILE_NOT_EMPTY = 1010;
    public static final Integer ACCOUNT_MOBILE_EXIST = 1011;
    public static final Integer ACCOUNT_EMAIL_NOT_EMPTY = 1012;
    public static final Integer ACCOUNT_EMAIL_EXIST = 1013;
    public static final Integer ACCOUNT_GROUPID_NOT_EMPTY = 1014;
    public static final Integer ACCOUNT_PASSWORD_NOT_EMPTY = 1015;
    public static final Integer ACCOUNT_PASSWORD_SIZE_NOT_LESS_THAN_EIGHT= 1016;
    public static final Integer ACCOUNT_PASSWORD_SIZE_NOT_MORE_THAN_THIRTY_TWO= 1017;
    public static final Integer ACCOUNT_STATUS_NOT_EMPTY= 1018;
    public static final Integer ACCOUNT_ID_NOT_EMPTY= 1019;

    public static final Integer ACCOUNT_GROUP_NAME_EXIST = 1201;
    public static final Integer PUT_ACCOUNT_GROUP_FAILED = 1202;
    public static final Integer ACCOUNT_GROUP_NOT_EXIST = 1203;
    public static final Integer ACCOUNT_GROUP_NAME_NOT_EMPTY = 1204;
    public static final Integer ACCOUNT_GROUP_PARENT_NOT_EMPTY = 1205;
    public static final Integer ACCOUNT_GROUP_PARENT_NOT_EXIST = 1206;
    public static final Integer NOT_EMPTY_GROUP = 1207;

    public static final Integer PUT_ROLE_FAILED = 4001;
    public static final Integer DELETE_ROLE_FAILED = 4002;
    public static final Integer ROLE_NOT_EXIST = 4003;
    public static final Integer ROLE_NAME_NOT_EMPTY = 4004;
    public static final Integer ROLE_NAME_EXIST = 4005;


    public static final Integer PUT_PERMISSION_FAILD = 5001;
    public static final Integer DELETE_PERMISSION_FAILED = 5002;
    public static final Integer PERMISSION_NOT_EXIST = 5003;
    public static final Integer PERMISSION_NAME_NOT_EMPTY = 5004;
    public static final Integer PERMISSION_NAME_EXIST = 5005;
    public static final Integer PERMISSION_CODE_NOT_EMPTY = 5006;
    public static final Integer PERMISSION_CODE_STYLE_ERROR = 5007;
    public static final Integer PERMISSION_CODE_EXIST = 5008;
    public static final Integer PERMISSION_TYPE_NOT_EMPTY = 5009;
    public static final Integer PERMISSION_TYPE_ERROR = 5010;



    public static final Integer PUT_PERMISSION_MODULE_FAILD = 6001;
    public static final Integer DELETE_PERMISSION_MODULE_FAILED = 6002;
    public static final Integer PERMISSION_MODULE_NOT_EMPTY = 6003;
    public static final Integer PERMISSION_MODULE_NOT_EXIST = 6004;
    public static final Integer PERMISSION_MODULE_PARENT_NOT_EMPTY = 6005;
    public static final Integer PERMISSION_MODULE_PARENT_NOT_EXIST = 6006;
    public static final Integer PERMISSION_MODULE_NAME_NOT_EMPTY = 6007;
    public static final Integer PERMISSION_MODULE_NAME_EXIST = 6008;



    public static final Integer ROLE_DATA_PERMISSION_PATTERN_ERROR = 7001;


    public static final Integer CREATE_CLIENT_FAILED = 8001;
    public static final Integer UPDATE_CLIENT_FAILED = 8002;
    public static final Integer DELETE_CLIENT_FAILED = 8003;
    public static final Integer CLIENT_IS_NOT_EXIST = 8004;
    public static final Integer CLIENT_ID_ERROR = 8005;
    public static final Integer CLIENT_SECRET_ERROR = 8006;
    public static final Integer CLIENT_AUTHORIZATION_NOT_ENOUGH = 8006;
    public static final Integer CLIENT_AUTHORIZATION_EXPIRED = 8007;
    public static final Integer CLIENT_ID_NOT_EMPTY = 8008;
    public static final Integer CLIENT_ID_NOT_EXIST = 8009;
    public static final Integer CLIENT_NAME_NOT_EMPTY = 8010;
    public static final Integer CLIENT_STATUS_NOT_EMPTY = 8011;

    public static final Integer CREATE_CLIENT_MODULE_FAILED = 9001;
    public static final Integer CLIENT_MODULEID_NOT_EXIST = 9002;
    public static final Integer CLIENT_MODULEID_NOT_EMPTY = 9003;

    public static final Integer SERVICE_ERROR = 3001;
    public static final Integer LOGIN_STATUS_INVALID = 3002;
    public static final Integer DATA_BIND_ERROR = 3003;

}
