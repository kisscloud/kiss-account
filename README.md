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
spring:
  application:
    name: kiss-account
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    username: root
    password: 123456
    jdbc-url: jdbc:mysql://mysql.rds.aliyuncs.com/kiss-account?characterEncoding=utf-8&useSSL=false
  messages:
    basename: i18n/codes
    baseFolder: i18n
    use-code-as-default-message: true
  ldap:
    urls: ldap://localhost:389
    username: cn=admin,dc=kisscloud,dc=io
    password: kisscloud
    base: dc=kisscloud,dc=io
server:
  port: 8001
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
logging:
  level:
    com.kiss.account.mapper: debug
max:
  accounts:
    size: 100
  log:
    size: 100
account:
  default:
    password: 123456
  ldap:
    enabled: true
authorization:
  code:
    expired: 600000
```

## 3. 导入数据库

导入 kiss-account.sql 即可，在配好图形化界面第一次启动时，会检查是否存在超级管理员并引导创建。
