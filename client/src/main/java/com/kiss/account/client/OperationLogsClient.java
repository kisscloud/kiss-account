package com.kiss.account.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import output.ResultOutput;

@RequestMapping
public interface OperationLogsClient {

    @GetMapping("/operationLogs")
    ResultOutput getOperationLogs(@RequestParam("page") String page, @RequestParam("size") String size);

}
