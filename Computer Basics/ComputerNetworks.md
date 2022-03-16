# 计算机网络



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



# REF

+ 应用层：

    [计算机网络（第七版）——谢希仁]()

    [什么是域名？- 学习 Web 开发 | MDN](https://developer.mozilla.org/zh-CN/docs/Learn/Common_questions/What_is_a_domain_name)



[^1]: Top-Level Domain，顶级域名
[^ 2]: Secondary Level Domain，二级域名

