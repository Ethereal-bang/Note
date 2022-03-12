# 购买云服务器

于阿里云购买 ECS 服务器。

实例名称：`iZuf6b87fb1fzd0htv51ccZ`。

IP 地址： `101.132.100.245`。

系统用户名： `root`。

<img src="https://gitee.com/ethereal-bang/images/raw/master/20211102233808.png" alt="image-20211102233808525" style="zoom:150%;" />



1. 进入阿里云服务器控制台选择[服务器实例](https://ecs.console.aliyun.com/?spm=5176.8789780.J_5834642020.2.6f8545b5B407Vw&accounttraceid=2c30dda864ae4b909e4b38dae8d8a5b8fqxw#/server/region/cn-shanghai)，重置密码设置个人的**云服务登录密码**。



# 登录云服务器

+ <span style="font-size:20px">Shell 登录：</span>

    ```shell
    ssh root@101.132.100.245
    ```

+ <span style="font-size:20px">SSH 工具登录：</span>

    选择一款 SSH 工具登录远程服务器。常见的SSH工具有 putty、xshell、xftp、SecureCRT 等。

    1. 这里选择了 XShell、XFtp。

    2. Xshell 中首次创建会话：

        其中名称自定义，主机填公网 IP，其他默认。

<img src="https://gitee.com/ethereal-bang/images/raw/master/20211103000016.png" alt="image-20211103000016704" style="zoom:50%;" />

​			3. Xshell 中连接远程服务器：

​				首先出现 SSH 警告，点接受并保存。然后输入系统用户名`root`和上一步中重置的密码。

​				然后在上部栏选择**新建文件传输**：

​			<img src="https://gitee.com/ethereal-bang/images/raw/master/20211103001131.png" alt="image-20211103001131433" style="zoom:50%;" />

​			之后自动打开登录后的 Xftp。



# 在云服务器上安装 Node.js、Express

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



## 下载 Node.js

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



## 配置 Node.js 环境变量

不配置的话无法在全局任意位置使用node和npm指令。

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

    

## 安装 Express

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

4. 配置==实例安全组？==：

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




# GitHub Pages + Jekyll



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



# REF

[阿里云ECS服务器前后端项目部署 - 掘金](https://juejin.cn/post/6908323868360835085)

[超详细！阿里云ECS服务器建站指南！- 知乎](https://zhuanlan.zhihu.com/p/48246138)

[阿里云ECS服务器建站指南（进阶篇）：发布你的个人主页 - 知乎](https://zhuanlan.zhihu.com/p/48343027)

[个人博客第5篇安装node.js和Hexo - 知乎](https://zhuanlan.zhihu.com/p/105715224)

[Quickstart fro GitHub Pages - GitHub Docs](https://docs.github.com/cn/pages/quickstart)

+ GitHub Pages + Jekyll：

    [搭建一个免费的，无限流量的Blog----github Pages和Jekyll入门 - 阮一峰的网络日志](https://www.ruanyifeng.com/blog/2012/08/blogging_with_jekyll.html)
    
+ 部署前端：

    [阿里云ECS服务器前后端项目部署 - 掘金](https://juejin.cn/post/6908323868360835085#heading-15)

