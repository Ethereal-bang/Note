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



# IoC 创建对象的方式

+ `<bean>`的使用需要与对应 class 的**构造函数对应**。![image-20220205203819248](https://gitee.com/ethereal-bang/images/raw/master/20220205203819.png)
+ **配置文件加载时，容器中管理的对象就已初始化了——**bean 对象创建即使不赋值不调用，该类的默认构造函数也会调用-即该类也会被实例化

<hr/>

+ 默认，**无参构造创建：**

    ```xml
    <!--变量名为name字段，值为value字段-->
    <property name="name" value="test IoC对象"></property>
    ```

    > **关于 class 的无参构造函数：**
    >
    > 使用无参构造时，即使 class 内没有显式声明构造函数也不会报错，因为会默认创建无参构造函数。
    >
    > **setName 方法的作用：**
    >
    > ![image-20220205205435756](https://gitee.com/ethereal-bang/images/raw/master/20220205205435.png)
    >
    > 当构造函数没有为类属性赋值时，bean 变量通过 setter 赋值。

+ **下标赋值：**

    ```java
    <constructor-arg index="0" value="test1"></constructor-arg>
    ```

    > 这种情况 class 内需有对应构造函数：
    >
    > ```java
    > public User(String name) {
    >   this.name = name;
    > }
    > ```

+ **类型赋值：**（不建议）

    ```java
    <constructor-arg type="java.lang.String" value="类型赋值"></constructor-arg>
    ```

+ **直接通过参数名设置：**

    ```java
    <constructor-arg name="name" value="直接通过参数名赋值"></constructor-arg>
    ```



# Spring 配置

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



# DI 依赖注入





# Ref

+ ：

    [【狂神说Java】Spring5最新完整教程IDEA版通俗易懂\_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1WE411d7Dv)

+ Spring:

    [Spring 教程_w3cschool](https://www.w3cschool.cn/wkspring/)

+ Demo:

    [什么是POJO，JavaBean？- 简书](https://www.jianshu.com/p/6f3e2bd50cb1)

    [Spring 概述_w3cschool](https://www.w3cschool.cn/wkspring/dgte1ica.html)