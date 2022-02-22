# Java Web



# Servlet

Servlet——用 Java 编写的服务器端程序。其主要功能在于交互式地浏览和修改数据，生成动态 Web 内容

1. **添加依赖：**

    ```xml
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
    ```

    > 添加了 spring-webmvc 依赖之后，其他的 spring-web、spring-aop、spring-context 等等就全部都加入进来了。

2. **Module 添加 Web app 支持：**

    > IDEA：右键“添加框架支持”，选择 Web app 后结构如下：
    >
    > ![image-20220212105048592](C:/Users/HP/AppData/Roaming/Typora/typora-user-images/image-20220212105048592.png)

3. **编写一个 Servlet 类处理用户请求：**

    ```java
    public class HelloServlet extends HttpServlet {
    
        protected void deGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
            // 1.获取前端参数
            String method = req.getParameter("method");
            if (method.equals("add")) {
                req.getSession().setAttribute("msg", "执行了add方法");
            }
            if (method.equals("delete")) {
                req.getSession().setAttribute("msg", "执行了delete方法");
            }
    
            // 2.调用业务层
    
            // 3.视图转发或重定向
            req.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(req, res);
        }
    
        protected void dePost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
            doGet(req, res);
        }
    }
    ```

4. **编写 jsp 文件：**

    ```jsp
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
    <head>
       <title>Hello</title>
    </head>
    <body>
    ${msg}
    </body>
    </html>
    ```

5. **在 web.xml 注册 Servlet：**

    ```xml
    <servlet>
        <servlet-name>hello</servlet-name>
      	<servlet-class>com.bei.servlet.HelloServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>hello</servlet-name>
        <url-pattern>/hello</url-pattern>
    </servlet-mapping>
    ```



## web.xml

+ <span style="font-size:20px">`<welcome-file-list>`：</span>

    设置欢迎页，依次在 web 文件夹查找：

    ```xml
    <welcome-file-list>
      <welcome-file>index.html</welcome-file>
      <welcome-file>index.jsp</welcome-file>
    </welcome-file-list>
    ```

    



## Tomcat

+ <span style="font-size:20px">文件结构：</span>
    + 可执行文件选择开启/停止服务：

        <img src="C:/Users/HP/AppData/Roaming/Typora/typora-user-images/image-20220212113801925.png" alt="image-20220212113801925" style="zoom:33%;" />

    + 默认网站应用存放位置：webapps

        ```diff
        -webapps
        	-ROOT
        	-test	：网站目录名
        		-WEB-INF
        			-classes ：java程序
        			-lib ：web应用jar包
        			-web.xml ：网站配置文件
            -index.html ：默认首页
            -static
            	-css
            	-...
        ```

+ **IDEA + Tomcat：**

    编辑 Tomcat 配置

    ![image-20220219173001181](https://gitee.com/ethereal-bang/images/raw/master/20220219173001.png)

    ![image-20220219173225453](https://gitee.com/ethereal-bang/images/raw/master/20220219173225.png)

    + URL：访问的根路径



# REF

+ Servlet：

    [Java Servlet - 维基百科，自由的百科全书](https://zh.wikipedia.org/wiki/Java_Servlet)



# DEBUG

### web.xml

+ 设置的欢迎页 404：

    + Q_Desc：

        ```xml
        <welcome-file-list>
          <welcome-file>index.html</welcome-file>
        </welcome-file-list>
        ```

    + Q_Solu：将 WEB-INF 下的`index.html`移到 web 目录下。

## Tomecat

+ **[Tomcat 日志显示中文乱码](https://www.cxyzjd.com/article/wangpaiblog/115587823)：**

    + A_Solu: 修改 IDEA 用户配置文件：

        <img src="https://gitee.com/ethereal-bang/images/raw/master/20220219161754.png" alt="image-20220219161754415" style="zoom:50%;" />

        在该文件添加一行：`-Dfile.encoding=UTF-8`

+ [ org.apache.catalina.startup.Catalina.stopServer 未配置关闭端口。通过OS信号关闭服务器。服务器未关闭](https://www.cxymm.net/article/qq_44723773/110526082)
    + A_Solu: 配置文件 conf/server.xml 中修改关闭端口

+ [java.lang.ClassNotFoundException: org.springframework.web.servlet.DispatcherServlet](https://blog.csdn.net/qq_41985361/article/details/113035128)

    + Q_Desc：项目打包时没有导 lib

    + A_Solu：

        在输出里面原本没有 lib 目录，手动添加后将右栏 jar 包引入

        ![image-20220219172734522](https://gitee.com/ethereal-bang/images/raw/master/20220219172734.png)

