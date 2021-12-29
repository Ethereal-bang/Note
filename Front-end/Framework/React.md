



# React 配置

+ <span style="font-size:20px">学习用环境配置</span>

    不适用于实际生产环境，学习足够

    1. 安装较新版本 Node.js

    2. 创建一个新的项目

        命令行依次输入：

        ``` 
        npx create-react-app my-app
        cd my-app
        npm start
        ```

        结果是在  C 盘 HP 文件夹内创建了一个 my-app 文件夹。

    3. 浏览器中弹出<img src="https://i.loli.net/2021/04/25/eFEmUskDdQpwSLR.png" alt="image-20210425110124810" style="zoom:33%;" />

    4. 在该文件夹中找到 src.js 或 App.js 文件，将代码修改为

        ``` jsx
        import React, { Component } from 'react';
        import logo from './logo.svg';
        import './App.css';
         
        class App extends Component {
          render() {
            return (
              <div className="App">
                <div className="App-header">
                  <img src={logo} className="App-logo" alt="logo" />
                </div>
                <p className="App-intro">
                  你可以在 <code>src/App.js</code> 文件中修改。
                </p>
              </div>
            );
          }
        }
         
        export default App;
        ```

    5. 该网站`http://localhost:3000/`刷新

    6. 浏览器中添加扩展程序`React Developer Tools`到 Chrome

    7. 在`<head></head>`标签内插入以下 react 库：

        ``` html
        <script src="https://unpkg.com/react@17/umd/react.development.js" crossorigin></script>
        <script src="https://unpkg.com/react-dom@17/umd/react-dom.development.js" crossorigin></script>
        <script src="https://unpkg.com/babel-standalone@6/babel.min.js"></script>
        <script type="text/babel">
    ```
    
    8. 使用 jsx 语法需把`script`标签的`type`属性设置为`text/babel`

# JSX

## 为什么使用 JSX

``` jsx
const element = <h1>Hello,world</h1>;
```

这个标签语法被称为 JSX，是一个 JS 的语法扩展 js+XML，本质是语法糖。

在 React 中配合使用 JSX，JSX 可以很好地描述 UI 应该呈现出它应有交互的本质形式，创建虚拟 DOM 更方便。

React 不强制要求使用 JSX，但是将 JSX 和 UI 放在一起时会在视觉上有辅助作用。还可以使 React 显示更多有用的错误和警告信息。

> XML 和 JSON
>
> 起初使用 XML 存储数据：
>
> ``` xml
> <student>
> 	<name>TOM</name>
>     <age>19</age>
> </student>
> ```
>
> 人们发现，结构比存储内容更加复杂。于是有了 JSON 使用更多，但并不是完全取代。
>
> ``` json
> "{" name ":" Tom "," age ":" 19 "}"	// 为方便看，我加了几个空格分隔
> ```



## JSX 语法规则

+ <span style="font-size:20px">在 JSX 中使用 {} 嵌入表达式</span>

    ``` JSX
    const name = 'Josh';
    const element = <h1>Hello, {name}</h1>;
    ```
    
    在 JSX 语法中，可以在`{}`内放置任何有效的 JS 表达式，同时 **JSX 本身也是一个表达式**

+ <span style="font-size:20px">JSX "" 写入字面量</span>

    可以通过**使用`" "`将属性值指定为字符串字面量**
    

    ``` jsx
    const element = <h2 className="title" id={myId.toLowerCase()} />
    ```


> 注意：小驼峰命名
>
> 因为 JSX 语法上更接近 JS 而不是 HTML，所以 React DOM 使用“小驼峰命名”来定义属性的名称，而不使用 HTML 属性名称的命名约定
>
> 例如，JSX 里的`class`变成**`className`**，而`tabindex`变成`tabIndex`。

+ <span style="font-size:20px">内联样式 {{}} </span>

    内联样式，要用 **style={{key: value, key: value}}** 的形式写

    ```jsx
    const VDOM = (
      <h2 className="title">
      	<span style={{color:'white', fontSize:'29px'}}>{myDate.toLowerCase()}</span>  
      </h2> 		
    )   
    ```

+ <span style="font-size:20px">使用 JSX 指定子元素</span>

  
    JSX 标签里能够包含很多子元素（*嵌套标签*）：
    
    ``` jsx
    const element = (
    	<div>
        	<h1>Hello</h1>
            <h2>Good to see you.</h2>
        </div>
    );
    ```
    
    简洁写法：若一个标签里面没有内容，可以使用**`/>`闭合空标签**
    
    ``` jsx
    const element = <img src={user.avatarUrl} />
    ```

+ <span style="font-size:20px">JSX 表示对象</span>

    Babel 会把 JSX 译为`React.createElement()`函数调用，

    例如创建以下虚拟 DOM 时：
    
    ``` jsx
    const element = (
    	<h1 className="greeting">
            Hello, world
        </h1>
    );
    ```

    `React.createElement()`实际上创建了一个这样的对象：
    
    ``` jsx
    // 这是简化后的结构
    const element = {
        type: 'h1',
        props: {
            className: 'greeting',
            children: 'Hello, world'
        }
    };
    ```
    
    这些对象被称为”React 元素“，描述了希望在屏幕上看到的内容。React 通过读取这些对象，然后使用它们来构建 DOM 以及保持随时更新。

+ <span style="font-size:20px">根标签只能有一个</span>

+ <span style="font-size:20px">标签首字母大小写区分</span>

    **小写**字母开头，将该标签转为 HTML 中同名元素；若无，报错。

    **大写**字母开头，react 渲染对应组件；若没有定义该组件，报错。

    

# 元素渲染

+ <span style="font-size:20px">创建虚拟 DOM</span>

    
    元素是构成 React 应用的最小砖块，就是虚拟 DOM。
    
    元素描述了你在屏幕上想看到的内容
    
+ <span style="font-size:20px">将元素渲染为 DOM</span>

    <hr>
    将元素渲染为 DOM，即将虚拟 DOM 渲染到页面

    准备好一个“容器”`<div>`：

    ``` jsx
    <div id="root"></div>
    ```

    我们称其为“根 DOM 节点”，因为该节点内所有内容都将由 React DOM 管理。  

    想要将一个 React 元素渲染到根 DOM 节点中，只需要把它们一起传入**`ReactDom.render()`**：

    ``` jsx
    const element = <h1>Hello</h1>;
    ReactDOM.render(element, document.getElementById('root'));
    ```

+ <span style="font-size:20px">更新已渲染元素</span>

    
React 元素是不可变对象。一旦被创建，就无法更改它的子元素或者属性。
    
更新 UI 的方式是创建一个全新的元素传入`ReactDOM.render()`
    
以下是一个计时器的例子：
    
``` jsx
    function tick() {
        const element = (
        	<div>
        		<h1>Hello, world</h1>
            	<h2>It'is {new Date().toLocaleTimeString()}.</h2>
        	</div>
        );
        ReactDOM.render(element, document.getElementById('root'));
    }
    
    setInterval(tick, 1000);
    ```
    
> [window.setInterval | MDN](https://zh-hans.reactjs.org/docs/rendering-elements.html)
    >
    > 重复调用一个函数或执行一个代码段
    
React 只更新它需要更新的部分：React DOM 会将元素和它的子元素与它们之前的状态进行比较，并只会进行必要的更新来使 DOM 达到预期的状态



# 组件

组件允许我们将 UI 拆分为独立可复用的代码片段，并对每个片段进行独立构思。组件，从概念上类似于 JS 函数。接受任意的入参（*props*），并返回描述页面内容的 React 元素。

+ <span style="font-size:20px">函数组件与 class 组件</span>


    定义组件最简单的方式就是**编写 JS 函数**：

    ``` jsx
    function Welcome(props) {
        return <h1>Hello, {props.name}</h1>;
    }
    ```

    该函数是一个有效的 React 组件，因为它接收唯一带有数据的"props"（*代表属性*）对象并返回一个 React 元素。这类组件被称为“**函数组件**”，因为它本质上就是 JS 函数。

    

    还可以使用 ES6 的 **class 来定义组件**：

    几个必要条件：

    1. 必须继承`React.Component`父类
    2. 必须有``render()`
    3. `render()`要有返回值	返回想呈现的内容

    ``` jsx
    class Welcome extends React.Component {	// extends 继承
        render() {
        	return <h1>Hello, {this.props.name}</h1>;
        }
    }
    ReactDOM.render(<Welcome name={"app"} />)
    ```

     第 2 行的`render()`是放在`MyComponent`的原型对象上，供实例使用。

    那么实例在哪呢：

    ​	执行了`ReactDOM.render(<Mycomponent />....`之后，发生了什么：

    1. React 解析**组件标签**，找到了`MyComponent`组件
    2. 发现组件是使用类定义的，随后`new`出该类的实例，并通过该实例调用原型上的`render()`方法
    3. 将`render`返回的虚拟 DOM 转为真实 DOM，随后呈现在页面中

    

+ <span style="font-size:20px">渲染组件</span>

    React 元素也可以是用户自定义的组件：	

    ``` jsx
    const element = <Welcome name="Sara" />
    ```

    当 React 元素是用户自定义组件时，它会将 JSX 所接收的属性（*attributes*）以及子组件（*children*）转换为单个对象传递给组件，这个对象称为“props”。	




+ <span style="font-size:20px">组合组件</span>

    组件可以在其输出中引用其他组件。这就可以让我们用同一组件抽象出任意层次的细节：按钮、表单、对话框、甚至整个屏幕的内容。在 React 应用程序中，这些通常都会以组件的形式表示。

    例如，可以创建一个多次渲染`Welcome`组件的`App`组件：

    ``` jsx
    function Welcome(props) {
        return <h1>Hello, {props.name}</h1>;
    }
    
    function App() {
        return (
        	<div>
            	<Welcome name="Sara" />
                <Welcome name="Cahal" />
                <Welcome name="Edite" />
            </div>
        )
    }
    ```

    通常，每个新的 React 应用程序的顶层组件都是`App`组件。但是，如果将 React 集成到现有的应用程序中，可能需要使用像`Button`这样的小组件，并自下而上地将这类组件逐步应用到视图层的每一处。	



## 组件实例的三大核心属性

+ <span style="font-size:20px">构造器</span>

    在 React 中 construcotr 通常仅用于以下两种情况：

    1. 通过给 `this.state`赋值对象来初始化内部 state

        ```js
        constructor() {
          super(props);
          this.state = {cnt: 1}
        }
        ```

        也可以简写，不在构造器里：

        ```js
        state = {cnt: 1};
        ```

    2. 为事件处理函数绑定实例

        ```js
        this.add = this.add.bind(this);
        // `bind`返回一个`this`被改变的新函数
        ```

        用箭头函数可以省去上面步骤：

        ```js
        add = () => {
          this.setState({cnt: ++this.state.cnt})
        }
        ```

        + `add()`放在哪里：原型对象上，供实例使用
        + `add`是作为 onClick 的回调，其不是通过实例调用，而是直接调用
        + 为什么要绑定 this：类中的方法默认开启了严格模式，所以`add`中的 this 为 undefined
                

### State

组件就是“状态机”。通过更新组件的状态来更新对应的页面显示。（*每 `setState` 一次，`render`就会执行一次*)

state 借助构造器`constructor()`初始化状态`state`；在`render()`中读出`state`属性；用`setState`改变状态



### Props

+ **props 与 state 区别：**

    props 是组件对外的接口，而 state 是组件对内的接口——props 用于组件间数据传递，而 state 用于组件内部的数据传递

+ <span style="font-size:20px">props 基本应用</span>

    ``` jsx
    class Person extends React.Component{
      render() {
        const {name, age} = this.props;
        return (
        	<u1>
          	<li>姓名：{name}</li>
            <li>性别：{sex}</li>
          </u1>
        )
      }
    }
    
    ReactDom.render(<Person name="jerry" age="19" />, test)	// Person 后是传组件标签的值
    ReactDom.render(<Person name="mac" age="16" />, test)
    ```

+ <span style="font-size:20px">Props 的只读性</span>

+ <span style="font-size:20px">[使用 PropTypes 进行类型检查 - React](https://zh-hans.reactjs.org/docs/typechecking-with-proptypes.html)</span>

    需要引入 [props-types](https://www.npmjs.com/package/prop-types) 依赖包，用于对组件标签属性进行限制。在全局增加了一个`ProtoTypes`对象


+ <span style="font-size:20px">函数式组件使用 props</span>

    函数式组件只能通过入参来使用 props，而不能使用 state 和 refs。除非用较新版本的 hooks

    ``` jsx
    /* 创建组件 */
    function Person (porps) {
      const {name, age, sex} = props;
      return (
      <ul>
          <li>姓名：{name}</li>
          <li>性别：{sex}</li>
          <li>年龄：{age}</li>
      </ul>
      )
    }
    ```
    
    

### refs

组件内的标签可以定义 ref 属性来标识自己，与使用 id 的区别是不需使用`document.` 

+ <span style="font-size:20px">字符串形式的 ref</span>

    过时 API，不建议使用，因为 string 类型的 refs 虽然写法简单但效率不高。已过时且可能在未来的版本中移除

    以下实例实现页面显示分别两个功能不同的输入框。
    
    ``` jsx
    class Demo extends React.Component{
      /* 展示左侧输入框的数据 */
      showData = () => {
        const input1 = this.refs.input1;
        alert(input1.value);	// 弹出输入框1内的文本内容
      }
      /* 展示右侧输入框的数据 */
      showData2 = () => {
        const {input2} = this.refs;	
        alert(input2.value);
      }
      
      render() {
        return(
        <div>
            <input ref="input1" type="text" placeholder="点击按钮弹出提示数据"/>&nbsp;
            <button onClick={this.showData}>提示左侧的数据</button>&nbsp;
            <input ref="input2" onBlur={this.showData2} type="text" placeholder="失去焦点弹出数据"/>&nbsp;  
        </div>
        )
      }
    }
    ReactDOM.render(<Demo/>, document.getElementById('root'))	
    ```
    
    注意上文取 refs 对象的两种写法：
    
    1. 第 4 行常规写法。
    2. 第 9 行利用对象的结构赋值，将 refs 内的同名属性`input2`取出赋值到`input2`。



+ <span style="font-size:20px">回调形式的 ref</span>

    还是上例。

    ``` jsx
    class Demo extends React.Component {
      // ...
      
      render() {
        return(
        	<div>
          	<input ref={currentNode => this.input1 = currentNode} type="text" placeholder="点击按钮数据"/>&nbsp;
            <button onClick={this.showData}>点击弹出左侧数据</button>
            <input ref={c => this.input2 = c} type="text" placeholder="失去焦点弹出数据"/>&nbsp;
          </div>
        )
      }
    }
    ```

    这里的 ref 是回调函数，返回当前节点。



+ <span style="font-size:20px">createRef</span>

    通过调用`React.createRef`返回一个容器，该容器存储被 ref 所标识的节点。
    
    但是该容器一个只能储存一个节点，即每要得到一个节点就得调用一次
    
    是最麻烦但 React 最推荐的一种方式。
    
    ``` jsx
    class Demo extends React.Component {
      myRef1 = React.createRef();
    	myRef2 = React.createRef();
    
      showData1 = () => {
        alert(this.myRef.current.value);
      }
      showData2 = () => {
        alert(this.myRef.current.value);
      }
      
      render() {
        return(
          <div>
            <input ref={this.myRef1} type="text"/>&nbsp;
            <button onClick={this.showData1}>点击弹出数据</button>&nbsp;
            <input ref={this.myRef2} onBlur={this.showData2} type="text"/>;
          </div>
        )
      }
    }
    ```



官方提醒，勿过度使用 ref。当发生事件的元素就是将要操控的元素时，可以避免 ref。

例如上面几个例子中的`input`框 2：

``` jsx
class Demo extends React.Component {
  // ...
  
  showData2 = (event) => alert(event.target.value);

  render() {
    return(
    	// ...
      <input onBlur={this.showData2} type="text" placeholder="失去焦点弹出数据"/>
    )
  }
}
```

调用`showData2`时还传了一个事件参数`event`。这里的`event.target`得到事件源，即该`input`节点。



#### React 中的事件处理

<hr>

+ <span style="font-size:20px">通过 onXxx 属性指定事件处理函数</span>
    + React 使用的是自定义事件（*二次封装所以区分大小写如 `onClick`*），而不是原生 DOM 事件（*如 `onclick`*）。
    + React 中的事件是通过事件委托方式处理的，即委托给组件最外层的元素，更高效
+ <span style='font-size:20px'>通过 event.target 得到发生事件的 DOM 元素对象</span>



## 收集表单数据

### 非受控组件

实现效果：表单点击提交后弹出用户名和密码，但不触发表单提交。

``` jsx
class Login extends React.Component{
  handleSubmit = () => {
    event.preventDefault();	// 防止默认事件触发，阻止表单提交
    const {username, password} = this;
    alert(`你输入的用户名是：${username.value}，你输入的密码是：${password.value}`)；
  }
  
  render() {
    return(
      <form onSubmit={this.handleSubmit}>	
        用户名：<input ref={c => this.username = c} type="text" name="username"/>
        密码：<input ref={c => this.password = c} type="password" name="password"/>
        <button>登录</button>
      </form>
    )
  }
}
```

> \<input> 元素的 name 属性
>
> input表单控件的名字。以名字/值对的形式随表单一起提交
>
> （*如上例，提交表单网址刷新变成 test.html?**username=12&password=12***）

总结非受控组件：先用先取。

### 受控组件

同样的例子：

``` jsx
class Login extends React.Component {
    /* 初始化状态 */
    state = {
        username:'',
        password:''
    }
		/* 保存用户名、密码到状态中 */
    saveUsername = (event) => {
        this.setState({username:event.target.value});
    }
    savePassword = (event) => {
        this.setState({password:event.target.value});
    }
		/* 表单提交的回调 */
    handleSubmit = (event) => {
        event.preventDefault();
        const {username, password} = this.state;	// 从状态中取
        alert(`你输入的用户名是：${username}，你输入的密码是：${password}`);
    }

    render() {
        return(
            <form onSubmit = {this.handleSubmit}>
                用户名：<input onChange={this.saveUsername} type="text" name="username"/>
                密码：<input onChange={this.savePassword} type="password" name="password"/>
                <button>登录</button>
            </form>
        )
    }
}

ReactDOM.render(<Login/>, document.getElementById('root'))
```

总结受控组件：页面中输入类 DOM（*如 input 框*），随着输入就能把东西维护到状态（*state*）里去，等需要用的时候直接从状态里面去取，这就属于受控组件。

（*用 `onChange` 实现相当于 Vue 里的双向数据绑定*）

非受控组件与受控组件中建议使用受控组件，因为不需使用 ref，而 ref 使用过多易造成效率问题能省则省。



## 组件的生命周期

+ <span style="font-size:20px">生命周期图</span>

    <hr>

    旧 React：

    组件渲染有三个路线。

    ![image-20210520131203496](https://i.loli.net/2021/05/20/8GDFzEgIUrSfy9q.png)

    新 React：

    将废弃`componentWillMount`、`componentWillRecieveProps`、`componentWillUpdate`三个钩子。

    新增了`getDerivedStateFromProps`、`getSnapshotBeforeUpdate`。

    <img src="C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20210524124359645.png" alt="image-20210524124359645" style="zoom:55%;" />

+ <span style="font-size:20px">关于生命周期的API</span>

    <hr>

组件的生命周期就是在特定的时间点执行：生命周期回调函数 / 生命周期钩子函数
旧：

1. 初始化阶段：由`ReactDOM.render()`触发--初次渲染

    + constructor()		

    + componentWillMount()

    + render()

    + **componentDidMount()**：组件挂载完毕，常用，一般用来初始化（*：如 开启定时器、发送网络请求、订阅消息*）

2. 更新阶段：由组件内部 `this.setState()`或父组件`render`触发

    + componentWillReceiveProps：组件将要接收**新的** props，第一次传的 props 不算。
    + shouldComponentUpdata()：控制组件更新的”阀门“，如果这个钩子返回`true`（*默认返回 `true`*），“阀门”开启，可以继续执行
    + componentWillUpdate()
    + render
    + componentDidUpdate：组件更新完毕

3. 卸载组件：由`ReactDOM.unmountComponentAtNode()`触发

    + **componentwillUnmount()**：常用，一般用来收尾（*：如 关闭定时器、取消订阅消息*） 

    

新：

+ static getDerivedStateFromProps：不常用，state 任何时候都取决于 props 时可以使用。
+ getSnapshotBeforeUpdate：snapshot（*快照*）。在最近一次渲染前输出之前调用，使得组件能在发生更改之前从 DOM 中捕获一些信息，返回的任何值都将作为参数传递给`componentDidUpdate()`。



总结：

+ 重要的勾子：
    + render
    + componentDidMount
    + componentWillUnmount
+ 即将废弃的勾子：
    + componentWillMount
    + componentWillReceiveProps
    + componentWillUpdate



# Context

Context 提供了一个无需为每层组件手动添加 props，就能在组件树间进行数据传递的方法

一般方法：![image-20210528204522678](https://i.loli.net/2021/05/28/z85k9BhIj2iArnu.png)

Context：![image-20210528204602287](https://i.loli.net/2021/05/28/NZePigDjxUQkra8.png)



# React 脚手架

React 脚手架用于帮助程序员快速创建一个基于 xxx 库的模板项目

+ 包含了所有需要的配置（语法检查、jsx 编译、devServer...）
+ 下载好了所有相关的依赖
+ 可直接运行一个简单效果

React 提供了一个用于创建 react 项目的脚手架库：create-react-app

项目的整体技术架构为：react + webpack + es6 +eslint ...

使用脚手架开发项目的特点：模块化、组件化、工程化



## React 脚手架创建

1. 全局安装：windows 命令行内输入`npm i create-react-app -g`。
2. 切换到想创项目的目录后使用命令`create-react-app react_staging`（*其中 `react_staging` 是想取的名字（不能含中文）*）
3. 最好[安装 Yarn](https://classic.yarnpkg.com/en/docs/install#windows-stable)与 react 配合使用

4. 在创建的文件夹路径下`yarn start`，或在 VS Code 终端（ ctrl + \` ）输入 yarn start，浏览器弹出

    <img src="C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20210528190553720.png" alt="image-20210528190553720" style="zoom:33%;" />

+ <span style='font-size:20px'>TypeScript 配置</span>

    大体步骤同上，命令行内输入`npx create-react-app my-app-ts --template typescript`



+ <span style="font-size:20px">react 脚手架项目结构</span>
+ public --- 静态资源文件夹：
  
    + favicon.icon ------ 网站页签图标
        + **index.html-------主页面**
    
    + logo192.png ------- logo图
    
    + logo512.png ------- logo图
    
    + manifest.json ----- 应用加壳的配置文件
    
    + robots.txt -------- 爬虫协议文件
    
+ src --- 源码文件夹：
  
    + App.css
        + **App.js --------- App组件**
        + App.test.js
        + index.css
        + **index.js -------- 入口文件**
        + logo.svg ------- logo图
        + reportWebVitals.js --- 页面性能分析文件(需要web-vitals库的支持)
        + setupTests.js ---- 组件单元测试的文件(需要jest-dom库的支持)



## 创建简单组件

+ public 文件夹：

    + `index.html`主页面：

        ```html
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <title>React 脚手架</title>
            
        </head>
        <body>
            <div id="root"></div>
        </body>
        </html>
        ```

+ src 文件夹：

    + `index.js`入口文件：

        ```jsx
        // 引入 react 核心库
        import React from 'react';
        // 引入 ReactDom 
        import ReactDOM from 'react-dom';
        // 引入 App 组件
        import App from './App'
        
        // 渲染 App 到页面
        ReactDOM.render(<App/>, document.getElementById('root'));
        ```

    +  `App.js`App组件：

        ```jsx
        // 创建外壳组件（囊括其他组件
        import React, {Component} from 'react'
        import Hello from './components/Hello.jsx'
        
        // 创建并暴露 App 组件
        export default class App extends Component {
            render() {
                return (
                    <div>
                        <Hello/>
                    </div>
                )
            }
        }
        ```

    + components 文件夹（*有多个组件时习惯新建该文件夹存放*）

        + `Hello.jsx`：

            ```jsx
            import React, {Component} from 'react'
            import './Hello.css'
            
            export default class Hello extends Component {
                render() {
                    return <h2>Hello, react</h2>
                }
            }
            ```



## 样式的模块化（css in js    /   JSS）

+ <span style="font-size:20px">JSS 是什么</span>

    一句话概括 CSS in JS，就是"行内样式"（*inline style*）和"行内脚本"（*inline script*）

    JSS 就是将应用的 CSS 样式写在 JS 文件里面，而不是独立未一些`.css`，``.scss`或者`less`之类的文件，这样就可以在 CSS 中使用一些属于 JS 的诸如模块声明、变量定义、函数调用和条件判断等语言特性来提供灵活的可扩展的样式定义



上例的`Hello.jsx`文件中，若有多个组件，每个组件的样式都通过这种方式引入的话会造成样式冲突：

```jsx
import './Hello.css'

import './Welcome.css'
```

解决方法是样式模块化：利用对象（*styles*）的形式访问。

```jsx
import styles from './hello.module.css'

export default class Hello extends Component {
  render() {
    return <h2 className={styles.title}>Hello, React</h2>	
  }
}
```

同时，需添加一个 ts 样式声明文件



+ <span style='font-size:20px'>给 css 添加样式声明</span>

    1. `npm install typescript-plugin-css-modules --save-dev`，将该插件安在`package.json`的`dev`依赖下，即只参与代码开发不参与最终上线打包的项目。安装完成在`package.json`中会出现：

        ```json
        "devDependencies": {
            "typescript-plugin-css-modules": "^3.3.0"
          }
        ```

    2. 在`tsconfig.json`中加入`"plugins": [{"name": "typescript-plugin-css-modules"}]`。

    3. 在根目录下新建`.vscode`文件夹，在该文件夹内新建`settings.json`文件，保存如下代码：

        ```json
        {
        	"typescript.tsdk": "node_modules/typescript/lib",
        	"typescript.enablePromptUseWorkspaceTsdk": true
        }
        ```

    4. 配置完成后在引用样式对象时会自动弹出提示，如图：<img src="C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20210605141748402.png" alt="image-20210605141748402" style="zoom: 67%;" />

    

## 加载媒体与字体文件

+ <span style='font-size:20px'>加载图片</span>

    <hr>

    习惯上新建一个`assets`文件夹存放媒体资源，如图：

    ![image-20210605144832793](https://i.loli.net/2021/06/05/O1kUzI2gt9loN5h.png)

    引入媒体资源时，依旧使用`import`语句，例如：

    ![image-20210605144927794](https://i.loli.net/2021/06/05/rgZLVAxdtqm1vbw.png)

    因为 ts 已经对`.svg`等文件声明，因此无需像引入`.css`文件一样在`custom.d.ts`文件中重复声明



+ <span style='font-size:20px'>加载字体</span>

    <hr>

    将`.ttf`字体文件放入`fonts`文件夹，因为字体是全局样式，因此在`index.css`和`index.tsx`中设置及引用：

    ```css
    // index.css
    @font-face {
        font-family: 'Brush';
        src: local('Brush'), url(./assets/fonts/Brush.ttf);
    }
    ```

    ```css
    // App.modules.css
    h1 {
          font-family: 'Brush';
          font-size: 72px;
    }
    ```

    





# React Hooks

+ <span style="font-size:20px">Why hooks</span>

    <hr>

    + 现在，**React API 有两套**：类（*class*）API 和基于函数的钩子（*hooks*）API。

        相比类，钩子更简洁，代码量少。而且钩子是函数，更符合 React 函数式的本质。

        但是钩子灵活性太大，不理解容易写出混乱且无法维护的代码。而类有很多强制的语法约束不容易搞乱。

        

    + **类和函数的差异**：

        类是数据和逻辑的封装，即组件的状态和操作方法是封装在一起的

        函数一般来说只应做一件事，就是返回一个值。数据的状态应该与操作方法分离。所以React 的函数组件只应做一件事，返回组件的 HTML 代码

        

    + **副效应**

        函数式编程把那些根数据计算无关的操作都称为副效应（*side effect*）。如果函数内部直接包含产生副效应的操作，就不再是纯函数了，我们称为不纯的函数

        纯函数内部只能通过间接的手段（*即通过其他函数调用*）才能包含副效应

        

    + **钩子的作用**

        钩子就是 React 函数组件的副效应解决方案，用来为函数组件引入副效应。函数组件的主体只应用来返回组件的 HTML 代码，所有其他的操作（*副效应*）都应通过钩子引入。

        由于副效应非常多，所以钩子有许多种。React 为常见的操作（*副效应*）都提供了专用的钩子。

        + **`useState()`**：保存状态
        + **`useContext()`***：保存上下文
        + **`useRef()`**：保存引用
        + ...

        上面这些钩子都是引入某种特定的副效应，而 **`useEffect()`** 是通用的副效应钩子，找不到对应的钩子时就可以用它。



## useEffect()

### useEffect() 基本用法

<hr>

`useEffect()`本身是一个函数，由 React 框架提供，在函数内部调用即可。

举例来说，如果想要组件加载之后，网页标题`document.title`会随之改变。那么改变网页标题这个操作就是组件的副效应，必须通过`useEffect()`来实现。

``` react
function Welcome(props) {
  useEffect(() => {
    document.title == '加载完成';
  });
  
  return <h1>Hello, {props.name}</h1>;
}
```

上例中，`useEffect()`的参数是一个函数，它就是所要完成的副效应。组件加载之后，React 就会执行这个函数。



`useEffect()`的**作用就是指定一个副效应函数**，**组件每渲染一次，该副效应函数就自动执行一次**。组件首次在网页 DOM 加载后，副效应函数也会执行。



### useEffect() 的第二个参数

<hr>

有时，不需要`useEffect()`每次渲染都执行，这时可以使用它的第二个参数，**使用一个数组指定副效应函数的依赖项，只有依赖项发生变化才会重新渲染**。



```react
function Welcome(props) {
  useEffect(() => {
    document.title = 'Hello, ${props.name}';
  }, [props.name]);
  
  return <h1>Hello, {props.name}</h1>;
}
```

上面例子中，`useEffect()`的第二个参数是一个数组，指定了第一个参数（副效应函数）的依赖项（`props.name`）。只有该变量发生变化时，副效应函数才会执行。



如果**第二个参数是空数组**，就表明无任何依赖项。副效应函数只在组件加载进入 DOM 后执行一次，后面组件重新渲染就不会再次执行



### useEffect() 的用途

<hr>

常见用途有如下几种：

+ 获取数据（*data fetching*）
+ 事件监听或订阅（*setting up a subscription*）
+ 改变 DOM（*changing the DOM*）
+ 输出日志（*logging*）



### useEffect() 的返回值

<hr>

副效应随组件加载而发生，那么组件卸载时，可能需要清理这些副效应。

useEffect() 允许返回一个函数，在组件卸载时执行该函数，清理副效应。若不需清理副效应，useEffect() 不用返回任何值

```react
useEffect(() => {
  const subscription = props.source.subscribe();
  return () => {
    subscription.unsubscribe();
  };
}, [props.source]);
```

上例，`useEffect()`在组件加载时订阅了一个事件，并且返回一清理函数，在组件卸载时取消订阅



实际使用中，由于副效应函数默认是每次渲染都会执行，所以清理函数不仅会在组件卸载时执行一次，每次副效应函数重新执行前也会执行一次，用来清理上一次渲染的副效应



### useEffect() 的注意点

<hr>

若有多个副效应，应该调用多个`useEffect()`，而不应该合并写一起



## useState() 状态钩子

useState() 用于为函数组件引入状态（*state*）。纯函数不能有状态，所以把状态放进钩子里面

useState() 函数接受状态的初始值，作为**参数**。该函数**返回**一个数组，数组的第一个成员是一个变量，指向状态的当前值；第二个成员是函数，用来更新状态，约定是 set 前缀加上状态的变量名

```react
function Example() {
  // 声明一个叫“count”的 state 变量
  const [count, setCount] = useState(0);
  
  return (
  	<div>
    	<p>You clicked {count} times</p>
      <button onClick={() => setCount(count + 1)}>	// ?
      	Click me
      </button>
    </div>
  )
}
```



## useContext() 共享状态钩子

如果需要在组件间共享状态，可以使用`useContext()`



# React 部分实现

## React 的构建

通过 [Babel 转换](https://www.babeljs.cn/repl)可以知道，ReactDOM.render 是由这个方法转义 JSX 代码的：(`ReactDOM.render(<div id="title">day</div>, document.getElementById("root"))`)

![image-20211220185441552](https://gitee.com/ethereal-bang/images/raw/master/20211220185448.png)

+ <Span style="font-size:20px">React.createElement(type,  props, children)：</span>

    实质是返回一个 React 元素

    打印`createElement`方法返回的元素：

    ![image-20211220193222873](https://gitee.com/ethereal-bang/images/raw/master/20211220193222.png)

+ <span style="font-size:20px">组件：</span>

    由 Babel 可以知道，组件渲染本质也是将组件转化为一个 React 元素：

    ```jsx
    // JSX
    function App(props) {
      return <h1>Hello, {props.name}</h1>
    }
    // JS
    function App(props) {
      return /*#__PURE__*/React.createElement("h1", null, "Hello, ", props.name);
    }
    ```

    

+ <Span style="font-size:20px">ReactDOM.render():</span>

    将`React.createElement()`返回的元素渲染到页面

    ```jsx
    <script type="text/babel">
        ReactDOM.render(<h1>try</h1>, document.getElementById("root"));
    </script>
    ```

    只有含有`text/babel`时才能成功将 JSX 代码识别为 React 元素

      

# 参考链接

[React 官方中文文档](https://zh-hans.reactjs.org/docs/hello-world.html)

 

