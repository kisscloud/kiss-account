package com.kiss.account.mapper;

import com.kiss.account.entity.Account;
import com.kiss.account.entity.AccountRoles;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {
    Integer createAccount(Account account);

    void allocateRoleToAcount(AccountRoles accountRoles);

    void allocateRolesToAcount(List<AccountRoles> accountRoles);

    List<Account> getAccounts();

    Account getAccountByUsername(String username);

    Account getAccountById(int userId);
}
