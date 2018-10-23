package com.kiss.account.dao.impl;

import com.kiss.account.dao.OperationLogDao;
import com.kiss.account.entity.OperationLog;
import com.kiss.account.mapper.OperationLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OperationLogDaoImpl implements OperationLogDao {

    @Autowired
    private OperationLogMapper operationLogMapper;

    @Override
    public Integer createOperatorLog(OperationLog operationLog) {
        return operationLogMapper.createOperatorLog(operationLog);
    }
}
