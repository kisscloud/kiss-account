package com.kiss.account.Filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface InnerFilter {
    public void doFilter(HttpServletRequest request, HttpServletResponse response,InnerFilterChain filterChain);
}
