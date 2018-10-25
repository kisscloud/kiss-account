package com.kiss.account.output;

import lombok.Data;

import java.util.List;

@Data
public class OperationLogsOutput {

    private Integer count;

    private List<OperationLogOutput> logs;

    public OperationLogsOutput(Integer count, List<OperationLogOutput> logs) {
        this.count = count;
        this.logs = logs;
    }
}
