package com.kiss.account.dao;

import com.kiss.account.entity.OperationLog;

public interface OperationLogDao {

    /**
     * 添加操作日志
     *
     * @return Integer
     */
    Integer createOperatorLog(OperationLog operationLog);
}
