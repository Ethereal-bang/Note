const fs = require('fs');
const EventEmitter = require('events')
const {Buffer} = require('buffer')

function createReadStream(path, options) {
    return new ReadStream(path, options);
}

class ReadStream extends EventEmitter {
    constructor(path, options) {
        super();
        this.path = path;
        this.fd = options.fd;
        // 利用 || 设置默认值
        this.flags = options.flags || 'r';
        this.encoding = options.encoding || null;   // 读取格式
        this.mode = options.mode || 0o666   // 操作权限
        this.autoClose = options.autoClose || true; // 读取完成后是否自动关闭文件
        this.emitClose = options.emitClose || true; // 流销毁后是否触发close事件
        this.start = options.start || 0;
        this.end = options.end || fs.statSync(path).size;
        this.highWaterMark = options.highWaterMark || 64 * 1024;
        this.fs = options.fs;
        this.flowing = true;
        // 有添加新事件时
        this.on('newListener', (type) => {
            if (type === 'data') {
                // this.flowing = true;    // 流动模式
                this.open();
            }
        })
        this.on('error', (err) => {
            console.log(err)
        });
    }

    open() {
        fs.open(this.path, this.flags, this.mode, (err, fd) => {
            if (err) {
                return this.emit('error', err);
            }
            this.fd = fd;
            // 打开文件后开始读取：
            this.emit("open", fd);
            this.read(fd);  // fd的作用
        })
    }

    read(fd = this.fd) {
        console.log("start read")
        const that = this;  // 储存this
        const buffer = Buffer.alloc(this.highWaterMark);   // 分配被读数据将写入的缓冲区
        that.pos = that.start;

        function next() {
            // toReadNum标示每一次读取多少字节（由end而定
            that.toReadNum = (that.pos+that.highWaterMark > that.end) ? (that.end+1-that.pos) : that.highWaterMark;
            fs.read(fd, buffer, 0, that.toReadNum, that.pos, (err, byteRead) => {
                // console.log(that.pos, that.toReadNum, byteRead);
                if (err) {
                    that.emit('error', err);
                }
                if (!that.flowing) {
                    return;
                }
                // 终止条件：
                if (that.pos > that.end) {
                    that.flowing = false;
                    // 判断用户是否需要自动关闭文件
                    if (that.autoClose) {
                        fs.close(that.fd, (err) => {
                            if (err) {
                                return that.emit('error', err);
                            }
                            // 流毁后触发close事件
                            if (that.emitClose) {
                                return that.emit('close');
                            }
                        })
                        // 读取完成触发end事件
                        return that.emit('end');
                    }
                }
                that.emit("data", buffer.slice(0, byteRead).toString());
                that.pos += byteRead;
                next();
            })
        }
        next();
    }
    pause() {
        this.flowing = false;
    }
    resume() {
        this.flowing = true;
        // this.read(this.fd)  //?
    }
}

/* test: */
const file = createReadStream('./1.txt', {
    start: 0,
    end: 4,
    highWaterMark: 2,
})
file.on('open', fd => {
    console.log('open', fd);
})
file.pause();
file.on('data', (data) => {
    console.log("data", data);
})
file.resume();
file.on('close', () => {
    console.log("close");
})



