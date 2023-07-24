# 连接

<span style="font-size:20px">Shell 登录：</span>

```shell
ssh root@<ip> # 以root身份登录 
ssh <ip> 
```



# 环境

### 宝塔

> **前提 操作系统：**
>
> 1. 停止运行实例
> 2. 更改操作系统，选择镜像市场，选择宝塔面板

<span style="font-size:20px">Linux 面板：</span>

1. 执行命令安装：

    ```shell
    yum install -y wget && wget -O install.sh http://download.bt.cn/install/install_6.0.sh && sh install.sh ed8484bec
    ```

2. 安装完成会返回面板地址，输入面板用户名、密码，绑定宝塔账号 后进入。

    > bt default 返回面板初始用户名和密码

> 进入 Linux 面板后就可以方便地管理服务器了，配置环境更加便捷无需进行后面的步骤。



### Node.js、Express 安装

以下的操作都是在云服务器 CentOS 系统进行的。



<span style="font-size:22px">下载 Node.js:</span>

1. Node.js 官网中查找Linux对应的二进制安装包（`.xz`文件），复制链接地址：

    这里是`https://nodejs.org/dist/v16.13.0/node-v16.13.0-linux-x64.tar.xz`

2. 通过 Xshell 在服务器下载 Node.js：

    这里将其安装在`usr/local`文件夹

    ```shell
    # cd 
    # cd ..
    # cd usr
    # cd local
    # wget https://nodejs.org/dist/v16.13.0/node-v16.13.0-linux-x64.tar.xz
    ```

3. 解压和重命名刚才下载的 node 文件

    ```shell
    # xz -d node-v16.13.0-linux-x64.tar.xz
    # tar -xf node-v16.13.0-linux-x64.tar
    # mv node-v16.13.0-linux-x64 node
    ```

4. 验证：

    ```shell
    # cd node/bin
    # ./node-v
    v16.13.0
    ```



<span style="font-size:22px">配置 Node.js 环境变量</span>

> 不配置的话无法在全局任意位置使用node和npm指令。

1. 打开文本编辑器编辑`/etc/profile`：

    任意路径输入下列指令:

    ```shell
    # vim /etc/profile
    ```

2. 移动光标，`i`键启动文本编辑

    ```shell
    fi
    #set nodejs env 
    export NODE_HOME=/usr/local/node
    export PATH=$NODE_HOME/bin:$PATH
    export NODE_PATH=$NODE_HOME/lib/node_modules:$PATH
    ```

3. 编辑完毕，ESC 键退出文本编辑，键入`:wq`保存并退出

    （不正常退出会很麻烦）

4. 重启配置项使环境变量生效：

    ```shell
    # source /etc/profile
    ```

5. 验证：

    ```shell
    # node -v
    v16.13.0
    # npm -v
    8.1.0
    ```

    

<span style="font-size:22px">安装 Express</span>

1. 安装 express 框架：

    ```bash
    npm install -g express
    ```

2. 安装 express-generator：

    用于全局使用 express 命令。

    ```bash
    npm i -g express-generator
    ```

3. 验证：

    ```bash
    express --version
    ```



# Github pages 部署

> 要使用 GitHub Pages 功能实现全面效果，项目应该被构造为典型的网站——index.html 入口，原生 JS 等

1. 新建 gh-pages 分支

2. GitHub 里设置：

    ![image-20220812163024827](http://img.giantbear.top/img/20220812163034.png)

3. 分支代码更新后 Github 自动执行 Action 重新部署



# 发布 Node 项目

> **入口：**Linux 面板 / 网站 / Node 项目 / 添加 Node 项目

**添加项目**

**添加子域名泛解析：**

访问子域名时解析其指向同一 ip

**域名反向代理：**

> 上一步填写了真实端口并添加了域名,点开配置文件会发现已经做好了域名反代:
>
> ```
> server
> {
>     listen 80;
>     server_name dangServer.giantBear.top;
> 
>     location / {
>         proxy_pass http://127.0.0.1:3001;
> ```



# 发布 Java 项目

服务器上**创建数据库**并上传 SQL 文件与本地数据库同步

1. **yum 安装 JDK：**

    ```shell
    yum search jdk
    yum install java-1.8.0-openjdk.x86_64
    java -version
    ```

    > yum 安装默认路径：`/usr/lib/jvm`

2. **JDK 的安装路径加入到 JAVA_HOME：**

    ```shell
    vi /etc/profile
    ```

    文件末尾加入：

    ```shell
    set java environment
    JAVA_HOME=/usr/local/java/jdk1.8.0_261        
    JRE_HOME=/usr/local/java/jdk1.8.0_261/jre     
    CLASS_PATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$JRE_HOME/lib
    PATH=$PATH:$JAVA_HOME/bin:$JRE_HOME/bin
    export JAVA_HOME JRE_HOME CLASS_PATH PATH
    ```

    让修改生效：

    ```shell
    source /etc/profile
    ```

    测试：

    ```shell
    echo $JAVA_HOME
    ```

3. **项目打包 jar / war 文件传入服务器：**

    + Linux 命令行方式：

        运行该 jar 程序：

        ```shell
        java -jar online_chat-0.0.1-SNAPSHOT.jar
        ```

    + 宝塔面板方式：

        安装软件 “Java项目一键部署”



# 数据库

> 项目内**密码简易加密**方式：
>
> 用户名、密码放在根目录下 config.json，require 使用。将文件加入 .gitignore。

## MongoDB

1. Linux 面板**下载 MongoDB**

    > 在数据库菜单下新建数据库并不能选择 Mongodb，而软件对应的设置也少得可怜。于是用 SSH 方式设置。

2. SSH **进入 MongoDB 服务：**，添加超管账户

3. **修改配置文件**，启用密码登录：

    ```shell
    net:
      port: 27017
    #  bindIp: 0.0.0.0
        
    security:
    #  authorization: enabled
    ```

4. **远程访问：**

    ```
    mongodb://<user>:<pwd>@host[:port]
    ```

5. **项目访问：**

    ```shell
    mongodb://<user>:<pwd>@host[:port]/<database>
    ```

    

# FTP

> 安装软件：Pure-Ftpd

+ FTP 地址：ftp://101.132.100.245/[:21]

1. 添加 FTP，设置登入的用户名、密码，能查看的文件夹

2. 访问 FTP

    + Xftp：注意协议为 FTP（一般远程传输文件时使用的是 SFTP）

    + 资源管理器



# 命令

## nohup

守护后台进程：

```shell
nohup node www
```

> nohup 命令执行后显示："nohup: ignoring input and appending output to 'nohup.out'" 表示 ”忽略输入输出，将信息化信息记录到 nohup.out 文件中“



# 域名

<span style="font-size:20px">域名备案：</span>

需要去服务器主体运营商备案



## HTTPS

申请 SSL 证书



# Pic Bed

域名 + 七牛云图床

1. 域名备案
2. 添加子域名解析，例如 `img.website.top`

2. 在七牛云添加域名



# REF

[阿里云ECS服务器前后端项目部署 - 掘金](https://juejin.cn/post/6908323868360835085)

[超详细！阿里云ECS服务器建站指南！- 知乎](https://zhuanlan.zhihu.com/p/48246138)

[阿里云ECS服务器建站指南（进阶篇）：发布你的个人主页 - 知乎](https://zhuanlan.zhihu.com/p/48343027)

[Quickstart fro GitHub Pages - GitHub Docs](https://docs.github.com/cn/pages/quickstart)

+ 部署前端：

    [阿里云ECS服务器前后端项目部署 - 掘金](https://juejin.cn/post/6908323868360835085#heading-15)



# DEBUG

<span style="font-size:20px">[打开FTP服务器上的文件夹时发生错误。请检查是否有权限访问该文件夹](https://blog.51cto.com/u_15127679/4363643)</span>

Q_Desc：资源文件夹访问时报错如上。

S：Internet 设置中取消勾选 “使用被动FTP(用于防火墙和DSL调制解调器的兼容)”

<span style="font-size:20px">访问 502:</span>

Q_DESC: 后端项目部署后访问接口 502

S：安全组规则里放开该 port