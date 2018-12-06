package com.kiss.account.dao;

import com.kiss.account.entity.Account;
import com.kiss.account.entity.AccountRole;
import com.kiss.account.output.AccountOutput;
import java.util.List;

public interface AccountDao {
    /**
     * 创建账户
     *
     * @param account Account
     */
    void createAccount(Account account);

    /**
     * 给账号分配角色
     *
     * @param accountRoles List<AccountRoleOutput>
     */
    void bindRolesToAccount(List<AccountRole> accountRoles);

    /**
     * 查询账号信息
     *
     * @param start int
     * @param size  int
     * @return List<AccountOutput>
     */
    List<AccountOutput> getAccounts(int start, int size);

    /**
     * 查询部门用户
     *
     * @param groupId Integer
     * @return List<Account>
     */
    List<Account> getAccountsByGroupId(Integer groupId);

    /**
     * 根据账号id查询账号
     *
     * @param id Integer 用户ID
     * @return Account
     */
    Account getAccountById(Integer id);

    /**
     * 根据姓名查询用户
     *
     * @param name String
     * @return Account
     */
    Account getAccountByName(String name);

    /**
     * 通过账号名查询账号
     *
     * @param username 用户名
     * @return Account
     */
    Account getAccountByUsername(String username);

    /**
     * 根据邮箱查询用户
     *
     * @param email String
     * @return Account
     */
    Account getAccountByEmail(String email);

    /**
     * 根据手机号查询用户
     *
     * @param mobile String
     * @return Account
     */
    Account getAccountByMobile(String mobile);

    /**
     * 通过账号id查询账号
     *
     * @param id Integer
     * @return AccountOutput
     */
    AccountOutput getAccountOutputById(Integer id);

    /**
     * 查询所有账号的数量
     *
     * @return Integer
     */
    Integer getAccountsCount();

    /**
     * 更新账号信息
     *
     * @param account
     * @return Integer
     */
    Integer updateAccount(Account account);

    /**
     * 重置账号密码
     *
     * @param account Account
     * @return Integer
     */
    Integer updateAccountPassword(Account account);

    /**
     * 更新账号状态
     *
     * @param account
     * @return Integer
     */
    Integer updateAccountStatus(Account account);

    /**
     * 删除账号所拥有的所有角色
     *
     * @param accountId Integer
     * @return Integer
     */
    Integer deleteAccountRolesByAccountId(Integer accountId);

    /**
     * 查询账户下所有的权限
     *
     * @param accountId 账户id
     * @return  List<String>
     */
    List<String> getAccountPermissionsByAccountId(Integer accountId);

    /**
     * 根据账户id及权限码查询账户的数据权限
     *
     * @param accountId Integer
     * @return List<String>
     */
    List<String> getAccountPermissionDataScope(Integer accountId, String code);

    /**
     * 查询有效账户的数量
     * @return
     */
    Integer getValidAccountsCount();

    /**
     * 查询超级用户个数
     * @return
     */
    Integer getRootsCount();
}
