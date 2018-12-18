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
# 服务名称
spring.application.name=kiss-api-gateway

# 服务端口
server.port=8100

# 服务中心地址
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/

# 超时配置
ribbon.ConnectTimeout=60000
ribbon.ReadTimeout=60000
```
