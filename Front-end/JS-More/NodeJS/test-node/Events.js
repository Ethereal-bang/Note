const EventEmitter = require('events');

class MyEmitter extends EventEmitter {}

const myEmitter = new MyEmitter();
// .on()用于注册监听器
myEmitter.on('event', () => {
    console.log('an event occurred');
});
myEmitter.on('newListener', () => {
    console.log('add new listener')// 添加了新的事件才触发（第一次不算
})
// .emit()用于触发事件
myEmitter.emit('event');    // an event occurred

myEmitter.on('test', () => {
    console.log('a test for name');
})
myEmitter.emit('test')  // 