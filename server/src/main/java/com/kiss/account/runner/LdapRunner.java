package com.kiss.account.runner;

import com.kiss.account.config.LdapConfig;
import com.kiss.account.dao.AccountsOrganizationEntryDao;
import com.kiss.account.entity.AccountsOrganizationEntry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.naming.NameNotFoundException;

@Component
public class LdapRunner implements CommandLineRunner {

    @Autowired
    private LdapConfig ldapConfig;

    @Autowired
    private AccountsOrganizationEntryDao accountsOrganizationEntryDao;

    @Override
    public void run(String... args) throws InvalidNameException {
        if (ldapConfig.enabled) {
            LdapName entryId = new LdapName("o=accounts");
            boolean flag = true;
            try {
                flag = accountsOrganizationEntryDao.findById(entryId).isPresent();
            } catch (Exception nameNotFoundException) {
                if (NameNotFoundException.class.isInstance(nameNotFoundException)) {
                    flag = false;
                }
            } finally {
                if (!flag) {
                    try {
                        AccountsOrganizationEntry accountsOrganizationEntry = new AccountsOrganizationEntry();
                        accountsOrganizationEntry.setName("accounts");
                        accountsOrganizationEntryDao.save(accountsOrganizationEntry);
                    } catch (Exception e) {
                        System.out.println("【LDAP 连接错误】:");
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
