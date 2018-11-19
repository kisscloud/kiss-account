package com.kiss.account.dao.impl;

import com.kiss.account.dao.AuthorizationTargetDao;
import com.kiss.account.entity.AuthorizationTarget;
import com.kiss.account.mapper.AuthorizationTargetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AuthorizationTargetDaoImpl implements AuthorizationTargetDao {

    @Autowired
    private AuthorizationTargetMapper authorizationTargetMapper;

    @Override
    public Integer createAuthorizationTarget(AuthorizationTarget authorizationTarget) {

        return authorizationTargetMapper.createAuthorizationTarget(authorizationTarget);
    }

    @Override
    public List<AuthorizationTarget> getAuthorizationTargetsByClientId(Integer clientId) {

        return authorizationTargetMapper.getAuthorizationTargetsByClientId(clientId);
    }

    @Override
    public AuthorizationTarget getAuthorizationTargetsByClientIdAndIp(Integer clientId, String ip) {

        Map<String,Object> params = new HashMap<>();
        params.put("clientId",clientId);
        params.put("ip",ip);

        return authorizationTargetMapper.getAuthorizationTargetsByClientIdAndIp(params);
    }
}
