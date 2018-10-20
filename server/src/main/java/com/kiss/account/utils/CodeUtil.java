package com.kiss.account.utils;

import org.springframework.stereotype.Component;
import status.CodeMessage;

import java.io.UnsupportedEncodingException;

/**
 * 态码常量，在接口返回数据时使用
 * 例如：
 * CodeUtil.SUCCESS = 200
 * CodeUtil.VALIDATE_ERROR = 1000
 */
@Component
public class CodeUtil extends CodeMessage {

    public CodeUtil() throws UnsupportedEncodingException {
    }
}
