package com.kiss.account.mapper;

import com.kiss.account.entity.Account;
import com.kiss.account.entity.AccountRole;
import com.kiss.account.output.AccountOutput;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AccountMapper {

    /**
     * 创建账号
     *
     * @param account
     * @return
     */
    Integer createAccount(Account account);

    /**
     * 给账号分配角色
     *
     * @param accountRoles
     */
    void bindRolesToAccount(List<AccountRole> accountRoles);

    /**
     * 分页查询账号信息
     *
     * @param start int
     * @param size  int
     * @return List<AccountOutput>
     */
    List<AccountOutput> getAccounts(@Param("start") int start, @Param("size") int size);


    /**
     * 查询部门用户
     *
     * @param groupId Integer
     * @return List<Account>
     */
    List<Account> getAccountsByGroupId(Integer groupId);

    /**
     * 更加账户id查询账户信息
     *
     * @param id Integer
     * @return Account
     */
    Account getAccountById(Integer id);

    /**
     * 根据用户名查询账户信息
     *
     * @param name String
     * @return Account
     */
    Account getAccountByName(String name);

    /**
     * 根据用户名查找用户
     *
     * @param username String
     * @return Account
     */
    Account getAccountByUsername(String username);

    /**
     * 根据邮箱查找用户
     *
     * @param email String
     * @return Account
     */
    Account getAccountByEmail(String email);


    /**
     * 根据手机号查找用户
     *
     * @param mobile String
     * @return Account
     */
    Account getAccountByMobile(String mobile);

    /**
     * 根据账户id查询账户
     *
     * @param userId int
     * @return AccountOutput
     */
    AccountOutput getAccountOutputById(int userId);

    /**
     * 查询所有账户的数量
     *
     * @return Integer
     */
    Integer getAccountsCount();

    /**
     * 更新账户
     *
     * @param account AccountOutput
     * @return Integer
     */
    Integer updateAccount(Account account);

    /**
     * 更新账户密码
     *
     * @param account Account
     * @return Integer
     */
    Integer updateAccountPassword(Account account);

    /**
     * 更新账户状态
     *
     * @param account
     * @return Integer
     */
    Integer updateAccountStatus(Account account);

    /**
     * 删除账户所拥有的所有角色
     *
     * @param accountId Integer
     * @return Integer
     */
    Integer deleteAccountRolesByAccountId(Integer accountId);

    /**
     * 获取账户下所有的权限
     *
     * @param accountId Integer
     * @return   List<String>
     */
    List<String> getAccountPermissionsByAccountId(Integer accountId);


    /**
     * 根据账户id及权限码code查询账户的数据权限
     *
     * @param params Map<String, Object>
     * @return List<String>
     */
    List<String> getAccountPermissionDataScope(Map<String, Object> params);

    /**
     * 查询有效账户的数量
     * @return
     */
    Integer getValidAccountsCount();

    /**
     * 查询超级用户的个数
     * @return
     */
    Integer getRootsCount();
}
