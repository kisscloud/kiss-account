package com.kiss.account.validator;


// 1. 如果是新增用户，则直接查询数据库是否有重名用户
// 2. 如果是更新用户，则先判断用户姓名是否更改，再查询数据库是否有重名用户
public class AccountNameValidator  {
}
