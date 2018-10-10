package com.kiss.account.input;

import lombok.Data;

/**
 * 创建用户分组输入
 */
@Data
public class CreateAccountGroupInput {
    private String name;
    private String level;
    private Integer seq;
    private String remark;
    private Integer parentId;
}
