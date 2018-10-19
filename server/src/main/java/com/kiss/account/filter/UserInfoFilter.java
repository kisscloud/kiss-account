package com.kiss.account.filter;

import com.kiss.account.entity.Operator;
import com.kiss.account.utils.UserUtil;
import org.apache.commons.lang.StringUtils;
import utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInfoFilter implements InnerFilter{

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, InnerFilterChain filterChain) {

        String token = request.getHeader("X-Access-Token");

        if (!StringUtils.isEmpty(token)) {
            Integer userId = Integer.parseInt(JwtUtil.getUserId(token));
            String username = JwtUtil.getUserName(token);
            Operator operator = new Operator();
            operator.setUserId(userId);
            operator.setUsername(username);
            UserUtil.setUserInfo(operator);
        }

        filterChain.doFilter(request,response,filterChain);
    }
}
