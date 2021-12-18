# MyBatis

+ 优秀的持久层框架
+ 它支持自定义 SQL、存储过程以及高级映射
+ MyBatis 免除了几乎所有的 JDBC 代码以及设置参数和获取结果集
+ MyBatis 可以通过简单的 XML 或注解来配置和映射原始类型、接口和 Java POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录



+ **地址：**

    ```xml
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>x.x.x</version>
    </dependency>
    ```



## 持久化

将数据持久化，持久化——将程序的数据在持久状态和瞬时状态转化的过程

+ 因为内存断电即失。

+ 数据库（jdbk）、io 文件持久化

+ 为什么需要持久化：

    不丢失某些对象、内存昂贵

+ **持久层：**

    Dao 层（数据持久层）、Service 层（业务逻辑层）、Controller 层

    + 完成持久化工作的代码块



## Why MyBatis

+ 将数据存入到数据库
+ 传统 JDBC 代码复杂

+ 灵活：sql 写在 xml 里 便于统一管理和优化，通过 sql 语句可以满足数据库的所有需求
+ 解除 sql 与程序代码的耦合，通过提供 DAO 层将业务逻辑和数据访问逻辑分离



+ <span style="font-size:22px">MyBatis 的功能架构：</span>
    1. **API接口层：**提供给外部使用的接口API，开发人员通过这些本地API来操纵数据库。接口层一接收到调用请求就会调用数据处理层来完成具体的数据处理。
    2. 数据处理层：负责具体的SQL查找、SQL解析、SQL执行和执行结果映射处理等。它主要的目的是根据调用的请求完成一次数据库操作。
    3. **基础支撑层：**负责最基础的功能支撑，包括连接管理、事务管理、配置加载和缓存处理，这些都是共用的东西，将他们抽取出来作为最基础的组件。为上层的数据处理层提供最基础的支撑。



# MyBatis + Maven 搭建程序

1. "SqlMapConfig.xml"文件：

    ```xml
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE configuration
            PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-config.dtd">
    
    <!--核心配置文件：-->
    <configuration>
        <environments default="development">    <!--选择默认环境-->
            <environment id="development">  <!--包含事务管理和连接池配置-->
                <transactionManager type="JDBC"/>
                <dataSource type="POOLED">
                    <property name="driver" value=""/>
                    <property name="url" value="jdbc:mysql://localhost:3306/task7"/>
                    <property name="username" value="${username}"/>
                    <property name="password" value="${password}"/>
                </dataSource>
            </environment>
        </environments>
        <mappers>   <!--包含映射器mapper：-->
            <!--每一个Mapper.xml都需在MyBatis核心配置文件中注册-->
            <mapper resource="SqlMapConfig.xml"/>
        </mappers>
    </configuration>
    ```

2. <span style="font-size:22px">获取 SqlSession：</span>

    1. 从 XML 中构建 SqlSessionFactory：

        ```java
        String resource = "SqlMapConfig.xml";
        
        InputStream inputStream = Resources.getResourceAsStream(resource);
        
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream)
        ```

    2. 从 SqlSessionFactory 获取 SqlSession：

        SqlSession 提供了在数据库执行 SQL 命令所需的所有方法。

        ```java
        sqlSession = factory.openSession()
        ```

        可以通过 SqlSession 实例来直接执行已映射的 SQL 语句

3. com.user.domain，`Account.java`类：

    ```java
    public class Account {
        private String id;
        private String name;
        private int money;
        private Date createTime;
        private Date updateTime;
    		// getter
      	// setter
    }
    ```

4. Dao 接口，com.user.dao，`AccountDao.java`：

    ```java
    public interface AccountDao {
        // 1.查询所有记录
        List<Account> findAll();
    
        // 2.通过id删除记录
        int deleteByPrimary(String id);
    
        // 3.插入记录
        int insert(Account record);
    
        // 4.通过id查找对象
        Account selectByPrimaryKey(String id);
    
        // 5.更新Account
        int updateByPrimaryKey(Account record);
    }
    ```

5. Mapper 配置文件——利用 xml 定义 sql 语句（，`AccountDao.xml`相当于`AccountMapper.xml`）：

    ```xml
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper
            PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    
    <mapper namespace="com.user.dao.AccountDao">
        <!--查询语句-->
        <select id="findAll" resultType="com.user.domain.Account">	<!--对应方法名-->
            select * from task7.account;
        </select>
    </mapper>
    ```

    + namespace 绑定一个对应的 Dao / Mapper 接口，包名与接口包名要一致
    + SQL 语句 id 名对应原来的方法名
    + `resultType`查询结果的返回类型



# CRUD——增删改查

Create、Retrieve、Update、Delete

## Select

+ 查询所有

    ```xml
    <select id="findAll" resultType="com.user.domain.Account">
        select * from task7.account;
    </select>
    ```

+ 条件查询

    ```xml
    <select id="selectByPrimaryKey" parameterType="String" resultType="com.user.domain.Account">
      select * from task7.account where id = #{id}
    </select>
    ```

    ```java
    // AccountService.java
    Account selectByPrimaryKey(String id) {
            return sqlSession.getMapper(AccountDao.class).selectByPrimaryKey(id);
    }
    ```

## Insert

```xml
<!--插入部分字段-->
<insert id="insert" parameterType="com.user.domain.Account">
  insert into account (id, name, money, createtime) 
  values (#{id},#{name},#{money},#{createTime});
</insert>

<!--插入全部字段-->
<insert id="insert" parameterType="com.user.domain.Account">
  insert into account 
  values (#{id},#{name},#{money},#{createTime});
</insert>
```

+ 注意传参时`#{}`和`${}`的区别，`#{}`对传入数据加一个`""`，很大程度防止 SQL 注入

+ 注意向 MySQL 中 Date 类型添加数据时容易出现问题，此处这样解决（没有时分秒）：

    ```java
    java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
    ```

+ MyBatis 在更新数据库数据后，要手动提交事务：

    ```java
    sqlSession.commit();
    ```

## Update

```xml
<update id="updateByPrimaryKey" parameterType="com.user.domain.Account">
  update account
  set id=#{id},
  name=#{name},
  money=#{money},
  updatetime=#{updateTime}
  where id = #{id};
</update>
```

## Delete

```xml
<delete id="deleteByPrimary" parameterType="String">
    delete
    from account
    where id = #{id};
</delete>
```

```java
public void deleteByPrimaryKey(String id) {
  sqlSession.getMapper(AccountDao.class).deleteByPrimary(id);
}
```



# 参考

+ 总：

    [【狂神说Java】Mybatis最新完整版IDEA版通俗易懂\_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1NE411Q7Nx)

+ CRUD:

    [mybatis中的#{}和${}\$区别_u0135522450的专栏-CSDN博客](https://blog.csdn.net/u013552450/article/details/72528498)

    [java中Date类型存储到mysql_卓尔游侠-CSDN博客](https://blog.csdn.net/u010928364/article/details/50109193)

    [mybatis插入更新删除数据时，成功但是不能写入数据库-CSDN博客](https://blog.csdn.net/chenbetter1996/article/details/82727952)