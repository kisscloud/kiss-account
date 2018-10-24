package com.kiss.account.dao.impl;

import com.kiss.account.dao.OperationLogsDao;
import com.kiss.account.entity.OperationLog;
import com.kiss.account.mapper.OperationLogsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OperationLogsDaoImpl implements OperationLogsDao {

    @Autowired
    private OperationLogsMapper operationLogsMapper;

    @Override
    public List<OperationLog> getOperationLogs(int start, int size) {

        return operationLogsMapper.getOperationLogs(start,size);
    }

    @Override
    public Integer getOperationLogsCount() {
        return operationLogsMapper.getOperationLogsCount();
    }
}
