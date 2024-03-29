# 配置

## MySQL

+ 配置服务器类型：

+ 配置网站并发连接数：


+ 默认字符集：


+ 设置密码进入：

+ 配置：

    `my.ini`是 MySQL 数据库中使用的配置文件，MySQL 服务器启动时会读取这个配置文件，我们可以通过修改这个文件，达到更新配置的目的。



## SQLyog

SQLyog 是一款简洁高效、功能强大的<span style="color:red">图形化 </span>MySQL 数据库管理工具



+ <span style="font-size:20px">导入本地 .csv 文件：</span>

    1. 选中表，依次建立对应各字段

    2. 选择该表 “导入使用本地加载的CSV数据”

    3. 更改相关设置：

        ![image-20220509184026558](https://gitee.com/ethereal-bang/images/raw/master/20220509184035.png)

    4. 导入



## Navicat

1. 新建连接

2. 新建查询：



# 数据类型

`DECIMAL(M, D)`，M 表示总位数，D 表示小数点后位数，`NUMERIC`同。

<span style="font-size:20px">时间:</span>

timestamp: 可设默认值 CURRENT_TIME, eg:`2022-10-03 23:28:04`




# 数据库

数据库——DataBase，用于存储、管理数据的仓库





# Table

## Colum

<span style="font-size:20px">AUTO_INCREMENT:</span>

- 一个表中只能有一个自增列。
- 自增列的数据类型只能使用整数和浮点数。
- 自增列的初始值是 `1`。你可以在创建表的时候置顶自增列的初始值，也可以修改自增列的值。
- 删除一些行后，删除的自增列的值不能重用。



# 基础 SQL

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
        
    4. [mysql中**\`**符号的用处](https://www.yisu.com/zixun/27328.html)： 数据库字段是 sql 保留的关键字，在写sql语句的时候，用到这些字段的时候需要用``包含起来，不然会报语法错误。



## 连接

```shell
mysql -u root -p
```



## 操作数据库

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

    > `information_schema`、`mysql`、`performance_schema`和`sys`是系统库，不要去改动它们。其他的是用户创建的数据库。
>
    > 数据库存在 data 文件夹内

+ <span style="font-size:22px">修改数据库：</span>

    修改默认字符集。

    ```mysql
    alter database `db3` character set utf8;
    ```

+ <span style="font-size:22px">删除数据库：</span>

    ```mysql
    drop database `db2`;
    ```

+ <span style="font-size:22px">使用数据库：</span>

    ```mysql
    -- 查看正在使用的数据库：
    select database();	# 使用的mysql中的全局函数
    
    -- 切换数据库：
    use db1;
    ```



## 操作表结构

+ <span style="font-size:22px">创建表——CREATE：</span>

    ```mysql
    -- 创建表：
    create table t1 (
    	`studentid` Varchar(10) COMMENT '学生id' -- 最后行没有逗号(，)
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
    INSERT INTO `cqupt student` (`studentid`) VALUE (785); 
    
    # 一次插入多条数据(mysql 语法）
    INSERT INTO test VALUES
    ('钱二', 19, 540.00, '南海苑 5-2-9'),
    ('孙三', 21, 555.50, '学生新区 21-5-15');
    
    # 插入另一表的数据
    INSERT INTO test SELECT * FROM test_temp;
    ```

+ <span style="font-size:22px">UPDATE——更新表记录：</span>

    <span style="color:red">`update <表名> set <字段名> = <值> [where 条件表达式]`</span>

    ```mysql
    # 不带条件修改数据
    update `student` set `sex` = '女';
    
    # 带条件修改数据
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

## 查询数据

查询只是一种显示数据的方式

### 基本查询

<span style='font-size:22px'>简单查询：</span>

```mysql
# 查询表所有数据：
select * from `student`;	-- *表示所有列

# 查询指定列：
select `name`, `age` from `student`;

# 返回记录数
select COUNT(*) from `student`;
```

<span style="font-size:22px">指定列的别名进行查询：</span>

使用别名的好处：显示时用新名字，而又不修改表的结构。

```mysql
# 表也使用别名：
select `name` as `姓名` , `age` as `年龄` from `student` as `学生`;
```

<span style="font-size:22px">查询时清除重复值：</span>

```mysql
select distinct address from `student`; 
```

<span style="font-size:22px">查询结果参与运算：</span>

<span style="font-size:22px">查询结果排序：</span>

```mysql
SELECT * FROM goods WHERE TYPE = 'bookRanking' ORDER BY ranking ASC;
```

<span style="font-size:22px">限制查询记录数: </span>

```mysql
SELECT * from `user` LIMIT 3;	# 返回1~3
SELECT * from `user` LIMIT 1,3;	# 返回2~4
```



### 条件查询

+ <span style="font-size:22px">WHERE：</span>

    ```mysql
    -- BETWEEN ... AND ...
    SELECT * FROM student WHERE age BETWEEN 20 AND 23;
    -- IS NULL
    SELECT CourseName FROM course WHERE CourseBeforeID IS NULL;
    -- EXISTS
    ```




### 聚合查询

`COUNT()`、`MAX`、`MIN`、`SUM`、`AVG`

```sql
SELECT COUNT(*) FROM student;	-- 得出总数
SELECT MAX(score) FROM choose;	-- 查询score的最大值 
```



### 多表查询

还可以从多张表同时查询数据，`SELECT * FROM <表1> <表2>`

**连接查询**对多表进行 JOIN 运算，即 先确定一个主表作结果集，然后把其他表的行选择性地连接在主表结果上

+ <span style="font-size:18px">INNER JOIN：</span>交集

    选出两张表都存在的记录

+ <span style="font-size:18px">LEFT OUTER JOIN：</span>

    选出左表存在的记录：

+ <span style="font-size:18px">RIGHT OUTER JOIN：</span>

+ <span style="font-size:18px">FULL OUTER JOIN：</span>并集

```mysql
SELECT stu.`SNO`, stu.`name`, choose.`Score` FROM student stu INNER JOIN choose ON choose.`SNO` = stu.`SNO`;
```



### 嵌套查询

一个 SELECT...FROM...WHERE 语句称为一个<span style="color:red">查询块</span>

嵌套查询——将一个查询块嵌套在另一查询块的 WHERE 子句 或 HAVING 短语 

>  例：查询选修 C1 课程的 成绩低于”张三“的 学生的学号和成绩：
>
>  最外层选择学号`SNO`、成绩`Score`，最子层查询条件——选修 C1(*因为它是最基本的条件*)，第二层查询
>
>  1. 确定选修 C1 课程的学生成绩：
>
>     ```sql
>     SELECT score
>     FROM choose
>     WHERE courseID = 'C1';
>     -- 结果：95，80，78
>     ```
>
>  2. 确定张三 C1 的成绩：
>
>     ```sql
>     # 1. 获得张三的学号：
>     SELECT SNO	-- 得学号
>     FROM student
>     WHERE `name` = '张三'; -- 由名字 
>     # 2. 获得张三的成绩：
>     SELECT score	-- 得成绩
>     FROM choose
>     WHERE SNO = <;-- 由学号
>     ```
>
>  3. 确定学号和成绩：
>
>     ```sql
>     SELECT SNO, SCORE
>     FROM choose
>     WHERE <>;
>     ```
>



### 集合查询

SELECT 的查询结果是<span style="color:red">元组的集合</span>，所以多个 SELECT 的结果可<span style="color:red">进行集合操作</span>

> **多表查询**将数据集合，**集合查询**是将结果集合

+ **主要集合操作：**

    UNION——并；INTERSECT(intersect)——交；EXCEPT——差

+ **要求：**

    各查询结果的列数相同、对应数据类型相同

```sql
# 查询 选修了C1 或者 选修了C3 的学生学号
SELECT SNO
  FROM Choose
  WHERE CourseID = 'C1'
 UNION
 SELECT SNO
  FROM Choose
  WHERE CourseID = 'C3'
```



### 条件语句

<span style="color:red">`WHERE <条件表达式>`</span>

+ **与：**`<条件1> AND <条件2>`
+ **或：**`<> OR <>`
+ **非：**`NOT <条件>`



## 用通配符进行过滤

+ <span style="font-size:22px">LIKE 操作符：</span>

    LIKE 语句在 SQL 结构化查询语言中起重要作用，为在搜索子句中使用通配符，必须使用 LIKE 操作符<span style="color:red">`WHERE <字段名> LIKE <对应值>`</span>

    + **百分号（%）通配符：**

        最常使用，表示该位置任意字符出现任意次数

        ```sql
        -- 将address是'南海苑'开头的，年龄增加一岁
        UPDATE test SET age=age-1 WHERE address LIKE '南海苑%';
        
        -- 查找name字段中含有字符串100的
        SELECT * FROM goods WHERE `name` LIKE '%100%';
        ```

    + **下划线（_）通配符：**

        代表该位置出现单个字符，字符的长度不能为 0



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
  
  **删除自增主键约束：**必须先删自增再删主键



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

+ **定义：**帮助 MySQL 提高查询效率的<span style="color:red">数据结构</span>，一般在常用的搜索字段建立索引

+ <span style="font-size:22px">索引分类：</span>

    + 主键索引——设定为主键后数据库自动建立的索引

    + 单值索引/普通索引——一个索引只含单列，一表可有多个单列索引

    + 唯一索引——索引值必须唯一（允许有一个空值）
    + 复合索引——一个索引包含多个列

+ <span style="font-size:22px">创建索引：</span>

    ```mysql
    # 创建普通索引：
    CREATE INDEX `index_bb2` ON `table_name`(`bb2`);
    CREATE INDEX `index_bb4` ON `choosebb`(`Bb4` DESC);	-- 降序索引
    ```
    
    > **ORDER BY：**
    >
    > order by 用于对结果集按照一个列或多列进行排序，默认按升序（**ASC**）排序，降序使用关键字（**DESC**）
    
+ <span style="font-size:22px">查看索引：</span>

    ```mysql
    SHOW INDEX FROM `choosebb`;
    ```

+ <span style="font-size:22px">删除索引：</span>

    ```mysql
    DROP INDEX `index_bb2` ON `choosebb`;
    ```



# 数据库设计

## 多表关系设计

<span style="font-size:20px">一对多关系：</span>

> 最常见的关系，例如班级-学生、客户-订单

**建表原则：** 在从表（多方）创建一个字段， 字段作为外键指向主表（一方）的主键



<span style="font-size:20px">多对多关系：</span>

**建表原则：** 创建中间关系表，至少有两个字段——分别作为外键指向各自一方的主键

![MySQL：多表关系设计（一对多 / 多对多 / 一对一）_Back end_02](https://s7.51cto.com/images/blog/202107/16/e3d1940e0eafdbeddd4c2cfeb028f196.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk=)



<span style="font-size:20px">一对一关系：</span>

> 实际开发中应用不多，因为一对一可以创建成一张表

**建表原则：**主表的 PRIMARY KEY 和从表的 Unique 外键，形成主外键关系

![MySQL：多表关系设计（一对多 / 多对多 / 一对一）_建表_03](https://s7.51cto.com/images/blog/202107/16/7c65d3e864d52fbd20e61c36d3eb6056.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk=)



# Others

## 关于中文乱码

+ <span style="font-size:20px">查看字符集设置情况：</span>

    ```sql
    > SHOW VARIABLES LIKE '%char%';
    +--------------------------+-------------+
    | Variable_name            | Value       |
    +--------------------------+-------------+
    | character_set_client     | utf8        |
    | character_set_connection | utf8        |
    | character_set_database   | utf8        |
    | character_set_filesystem | binary      |
    | character_set_results    | utf8        |
    | character_set_server     | latin1      |
    | character_set_system     | utf8				 |
    | character_sets_dir       |             |
    +--------------------------+-------------+
    ```

+ <span style="font-size:20px">MySQL 中涉及的几个字符集：</span>

    + character-set-server / default-character-set ——服务器字符集，默认情况采用
    + character-set-database ——数据库字符集
    + character-set-table ——数据库表字符集

    > 优先级依次增加，所以一般情况只需设置 character-set-server，而在创建数据库和表时不特别指定字符集，这样统一采用character-set-server字符集。

    <hr>

    + character-set-client ——客户端的字符集，客户端向服务器发送请求时，请求以该字符集进行编码
    + character-set-results ——结果字符集，服务器向客户端返回结果时，结果以该字符集进行编码

    > 客户端，如果没有定义character-set-results，则采用character-set-client字符集作为默认的字符集。所以只需要设置character-set-client字符集。

+ 输入中文：将默认字符集均设置为 utf8。

+ 命令行查询时中文显示错误：

    设置`set character_set_results=gbk`后显示正常：




# 进阶 SQL

## GROUP BY + COUNT

将查询集分组

```mysql
SELECT name, COUNT(*)
FROM `news`
GROUP BY name	# 相同的carrier_id并在一起
```

> **Eg 实现：**按名字分组，并统计每人有多少条记录



## ORDER BY + GROUP BY

```mysql
SELECT carrier_id, COUNT(*)
FROM `news`
GROUP BY carrier_id	# 相同的carrier_id并在一起
ORDER BY COUNT(carrier_id) desc	# 以carrier_id出现次数排序
```

> **实现：**返回 10 条以出现次数排序的 `carrier_id`。 



## 函数

+ MAX()

    ```mysql
    select MAX(price) from products;
    ```



# 性能

## SELECT

+ 指定返回具体字段而不是 `*`



# REF 

+ ：

    [MySQL基础.pdf - 黑马程序员]()

+ 配置：

    [MySQL配置文件（my.ini）详解 - C语言中文网](http://c.biancheng.net/view/7571.html)

    [再见乱码：5分钟读懂MySQL字符集设置 - 程序猿小卡 - 博客园](https://www.cnblogs.com/chyingp/p/mysql-character-set-collation.html)

+ Table：

    [MySQL AUTO_INCREMENT 自增列](https://www.sjkjc.com/mysql/auto-increment)

+ SQL：

    [MySQL decimal、numeric数据类型介绍-CSDN](https://blog.csdn.net/love_xsq/article/details/42294533)

    [SQL truncate、delete与drop区别 - 编程笔记 - 博客园](https://www.cnblogs.com/8765h/archive/2011/11/25/2374167.html)

    [连接查询 - 廖雪峰的官方网站](https://www.liaoxuefeng.com/wiki/1177760294764384/1179610888796448)

    [SQL COUNT() 函数 | 菜鸟教程](https://www.runoob.com/sql/sql-func-count.html)

    [mysql在学号列创建主码约束_MySQL 数据完整性_weixin_39997037的博客-CSDN博客](https://blog.csdn.net/weixin_39997037/article/details/113681744)

    [MySQL 索引 | 菜鸟教程](https://www.runoob.com/mysql/mysql-index.html)

    [sql语句like的用法 有些正则表达式可以通过like实现-CSDN](https://blog.csdn.net/shadowyelling/article/details/7913126)

+ 数据库设计：

    [MySQL：多表关系设计（一对多 / 多对多 / 一对一）](https://blog.51cto.com/u_15127650/3351867)
    
+ Others：

    [MySQL字符集 GBK/GB2312/UTF8区别 解决 MYSQL中文乱码问题以及error 1406:data too long for column 'name' at row 1](https://blog.csdn.net/zsm653983/article/details/7970179)
