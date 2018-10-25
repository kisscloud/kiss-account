package com.kiss.account.filter;

import com.alibaba.fastjson.JSONObject;
import com.kiss.account.utils.CodeUtil;
import filter.InnerFilter;
import filter.InnerFilterChain;
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
    public void doFilter(HttpServletRequest request, HttpServletResponse response, InnerFilterChain filterChain) {

        filterChain.doFilter(request,response,filterChain);
        byte[] bytes = responseWrapper.getBytes();

        try {
            String responseMsg = new String(bytes,"utf-8");
            JSONObject jsonObject = JSONObject.parseObject(responseMsg);
            String lang = StringUtils.isEmpty(request.getHeader("X-LANGUAGE")) ? "zh-CN" : request.getHeader("X-LANGUAGE");
            if(jsonObject != null && !StringUtils.isEmpty(jsonObject.getInteger("code")) && StringUtils.isEmpty(jsonObject.getString("message"))) {
                String message = CodeUtil.getMessage(lang,jsonObject.get("code"));
                jsonObject.put("message",message);
                bytes = jsonObject.toJSONString().getBytes();
            }
        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            try {
                response.getOutputStream().write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
