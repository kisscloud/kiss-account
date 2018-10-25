package com.kiss.account.mapper;

import com.kiss.account.entity.OperationLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OperationLogMapper {

    Integer createOperatorLog(OperationLog operationLog);

    List<OperationLog> getOperationLogs(@Param("start") int start, @Param("size") int size);

    Integer getOperationLogsCount();
}
