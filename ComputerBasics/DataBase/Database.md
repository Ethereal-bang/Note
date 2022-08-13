# 数据库系统概论

> 选择 10 × 2 = 20
>
> 判断 10 × 1 = 10
>
> 填空 5 × 2 = 10
>
> 应用题(English)——关系代数、SQL 10 × 3 = 30
>
> 解答题——安全性，完整性，E-R模型，关系模式，设计理论，数据恢复，并发控制 3 × 10 = 30



# Ch1.绪论

> **要点:**
>
> 掌握数据管理技术的概念及其发展过程；掌握数据库、数据库管理系统、数据库系统等基本概念；掌握数据库系统的特点；掌握数据模型的概念及信息建模的抽象过程，掌握并区分概念模型和数据模型；掌握常用数据库模型及其特点；掌握关系模型及相关术语；掌握数据库的三级模式结构和二级映象，以及在此基础上的数据独立性保证；从硬件、软件、用户等方面了解数据库系统的组成。
>
> + 数据、数据库、数据库管理系统
> + 数据库的特点
> + 数据模型、数据模型的组成要素
> + 数据库系统三级结构及数据独立性
> + 数据库系统的组成

## 数据库系统概述

<span style="font-size:20px">数据、数据库、数据库管理系统:</span>

数据：数据库中存储的基本对象

数据库：长期存储在计算机内有组织的大量的共享的数据集合

数据库管理系统（DBMS）

数据库系统（**DBS）组成**——数据库，DBMS，应用程序，数据库管理员 DBA

<span style="font-size:20px">数据库的特点:</span>

> 人工管理阶段：数据不保存，应用程序管理数据，数据不共享，数据不独立性
>
> 文件系统：设备独立性。可长期保存，文件系统管理数据，共享性差冗余度大，数据独立性差

1. 数据结构化（与文件系统本质区别）

2. 共享性高冗余度低易扩充

3. 数据独立性高——物理独立性，逻辑独立性（二级映像功能保证的）

4. 数据由 DBMS 统一管理和控制

    <span style="color:blue">数据库系统必须提供的**数据控制功能**：并发控制、数据恢复、数据完整性、数据安全性</span>



##  数据模型

> 分为概念模型；逻辑模型、物理模型

**数据模型组成要素：**数据结构、数据操作、数据完整性约束条件

<span style="font-size:20px">概念模型:</span>

按用户<span style="color:blue">观点对数据建模</span>，主要用于数据库设计

**基本概念：**

+ 实体 <span style="color:blue">Entity——客观存在并可相互区别的事物</span>
+ <span style="color:blue">Attribute——实体所具有的某一特性</span>
+ <span style="color:blue">Key——唯一标识实体的属性集</span>
+ 实体型——实体名和属性名集合来抽象同类实体
+ 实体集
+ 联系 <span style="color:blue">Relationship——实体型内部各属性之间的联系；实体间联系通常指不同实体集之间的联系</span>



## 数据库系统结构

**数据库三级模式：**

+ 内模式——存储模式，数据库内部组织模式

+ 模式 Schema——数据在逻辑级上视图
+ 外模式——用户看到的数据<span style="color:blue">视图</span>

**二级映像功能与数据独立性：**

外模式 / 模式 映像——逻辑独立性

内模式 / 模式 映像——物理独立性



# Ch2.关系数据库

> **要点：**
>
> 掌握关系模型的数据结构、关系操作和完整性约束；掌握集合论角度的关系形式化定义，包括域、笛卡尔积、关系、码、属性、元组、关系数据库等概念；掌握关系与关系模式的区别和联系；熟练掌握用关系代数语言描述用户查询；了解关系演算语言。

<span style="font-size:20px">关系数据结构</span>

<span style="font-size:20px">关系操作</span>

<span style="color:blue">操作的对象和结果都是**集合**</span>

<span style="font-size:20px">关系完整性约束</span>

> 实体完整性、参照完整性、用户自定义完整性

**实体完整性：**主码不能取空值

**参照完整性：**外码取空或等于某主码值

**用户定义的完整性：**约束条件

<span style="font-size:20px">关系代数</span>

> 传统的集合运算、专门的关系运算

关系代数是一种抽象的查询语言，它用对关系的运算来表达查询

传统的集合运算是从关系的“水平”方向即行的角度进行
专门的关系运算不仅涉及行而且涉及列

**传统的集合运算：**并，差，交，笛卡尔积

**专门的关系运算：**选择，投影，连接，除运算

+ Selection: 从关系 R 中选取使逻辑表达式 F 为真的元组，是从行的角度进行的运算

    <span style="color:blue">**σ**~Sage<20~(Student)</span>——查询年龄小于 20 的学生

+ Projection: R 中选择出若干属性列组成新的关系，是从列的角度进行的运算

    <span style="color:blue">**π**~Sname,Sdept~(Student)</span>

+ Join: 从两个关系的笛卡尔积中选取属性间满足一定条件的元组

    ![image-20220629163202447](E:%5CTypora%5Cupload%5Cimage-20220629163202447.png)

+ Division: 同时从行和列角度进行运算



# Ch3.关系数据库标准语言SQL

<span style="font-size:20px">SQL 的特点及基本概念:</span>

<span style="font-size:20px">数据定义:</span>

表，索引，视图

<span style="font-size:20px">数据查询:</span>

单表查询

连接查询

嵌套查询

<span style="font-size:20px">数据更新:</span>

插入数据

修改数据

删除数据

## 视图

+ 建立视图：

    ```mysql
    CREATE VIEW <名> [(<列名>)]
    AS <子查询>
    [WITH CHECK OPTION]
    ```

+ 查询视图：

    > 像对基本表一样对视图进行查询

    视图消解——取出视图定义，子查询与用户查询结合转换成等价的查询，再执行修正的查询 的过程。<span style="color:blue">属于查询处理中的查询检查阶段</span>



# Ch4.数据库安全性

<span style="font-size:20px">数据库安全性基本概念:</span>

保护数据库以防止不合法使用所造成的数据泄露、更改或破坏 

<span style="font-size:20px">数据库安全性控制常用技术:</span>

+ 用户身份鉴别
+ 存取控制
    + <span style="color:blue">自主存取控制——GRANT, REVOKE</span>
    + 强制存取控制
+ 视图
+ 审计
+ 数据加密

```mysql
GRANT SELECT 	# ALL PRIVILEGES
ON TABLE Student
TO U1
WITH GRANT OPTION;	# 有授予他人权限的权力

REVOKE UPDATE(Sno) 
ON TABLE Student 
FROM U2;
```

创建角色：

```mysql
CREATE ROLE U1
[WITH] [DBA | RESOURCE | CONNECT];
```

> DBA——超级用户
>
> RESOURCE——创建基本表和视图
>
> 默认 CONNECT——只能登陆数据库，由其他用户授予权限



# Ch5.数据库完整性

数据的<span style="color:blue">正确性、相容性</span>

<span style="font-size:20px">实体完整性:</span>

在 CREATE TABLE 中用 PRIMARY KEY 定义。

```mysql
CREATE TABLE Student
	(Sno CHAR(9) PRIMARY KEY,	# 列级定义主码
   Sname CHAR(20) NOT NULL,
   Cno CHAR(4) NOT NULL,
   PRIMARY KEY(Sname, Cno)	# 表级定义主码
  )
```

> 对多个属性构成的码只能定义为表级约束条件

<span style="font-size:20px">参照完整性:</span>

FOREIGN KEY, REFERENCES 指明外码参照哪些表的主码

<span style="font-size:20px">用户自定义完整性:</span>

**属性上的约束条件**

**元组上的约束条件**

元组级限制——CREATE TABLE 时 CHECK 定义元组上的约束条件（可设置不同属性间取值的相互约束条件）

<span style="font-size:20px">触发器:</span>



# Ch6.关系数据理论

## 规范化

<span style="font-size:20px">函数依赖:</span>

X -> Y：称为 X 函数确定 Y，Y 函数依赖于 X

> 决定因素：同一 X 值不可能出现不同 Y 值

+ 此外，X -^F^> Y 完全函数依赖——对任何一个真子集 X' 都有 X' ↛  Y
+ Y -^P^> Y 部分函数依赖

> X 为属性组时
>
> Eg：(Sno, Cno) -> Sdept 是部分函数依赖，因为真子集 Sno -> Sdept 成立



<span style="font-size:20px">码:</span>

> 设 K 为R<U, F> 中属性或属性组

**Candidate Key 候选码：**K -^F^> U，则 K 为 R 的一个候选码

**Super Key 超码：** K -^P^> U。候选码的超集

**Primary Key：**若关系模式 R 有多个候选码，则选一个作

**Foreign Key 外码**



<span style="font-size:20px">范式:</span>

符合某一种级别关系模式的集合

**规范化：**低一级范式的关系模式通过模式分解转换为高一级范式的关系模式的集合 的过程

> 满足最低要求的为第一范式 1NF

+ **1 NF: **<span style="color:blue">每一个属性都是不可分解的</span>

+ **2 NF：**——是否单个主键

    消除非主属性对码的<span style="color:red">部分函数依赖</span>。每一个 非主属性 完全函数依赖于 任一码

    解决：将关系模式分解

+ **3 NF:** ——非主键属性间是否无依赖关系

    消除非主属性对码的<span style="color:red">传递函数依赖</span>。

+ **BCNF:**——主键属性间是否无依赖关系



<span style="font-size:20px">函数依赖集的闭包:</span>

+ **函数依赖集闭包：**

    **F^+^** F 的闭包——逻辑蕴涵的所有函数依赖

+ **属性集的闭包：**

    **X~F~^+^**——<span style="color:orange">属性集 X</span> 关于函数依赖集 F 的闭包（所有 X 可以决定的属性，包含自身）



## 数据依赖的公理系统

+ **Armstrong 公理系统:**

    + 自反律
    + 增广律 X -> Y——XZ -> YZ
    + 传递律
    + 合并 X -> Y, X -> Z——X -> YZ
    + 伪传递 X -> Y, WY -> Z——XW -> Z
    + 分解

+ **最小函数依赖集：**

    1. 分解：右边为多个的拆为一个

    2. 删去多余函数依赖：

        > Eg: B -> G 能通过 F - {B -> G} 推出来，则删去

    3. 删去每个函数依赖左边多余属性：

        > Eg: BC -> E 中 B -> C 能推出来，则左边删去 C



# Ch7.数据库设计

**基本步骤：**需求分析，概念结构设计，逻辑结构设计，物理结构设计，实施，维护

2. **概念结构设计**——<span style="color:blue">步骤：抽象数据，局部视图，合并取消冲突，修改重构消除冗余</span>

3. **逻辑结构设计**——E-R 向关系模型转换，关系模式<span style="color:blue">规范化</span>，设计用户子模式



# Ch8.数据库编程

## 嵌入式 SQL

**与主语言的通信：**

1. SQL 通信区

2. 主变量：使用主语言的程序变量输入输出数据
3. <span style="color:blue">游标：SQL 面向集合，主语言面向记录</span>。为协调两种，游标是数据缓冲区。



## 过程化 SQL

基本结构是块，命名块、匿名块

## 存储过程



# Ch9.关系查询处理和查询优化

## 代数优化

通过对<span style="color:blue">关系代数表达式的等价变换</span>提高查询效率



# Ch10.数据库恢复

<span style="font-size:20px">恢复的实现技术:</span>

数据库恢复的基础是利用转储的冗余数据。这些**转储的冗余数据**包括：<span style="color:blue">日志文件、数据库后备副本</span>

建立冗余数据最常用技术是数据转储和登记日志文件

**数据转储：**

> 定期将数据库恢复到转储时状态

+ 静态转储：转储期间不允许操作
+ 动态存储：允许存期或修改



## 事务

## 常见故障恢复

<span style="font-size:20px">事务故障恢复</span>

反向扫描<span style="color:blue">日志文件</span>，查找该事务更新操作

<span style="font-size:20px">系统故障恢复</span>

正向扫描<span style="color:blue">日志文件</span>，找出故障前已提交的事务

<span style="font-size:20px">介质故障恢复</span>

重装数据库，重做已完成事务

## 具有检查点的恢复技术



# Ch11.并发控制

![image-20220705005007604](E:%5CTypora%5Cupload%5Cimage-20220705005007604.png)

+ <span style="color:blue">**并发操作的问题**</span>——丢失修改、不可重复读、读脏数据
+ **并发控制的主要技术**：封锁，时间戳，乐观控制，多版本并发控制
+ 满足两阶段封锁协议的调度一定是：<span style="color:blue">可串行化调度</span>

## 封锁

+ **排他锁 / 写锁 X**——事务 T 允许修改或读取 A
+ **共享锁 / 读锁 S**——事务 T 只能读 A，其他只能给 A 加 S 锁



+ **一级封锁：**修改前必须加 X
+ **二级封锁：**一级封锁基础上，读取前必须先加 S，读取后释放

+ **三级封锁：**一级封锁基础上，读取前必须先加 S，事务结束后释放