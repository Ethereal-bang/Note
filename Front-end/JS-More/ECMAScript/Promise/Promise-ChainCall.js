// 关于Promise链式调用顺序:
/*
* 执行then时前面Promise为resolved状态——将回调放入微任务队列:
* 执行then时前面前面Promise为pending状态——回调存储在promise内部，promise被resolved后才将回调放入微任务队列
*/
{
    new Promise((resolve, reject) => {
        resolve('1');
        // console.log("promise1")
    }).then((data) => {
        // console.log("then1", data)
    })
    // console.log("外部1")// 执行顺序: promise 外部 then1

    new Promise((resolve, reject) => {
        // console.log("promise2")
        setTimeout(() => resolve(2), 1000);
    }).then(data => {
        // console.log("then2", data);
    })
    // console.log("外部2")   // 顺序: promise 外部 then2(1s后)
}
// 一个promise被resolve时——会遍历之前通过then给这个promise注册的所有回调，一次放入微任务队列
{
    let p = new Promise((resolve, reject) => {
        setTimeout(resolve, 1000, 1);
    })
    p.then(data => {
        // console.log("then1", data);
    })
    p.then(data => {
        // console.log("then2", data);
    })
    // console.log("外部")   // 顺序: 外部 (then11 then21)1s后
}
// then的返回值
{
    let p = new Promise((resolve, reject) => {
        resolve();
    })
        .then(() => {
            return new Promise((resolve, reject) => {
                setTimeout(resolve, 1000)
            })
                .then(() => {
                    console.log("内部第一个then")
                })
        })
        .then(() => {
            console.log("外部第二个then")
        })
}
// 链式调用实例:
{
    new Promise((resolve, reject) => {
        // console.log("log: 外部promise");
        resolve();
    })
        .then(() => {
            // console.log("log: 外部第一个then");
            new Promise((resolve, reject) => {
                // console.log("log: 内部promise");
                resolve();
            })
                .then(() => {
                    // console.log("log: 内部第一个then");
                })
                .then(() => {
                    // console.log("log: 内部第二个then");
                });
        })
        .then(() => {
            // console.log("log: 外部第二个then");
        });
    // log: 外部promise
// log: 外部第一个then
// log: 内部promise
// log: 内部第一个then
// log: 外部第二个then
// log: 内部第二个then
}

// then返回的Promise状态
{
    // 返回pending promise的情况——then也返回pending promise
    new Promise((resolve, reject) => {
        resolve(1)
    }).then((data) => {
        return new Promise((resolve, reject) => {
            setTimeout(resolve, 1000, data);
        })
    })
    // 返回值——then返回
    let p2 = new Promise((resolve, reject) => {
        resolve(2)
    }).then(data => {
        return 3;
    })
    // setTimeout(console.log, 0, p)    // Promise { 3 } 参数为该返回值
    // 返回resolved promise
    let p3 = new Promise((resolve, reject) => {
        resolve(3);
    }).then(data => {
        return Promise.resolve(4);
    })
    // setTimeout(console.log, 1000, p3)   // Promise { 4 } 参数为onResolved参数
    // 无返回值——返回的promise参数值undefined
    let p4 = new Promise((resolve, reject) => {
        resolve(4);
    }).then(data => {
        console.log("无返回值");
    }).then(data => {
        console.log(data)
    })
    // setTimeout(console.log, 1000, p4);  // Promise { undefined }
}