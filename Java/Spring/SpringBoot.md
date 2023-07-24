# SpringBoot

一个 javaweb 的开发框架，和 SpringMVC 类似，对比其他 javaweb 框架的好处——简化开发，约定大于配置

并不是用来替代 Spring 的解决方案，而是和 Spring 框架紧密结合用于提升 Spring 开发者体验的工具



# Demo

## 项目创建

使用 IDEA 直接创建 SpringBoot 项目：

1. <img src="https://gitee.com/ethereal-bang/images/raw/master/20220222140342.png" alt="image-20220222140335022" style="zoom:53%;" />

2. 勾选 Spring Web （这里如果勾上 Developer Tools 的 “Spring Boot DevTools” 则可热部署）

    <img src="https://gitee.com/ethereal-bang/images/raw/master/20220222140434.png" alt="image-20220222140434501" style="zoom:33%;" />

（第一次下包会很慢）

## 项目结构

基础项目包含以下：

+ **程序的主启动类**（*主入口*）（路径 `com.bei.<项目名>`）

    ![image-20220222142331511](https://gitee.com/ethereal-bang/images/raw/master/20220222142331.png)

    > 启动后，访问 localhost:8080	（不需手动配置 Tomcat）
    >
    > ![image-20220222142618164](https://gitee.com/ethereal-bang/images/raw/master/20220222142618.png)

+ **application.properties**——配置文件

+ **测试类**

+ **pom.xml**

其他包创建在入口类的同级目录下。

## HTTP 接口

1. 同级目录下**新建 controller 包**。

2. **新建类** HelloController：

    ```java
    @RestController
    public class HelloController {
        
        @RequestMapping("/hello")
        public String hello() {
            return "Hello World";
        }
    }
    ```

3. 测试：重启项目，“localhost:8080/hello" 显示 ”Hello World“



# 配置文件

## 全局配置

+ SpringBoot 使用一个**全局配置文件**：
    + **application**<span style="color:red">.properties</span>：

        "key=value"

    + **application**<span style="color:red">.yml</span>

        "key：空格 value"

+ <span style="font-size:22px">修改配置：</span>

    所有配置都是默认生成的，要修改则在配置文件中。

    + 修改端口号：

        ```
        server.port=8081
        ```

## yaml 语法

1. <span style="color:red">空格</span>不能省略

2. 以<span style="color:red">缩进</span>来控制层级关系，只要是左边对齐的一列数据都是同一个层级的。

3. 属性和值的<span style="color:red">大小写</span>都是十分敏感的。

```yaml
# 字面量
key: val

# 对象
obj: {name: 1,age: 2}

# 数组
arr: [cat,dog]
```

## 注入配置文件

1. **实体类**。

2. **yaml 配置类属性**：

    ```yaml
    person:
      name: bei
      age: 3
      happy: false
      birth: 2000/01/01
      maps: {k1: v1,k2: v2}
      lists:
       - code
       - girl
      dog:
        name: 旺财
        age: 1
    ```

3. **注入到类**：

    1. 添加依赖：（没有这步 @ConfigurationPeoperties 会爆红，不加不影响程序运行

        ```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>
        ```

    2. @ConfigurationProperties：

        ```java
        @Component //注册bean
        @ConfigurationProperties(prefix = "person")
        public class Person {
            private String name;
            private Integer age;
            private Boolean happy;
            private Date birth;
            private Map<String,Object> maps;
            private List<Object> lists;
            private Dog dog;
        }
        ```

        > **@ConfigurationProperties作用：**
        > 将配置文件中配置的每一个属性的值，映射到这个组件中
        > 参数 prefix = “person” : 将配置文件中 person 下面的所有属性一一对应



# 使用

2. **导入持久层相关依赖：**

    ```xml
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.mybatis.spring.boot</groupId>
      <artifactId>mybatis-spring-boot-starter</artifactId>
      <version>2.2.2</version>
    </dependency>
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
    </dependency>
    ```

3. **配置数据库连接信息：**——application.yaml

    ```yaml
    spring:
      datasource:
        username: root
        password: theday1012
        url: jdbc:mysql://localhost:3306/springboot?serverTimezone=UTC
        driver-class-name: com.mysql.cj.jdbc.Driver
    ```

4. **测试连接：**

    ```java
    @SpringBootTest
    class CsaTask11ApplicationTests {
    
        @Autowired
        DataSource dataSource;
    
        @Test
        void contextLoads() throws SQLException {
            /*测试是否连接成功*/
            // 获得连接
            Connection connection = dataSource.getConnection();
            System.out.println(connection);
            // 关闭连接
            connection.close();
        }
    }
    ```

5. ==Maven 资源过滤：好像没用?==

    ```xml
    <resources>
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.xml</include>
            </includes>
            <filtering>true</filtering>
        </resource>
    </resources>
    </build>
    ```

<hr>

5. **创建实体类：**—— .pojo

    ```java
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class User {
        private Integer id;
        private String sex;
        private String username;
    }
    ```
    
6. **创建接口类：**—— .mapper / .dao

    ```java
    @Mapper//表示一个 MyBatis 的 Mapper
    public interface UserMapper {
      	int isExists(String username);
    ```
    
    > Mapper 的方法面向数据库操作，而 Service 方法面向业务需求（一个业务运用多个 Service 中方法），具体体现在返回类型
    
7. **整合 MyBatis：**—— application.yaml

    ```yaml
    mybatis:
      type-aliases-package: com.bei.pojo
      mapper-locations: classpath:mybatis/mapper/*.xml
    ```

    > `type-aliases-package`配置Mybatis的包的别名，在mapper.xml文件中xxxType="xxx" 此处直接可以写实体类的类名即可，不需要将全类名都写上
    >
    > ```xml
    > <select id="queryAlluser" resultType="User">
    >   select * from  user
    > </select>
    > <select id="queryAlluser" resultType="com.xxx.xxx.entity.User">
    >   select * from  user
    > </select>
    > ```

8. **对应的 Mapper 映射文件：**—— resource/mybatis/mapper/user-mapper.xml

    ```xml
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper
            PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    
    <mapper namespace="com.bei.mapper.UserMapper">
    
      <select id="isExists" parameterType="String" resultType="boolean">
        select COUNT(*) from springboot.consumer where username = #{username}
      </select>
    
    </mapper>
    ```
    
9. **Service 类和接口：**

    ```java
    public interface UserService {
        boolean batchRemoveUser(List<Integer> ids);
    ```
    
    ```java
    @Service
    public class UserServiceImpl implements UserService {
      private UserMapper userMapper;
    
      @Autowired
      public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
      }
    
      @Override
      public boolean isExistUser(String username) {
        return 1 == userMapper.isExists(username);
      }
    ```
    
10. **Controller 类：**

    ```java
    @RestController
    public class UserController {
      @Autowired
      UserService userService;
    
      @RequestMapping("/register")
      public boolean isExistUser(@RequestParam("username") username) {
        if (userService.isExistUser(username)) {//...};
      }
    ```
    

<hr>

11. 测试：

    输入：/isExistUser?username=test1



## 逆向工程

> 分析数据库中数据表，自动生成 JavaBean（与数据库表对应的实体类）、dao 接口（数据访问层接口，定义了访问数据的方法）、SQLMap (sql 语句映射文件，与dao层接口类一一对应)

1. **配置 plugin**——逆向工程插件

    ```xml
    <!-- 逆向工程的插件 -->
    <plugin>
      <groupId>org.mybatis.generator</groupId>
      <artifactId>mybatis-generator-maven-plugin</artifactId>
      <version>1.3.2</version>
      <configuration>
        <verbose>true</verbose>
        <overwrite>true</overwrite>
        <!--定义配置文件，与下文位置一致-->
        <configurationFile>
          ${basedir}/src/main/resources/mybatis-generator.xml
        </configurationFile>
      </configuration>
    </plugin>
    ```

2. **MyBatis-Generator 配置文件**——xml

    Demo：

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <!-- MyBatis-Generator 相关配置 -->
    <!DOCTYPE generatorConfiguration
            PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
            "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
    
    <generatorConfiguration>
        <!--指定连接数据库的 JDBC 驱动包的所在位置——本机的完整路径-->
        <classPathEntry location="E:\Java\JDBC\mysql-connector-java-8.0.29.jar" />
        <context id="mysqlContext" targetRuntime="mybatis3" defaultModelType="flat">
            <commentGenerator>
                <property name="suppressDate" value="true"/>
                <property name="suppressAllComments" value="true"/>
            </commentGenerator>
            <!--配置数据库连接信息-->
            <jdbcConnection
                    driverClass="com.mysql.cj.jdbc.Driver"
                    connectionURL="jdbc:mysql://localhost:3306/forum?serverTimezone=UTC"
                    userId="root"
                    password="theday1012"
            >
                <property name="nullCatalogMeansCurrent" value="true" />
            </jdbcConnection>
            <!--配置实体pojo类-->
            <javaModelGenerator
                    targetPackage="com.bei.forumserver.pojo"
                    targetProject="src/main/java"
            >
                <property name="constructorBase" value="true"/>
            </javaModelGenerator>
            <!--mapper.xml-->
            <sqlMapGenerator
                    targetPackage="mybatis"
                    targetProject="src/main/resources"
            />
            <!--mapper类-->
            <javaClientGenerator
                    type="MIXEDMAPPER"
                    targetPackage="com.bei.forumserver.mapper"
                    targetProject="src/main/java"
            />
            <!--要生成的表-->
            <table
                    tableName="%"
            />
        </context>
    </generatorConfiguration>
    ```

    > **Note:**
    >
    > + `classPathEntry` 的 jar 包必须与项目依赖版本相同
    > + [`<property name="nullCatalogMeansCurrent" value="true" />`——只生成当前数据库下的表](https://blog.csdn.net/q258523454/article/details/82292045)

3. Eg——Example 类的使用：

    ```java
    public boolean isExist(String email) {
      UsersExample example = new UsersExample();
      UsersExample.Criteria criteria = example.createCriteria();
      criteria.andEmailEqualTo(email);
      return usersMapper.countByExample(example) == 1;
    }
    ```

    > `createCriteria()` 来增加条件



# 项目结构

## 入口文件

```java
@SpringBootApplication
@MapperScan(basePackages = {"com.bei.forumserver.mapper"})	
public class ForumServerApplication {
```

> 配置了 Mapper 包扫描位置后可以不在每一个 Mapper 类加上 @Mapper



# 静态资源访问

## 默认资源映射

**映射规则：**SpringBoot 默认将所有访问映射到:

```
classpath:/static
classpath:/public
classpath:/resources
classpath:/META-INF/resources
```

**Eg:**

在 /resources/static 存放图片 a.png

访问 localhost:8080/a.png 得到该图片



## 自定义资源映射

> 虚拟的路径映射到我们服务器中图片的地址

1. WebAppConfiguer:

    ```java
    @Configuration
    public class WebAppConfigure implements WebMvcConfigure {
      @Override
      public void addResourceHandlers(
        ResourceHandlerRegistry registry
      ) {
        String filePath = "E:/pictures/server/";
      WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("/images/**")      .addResourceLocations("classpath:/static/images/")
          .addResourceLocations("file:" + filePath);
      }
    }
    ```

    > 设置后可从 localhost:8080/images/图片.png 访问到指定图片
    >
    > + `**` 指该路径下任意目录
    > + classpath 对应 resources 包
    > + 绝对路径前加上 `file:`



# 打包

mvn package / mvn install

+ package 将编译代码并打包
+ install 同样也会编译并打包。但之后还会将打好的包安装在本地仓库，供其他项目使用

```xml
<!--忽略测试失败直接打包-->
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-surefire-plugin</artifactId>
  <configuration>
    <testFailureIgnore>true</testFailureIgnore>
  </configuration>
</plugin>
```



# Others

## 图片服务器

**上传图片：**

ImgController.java :

```java
@PostMapping("/upload")
public Result uploadFile(@RequestParam("file") MultipartFile multipartFile) {
  if (multipartFile.isEmpty()) {
    return Result.err().setMsg("文件为空");
  } else {
    String path = imgService.upload(multipartFile);
    return path.length() > 0
      ? Result.ok().setMsg("上传成功").data("path", path)
      : Result.err().setMsg("上传失败");
  }
}
```

ImgServiceImpl.java :

```java
public String upload(MultipartFile multipartFile) {
  // 获取源文件名称
  String originalFilename = multipartFile.getOriginalFilename();
  // 生成不重复标识
  UUID uuid = UUID.randomUUID();
  // 获取源文件后缀
  assert originalFilename != null;
  String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf('.'));
  // 保存文件
  String filesDir = "E:\\Pictures\\server\\";   // 放置图片的文件夹
  File file = new File(filesDir + uuid + fileSuffix);
  try {
    multipartFile.transferTo(file);
    return "http://localhost:8080/images/" + uuid + fileSuffix; // 返回图片完整访问路径
  } catch (IOException e) {
    e.printStackTrace();
  }
  return "";
}
```

**访问图片：**

参考静态资源访问/自定义资源映射



## 登录认证

### JWT 单点登录

1. **引入 JWT 依赖**：

    ```xml
    <dependency>
      <groupId>io.jsonwebtoken</groupId>
      <artifactId>jjwt</artifactId>
      <version>0.9.1</version>
    </dependency>
    ```

2. 编写 **Token 工具类**

    

### 基于 QQ 邮箱的小体量认证

> 电子邮件的在网络中传输和网页一样需要遵从特定的协议，常用的电子邮件协议包括 SMTP，POP3，IMAP。其中邮件的创建和发送只需要用到 SMTP[^1] 协议

**配置邮件客户端：**

1. QQ 邮箱账户设置开启 **POP3/SMTP 服务**

2. 通过生成授权码设置密码

    hzcq nnyu bthw bcgg

**Java 代码：**

1. 引入依赖：

    [JavaMail](http://www.oracle.com/technetwork/java/javamail/index.html)

    ```xml
    <dependency>
      <groupId>com.sun.mail</groupId>
      <artifactId>javax.mail</artifactId>
      <version>1.6.2</version>
    </dependency>
    ```

2. 实现发送随机验证码：

    ```java
    public static double send(String email) {
      // 指定发送邮件主机
      String host = "smtp.qq.com";    // QQ邮件服务器
      // 获取系统属性
      Properties properties = System.getProperties();
      // 设置邮件服务器
      properties.setProperty("mail.smtp.host", host);
      properties.put("mail.smtp.auth", "true");
      // 获取默认session对象
      Session session = Session.getDefaultInstance(properties, new Authenticator() {
        public PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication("shen.ruofeng@qq.com", "hzcqnnyubthwbcgg"); //发件人邮件用户名、授权码
        }
      });
      try {
        // 创建默认的 MimeMessage 对象
        MimeMessage message = new MimeMessage(session);
        // Set From: 头部头字段
        message.setFrom(new InternetAddress(senderEmail));
        // Set To: 头部头字段
        message.addRecipient(Message.RecipientType.TO,
                             new InternetAddress(email));
        // Set Subject: 头部头字段
        message.setSubject("邮子论坛登录认证");
        // 设置消息体
        double randomNum = Math.random() * 1000;
        message.setText("您的认证码是" + randomNum);
        // 发送消息
        Transport.send(message);
        System.out.println("Sent message successfully");
        return randomNum;
      } catch (MessagingException mex) {
        mex.printStackTrace();
      }
      return -1;
    ```



## 集成 Redis

见 `Redis.md`




# REF

+ 整合 MyBatis：

    [SpringBoot整合MyBatis---一篇就够了 - 知乎](https://zhuanlan.zhihu.com/p/143798465)

    [Springboot 整合Mybatis 逆向工程（详解版）](https://segmentfault.com/a/1190000020782343)

    [Mybatis代码生成器Mybatis-Generator使用详解 ](https://www.cnblogs.com/throwable/p/12046848.html)

+ Others：

    [Java 发送邮件 | 菜鸟教程](https://www.runoob.com/java/java-sending-email.html)

    [基于JavaMail的Java邮件发送：简单邮件发送](https://blog.csdn.net/xietansheng/article/details/51673073)



# DEBUG

+ <span style="font-size:20px">[org.apache.ibatis.binding.BindingException](https://blog.csdn.net/qq_35246620/article/details/77916992)：</span>
  
    + Q_Desc：访问接口时，控制台报错如上，即 ibatis 无效绑定异常。
    + A1_R：<span style="color:orange">Mapper 映射文件</span>中中 SQL 的 id 名与 Dao 中方法名不一致。
    + A2_R：<span style="color:orange">Mapper 映射文件</span>中 mapper namespace 地址错误
    
+ <span style="font-size:20px">Could not resolve type alias 'User'：</span>

    + Q_Desc：`user-mapper.xml`中使用别名报错：

        ```xml
        <insert id="addUser" parameterType="User">
          insert into user values (#{account}, #{pwd});
        </insert>
        ```

    + A_R：在`application.yaml`中 mybatis 的配置里别名配置错误：

        ```yaml
        mybatis:
          type-aliases-package: com.bei.pojo
        ```

    + A_S：改为`com.bei.loginserver.pojo`

+ <span style="font-size:20px">java.lang.ClassCastException:</span>java.lang.String cannot be cast to java.lang.Integer

    + Q_Desc：

        ```xml
        <select id="isExists" parameterType="String" resultType="boolean">
          select COUNT(*) from dang.user where tel = #{tel}
        </select>
        ```

    + A_R：数据库操作返回类型不匹配所填类型

    + A：resultType 改为 int

+ <span style="font-size:20px">数据库更改但请求查询结果未更新：</span>
  
    + A：在 IDEA 关联的数据库中先刷新

+ <span style="font-size:20px">不建议使用字段注入:</span>

    + Q_Desc：@Autowired 警告

        ```java
        @Autowired
        private UserMapper userMapper;
        ```

    + S：

        ```java
        private UserMapper userMapper;
        
        @Autowired
        public void setUserMapper(UserMapper userMapper) {
          this.userMapper = userMapper;
        }
        ```

## MyBatis

### DAO

+ <span style="font-size:20px">Cannot determine value type from string 'xxx':</span>
    + Q：执行查询时报错如上——字段和属性名没有对上。
    + R：实体类没有加上无参构造和全参构造 / 数据库字段属性和实体类型不匹配



### Controller

+ <span style="font-size:20px">一级导航路径匹配错误：</span>:

    + Q_Desc：访问 /user 为白页，而 /user/ 正确

        ```java
        @RestController
        @RequestMapping("/user")
        public class GoodsController {
            @RequestMapping("/")
            public String goods() {}
        ```

    + S：将 @RequestMapping("/") 改为 @RequestMapping("")



## 配置

+ <span style="font-size:20px">java.lang.IllegalArgumentException: Result Maps collection does not contain value:</span>

    未解决

+ <span style="font-size:20px">[com.mysql.cj.jdbc.Driver 报红](https://blog.csdn.net/qq_41999034/article/details/104299691)：</span>

    若外部库中没有下载 mysql-connector-java，在 pom.xml 文件中引入以下依赖：

    ```xml
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
    </dependency>
    ```

+ dangServer<span style="font-size:20px">[Application: 无法检索应用程序 JMX 服务 URL：](https://youtrack.jetbrains.com/issue/IDEA-204797)</span>

    + R：IntelliJ IDEA 使用本地 JMX 连接器来检索 Spring Boot 执行器端点的数据。本地 JMX 监视有一些限制，特别是，如果 Spring Boot 应用程序和 IntelliJ IDEA JVM 具有不同的位数，则无法获取本地 JMX连接器地址

    + S：在 Spring Boot运行配置的 VM 选项中添加以下内容：

        ```
        -Dcom.sun.management.jmxremote.port={some_port} 
        -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false
        ```

        > 其中 {some_port} 换为非项目运行的其他端口



[^1]: Simple Mail Transfer Protocol，简单邮件传输协议