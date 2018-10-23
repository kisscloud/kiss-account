package com.kiss.account.mapper;

import com.kiss.account.entity.OperationLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperationLogMapper {

    Integer createOperatorLog(OperationLog operationLog);
}
