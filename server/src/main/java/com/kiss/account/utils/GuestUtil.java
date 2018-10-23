package com.kiss.account.utils;

import com.alibaba.fastjson.JSONObject;
import com.kiss.account.entity.Guest;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 用户工具类，获取访问接口的用户的信息
 */
public class GuestUtil {

    /**
     * 获取访客ID
     *
     * @return Integer
     */
    public static Integer getGuestId() {
        Guest operator = ThreadLocalUtil.getGuest();

        if (operator == null) {
            return null;
        }
        return operator.getId();
    }

    /**
     * 获取访客姓名
     *
     * @return String
     */
    public static String getName() {
        Guest operator = ThreadLocalUtil.getGuest();
        if (operator == null) {
            return null;
        }

        return operator.getName();
    }

    /**
     * 设置访客信息
     *
     * @param operator Operator
     */
    public static void setGuest(Guest operator) {
        ThreadLocalUtil.setGuest(operator);
    }

    public static byte[] getGlobalMessage(HttpServletRequest request) throws IOException {
        InputStream in = request.getInputStream();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] bytes = new byte[4096];
        int n = 0;
        while ((n = in.read(bytes)) != -1) {
            output.write(bytes, 0, n);
        }

        String userMessage = new String(output.toByteArray(), "utf-8");
        JSONObject userMessageOb = JSONObject.parseObject(userMessage);

        if (userMessageOb != null) {
            Guest guest = new Guest();
            guest.setName(userMessageOb.getString("GateUsername"));
            guest.setId(userMessageOb.getInteger("GateUserId"));
            ThreadLocalUtil.setGuest(guest);
        }

        return bytes;
    }
}
