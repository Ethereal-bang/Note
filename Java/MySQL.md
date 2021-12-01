# 配置

## MySQL

+ 配置服务器类型：

<img src="https://gitee.com/ethereal-bang/images/raw/master/20211130093034.png" alt="image-20211130093034570" style="zoom:50%;" />

+ 配置网站并发连接数：

    <img src="https://gitee.com/ethereal-bang/images/raw/master/20211130093213.png" alt="image-20211130093213718" style="zoom:50%;" />

<img src="https://gitee.com/ethereal-bang/images/raw/master/20211130002403.png" alt="image-20211130002403656" style="zoom:50%;" />

设置密码

<img src="https://gitee.com/ethereal-bang/images/raw/master/20211130002524.png" alt="image-20211130002524903" style="zoom:50%;" />



进入：

![image-20211130002747659](https://gitee.com/ethereal-bang/images/raw/master/20211130002747.png)

![image-20211130002732780](https://gitee.com/ethereal-bang/images/raw/master/20211130002732.png)

## SQLyog

SQLyog 是一款简洁高效、功能强大的<span style="color:red">图形化 </span>MySQL 数据库管理工具

1. 使用 SQLyog 登录数据库：

    <img src="https://gitee.com/ethereal-bang/images/raw/master/20211130093627.png" alt="image-20211130093627570" style="zoom:50%;" />

## Navicat

1. 新建连接

2. 新建查询：

    ![image-20211130091000665](https://gitee.com/ethereal-bang/images/raw/master/20211130091000.png)



# 数据库

数据库——DataBase，用于存储、管理数据的仓库

+ **特点：**
    1. 持久化存储数据——本质上还是文件系统，以文件的方式存在服务器的电脑上
    2. 方便存储和管理数据
    3. 使用了统一的方式操作数据库：SQL——所有的关系型数据库都可以使用通用的 SQL 语句进行管理



# SQL

SQL——Structured Query Language：结构化查询语言

+ **作用：**

    1. 每一关系型数据库都支持的查询规范
    2. 通用的数据库操作语言，可用于不同的数据库
    3. 不同数据库 SQL 语句有一定差别——MySQL、Oracle

+ **SQL 语句分类：**

    + 数据定义——Data Definition Language：建库、建表
    + 数据查询——Data Manipulation Language：表中记录增删改
    + 数据控制——Data Control Language：用户权限的设置

+ **MySQL 语法：**

    1. 分号结尾（在 SQLyog 中不是必须）

    2. 大小写不敏感

    3. 注释：

        ```mysql
        -- 单行注释
        
        /* 多行注释
        */
        
        # mysql 特有注释
        ```

![image-20211130215155708](https://gitee.com/ethereal-bang/images/raw/master/20211130215155.png)

## DDL 操作数据库

+ <span style="font-size:22px">创建数据库：</span>

    ```mysql
    -- 直接创建数据库 db1
    create database db1;
    
    -- 不存在则创建数据库
    create database if not exists db2;
    
    -- 创建数据库并指定字符集gbk
    create database db3 default character set gbk;
    ```

+ <span style="font-size:22px">查看数据库：</span>

    ```mysql
    -- 查看所有的数据库
    show databases;
    
    -- 查看某数据库的定义信息
    show create database db3;
    ```

    要记得`;`：

    ![image-20211130104542199](https://gitee.com/ethereal-bang/images/raw/master/20211130104542.png)

    > `information_schema`、`mysql`、`performance_schema`和`sys`是系统库，不要去改动它们。其他的是用户创建的数据库。

    数据库会存在 data 文件夹内：![image-20211130110835987](https://gitee.com/ethereal-bang/images/raw/master/20211130110853.png)

+ <span style="font-size:22px">修改数据库：</span>

    修改默认字符集。

    ```mysql
    alter database `db3` character set utf8;
    ```

    

+ <span style="font-size:22px">删除数据库：</span>

    ```mysql
    drop database `db2`;
    ```

    <img src="https://gitee.com/ethereal-bang/images/raw/master/20211130111105.png" alt="image-20211130111105156" style="zoom:50%;" />

+ <span style="font-size:22px">使用数据库：</span>

    ```mysql
    -- 查看正在使用的数据库：
    select database();	# 使用的mysql中的全局函数
    
    -- 切换数据库：
    use db1;
    ```



## DDL 操作表结构

+ <span style="font-size:20px">MySQL 数据类型：</span>

    | 常用**类型** | **描述**                    |
    | ------------ | --------------------------- |
    | int          |                             |
    | double       |                             |
    | varchar      | 字符串类型                  |
    | date         | 日期类型（格式 yyyy-mm-dd） |

    ![image-20211130131610466](https://gitee.com/ethereal-bang/images/raw/master/20211130131610.png)

+ <span style="font-size:22px">创建表——CREATE：</span>

    ```mysql
    -- 创建表：
    create table t1 (
    	`studentid` Varchar(10)	-- 最后行没有逗号(，)
    );
    -- 创建个相同表结构的表：
    create table `t2` like `t1`;
    ```

+ <span style="font-size:22px">查看表——SHOW：</span>

    ```mysql
    use `db1`;
    -- 查看数据库中所有表：
    show tables;
    -- 查看表结构：
    desc `t1`;
    -- 查看创建表的 SQL 语句：
    show create table `t1`;
    ```

    `SHOW CREATE TABLE <表名>`存在的意义是防止命名冲突

+ <span style="font-size:22px">删除表——DROP：</span>

    ```mysql
    -- 直接删除表：
    drop table `t1`;
    -- 如果存在则删除表：
    drop table if exeits `t1`;
    ```

+ <span style="font-size:22px">修改表结构——ALTER：</span>

    ```mysql
    # 添加表列——ADD：
    alter table `t1` add id int(11);
    # 修改类型——MODIFY：(id改为varchar类型)
    alter table `t1` modify `id` varchar(100);
    # 修改列名——CHANGE：
    alter table `t1` change `id` `newid` varchar(30);
    # 删除列——DROP：
    alter table `t1` drop `newid`;
    
    # 修改表名 RENAME
    rename table `t1` to `t2`;
    ```

## DML 操作表中数据

对表中记录进行增删改。

+ <span style="font-size:22px">INSERT——插入记录：</span>

    <span style="color:red">`insert [into] <表名> [字段名] values(<字段值...>)`</span>

    ```mysql
    # 插入全部字段（可以省略字段名）
    insert into `student` values (785, 'Jim', 19);
    
    # 插入部分数据
    INSERT INTO `cqupt student` (`studentid`, `name`) VALUES (785, 'Jim'); 
    
    # 更新表记录
    ```

    

+ <span style="font-size:22px">UPDATE——更新表记录：</span>

    <span style="color:red">`update <表名> set <字段名> = <值> [where 条件表达式]`</span>

    ```mysql
    # 不带条件修改数据
    update `student` set `sex` = '女';
    
    # 带条件修改数据
    update `sutdent` set `sex` = '男' where `id` = 3;
    
    # 一次修改多列
    update `student` set `age` = 26, `address` = '北京' where `id` = 3;
    ```

+ <span style="font-size:22px">删除表记录：</span>

    <span style="color:red">`delete from <表名> [where 条件表达式]`</span>

    ```mysql
    # 删除表中所有数据：
    delete from 'student';
    
    # 带条件删除数据：
    delete from `student` where `id` = 1;
    
    # 使用truncate删除表中所有记录：
    truncate table `student`;
    ```

    + `truncate table`和不带`where`的`delete`语句：

        功能上相同——删除表中全部行，但`truncate`速度更快，使用的系统和事务日志资源少

## DQL 查询表中数据

查询只是一种显示数据的方式

+ <span style='font-size:22px'>简单查询：</span>

    ```mysql
    # 查询表所有数据：
    selsect * from `student`;	-- *表示所有列
    
    # 查询指定列：
    select `name`, `age` from `student`;
    ```

    ![image-20211130214444050](https://gitee.com/ethereal-bang/images/raw/master/20211130214444.png)

+ <span style="font-size:22px">指定列的别名进行查询：</span>

    使用别名的好处：显示时用新名字，而又不修改表的结构。

    ```mysql
    # 表也使用别名：
    select `name` as `姓名` , `age` as `年龄` from `student` as `学生`;
    ```

+ <span style="font-size:22px">查询时清除重复值：</span>

    ```mysql
    select distinct address from `student`; 
    ```

+ <span style="font-size:22px">查询结果参与运算：</span>

+ <span style="font-size:22px">条件查询：</span>



## 约束

**约束**——对表中的数据进行限定，保证数据的正确、有效、完整性

**分类**：主键查询——primary key、非空约束——not null、唯一约束——unique、外键约束——foreign key、缺省值约束——DEFAULT

+ <span style="font-size:22px">非空约束：</span>

    ```mysql
    # 创建表时添加约束：
    create table stu(
    	`id` INT,
      `name` varchar(20) NOT NULL
    );
    
    # 创建表后添加约束：
    alter table stu modify `name` varchar(20) NOT NULL;
    
    # 删除非空约束：
    ALTER TABLE `stu` modify `name` varchar(20);
    ```

+ <span style="font-size:22px">唯一约束：</span>

+ <span style="font-size:22px">主键约束：</span>

  主键——主键的值能唯一标识表中的每一行( *这就好比所有人都有身份证，每个人的身份证号是不同的，能唯一标识每一个人* )
  
    

## 视图 View

视图可以建立在一张表中，也可以建立在多张表中。

<span style="color:red">`CREATE VIEW <视图名> [字段名] AS <select 语句>`</span>

```mysql
# 选取部分字段创建view
CREATE VIEW `View_Choosebb`(
	`View_bb1`, `View_bb2`, `view_bb3`)
AS SELECT `bb1`, `bb4`, `bb5` FROM `choosebb`;

# 查询view
desc `view_chossebb`;

# 删除view
DROP VIEW `View_Choosebb`;
```



## 索引

+ **定义：**帮助 MySQL 提高查询效率的<span style="color:red">数据结构</span>，一般在常用的搜索字段建立索引==？==

+ <span style="font-size:22px">索引分类：</span>

    + 主键索引——设定为主键后数据库自动建立的索引

    + 单值索引/普通索引——一个索引只含单列，一表可有多个单列索引

    + 唯一索引——索引值必须唯一（允许有一个空值）
    + 复合索引——一个索引包含多个列

+ <span style="font-size:22px">创建索引：</span>

    ```mysql
    # 创建普通索引：
    CREATE INDEX `index_name` ON `table_name`(`bb2`);
    ```

    

# 参考

+ ：

    [MySQL基础.pdf - 黑马程序员]()

+ DML：

    [SQL truncate、delete与drop区别 - 编程笔记 - 博客园](https://www.cnblogs.com/8765h/archive/2011/11/25/2374167.html)

+ 约束：

    [mysql在学号列创建主码约束_MySQL 数据完整性_weixin_39997037的博客-CSDN博客](https://blog.csdn.net/weixin_39997037/article/details/113681744)

+ 索引：

    [MySQL 索引 | 菜鸟教程](https://www.runoob.com/mysql/mysql-index.html)
