package com.kiss.account.dao;

import com.kiss.account.entity.AuthorizationTarget;

import java.util.List;

public interface AuthorizationTargetDao {

    Integer createAuthorizationTarget(AuthorizationTarget authorizationTarget);

    List<AuthorizationTarget> getAuthorizationTargetsByClientId(Integer clientId);

    AuthorizationTarget getAuthorizationTargetsByClientIdAndIp(Integer clientId,String ip);
}
