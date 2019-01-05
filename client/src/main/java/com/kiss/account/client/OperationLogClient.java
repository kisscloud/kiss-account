package com.kiss.account.client;

import com.kiss.account.output.OperationLogsOutput;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping
public interface OperationLogClient {

    @GetMapping("/operation/logs")
    OperationLogsOutput getOperationLogs(@RequestParam("page") Integer page, @RequestParam("size") Integer size);

}
