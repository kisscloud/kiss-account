package com.kiss.account.dao;

import com.kiss.account.entity.OperationLog;

import java.util.List;

public interface OperationLogDao {

    /**
     * 添加操作日志
     *
     * @return Integer
     */
    Integer createOperatorLog(OperationLog operationLog);

    List<OperationLog> getOperationLogs(int start, int size);

    Integer getOperationLogsCount();
}
