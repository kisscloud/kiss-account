package com.kiss.account.dao.impl;

import com.kiss.account.dao.OperationLogDao;
import com.kiss.account.entity.OperationLog;
import com.kiss.account.mapper.OperationLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OperationLogDaoImpl implements OperationLogDao {

    @Autowired
    private OperationLogMapper operationLogMapper;

    @Override
    public Integer createOperatorLog(OperationLog operationLog) {

        return operationLogMapper.createOperatorLog(operationLog);
    }

    @Override
    public List<OperationLog> getOperationLogs(int start, int size) {

        return operationLogMapper.getOperationLogs(start,size);
    }

    @Override
    public Integer getOperationLogsCount() {

        return operationLogMapper.getOperationLogsCount();
    }
}
