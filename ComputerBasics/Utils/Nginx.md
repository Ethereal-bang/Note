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

+ 配置文件 nginx.conf：

    ```
    # set default home page
    server 
        {
            listen 80;
            server_name giantcat.top;
            root /www/server/web_site/home_page;
            index index.html;
        }
    ```

    ```
    user  www www;
    worker_processes auto;
    error_log  /www/wwwlogs/nginx_error.log  crit;
    pid        /www/server/nginx/logs/nginx.pid;
    worker_rlimit_nofile 51200;
    
    events
        {
            use epoll;
            worker_connections 51200;
            multi_accept on;
        }
    
    http
        {
            include       mime.types;
    		#include luawaf.conf;
    
    		include proxy.conf;
    
            default_type  application/octet-stream;
    
            server_names_hash_bucket_size 512;
            client_header_buffer_size 32k;
            large_client_header_buffers 4 32k;
            client_max_body_size 50m;
    
            sendfile   on;
            tcp_nopush on;
    
            keepalive_timeout 60;
    
            tcp_nodelay on;
    
            fastcgi_connect_timeout 300;
            fastcgi_send_timeout 300;
            fastcgi_read_timeout 300;
            fastcgi_buffer_size 64k;
            fastcgi_buffers 4 64k;
            fastcgi_busy_buffers_size 128k;
            fastcgi_temp_file_write_size 256k;
    		fastcgi_intercept_errors on;
    
            gzip on;
            gzip_min_length  1k;
            gzip_buffers     4 16k;
            gzip_http_version 1.1;
            gzip_comp_level 2;
            gzip_types     text/plain application/javascript application/x-javascript text/javascript text/css application/xml;
            gzip_vary on;
            gzip_proxied   expired no-cache no-store private auth;
            gzip_disable   "MSIE [1-6]\.";
    
            limit_conn_zone $binary_remote_addr zone=perip:10m;
    		limit_conn_zone $server_name zone=perserver:10m;
    
            server_tokens off;
            access_log off;
    
    server
        {
            listen 888;
            server_name phpmyadmin;
            index index.html index.htm index.php;
            root  /www/server/phpmyadmin;
    
            #error_page   404   /404.html;
            include enable-php.conf;
    
            location ~ .*\.(gif|jpg|jpeg|png|bmp|swf)$
            {
                expires      30d;
            }
    
            location ~ .*\.(js|css)?$
            {
                expires      12h;
            }
    
            location ~ /\.
            {
                deny all;
            }
    
            access_log  /www/wwwlogs/access.log;
        }
    include /www/server/panel/vhost/nginx/*.conf;
    }
    ```

    

+ html 文件夹：/usr/share/html



# 命令

+ **启动：**进入 /usr/sbin ：

    ```shell
    # ./nginx
    ```

    输入 IP 地址，浏览器显示（默认监听 80 端口）

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

+ <span style="font-size:20px">nginx.conf 配置文件：</span>

    ```js
    ...              #全局块
    
    events {         #events块
       ...
    }
    
    http      #http块
    {
        ...   #http全局块
        server        #server块
        { 
            ...       #server全局块
            location [PATTERN]   #location块
            {
                ...
            }
            location [PATTERN] 
            {
                ...
            }
        }
        server
        {
          ...
        }
        ...     #http全局块
    }
    ```

    - **全局块**：配置影响nginx全局的指令。一般有运行nginx服务器的用户组，nginx进程pid存放路径，日志存放路径，配置文件引入，允许生成worker process数等。
    - **events块**：配置影响nginx服务器或与用户的网络连接。有每个进程的最大连接数，选取哪种事件驱动模型处理连接请求，是否允许同时接受多个网路连接，开启多个网络连接序列化等。
    - **http块**：可以嵌套多个server，配置代理，缓存，日志定义等绝大多数功能和第三方模块的配置。如文件引入，mime-type定义，日志自定义，是否使用sendfile传输文件，连接超时时间，单连接请求数等。
    - **server块**：配置虚拟主机的相关参数，一个http中可以有多个server。
    - **location块**：配置请求的路由，以及各种页面的处理情况。



## server 块配置

**listen**：监听的端口

**server_name**：IP / 域名。多个配置空格隔开

**root**：web 项目的根目录 

**index**：用户访问 web 网站时的全局首页

**location**：配置路由规则

```
server 
    {
        listen 80;
        server_name localhost;
        root /www/server/web_site/home_page;
        index index.html;
        
        error_page 404 404.html;
        error_page 500 502 503 504 50x.html;   
    }
```



### location 匹配规则

[彻底弄懂 Nginx location 匹配 - 掘金](https://juejin.cn/post/6844903849166110733)

[Nginx location匹配规则 - 博客园](https://www.cnblogs.com/woshimrf/p/nginx-config-location.html)



**语法：**`location [=|~|~*|^~|@] pattern { ... }`——修饰符 + 模式

忽略 querystring

**修饰符：**

+ 【=】路径完全匹配 <span style="color:blue">`location = /abc`</span>
+ 【~】区分大小写的正则匹配 <span style="color:blue">`location ~ ^/abc$`</span>

+ 【~*】不区分大小写的正则匹配
+ 【^~】前缀匹配。优先于正则表达式的匹配

**查询优先级：**

1. 【=】**完全匹配**则停止搜索
2. 【^~】**前缀匹配**，停止后续正则搜索
3. **正则匹配**
4. 不带修饰符的匹配

<span style="color:blue">**EG:**</span>

`location ~ ^.+$ `



# Ref

+ 安装：

    [Nginx 安装配置 | 菜鸟教程](https://www.runoob.com/linux/nginx-install-setup.html)
    
    [centos7 npm安装nginx - 代码先锋网](https://codeleading.com/article/8075388541/)

+ 命令：

    [Linux 下安装nginx的服务 - CSDN博客](https://blog.csdn.net/qq_15901351/article/details/87970932)

+ 项目配置：

    [Nginx 配置详解 | 菜鸟教程](https://www.runoob.com/w3cnote/nginx-setup-intro.html)