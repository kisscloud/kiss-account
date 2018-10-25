package com.kiss.account.filter;

import filter.GuestFilter;
import filter.InnerFilterChain;
import org.springframework.stereotype.Component;
import utils.ThreadLocalUtil;

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
        InnerFilterChain preFilterChain = new InnerFilterChain();

        if (!httpServletRequest.getRequestURI().contains("/login")) {
            GuestFilter userInfoFilter = new GuestFilter();
            preFilterChain.addFilter(userInfoFilter);
        }

        preFilterChain.doFilter(httpServletRequest,httpServletResponse,preFilterChain);
        chain.doFilter(httpServletRequest,responseWrapper);
        InnerFilterChain suffixFilterChain = new InnerFilterChain();
        suffixFilterChain.addFilter(new ResponseFilter(responseWrapper));
        suffixFilterChain.doFilter(httpServletRequest,httpServletResponse,suffixFilterChain);
        ThreadLocalUtil.remove();
    }

    @Override
    public void destroy() {

    }
}
