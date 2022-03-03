const fs = require('fs');

// 创建并写入数据到文件
const file = fs.createWriteStream('./1.txt');   // file: WriteStream
for (let i = 1; i <= 9; i++) {
    file.write(i + ''); // 将其变为字符串
}
