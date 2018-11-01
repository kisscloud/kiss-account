package com.kiss.account.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LdapConfig {

    @Value("${account.ldap.enable}")
    public Boolean enable;
}
