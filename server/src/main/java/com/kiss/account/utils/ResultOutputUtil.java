package com.kiss.account.utils;

import output.ResultOutput;

public class ResultOutputUtil extends utils.ResultOutputUtil {

    public static ResultOutput error(int code) {
        ResultOutput resultOutput = new ResultOutput();
        resultOutput.setCode(code);
        return resultOutput;
    }

}
