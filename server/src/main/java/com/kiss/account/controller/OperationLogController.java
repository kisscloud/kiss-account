package com.kiss.account.controller;


import com.kiss.account.client.OperationLogClient;
import com.kiss.account.entity.OperationLog;
import com.kiss.account.output.OperationLogOutput;
import com.kiss.account.output.OperationLogsOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "OperationLogs", description = "操作日志相关接口")
public class OperationLogController extends BaseController implements OperationLogClient {

    @Override
    @ApiOperation(value = "获取所有操作日志")
    public OperationLogsOutput getOperationLogs(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {

        Integer queryPage = page == null ? 1 : page;
        Integer maxSize = Integer.parseInt(maxLogSize);
        Integer pageSize = (size == null || size > maxSize) ? maxSize : size;
        List<OperationLog> operationLogs = operationLogDao.getOperationLogs((queryPage - 1) * pageSize, pageSize);
        List<OperationLogOutput> operationLogOutputs = new ArrayList<>();

        for (OperationLog operationLog : operationLogs) {
            OperationLogOutput operationLogOutput = new OperationLogOutput();
            BeanUtils.copyProperties(operationLog,operationLogOutput);
            operationLogOutputs.add(operationLogOutput);
        }

        Integer count = operationLogDao.getOperationLogsCount();
        OperationLogsOutput operationLogsOutput = new OperationLogsOutput(count,operationLogOutputs);

        return operationLogsOutput;
    }

}
