# MySQL + NodeJS

```shell
$ yarn add mysql
```



## 连接数据库

```js
var mysql  = require('mysql');  
 
var connection = mysql.createConnection({     
  host     : 'localhost',       
  user     : 'root',              
  password : '123456',       
  port: '3306',                   
  database: 'test' 
}); 
 
connection.connect();
```

+ Demo——查询并返回结果集:

    ```js
    case "/showUser":
    	res.writeHead(200, {
      "Content-Type": "application/json",
    	});
      new Promise(resolve => {
        connection.connect();
        const sql = "SELECT `username` FROM userList";
        connection.query(sql, (err, result) => {
          if (err) {
            return;
          }
          resolve(JSON.stringify(result));
        });
      })
        .then(data => {
        res.end(data);
      })



# CRUD

+ <span style="font-size:22px">查询：</span>

    关于在查询语句中使用变量——**占位符**：

    ```js
    const sql = "SELECT * FROM userList WHERE name = ? AND pwd = ?";
    const { username, pwd } = responseData.query;
    connection.query(sql, [username, pwd], (err, result) => {
      
    })
    ```

    



# Ref

+ 连接数据库：

    [Node.js 连接 MySql | 菜鸟教程](https://www.runoob.com/nodejs/nodejs-mysql.html)



# DEBUG

+ [返回结果集出错](https://www.cnblogs.com/xiaoliu66007/p/6785769.html)：

    + Q：TypeError: Object prototype may only be an Object or null: [{"username":"test"}]

    + A：同步改为异步 Promise 返回：

        ```js
        new Promise(resolve => {
        
          connection.connect();
          const sql = "SELECT `username` FROM userList";
          connection.query(sql, (err, result) => {
            if (err) {
              console.log('[SELECT ERROR] - ',err.message);
              return;
            }
            resolve(JSON.stringify(result));
          });
        })
          .then(data => {
          res.end(data);
        })
        ```

        