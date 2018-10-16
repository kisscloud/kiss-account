package com.kiss.account.filter;

import com.kiss.account.utils.ThreadLocalUtil;
import com.kiss.account.utils.UserUtil;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter(filterName = "responseFilter",urlPatterns = "/*")
public class AccountFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        ResponseWrapper responseWrapper = new ResponseWrapper(httpServletResponse);
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        byte[] bytes = UserUtil.getGlobalMessage(httpServletRequest);
        RequestWrapper requestWrapper = new RequestWrapper(httpServletRequest,bytes);
        chain.doFilter(requestWrapper,responseWrapper);
        InnerFilterChain innerFilterChain = new InnerFilterChain();
        innerFilterChain.addFilter(new ResponseFilter(responseWrapper));
        innerFilterChain.doFilter(httpServletRequest,httpServletResponse,innerFilterChain);
        ThreadLocalUtil.remove();
    }

    @Override
    public void destroy() {

    }
}
