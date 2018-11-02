package com.kiss.account.entity;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Data
@Entry(objectClasses = {"organization", "top"})
public final class AccountsOrganizationEntry {

    @Id
    @JsonIgnore
    private Name id;

    @DnAttribute(value = "o", index = 0)
    @Attribute(name = "o")
    private String name;

}