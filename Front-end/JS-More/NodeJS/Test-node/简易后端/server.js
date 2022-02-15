const http = require('http');
const querystring = require("querystring");

http.createServer((req, res) => {
    const { url, method } = req;
    const path = url.split("?")[0]; // 分离出参数前的路径
    const query = querystring.parse(url.split("?")[1]);   // 参数

    const responseData = {
        url,
        method,
        path,
        query
    }
    // console.log(responseData)

    // 请求头
    res.writeHead(200, {
        "Content-Type": "application/json",
    })

    if (method === "GET") {
        res.end(JSON.stringify(responseData));
    } else if (method === "POST") {
        let postData = "";
        req
            .on("data", chunk => {
                // console.log(chunk)
                postData += chunk;
            })
            .on("end", () => {
                responseData.postData = postData;
                res.end(postData);
            })
    }
})
    .listen(8000, () => {
        console.log("server is running at http://localhost:8000")
    })