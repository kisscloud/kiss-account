# 使用 LDAP

## 1. 启动容器

OpenLDAP 是 LDAP 协议的开源实现版本，通过 Dokcer 快速启动 LDAP 服务器。

```
docker run --name kiss-ldap --env LDAP_ORGANISATION="KissCloud" --env LDAP_DOMAIN="kisscloud.io" \
--env LDAP_ADMIN_PASSWORD="kisscloud" --detach \
-p 389:389 osixia/openldap:1.2.2
```

## 2. 查看管理员信息
管理员账户为 `admin`，可通过 `LDAP_ADMIN_PASSWORD` 环境变量指定管理员的密码，此处为 `kisscloud`。
```
docker exec kiss-ldap ldapsearch -x -H ldap://localhost -b dc=kisscloud,dc=io -D "cn=admin,dc=kisscloud,dc=io" -w kisscloud
```

输出如下内容，则为启动成功：

```

# extended LDIF
#
# LDAPv3
# base <dc=kisscloud,dc=io> with scope subtree
# filter: (objectclass=*)
# requesting: ALL
#

# kisscloud.io
dn: dc=kisscloud,dc=io
objectClass: top
objectClass: dcObject
objectClass: organization
o: KissCloud
dc: kisscloud

# admin, kisscloud.io
dn: cn=admin,dc=kisscloud,dc=io
objectClass: simpleSecurityObject
objectClass: organizationalRole
cn: admin
description: LDAP administrator
userPassword:: e1NTSEF9OWYwYmEvKzhKbG01cVpsRXFaazNuVHRvbEtyUGZwZnk=

# search result
search: 2
result: 0 Success

# numResponses: 3
# numEntries: 2
```

## 3. Spring Boot 配置

```
spring.ldap.urls=ldap://localhost:389
spring.ldap.username=cn=admin,dc=kisscloud,dc=io
spring.ldap.password=kisscloud
spring.ldap.base=dc=kisscloud,dc=io

account.ldap.enabled=true 
```

## 4. 参考链接

[https://github.com/osixia/docker-openldap](https://github.com/osixia/docker-openldap)