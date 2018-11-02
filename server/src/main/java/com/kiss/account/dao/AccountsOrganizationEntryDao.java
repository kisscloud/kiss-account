package com.kiss.account.dao;

import com.kiss.account.entity.AccountsOrganizationEntry;
import org.springframework.data.repository.CrudRepository;

import javax.naming.Name;

public interface AccountsOrganizationEntryDao extends CrudRepository<AccountsOrganizationEntry, Name> {

}

