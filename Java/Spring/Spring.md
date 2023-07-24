# Spring

企业级 Java 应用程序开发框架

Spring 框架的核心特性是可以用于开发任何 Java 应用程序，但是在 Java EE 平台上构建 web 应用程序是需要扩展的。 Spring 框架的目标是使 J2EE 开发变得更容易使用，通过启用基于 POJO 编程模型来促进良好的编程实践

+ **三层架构：**

    - A 表现层  web层  MVC是表现层的一个设计模型 

    - B 业务层 service层

    - C 持久层 dao层

+ **体系结构：**

    ![Spring 体系结构](https://atts.w3cschool.cn/attachments/image/wk/wkspring/arch1.png)

+ **jar 包：**

    ```xml
    <!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.3.15</version>
    </dependency>
    ```

    > 下载 Spring-webmvc 后会自动下载其他相关包
    >
    >  <img src="https://gitee.com/ethereal-bang/images/raw/master/20220205102859.png" alt="image-20220205102858954" style="zoom:53%;" />

## Spring 的 IoC 容器本质研究

Spring 框架的控制反转(IoC)容器

+ 项目结构：<img src="https://gitee.com/ethereal-bang/images/raw/master/20220205104906.png" alt="image-20220205104906494" style="zoom:63%;" />

1. **UserDao 接口：**

    ```java
    public interface UserDao {
        void getUser();
    }
    ```

2. **UserDaoImpl 实现类：**(impl 即 implements)

    ```java
    public class UserDaoImpl implements UserDao {
        public void getUser() {
            System.out.println("默认获取用户的数据");
        }
    }
    ```

3. **UserService 业务接口：**

    ```java
    public interface UserService {
        void getUser();
    }
    ```

4. **UserServiceImpl 业务实现类：**

    ```java
    public class UserServiceImpl implements UserService {
        private UserDao userDao;
    
        // 利用set动态实现值的注入
        public void setUserDao(UserDao userDao) {
            this.userDao = userDao;
        }
    
        public void getUser() {
            userDao.getUser();
        }
    }
    ```

+ test 文件夹下：

    ```java
    public class MyTest {
        public static void main(String[] args) {
            // 用户实际调用的是业务层Service，不需接触Dao层
            UserService userService = new UserServiceImpl();
    
            ((UserServiceImpl) userService).setUserDao(new UserDaoImpl());
        }
    	  		userService.getUser();
    }
    ```

    之前是程序主动创建对象，现在使用 set 注入后程序变成了被动接受对象，控制权从程序员转交给用户。——这就是 IoC 控制反转

控制反转 IoC(Inversion of Control) 是一种设计思想，DI( 依赖注入)是实现 IoC 的一种方法



# Demo

> **pojo:**
>
> Plain Old Java Object——强调这是一个普通 java 对象，这个类没有实现/继承任何特殊的java接口或者类，不遵循任何主要java模型，约定或者框架的java对象。在理想情况下，POJO不应该有注解。

项目结构：<img src="https://gitee.com/ethereal-bang/images/raw/master/20220205121022.png" alt="image-20220205121022745" style="zoom:60%;" />

1. **配置元数据——**创建 Bean 的 XML 配置文件于 resources 目录，通常命名为 Beans.xml。

    Beans.xml 用于给不同的 bean 分配唯一的 ID，并且控制不同值的对象的创建而不影响源文件(*例如下例为`str`变量传递任何值*)

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">
      
    		<!--这里id不一定于class对应-->
        <bean id="hello" class="com.bei.pojo.Hello">
            <property name="str" value="SpringStr" />
        </bean>
    </beans>
    ```

    ref 引用 Spring 容器中创建好的对象，value 为具体的值 基本数据类型

2. **实例化容器：**

    ```java
    // 获取Spring的上下文对象:
    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    ```

3. **使用 Spring 管理的对象：**

    ```java
    public class MyTest {
        public static void main(String[] args) {
            // 获取Spring的上下文对象:
            ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
            // 对象现在都在Spring中的管理了，要使用直接去里面取出来:
            Hello hello = (Hello) context.getBean("hello");
            System.out.println(hello.toString());
        }
    }
    ```

现在不需要在程序中改动，要实现不同操作只需在 xml 配置文件中进行修改，对象由 Spring 创建、管理、装配。



# Bean 对象

## IoC 创建对象方式

- **配置文件加载时，容器中管理的对象就已初始化了——**bean 对象创建即使不赋值不调用，该类的默认构造函数也会调用-即该类也会被实例化

- **依赖注入**（Dependency Injection,DI）。

    - 依赖 : 指Bean对象的创建依赖于容器 . Bean 对象的依赖资源 .
    - 注入 : 指 Bean 对象所依赖的资源 , 由容器来设置和装配 .

    依赖注入分为<span style="color:red">构造器注入</span>、<span style="color:red">set 注入</span>（重点）

### 构造器注入

![image-20220205203819248](https://gitee.com/ethereal-bang/images/raw/master/20220205203819.png)

+ 默认，**无参构造创建：**

    ```xml
    <!--变量名为name字段，值为value字段-->
    <property name="name" value="test IoC对象"></property>
    ```

    > **关于 class 的无参构造函数：**使用无参构造时，即使 class 内没有显式声明构造函数也不会报错，因为会默认创建无参构造函数。

    > 除了这种情况外 class 内都需有对应构造函数：
    >
    > ```java
    > public User(String name) {
    > 	this.name = name;
    > }
    > ```

+ **下标赋值：**

    ```java
    <constructor-arg index="0" value="test1"></constructor-arg>
    ```

+ **类型赋值：**（不建议）

    ```java
    <constructor-arg type="java.lang.String" value="类型赋值"></constructor-arg>
    ```

+ **直接通过参数名设置：**

    ```java
    <constructor-arg name="name" value="直接通过参数名赋值"></constructor-arg>
    ```

+ > **setName 方法的作用：**![image-20220205205435756](https://gitee.com/ethereal-bang/images/raw/master/20220205205435.png)当构造函数没有为类属性赋值时，bean 变量通过 setter 赋值。也就是下面的 set 注入。

### Set 注入

要求被注入的属性 , 必须有 setter 方法。

```xml
<!--常量注入-->
<property name="name" value="Mike" />
<!--bean-->
<property name="address" ref="addr" />  <!--ref是引用bean-->
<!--数组-->
<property name="books">
  <array>
    <value>西游记</value>
    <value>红楼梦</value>
  </array>
</property>
<!--List-->
<property name="hobbies">
  <list>
    <value>听歌</value>
    <value>看电影</value>
    <value>爬山</value>
  </list>
</property>
<!--Map-->
<property name="card">
  <map>
    <entry key="中国邮政" value="456456456465456"/>
    <entry key="建设" value="1456682255511"/>
  </map>
</property>
<!--set注入-->
<property name="games">
  <set>
    <value>LOL</value>
    <value>CSGO</value>
  </set>
</property>
<!--null-->
<property name="wife"><null/></property>
<!--Properties-->
<property name="info">
  <props>
    <prop key="学号">20190604</prop>
    <prop key="性别">男</prop>
    <prop key="姓名">小明</prop>
  </props>
</property>
```



## Bean 作用域 scope

| Scope 类别 | 说明                                                         |
| ---------- | ------------------------------------------------------------ |
| singleton  | Spring IoC 容器中仅存一个 Bean 实例，默认                    |
| prototype  | 每次调用 getBean()，相当于执行 new XxxBean()                 |
| request    | 每次 HTTP 请求都会创建一个新 Bean（该 scope 仅适用于 WebApplicationContext 环境 |
| session    | 同一 HTTP Session 共享一 Bean（该 scope 仅适用于 WebApplicationContext 环境 |

```xml
<!--singleton:-->
<bean id="ServiceImpl" class="cn.csdn.service.ServiceImpl" scope="singleton">
```



## Bean 的自动装配

+ **自动装配：**

    Spring 在应用上下文中为某个 bean 寻找其依赖的 bean。

+ **三种装配机制：**

    + 在 xml 中显式配置
    + 在 java 中显式配置
    + 隐式 bean 发现机制和自动装配

+ <span style="font-size:22px">使用注解：</span>

    jdk1.5 开始支持注解，spring2.5 开始全面支持注解

    1. 导入约束
    2. 配置注解支持（*没做这两步运行时会报空指针 null point*）

    ```diff
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xmlns:context="http://www.springframework.org/schema/context"
           xsi:schemaLocation="http://www.springframework.org/schema/beans
            https://www.springframework.org/schema/beans/spring-beans.xsd
    +        http://www.springframework.org/schema/context
    +        https://www.springframework.org/schema/context/spring-context.xsd">
    
    +    <context:annotation-config/>
    ```

    3. 类中使用注解：

        + @Autowired——按类型自动装配，找到多个再按名字 id

            ```java
            @Autowired(required = false)    //required=false允许对象为null
            private Dog dog;
            @Autowired
            private Cat cat;
            ```

            ```xml
            <bean id="cat" class="com.bei.pojo.Cat"/>
            <bean id="dog" class="com.bei.pojo.Dog"/>
            <bean id="people" class="com.bei.pojo.People"/>
            ```

            ```js
            ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
            People people = context.getBean("people", People.class);
            people.getDog().shout();
            ```

            > `People people = context.getBean("people", People.class)`相当于`= (People) context.getBean("people")`

        + @AutoWired @Qualifier——匹配 id

            ```java
            @Autowired
            @Qualifier(value = "cat2")
            private Cat cat;
            ```

        + @Resource——匹配指定 id，没有匹配默认 id，再匹配类型

            ```java
            @Resource(name = "cat2")
            private Cat cat;
            ```

            

# Spring 配置

## XML

即 resources 的 xml 文件。

+ <span style="font-size:22px">Bean 的配置：</span>

    **id**——bean 的唯一标识对象名；**class**——bean 对应的<span style="color:red">全限定名</span>(*包名+类名* )

+ <span style="font-size:22px">alias——别名：</span>

    ```xml
    <bean id="user" class="com.bei.pojo.User">
    		<constructor-arg name="name" value="直接通过参数名赋值"></constructor-arg>
      	<alias name="user" alias="u" />
    ```

    ```java
    User user = (User) context.getBean("u");
    ```

    这样通过别名也能取到原来变量名的 value

+ <span style="font-size:22px">import——多个配置文件的导入</span>

    ```xml
        <import resource="beans.xml" />
    </beans>
    <!--applicationContext.xml-->
    ```

    使用时直接使用总的配置。

## 基于 Java 类配置——JavaConfig

> JavaConfig 原来是 Spring 的一个子项目，它通过 Java 类的方式提供 Bean 的定义信息，在 Spring4 的版本， JavaConfig 已正式成为 Spring4 的核心功能 

1. 编写实体类：

    ```java
    @Component  //将这个类标注为Spring的一个组件，放到容器中！
    public class Dog {
       public String name = "dog";
    }
    ```

2. 新建一 config 配置包——编写一个 MyConfig 配置类：

    ```java
    @Configuration  //代表这是一个配置类
    @Import(MyConfig2.class)  //导入合并其他配置类，类似于配置文件中的 inculde 标签
    public class MyConfig {
       @Bean //通过方法注册一个bean，这里的返回值就Bean的类型，方法名就是bean的id
       public Dog dog(){
           return new Dog();
      }
    }
    ```

3. Test:

    ```java
    @Test
    public void test2(){
       ApplicationContext applicationContext =
               new AnnotationConfigApplicationContext(MyConfig.class);
       Dog dog = (Dog) applicationContext.getBean("dog");
       System.out.println(dog.name);
    }
    ```




# 注解开发

> 实际开发很少将 bean 配到配置文件里。

1. 配置扫描哪些包：

    ```xml
    <!--指定要扫描的包，这个包下注解才生效-->
    <context:component-scan base-package="com.bei.dao" />
    <context:annotation-config/>
    ```

2. 指定包下编写类的注解：

    ```java
    @Component("user") 
    @Scope("prototype")
    public class User {
        public String name = "bei";
      
        @Value("17")
        public int age;
    }
    ```

> 1. **<span style="font-size:20px">Bean 注入：</span>**
>
>      等价于 `<bean id="user" class=".." />` 不指定id则默认类名的小写
>
>     @Component——衍生注解（*这四个注解功能相同，只是语义化* ）：
>
>     1. @Repository——dao 层
>     2. @Service——service 层
>     3. @Controller——controller 层
>
> 2. **<span style="font-size:20px">属性注入:</span>**
>
>     属性注入 `<property name="age" value="17" />`
>
>     @Value
>
> 3. **<span style="font-size:20px">Scope:</span>**
>
>     @Scope

+ XML 配置与注解：
    + XML 更普适，结构清晰，维护方便
    + 注解只能使用自己提供的类，开发方便
+ **最佳实践——XML 与注解整合开发：**
    1. XML 管理 Bean（*可以不用扫描——为了类上注解* ）
    2. 注解完成属性注入



# AOP

## 代理模式

Spring AOP[^1] 的底层机制就是动态代理

在不改变原来的代码的情况下，实现了对原有功能的增强，这是AOP中最核心的思想

### 静态代理

+ **角色分析：**
    + 抽象角色 : 一般使用接口或者抽象类来实现
    + 真实角色 : 被代理的角色
    + 代理角色 : 代理真实角色 ; 代理真实角色后 , 一般会做一些附属的操作 .
    + 客户         :  使用代理角色来进行一些操作 

实例见 testSpring/spring-06-Proxy demo1

+ **缺点：**

    类多了 , 多了代理类 , 工作量变大、开发效率降低（*因此需要动态代理* )

### 动态代理

- 动态代理的**角色**和静态代理的一样

- 动态代理的**代理类**是动态生成的 . 静态代理的代理类是我们提前写好的

- 动态代理分为两类 : 一类是**基于接口动态代理** , 一类是**基于类的动态代理**

- - 基于接口的动态代理----JDK动态代理
    - 基于类的动态代理--cglib
    - 现在用的比较多的是 javasis 来生成动态代理
    - 这里用 JDK 的原生代码来实现，其余的道理都是一样

实例见 testSpring/spring-06-Proxy demo2

1. 可以编写一个通用的动态代理实现的类：

    ```java
    public class ProxyInvocationHandler implements InvocationHandler {
       private Object target;
    
       public void setTarget(Object target) {
           this.target = target;
      }
    
       //生成代理类
       public Object getProxy(){
           return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                   target.getClass().getInterfaces(),this);
      }
    
       // proxy : 代理类
       // method : 代理类的调用处理程序的方法对象.
       public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
           log(method.getName());
           Object result = method.invoke(target, args);
           return result;
      }
    
       public void log(String methodName){
           System.out.println("执行了"+methodName+"方法");
      }
    }
    ```

2. Test:

    ```java
    public class Test {
       public static void main(String[] args) {
           //真实对象
           UserServiceImpl userService = new UserServiceImpl();
         	 // 通过调用程序处理角色来处理要调用的接口对象
           ProxyInvocationHandler pih = new ProxyInvocationHandler();
           pih.setTarget(userService); //设置要代理的对象
           UserService proxy = (UserService)pih.getProxy(); //动态生成代理类
           
         proxy.delete();// 代理执行方法
      }
    }
    ```

一个动态代理 , 一般代理某一类业务 , 一个动态代理可以代理多个类，代理的是接口



## 注解实现 AOP



# 整合 MyBatis

## MyBatis 回忆

1. <span style="font-size:20px">编写实体类：</span>——（User）对应于 MySQL 的表，字段对应列

    ```java
    public class User {
        private int id;  
        private String name;  
        private String pwd;   
    }
    ```

2. <span style="font-size:20px">MyBatis 的配置文件:</span>

    ```xml
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE configuration
            PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-config.dtd">
    <configuration>
    
        <typeAliases>
            <package name="com.bei.pojo"/>
        </typeAliases>
    
        <environments default="development">
            <environment id="development">
                <transactionManager type="JDBC"/>
                <dataSource type="POOLED">
                    <property name="driver" value="com.mysql.jdbc.Driver"/>
                    <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;useUnicode=true&amp;characterEncoding=utf8"/>
                    <property name="username" value="root"/>
                    <property name="password" value="123456"/>
                </dataSource>
            </environment>
        </environments>
    
        <mappers>
    	      <mapper class="com.bei.mapper.UserMapper" />
        </mappers>
    </configuration>
    ```

3. <span style="font-size:20px">编写 mapper 接口</span>——对应操作该表的语句

    ```java
    public interface UserMapper {
        public List<User> selectUser();
    }
    ```

4. <span style="font-size:20px">接口的映射文件:</span>

    ```java
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper
            PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <mapper namespace="com.bei.dao.UserMapper">
    
        <select id="selectUser" resultType="User">
            select * from testspring.user;
        </select>
    
    </mapper>
    ```

5. <span style="font-size:20px">测试:</span>

<hr />

+ <span style="font-size:20px">配置：</span>

    1. **引入 jar 包：**项目名称 artifactId）
        + junit
        + mybatis、mysql-connector-java（mySql 要么 5. 要么 8. 比较稳定)
        + spring-webmvc、spring-jdbc
        + aspectjweaver（*aspectJ AOP 织入器*）
        + mybatis-spring（*mybatis-spring 整合包 【重点】*）

    2. **配置Maven静态资源过滤问题：**

        ```xml
        <build>
           <resources>
               <resource>
                   <directory>src/main/java</directory>
                   <includes>
                       <include>**/*.properties</include>
                       <include>**/*.xml</include>
                   </includes>
                   <filtering>true</filtering>
               </resource>
           </resources>
        </build>
        ```

## 整合方式一

1. **DataSource:** 使用Spring的数据源替换MyBatis的配置

    ```xml
    <bean id="datasource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
      <property name="driverClassName" value="com.mysql.jdbc.Driver" />
      <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;useUnicode=true&amp;characterEncoding=utf8"/>
      <property name="username" value="root"/>
      <property name="password" value="theday1012"/>
    </bean>
    ```

2. **sqlSessionFactory 配置，关联 MyBatis:**

    ```xml
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
      <property name="dataSource" ref="dataSource"/>
      <!--关联Mybatis-->
      <property name="configLocation" value="classpath:mybatis-config.xml"/>
      <property name="mapperLocations" value="classpath:com/bei/mapper/*.xml"/>
    </bean>
    ```

3. **注册sqlSessionTemplate，关联sqlSessionFactory:**

    ```xml
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
       <!--利用构造器注入-->
       <constructor-arg index="0" ref="sqlSessionFactory"/>
    </bean>
    ```

4. **增加 Mapper 接口的实用类，私有化sqlSessionTemplate:**

    ```java
    public class UserMapperImpl implements UserMapper {
        // 现在所有操作都使用sqlSessionTemplate执行
    
        private SqlSessionTemplate sqlSession;
    
        public void setSqlSession(SqlSessionTemplate sqlSession) {
            this.sqlSession = sqlSession;
        }
    
        public List<User> selectUser() {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return mapper.selectUser();
        }
    }
    ```

5. **注册 bean 实现:**

    ```java
    <bean id="userMapper" class="com.bei.mapper.UserMapperImpl">
      <property name="sqlSession" ref="sqlSession" />
    </bean>
    ```

6. **Test:**

    ```java
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    UserMapper mapper = (UserMapper) context.getBean("userMapper");
    List<User> user = mapper.selectUser();
    System.out.println(user);
    ```

## 整合方式二（更优）

1. **DataSource:** 使用Spring的数据源替换MyBatis的配置

    ```xml
    <bean id="datasource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
      <property name="driverClassName" value="com.mysql.jdbc.Driver" />
      <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;useUnicode=true&amp;characterEncoding=utf8"/>
      <property name="username" value="root"/>
      <property name="password" value="theday1012"/>
    </bean>
    ```

2. **sqlSessionFactory 配置，关联 MyBatis:**

    ```xml
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
      <property name="dataSource" ref="dataSource"/>
      <!--关联Mybatis-->
      <property name="configLocation" value="classpath:mybatis-config.xml"/>
      <property name="mapperLocations" value="classpath:com/bei/mapper/*.xml"/>
    </bean>
    ```

3. **增加 Mapper 接口的实用类，继承 SqlSessionDaoSupport 类:**(*这步替代了方法一的3、4步，其余步骤相同* )

    ```java
    public class UserMapperImpl2 extends SqlSessionDaoSupport implements UserMapper2 {
        public List<User> selectUser() {
            UserMapper mapper = getSqlSession().getMapper(UserMapper.class);
            return mapper.selectUser();
        }
    }
    ```
    
4. **注册 bean 实现:**

    ```java
    <bean id="userMapper" class="com.bei.mapper.UserMapperImpl">
      <property name="sqlSessionFactory" ref="sqlSessionFactory" />
    </bean>
    ```

5. **Test:**

    ```java
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    UserMapper mapper = (UserMapper) context.getBean("userMapper");
    List<User> user = mapper.selectUser();
    System.out.println(user);
    ```

## 事务管理

**事务：**把一系列的动作当成一个独立的工作单元，这些动作要么全部完成，要么全部不起作用



# Ref

+ ：

    [【狂神说Java】Spring5最新完整教程IDEA版通俗易懂\_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1WE411d7Dv)

+ Spring:

    [Spring 教程_w3cschool](https://www.w3cschool.cn/wkspring/)

+ Demo:

    [什么是POJO，JavaBean？- 简书](https://www.jianshu.com/p/6f3e2bd50cb1)

    [Spring 概述_w3cschool](https://www.w3cschool.cn/wkspring/dgte1ica.html)



# Debug

+ Q：resources 目录下 spring-dao.xml 不能识别为配置文件类型

    A：改名为 springDao.xml



[^1]:面向切片编程

