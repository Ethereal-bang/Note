# WebSocket

[WebSocket](https://websocket.org/) 是一种网络通信协议

> HTTP 是运行在 TCP 协议传输层上的应用协议，而 WebSocket 是通过HTTP 协议协商如何连接，然后独立运行在 TCP 协议传输层上的应用协议

![img](https://www.ruanyifeng.com/blogimg/asset/2017/bg2017051503.jpg)

## 客户端

使用 HTML5 的 WebSocket 接口。

```js
const ws = new WebSocket("ws://localhost:8080/${userId}");

ws.onopen = function(evt) { 
  console.log("Connection open ..."); 
  ws.send("Hello WebSockets!");
};
ws.onmessage = function(evt) {
  console.log( "Received Message: " + evt.data);
  ws.close();
};
ws.onclose = function(evt) {
  console.log("Connection closed.");
};      
```

### ws 实例属性

+ `readyState`：

    > - CONNECTING：值为0，表示正在连接。
    > - OPEN：值为1，表示连接成功，可以通信了。
    > - CLOSING：值为2，表示连接正在关闭。
    > - CLOSED：值为3，表示连接已经关闭，或者打开连接失败。

+ `onopen`，指定连接成功时回调函数

+ `onerror`

+ `onclose`，连接关闭后回调

+ `onmessage`，收到服务器数据后：

    ```js
    ws.onmessage = function(event) {
      var data = event.data;
      // 处理数据
    };
    
    ws.addEventListener("message", function(event) {
      var data = event.data;
      // 处理数据
    });
    ```

### ws 实例方法

+ `send`，向服务器发送数据
+ `close`



## 服务端

> 原理：
>
> 为了建立一个 WebSocket 连接，客户端浏览器首先要向服务器发起一个 HTTP 请求，这个请求和通常的 HTTP 请求不同，包含了一些附加头信息，其中附加头信息"Upgrade: WebSocket"表明这是一个申请协议升级的 HTTP 请求，服务器端解析这些附加的头信息然后产生应答信息返回给客户端，客户端和服务器端的 WebSocket 连接就建立起来了，双方就可以通过这个连接通道自由的传递信息，并且这个连接会持续存在直到客户端或者服务器端的某一方主动的关闭连接。

WebSocket 服务器常用的 Node 实现有三种：

- [µWebSockets](https://github.com/uWebSockets/uWebSockets)
- [Socket.IO](http://socket.io/)
- [WebSocket-Node](https://github.com/theturtle32/WebSocket-Node)

### Demo

1. 开启 http 服务器：

    ```js
    const server = http.createServer((req, res) => {
        console.log("Received request for: ", req.url);
        res.writeHead(404);
        res.end();
    })
    server.listen(8080, () => {
        console.log("Server is listening on port 8080");
    })
    ```

2. 开启 Websocket 服务：

    ```js
    const WebsocketServer = require("websocket").server;
    
    const wsServer = new WebsocketServer({
        httpServer: server,	// http服务器
        autoAcceptConnections: false,
    })
    ```

3. 处理每个连接到 ws 服务的请求：

    ```js
    // 判断源是否被允许连接 origin——请求的域
    function originIsAllowed(origin) {
      	// ...判断逻辑
        return true;
    }
    wsServer.on("request", (req) => {
        if (!originIsAllowed(req.origin)) {
            req.reject();	// 拒接连接请求
        }
        const connection = req.accept("echo-protocol", req.origin); 
      	connection.on("message", (mes) => {
        })
        connection.on("close", (reasonCode, desc) => {
            console.log(connection.remoteAddress, "disconnected.");	// 同一电脑的地址好像是一样
        })
    })
    ```

    + 如果连接设置了`echo-protocol`，客户端的请求也要对应：

        ```js
        ws = new WebSocket(`ws://localhost:8080`, 'echo-protocol'); 
        ```

### Tips

+ 广播给所有用户：`wsServer.broadcast()`
+ 向某一用户发送：`connection.send()`



# Web Socket in SpringBoot

1.**pom.xml:**

```xml
<dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-websocket</artifactId>
</dependency>
```

2.**配置 WebSocket 部署在 SpringBoot：**

```java
@Configuration
public class WebSocketConfig {    
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
```

> 这个Bean会自动使用@ServerEndpoint注解声明的Websocket dedpoint

3.**核心配置 WebSocketServer:**

```java
@ServerEndPoint("/websocket/{uid}")
@Component
public class WebSocketServer {
	private Session session;
  // 存放每个客户端对应的session对象
  private static Map<Integer, Session> socketMap = new ConcurrentHashMap<>();
	
  @OnOpen
  public void onOpen(Session session, @PathParam("uid") int uid) {
    this.session = session;
    socketMap.put(uid, session);
    System.out.println("有新连接加入。当前连接人数" + socketMap.size());
  }
  
  @OnClose
  /*...*/
  
  @OnMessage
  public void onMessage(String message) {/*...*/}
  
  public void sendMessage(String message, int id) throws IOException {
  socketMap.get(id).getBasicRemote().sendText(message);
  }
}
```

> **uid, Session 关系**
>
> 一个 uid 多次请求 ws 连接，得到的是不同 session。所有一个 uid 对应多个 session

> @OnOpen 等方法是自动调用的

4.**调用 WebSocketServer 方法实现发送消息等:**

```java
@Controller
@RequestMapping("/websocket")
public class WebSocketController {
  private WebSocketServer webSocketServer;
  @Autowired
  public void setWebSocketServer /*...*/

  @GetMapping("/{uid}")
	public void webSocket(@PathVariable("uid") int uid) throws IOException {
  	new WebSocketServer().sendMessage("test", uid);
  }

}
```



# Ref

WebSocket-客户端：

[WebSocket 教程 - 阮一峰的网络日志](https://www.ruanyifeng.com/blog/2017/05/websocket.html)

WebSocket-服务端：

[使用webSocket向特定的已连接用户发送消息？](https://qastack.cn/programming/16280747/sending-message-to-a-specific-connected-users-using-websocket)

[mdn/saples-server: MDN samples server; used for sampl...](https://github.com/mdn/samples-server)

WebSocket in SpringBoot