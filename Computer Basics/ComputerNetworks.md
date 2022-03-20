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

+ 超文本文档仅含文本信息
+ 默认端口号 80
+ 面向<span style="color:orange">事务</span>——(*要么所有信息交换都完成，要么依次交换都不进行*) 的应用层协议，基于 TCP/IP 通信协议来传递数据



# REF

+ 总：

    [计算机网络（第七版）——谢希仁]()

+ 应用层：

    [什么是域名？- 学习 Web 开发 | MDN](https://developer.mozilla.org/zh-CN/docs/Learn/Common_questions/What_is_a_domain_name)



[^1]: Top-Level Domain，顶级域名
[^ 2]: Secondary Level Domain，二级域名

[^ 3]: Transmission Control Protocol，传输控制协议
[^ 4]: User Datagram Protocol，用户数据报协议
[^ 5]: Hyper Text Transfer Protocol，超文本传输协议

[^ 6]: World Wide Web，万维网

