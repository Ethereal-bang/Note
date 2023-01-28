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
    



# Windows

## 常用

+ 打开 **AppData/Local 文件夹**：Win + R, 输入 `%localappdata`  

+ **管理员身份运行：** Win + R，输入 cmd，Ctrl + Shift + Enter 打开

+ **创建目录链接：**管理员身份下

    ```shell
    mklink /j C:\Users\hp\AppData\Local\Spotify\Data D:\ hp\Spotify\Data
    为 C:\Users\hp\AppData\Local\Spotify\Data <<===>> D:\Spotify\Data 创建的联接
    ```

    > 上例通过建立目录连接，原始文件夹存储自动移动到新位置，并镜像到原始位置




# 进程管理

## 进程与线程

<span style="font-size:22px">进程：</span>

资源分配的基本单位

<hr/>

<span style="font-size:22px">线程：</span>

独立调度的基本单位。

<hr/>

<span style="font-size:22px">区别：</span>

+ 系统开销：系统创建、撤销进程的开销远大于对线程的开销

+ 通信：同一进程的线程间可直接读写；进程间通信需借助 IPC

    > **IPC：**（进程间通信）



## 进程状态的切换

![img](https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Process_states.svg/400px-Process_states.svg.png)

![image-20220417132959182](https://gitee.com/ethereal-bang/images/raw/master/20220417133006.png)

+ 就绪 **waiting**——等待调度；运行 **running**；阻塞 **blocked**——等待资源

    waiting——可运行，但因为其他进程正在运行而暂时停止

    runnning——占用 CPU

    blocked——除非某种外部事件发生，否则进程不能运行



## 进程通信

### 相关概念

+ <span style="font-size:20px">竞争条件：</span>

    多个进程同时读写共享的数据。

+ <span style="font-size:20px">临界区：</span>

    **临界区：**对共享内存进行访问的程序片段

    使两个进程不同时处于临界区中就能避免竞争条件

    使用临界区的互斥如图：

    <img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/bb1dffc1075d42eeb00619ea4fe3e8b1~tplv-k3u1fbpfcp-zoom-in-crop-mark:1304:0:0:0.awebp" alt="img" style="zoom: 50%;" />

### IPC

Inter Process Communication——进程通信

+ <span style="font-size:20px">消息传递——Message Passing：</span>

    进程间实现通信和同步等待的机制

+ <span style="font-size:20px">管道：</span>

    半双工的通信，数据只能单向流动

+ ...



# Ref

+ Linux:

    [2万字系统总结，带你实现 Linux 命令自由？- 掘金](https://juejin.cn/post/6938385978004340744)

    [Linux 系统目录结构 | 菜鸟教程](https://www.runoob.com/linux/linux-system-contents.html)

+ 进程管理：

    [CS-Notes/计算机操作系统 - 进程管理](https://github.com/CyC2018/CS-Notes/blob/master/notes/%E8%AE%A1%E7%AE%97%E6%9C%BA%E6%93%8D%E4%BD%9C%E7%B3%BB%E7%BB%9F%20-%20%E8%BF%9B%E7%A8%8B%E7%AE%A1%E7%90%86.md)

    [《现代操作系统》]()

    [2.5w字 + 36 张图爆肝操作系统面试题，太牛逼了！- 掘金](https://juejin.cn/post/6934500044057870350)



[^1]: change mode