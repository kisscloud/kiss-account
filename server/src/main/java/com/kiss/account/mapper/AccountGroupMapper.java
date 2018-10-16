package com.kiss.account.mapper;

import com.kiss.account.entity.AccountGroup;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountGroupMapper {

    Integer createAccountGroup(AccountGroup accountGroup);

    AccountGroup getAccountGroupByName(String name);

    Integer putAccountGroup(AccountGroup accountGroup);
}
