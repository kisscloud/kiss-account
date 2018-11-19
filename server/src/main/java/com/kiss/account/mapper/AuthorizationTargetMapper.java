package com.kiss.account.mapper;

import com.kiss.account.entity.AuthorizationTarget;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AuthorizationTargetMapper {

    Integer createAuthorizationTarget(AuthorizationTarget authorizationTarget);

    List<AuthorizationTarget> getAuthorizationTargetsByClientId(Integer clientId);

    AuthorizationTarget getAuthorizationTargetsByClientIdAndIp(Map params);
}
