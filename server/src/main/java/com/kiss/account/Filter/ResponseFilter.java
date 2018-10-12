package com.kiss.account.Filter;

import com.alibaba.fastjson.JSONObject;
import com.kiss.account.out.Message;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseFilter implements InnerFilter {

    private ResponseWrapper responseWrapper;

    public ResponseFilter (ResponseWrapper responseWrapper) {
        this.responseWrapper = responseWrapper;
    }
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response,InnerFilterChain filterChain) {
        filterChain.doFilter(request,response,filterChain);
        byte[] bytes = responseWrapper.getBytes();
        try {
            String responseMsg = new String(bytes,"utf-8");
            JSONObject jsonObject = JSONObject.parseObject(responseMsg);
            String lang = request.getHeader("X-LANGUAGE");
            if(!StringUtils.isEmpty(lang) && !StringUtils.isEmpty(jsonObject.getInteger("code")) && StringUtils.isEmpty(jsonObject.getString("message"))) {
                String message = Message.getMessage(lang,jsonObject.getInteger("code"));
                jsonObject.put("message",message);
                bytes = jsonObject.toJSONString().getBytes();
            }
        } catch (Exception e) {

        } finally {
            try {
                response.getOutputStream().write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
