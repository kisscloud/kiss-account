package com.kiss.account.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import output.ResultOutput;

@RequestMapping
public interface OperationLogClient {

    @GetMapping("/operation/logs")
    ResultOutput getOperationLogs(@RequestParam("page") Integer page, @RequestParam("size") Integer size);

}
