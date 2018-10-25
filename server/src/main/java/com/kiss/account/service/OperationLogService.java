package com.kiss.account.service;

import com.alibaba.fastjson.JSON;
import com.kiss.account.dao.OperationLogDao;
import com.kiss.account.entity.*;
import com.kiss.account.input.BindAccountsToRoleInput;
import com.kiss.account.input.BindPermissionToRoleInput;
import entity.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OperationLogService {

    private static final Integer TYPE_ACCOUNT_GROUP = 1;
    private static final Integer TYPE_ACCOUNT = 2;
    private static final Integer TYPE_PERMISSION_MODULE = 3;
    private static final Integer TYPE_PERMISSION = 4;
    private static final Integer TYPE_ROLE = 5;
    private static final Integer TYPE_ROLE_ACCOUNTS = 6;
    private static final Integer TYPE_ROLE_PERMISSIONS = 7;
    private static final Integer TYPE_CLIENT = 8;
    private static final Integer TYPE_CLIENT_MODULES = 9;


    @Autowired
    private OperationLogDao operationLogDao;

    private void saveLog(Guest guest, OperationLog operationLog) {
        operationLog.setOperatorId(guest.getId());
        operationLog.setOperatorName(guest.getName());
        operationLog.setOperatorIp(guest.getIp() == null ? "" : guest.getIp());
        operationLog.setCreatedAt(new Date());
        operationLog.setUpdatedAt(new Date());
        operationLog.setRecoveredAt(null);
        operationLogDao.createOperatorLog(operationLog);
    }

    public void saveAccountGroupLog(Guest guest, AccountGroup before, AccountGroup after) {
        OperationLog operationLog = new OperationLog();
        operationLog.setTargetType(TYPE_ACCOUNT_GROUP);
        operationLog.setTargetId(before == null ? after.getId() : before.getId());
        operationLog.setBeforeValue(before == null ? "" : JSON.toJSONString(before));
        operationLog.setAfterValue(after == null ? "" : JSON.toJSONString(after));
        saveLog(guest, operationLog);
    }


    public void saveAccountLog(Guest guest, Account before, Account after) {
        OperationLog operationLog = new OperationLog();
        operationLog.setTargetType(TYPE_ACCOUNT);
        operationLog.setTargetId(before == null ? after.getId() : before.getId());
        operationLog.setBeforeValue(before == null ? "" : JSON.toJSONString(before));
        operationLog.setAfterValue(after == null ? "" : JSON.toJSONString(after));
        saveLog(guest, operationLog);
    }

    public void savePermissionModuleLog(Guest guest, PermissionModule before, PermissionModule after) {
        OperationLog operationLog = new OperationLog();
        operationLog.setTargetType(TYPE_PERMISSION_MODULE);
        operationLog.setTargetId(before == null ? after.getId() : before.getId());
        operationLog.setBeforeValue(before == null ? "" : JSON.toJSONString(before));
        operationLog.setAfterValue(after == null ? "" : JSON.toJSONString(after));
        saveLog(guest, operationLog);
    }

    public void savePermissionLog(Guest guest, Permission before, Permission after) {
        OperationLog operationLog = new OperationLog();
        operationLog.setTargetType(TYPE_PERMISSION);
        operationLog.setTargetId(before == null ? after.getId() : before.getId());
        operationLog.setBeforeValue(before == null ? "" : JSON.toJSONString(before));
        operationLog.setAfterValue(after == null ? "" : JSON.toJSONString(after));
        saveLog(guest, operationLog);
    }


    public void saveRoleLog(Guest guest, Role before, Role after) {
        OperationLog operationLog = new OperationLog();
        operationLog.setTargetType(TYPE_ROLE);
        operationLog.setTargetId(before == null ? after.getId() : before.getId());
        operationLog.setBeforeValue(before == null ? "" : JSON.toJSONString(before));
        operationLog.setAfterValue(after == null ? "" : JSON.toJSONString(after));
        saveLog(guest, operationLog);
    }


    public void saveRoleAccountsLog(Guest guest, BindAccountsToRoleInput before, BindAccountsToRoleInput after) {
        OperationLog operationLog = new OperationLog();
        operationLog.setTargetType(TYPE_ROLE_ACCOUNTS);
        operationLog.setTargetId(before == null ? after.getId() : before.getId());
        operationLog.setBeforeValue(before == null ? "" : JSON.toJSONString(before));
        operationLog.setAfterValue(after == null ? "" : JSON.toJSONString(after));
        saveLog(guest, operationLog);
    }

    public void saveRolePermissionsLog(Guest guest, BindPermissionToRoleInput before, BindPermissionToRoleInput after) {
        OperationLog operationLog = new OperationLog();
        operationLog.setTargetType(TYPE_ROLE_PERMISSIONS);
        operationLog.setTargetId(before == null ? after.getRoleId() : before.getRoleId());
        operationLog.setBeforeValue(before == null ? "" : JSON.toJSONString(before));
        operationLog.setAfterValue(after == null ? "" : JSON.toJSONString(after));
        saveLog(guest, operationLog);
    }

    public void saveClientLog(Guest guest, Permission before, Permission after) {
        OperationLog operationLog = new OperationLog();
        operationLog.setTargetType(TYPE_CLIENT);
        operationLog.setTargetId(before == null ? after.getId() : before.getId());
        operationLog.setBeforeValue(before == null ? "" : JSON.toJSONString(before));
        operationLog.setAfterValue(after == null ? "" : JSON.toJSONString(after));
        saveLog(guest, operationLog);
    }

    public void saveClientModulesLog(Guest guest, Permission before, Permission after) {
        OperationLog operationLog = new OperationLog();
        operationLog.setTargetType(TYPE_CLIENT_MODULES);
        operationLog.setTargetId(before == null ? after.getId() : before.getId());
        operationLog.setBeforeValue(before == null ? "" : JSON.toJSONString(before));
        operationLog.setAfterValue(after == null ? "" : JSON.toJSONString(after));
        saveLog(guest, operationLog);
    }
}
