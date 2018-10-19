package com.kiss.account.utils;

import com.alibaba.fastjson.JSONObject;
import com.kiss.account.entity.Operator;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class UserUtil {
    public static String getUsername() {
        Operator operator = ThreadLocalUtil.getOperatorInfo();
        if (operator == null) {
            return null;
        }

        return operator.getUsername();
    }

    public static Integer getUserId() {
        Operator operator = ThreadLocalUtil.getOperatorInfo();

        if (operator == null) {
            return null;
        }
        return operator.getUserId();
    }

    public static void setUserInfo(Operator operator) {
        ThreadLocalUtil.setOperatorInfo(operator);
    }
    public static byte[] getGlobalMessage(HttpServletRequest request) throws IOException {

        InputStream in = request.getInputStream();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] bytes = new byte[4096];
        int n = 0;
        while ((n = in.read(bytes)) != -1) {
            output.write(bytes,0,n);
        }

        String userMessage = new String(output.toByteArray(),"utf-8");
        System.out.println(userMessage);
        JSONObject userMessageOb = JSONObject.parseObject(userMessage);

        if (userMessageOb != null) {
            Operator operator = new Operator();
            operator.setUsername(userMessageOb.getString("GateUsername"));
            operator.setUserId(userMessageOb.getInteger("GateUserId"));
            ThreadLocalUtil.setOperatorInfo(operator);
        }

        return bytes;
    }
}
