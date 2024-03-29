# 计算机网络

概述


## 互联网核心

> 网络中的核心部分要向网络边缘中的大量主机提供**连通性**，使边缘部分中的任何一个主机都能够向其他主机通信（即传送或接收各种形式的数据）

**路由器：**

网络核心部分起特殊作用。实现<span style="color:orange">分组交换</span>的关键构件，用来<span style="color:orange">分组转发</span>（网络核心最重要功能）

<span style="font-size:20px">分组交换: </span>

> **典型交换技术：**
>
> 电路交换、分组交换、报文交换

+ **电路交换：**

    N 部电话机需 N * (N - 1) / 2 对电线。电话机数量增多就需使用交换机完成全网交换任务

    <img src="http://img.giantbear.top/img/20220913211210.png" alt="image-20220913211208365" style="zoom:53%;" />

    特点：面向连接。1.建立连接；2.通信；3.释放连接

**特点：**

+ <span style="color:orange">存储转发</span>技术

+ 发送端 把较长<span style="color:orange">报文划分固定长度数据段</span>

    每一数据段前添上首部 构成分组

+ 以<span style="color:orange">分组为基本单位</span>在网络中传送

+ 接收端收到分组<span style="color:orange">剥去首部还原报文</span>

    ![image-20220913211721258](http://img.giantbear.top/img/20220913211722.png)



## 性能指标

> **单位：**
>
> 1G = 10^9^ bit
>
> 1s = 10^6^ us 

速率，带宽，吞吐率，时延，时延带宽积，往返时间 RTT，利用率

**速率：**数据传送速率 bit/s

**带宽：**通道传送数据能力

**吞吐量：**单位时间通过某网络数据量

**<span style="color:orange">时延：</span>**= 发送时延 + 传播时延 + 处理时延 + 排队时延

+ 发送时延——从节点进入传输媒体所需时间 = 数据帧长度(bit) / 发送速率(bit/s)
+ 传播时延——传播一定距离所花时间 = 信道长度(m) / 传播速率(m/s)
+ 处理时延——收到分组时处理所花时间
+ 排队时延——分组在队列排队等待处理时

> <span style="color:blue">Eg: </span>
>
> 1-10 试在下列条件下比较电路交换和分组交换。要传送的报文共x（bit）。从源点到终点共经过k段链路，每段链路的传播时延为d（s），数据率为b(b/s)。在电路交换时电路的建立时间为s(s)。在分组交换时分组长度为p(bit)，且各结点的排队等待时间可忽略不计。问在怎样的条件下，分组交换的时延比电路交换的要小

> **不同时延产生时机**![image-20220913215714679](http://img.giantbear.top/img/20220913215716.png)

+ + 



# 运输层

向应用层提供通信服务

**作用：**计算机间 应用进程的 端到端的 逻辑通信——通信的端点是<span style="color:orange">主机中的进程</span>



**运输协议：**TCP[^ 3]——面向连接、UDP[^ 4]——无连接



**TCP vs UDP:** 

+ 传送的数据单位协议：TCP 报文段 vs UDP 报文 / 用户数据报
+ 通信信道：全双工的可靠信道 VS 不可靠信道



## TCP

传送数据前必须先建立连接，传输结束后要释放连接

运输连接有三个阶段：**连接建立**、**数据传送**、**连接释放**



<span style="font-size:20px">**Socket:**</span>

TCP 把连接作为最基本的抽象，每一条 TCP 连接有两个端点，这样的端点即 socket

socket = (IP: port)



### TCP 报文段

TCP 报文段分为首部与数据两部分

<span style="font-size:22px">首部格式：</span>

+ **序号**——该报文段发送的数据的第一个字节的序号。

    > 在 TCP 传送的流中，**每一个字节都有一个序号**
    >
    > 如一个报文段的序号为300，报文段数据部分共有100字节，则下一个报文段的序号为400。所以序号确保了TCP传输的有序性。

+ **确认序号 ack**——指明下一期待收到的字节序号，表明该序号前所有数据已经成功收到。

    > **Note：**不能将确认号 ack 与 控制位 ACK 混淆

+ **控制位：URG、ACK、PSH、RST、SYN、FIN**——共 6 个，每一个标志位表示一控制功能

    + SYN 同步——连接建立时用来同步序号。SYN 置为 1 表示是一个连接请求或连接接受报文
+ ACK 确认[^ 8]——只有 ACK 标志位为 1 时确认号字段有效



### 连接建立

**三报文握手**——TCP 建立连接的过程叫握手，握手需在 client 和 server 间交换 3 个 TCP 报文段

![3次握手图解](https://camo.githubusercontent.com/efa4fee9e6025b8618d9e9e7ce60ab52c74b0a49b726ea9e6bb095d6b2e4c2ee/68747470733a2f2f706963342e7a68696d672e636f6d2f38302f76322d35373662303433643132333533393238656561366534353337333635353636385f31343430772e6a70673f736f757263653d3139343065663563)

+ **为什么需要三次握手：**
    + **确认双方都有接收和发送的能力**
    + 本质——TCP 需要 seq 来做可靠重传或接收，而避免连接复用时无法分辨出 seq 是延迟或旧链接的 seq。因此需要三次握手**约定双方 ISN**[^ 7] (*初始 seq* )
    + 避免**已失效的连接请求报文段**再次传输到服务端



### 连接释放

> TCP 在断开连接时，客户端与服务器要交换四次报文，所以 TCP 的断开连接也叫四次挥手



# 应用层

## DNS

+ <span style="font-size:22px">域名系统 DNS：</span>

    > 理论上讲，整个互联网可以只用一个域名服务器，回答所有对 IP 地址的查询。但这样域名服务器会因负荷无法正常工作，且一旦域名服务器故障整个互联网会瘫痪。

    因此互联网采用层次树状结构的命名方法，并使用分布式 DNS。

+ <span style="font-size:22px">域名结构：</span>

    一个域名是由及部分组成的结构，以 <span style="color:red">**.**</span> 分隔，从右到左分隔，每一部分提供特定信息

    ![Anatomy of the MDN domain name](https://mdn.mozillademos.org/files/11229/structure.png)

    + **TLD**[^1] ：告诉用户域名所提供的服务类型
    + **SLD**[^ 2]

    依次一级一级地往下

+ <span style="font-size:22px">域名服务器：</span>

    <img src="https://gitee.com/ethereal-bang/images/raw/master/20220316142156.jpg" alt="img" />

    + 根据域名服务器所起作用，可把域名服务器划分为以下 4 种类型：
        + **根域名服务器**——最高层次。告诉本地域名服务器去哪一个 TLD 服务器查询。
        + **TLD服务器**
        + **权限域名服务器**——负责一个区的权限域名服务器。如不能给出最后的查询回答，会返回下一步应当找哪一个权限域名服务器。
        + **本地域名服务器**——不属于域名服务器层次结构但对域名系统非常重要。当一台主机发出 DNS 查询请求时这个查询请求报文就发给本地域名服务器。当要查询的主机页属于同一本地 ISP，立即转换为它的 IP 地址，不需询问其他域名服务器。

+ <span style="font-size:22px">域名解析过程：</span>

    + **主机向本地域名服务器**的查询：**递归**查询——替该主机继续查询而不是让其自己进行下一步查询。返回 IP 地址或报错。
    + **本地域名服务器向根域名服务器**的查询：**迭代**查询——返回 IP 地址或告诉本地接下来向哪一个服务器查询

> **localhost** / **127.0.0.1**



## www[^ 6]

万维网是大规模的、联机式信息储藏所，简称 Web。

+ **URL——统一资源定位符：**<协议>://<主机>:<端口>/<路径>



### HTTP[^ 5]



# 无线网络和移动网络

> 的**数据链路层**与传统有线互联网相差很大



**设备：**

+ 集线机 / Hub：汇聚网线的中心

+ 交换机：信号转发，为接入的任意两个网络节点提供信号通路

    <img src="https://picx.zhimg.com/80/v2-c373ac4ecd477da6dba19e39ebdda13d_720w.webp?source=1940ef5c" alt="img" style="zoom:63%;" />

## MAC 层

[有了 IP 地址，为什么还要用 MAC 地址 - 知乎](https://www.zhihu.com/question/21546408)



# REF

+ 总：

    [计算机网络（第七版）——谢希仁]()

+ 运输层：

    [史上最详细的经典面试题 从输入URL到看到页面发生了什么？- 掘金](https://juejin.cn/post/6844903832435032072)

+ 应用层：

    [什么是域名？- 学习 Web 开发 | MDN](https://developer.mozilla.org/zh-CN/docs/Learn/Common_questions/What_is_a_domain_name)
    
    



[^1]: Top-Level Domain，顶级域名
[^ 2]: Secondary Level Domain，二级域名

[^ 3]: Transmission Control Protocol，传输控制协议
[^ 4]: User Datagram Protocol，用户数据报协议
[^ 5]: Hyper Text Transfer Protocol，超文本传输协议

[^ 6]: World Wide Web，万维网

[^ 7]: Initial Sequence Number
[^ 8]: Acknowledgment，承认

