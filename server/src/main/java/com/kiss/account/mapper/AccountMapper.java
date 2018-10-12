package com.kiss.account.mapper;

import com.kiss.account.entity.Account;
import com.kiss.account.entity.AccountGroup;
import com.kiss.account.entity.AccountRoles;
import com.kiss.account.output.AccountOutput;
import com.kiss.account.output.AccountRolesOutput;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AccountMapper {
    Integer createAccount(Account account);

    void allocateRoleToAccount(AccountRoles accountRoles);

    void allocateRolesToAccount(List<AccountRolesOutput> accountRoles);

    List<AccountOutput> getAccounts(@Param("start") int start, @Param("size") int size);

    Account getAccountByUsername(String username);

    AccountOutput getAccountById(int userId);

    List<AccountGroup> getGroups();

    AccountGroup getGroup(int id);

    Integer getAccountsCount();

}
