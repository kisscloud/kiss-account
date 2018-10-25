package com.kiss.account.mapper;

import com.kiss.account.entity.OperationLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OperationLogMapper {

    /**
     * 添加操作日志
     * @param operationLog
     * @return
     */
    Integer createOperatorLog(OperationLog operationLog);

    /**
     * 查询操作日志
     * @param start
     * @param size
     * @return
     */
    List<OperationLog> getOperationLogs(@Param("start") int start, @Param("size") int size);

    /**
     * 查询操作日志数量
     * @return
     */
    Integer getOperationLogsCount();
}
