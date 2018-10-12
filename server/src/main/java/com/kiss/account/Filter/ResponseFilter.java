package com.kiss.account.Filter;

import com.alibaba.fastjson.JSONObject;
import com.kiss.account.out.Message;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

@Component
@WebFilter(filterName = "responseFilter",urlPatterns = "/*")
public class ResponseFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse)response);
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        chain.doFilter(request,responseWrapper);

        byte[] bytes = responseWrapper.getBytes();
        String responseMsg = new String(bytes,"utf-8");
        try {
            JSONObject jsonObject = JSONObject.parseObject(responseMsg);
            String lang = httpServletRequest.getHeader("X-LANGUAGE");
            if(!StringUtils.isEmpty(lang) && !StringUtils.isEmpty(jsonObject.getInteger("code")) && StringUtils.isEmpty(jsonObject.getString("message"))) {
                String message = Message.getMessage(lang,jsonObject.getInteger("code"));
                jsonObject.put("message",message);
                bytes = jsonObject.toJSONString().getBytes();
            }
        } catch (Exception e) {

        } finally {
            response.getOutputStream().write(bytes);
        }
    }

    @Override
    public void destroy() {

    }
}
