package com.kiss.account.entity;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Data
@Entry(base = "o=accounts", objectClasses = {"organizationalPerson", "inetOrgPerson", "person", "top"})
public final class AccountEntry {

    @Id
    @JsonIgnore
    private Name id;

    @DnAttribute(value = "uid", index = 1)
    private String uid;

    @Attribute(name = "cn")
    private String name;

    @Attribute(name = "sn")
    private String username;

    @Attribute(name = "userPassword")
    private String password;

    @Attribute(name = "mail")
    private String email;

    @Attribute(name = "mobile")
    private String mobile;
}