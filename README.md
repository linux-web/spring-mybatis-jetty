
# 基于spring+mybatis+jetty实现API服务

> Spring版本是3.2.3.RELEASE。

### 执行命令

```
mvn sql:execute

注：执行完以后，更新项目（Update Project）即可以看到数据库。

或者使用`mvn clean package`执行可执行sql语句。

mvn clean package [-Dmaven.test.skip=true]
```

### 接口说明
1. http://localhost:8111/users/{uid}   POST: User
2. http://localhost:8111/users/{uid}/{mid}   DELETE
3. http://localhost:8111/users/{uid}/{mid}/gender  GET

> 待修改Bug：执行DELETE操作时，狂打日志。。。

### 开发人员

WeChat: wgybzb

QQ: 1010437118

E-mail: wgybzb@sina.cn



