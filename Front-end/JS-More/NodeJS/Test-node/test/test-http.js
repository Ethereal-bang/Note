const http = require('http');
const fs = require('fs');
const path = require('path');

// 创建http服务器
function createServer() {
    http.createServer((request, response) => {
        // 发送HTTP头部（状态值，内容类型
        response.writeHead(200, {'Content-Type': 'text/plain'});
        // 发送响应数据
        response.end("Hello world");
    })// （返回server对象）监听端口等待连接
        .listen(8000, () => {
            console.log("server is running at http://localhost:8000");
        })
}

// 简易后端
// 服务端
