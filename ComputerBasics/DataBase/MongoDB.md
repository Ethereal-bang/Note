# MongoDB

[MongoDB](https://www.mongodb.com/what-is-mongodb)（面向文档数据模型的开源 [NoSQL](https://en.wikipedia.org/wiki/NoSQL) 数据库）MongoDB 数据库里，“集合”中的“文档” [类似于](https://docs.mongodb.com/manual/core/databases-and-collections/#collections) 关系数据库里“表”中的“行”。（*字段*）




+ <span style="font-size:20px">MongoDB 与 Sql</span>：

    | SQL术语     | MongoDB术语 | 说明                                |
    | :---------- | :---------- | :---------------------------------- |
    | database    | database    | 数据库                              |
    | table       | collection  | 数据库表/集合                       |
    | row         | document    | 数据记录行/文档                     |
    | column      | field       | 数据字段/域                         |
    | index       | index       | 索引                                |
    | table joins |             | 表连接,MongoDB不支持                |
    | primary key | primary key | 主键,MongoDB自动将_id字段设置为主键 |



## MongoDB Compass

客户端应用

+ <span style="font-size:20px">连接：</span> 

    ```
    mongodb://localhost
    ```
    
    连接后：

    ![image-20220215122350055](https://gitee.com/ethereal-bang/images/raw/master/20220215122357.png)



# Connection

+ <span style="font-size:22px">命令行启动：</span>

    ```shell
    mongo
    ```

+ <span style="font-size:22px">连接字符串 URL 格式：</span>

    ```js
    // 基本格式
    mongodb://username:password@host/dbname
    
    // 标准格式
    mongodb://[username:password@]host1[:port1][,...hostN[:portN]][/[defaultauthdb][?options]]
    ```

    ```js
    /* Eg: */
    "mongodb://localhost"
    "mongodb+srv://127.0.0.1/blog"
    ```

+ <span style="font-size:22px">DNS Seed List Connection Format:</span>

    **作用**——更加灵活的部署且可以轮流更改服务器而无需重新配置 Client 端

    **使用** DNS 种子列表——连接字符串前缀改为 `mongodb+srv:` 代替 `mongodb:`



# 数据库

+ show dbs ——显示所有数据库

+ db ——显示当前数据库

    > - **admin**： 权限的角度"root"数据库。要是将一个用户添加到这个数据库，这个用户自动继承所有数据库的权限。一些特定的服务器端命令也只能从这个数据库运行，比如列出所有的数据库或者关闭服务器。
    > - **local:** 这个数据永远不会被复制，可以用来存储限于本地单台服务器的任意集合
    > - **config**: 当Mongo用于分片设置时，config数据库在内部使用，用于保存分片的相关信息。

+ use <> ——连接到指定数据库



# Collection 集合



# Authentication

> MongoDB 的用户对应每个数据库操作权限，而管理员具有操作用户的权限

<span style="font-size:20px">创建管理员：</span>

+ `use admin`

1. 新建**管理员**账户：

    ```shell
    db.createUser({ user: "admin", pwd: "password", roles: [{ role: "userAdminAnyDatabase", db: "admin" }] })
    ```

    > **admin 用户**用于管理账号，不能进行关闭数据库等操作

2. 新建 **root** 账户：

    ```shell
    db.createUser({user: "root",pwd: "password", roles: [ { role: "root", db: "admin" } ]})
    ```

3. 验证：

    ```shell
    db.auth('root', 'password');
    ```

    > 验证后才能执行创建其他用户的操作。

<span style="font-size:20px">创建其他用户：</span>

```shell
use test
db.createUser(
  {
    user: "myTester",
    pwd:  passwordPrompt(),  # or cleartext password
    roles: [ { role: "readWrite", db: "test" },
             { role: "read", db: "reporting" } ]
  }
)
```

> **部分 Role：**
>
> Read：允许用户读取指定数据库
> readWrite：允许用户读写指定数据库
> readAnyDatabase：只在admin数据库中可用，赋予用户所有数据库的读权限
> readWriteAnyDatabase：只在admin数据库中可用，赋予用户所有数据库的读写权限
> userAdminAnyDatabase：只在admin数据库中可用，赋予用户所有数据库的userAdmin权限
> dbAdminAnyDatabase：只在admin数据库中可用，赋予用户所有数据库的dbAdmin权限。
> root：只在admin数据库中可用。超级账号，超级权限



# REF

+ Connection：

    [Connection String URI Format — MongoDB Manual](https://www.mongodb.com/docs/manual/reference/connection-string)



# DEBUG

<span style="font-size:22px">MongoDB:</span>

+ <span style="font-size:20px">[***aborting after fassert() failure](https://stackoverflow.com/questions/34555603/mongodb-failing-to-start-aborting0-after-fassert-failure)</span>

    Q_Desc：突然无法启动 Mongo，部分报错日志如上，启动时返回：

    > ```
    > about to fork child process, waiting until server is ready for connections.
    > forked process: 5412
    > ERROR: child process failed, exited with 14
    > To see additional information in this output, start without the "--fork" option.
    > ```

    S：removing temporary file which didnt delete itself and its blocking normal operation

    ```shell
    sudo rm /tmp/mongodb-27017.sock
    ```

+ <span style="font-size:20px">[MongoDB Compass 导出 .csv 数据在 excel 中文乱码：](https://baijiahao.baidu.com/s?id=1634287239598842140&wfr=spider&for=pc)</span>

    S：用记事本打开，另存为选择编码格式 ANSI。再在 Excel 中打开显示正常。

<span style="font-size:22px">Authentication:</span>

+ <span style="font-size:20px">[MongoServerError: Authentication failed.](https://stackoverflow.com/a/55751766/16622827)</span>
    + Q_Desc：MongoDB Compass 中能成功登录的 URL 串在 Node 中连接失败。
    + R：混淆了管理员账户和用户账户权限