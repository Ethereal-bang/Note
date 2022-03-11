# SpringMVC

- MVC是模型(<span style="color:red">Model</span>)、视图(<span style="color:red">View</span>)、控制器(<span style="color:red">Controller</span>)的简写，是一种软件设计规范。
- 是将**业务逻辑、数据、显示 分离**的方法来组织代码。
- MVC主要作用是**降低了视图与业务逻辑间的双向偶合**。
- MVC不是一种设计模式，**MVC是一种架构模式**。当然不同的MVC存在差异。

**最典型的MVC就是 JSP + servlet + javabean 的模式**



## Demo && 模板

1. **创建 Web 应用并引入 jar 包**。

2. **资源过滤:**

    ```xml
    <build>
       <resources>
           <resource>
               <directory>src/main/java</directory>
               <includes>
                   <include>**/*.properties</include>
                   <include>**/*.xml</include>
               </includes>
               <filtering>false</filtering>
           </resource>
           <resource>
               <directory>src/main/resources</directory>
               <includes>
                   <include>**/*.properties</include>
                   <include>**/*.xml</include>
               </includes>
               <filtering>false</filtering>
           </resource>
       </resources>
    </build>
    ```

3. **web.xml 配置：**

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
            version="4.0">
    
       <!--1.注册servlet-->
       <servlet>
           <servlet-name>SpringMVC</servlet-name>
           <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
           <!--通过初始化参数指定SpringMVC配置文件的位置，进行关联-->
           <init-param>
               <param-name>contextConfigLocation</param-name>
               <param-value>classpath:springmvc-servlet.xml</param-value>
           </init-param>
           <!-- 启动顺序，数字越小，启动越早 -->
           <load-on-startup>1</load-on-startup>
       </servlet>
    
       <!--所有请求都会被springmvc拦截 -->
       <servlet-mapping>
           <servlet-name>SpringMVC</servlet-name>
           <url-pattern>/</url-pattern>
       </servlet-mapping>
    
    </web-app>
    ```

    +  注册 DispatcherServlet
    +  关联 SpringMVC 配置文件
    +  启动级别为 1
    +  映射路径 /（/* 会 404）

4. **添加 MVC 配置文件:**

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:context="http://www.springframework.org/schema/context"
          xmlns:mvc="http://www.springframework.org/schema/mvc"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/context
           https://www.springframework.org/schema/context/spring-context.xsd
           http://www.springframework.org/schema/mvc
           https://www.springframework.org/schema/mvc/spring-mvc.xsd">
    
       <!-- 自动扫描包，让指定包下的注解生效,由IOC容器统一管理 -->
       <context:component-scan base-package="com.bei.controller"/>
       <!-- 让Spring MVC不处理静态资源 -->
       <mvc:default-servlet-handler />
       <!--
       支持mvc注解驱动
           在spring中一般采用@RequestMapping注解来完成映射关系
           要想使@RequestMapping注解生效
           必须向上下文中注册DefaultAnnotationHandlerMapping
           和一个AnnotationMethodHandlerAdapter实例
           这两个实例分别在类级别和方法级别处理。
           而annotation-driven配置帮助我们自动完成上述两个实例的注入。
        -->
       <mvc:annotation-driven />
    
       <!-- 视图解析器 -->
       <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"
             id="internalResourceViewResolver">
           <!-- 前缀 -->
           <property name="prefix" value="/WEB-INF/jsp/" />
           <!-- 后缀 -->
           <property name="suffix" value=".jsp" />
       </bean>
    
    </beans>
    ```

    +  让 IOC 注解生效
    +  静态资源过滤：HTML、JS、CSS、图片 ...
    +  MVC 注解驱动
    +  配置视图解析器
    
5. **创建 Controller：**

    ```java
    package com.kuang.controller;
    
    @Controller
    @RequestMapping("/HelloController")
    public class HelloController {
    
        //访问URL : 项目名/HelloController/hello
        @RequestMapping("/hello")
        public String sayHello(Model model){
            //向模型中添加属性msg与值，可以在JSP页面中取出并渲染
            model.addAttribute("msg","hello,SpringMVC");
    
            //web-inf/jsp/hello.jsp
            return "hello";
        }
    }
    ```

    + @Controller——让 Spring IOC 容器初始化时自动扫描到

    + @RequestMapping——映射请求路径

    + `Model model`——方法中声明 Model 类型的参数是为了把 Action 中的数据带到视图中

    + `return "hello"`——方法返回的结果是视图的名称`hello`，加上配置文件中的前后缀即 WEB-INF/jsp/**hello**.jsp

        ![image-20220219143250046](https://gitee.com/ethereal-bang/images/raw/master/20220219143257.png)

6. **创建视图层：** WEB-INF/jsp 目录

    ```jsp
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
    <head>
        <title>SpringMVC</title>
    </head>
    <body>
    <%--对应的是controller文件中设置的属性msg值--%>
    It`s ${msg}
    </body>
    </html>
    ```




## RestFul 风格

一个资源定位及资源操作的风格。不是标准也不是协议，只是一种风格。基于这个风格设计的软件可以更简洁，更有层次，更易于实现缓存等机制。



# Controller

- 控制器负责提供访问应用程序的行为，通常通过接口定义或注解定义两种方法实现。
- 控制器负责解析用户的请求并将其转换为一个模型。
- 在Spring MVC中一个控制器类可以包含多个方法
- 在Spring MVC中，对于Controller的配置方式有很多种



+ <span style="font-size:20px">@Controller:</span>

    ```java
    //@Controller注解的类会自动添加到Spring上下文中
    @Controller
    public class ControllerTest2{
    
       //映射访问路径
       @RequestMapping("/t2")
       public String index(Model model){
           //Spring MVC会自动实例化一个Model对象用于向视图中传值
           model.addAttribute("msg", "ControllerTest2");
           //返回视图位置
           return "test";
      }
    
    }
    ```



## 数据处理

### 提交数据

+ <span style="font-size:22px">域名称和处理方法参数名：</span>

    1. 一致：

        提交数据 : http://localhost:8080/hello?name=test

        处理方法 :

        ```java
        @RequestMapping("/hello")
        public String hello(String name){
           System.out.println(name);
           return "hello";
        }
        ```

    2. 不一致：

        http://localhost:8080/hello?username=test

        ```java
        @RequestMapping("/hello")
        public String hello(@RequestParam("username") String name){
           System.out.println(name);
           return "hello";
        }
        ```

+ <span style="font-size:22px">提交对象：</span>

    1. **创建实体类：**

        ```java
        public class User {
           private int id;
           private String name;
           private int age;
        	// ...
        }
        ```

    2. **提交数据：**http://localhost:8080/mvc04/user?name=kuangshen&id=1&age=15

        > 前端传递的参数名和对象名必须一致，否则就是null。

    3. **处理方法：**

        ```java
        @RequestMapping("/user")
        public String user(User user){
           System.out.println(user);
           return "hello";
        }
        ```

### 数据显示到前端

>  **三种方式的区别：**
>
> + Model 只有寥寥几个方法只适合用于储存数据，简化了新手对于Model对象的操作和理解；
>
> + ModelMap 继承了 LinkedMap ，除了实现了自身的一些方法，同样的继承 LinkedMap 的方法和特性；
>
> + ModelAndView 可以在储存数据的同时，可以进行设置返回的逻辑视图，进行控制展示层的跳转。

+ <span style="font-size:22px">通过 ModelAndView:</span>

    ```java
    public class ControllerTest1 implements Controller {
    
       public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
           //返回一个模型视图对象
           ModelAndView mv = new ModelAndView();
           mv.addObject("msg","ControllerTest1");
           mv.setViewName("test");
           return mv;
      }
    }
    ```

+ <span style="font-size:22px">通过 ModelMap：</span>

    ```java
    @RequestMapping("/hello")
    public String hello(@RequestParam("username") String name, ModelMap model){
       //封装要显示到视图中的数据
       //相当于req.setAttribute("name",name);
       model.addAttribute("name",name);
       System.out.println(name);
       return "hello";
    }
    ```

+ <span style="font-size:22px">通过 Model：</span>

    ```java
    @RequestMapping("/ct2/hello")
    public String hello(@RequestParam("username") String name, Model model){
       //封装要显示到视图中的数据
       //相当于req.setAttribute("name",name);
       model.addAttribute("msg",name);
       System.out.println(name);
       return "test";
    }
    ```



## 错误处理

+ **throw：**

    ```java
    @GetMapping("/register")
    public boolean register(User user) {
      // 若没提供相应参数，
      if (user.getAccount() == null || user.getPwd() == null) {
        throw new IllegalArgumentException("请提供合法参数！");
      }
      return userService.register(user);
    }
    ```

    > 上例，如果有请求没有带参数，后端控制台抛出相应错误：![image-20220311105352191](https://gitee.com/ethereal-bang/images/raw/master/20220311105359.png)
    >
    > 而前端请求到的响应体会报 500 服务器出错。



# 视图

## 跳转视图方式

```xml
<!-- 视图解析器 -->
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"
     id="internalResourceViewResolver">
   <!-- 前缀 -->
   <property name="prefix" value="/WEB-INF/jsp/" />
   <!-- 后缀 -->
   <property name="suffix" value=".jsp" />
</bean>
```

三种方式：

+ <span style="font-size:22px">ModelAndView：</span>——需视图解析器

    设置ModelAndView对象 , 根据view的名称 , 和视图解析器跳到指定的页面 .

    页面 : {视图解析器前缀} + viewName +{视图解析器后缀}

    ```java
    public class ControllerTest1 implements Controller {
    
       public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
           //返回一个模型视图对象
           ModelAndView mv = new ModelAndView();
           mv.addObject("msg","ControllerTest1");
           mv.setViewName("test");
           return mv;
      }
    }
    ```
    
+ <span style="font-size:22px">ServletAPI：</span>——不需视图解析器

    通过设置ServletAPI , 不需要视图解析器。

    + 通过HttpServletResponse进行输出
    + 通过HttpServletResponse实现重定向
    + 通过HttpServletResponse实现转发

    （依次对应下例 test1、2、3）
    
    ```java
    @Controller
    public class ResultGo {
    
       @RequestMapping("/result/t1")
       public void test1(HttpServletRequest req, HttpServletResponse rsp) throws IOException {
           rsp.getWriter().println("Hello,Spring BY servlet API");
      }
    
       @RequestMapping("/result/t2")
       public void test2(HttpServletRequest req, HttpServletResponse rsp) throws IOException {
           rsp.sendRedirect("/index.jsp");
      }
    
       @RequestMapping("/result/t3")
       public void test3(HttpServletRequest req, HttpServletResponse rsp) throws Exception {
           req.setAttribute("msg","/result/t3");
           req.getRequestDispatcher("/WEB-INF/jsp/test.jsp").forward(req,rsp);
      }
    
    }
    ```
    
+ <span style="font-size:22px">SpringMVC 实现转发、重定向：</span>——需视图解析器

    ```java
    @Controller
    public class ResultSpringMVC2 {
       @RequestMapping("/rsm2/t1")
       public String test1(){
           //转发
           return "test";
      }
    
       @RequestMapping("/rsm2/t2")
       public String test2(){
           //重定向
           return "redirect:/index.jsp";
           //return "redirect:hello.do"; //hello.do为另一个请求/
      }
    
    }
    ```



# 整合 SSM 框架

+ **配置文件：**
    + web部署配置：WEB-INF/web.xml 
    + spring配置：classpath:spring.xml 
    + spring-mybatis 整合配置

## MVC 三层架构

+ 控制层 Controller
+ 业务层 Service——Mapper 的基本操作外还有其他业务操作
+ 持久层 Dao/Mapper——操作数据库等的基本操作

## Demo && 模板

### 1.基本环境搭建

1. **新建 Maven 项目**，添加 Web 支持

2. **项目配置文件：**导入 jar、资源过滤

    ```xml
    <dependencies>
       <!--Junit-->
       <dependency>
           <groupId>junit</groupId>
           <artifactId>junit</artifactId>
           <version>4.12</version>
       </dependency>
       <!--数据库驱动-->
       <dependency>
           <groupId>mysql</groupId>
           <artifactId>mysql-connector-java</artifactId>
           <version>5.1.47</version>
       </dependency>
       <!-- 数据库连接池 -->
       <dependency>
           <groupId>com.mchange</groupId>
           <artifactId>c3p0</artifactId>
           <version>0.9.5.2</version>
       </dependency>
    
       <!--Servlet - JSP -->
       <dependency>
           <groupId>javax.servlet</groupId>
           <artifactId>servlet-api</artifactId>
           <version>2.5</version>
       </dependency>
       <dependency>
           <groupId>javax.servlet.jsp</groupId>
           <artifactId>jsp-api</artifactId>
           <version>2.2</version>
       </dependency>
       <dependency>
           <groupId>javax.servlet</groupId>
           <artifactId>jstl</artifactId>
           <version>1.2</version>
       </dependency>
    
       <!--Mybatis-->
       <dependency>
           <groupId>org.mybatis</groupId>
           <artifactId>mybatis</artifactId>
           <version>3.5.2</version>
       </dependency>
       <dependency>
           <groupId>org.mybatis</groupId>
           <artifactId>mybatis-spring</artifactId>
           <version>2.0.2</version>
       </dependency>
    
       <!--Spring-->
       <dependency>
           <groupId>org.springframework</groupId>
           <artifactId>spring-webmvc</artifactId>
           <version>5.1.9.RELEASE</version>
       </dependency>
       <dependency>
           <groupId>org.springframework</groupId>
           <artifactId>spring-jdbc</artifactId>
           <version>5.1.9.RELEASE</version>
       </dependency>
    </dependencies>
    ```

    ```xml
    <build>
       <resources>
           <resource>
               <directory>src/main/java</directory>
               <includes>
                   <include>**/*.properties</include>
                   <include>**/*.xml</include>
               </includes>
               <filtering>false</filtering>
           </resource>
           <resource>
               <directory>src/main/resources</directory>
               <includes>
                   <include>**/*.properties</include>
                   <include>**/*.xml</include>
               </includes>
               <filtering>false</filtering>
           </resource>
       </resources>
    </build>
    ```

3. **建立基本结构：**

    + dao
    + domain
    + service
    + controller

4. **配置框架：**

    + mybatisConfig.xml——Mybatis 配置文件

        ```xml
        <?xml version="1.0" encoding="UTF-8" ?>
        <!DOCTYPE configuration
               PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
               "http://mybatis.org/dtd/mybatis-3-config.dtd">
        <configuration>
        
        </configuration>
        ```

    + applicationContext.xml——Spring 配置文件



### 2.MyBatis 层编写
1. **数据库配置文件：**——database.properties

    ```
    jdbc.driver=com.mysql.jdbc.Driver
    jdbc.url=jdbc:mysql://localhost:3306/userSystem
    jdbc.username=root
    jdbc.password=theday1012
    ```

2. **Mybatis 核心配置文件**——mybatisConfig.xml

    ```xml
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE configuration
    PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
    "http://mybatis.org/dtd/mybatis-3-config.dtd">
    <configuration>
    
    <!--配置数据源，Spring完成-->
    
    <typeAliases>
    <package name="com.bei.domain"/>
    </typeAliases>
    <mappers>
    <mapper resource="com/bei/dao/UserMapper.xml"/>
    </mappers>
    
    </configuration>
    ```

3. **IDEA 连接数据库**（等会编写mapper.xml 才会有提示）

4. 编写**数据库对应的实体类**——com.bei.domain

    ```java
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public class User {
    private int age;
    private String mobile;
    private String password;
    }
    ```

    > lombok 插件使用注解快速生成

5. 编写 **Dao 层 Mapper 接口：**

    ```java
    public interface UserMapper {
      // int因为返回的是受影响行数
        int addUser(User user);
    
        User queryUserByMobile(String mobile);
    }
    ```

6. **Mapper 接口对应的 Mapper.xml：**

    ```xml
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper
            PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    
    <mapper namespace="com.bei.dao.UserMapper">
    
            <!--增加一个user-->
        <insert id="addUser" parameterType="User">
            insert into user.user
            values (#{mobile}, #{password}, #{age})
        </insert>
    
        <!--查询mobile,返回一个User-->
        <select id="queryUserByMobile" resultType="User">
            select * from user.user
            where mobile = #{mobile}
        </select>
    
    </mapper>
    ```

7. **Service 层的接口和实现类：**

    ```java
    public interface UserService {
        int addUser(User user);
        User queryUser(String name);
    }
    ```

    > Service 调 Dao 层

    ```java
    public class UserServiceImpl implements UserService {
        // service调用dao层，设置一个set，方便Spring管理
        private UserMapper userMapper;
        public void setUserMapper(UserMapper userMapper) {
            this.userMapper = userMapper;
        }
    
        public int addUser(User user) {
            return userMapper.addUser(user);
        }
        
        public User queryUser(String name) {
            return userMapper.queryUser(name);
        }
    }
    ```

    

### 3.Spring 层

1. 配置 **Spring 整合 MyBatis**——springDao.xml

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xmlns:comtext="http://www.springframework.org/schema/context"
           xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
    
        <!--1.关联数据库配置文件-->
        <comtext:property-placeholder location="classpath:database.properties" />
    
        <!--2.连接池-->
        <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
            <property name="driverClass" value="${jdbc.driver}" />
            <property name="jdbcUrl" value="${jdbc.url}" />
            <property name="user" value="${jdbc.username}" />
            <property name="password" value="${jdbc.password}" />
        </bean>
    
        <!--3.sqlSessionFactory-->
        <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
            <!-- 注入数据库连接池 -->
            <property name="dataSource" ref="dataSource" />
            <!--绑定mybatis配置文件-->
            <property name="configLocation" value="classpath:mybatisConfig.xml" />
        </bean>
    
        <!--4.配置dao接口扫描包，动态实现dao接口注入到spring容器-->
        <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
            <!--注入sqlSessionFactory-->
            <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory" />
            <!--需要扫描的dao包-->
            <property name="basePackage" value="com.bei.dao" />
        </bean>
    </beans>
    ```

2. **Spring 整合 Service 层：**——springService.xml

    ```xml
    <!--1.扫描service下的bean-->
    <context:component-scan base-package="com.bei.service" />
    
    <!--2.BookServiceImpl注入到IOC容器-->
    <bean id="UServiceImpl" class="com.bei.service.UserServiceImpl">
      <property name="userMapper" ref="userMapper" />
    </bean>
    
    <!--3.配置事务管理器-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
      <!-- 注入数据库连接池 -->
      <property name="dataSource" ref="dataSource" />
    </bean>
    ```



### 4.SpringMVC 层

1. **web.xml：**

    ```xml
    <!--DispatchServlet-->
    <servlet>
      <servlet-name>DispatcherServlet</servlet-name>
      <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
      <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:applicationContext.xml</param-value>
      </init-param>
      <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
      <servlet-name>DispatcherServlet</servlet-name>
      <url-pattern>/</url-pattern>
    </servlet-mapping>
    
    <!--encodingFilter乱码过滤-->
    <filter>
      <filter-name>encodingFilter</filter-name>
      <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
      <init-param>
        <param-name>encoding</param-name>
        <param-value>utf-8</param-value>
      </init-param>
    </filter>
    <filter-mapping>
      <filter-name>encodingFilter</filter-name>
      <url-pattern>/*</url-pattern>
    </filter-mapping>
    
    <!--Session过期时间-->
    <session-config>
      <session-timeout>15</session-timeout>
    </session-config>
    ```

2. **spring-mvc.xml：**

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xmlns:context="http://www.springframework.org/schema/context"
           xmlns:mvc="http://www.springframework.org/schema/mvc"
           xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/mvc
       https://www.springframework.org/schema/mvc/spring-mvc.xsd">
    
        <!-- 配置SpringMVC -->
        <!-- 1.开启SpringMVC注解驱动 -->
        <mvc:annotation-driven />
        <!-- 2.静态资源默认servlet配置-->
        <mvc:default-servlet-handler/>
    
        <!-- 3.配置jsp 显示ViewResolver视图解析器 -->
        <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
            <property name="viewClass" value="org.springframework.web.servlet.view.JstlView" />
            <property name="prefix" value="/WEB-INF/jsp/" />
            <property name="suffix" value=".jsp" />
        </bean>
    
        <!-- 4.扫描web相关的bean -->
        <context:component-scan base-package="com.bei.controller" />
    
    </beans>
    ```

    > Note：要导入约束

3. **Spring 配置整合文件**——applicationContext.xml

    ```xml
    <import resource="springDao.xml"/>
    <import resource="springService.xml"/>
    <import resource="springMVC.xml"/>
    ```



### 5.功能整合





# Ref

+ SpringMVC：

    [狂神说SpringMVC02：第一个MVC程序](https://mp.weixin.qq.com/s?__biz=Mzg2NTAzMTExNg==&mid=2247483978&idx=1&sn=6711110a3b2595d6bb987ca02ee0a728&scene=19#wechat_redirect)

    [狂神说SpringMVC03：RestFul和控制器](https://mp.weixin.qq.com/s?__biz=Mzg2NTAzMTExNg==&mid=2247483993&idx=1&sn=abdd687e0f360107be0208946a7afc1d&scene=19#wechat_redirect)

+ Controller：

    [狂神说SpringMVC03：RestFul和控制器](https://mp.weixin.qq.com/s?__biz=Mzg2NTAzMTExNg==&mid=2247483993&idx=1&sn=abdd687e0f360107be0208946a7afc1d&scene=19#wechat_redirect)




# DEBUG

## 整合 SSM 框架

+ **数据存储后于数据库字段不对应：**

    + Q_Desc：

        ```java
        public class User {
            private String mobile;
            private String password;
            private int age;
        }
        ```

        数据库字段顺序：password、age、mobile

    + A_Solu：将 User 属性顺序与数据库对应