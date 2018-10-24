package com.kiss.account.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class InnerFilterChain implements InnerFilter {

    private List<InnerFilter> innerFilters = new ArrayList<>();
    private int index = 0;

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response,InnerFilterChain filterChain) {

        if (innerFilters.size() == index) {
            return;
        }

        InnerFilter innerFilter = innerFilters.get(index);
        index ++;
        innerFilter.doFilter(request,response,filterChain);
    }

    public InnerFilterChain addFilter(InnerFilter innerFilter) {

        innerFilters.add(innerFilter);
        return this;
    }
}
