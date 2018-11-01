package com.kiss.account.dao;


import com.kiss.account.entity.AccountEntry;
import org.springframework.data.repository.CrudRepository;
import javax.naming.Name;

public interface AccountEntryDao extends CrudRepository<AccountEntry, Name> {

}

