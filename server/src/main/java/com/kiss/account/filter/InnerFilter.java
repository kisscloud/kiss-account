package com.kiss.account.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface InnerFilter {
    void doFilter(HttpServletRequest request, HttpServletResponse response,InnerFilterChain filterChain);
}
