# 工厂模式



# 单例模式

工厂模式每一次调用产生不同结果，单例模式与之相反，无论多少次调用获得的都是同一结果

一个类**产生同一个实例**，例如 redux 中的 store。



1. 以下是一个用 **Class** 构建单例模式的例子：

    ```js
    class Window {
        static instance	
        constructor(){
        }
    
        static getInstance(){
            if(!Window.instance){	// 第一次调用时，`instance`是`undefined` 
                Window.instance = new Window()
            }
            return Window.instance
        }
    }
    
    let w1 = Window.getInstance()	// Window {}
    let w2 = Window.getInstance()
    console.log(w1 === w2);	// true
    ```

    > **类的静态属性**：
    >
    > 由`static`定义，指的是 Class 本身的属性，而不是定义在实例对象（`this`）上的属性

2. 用 **函数** 构建单例模式

    ```js
    function Window(){
    }
    
    Window.getInstance = (function(){	// `.getInstance`得到的是该匿名函数返回的第10行 fn
        let window;	// 利用闭包，类似于上例的`static`
      	// 第5行代码只会在第一次调用`.getInstance`时运行
        return function(){
            if(!window){
                window = new Window()	// 调第1行`Window`函数
            }
            return window
        }
    })()
    
    let t1 = Window.getInstance()   // Window {}
    let t2 = Window.getInstance()
    console.log(t1 === t2);
    ```

    > **关于返回的`Window {}`**：
    >
    > 表示一个类，例如：
    >
    > ```js
    > let Foo = function (x, y) {
    >     this.x = x;
    >     this.y = y;
    > }
    > 
    > let point = new Foo(2, 3);	// Foo {x: 2, y: 3} 
    > ```

3. **透明单例**

    透明单例：不需向前例一样调用方法创建实例

    通过在类的内部返回指向原型的`this`。

    ```js
    //透明单例
    let Window = (function() {
        let window;
      // 这时候this指向全局
        let Window = function() {
            if(window){
                return window
            }
            else{
                return (window = this)	// this指向实例，为`Window {}`，
            }
        }
        return Window	// 得到的时第5排的函数
    })()
    
    let w1 = new Window()   // Window {}
    let w2 = new Window()
    console.log(w1)
    ```

    > **上例的自执行与返回函数：**
    >
    > ```js
    > // 在创建第2排自执行函数时就会执行：
    > let window
    > // 第一次执行`new Window()`代码，相当于
    > let Window = function Window() {
    >   return window = this;
    > }
    > ```
    >
    > 打印`Window`，得到`[Function: Foo]`。所以`new`时会自动执行第5排的函数不需再次调用。

4. 另一种创建**透明单例**方法

    通过在内部`new Window()`创建单一的`Window {}`实例

    ```js
    function Window(){
    }
    
    let createInstance = (function(){
        let instance;
        return function(){
            if(!instance){
                instance = new Window()
            }
            return instance
        }
    })()
    
    let w1 = createInstance()
    let w2 = createInstance()
    ```

5. 适用度更广的**接受构造函数为参数**的透明单例

    ```js
    function Window(name, age) {
      this.name = name;
      this.age = age;
    }
    
    let createInstance = function(Constructor){
        let instance;
        return function(){
            if(!instance){
                Constructor.apply(this,arguments)	
              	// 此时this为原型`createWindow`, arguments指20排调用时传参
              
                // this.__proto__ = Constructor.prototype	继承该构造函数的原型
                Object.setPrototypeOf(this,Constructor.prototype)
                instance = this
            }
            return instance
        }
    }
    
    let createWindow = createInstance(Window)
    let w1 = new createWindow("一号", 18)
    let w2 = new createWindow()
    console.log(w1, w2)
    // Window { name: '一号', age: 18 } Window { name: '一号', age: 18 }
    console.log(w1 === w2)  // true
    ```

6. **应用**单例模式的实例

    功能：点击出现表单，多次点击也只创建一个

    <img src="https://gitee.com/ethereal-bang/images/raw/master/20211018203756.png" alt="image-20211018203748998" style="zoom:50%;" /><img src="https://gitee.com/ethereal-bang/images/raw/master/20211018203807.png" alt="image-20211018203806955" style="zoom:53%;" />

    ```js
    class LoginModel{
      static instance;   // 相当于前例的闭包
      constructor(){
        // 要显示的form表单
        this._element = document.createElement("div")
        this._element.innerHTML = (`
          <form>
          用户名:<input/>
          <button>登录</button>
          </form>
    		`)
        document.getElementsByTagName("body")[0].appendChild(this._element)
      }
      show(){
        this._element.style.display = "block"
      }
      hidden(){
        this._element.style.display = "none"
      }
      static getInstance(){
        if(!LoginModel.instance){
          LoginModel.instance = new LoginModel()
        }
        return LoginModel.instance
      }
    }
    document.getElementById("show-button").addEventListener("click",()=>{
      const instance = LoginModel.getInstance()
      instance.show()
    })
    document.getElementById("hidden-button").addEventListener("click",()=>{
      const instance = LoginModel.getInstance()
      instance.hidden()
    })
    ```

7. **应用**单例实现 Node 缓存文件：

    ```js
    const express = require("express")
    const app = express()
    
    const { resolve } = require("path")
    
    app.get("/api/users/:id",function(req,res){
        /**
         * {
         * 		id:"87"
         * }
         */
        const id = req.params.id
        const cache = {}
        let content
        
        //缓存已经读取过的文件
        if (cache[id]) {
            res.send(cache[id])
        } else {
            content = require("fs").readFileSync(resolve(__dirname,`${id}.json`),"utf-8")
            if (!content) {
                res.status(404).send("没有找到你想要的文件")
            } else {
                cache[id] = JSON.parse(content)
                res.send(content)
            }
        }
    })
    
    app.listen(3000,function(err){
        if(!err){
            console.log("服务器启动了");
            return
        }
        console.log(err);
    })
    ```

    

# 适配器模式

将一个类的**接口转换**成另一个类的接口，以满足需求，使不兼容的问题得以解决

优点：

+ 适配对象、库、数据
+ 提高类的复用



1. 电源适配器

    实现将输出 220V 电压转换为 24V

    typescript 版本：

    ```typescript
    class Socket{
        output(){
            return "220V"
        }
    }
    
    abstract class Power{
        abstract charge():void
    }
    
    class PowerAdaptor extends Power{
        constructor(public socket: Socket){
            super()
        }
        charge():string{
            return this.socket.output() + "转换为24V"
        }
    }
    
    const adaptor = new PowerAdaptor(new Socket())
    console.log(adaptor.charge());
    ```

    > **abstract 抽象类**：
    >
    > 不能实例化，只能实例化其子类

    JavaScript 版本：

    ```js
    class Socket {
        output() {
            return "220V";
        }
    }
    
    class PowerAdaptor {
        constructor(socket) {
            this.socket = socket;   // Socket创建的实例
        }
        charge() {
            return this.socket.output() + "转换为24V";
        }
    }
    
    const adaptor = new PowerAdaptor(new Socket())
    console.log(adaptor.charge())   // 220V转换为24V
    ```

2. 实现适配客户端、服务器的 Axios

    ```js
    function axios(config) {
        let adaptor = getDefaultAdaptor()
        return adaptor(config)
    }
    
    // 判断适配客户端还是服务端
    function getDefaultAdaptor() {
        let adaptor;
        //不存在xhr对象，在客户端发请求
        if(typeof XMLHttpRequest !== "undefined"){
            adaptor = xhr
        }
        //不存在process对象,在服务端发送请求
        if(typeof process !== "undefined"){
            adaptor = http
        }
        return adaptor
    }
    
    function xhr(config){
        // return ...
    }
    
    function http(config){
        // return ...
    }
    ```

3. 实现将 Ajax 请求适配为 Axios 请求

    ```js
    const $ = require("jquery")
    const axios = require("axios")
    
    $.ajax = function(options){
        return toAxiosAdaptor(options)
    }
    // 适配器
    function toAxiosAdaptor(options){
        return axios({
            url:options.url,
            method:options.type
            //(data)=>{} 
        }).then(options.success).catch(options.error)
    }
    
    // 使用：
    $.ajax({
        url:"http://localhost:3000/api/user",
        type:"GET",
        success(data){
           console.log(data);
        },
        error(err){
            console.log(err);
        }
    })
    ```

4. 将函数`fs.readFile`转换为 Promise 的适配器==?==

    ```js
    const fs = require("fs")
    const { promisify } = require("util")
    fs.readFile("./1.txt","utf-8",function(err,data){
       
    })
    
    function promiseAdaptor(...args){
        return new Promise(function(resolve,reject){
            fs.readFile(...args,function(err,data){
                resolve(data)
            })
        })
    }
    
    (async function(){
        let content = await promiseAdaptor(__dirname+"\\5.html","utf-8")
        console.log(content);
    })()
    
    function promisify(callbcakFn){
        return function(...args){
            return new Promise(function(resolve,reject){
                callbcakFn(...args,function(err,data){
                    err?reject(err):resolve(data)
                })
            })
        }
    }
    
    (async function(){
        let promiseFn = promisify(fs.readFile)
        const content = await promiseFn(__dirname+"\\5.html","utf-8")
        console.log(content);
    })()
    
    ```

5. Generator 转换为 Await 自动执行

    ```js
    function generatorToAwait(generator) {
      const iterator = generator();
      return new Promise((resolve, reject) => {
        (function repeat() {
          nextRes = iterator.next();
          const { value, done } = nextRes;
          
          if (done) {
            resolve(value);
          } else {
            value.then(() => {	// 因为该value是个Promise，让其resolve后自动执行下一yield
              repeat()
            })
          }
        })();
      })
    }
    
    // 使用：
    function* generator() {
        const a = yield awaitFunction();
        yield awaitFunction();
        yield awaitFunction();
        yield awaitFunction();
    }
    const awaitFunction = () => {
      return new Promise(resolve => {
        setTimeout(() => {
          console.log(111);
          resolve();
        }, 1000)
      })
    }
    
    generatorToAwait(generator);	// 执行效果：每隔一秒自动打印111，共4次
    ```
    
    

# 装饰者模式

不改变现有对象代码的情况下，动态地给该对象添加一些额外的功能，是一种代替继承实现对类的扩展的替代方案
