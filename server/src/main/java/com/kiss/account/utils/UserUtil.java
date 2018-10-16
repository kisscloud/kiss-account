package com.kiss.account.utils;

import com.alibaba.fastjson.JSONObject;
import com.kiss.account.entity.Operator;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class UserUtil {
    public static String getUsername() {
        return ThreadLocalUtil.getUserMessage().getUsername();
    }

    public static Integer getUserId() {
        return ThreadLocalUtil.getUserMessage().getUserId();
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
            ThreadLocalUtil.setUserMessage(operator);
        }

        return bytes;
    }
}
