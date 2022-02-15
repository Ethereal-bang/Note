// 简易文件服务器——由路径返回文件
import fs from "fs";
import http from "http";
import path from "path";

function createFileServer() {
    const fileShow = pathname => {
        return new Promise(resolve => {
            fs.stat(pathname, (err, stats) => {
                if (err || !stats.isFile()) {
                    // 错误或为文件夹
                    resolve(false);
                }
                fs.readFile(pathname, "utf-8", (err, data) => {
                    if (err)
                        resolve(false);
                    resolve(data);
                })
            })
        })
    }
    http.createServer((request, response) => {
        const { url, method } = request;
        if (method === "GET") {
            fileShow(path.resolve(__dirname, "." + url))    // ?
                .then(data => {
                    if (!data) {
                        response.writeHead(404); // 设置状态值
                        response.end("404 not found~");  // 设置响应数据
                    } else {
                        response.writeHead(200, {
                            "Content-Type": "text/plain; charset=utf-8",
                        })
                        response.end(data);
                    }
                })
        }
    })
        .listen(8000, () => {
            console.log("server is running at http://localhost:8000");
        })
}