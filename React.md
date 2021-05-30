



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
    ```
    
    8. 使用 jsx 语法需把`script`标签的`type`属性设置为`text/babel`
    
    
    ```

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

    <hr>

    在下面的例子中，声明了一个`name`变量，然后在 JSX 中使用它，并将它包裹在`{}`中：

    ``` JSX
    const name = 'Josh';
    const element = <h1>Hello, {name}</h1>;
    ```

    在 JSX 语法中，可以在`{}`内放置任何有效的 JS 表达式，同时 **JSX 本身也是一个表达式**



+ <span style="font-size:20px">JSX "" 写入字面量</span>

    <hr>
    可以通过**使用`""`将属性值指定为字符串字面量**

    ``` jsx
    const element = <h2 className="title" id={myId.toLowerCase()} />
    ```

    

    > 注意：小驼峰命名
    >
    > 因为 JSX 语法上更接近 JS 而不是 HTML，所以 React DOM 使用“小驼峰命名”来定义属性的名称，而不使用 HTML 属性名称的命名约定
    >
    > 例如，JSX 里的`class`变成**`className`**，而`tabindex`变成`tabIndex`。



+ <span style="font-size:20px">内联样式 {{}} </span>

    <hr>

    内联样式，要用 **style={{key: value, key: value}}** 的形式写

    ``` jsx
    const VDOM = (
      <h2 className="title">
      	<span style={{color:'white', fontSize:'29px'}}>{myDate.toLowerCase()}</span>  
      </h2> 		
    )
    ```

    

+ <span style="font-size:20px">使用 JSX 指定子元素</span>

    <hr>

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

    Babel会把 JSX 译为`React.createElement()`函数调用，

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

    下面的情况就叫做有多个根标签：

    ``` jsx
    /* 以下为错误写法 */
    const VDOM = (
    	<h2>
      	<span />
      </h2>
      
      <span />
    )
    ```

    可以在外面再包层容器，改为：

    ``` jsx
    const VDOM = (
    	<div>
      	<h2>
      		<span />
      	</h2>
        
        <span />
      </div>
    )
    ```



+ <span style="font-size:20px">标签首字母大小写区分</span>

    小写字母开头，将该标签转为 HTML 中同名元素；若无，报错。

    大写字母开头，react 渲染对应组件；若没有定义该组件，报错。

    

# 元素渲染

+ <span style="font-size:20px">创建虚拟 DOM</span>

    <hr>

    元素是构成 React 应用的最小砖块，就是虚拟 DOM。

    元素描述了你在屏幕上想看到的内容

    ``` jsx
    const element = <h1>Hello</h1>;	// 创建虚拟 DOM
    
    ```

const element2 = (
    	<h1 className="greeting">
            Hello, world
        </h1>
    );
    ```
    
    与浏览器的 DOM 元素不同，React 元素是创建开销极小的普通对象。React Dom 会负责更新 DOM 来与 React 元素保持一致



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

    <hr>

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

    <hr>

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
    ```
    
     
    
    上述两个组件在 React 里是等效的。



+ <span style="font-size:20px">渲染组件</span>

    React 元素也可以是用户自定义的组件：	

    ``` jsx
    const element = <Welcome name="Sara" />
    ```

    当 React 元素是用户自定义组件时，它会将 JSX 所接收的属性（*attributes*）以及子组件（*children*）转换为单个对象传递给组件，这个对象称为“props”。	==？==

    

    渲染组件的简单例子：

    函数组件

    ``` jsx
    function MyComponent() {
      return <h2>Hello</h2>
    }
    
    ReactDOM.render(
      <MyComponent />, 
      document.getElementById('root')
    )
    ```

    执行 `ReactDOM.render(<MyComponent/>.... `之后，发生了什么：

    1. React 解析组件标签，找到了 MyComponent 组件。
    2. 发现组件是使用函数定义的，随后调用该函数，将返回的虚拟 DOM 转为真实 DOM，随后呈现在页面中

    

    例如，在页面渲染`Hello, Sara`：	==?==

    ``` jsx
    function Welcome(props) {	// 
        return <h1>Hello, {props.name}</h1>;
    }
    
    const element = <Welcome name="Sara" />;	// 自定义 Welcome 组件
    ReactDom.render(
    	element,
        document.getElementById('root')
    );
    ```

    分析这个例子：

    1. 调用`ReactDOM.render()`函数，传入`<Welcome name="Sara" />`作为参数。
    2. React 调用 Welcome 组件，并将`{name: 'Sara'}`作为 props 传入
    3. `Welcome`组件将`<h1>Hello, Sara</h1>`元素作为返回值
    4. React DOM 将 DOM 更新为`<h1>Hello, Sara</h1>`。

    

    注意，**组件名称必须以大写字母开头**：React 会将以小写字母开头的组件视为原生 DOM 标签；大写字母开头的，React 将渲染对应组件。例如：`<div />`代表 HTML 的`div`标签；而`<Welcome />`则代表一个组件，并且需在作用域内使用`Welcome`。

    

    类式组件：

    ``` jsx
    class MyComponent extends React.component {
      render() {	
        return <h2>我是类式组件</h2>
      }
    }
    
    ReactDOM.render(
      <MyComponent/>, 
      document.getElementById('test')
    )
    ```

    第 2 行的`render()`是放在`MyComponent`的原型对象上，供实例使用。

    那么实例在哪呢：

    ​	执行了`ReactDOM.render(<Mycomponent />....`之后，发生了什么：

    1. React 解析**组件标签**，找到了`MyComponent`组件
    2. 发现组件是使用类定义的，随后`new`出该类的实例，并通过该实例调用原型上的`render()`方法
    3. 将`render`返回的虚拟 DOM 转为真实 DOM，随后呈现在页面中 



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

    通常，每个新的 React 应用程序的顶层组件都是`App`组件。但是，如果将 React 集成到现有的应用程序中，可能需要使用像`Button`这样的小组件，并自下而上地将这类组件逐步应用到视图层的每一处。	==？==

    

+ <span style="font-size:20px">提取组件</span>==？==

    将组件拆分为更小的组件。
    例如，如下`Comment`组件：

    ``` jsx
    function Comment(props) {
        return (
        <div className="Comment">
        	<div className="UserInfo">
            	<img className="Avatar" 
                    src={props.author.avatarUrl}
                    alt={props.author.name}
                />
                <div className="UserInfo-name">
                    {props.author.name}
                </div>
            </div>
            <div className="Comment-text">
                {props.text}
            </div>
            <div className="Comment-date">
                {formateDate(props.date)}
            </div>
        </div>
        )
    }
    ```

     该组件用于描述一个社交媒体网站上的评论功能，它接收 `author`（*对象*），`text` （*字符串*）以及 `date`（*日期*）作为 props。

    该组件由于嵌套的关系难以维护，且很难复用它的各个部分。因此从中提取一些组件出来。

    ==。。。==







## 组件实例的三大核心属性

+ <span style="font-size:20px">构造器</span>

在 React 中 construcotr 通常仅用于以下两种情况：

1. 通过给 `this.state`赋值对象来初始化内部 state

    ``` jsx
    this.state = {isHot: false, wind:'微风'}
    ```

    也可以简写，不在构造器里：

    ``` jsx
    state = {isHot: false, wind:'微风'}
    ```

    

2. 为事件处理函数绑定实例

    ``` jsx
    this.changeWeather = this.changeWeather.bind(this)
    ```

    也可以简写，不在构造器里：

    ``` jsx
    changeWeather = () => {
      const isHot = this.state.isHot
      this.setState({isHot: !isHot})
    }
    ```

    

### state

组件就是“状态机”。通过更新组件的状态来更新对应的页面显示。（*每 `setState` 一次，`render`就会执行一次*)

state 借助构造器`constructor()`初始化状态`state`；在`render()`中读出`state`属性；用`setState`改变状态

下面的例子实现点击屏幕切换显示：“今天天气很 炎热 / 凉爽”

``` jsx
/* 创建组件 */
class Weather extends React.Component {
  constructor() {
    super(props);
    this.state = {isHot:true};	// 借助构造器初始化状态
  	this.changeWeather = this.changeWeather.bind(this)	// bind 找回丢失的 this ？
  }
  render() {
    return <h2 onClick={this.changeWeather}>今天天气很{this.state.isHot ? '炎热' : '凉爽'}</h2>
  }
  changeWeather() {
    // changeWeather 放在哪里：Weather 的原型对象上，供实例使用
    // 由于 changeWeather 是作为 onClick 的回调，所以不是通过实例调用，而是直接调用
    // 类中的方法默认开启了严格模式，所以changeWeather 中的 this 为 undefined
    
    /* 获取原来的 isHot 值 */
    const isHot = this.state.isHot
    this.setState({isHot: !isHot});	// 注意：state 必须通过 setState 进行更新，且更新是合并不是替换
    
    
  }
}

/* 渲染组件 */
ReactDOM.render(<Weather/>,document.getElementById('root'))
```

关于第 6 行代码：	==？==

+ 等号右边的`this.changeWeather`指向第 11 行原型对象上的`changeWeather()`。
+ `bind`生成一个新的函数，该函数的`this`被改变
+ `bind`生成的新函数赋值给等号左边 



+ <span style="font-size:20px">state 的简写</span>

    类中可以直接写赋值语句，作用与在`constructor`中赋值相同

    ``` jsx
    /* 创建组件 */
    class Weather extends React.component {
      /* 初始化状态 */
      state = {isHot: flase, wind: "微风"};
    	
    	render() {
      	const {isHot, wind} = this.state;
        return <h2 onClick={this.changeWeather}>今天天气很{isHot ? '热' ： '凉'}, {wind}</h2>
      }
    
    	/* 自定义方法：用赋值语句的形式 + 箭头函数 */
    	changeWeather = () => {
        const isHot = this.state.isHot;
        this.setState({isHot: !isHot})
      }
    }
    
    /* 渲染组件到页面 */
    ReactDOM.render(<Weather/>,document.getElementById('root'))
    ```

    

+ <span style="font-size:20px">将函数组件转换成 class 组件</span>

    通过以下五步将函数组件转换成 class 组件：	==有啥用？==

    1. 创建一个同名的 ES6 class，并且继承于 `React.Component`。
    2. 添加一个空的`render()`方法。
    3. 将函数体移动到`render()`方法之中。
    4. 在`render()`方法中使用`this.props`替换`props`。
    5. 删除剩余的空函数声明

    ``` jsx
    class Clock extends React.Component {
        render() {
            return (
            	<div>
                	<h1>Hello, world</h1>
                	<h2>It's {this.props.date.toLocaleTimeString()}.</h2>
                </div>
            );
        }
    }
    ```

    现在，`Clock`组件被定义为 class，而不是函数

    每次组件更新时`render`方法都会被调用==？==，但只要在相同的 DOM 节点中渲染`<Clock />`，就仅有一个`Clock`组件的 class 实例被创建使用。这就使得我们可以使用如 state 或生命周期方法等许多其他特性。



+ <span style="font-size:20px">向 class 组件中添加局部的 state</span>

    通过以下三步将`date`从 props 移动到 state 中：

    1. 把`render()`方法中的`this.props.date`替换成`this.state.date`：（*下例第 6 行*）

        ``` jsx
        class Clock eextends React.Component {
            render() {
                return (
                	<div>
                   	  <h1>Hello, world</h1>
                      <h2>It's {this.state.date.toLocaleTimeString()}</h2>
                    </div>
                );
            }
        }
        ```

    2. 添加`class`构造函数`constructor`，然后在该函数中为`this.state`赋初值：（*第 4 行*）

        ``` jsx
        class Clock extends React.Component {
            construtor(props) {
                super(props);
                this.state = {date: new Date()};
            }
            
            render() {
                return (
                	<div>
                      <h1>Hello, world</h1>
                      <h2>It's {this.state.date.toLocaleTimeString()}.</h2>
                    </div>
                );
            }
        }
        ```

        通过以下方式将`props`传递到父类的构造函数中：

        ``` jsx
        constructor(props) {
            super(props);	// super 作为函数调用，代表父类的构造函数
            this.state = {date: new Date()};
        }
        ```

        Class 组件应该始终使用`props`参数来调用父类的构造函数。

    3. 移除`<Clock />`元素中的`date`属性：

        ``` jsx
        ReactDOM.render(
        	<Clock />,
            document.getElementById('root')
        );
        ```

    之后将计时器相关的代码添加到组件中，如下：此时还不会更新

    ``` jsx
    class Clock extends React.Component {
        constructor(props) {
            super(props);
            this.state = {date: new Date()};
        }
        
        render() {
            return (
            	<div>
                  <h1>Hello, world</h1>
                  <h2>It's {this.state.date.toLocaleTimeString()}.</h2>
                </div>
            )
        }
    }
    
    ReactDOM.render(
    	<Clock />,
        document.getElementId('root')
    );
    ```

    接下来，设置`Clock`的计时器并每秒更新。



+ <span style="font-size:20px">将生命周期方法添加到 Class 中</span>

    在具有许多组件的应用程序中，当组件被销毁时释放所占用的资源非常重用

    当`Clock`组件第一次被渲染到 DOM 中时，就设置一个计时器。这在 React 中被称为”挂载（*mount*）

    同时，当 DOM 中`Clock`组件被删除的时候，应该清除计时器。这在 React 中被称为“卸载”（*unmount*）

    可以为 class 组件声明一些特殊的方法，当组件挂载或卸载时就会去执行这些方法：

    ``` jsx
    class Clock extends React.Component {
        constructor(props) {
            super(props);
            this.state = {date: new Date()};
        }
        
        componentDidMount() {}
        
        componentWillUnmount() {}
        
        render() {
            return (
              <div>
            	<h1>Hello, world</h1>
                <h2>It's {this.state.date.toLocaleTimeString()}.</h2>
              </div>
            );
        }
    }
    ```

    这些方法叫做“生命周期方法”



### props

State 与 props 类似，但是 state 是私有的，并且完全受控于当前组件



+ <span style="font-size:20px">props 基本应用</span>

    ``` jsx
    class Person extends React.Component{
      render() {
        const {name, age, sex} = this.props;
        return (
        	<u1>
          	<li>姓名：{name}</li>
            <li>性别：{sex}</li>
            <li>年龄：{age}</li>
          </u1>
        )
      }
    }
    /* 渲染组件到页面 */
    ReactDom.render(<Person name="jerry" age="19" sex="男"/>document.getElementById('test1'))	// Person 后是传组件标签的值
    ReactDom.render(<Person name="mac" age="16" sex="男"/>document.getElementById('test2'))
    ReactDom.render(<Person name="tom" age="18" sex="男"/>document.getElementById('test3'))
    ```



+ props 的批量传递==?==

    

+ <span style="font-size:20px">对 props 进行限制</span>

    需要引入 proto-types.js 依赖包，用于对组件标签属性进行限制。在全局增加了一个`ProtoTypes`对象

    ``` jsx
    class Person extends React.Component{
      render() {
        const {name, age, sex} = this.props;
        return (
        	<u1>
          	<li>姓名：{name}</li>
            <li>性别：{sex}</li>
            <li>年龄：{age}</li>
          </u1>
        )
      }
    }
    /* 对标签属性进行 类型、必要性 的限制 */
    Person.protoTypes = {
      name:ProtoTypes.string.isRequired,	// 限制 name 必传，且为字符串
      sex:ProtoTypes.string,
      age:ProtoTypes.number,
      speak:ProtoTypes.func,
    }
    /* 指定默认标签属性值 */
    Person.defaultProps = {
      sex:'男',
      age:18,	// 默认值 18 
    }
    
    /* 渲染组件到页面 */
    ReactDOM.render(
      <Person name="jerry" speak="1"/>,	// 此时控制台报错，因为需要 speak 为函数类型
      document.getElementById('test1')
    );
    ```



+ <span style="font-size:20px">Props 的只读性</span>

    组件无论是使用函数声明还是通过 class 声明，都绝不能修改自身的 props。因为 React 有一个严格的规则：**所有 React 组件都必须保护它们的入参 props 不被更改**。

    当前，应用程序的 UI 是动态的，并会随着时间的推移而变化。”state“，在不违反上述规则的情况下， state 允许 React 组件随用户操作、网络响应或者其他变化而动态更改输出内容。



+ <span style="font-size:20px">props 的简写</span>

    通过 static 写在 class 语句块内。

    ``` jsx
    class Person extends React.Component {
      static protoTypes = {
        name:ProtoTypes.string.isRequired,	// 限制 name 必传，且为字符串
      	sex:ProtoTypes.string,
      	age:ProtoTypes.number,
    	  speak:ProtoTypes.func,
      }
    	static defaultProps = {
        sex:'男',
      	age:18,	// 默认值 18 
      }
    }
    ```

    > [static | MDN](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Classes/static)
    >
    > 类通过 static 关键字定义静态方法。
    >
    > 静态方法只能通过类本身调用，不能在类的实例上调用



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
    /* 限制标签 */
    Person.propTypes = {
      name:ProtoTypes.string.isRequired,
      sex:ProtoTypes.string,
      age:ProtoTypes.number,
    }
    /* 指定默认值 */
    Perosn.defaultProps = {
      sex: '男',
      age: 18
    }
    
    /* 渲染组件到页面 */
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

4. 在创建的文件夹路径下`yarn start`，或在 VS Code 终端输入`yarn start`，浏览器弹出

    <img src="C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20210528190553720.png" alt="image-20210528190553720" style="zoom:33%;" />



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



# 参考链接

[React 官方中文文档](https://zh-hans.reactjs.org/docs/hello-world.html)

 

