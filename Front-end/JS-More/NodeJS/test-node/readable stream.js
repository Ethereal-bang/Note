const stream = require('stream');
const fs = require("fs");
const EventEmitter = require('events')

const file = fs.createReadStream('./1.txt', {
    encoding: 'utf-8', // 编码格式 没有这行读到的是<Buffer xx>
    autoClose: true, // 是否关闭读取文件操作系统内部使用的文件描述符
    start: 0,
    end: 4,
    highWaterMark: 2,
});
file.on('open', (fd) => {
    console.log('开始读文件', fd);
})
file.pause();
file.on('data', (data) => { // 这个事件注掉后，下面end也不会触发
    console.log('读取到的数据：', data);
})
file.on('end', () => {
    console.log('文件读取完毕');
})