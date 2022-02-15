const http = require("http");

http.get("http://localhost:8000/data", res => {
    let data = "";
    res
        .setEncoding("utf-8")
        .on("data", chunk => {
            data += chunk;
        })
        .on("end", () => {
            res.statusCode = 200;
            console.log(JSON.parse(data));
        })
})
const data = JSON.stringify([
    {
        name: "Jim",
        age: "20",
    },
    {
        name: "Jack",
        age: "18",
    },
]);
const options = {
    hostname: "localhost",
    port: 8000,
    path: "/data",
    method: "POST",
    headers: {
        "Content-Type": "application/json",
        "Content-Length": data.length,
    },
}
const req = http.request(options, res => {
    res.on("data", d => {
        process.stdout.write(d);    // ?
    })
})
req.on("error", err => {
    console.log(err);
})
req.write(data);
req.end();