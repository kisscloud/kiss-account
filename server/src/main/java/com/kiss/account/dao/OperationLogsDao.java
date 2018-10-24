package com.kiss.account.dao;

import com.kiss.account.entity.OperationLog;

import java.util.List;

public interface OperationLogsDao {

    List<OperationLog> getOperationLogs(int start, int size);

    Integer getOperationLogsCount();
}
