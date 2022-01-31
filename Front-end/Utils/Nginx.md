# Nginx

## 安装

平台：CentOS

1. <span style="font-size:20px">安装编译工具及库文件：</span>（*不然后面 `make` 的时候会出错* ）

    ```shell
    yum -y install make zlib zlib-devel gcc-c++ libtool  openssl openssl-devel
    ```

2. <span style="font-size:20px">安装 PCRE：</span>——让 Niginx 支持 Rewrite

    ```shell
    cd /usr/local/src
    wget http://downloads.sourceforge.net/project/pcre/pcre/8.35/pcre-8.35.tar.gz
    tar zxvf pcre-8.35.tar.gz
    cd pcre-8.35
    ./configure
    make && make install
    ```

    ```shell
    pcre-config --version
    ```

3. <span style="font-size:20px">安装 Nginx：</span>

    ```shell
    # wget  http://nginx.org/packages/centos/7/noarch/RPMS/nginx-release-centos-7-0.el7.ngx.noarch.rpm
     
    建立nginx的yum仓库
    # rpm -ivh nginx-release-centos-7-0.el7.ngx.noarch.rpm
     
    下载并安装nginx
    # yum install nginx
     
    启动nginx服务
    systemctl start nginx
    ```



## 结构

+ nginx 的可执行文件位于 /usr/sbin
+ nginx 安装于 /etc/nginx 
+ 配置文件 nginx.conf
+ html 文件夹：/usr/share/html



# 命令

+ **启动：**进入 /usr/sbin ：

    ```shell
    # ./nginx
    ```

    输入 IP 地址，浏览器显示：（默认监听 80 端口）

    ![image-20220129144607305](https://gitee.com/ethereal-bang/images/raw/master/20220129144614.png)

+ ```shell
    停止
    # ./nginx -s stop
    安全退出
    # ./nginx -s quit
    
    重启
    # ./nginx -s  reload
    重载配置文件（修改配置文件后需执行
    # ./nginx -s reload
    
    查看nginx进程
    # ps aux|grep nginx
    ```

+ 设置开机启动：

    在 /etc/rc.local 底部增加：`/usr/sbin/nginx`：

    <img src="https://gitee.com/ethereal-bang/images/raw/master/20220129150102.png" alt="image-20220129150102546" style="zoom: 50%;" />



# 项目配置

1. 修改配置文件：

    ```
    ```

    



# Ref

+ 安装：

    [Nginx 安装配置 | 菜鸟教程](https://www.runoob.com/linux/nginx-install-setup.html)
    
    [centos7 npm安装nginx - 代码先锋网](https://codeleading.com/article/8075388541/)

+ 命令：

    [Linux 下安装nginx的服务 - CSDN博客](https://blog.csdn.net/qq_15901351/article/details/87970932)