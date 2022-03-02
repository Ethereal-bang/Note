# SpringBoot

一个 javaweb 的开发框架，和 SpringMVC 类似，对比其他 javaweb 框架的好处——简化开发，约定大于配置

并不是用来替代 Spring 的解决方案，而是和 Spring 框架紧密结合用于提升 Spring 开发者体验的工具



# Demo

## 项目创建

使用 IDEA 直接创建 SpringBoot 项目：

1. <img src="https://gitee.com/ethereal-bang/images/raw/master/20220222140342.png" alt="image-20220222140335022" style="zoom:33%;" />

2. 这里如果勾上 Developer Tools 的 “Spring Boot DevTools” 则可热部署

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

1. 同级目录下**新建 conteoller 包**。

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



# 整合 MyBatis

1. **新建项目：**

    ![image-20220227102145301](https://gitee.com/ethereal-bang/images/raw/master/20220227102152.png)

2. **导入依赖：**

    ```xml
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>2.1.1</version>
    </dependency>
    ```

3. **配置数据库连接信息：**

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

5. **Maven 资源过滤：**

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
    //@Mapper : 表示一个 MyBatis 的 Mapper
    @Mapper
    @Repository
    public interface UserMapper {
      	boolean isExists(String username);
    ```
    
7. **整合 MyBatis：**—— application.yaml

    ```yaml
    mybatis:
      type-aliases-package: com.bei.pojo
      mapper-locations: classpath:mybatis/mapper/*.xml
    ```

    > 配置Mybatis的包的别名，在mapper.xml文件中xxxType="xxx" 此处直接可以写实体类的类名即可，不需要将全类名都写上
    >
    > ```xml
    > <select id="queryAlluser" resultType="User">
    >   select * from  user
    > </select>
    > <select id="queryAlluser" resultType="com.xxx.xxx.entity.User">
    >   select * from  user
    > </select>
    > ```

8. **对应的 Mapper 映射文件：**—— resource/mybatis/mapper/userMapper.xml

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
      @Autowired
      private UserMapper userMapper;
    
      public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
      }
    
      @Override
      public boolean isExistUser(String username) {
        return userMapper.isExists(username);
      }
    ```
    
10. **Controller 类：**

    ```java
    @RestController
    public class UserController {
      @Autowired
      UserService userService;
    
      @GetMapping("/isExistUser")
      public boolean isExistUser(String username) {
        return userService.isExistUser(username);
      }
    ```
    

<hr>

11. 测试：

    输入：/isExistUser?username=test1



# REF

+ 整合 MyBatis：

    [SpringBoot整合MyBatis---一篇就够了 - 知乎](https://zhuanlan.zhihu.com/p/143798465)



# DEBUG

+ [org.apache.ibatis.binding.BindingException](https://blog.csdn.net/qq_35246620/article/details/77916992)
    + Q_Desc：访问接口时，控制台报错如上，即ibatis 无效绑定异常。
    + A_R：Mapper 映射文件中中 SQL 的 id 名与 Dao 中方法名不一致。



```java
package com.bei;

public class LinkedList {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null, cur = head, next;

        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

}

class ListNode {
    int val;
    ListNode next;
    ListNode() {};
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

```

