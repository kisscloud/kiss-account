package com.kiss.account.controller;

import com.kiss.account.client.OperationLogsClient;
import com.kiss.account.dao.OperationLogsDao;
import com.kiss.account.entity.OperationLog;
import com.kiss.account.output.OperationLogOutput;
import com.kiss.account.output.OperationLogsOutput;
import com.kiss.account.utils.ResultOutputUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import output.ResultOutput;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "OperationLogs", description = "操作日志相关接口")
public class OperationLogsController implements OperationLogsClient {

    @Autowired
    private OperationLogsDao operationLogsDao;

    @Value("${max.log.size}")
    private String maxLogSize;

    @Override
    @ApiOperation(value = "获取所有操作日志")
    public ResultOutput getOperationLogs(@RequestParam("page") String page, @RequestParam("size") String size) {

        Integer queryPage = StringUtils.isEmpty(page) ? 1 : Integer.parseInt(page);
        Integer maxSize = Integer.parseInt(maxLogSize);
        Integer pageSize = (StringUtils.isEmpty(size) || Integer.parseInt(size) > maxSize) ? maxSize : Integer.parseInt(size);
        List<OperationLog> operationLogs = operationLogsDao.getOperationLogs((queryPage - 1) * pageSize, pageSize);
        List<OperationLogOutput> operationLogOutputs = new ArrayList<>();

        for (OperationLog operationLog : operationLogs) {
            OperationLogOutput operationLogOutput = new OperationLogOutput();
            BeanUtils.copyProperties(operationLog,operationLogOutput);
            operationLogOutputs.add(operationLogOutput);
        }

        Integer count = operationLogsDao.getOperationLogsCount();
        OperationLogsOutput operationLogsOutput = new OperationLogsOutput(count,operationLogOutputs);

        return ResultOutputUtil.success(operationLogsOutput);
    }
}
