package com.kiss.account.runner;

import com.kiss.account.config.LdapConfig;
import com.kiss.account.dao.AccountsOrganizationEntryDao;
import com.kiss.account.entity.AccountsOrganizationEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.naming.ldap.LdapName;

@Component
public class LdapRunner implements CommandLineRunner {

    @Autowired
    private LdapConfig ldapConfig;

    @Autowired
    private AccountsOrganizationEntryDao accountsOrganizationEntryDao;

    @Override
    public void run(String... args) throws Exception {
        if (ldapConfig.enabled) {
            LdapName entryId = new LdapName("o=accounts");
            if (!accountsOrganizationEntryDao.findById(entryId).isPresent()) {
                AccountsOrganizationEntry accountsOrganizationEntry = new AccountsOrganizationEntry();
                accountsOrganizationEntry.setName("accounts");
                accountsOrganizationEntryDao.save(accountsOrganizationEntry);
            }
        }
    }
}
