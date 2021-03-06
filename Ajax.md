

# 初步自检

+ Ajax简介和异步的概念

+ Ajax框架的封装

+ XMLHttpRequest对象详细介绍、兼容性处理方法

+ Ajax框架的封装

+ Ajax中缓存问题

+ XML介绍和使用

+ JSON和JSON的解析

+ 数据绑定和模板技术

+ JSONP、跨域技术

+ 图片预读取和lazy-load技术

+ jQuery框架中的AjaxAPI

+ 使用Ajax实现瀑布流案例



# Ajax 简介

Ajax（*Asynchronous JavaScript+XML*）

Ajax 是一种在无需加载整个页面的情况下，能更新部分网页的技术

特点：

1. 不是编程语言，而是种现有标准的新方法
2. 无需加载整个页面，可与服务器交换数据，更新部分网页
3. 不需浏览器插件



# 工作原理

<span style="font-size:20px">包含的技术</span>

<hr>

Ajax 是基于现有的 Internet 标准，并联合使用它们：

1. XMLHttpRequest（*XHR对象*） 对象 （*异步与服务器交换数据*）

    XHR 对象为发送服务器请求和获取响应提供了合理的接口，这个接口可以实现**异步**从服务器获取额外数据，意味着用户不用点击页面刷新也可以获取数据

2. JavaScript 、DOM  （*信息显示、交互*）

    通过 XHR 对象获取数据后，可以使用 DOM 方法把数据插入网页。

3. CSS（*给数据定义样式*）

4. XML（*转换数据的格式*）

    虽然 Ajax 名称中包含 XML，但实际上 Ajax 通信 与数据格式无关，这个技术主要是可以实现不刷新页面的情况下从服务器获取数据，格式并不一定是 XML。



<span style="font-size:20px">原理</span>

<hr>

通过 XMLHttpRequest 对象来向服务器发送异步请求 ，从服务器获得数据，然后用 js 来操作 DOM 而更新页面。

<img src="https://i.loli.net/2021/04/05/m2nWpOTrGIEzRyQ.png" alt="image-20210405100239207" style="zoom: 67%;" />

Ajax 的工作原理相当于在用户和服务器之间加了一个中间层（*ajax引擎*），使用户操作与服务器响应异步化。



原生 JS 实现步骤 ==？==

![image-20210405173711093](https://i.loli.net/2021/04/05/TsxBcY3zkU6G5VH.png)

## XMLHttpRequest 对象

所有现代浏览器都通过 **`XMLHttpRequest`构造函数**原生支持 XHR 对象

```js
let xhr = new XMLHttpRequest();
```



<span style="font-size:20px">使用 XHR</span>

<hr>

+ 使用 XHR 对象首先要调用 **`open()`方法初始化请求**，`open()`方法接收 3 个参数：

    1.  请求类型（*"get"、"post" 等*）
    2.  请求 URL
    3. 表示请求是否异步的布尔值

    如下例。

    ``` js
    xhr.open("get", "example.php", false);
    ```

    上面这行代码就可以向 `example.php`发送一个同步的 GET 请求。

    需注意以下几点：

    1. 这里的 URL 是相对于代码所在页面的，当然也可以使用绝对 URL
    2. 调用`open()`不回实际发送请求，只是为发送请求做好准备
    3. 只能访问**同源 URL**，也就是域名相同、端口相同、协议相同。如果请求的 URL 与发送请求的页面在任何方面有所不同，则会抛出安全错误

    

+ 要发送定义好的请求，必须像下面这样调用**`send()`**方法

    ``` js
    xhr.open("get", "example.txt", false);
    xhr.send(null);
    ```

    `send()`方法接收一个参数，是**作为请求体发送的数据**。如果不需要发送请求体，则需传入`null`

    调用`send()`之后，请求就会发送到服务器

    因为这个请求是同步的，所以 JS 代码会等待服务器响应之后再继续执行。收到响应后，XHR 对象的以下**属性**会被填充上数据：

     1. `responseText`：字符串形式的响应数据

     2. `responseXML`：XML 形式的响应数据。（*如响应的内容类型是`text/xml` 或 `application/xml`，那就是包含响应数据的 XML DOM文档*）

     3. `status`：响应的 HTTP 状态

        在 HTTP1.1 协议下，HTTP 状态码可分为五大类，其中：

        + 2xx：处理成功响应类
        + 3xx：重定向响应类

     4. `statusText`：响应的 HTTP 状态描述



虽然可以像前面的例子一样发送同步请求，但多数情况下最好还是使用异步请求，这样可以不堵塞 JS 代码继续执行



+ XHR 对象有一个 **`readyState`**属性，表示当处在请求/响应过程的哪一个阶段

    每次 `readyState`从一个值变为另一个值，都会触发`readystatechange`事件，可以借机检查`readyState`的值。一般来说我们只关心`readyState`的值是 4（*已经收到所有响应，可以使用了*），表示数据已就绪。

    为保证跨浏览器兼容，`onReadystatechange`事件处理程序应该在调用`open()`前赋值，见下例。

    ```js
    let xhr = new XMLHttpRequest();
    xhr.onReadystatechange = function() {
        if (xhr.readyState == 4) {
            if (xhr.status >= 200 && xhr.status < 300) {
                alert(xhr.responceText);	// ?
            } else {
                alert(`Request was unsuccessful: ${xhr.status}`);
            }
        }
    };
    xhr.open("get", "example.txt", true);	// 跨域访问需配置？
    xhr.send(null);
    ```

    为保证跨浏览器兼容，onReadystatechange 事件处理程序应该在调用`open()`之前赋值。以上代码使用 DOM Level 0 风格为 XHR 对象添加事件处理程序。与其他事件处理程序不同，onReadystatechange 事件处理程序不会收到 `event`对象，在该事件处理程序中必须使用 XHR 对象来确定接下来该做什么。 	==？==



<span style="font-size:20px">HTTP头部</span>

<hr>

==。。。？==



<span style="font-size:20px">GET请求</span>

<hr>

最常用的请求方法是 GET 请求，用于向服务器查询某些信息。必要时，需在 GET 请求的 URL 后面添加查询字符串参数。对 XHR 而言，查询字符串必须正确编码==？==后添加到 URL 后面，然后再传给`open()`。

发送 GET 请求最常见的一个错误是查询字符串格式不对。

每个名和值都必须使用`uncodeURIComponent()`编码、所有名/值对必须以`&`分隔。下面的例子自定义了一个`addURLParam()`函数保证通过 XHR 发送请求的 URL 格式正确。

``` js
function addURLParam(url, name, value) {
    url += (url.indexOf("?") == -1 ? "?" : "&");
    url += encodeURIComponent(name) + "=" + encodeURIComponent(value);
    return url;
}
```



<span style="font-size:20px">POST 请求</span>

<hr>

第二个最常用的请求是 POST 请求，用于向服务器发送应该保存的数据。每个 POST 请求都应该在请求体中携带提交的数据，而 GET 请求则不然。==？== POST 请求的请求体可以包含非常多的数据，而且数据可以是任意格式。



+ 要**初始化 POST 请求**，`open()`的第一个参数要传`"post"`。

+ 接下来就是给`send()`传入要发送的数据。

    因为 XHR 最初主要设计用于发送 XML，所以可以传入序列话之后的 XML DOM 文档==？==作为请求体。当然也可以传入任意字符串。



默认情况下，对服务器而言，POST 请求与提交表单是不一样的。



# JSON 字符串与 JSON 对象的相互转化

`JSON.parse(str)`将字符串转对象



`JSON.stringigy(obj)`将对象转字符串



# 参考链接

[Ajax基础](https://juejin.cn/post/6844904017693261832)

JavaScript高级程序设计（第四版）第24章

