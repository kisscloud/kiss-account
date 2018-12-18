# 统一账户中心

## 1. 程序运行

```
$ git clone https://github.com/kisscloud/kiss-account.git
$ cd kiss-account
$ mvn install -Dmaven.test.skip=true
$ mvn package -Dmaven.test.skip=true
$ java -jar -Dspring.config.location=/opt/configs/kiss-account/application.yml /opt/apps/kiss-account/target/kiss-account-0.0.1-SNAPSHOT.jar

```

## 2. 配置文件

编辑配置文件：

```
$ vim /opt/configs/kiss-account/application.yml
```

配置文件内容：

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
spring.datasource.password=123456
#spring.datasource.password=123456
spring.datasource.jdbc-url=jdbc:mysql://.mysql.rds.aliyuncs.com/kiss-account?characterEncoding=utf-8&useSSL=false
#spring.datasource.url=jdbc:mysql://localhost/kiss-account?characterEncoding=utf-8&useSSL=false

# mybatis配置
mybatis.config-location=classpath:mybatis-config.xml
logging.level.com.kiss.account.mapper.AccountMapper=debug

# 多语言配置
spring.messages.basename=i18n/codes
spring.messages.use-code-as-default-message=true

# LDAP
spring.ldap.urls=ldap://47.100.235.203:389
spring.ldap.username=cn=admin,dc=kisscloud,dc=io
spring.ldap.password=kisscloud
spring.ldap.base=dc=kisscloud,dc=io


# 应用配置
max.accounts.size=100
max.log.size=100
account.default.password=123456
authorization.code.expired=10000

# 开启 LDAP
account.ldap.enabled=true
account.ldap.base=o=accounts
```

## 3. 导入数据库

导入 kiss-account.sql 即可，在配好图形化界面第一次启动时，会检查是否存在超级管理员并引导创建。
