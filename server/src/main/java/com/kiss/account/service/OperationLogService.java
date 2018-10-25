package com.kiss.account.service;


import entity.Guest;

public interface OperationLogService {

    void saveOperationLog (Guest guest,Object before,Object after,String targetField, Integer targetType);
}
