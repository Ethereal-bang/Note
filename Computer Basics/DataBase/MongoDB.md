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

    

# REF

+ Connection：

    [Connection String URI Format — MongoDB Manual](https://www.mongodb.com/docs/manual/reference/connection-string)

