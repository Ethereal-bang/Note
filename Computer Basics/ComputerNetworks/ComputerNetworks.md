# 计算机网络

+ **网络协议**——为网络中数据交换而建立的规则。由语法、语义、同步三要素组成

+ <span style="font-size:20px">体系结构</span>

    计算机网络的<span style="color:orange">各层及其协议</span>的集合。

    + 计网结构体系分为三种：

        + OSI 的七层体系结构
        + TCP/IP 的四层协议
        + 五层协议——重点。

        > 五层结构只是为了介绍网络原理设计的，实际应用的还是 TCP/IP 四层体系结构。

    <img alt="计网体系结构" src="https://gj1e.github.io/images/%E4%BA%94%E5%B1%82%E4%BD%93%E7%B3%BB%E7%BB%93%E6%9E%84.png">




# 物理层



# 数据链路层



# 网络层



# 运输层

向应用层提供通信服务

+ **作用：**计算机间应用进程的端到端的逻辑通信——通信的真正端点是<span style="color:orange">主机中的进程</span>
+ **运输协议：**TCP[^ 3]——面向连接、UDP[^ 4]——无连接



## TCP

传送数据前必须先建立连接，传输结束后要释放连接

运输连接有三个阶段：**连接建立**、**数据传送**、**连接释放**



+ <span style="font-size:20px">Socket:</span>

    Socket 套接字是双向通信的端点的抽象

    socket = (IP: port)

    每一条 TCP 连接被通信两端的两个 socket 确定



### TCP 报文段

TCP 报文段分为首部与数据两部分

![image-20220321143853864](https://gitee.com/ethereal-bang/images/raw/master/20220321143901.png)

+ <span style="font-size:22px">首部格式：</span>

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

1. **Client 发送连接请求报文段**——其中`SYN = 1, squ = x`；Client 进程进入 SYN_SENT(*同步已发送* ) 状态
2. **Server 收到后同意连接则发送确认报文段**——`SYN = 1, ACK = 1, seq = y, ack = x+1`；Server 进程进入 SYN_RCVD(*同步收到* ) 状态
3. **Client 收到确认后向 server 给出确认**——`ACK = 1, seq = x+1, ack = y+1`；Client 进入 ESTABLISHED(*已建立连接* ) 状态；Server 收到也进入 ESTABLISHED 状态

![img](https://camo.githubusercontent.com/efa4fee9e6025b8618d9e9e7ce60ab52c74b0a49b726ea9e6bb095d6b2e4c2ee/68747470733a2f2f706963342e7a68696d672e636f6d2f38302f76322d35373662303433643132333533393238656561366534353337333635353636385f31343430772e6a70673f736f757263653d3139343065663563)

+ **为什么需要三次握手：**
    + **确认双方都有接收和发送的能力**
    + 本质——TCP 需要 seq 来做可靠重传或接收，而避免连接复用时无法分辨出 seq 是延迟或旧链接的 seq。因此需要三次握手**约定双方 ISN**[^ 7] (*初始 seq* )
    + 避免**已失效的连接请求报文段**再次传输到服务端
+ **ISN 动态生成==？==**



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



## www[^ 6]

万维网是大规模的、联机式信息储藏所，简称 Web。

+ **URL——统一资源定位符：**<协议>://<主机>:<端口>/<路径>



### HTTP[^ 5]



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

