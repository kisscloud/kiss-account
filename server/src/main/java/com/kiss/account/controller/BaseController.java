package com.kiss.account.controller;

import com.kiss.account.dao.*;
import com.kiss.account.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BaseController {

    @Value("${max.accounts.size}")
    protected String maxAccountsSize;

    @Value("${account.default.password}")
    protected String accountDefaultPassword;

    @Value("${authorization.code.expired}")
    protected String authorizationCodeExpired;

    @Value("${max.log.size}")
    protected String maxLogSize;

    @Autowired
    protected AccountGroupDao accountGroupDao;

    @Autowired
    protected AccountDao accountDao;

    @Autowired
    protected AccountEntryDao accountEntryDao;

    @Autowired
    protected OperationLogService operationLogService;

    @Autowired
    protected ClientDao clientDao;

    @Autowired
    protected ClientModuleDao clientModuleDao;

    @Autowired
    protected AuthorizationTargetDao authorizationTargetDao;

    @Autowired
    protected WebHookDao webHookDao;

    @Autowired
    protected PermissionDao permissionDao;

    @Autowired
    protected RoleDao roleDao;

    @Autowired
    protected OperationLogDao operationLogDao;
}
