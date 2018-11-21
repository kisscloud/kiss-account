package com.kiss.account.service.impl;

import com.alibaba.fastjson.JSON;
import com.kiss.account.dao.OperationLogDao;
import com.kiss.account.entity.OperationLog;
import com.kiss.account.service.OperationLogService;
import entity.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Date;

@Component
public class OperationLogServiceImpl implements OperationLogService {

    @Autowired
    private OperationLogDao operationLogDao;

    @Override
    public void saveOperationLog(Guest guest, Object before, Object after, String targetField, Integer targetType) {

        OperationLog operationLog = new OperationLog();

        try {
            if (before == null) {
                Field field = after.getClass().getDeclaredField(targetField);
                field.setAccessible(true);
                operationLog.setTargetId(Integer.parseInt(field.get(after).toString()));
                operationLog.setBeforeValue("");
                operationLog.setAfterValue(JSON.toJSONString(after));
            } else {
                Field field = before.getClass().getDeclaredField(targetField);
                field.setAccessible(true);
                operationLog.setTargetId(Integer.parseInt(field.get(before).toString()));
                operationLog.setBeforeValue(JSON.toJSONString(before));
                operationLog.setAfterValue(after == null ? "" : JSON.toJSONString(after));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        operationLog.setTargetType(targetType);
        saveLog(guest, operationLog);
    }

    private void saveLog(Guest guest, OperationLog operationLog) {
        operationLog.setOperatorId(guest.getId());
        operationLog.setOperatorName(guest.getName());
        operationLog.setOperatorIp(guest.getIp() == null ? "" : guest.getIp());
        operationLog.setCreatedAt(new Date());
        operationLog.setUpdatedAt(new Date());
        operationLog.setRecoveredAt(null);
        operationLogDao.createOperatorLog(operationLog);
    }
}
