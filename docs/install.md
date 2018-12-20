# 程序启动


统一账户服务启动依赖 Kiss Cloud 的服务发现中心（kiss-eureka-server）和后台网关（kiss-console-gateway），基于 Spring Cloud 构建的微服务基础组件。

* [启动 kiss-eureka-server](https://github.com/kisscloud/kiss-eureka-server)
* [启动 kiss-console-gateway](https://github.com/kisscloud/kiss-console-gateway)


## 1. 启动 kiss-account

kiss-account 是统一账户中心的主程序，满足微服务结构，提供了 Feign 接口供其他服务通过服务发现中心（kiss-eureka-server）调用。

**程序启动：**

```
$ git clone github.com/kisscloud/kiss-console-gateway.git
$ cd kiss-eureka-server
$ mvn package
$ java -j kiss-console-gateway.jar
```

**编辑配置文件：**

```
```

**配置文件内容：***

```
# 应用名称
spring.application.name=kiss-account

# 应用监听端口
server.port=8001

# 服务发现中心地址
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/

# 数据源配置
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=Lehu1234
#spring.datasource.password=123456
spring.datasource.jdbc-url=jdbc:mysql://rm-uf6p47403601q2gz7oo.mysql.rds.aliyuncs.com/kiss-account?characterEncoding=utf-8&useSSL=false
#spring.datasource.url=jdbc:mysql://localhost/kiss-account?characterEncoding=utf-8&useSSL=false

# mybatis配置
mybatis.config-location=classpath:mybatis-config.xml
logging.level.com.kiss.account.mapper.AccountMapper=debug

# 多语言配置
spring.messages.basename=i18n/codes
spring.messages.use-code-as-default-message=true

# LDAP
spring.ldap.urls=ldap://localhost:389
spring.ldap.username=cn=admin,dc=kisscloud,dc=io
spring.ldap.password=kisscloud
spring.ldap.base=dc=kisscloud,dc=io


# 应用配置
max.accounts.size=100
max.log.size=100
account.default.password=123456
authorization.code.expired=10000
account.ldap.enabled=true
account.ldap.base=o=accounts
```

## 2. 启动 kiss-account-console

