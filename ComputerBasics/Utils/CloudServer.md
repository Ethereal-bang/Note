# 准备

## 云服务器

+ <span style="font-size:20px">购买:</span>

    IP 地址、系统用户名、密码。

+ <span style="font-size:20px">登录:</span>

    <span style="font-size:20px">Shell 登录：</span>

    ```shell
    ssh root@101.132.100.245
    ```

    <span style="font-size:20px">SSH 工具登录：</span>

    1. 这里选择了 XShell、XFtp。

    2. Xshell 中首次创建会话：

        > 其中名称自定义，主机填公网 IP，其他默认。

    3.  Xshell 中连接远程服务器：

        > 首先出现 SSH 警告，点接受并保存。



## 环境配置

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



### 在云服务器上安装 Node.js、Express

以下的操作都是在云服务器 CentOS 系统进行的。

> **CentOS 与 Linux 关系：**
>
> Linux 通常指的是 linux 内核

> **Linux 常用指令:**
>
> `ll`：当前目录下文件夹
>
> `cd [目录名]`：进入(当前目录下的)指定文件夹
>
> ![image-20211103200651164](https://gitee.com/ethereal-bang/images/raw/master/20211103200658.png)
>
> 更多移步[Linux命令大全(手册)-真正好用的Linux命令在线查询网站](https://www.linuxcool.com/)



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
    # npm install -g express
    ```

2. 安装 express-generator：

    用于全局使用 express 命令。

    ```bash
    # npm i -g express-generator
    ```

3. 验证：

    ```bash
    # express --version
    ```



# 使用 Express 搭建简易服务器

1. 创建 Express 项目：

    这里在`usr/local`下创建项目`demo`

    ```shell
    # express demo
    ```

2. 进入`demo`安装依赖：

    ```bash
    # cd demo
    # npm install
    ```

3. 文本编辑器编辑`app.js`：

    ```shell
    # vim app.js
    ```

    添加(80——端口号）：

    ```js
    app.listen(80, function () {
        console.log("service is starting at 80")
    });
    ```

    ESC，`:wq`保存并退出。

4. 配置实例安全组：

    ![image-20211104134213161](https://gitee.com/ethereal-bang/images/raw/master/20211104134220.png)

5. 可以`# node app`开启服务器了：

    `node app`后才能进入该网址。

    <img src="https://gitee.com/ethereal-bang/images/raw/master/20211104134330.png" alt="image-20211104134329949" style="zoom:30%;" />



# 在云服务器发布个人主页

为方便操作可在本地电脑搭建一个 localhost 服务器，配置完成后再一次性上传到云服务器。

完成前面的步骤后可以在 Xftp 中看到项目目录中有以下文件。

![image-20211104134930158](https://gitee.com/ethereal-bang/images/raw/master/20211104134930.png)

1. 调整`app.js`、`routes/index.js`==？==

    调整后可以把 HTML 入口文件放在`routes`文件夹下了。

2. 存放静态文件：

    还有静态文件 css、js 等需存放在`pubic`文件夹下。

    初始如下：<img src="https://gitee.com/ethereal-bang/images/raw/master/20211104141020.png" alt="image-20211104141020581" style="zoom:73%;" />

    把每个页面的响应文件存在对应页面文件夹，使其不相互影响。

3. 引用静态文件：



# Niginx

非同步框架的网页伺服器，也可以用作反向代理、负载平衡器和 HTTP 快取

Nginx可以作为一个HTTP服务器进行网站的发布处理



# 部署前端

1. 将 Express 项目复制到云服务器上，可以看到访问网站 IP：

    这是存放于 public/images/ 文件夹下的文件，可以直接根据绝对路径访问：

    <img src="https://gitee.com/ethereal-bang/images/raw/master/20220207154927.png" alt="image-20220207154927079" style="zoom:43%;" />

    <img src="https://gitee.com/ethereal-bang/images/raw/master/20220207154811.png" alt="image-20220207154811012" style="zoom:33%;" />

+ **nohup** 守护后台进程：

    ```shell
    nohup node www
    ```

    > nohup 命令执行后显示："nohup: ignoring input and appending output to 'nohup.out'" 表示 ”忽略输入输出，将信息化信息记录到nohup.out文件中“



## Github pages 部署

> 要使用 GitHub Pages 功能实现全面效果，项目应该被构造为典型的网站——index.html 入口，原生 JS 等

1. 新建 gh-pages 分支

2. GitHub 里设置：

    ![image-20220812163024827](http://img.giantbear.top/img/20220812163034.png)

3. 分支代码更新后 Github 自动执行 Action 重新部署



# 宝塔：前后端分离部署

## 后端：发布 Node 项目

> **入口：**Linux 面板 / 网站 / Node 项目 / 添加 Node 项目

**添加项目:**

![image-20220514111725307](https://gitee.com/ethereal-bang/images/raw/master/20220514111828.png)

**添加子域名泛解析：**

访问子域名时解析其指向同一 ip

![image-20220515141515672](https://gitee.com/ethereal-bang/images/raw/master/20220515141546.png)

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

## 前端





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

        <img src="https://gitee.com/ethereal-bang/images/raw/master/20220520213429.png" alt="连接属性设置" />

    + 资源管理器



# REF

[阿里云ECS服务器前后端项目部署 - 掘金](https://juejin.cn/post/6908323868360835085)

[超详细！阿里云ECS服务器建站指南！- 知乎](https://zhuanlan.zhihu.com/p/48246138)

[阿里云ECS服务器建站指南（进阶篇）：发布你的个人主页 - 知乎](https://zhuanlan.zhihu.com/p/48343027)

[Quickstart fro GitHub Pages - GitHub Docs](https://docs.github.com/cn/pages/quickstart)

+ 部署前端：

    [阿里云ECS服务器前后端项目部署 - 掘金](https://juejin.cn/post/6908323868360835085#heading-15)



# DEBUG

+ <span style="font-size:20px">[打开FTP服务器上的文件夹时发生错误。请检查是否有权限访问该文件夹](https://blog.51cto.com/u_15127679/4363643)</span>

    Q_Desc：资源文件夹访问时报错如上。

    S：Internet 设置中取消勾选 “使用被动FTP(用于防火墙和DSL调制解调器的兼容)”

    ​	