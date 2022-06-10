



# React 配置

+ <span style="font-size:20px">学习用环境配置</span>

    7. 在`<head></head>`标签内插入以下 react 库：

        ``` html
        <script src="https://unpkg.com/react@17/umd/react.development.js" crossorigin></script>
        <script src="https://unpkg.com/react-dom@17/umd/react-dom.development.js" crossorigin></script>
        <script src="https://unpkg.com/babel-standalone@6/babel.min.js"></script>
        ```
        
    8. 使用 jsx 语法需把`script`标签的`type`属性设置为`text/babel`

## React 脚手架

React 提供了一个用于创建 react 项目的脚手架库：create-react-app

项目的整体技术架构为：react + webpack + es6 +eslint ...



### React 脚手架创建

1. `npm i create-react-app`。（官方建议不全局安装以此获取最新版本）

2. `create-react-app my-appn start`


+ <span style='font-size:20px'>TypeScript 配置</span>

    第二步改为 `npx create-react-app my-app --template typescript`



# 元素渲染
## JSX

+ <span style="font-size:20px"> {} 嵌入表达式</span>
+ <span style="font-size:20px">"" 写入字面量</span>
+ <span style="font-size:20px">内联样式 {{}} </span>


> 注意：**小驼峰命名**
>
> 因为 JSX 语法上更接近 JS 而不是 HTML，所以 React DOM 使用“小驼峰命名”来定义属性的名称，而不使用 HTML 属性名称的命名约定
>
> 例如，JSX 里的`class`变成**`className`**



## 条件渲染

```tsx
// 三目运算符：
return (
	{loading ? <Spin /> : <Hello />}
)
// 与运算符
return (
	{len > 0 && <h2>Hello</h2>}
)
```



## 组件渲染

+ Clinet 和 Server side 渲染==？==

+ React 18 弃用原来的根 API，改为 `createRoot`:

    ```js
    const ReactDOMClient = require("react-dom/client");
    
    const root = ReactDOMClient.createRoot(document.getElementById('root')!);
    root.render(<App />);
    ```



+ <span style="font-size:20px">UI 自动更新:</span>

    State 改变 React 会自动更新相应的 UI，而 JSX 的 State 更新不会引起 UI 变化：

    ```jsx
    {signFlag ? () : ()}
    ```

    > 如上，signFlag 的更新并不会导致重新渲染



## 重复渲染

> 通过 JS 快速渲染多个具相似结构元素。

+ <span style="font-size:18px">[]:</span>

    ```tsx
    <ul>
    	{list.map((item, index) => (
      	<li key={index}>item</li>
      ))}
    </ul>
    ```

+ <span style="font-size:18px">Map:</span>

    ```tsx
    {booksMap && Array.from(booksMap).map((item, index) => (
      <img
        key={item[0]}
        src={item[1].img}
        />
    ```

    转为数组后操作

    > 1. Map 转为数组后 0  对应 key，1 对应 value
    > 2. `booksMap && ` 的作用是确保其不为空（Array.from 不接受可能为 undefined 的参数）



# 组件

组件允许我们将 UI 拆分为独立可复用的代码片段，并对每个片段进行独立构思。组件，从概念上类似于 JS 函数。接受任意的入参（*props*），并返回描述页面内容的 React 元素。

+ <span style="font-size:20px">函数组件与 class 组件</span>

    1. 定义组件最简单的方式就是**编写 JS 函数**：

        ```js
        function Welcome(props) {
            return <h1>Hello, {props.name}</h1>;
        }
        ```

    2. **class：**

        ```js
        class Welcome extends React.Component {	// extends 继承
            render() {
            	return <h1>Hello, {this.props.name}</h1>;
            }
        }
        ReactDOM.render(<Welcome name={"app"} />)
        ```

        > **执行过程：**
        >
        > 1. React 解析**组件标签**，找到了`MyComponent`组件
        > 2. 发现组件是使用类定义的，随后`new`出该类的实例，并通过该实例调用原型上的`render()`方法
        > 3. 将`render`返回的虚拟 DOM 转为真实 DOM，随后呈现在页面中

        > 几个**必要条件：**
        >
        > 1. 必须继承`React.Component`父类
        > 2. 必须有``render()`
        > 3. `render()`要有返回值	返回想呈现的内容



+ <span style="font-size:20px">渲染组件</span>

    React 元素也可以是用户自定义的组件：	

    ``` jsx
    const element = <Welcome name="Sara" />
    ```

    当 React 元素是用户自定义组件时，它会将 JSX 所接收的属性（*attributes*）以及子组件（*children*）转换为单个对象传递给组件，这个对象称为“props”。	




+ <span style="font-size:20px">组合组件</span>

    组件可以在其输出中引用其他组件

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

    > 通常，每个新的 React 应用程序的顶层组件都是`App`组件



## 三大核心属性

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

        使用事件处理函数：
    
        ```jsx
        render() {
          return (
          	<button onClick={this.add}>Add: {this.state.cnt}</button>
          )
        }
        ```
    
        
    
    
    + `add()`放在哪里：原型对象上，供实例使用
    + `add`是作为 onClick 的回调，其不是通过实例调用，而是直接调用
    + 为什么要绑定 this：类中的方法默认开启了严格模式，所以`add`中的 this 为 undefined
            

### State

+ **this.state 的更新时机：**

    进入了 <span style="color:red">react 的调度流程</span>，那就是异步的。没有进入 react 的调度流程，那就是同步的
    
+ <span style="font-size:22px">Class 组件：</span>

+ <span style="font-size:22px">函数式组件：</span>——useState Hook

    ```tsx
    function Cnt() {
      // 声明初始化为0的cnt状态，和状态set函数
      const [cnt, setCnt] = useState(0);
      
        return (
            <div>
                <p>Clicked {cnt} times.</p>
                <button onClick={() => { setCnt(cnt + 1) }}>
                    Click me!
                </button>
            </div>
        )
    }
    ```

    不像 class 中的 `this.setState`，更新 state 变量总是<span style="color:red">替换而不是合并它</span>：

    ```tsx
    const [musicList, setMusicList] = useState([]);
    
    useEffect(() => {
      axios.get("http://cloud-music.pl-fe.cn/search/hot")
        .then(res => {
          const { data } = res;
    #     setMusicList(data.result.hots);
      })
    });
    ```

    > 这个储存数组的例子可以看出，不能用`musicList.push(data.result.hots)`，因为是替换 state 变量。
    
+ <span style="font-size:22px">State 更新：</span>

  + **引用型 State** 更新注意点：

    setState 函数以返回的变量地址判断是否更新，即一下情况 state 不会成功更新：

    ```tsx
    setState(stateObj => {
      let tmp = stateObj;
      tmp.cnt++;
      return tmp;
    })
    ```

    > 因为 `tmp` 和 `stateObj` 指向的同一个地址（`tmp` === `stateObj`。

  + **数组式** State 更新：

      ```tsx
      setHoverState((hoverState) => [
        ...hoverState.slice(0, index),
        flag,
        ...hoverState.slice(index + 1)
      ])
      // 更新index位为flag
      ```

  + **Map** 结构：（[ [key-value], [key-value] ]）

      ```tsx
      setMap(map => {
        map.set(id, 1);
        return new Map(Array.from(map));
      })
      ```



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

    函数式组件通过传入参数来使用 props

    ``` jsx
    function Person (porps) {
      const {name, age, sex} = props;
    ```
    
+ <span style="font-size:20px">children prop：</span>

    ```tsx
    interface Props {
        children: ReactNode,
    }
    
    const MainLayout = (prop: Props) => {
    	return <div>{props.children}</div>
    }
    
    const Home = () => {
      return <MainLayout>
      	<h2>这就是传给Layout的children</h2>
      </MainLayout>
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
    2. 第 9 行利用对象的解构赋值，将 refs 内的同名属性`input2`取出赋值到`input2`。



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



## React 中的事件处理


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



## Context

Context 提供了一个无需为每层组件手动添加 props，就能在组件树间进行数据传递的方法

一般方法：![image-20210528204522678](https://i.loli.net/2021/05/28/z85k9BhIj2iArnu.png)

Context：![image-20210528204602287](https://i.loli.net/2021/05/28/NZePigDjxUQkra8.png)



## 创建简单组件

+ public 文件夹：

    + `index.html`主页面：

        ```html
        <body>
            <div id="root"></div>
        </body>
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
                    <Hello/>
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



# 样式

+ <span style="font-size:20px">CSS in JS——样式的模块化：</span>

    这样就可以在 CSS 中使用一些属于 JS 的诸如模块声明、变量定义、函数调用和条件判断等语言特性来提供灵活的可扩展的样式定义，且避免全局样式冲突

    ```js
    import styles from './hello.module.css'
    
    export default class Hello extends Component {
      render() {
        return <h2 className={styles.title}>
          <i className={
            check 
            ? styles["checked"]
      			: styles["check"]} />
        </h2>	
      }
    }
    ```

    > **添加多个 class：**
    >
    > ```jsx
    > <div className={styles["menu"] + " " + styles["genremenu"]} />	
    > /*<div class=".menu .genremenu" />*/
    > ```

+ <span style="font-size:20px">内联样式 {{}} :</span>

    配合 JS：

    ```jsx
    {bannerData.map(item => {
      return <li style={(item.key===cur) ? {backgroundColor:"#ff2832"} : {}} key={item.key}>{item.key}</li>
    })}
    ```

    > **Note:**
    >
    > + `style={}` 内的中括号内会认为是 CSS 样式，因此这里条件语句没有用 `if() {}`。
    > + CSS 代码 key: value 中 value 要加 `" "`



## 加载媒体与字体文件

习惯上新建`src/assets`文件夹存放媒体资源，如图：

![image-20210605144832793](https://i.loli.net/2021/06/05/O1kUzI2gt9loN5h.png)

+ <span style='font-size:20px'>加载图片</span>
   引入媒体资源时，依旧使用`import`语句，例如：
  ![image-20210605144927794](https://i.loli.net/2021/06/05/rgZLVAxdtqm1vbw.png)

+ <span style='font-size:20px'>加载字体</span>

  
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

    现在，**React API 有两套**：类（*class*）API 和基于函数的钩子（*hooks*）API。

    > 相比类，钩子更简洁，代码量少。而且钩子是函数，更符合 React 函数式的本质。
    >
    

    
+ **类和函数的差异**：
  
    类是数据和逻辑的封装，即组件的状态和操作方法是封装在一起的
    
    函数一般来说只应做一件事，就是返回一个值。数据的状态应该与操作方法分离。所以React 的函数组件只应做一件事，返回组件的 HTML 代码
    
    
    
+ **副效应**
  
    纯函数内部只能通过间接的手段（*即通过其他函数调用*）才能包含副效应



## useEffect())

给函数组件提供了操作副作用(*——数据获取、订阅、修改 DOM* )的能力与 class 中的生命周期函数极为类似。

+ <span style="font-size:22px">基本用法：</span>

  举例来说，如果想要组件加载之后，网页标题`document.title`会随之改变。那么改变网页标题这个操作就是组件的副效应，必须通过`useEffect()`来实现。

  ```js
  function Welcome(props) {
    useEffect(() => {
      document.title == '加载完成';
    });
    
    return <h1>Hello, {props.name}</h1>;
  }
  ```

  > 上例中，`useEffect()`的参数是一个函数，它就是所要完成的副效应。组件加载之后，React 就会执行这个函数。

  `useEffect()`的**作用就是指定一个副效应函数**，**组件每渲染一次，该副效应函数就自动执行一次**

+ <span style="font-size:22px">第二个参数</span>

    有时，不需要`useEffect()`每次渲染都执行，这时可以使用它的第二个参数，**使用一个数组指定副效应函数的依赖项，只有依赖项发生变化才会重新渲染**。按照规范，每个 Hook 中的变化量都应写入依赖项里。

    ```js
    function Welcome(props) {
      useEffect(() => {
        document.title = 'Hello, ${props.name}';
      }, [props.name]);
      
      return <h1>Hello, {props.name}</h1>;
    }
    ```

    > 上面例子中，`useEffect()`的第二个参数是一个数组，指定了第一个参数（副效应函数）的依赖项（`props.name`）。只有该变量发生变化时，副效应函数才会执行。

    如果**第二个参数是空数组**，就表明无任何依赖项。副效应函数只在组件加载进入 DOM 后执行一次，后面组件重新渲染就不会再次执行

+ <span style="font-size:22px">用途：</span>

    常见用途有如下几种：

    + 获取数据（*data fetching*）：

        ```tsx
        useEffect(() => {
          const fetchData = async () => {
            axios.get("http://cloud-music.pl-fe.cn/search/hot")
              .then(res => {
              	const { data } = res;
        	      setMusicList(data.result);
            })
          }
          fetchData()
            .catch(console.error);
        }, []);	// 只执行一次
        ```

    + 事件监听或订阅（*setting up a subscription*）

    + 改变 DOM（*changing the DOM*）

    + 输出日志（*logging*）

+ <span style="font-size:22px">返回值：</span>
	副效应随组件加载而发生，那么组件卸载时，可能需要清理这些副效应。
	
	useEffect() 允许返回一个函数，在组件卸载时执行该函数，清理副效应
	
	```js
	useEffect(() => {
	  const subscription = props.source.subscribe();
	  return () => {
	    subscription.unsubscribe();
	  };
	}, [props.source]);
	```
	
	> 上例，`useEffect()`在组件加载时订阅了一个事件，并且返回一清理函数，在组件卸载时取消订阅
	
	实际使用中，由于副效应函数默认是每次渲染都会执行，所以清理函数不仅会在组件卸载时执行一次，每次副效应函数重新执行前也会执行一次，用来**清理上一次渲染的副效应**

+ <span style="font-size:22px">注意点:</span>

    若有多个副效应，应该调用多个`useEffect()`，而不应该合并写一起



## useState()

useState() 用于**为函数组件引入 State**。纯函数不能有状态，所以把状态放进钩子里面

> **用法：**
>
> useState() 函数接受状态的初始值，作为参数。该函数返回一个数组，数组的第一个成员是一个变量，指向状态的当前值；第二个成员是函数，用来更新状态，约定是 set 前缀加上状态的变量名

```react
function Example() {
  // 声明初始化为0的cnt状态，和状态set函数
  const [cnt, setCnt] = useState(0);
  
    return (
        <div>
            <p>Clicked {cnt} times.</p>
            <button onClick={() => { setCnt(cnt + 1) }}>
                Click me!
            </button>
        </div>
    )
}
```

+ **解决 setState 更新不及时：**

    setState 的**函数式更新模式**允许指定 state 该如何改变，而不用引用当前 state：

    ```jsx
    function Counter() {
      const [count, setCount] = useState(0);
    
      useEffect(() => {
        const id = setInterval(() => {
          setCount(c => c + 1); // ✅ 在这不依赖于外部的 `count` 变量
        }, 1000);
        return () => clearInterval(id);
      }, []); // ✅ effect 不使用组件作用域中的任何变量
      return <h1>{count}</h1>;
    }
    ```

    > setState 的回调的参数永远获得的都是最新值不会受闭包等影响



## useContext()

组件树间共享数据的方法

```jsx
const ThemeContext = React.createContext(theme);

function App() {
  return (
  	<ThemeContext.Provider value={themes.dark}>
      <div>
      	<Button />
      </div>
    </ThemeContext.Provider>
	)}

function Button() {
  const theme = useContext(ThemeContext);
  // ...
}
```

调用了 useContext 的组件总会在 context 值变化时重新渲染。如果重渲染组件的开销较大，可通过使用 memoization 来优化。



## 自定义 Hook

重用状态逻辑



## useRef——保存引用

使用 useRef() 返回 ref 对象，其 .current 属性初始化参数，.current 可重新赋值，但每次返回的都是同一值（类似闭包的效果）

```js
const refContainer = useRef(initVal);
```

每次渲染时返回的都是同一 ref 对象

> **Eg**——保存 state 快照：
>
> ```tsx
> const [cnt, setCnt] = useState(0);
> const cntRef = useRef(cnt);
> 
> const click = () => {
>     setCnt(cnt => cnt + 1)
>     console.log(cntRef)	// 始终为0
> }
> ```

> **Eg**——获取上一 state 状态：
>
> ```tsx
> const [cnt, setCnt] = useState(0);
> const cntRef = useRef(cnt);
> 
> const click = () => {
>     setCnt(cnt => cnt + 1)
>  		cntRef.current = cnt;	// 虽然上一步更改了cnt但此时得到的还是更改前值
>   	console.log(cntRef)	// 0,1,...
> }
> ```



## useCallback

传入 useCallback 返回该回调函数的 memoized 版本——仅在某个依赖项改变时才会更新

**用途：**避免非必要渲染



# Redux

+ **Redux 原理：**

    应用中所有的 <span style="color:red">state</span> 都以一个对象树的形式储存在一个单一的 <span style="color:red">store</span> 中。 惟一改变 state 的办法是触发 <span style="color:red">action</span>，一个描述发生什么的对象。 为了描述 action 如何改变 state 树，你需要编写 <span style="color:red">reducers</span>。

    ![image-20210719161201719](https://camo.githubusercontent.com/c754e2b863804c2bc08ad8b500ce3a2e101c43bf8259d19e86e3ebf5479d9c88/68747470733a2f2f692e6c6f6c692e6e65742f323032312f30372f32302f6f494a457141363352354d465156742e706e67)

+ <span style="font-size:20px">配置：</span>

    ```shell
    $yarn add react-redux
    
    $npm i @types/react-redux
    ```

## Reducer

Reducer——store 处理数据的方法

+ **Reducer 的基本框架：**

    ```js
    (state, action) => {
    	return state	// 应该返回新的状态，在这里暂时用就状态代替
    }
    ```

    > **参数 state 和 action：**
    >
    > state 是 store 中的旧数据，而 action 是指挥 reducer 函数作数据变换的指令

```typescript
// 1.定义state接口
interface CntState {	
    cnt: number;
    clickState: "low" | "high";
}
// 2.初始化默认state
const defaultState: CntState = {	
    cnt: 0,
  	clickState: "low",
}
// 3.导出reducer
const cntReducer = (state = defaultState, action) => {
    return state;
} 
export default cntReducer;
```

>  reducer 还是 action 都是**纯函数**，所以文件输出的最终结果就是一个函数，可以使用匿名函数的形式

## Action

```typescript
export const CHANGE_CNT = "change_cnt";

interface ChangeCntAction {
    type: typeof CHANGE_CNT;
    payload: number;
}

export const changeCntActionCreator = (num: number) : ChangeCntAction => {
    return {
        type: CHANGE_CNT,
        payload: num,
    }
}
```



## Redux + **Redux-Toolkit**

```shell
$npm i @reduxjs/toolkit
```

### Slice

使用 RTK 后自动包含 reducer 和 action 的映射关系，不再需要分别创建对应的 reducer 和 action 了

```tsx
interface CounterState {
    cnt: number;
    clickState: "low" | "high";
}
const initialState: CounterState = {
    cnt: 0,
    clickState: "low",
}

export const counterSlice = createSlice({
    name: "counterSlice",
    initialState,
    reducers: {
        addCnt: (state, action:PayloadAction<number>) => {
            state.cnt += action.payload;
        },
        changeClickState: (state) => {
            state.clickState = (state.clickState === "low") ? "high" : "low";
        },
    }
})
```

> **action 的类型：**
>
> RTK 已经定义好了 action 的类型`payload: any; type: string;`，如果需要自定义 action 类型可以使用`PayloadAction`

### Store

1. <span style="font-size:22px">创建 Store：</span>

    ```ts
    // /redux/store.ts
    import {applyMiddleware, combineReducers} from "@reduxjs/toolkit";
    
    const rootReducer = combineReducers({
        counter: counterSlice.reducer,
    })
    
    const store = createStore(rootReducer);
    
    export default store;
    ```
+ <span style="font-size:22px">Middleware：</span>

    以 log 功能为例——每次 state 改变打印 state：

    ```typescript
    // redux/middlewares/actionLog.ts
    import {Middleware} from "@reduxjs/toolkit";
    
    export const actionLog : Middleware = (store) => {
        return (next) => {
            return (action) => {
                console.log("state 当前：", store.getState());
                next(action);
                console.log("state 更新", store.getState());
            }
        }
    }
    ```

    创建 store 时加入 middleware：

    ```typescript
    import {actionLog} from "./middlewares/actionLog";
    import thunk from "redux-thunk";
    
    const store = createStore(rootReducer, applyMiddleware(thunk, actionLog));
    ```

2. <span style="font-size:22px">组件与 Store 的连接：</span>

    1. **Provider 组件**包裹根组件，并传入 Store：`Provider`组件创建了一个 react-redux 上下文

        ```tsx
        function App() {
            return (
                <Provider store={store}>
                    <Hello />
                    <Counter />
                </Provider>
            );
        }
        ```
        
        > We recommend using the React-Redux hooks API as the default approach in your React components.
        >
        > The existing `connect` API still works and will continue to be supported, but the hooks API is simpler and works better with TypeScript.
        
    
3. <span style="font-size:22px">访问组件:</span>

    **useSelector()**——获得相应字段

    ```tsx
    export default function Counter() {
      const cnt = useSelector(state => state.counter.cnt);
      const clickState = useSelector(state => state.counter.clickState);
    
      return (
        <div>
          <p>Clicked {cnt} times.</p>
          <p>It`s {clickState} state.</p>
        </div>
      )
    }
    ```

    > **让 state 拥有智能联想：**
    >
    > ```typescript
    > // redux/store.ts
    > export type RootState = ReturnType<typeof store.getState>;
    > ```
    >
    > ```ts
    > // redux/hooks.ts
    > import {
    >     TypedUseSelectorHook,
    >     useSelector as useReduxSelector,
    > } from "react-redux";
    > import {RootState} from "./store";
    > 
    > export const useSelector: TypedUseSelectorHook<RootState> = useReduxSelector;
    > ```
    >
    > 然后引用 useSelector 时从上述文件引用。

4. <span style="font-size:22px">Dispatch action：</span>

    ```tsx
    <button onClick={() => {
        dispatch(counterSlice.actions.addCnt(2))
    }}>
      Click me!
    </button>
    ```




# Router

> **React 路由框架：**
>
> - 综合性路由框架：**react-router**（*最主流、完整的 React 路由解决方案*）
> - 浏览器路由框架：react-keeper
> - 手机 app 框架（*react-native*）：react-navigation

+ **React-router-dom：**

    用于浏览器，处理 Web App 的路由。会自动安装 React-router 核心框架

    - 使用`<Link />`组件可以渲染出`<a />`标签
    - `BrowserRouter />`组件利用 H5 API 实现路由切换
    - `<HashRouter />`组件利用原生 JS 中`window.location.hash`实现路由切换

    > 其余扩展性框架：
    >
    > - React-router-redux 提供路由中间件，处理 redux 的集成
    > - React-router-config 用来配置静态路由

    ```shell
    # v5版本
    $npm i react-router-dom
    ```

## 路由初始化

```tsx
function App() {
    return (
        <Provider store={store}>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<HomePage />} />
                    <Route path="/show" element={<ShowPage />} />
                    <Route path="*" element={<h1>404</h1>} />
                </Routes>
            </BrowserRouter>
        </Provider>
    );
}
```

> **Notes:**
>
> + Route 组件内的写法有变动`element={<HomePage />}`，以前是`component={HomePage}`
>
> + BrowserRouter 内还要包裹一层 Routes 组件
>
> + 没有 exact 属性，现在默认精准匹配路径
>
> + Route 顺序在一下情况起作用：
>
>     ```jsx
>     <Route path={"/"} element={<h1>默认</h1>} />
>     <Route path={"*"} element={<h1>404</h1>} />
>     ```



## 路由跳转

+ 组件：

    ```tsx
    import {Link} from "react-router-dom";
    
    const ShowPage = () => {
        return (
            <>
                <ShowCnt />
                <Link to={"/"}>回到首页</Link>
            </>
        )
    }
    ```

+ JS：

    ```js
    import { useNavigate } from "react-router-dom";
    
    const navigate = useNavigate();
    navigate("/path", {	// 路径不是相对于当前
      replace: false,	// 默认false，true则不能回退
    	state: { name: 'x' }	// 路由传参
    })
    navigate(-1);	// 回退，数字代表回退层数    
    ```



## 路由参数传递

+ **路由匹配：**

    ```tsx
    {/* App.tsx */}
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/show" element={<ShowPage />}>
          <Route path=":showId" element={<ShowPage />} />
        </Route>
      </Routes>
    </BrowserRouter>
    
    // ShowPage.tsx
    const ShowPage = () => {
        const params = useParams();
        return (
            <>
                <h2>{params.showId}</h2>
                <Link to={"/"}>回到首页</Link>
            </>
        )
    }
    ```

    > 效果：
    >
    > URL 为 "/show" 时不显示`<h2>`内容，为 "/show/2" 时显示出 2.

+ 获取路由参数：

    ```tsx
    import {useLocation} from "react-router-dom";
    
    const location = useLocation();
    ```

    > 获取到的 location 有如下字段：`pathname`、`search`、`state`。



## 获取路由

**组件内监听路由：**

```jsx
const location = useLocation();
// 监听路由
useEffect(() => {
  console.log(location.pathname)
}, [location.pathname])
```



# 请求

## Axios

```shell
$npm i axios -S
```

+ <span style="font-size:22px">基本使用：</span>

    ```tsx
    useEffect(() => {
        const fetchData = async () => {
            axios.get("http://cloud-music.pl-fe.cn/search/hot")
                .then(res => {
                    const { data } = res;
                    console.log(data.result.hots)
        fetchData()
            .catch(console.error);
    }, []);
    ```



## Proxy 处理跨域

```json
{
  "proxy": "http://localhost:8080"
}
```

> 在 **package.json** 加入此行后请求不再报出跨域错误
>
> **使用：**
>
> ```js
> axiosInstance.get("/user/verify", {
> ```



## 数据渲染

+  <span style="font-size:18px">dataset：</span>

    ```tsx
    {shoppingList).map((item, index) => (
      <i
        key={index}
        data-price={item.price_now}
        onClick={checkGoods}
    	/>
    ```

    > 点击事件通过 e.currentTarget.dataset.price 获取到数据



# Antd

基于 Ant Design 设计体系的 React UI 组件库，主要用于研发企业级中后台产品。

+ **引入：**

    ```shell
    $npm i antd -S
    ```

    
    
    ```tsx
    // App.tsx
    import 'antd/dist/antd.css';
    ```
    



# TypeScript

+ <span style="font-size:22px">声明类型文件：</span>

    > Eg：TS2307: Cannot find module './README.md' or its corresponding type declarations.

    在 src 目录下新建 **globals.d.ts**，

    ```ts
    declare module "*.md";
    ```

+ <span style="font-size:22px">React 事件——Event：</span>

    + MouseEvent<T = element>

    > EG:
    >
    > ```tsx
    > function checkGoods(e: React.MouseEvent<HTMLElement>): void {
    >   console.log(e.currentTarget.dataset)
    > }
    > ```



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




# Others

## 渲染 .md 文件

+ 使用[react-markdown](https://github.com/remarkjs/react-markdown)

+ **Demo:**——fetch 识别文件内容

    ```tsx
    import ReactMarkdown from "react-markdown";
    import md from "./README.md";
    import {useEffect, useState} from "react";
    
    export const ShowMd = () => {
        const [markdown, setMarkdown] = useState("");
    
        useEffect(() => {
            fetch(md)
                .then(res => res.text())
                .then(text => setMarkdown(text));
        }, []);
    
        return (<ReactMarkdown children={markdown} />)
    }
    ```

    > 使用 ReactMarkdown 组件时可能会发生两种情况：能直接识别 md 文件；直接引入的markdown文件不能被识别，需要通过fetch来获取文件内容（上例为第二种情况）



## 导出 Excel

```shell
$yarn add exceljs
```



# REF

+ 总：

    [React 官方中文文档](https://zh-hans.reactjs.org/docs/hello-world.html)

+ 组件：

    [React-分享会 · 语雀](https://www.yuque.com/docs/share/42300f26-cb81-4947-ba04-4c207db13505?#a4878cb1)

+ Redux：

    [Hooks | React Redux](https://react-redux.js.org/api/hooks)

+ Others：

    [react引入渲染markdown文件_M_Eve的博客-CSDN博客](https://blog.csdn.net/M_Eve/article/details/105614726)



# DEBUG

+ <span style="font-size:20px">md 文件中 html 标签不能识别：</span>

    + Q_Desc：转换结果为字符串

        ```tsx
        <ReactMarkdown skipHtml={false} children={markdown} />
        ```

+ <span style="font-size:20px">使用 createRoot() </span>时，[Argument of type 'HTMLElement | null' is not assignable to parameter of type 'Element'. Type 'null' is not assignable to type 'Element'.ts(2345)](https://stackoverflow.com/questions/63520680/argument-of-type-htmlelement-null-is-not-assignable-to-parameter-of-type-el)

    + S_Desc：获取节点可能为空值，需加上 TS 断言或判断 
    + S：`ReactDOMClient.createRoot(document.getElementById('root')!)`
    
+ <span style="font-size:20px">setState 更新不及时：</span>

    + S_Desc：改为函数式更新

+ <span style="font-size:18px">[A component is changing an uncontrolled input to be controlled](https://stackoverflow.com/questions/47012169/a-component-is-changing-an-uncontrolled-input-of-type-text-to-be-controlled-erro)</span>

    + Q_Desc：

        ```tsx
        const [submitInfo, setSubmitInfo] = useState<Info>();
        
        return (
          <input value={submitInfo?.tel} onChange={changeTel} />
        )
        ```

    + R:value 值从 undefined 变为其余类型

    + S：给 state 设置初始值（让该字段不为 undefined）

+ <span style="font-size:18px">[Failed to parse source map: 'webpack://antd/./components/config-provider/style/index.less' URL is not supported](https://stackoverflow.com/questions/71500112/antd-source-map-not-supported-in-react)</span>

    Q_Desc：`react-script` 升级到 `5.0.0` 之后出现此问题

    S：

    ```diff
    - import '~antd/dist/antd.css';
    + import '~antd/dist/antd.less';
    ```

+ <span style="font-size:18px">[HPM] Subscribed to http-proxy events: [ 'error', 'close' ]</span> ==未解决==

    Q_Desc: 

    ```js
    // setupProxy.js
    const {createProxyMiddleware} = require("http-proxy-middleware")
    module.exports = (app)=>{
        app.use("/api", createProxyMiddleware({
            target:"http://120.0.0.1:8080",
            changeOrigin:true,
            pathRewrite: {
                "^/api":"/",
            },
            logLevel: "debug",
        }))
    }
    ```

    > 项目启动显示：
    >
    > ```shell
    > [HPM] Proxy created: /  -> http://120.0.0.1:8080
    > [HPM] Proxy rewrite rule created: "^/api" ~> "/"
    > [HPM] Subscribed to http-proxy events: [ 'error', 'close' ]
    > ```
    >
    > 请求仍没有被代理。

