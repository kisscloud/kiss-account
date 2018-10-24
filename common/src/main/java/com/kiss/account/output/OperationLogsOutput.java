package com.kiss.account.output;

import lombok.Data;

import java.util.List;

@Data
public class OperationLogsOutput {

    private Integer count;

    private List<OperationLogOutput> operationLogOutputs;

    public OperationLogsOutput(Integer count, List<OperationLogOutput> operationLogOutputs) {
        this.count = count;
        this.operationLogOutputs = operationLogOutputs;
    }
}
