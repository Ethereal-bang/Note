# Operating System

操作系统 Operating System 简称 OS ，是软件的一部分，它是硬件基础上的第一层软件，是<span style="color:red">硬件和其它软件沟通的桥梁</span>

操作系统会控制其他程序运行，管理系统资源，提供最基本的计算功能，如管理及配置内存、决定系统资源供需的优先次序等，同时还提供一些基本的服务程序。

<img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/c17c21686f21413085f3e32c85a19443~tplv-k3u1fbpfcp-watermark.awebp">

## Shell

Shell 原意是“外壳”，跟 kernel（内核）相对应，比喻内核外面的一层，即用户跟内核交互的对话界面

又称命令行环境（ command line interface ，简写为 CLI）

Shell 接收到用户输入的命令，将命令送入操作系统执行，并将结果返回给用户

+ <span style="font-size:20px">常用命令：</span>

    + 切换目录：

        ```shell
        $C:\Users\HP>e:
        $E:\>
        ```
        
        切换到该盘符下的目录。`cd`命令：
        
        ```shell
        $E:\>cd Microsoft VS Code
        $E:\Microsoft VS Code>
        
        
        $c:\Users>cd /d e:\Microsoft VS Code
        $e:\Microsoft VS Code>
        
        
        # 切换到上级目录。`cd..`
        ```
        
        
        切换到其他盘符下的目录时在`cd`后加上`/d`
        
        > Notes：
        >
        > 路径按下`tab`键有自动补全功能。
    
    

# Linux

真正的 Linux 指的是<span style="color:rgba(62, 175, 124)">系统内核</span>，而我们常说的 Linux 指的是“发行版完整的包含一些基础软件的<span style="color:rgba(62, 175, 124)">操作系统</span>”

> <span style="font-size:20px">Linux 系统种类：</span>
>
> + Red Hat Enterprise Linux ： RHEL 是全世界内使用最广泛的 Linux 系统。具有极强性能与稳定性，是众多生成环境中使用的（收费的）系统。
>
> + Fedora
>
> + **CentOS ：**通过把 RHEL 系统重新编译并发布给用户免费使用的 `Linux` 系统，具有广泛使用人群。
>
> + Deepin
>
> + Debian：稳定性、安全性强，提供了免费的基础支持，在国外拥有很高的认可度和使用率。
>
> + **Ubuntu ：**是一款派生自 Debian 的操作系统，对新款硬件具有极强的兼容能力。 `Ubuntu` 与 `Fedora` 都是极其出色的 `Linux` 桌面系统，而且 `Ubuntu` 也可用于服务器领域。

+ <span style="font-size:22px">目录结构：</span>
    + /home：每个用户的私人目录，存放私人文件
    + /root：超级用户的家目录
    + /usr：Unix Software Resource，Unix 操作系统软件资源，安装了大部分用户要调用的程序
    + /var：variable，表动态的 通常包含程序的数据如 log

## 配置

+ <span style="font-size:20px">安装 yum：</span>

    Yum——基于 RPM 包管理的<span style="color:red">包管理器</span>。 



## Linux 命令

**格式：**"command \<parameters> "

+ <span style="font-size:20px">快捷方式：</span>
    + Ctrl + L：清除屏幕
    + Ctrl + C：中止当前命令
    + Ctrl + D：关闭 Shell

### 软件仓库

Linux 下软件以包的形式存在，一个软件包其实就是软件的所有文件的压缩包，是二进制的形式，包含了安装软件的所有指令。

Linux 的包都存在一个仓库，叫做软件仓库，它可以使用 yum 来管理软件包， yum 是 CentOS 中默认的包管理工具

- `yum update | yum upgrade` 更新软件包
- `yum search xxx` 搜索相应的软件包
- `yum install xxx` 安装软件包
- `yum remove xxx` 删除软件包

### 用户与权限

|         | 用户                                |
| ------- | ----------------------------------- |
| sudo    | 以 root 身份（*超级管理* ）运行命令 |
| useradd | 添加用户（需 root 权限              |
| passwd  | 修改用户密码（需 root               |
| userdel | 删除用户（root                      |
| su      | 切换用户                            |

### 文件

|         | 查看路径                     |
| ------- | ---------------------------- |
| pwd     | 显示当前路径                 |
| which + | 查看命令的可执行文件所在路径 |

|           | 目录查看       |                                        |
| --------- | -------------- | -------------------------------------- |
| ls        | 列出文件和目录 | -a包括隐藏；-l详细列表；-h适合阅读的； |
| cd <路径> | 切换目录       | cd 表示回到家目录                      |

|               | 显示、创建文件                     |
| ------------- | ---------------------------------- |
| cat <文件名>  | 一次性显示文件内容（*适合小文件*） |
| less <文件名> | 分页显示文件内容（*适合大*）       |
| touch <>      | 创建一个文件                       |
| mkdir         | 创建目录                           |

|      | 复制、移动文件 |                                                              |
| ---- | -------------- | ------------------------------------------------------------ |
| cp   | 拷贝文件或目录 | -r 递归拷贝整个目录                                          |
| mv   | 移动、或重命名 | mv `file` `dict`——`file` 文件移到 `dict`目录；mv `file` `new_file`——重命名 |

|      | 删除文件 |                          |
| ---- | -------- | ------------------------ |
| rm   |          | -f 强制删除；-r 递归删除 |

- **查找文件：**
	```shell
	find -name "file.txt"	 当前目录以及子目录下通过名称查找文件
	find . -name "syslog"	 当前目录以及子目录下通过名称查找文件
	find / -name "syslog"	 整个硬盘下查找syslog
	find /var/log -name "syslog"	 在指定的目录/var/log下查找syslog文件
	find /var/log -name "syslog*"	 查找syslog1、syslog2 ... 等文件，通配符表示所有
	find /var/log -name "*syslog*"	 查找包含syslog的文件 
	```




|      | 创建链接   |                                                      |
| ---- | ---------- | ---------------------------------------------------- |
| ln   | 创建硬链接 | ln file file1：创建 file1为 file 的硬链接；-s 软链接 |

+ 硬链接的两个文件共享且修改的是同一文件内容。软链接类似 windows 的快捷方式：

    <img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/2a7460133e014a629fd09bbd6416ba6a~tplv-k3u1fbpfcp-watermark.awebp" style="zoom: 67%;" >

    

    <img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/529e1c254dfd489dafbf82326c89abbc~tplv-k3u1fbpfcp-watermark.awebp" style="zoom:67%;" >

### 文件压缩解压

<img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/0d87434a4c414defb180b05d9bfca4c4~tplv-k3u1fbpfcp-watermark.awebp" style="zoom: 70%;" >

+ tar：创建一 tar 归档

### Vim

#### 常用模式

+ <span style="font-size:20px">交互模式：</span>

    默认模式，执行 vim 后就进入该模式。

    + 不能输入文本
    + 可以文本见移动、删除一行文本、复制粘贴文本、跳转到指定行、撤销等

+ <span style="font-size:20px">插入模式：</span>

    按键 ”i“ 进入插入模式，退出只需按键 ”ESC“。

+ <span style="font-size:20px">命令模式：</span>

    这个模式可以运行一些诸如 退出、保存 的命令。

<img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/a06645b200354e56b8295751c8abac3f~tplv-k3u1fbpfcp-watermark.awebp" style="zoom:55%">

#### 退出文件

在交互模式下，按下冒号键 `:` 进入命令模式，再按下 `q` 键，就可以退出了。
如果在退出之前又修改了文件，就直接想用 `:q` 退出 `Vim` ，那么 `Vim` 会显示一个红字标明错误信息。此时我们有两个选择：

1. 保存并退出 `:wq` 或 `:x` ；
2. 不保存且退出 `:q!` 。

#### 标准操作

+ <span style="font-size:20px">删除字符：</span>

    交互模式下，按键 <span style="color:red">"x"</span>

+ <span style="font-size:20px">撤销操作：</span>

    <span style="color:red">ctrl + r</span>

### 系统

+ 查看系统进程：

    ```shell
    $ ps -rf
    ```

    ![image-20220207144454100](https://gitee.com/ethereal-bang/images/raw/master/20220207144501.png)

+ 结束指定 PID 值进程：

    ```shell
    kill <PID>
    ```

    

# Ref

+ Operating System:

    [2万字系统总结，带你实现 Linux 命令自由？- 掘金](https://juejin.cn/post/6938385978004340744)

