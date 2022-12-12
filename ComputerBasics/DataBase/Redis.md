# Redis

<span style="color:orange">NoSQL</span> 数据库，数据以 key-value 键值对的形式存储在<span style="color:orange">内存</span>中

**常见使用场景**：缓存（高并发需求场合），分布式锁，自增序列



## Install

**主要文件介绍：**

+ redis-server.exe：服务端程序，提供 redis 服务

+ redis-cli.exe: 客户端程序，通过它连接 redis 服务并进行操作

### Windows

**安装：**

[Redis for windows - GitHub](https://github.com/tporadowski/redis)

+ 下载 msi 后安装
+ 直接下载 zip 解压

**命令行启动：**

1. 在安装路径打开 cmd

    > 可以把 redis 的路径加到系统的<span style="color:orange">环境变量</span>里，就省去切换路径

2. 执行

    ```shell
    $ redis-server.exe redis.windows.conf
    ```

    > 配置文件 `redis.conf` 若省略，则启用默认

**安装 Redis 到 Windows 服务：**

```shell
redis-server --service-install
```

> 安装后可以在 windows 服务里快捷 开启/停止 redis 服务

**Redis Client 测试服务是否开启:**

1. 连接：

    ```shell
    $ redis-cli.exe -h 127.0.0.1 -p 6379
    ```



# 安全

## 验证

连接时验证：

```shell
$ redis-cli.exe -h 127.0.0.1 -p 6379 -a mypwd
```

连接后验证：

```shell
127.0.0.1:6379> AUTH "mypwd"
OK
```



## 修改密码

Redis 默认没有密码

+ 通过**修改配置文件 `redis.conf` 设置密码**：

    ```
    # requirepass foobared
    requirepass mypwd
    ```

+ **查看密码：**

    ```shell
    127.0.0.1:6379> CONFIG get requirepass
    1) "requirepass"
    2) ""
    ```

+ **通过命令修改**：

    ```shell
    127.0.0.1:6379> CONFIG set requirepass "mypwd"
    ```

设置密码后不密码验证不能执行命令



# 读取

**set** / **get**

```shell
> set myKey v

> get myKey
```

**exists**

```shell
> exists myKey
(integer) 1

> del myKey
(integer) 0
```

> (integer) 1 真，(integer) 0 假



# SpringBoot 中的集成

> 在 springBoot 中更常见的方式是集成 spring-data-redis，这是 spring提供的一个专门用来操作 redis 的项目，封装了对 redis 的常用操作，里边主要封装了 jedis 和 lettuce 两个客户端。相当于是在他们的基础上加了一层门面

1. 添加依赖：

    ```xml
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-pool2</artifactId>
    </dependency>
    ```

2. 配置 Redis 数据库连接：

    + Jedis 简易单次连接：

        ```java
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("pwd");
        jedis.get("myKey");
        ```

    + 连接池

    ```yaml
    spring:
      redis:
        host: 127.0.0.1
        port: 6379
        password: mypwd
    		spring.redis.database: 0 # 数据库索引（默认0 
    ```

3. 配置连接池

4. 编写 **Redis 操作工具类：**

    > 将 RedisTemplate 实例包装成一个工具类，便于对 redis 进行数据操作

    
