const fs = require('fs');
const fsPromise = require('fs/promises');

// 创建并写入数据到文件
function writeFileStream() {
    const file = fs.createWriteStream('./1.txt');   // file: WriteStream
    for (let i = 1; i <= 9; i++) {
        file.write(i + ''); // 将其变为字符串
    }
}

// 写文件 回调形式
function writeFileCallback() {
    const data = [
        {
            name: "Jack",
            age: 18,
        },
        {
            name: "Lav",
            age: "20",
        },
    ]
    fs.writeFile("./2.json", JSON.stringify(data), err => {
        if (err)
            console.log("写入时出错", err);
    })
}

// 读文件 同步形式
try {
    fs.readFileSync("./2.json");
} catch (e) {
    console.log("读取时出错：", e);
}

// 返回文件或目录信息
fsPromise.stat("./2.json")
    .then(res => {
        console.log(res);
    }, err => {
        console.log("返回信息时出错：", err);
    })

// 返回路径下目录、文件
fs.readdir('.', (err, files) => {
    if (!err)
        console.log(files);
})