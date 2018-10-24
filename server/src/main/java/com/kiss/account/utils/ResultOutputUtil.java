package com.kiss.account.utils;

import output.ResultOutput;

/**
 * 接口状态输出工具类 ,用来返回接口操作完成的状态
 * 如：
 * 成功返回：ResultOutputUtil.success()
 * 操作错误：ResultOutputUtil.error(CodeUtil.ERROR_CODE);
 */
public class ResultOutputUtil extends utils.ResultOutputUtil {

    public static ResultOutput error(int code) {

        ResultOutput resultOutput = new ResultOutput();
        resultOutput.setCode(code);
        return resultOutput;
    }

}
