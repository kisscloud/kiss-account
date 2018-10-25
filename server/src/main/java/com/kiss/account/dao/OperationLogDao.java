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

    /**
     * 查询操作日志
     * @param start 分页参数
     * @param size  分页数量
     * @return
     */
    List<OperationLog> getOperationLogs(int start, int size);

    /**
     * 查询操作日志数量
     * @return
     */
    Integer getOperationLogsCount();
}
