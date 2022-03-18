# Ajax
初步自检

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



## Ajax 简介

Ajax（*Asynchronous JavaScript+XML*）

Ajax 是一种在无需加载整个页面的情况下，能更新部分网页的技术



## 工作原理

<span style="font-size:20px">包含的技术</span>

<hr>

Ajax 是基于现有的 Internet 标准，并联合使用它们：

1. XMLHttpRequest（*XHR对象*） 对象 （*异步与服务器交换数据*）

    XHR 对象为发送服务器请求和获取响应提供了合理的接口，这个接口可以实现**异步**从服务器获取额外数据，意味着用户不用点击页面刷新也可以获取数据

2. JavaScript 、DOM  （*信息显示、交互*）

    通过 XHR 对象获取数据后，可以使用 DOM 方法把数据插入网页。

3. CSS

4. XML（*转换数据的格式*）

    虽然 Ajax 名称中包含 XML，但实际上 Ajax 通信 与数据格式无关，这个技术主要是可以实现不刷新页面的情况下从服务器获取数据，格式并不一定是 XML。



<span style="font-size:20px">原理</span>

<hr>

通过 XMLHttpRequest 对象来向服务器发送异步请求 ，从服务器获得数据，然后用 js 来操作 DOM 而更新页面。

<img src="https://i.loli.net/2021/04/05/m2nWpOTrGIEzRyQ.png" alt="image-20210405100239207" style="zoom: 67%;" />

Ajax 的工作原理相当于在用户和服务器之间加了一个中间层（*ajax引擎*），使用户操作与服务器响应异步化。



## 使用

![img](https://www.yuque.com/api/filetransfer/images?url=https%3A%2F%2Fcansiny.oss-cn-shanghai.aliyuncs.com%2Fimages%2F1613646249859.png&sign=5d0c6d54697f0ae4bf57aa9a6182894203d5ace0edb4e02b760e48ad4398a2c5&x-oss-process=image%2Fresize%2Cw_1280%2Climit_0)

+ 基本使用：

    ```js
    // 实例化XMLHttpRequest对象：
    const xhr = new XMLHttpRequest()
    // 初始化一个get请求：
    xhr.open("get", "http://cloud-music.pl-fe.cn/personalized", true)
    // 接收返回值：
    xhr.onreadystatechange = () => {
      if (xhr.readyState === 4) {
        if ((xhr.status >= 200 && xhr.status < 300) || xhr.status === 304) {
          const res = JSON.parse(xhr.responseText)
          // do something
        } else {
          console.log("请求失败")
        }
      }
    }
    //发送请求
    xhr.send()
    ```



<span style="font-size:22px">xhr.open():</span>

+ 使用 XHR 对象首先要调用 **`open()`方法初始化请求**，`open()`方法接收 3 个参数：

    1.  请求类型（*"get"、"post" 等*）
    2.  请求 URL
    3.  表示请求是否异步的布尔值

    如下例。

    ``` js
    xhr.open("get", "example.php", false);
    ```

    上面这行代码就可以向 `example.php`发送一个同步的 GET 请求。

    需注意以下几点：

    1. 这里的 URL 是相对于代码所在页面的，当然也可以使用绝对 URL
    2. 调用`open()`不回实际发送请求，只是为发送请求做好准备
    3. 只能访问**同源 URL**，也就是域名相同、端口相同、协议相同。如果请求的 URL 与发送请求的页面在任何方面有所不同，则会抛出安全错误

    

+ <span style="font-size:22px">`send`</span>

    ``` js
    xhr.open("get", "example.txt", false);
    xhr.send(null);
    ```

    `send()`方法接收一个参数，是**作为请求体发送的数据**。如果不需要发送请求体，则需传入`null`

    调用`send()`之后，请求就会发送到服务器

    

+ <span style="font-size:22px">响应：</span>

    收到响应后，XHR 对象的以下**属性**会被填充上数据：

     1. `responseText`：字符串形式的响应数据	

     2. `responseXML`：XML 形式的响应数据。（*如响应的内容类型是`text/xml` 或 `application/xml`，那就是包含响应数据的 XML DOM文档*）

     3. `status`：响应的 HTTP 状态

        在 HTTP1.1 协议下，HTTP 状态码可分为五大类，其中：

        + 2xx：处理成功响应类
        + 3xx：重定向响应类

     4. `statusText`：响应的 HTTP 状态描述



+ <span style="font-size:22px">readyState:</span>

    XHR 对象有一个 **`readyState`**属性，表示当处在请求/响应过程的哪一个阶段

    每次 `readyState`从一个值变为另一个值，都会触发**`readystatechange`**事件，可以借机检查`readyState`的值。一般来说我们只关心`readyState`的值是 4（*已经收到所有响应，可以使用了*），表示数据已就绪。

    

+ <span style="font-size:20px">HTTP头部</span>

    规范把 HTTP 请求分为三个部分：**状态行、请求头、消息主体**。

    + Content-Type：服务端通常是根据 headers 中的 Content-Type 字段来获知请求中的消息主体是用何种方式编码
    + Authorization



<span style="font-size:20px">GET请求</span>


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


第二个最常用的请求是 POST 请求，用于向服务器发送应该保存的数据。每个 POST 请求都应该在请求体中携带提交的数据，而 GET 请求则不然。 POST 请求的请求体可以包含非常多的数据，而且数据可以是任意格式。



+ 要**初始化 POST 请求**，`open()`的第一个参数要传`"post"`。

+ 接下来就是给`send()`传入要发送的数据。

    因为 XHR 最初主要设计用于发送 XML，所以可以传入序列话之后的 XML DOM 文档==？==作为请求体。当然也可以传入任意字符串。



默认情况下，对服务器而言，POST 请求与提交表单是不一样的。



+ **JSON 字符串与 JS 对象的相互转化：**

    `JSON.parse(str)`解析JSON字符串，构造由字符串描述的JavaScript值或对象

    `JSON.stringify(obj)`将一个 JavaScript 对象或值转换为 JSON 字符串



## 实现

+ [封装方法](https://github.com/Ethereal-bang/CSA-FrontEnd/tree/main/AJAX%2BES6)



# [Fetch](https://developer.mozilla.org/zh-CN/docs/Web/API/Fetch_API)

> Fetch 在 Node 环境没有被支持，浏览器环境可直接使用

+ **Demo：**

    ```js
    fetch("https://api.github.com/users/github")
        .then(response => {
            console.log(response)
            return response.json();
        })
        .then(data => {
            console.log(data)
        })
    ```

    + **Response.json()** 接收一个 response 流，返回一个被解析为 JSON 格式的 promise 对象

    + 返回的 **Response 对象**如图：

        ![image-20220318095903895](https://gitee.com/ethereal-bang/images/raw/master/20220318095911.png)



+ <span style="font-size:22px">fetch() 第二个参数：</span>

    配置请求

    + 上传 JSON 数据：

        ```js
        const data = { username: "test1"};
        fetch("。。。", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(data),
        })
          .then(response => response.json())
          .then(data => {
          console.log("Success: ", data);
        })
          .catch(err => {
          console.log("Error: ", err);
        })
        ```

        

+ <span style="font-size:22px">fetch() 返回值</span>

    fetch() 无论成功与否返回一个 Promise 对象

    + **请求错误：**
        + 接收到一个代表**错误的 HTTP 状态码**时，从 fetch 返回的 Promise **不会被标记为 reject**。相反，它会将 Promise 状态标记为 resolve （如果响应的 HTTP 状态码不在 200 - 299 的范围内，则设置 resolve 返回值的 [`ok`](https://developer.mozilla.org/zh-CN/docs/Web/API/Response/ok) 属性为 false ）
        + 仅当**网络故障时或请求被阻止**时，才会标记为 **reject**

    

# [Axios](http://axios-js.com/zh-cn/docs/index.html)

+ Demo: 

    ```js
    axios.get('/user', {
        params: {
          ID: 12345
        }
      })
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
    
    axios.post('/user', {
        firstName: 'Fred',
        lastName: 'Flintstone'
      })
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
    ```




# 跨域

1. Q："No 'Access-Control-Allow-Origin' header is present on the requested resource"，且 "status 200"

    Reason：头部不匹配

    Solution：

    ```js
    res.writeHead(200, {
      "Content-Type": "application/json",
    #  "Access-Control-Allow-Origin": "*",
    });
    ```
    
    

# DEBUG

1. **form 表单内 button 事件提交无效: **

    + Q_Desc：

        ```html
        <form>
          <input placeholder="请输入1~6位账户名" type="text">
          <button>Login</button>
        </form>
        <script>
        	btn.onclick = () => {
            axios.get("/login")
          }
        </script>
        ```

    + A_Reason: form 表单自带默认本页提交且刷新页面，导致请求获取数据无效。

    + A_Solution：去掉`<form>`



# REF

+ Ajax:

    [Ajax基础](https://juejin.cn/post/6844904017693261832)

    《JavaScript高级程序设计（第四版）》第24章

    [AJAX + ES6 · 语雀](https://www.yuque.com/ldfgqb/fpkor3/kgltn0#f9979775)

    [Ajax 请求头中常见content-type](https://www.jianshu.com/p/10cdbb35ac87)

    [封装ajax请求的两种方式 - 掘金](https://juejin.cn/post/6844903901074833416)

    [原生 ajax 封装 - SegmentFault 思否](https://segmentfault.com/a/1190000037701554)

+ Fetch：

    [Response.json() - Web API 接口参考 | MDN](https://developer.mozilla.org/zh-CN/docs/Web/API/Response/json)
    
+ 跨域：

    [ajax跨域，这应该是最全的解决方案了 - SegmentFault 思否](https://segmentfault.com/a/1190000012469713)

