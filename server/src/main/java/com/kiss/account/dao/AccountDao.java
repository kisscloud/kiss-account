package com.kiss.account.dao;

import com.kiss.account.entity.Account;
import com.kiss.account.entity.AccountGroup;
import com.kiss.account.output.AccountOutput;
import com.kiss.account.output.AccountRolesOutput;

import java.util.List;

public interface AccountDao {
    /**
     * 创建账户
     * @param account
     */
    void createAccount(Account account);

    /**
     * 给账号分配角色
     * @param accountRoles
     */
    void allocateRolesToAccount(List<AccountRolesOutput> accountRoles);

    /**
     * 查询账号信息
     * @param start
     * @param size
     * @return
     */
    List<AccountOutput> getAccounts(int start, int size);

    /**
     * 通过账号名查询账号
     * @param username
     * @return
     */
    Account getAccountByUsername(String username);

    /**
     * 通过账号id查询账号
     * @param id
     * @return
     */
    AccountOutput getAccountById(Integer id);

    /**
     * 查询所有账号的数量
     * @return
     */
    Integer getAccountsCount();

    /**
     * 获取满足账号唯一表示的账号(账号的部分属性是不允许重复的)
     * @param name
     * @param username
     * @param email
     * @param mobile
     * @return
     */
    Account getAccountByUniqueIdentification(String name,String username,String email,String mobile);

    /**
     * 更新账号信息
     * @param account
     * @return
     */
    Integer putAccount(AccountOutput account);

    /**
     * 重置账号密码
     * @param account
     * @return
     */
    Integer putAccountPassword(Account account);

    /**
     * 更新账号状态
     * @param accountOutput
     * @return
     */
    Integer putAccountStatus(AccountOutput accountOutput);

    /**
     * 删除账号所拥有的所有角色
     * @param id
     * @return
     */
    Integer deleteAccountRoles (Integer id);
}
