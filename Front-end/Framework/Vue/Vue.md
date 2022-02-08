# Vue 配置

## Vue CLI

1. 安装 Vue CLI

    ```
    npm i -g @vue/cli
    ```

2. 创建一个项目

    ```
    vue create test-vue
    ```

3. 启动开发服务器附带模块热重载

    ```
    npm run serve
    ```




## Vue  项目

+ <span style="font-size:20px">Vue 项目目录结构推荐</span>：

    - `main.js`主入口，`router.js`路由划分
    - `plugins` 自己或第三方插件,包括但不限于components、directives、filters、third lib
    - `pages` 所有路由页面。原则：轻page，重component
    - `components` 所有组件。包括原子组件、业务公用组件、页面独有组件
    - `server` api引入入口
    - `assets` sass、图片资源入口，不常修改数据
    - `utils` 工具文件夹
    - `store` 标准vuex格式，非必须

    ```
    project
    └───src
    │   │   App.vue    // 主页面
    │   │   main.js    // 主入口
    |   |   router.js  // 所有路由
    │   │
    │   |____assets    // css、image、svg等资源
    │   |   |____css   // 所有sass资源
    |   |   |    |  reset.scss       // 兼容各浏览器
    |   |   |    |  global.scss      // 全局css
    |   |   |    |  variable.scss    // sass变量和function等
    │   |   |____img   // image图标库
    |   |   |____svg   // svg图标库
    |   |
    |   |____components    // 组件
    │   |   |____common    // common自注册组件
    │   |        |____base // 原子组件(如果是引入第三方，该文件夹可省略)
    │   |        |   ...   // 业务公用组件
    │   |   |____entity    // entity页面组件
    │   |   |____about     // about页面组件
    |   |
    |   |____pages     // UI层(原则：轻page，重component)
    |   |   |____entity
    |   |   |    |  list.vue      // 列表页
    |   |   |    |  create.vue    // 新增页
    |   |   |    |  edit.vue      // 修改页
    |   |   | main.vue
    |   |
    |   |____plugins   // 自己或第三方插件
    |   |   | index.js       // 插件入口文件
    |   |   | directives.js  // 所有Vue指令
    |   |   | filters.js  // 所有Vue过滤
    |   |
    |   |____server    // 接口层
    |   |   | index.js   // 所有接口
    |   |   | http.js  // axios二次封装
    |   |
    |   |____store     // vuex数据
    |   |   | index.js
    |   |
    |   |____utils     // 工具层
    |   |   | config.js// 配置文件，包括常量配置
    |
    └───public         // 公用文件，不经过webpack处理
    │   │   favicon.ico
    │   │   index.html
    │   vue.config.js  // vue-cli3主配置
    │   babel.config.js// babel配置
    │   .eslintrc.js   // eslint配置
    │   .prettierrc.js // perttier配置
    │   package.json   // npm配置
    │   README.md      
    ```

    

+ <span style="font-size:20px">CSS 样式文件的引入</span>：

    + 全局样式：

        定义为单独的`.css`文件，

        + 在入口 JS 文件`main.js`引入后就能在全局生效了
        + 在`index.html`

    + CSS 作用域：

        写在`.vue`中的`<style>`标签内

        vue单文件组件中，为了防止全局同css类名名样式的污染，vue-loade对单文件组件 `<style> `标签增加了scoped属性的处理

        ```vue
        <style>
          /* 全局样式 */
        </style>
        <style scoped>
          /* 本地样式 */
        </style>
        ```

        > 因为权重的问题，如果是在子组件使用了scoped，那么在父组件中是不能直接修改子组件的样式的，需要在父组件中使用vue的深度作用选择器



+ <span style="font-size:20px">App.vue</span>

    主组件，页面入口文件。所有页面都是在`App.vue`下进行切换的，它负责构建定义及页面组件归集

     通常为页面申明了模板：

    ```vue
    <template>
      <div id="app">
        <img alt="Vue logo" src="./assets/logo.png">
        <HelloWorld />
      </div>
    </template>
    
    <script>
    import HelloWorld from './components/HelloWorld.vue'
    
    export default {
      name: 'App',
      components: {
        HelloWorld
      }
    }
    </script>
    
    <style>		<!--全局样式-->
    #app {
      font-family: Avenir, Helvetica, Arial, sans-serif;
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
      text-align: center;
      color: #2c3e50;
      margin-top: 60px;
    }
    </style>
    ```



+ <span style="font-size:20px">main.js：</span>

    初始化根组件为页面元素，以及负责我们想在 App 使用的其余插件和第三方组件：

    ```js
    import Vue from "vue";
    import { store } from "./store/store";
    import router from "./router";
    import App from "./App.vue";
    
    new Vue({
      router,
      store,
      render: h => h(App)
    }).$mount("#app");
    ```



+ <span style="font-size:20px">index.html：</span>

    入口文件，引入`main.js`初始化 App：

    ```html
    <!-- the html element that hosts the App.vue component -->
    <div id="app"></div>
    
    <!-- built files will be auto injected -->
    <script type="text/javascript" src="main.js"></script>  
    ```

    Webpack 提供插件 HtmlWebpackPlugin 自动加载构建后的脚本而无需手动引入。



#  Vue 基础

## 安装

+ npm：

    ```
    npm install vue
    ```

+ `<script>`引入：

    ```js
    <!-- 开发环境版本，包含了有帮助的命令行警告 -->
    <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
    
    <!-- 生产环境版本，优化了尺寸和速度 -->
    <script src="https://cdn.jsdelivr.net/npm/vue@2"></script>
    ```

    

## 声明式渲染

Vue.js 的**核心**是允许采用简洁**模板语法来声明式地将数据渲染进 DOM**

实现浏览器显示：`Hello Vue`

步骤：

1. 创建`Vue`实例对象，设置`el`和`data`属性
3. 使用简洁的**模板语法**把数据渲染到页面

```html
<div id="app">
  {{ message }}
</div>
```

```js
var app = new Vue({	
  el: "#app",	// #为id选择器
  data:{
    message: "Hello Vue"
  }
})
```



## el 挂载点

+ Vue的**作用范围**：

    `Vue`会作用于`el`选项命中的元素及其内部的后代元素

+ **选择器**：

    `el`属性支持其他**选择器**，但建议使用 ID 选择器



可以**设置其他 dom 元素**：

适用于除`html`和`body`外的所有双标签



## data 数据对象

在上文创建的第一个Vue程序里面，`data`属性只定义了一个基本数据类型字符串`message`。但`data`属性还可以支持复杂数据类型，如数组、对象。

```html
<body>
    <div id="a">
        {{ message }}	// Hello vue
        {{ student[1] }}	// Jim
        <h3>
            {{ Mike.age }}	// 三级标题Mike
        </h3>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script>
        let a = new Vue({
            el:"#a",
            data:{
                message:"Hello vue",
           			Mike:{
                    name:"Mike",
                    age:12
                },
                student:["Mike", "Jim", "Max"]
            }
        })
    </script>
</body>
```



总结：

+ Vue 中用到的数据定义在`data`中
+ `data`中可以写复杂类型数据
+ 渲染复杂数据类型时,遵守 js 的语法即可



## v 指令

通过 Vue 提供的各种指令，在渲染的 DOM 上应用特殊地响应式行为



+ <span style="font-size:22px">v-text	重置标签文本：</span>

    在标签内部使用，重置标签的文本值(*textcontent*)

    ```html
    <div id="app">
        <h2 v-text="message"></h2>
    </div>
    
    <script>
    	let app = new Vue({
            el:"#app",
            data:{
                message:"v-text用来重置标签的文本"
            }
        })
    </script>
    ```

    

    但是使用`v-text`有一个缺点是，它会将原有的值也替换掉。

    

    如果只设置部分内容，可使用另一种写法，差值表达式 `{{ }}`，。根据需求选择。

    ``` vue
    <div id="app">
        <h2>原有内容{{ message }}</h2>
    </div>
    
    // 后面代码同
    ```

    

    字符串拼接

    ```vue
    <div id="app">
        <h2 v-text="message + '!'">原有内容</h2>	// 设置内容!
        <h2>原有内容和{{ message + "!" }}</h2>	// 原有内容和设置内容!
    </div>
    
    <script>
    	let app = new Vue({
            el:"#app",
            data:{
                message:"设置内容"
            }
        })
    </script>
    ```

    

+ <span style="font-size:22px">v-html 设置元素 innerHTML：</span>

    `v-html `用来设置元素的`innerHTML`

    ``` vue
    <body>
        <div id="a">
            <p v-text="content"></p>
            <p v-html="content"></p>
            
            <p v-text="htmlContent"></p>
            <p v-html="htmlContent"></p>
        </div>
        
        <script>
        	let a = new Vue({
                el:"#a",
                data:{
                    content:"设置的文本内容"
                    htmlContent:"<a href='https://cn.bing.com'>必应</a>"
                }
            })
        </script>
    </body>
    ```

    由上面`v-text`与`v-html`的对比得出，`v-html`能解析html结构，而`v-text`只会解析文本

    

+ <span style="font-size:22px">v-on 为元素绑定事件：</span>

    作用：为元素绑定事件

    ```vue
    <div id="a">
        <input type="button" value="事件绑定" v-on:click="show('传的参数')">
        <input type="button" value="事件绑定" v-on:monseenter="show">	// 鼠标移入事件
        <input type="button" value="事件绑定" v-on:click="show">	// 双击事件
        <input type="button" value="事件绑定" @click="show">
    </div>
    ```

    `@`功能与之等同

    

    在`Vue`实例中设置`methods`属性

    ``` vue
    <div id="click">
        <h2 @click="show">点击</h2>
        {{ times }}{{ word }}
      </div>  
      
      <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
      <script>
          let show = new Vue({
    		    el:"#click",
            data:{
                times:0,
                word:"次"
            },
            methods:{
                show:function(){
                    console.log(this.times);
                    this.times++;
                }
            }
    	})
      </script>
    ```

    可通过`this`指代`data`中定义的数据来达到更改数据的效果

    上面的代码中，每次点击事件都改变了`times`的值。

    

    传自定义参数，事件修饰符：	**？**

    详情见：[黑马程序员教程](https://www.bilibili.com/video/BV12J411m7MG?p=16&spm_id_from=pageDriver)

    ``` vue
    <input type="button" v-on:keyup.enter="show">	// keyup:按键触发
    ```

    

    总结：

    + `v-on`指令的作用是：为元素绑定事件
    + 事件名不需写 on   *click*
    + 指令可简写为`@:`
    + 绑定的方法定义在`methods`属性中
    + 方法内部可通过`this`关键字访问定义在`data`中数据

    + 可传自定义参数，事件修饰符



+ <span style="font-size:22px">v-show 切换元素显示状态：</span>

    根据表达式的真假，切换元素的显示和隐藏

    ``` vue
    <div id="a">
        <input type="button" value="切换显示状态" @click="change">
        <span v-show="state">被切换的一段文字</span>
    </div>
    
    <script>
    	let app = new Vue({
            el:"#a",
            data:{
                state:true
            },
            methods:{
            	change:function(){
                    this.state = !this.state
                }	
        	}
        })
    </script>
    ```

    上面代码实现的功能是：点击按钮切换文字的显示状态。

    

+ <span style="font-size:22px">v-if 切换元素显示状态：</span>

    根据表达式的真假，切换元素的显示和隐藏（操纵 dom 元素）

    

    与`v-show`的**本质区别**：

    `v-show`改变元素的`display`属性,而`v-if`通过直接增删 dom 元素实现

    `v-if`对性能消耗较大，需要频繁更改的元素则使用`v-show`。

    

    其值虽然在`""`里，但不是字符串

    ```html
    <h1>{{ msg }}</h1>
    <h1 v-if="msg">Vue is awesome!</h1>
    <h1 v-else>Oh no</h1>
    ```

    

+ <span style="font-size:22px">v-bind 设置元素属性：</span>

    设置元素的**属性** *如 src，title，class*

    

    语法：

    ```vue
    <img v-bind:src="imgSrc">
    // 代码略
    	data:{
    		imgSrc:"图片地址"
    	}
    ```

    应用中，`v-bind`能省略不写，也就是`<img :src="imgSrc">`

    

    属性也可拼接字符串

    `<img :title="imgTitle + '!!'">`

    

    总结：

    + `v-bind`指令用来 为元素绑定属性

    + 完整写法是 `v-bind:属性名`

    + 简写 `:属性名`

    + 需要动态的增删 class 建议使用对象的形式    **?**

        

+ <span style="font-size:22px">v-for 操纵列表：</span>

    根据数据生成列表结构

    ``` vue
        <ul id="app">
          <li v-for="index in arr">
            {{ index }}
          </li>
        </ul>
      
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script>
      let app = new Vue({
        el:"#app",
        data:{
          arr:[				
            {name: "a",	// 数组中的对象
            age: 1
            },
            {name: "c",
            age:2
            }
          ]
        }
      })
    </script>
    ```

    上面代码在浏览器中显示结果为![image-20210224211905327](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20210224211905327.png)

    将上面代码第3行改为`{{ index.name }}`，则浏览器中显示结果为![image-20210224212127737](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20210224212127737.png)

    

    更多见：[黑马程序员教学视频](https://www.bilibili.com/video/BV12J411m7MG?p=15)

    

+ <span style="font-size:22px">v-model 双向数据绑定：</span>

    设置及获取表单元素的值

    ```vue
    <div id="app">
      <input type="text" v-model="message">
      <h3>{{ message }}</h3>
    </div>
      
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script>
      let app = new Vue({
        el:"#app",
        data:{
          message:"表单元素的值"
        },
      })
    </script>
    ```

    上面代码在浏览器中显示为![image-20210225183034792](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20210225183034792.png)，当我们修改文本框内容时，下面的`message`也会同步修改。同样，`message`值改变时，表单元素值也同步更改。因此，称为 双向数据绑定。

    

    总结：

    + `v-model`指令是为了便捷地设置和获取表单元素的值
    + 绑定的数据会和表单元素值相关联
    + 绑定的数据与表单元素值双向绑定



## 组件化应用构建

Vue 中，一个组件本质上是一个拥有预定义选项的 Vue 实例

一个 Vue 应用由一个通过`new Vue`创建的**根 Vue 实例**，及可选的组件树组成



+ **注册组件**：

    `template`是“模板”的意思。

    ```js
    Vue.component('todo-item', {
      template: '<li>这是个待办项</li>'
    })
    
    let app = new Vue(...)
    ```

    

+ **组件复用**：

    ```js
    <ol>
      <!-- 创建一个 todo-item 组件的实例 -->
      <todo-item></todo-item>
    </ol>
    ```

    

+ **从父作用域将数据`props`传到子组件**：

    修改组件的定义，使它能接受一个`prop`：

    ```js
    Vue.component('todo-item', {
      // todo-item 组件现在接受一个"prop"，类似于一个自定义 attribute。
      props: ['todo'],
      template: '<li>{{ todo.text }}</li>'
    })
    ```

    

+ **`v-bind`**将待办项传到**循环输出**的每个**组件**：

    ```html
    <div id="app">
      <ol>
      	<todo-item
    			v-for="item in groceryList"
          v-bind:todo="item"
          v-bind:key="item.id"
        ></todo-item>
      </ol>
    </div>
    ```

    ```vue
    Vue.component('todo-item', {
      props: ['todo'],
      template: '<li>{{ todo.text }}</li>'
    })
    
    let app = new Vue({
    	el: '#app',
    	data: {
    		groceryList: [
    			{ id: 0, text: '素菜' },
    			{ id: 1, text: '荤菜' },
        ]
      }
    })
    ```

    

# Vue 实例

## 创建 Vue 实例

每个 Vue 应用都是通过`new Vue`创建新的 Vue 实例开始：

```vue
let vm = new Vue({
	// 选项
})
```

创建 Vue 实例时，可传入一个**选项对象**，本章主要描述如何使用这些选项创建想要的行为



所有的 Vue 组件都是 Vue 实例



## 数据 data 与方法 $、methods

Vue 实例被创建时，将`data`对象中所有 property 加入到 Vue 的**响应式系统**中——当 property 值发生改变，视图将产生“响应”，匹配更新为新的值

```js
// 数据对象
let data = { a: 1 }

// 该对象加入到 Vue 实例
let vm = new Vue({
	data: data
})

// 设置 property 会影响到原始数据
vm.a = 2;
data.a	// 2
// 反之亦然
```



除了数据 property，Vue 实例还暴露了一些实例 property 与方法，以前缀`$`与用户定义的 property 区分：

eg. `vm.$watch`

可以在 [API 参考](https://cn.vuejs.org/v2/api/#%E5%85%A8%E5%B1%80-API)中查阅到完整的实例 property 和方法的列表。



## 实例生命周期钩子

生命周期钩子函数给了用户在不同阶段添加自己代码的机会



+ **created**：

    `created`钩子函数用来在一个**实例被创建之后执行**代码：

    ```js
    new Vue({
      data: {
        a: 1,
      },
      created: function () {
        // this 指向 vm 实例
        console.log(`a is: ${this.a}`);
      }
    })
    // a is: 1
    ```

    

+ 其余[生命周期钩子](https://cn.vuejs.org/v2/api/#%E9%80%89%E9%A1%B9-%E7%94%9F%E5%91%BD%E5%91%A8%E6%9C%9F%E9%92%A9%E5%AD%90)



所有生命周期钩子的`this`上下文指向实例，因此可以访问`data`、`computed`、`methods`。意味着**不应使用箭头函数定义一个生命周期方法**



**生命周期图示**：

<img src="https://cn.vuejs.org/images/lifecycle.png" alt="img" style="zoom:33%;" />

## 计算属性 computed 和侦听器 watch

### 计算属性 computed

模板表达的初衷是用于简单运算，模板中放入太多逻辑会让模板过重且难以维护

例如：

```jsx
<div id="example">{{ message.split('').reverse().join('') }}</div>
```

在这里，模板不再是简单的声明式逻辑。所以对于任何复杂逻辑都应使用**计算属性**。

如下例的`reversedMessage`即是我们声明的计算属性：

```html
<div id="example">
  <p>{{ message }}</p>
  <p>{{ reversedMessage }}</p>
</div>
```

```jsx
let vm = new Vue({
  el: '#example',
  data: {
    message: 'Hello',
  },
  computed: {
    reversedMessage: function() {
      return this.message.split('').rever().join('');
    }
  }
})
```



### 侦听器

==。。。==



# 模板语法

Vue.js 使用**基于 HTML 的模板语法**，允许声明式地将 DOM 绑定至底层 Vue 实例的数据

如果熟悉虚拟 DOM 可以不用模板，直接写 **render 函数**，使用可选的 **JSX** 语法



## 插值

+ <span style="font-size:18px; font-weight:bold">文本</span>

    最常见形式是用“Mustache”语法 (*双大括号*)**`{{ }}`**的文本插值

    ```js
    <span>Message: {{ msg }}</span>
    ```

    Mustache 标签会被替代为对应数据对象上`msg` property 的值，且会随之更新

    通过**`v-once`**也能执行**一次性插值**：

    ```vue
    <span v-once>这个将不会改变: {{ msg }}</span>
    ```

    

+ <span style="font-size:18px; font-weight:bold">原始 HTML</span>

    `{{ }}`会将数据解释为普通文本，而非 HTML 代码

    输出真正的 HTML，需要使用`v-html`指令

+ Attribute

+ JS 表达式



## template

`template`的作用时**模板占位符**，帮助我们包裹元素，但不会被渲染到页面上



# Class 与 Style 绑定

操作元素的 class 列表和内联样式是数据绑定的一个常见需求。因为它们都是属性 attribute，所以可以用`v-bind`处理

**`v-bind`**用于`class`和`style`时做了专门的增强：表达式结果的类型除字符串外可以是**对象**或**数组**

==。。。==



# 条件渲染

`v-if`

==。。。==



# 列表渲染

`v-for`

==。。。==



# 事件处理 `v-on`

+ **触发一些代码：**

    ```js
    <button v-on:click="counter += 1">Add 1</button>
    ```
    
+ **调用方法名：**

    ```html
    <button v-on:click="greet">Greet</button>
    <butt
    ```

    ```js
    // ...
    methods: {
      greet: function(event) {
        // ...
      }
    }
    ```

+ **处理器中访问原始 DOM 事件**，用特殊变量`$event`传入方法：

    ```html
    <button v-on:click="warn('Cannot be submitted yet.', $event)">
      Submit
    </button>
    ```

    ```js
    // ...
    methods: {
      warn: function(message, event) {
        // 第二个参数就是传进来的原生事件对象
      }
    }
    ```

    





# 表单输入绑定

`v-model`



# 组件

## 组件基础

+ **组件是带有名字的可复用的 Vue 实例**：

    以下是一个 Vue 组件的示例：

    ```jsx
    // 定义一个名为 button-counter 的新组件
    Vue.component('button-counter', {
      data: function() {
        return {
          count: 0
        }
      },
      template: 
      	'<button v-on:click="count++">
      		You clicked me {{ count }} times
    		</button>'
    })
    ```

+ 可以在一个通过<span style="color:red">`new Vue`创建的 Vue 根实例</span>中将这个组件**作为自定义元素使用**：

    ```jsx
    // HTML:
    <div id="components-demo">
      <button-counter></button-counter>	
    </div>
    
    // JS
    new Vue({ el: '#components-demo' })
    ```

+ 因为组件是可复用的 Vue 实例，它们**与`new Vue`接收相同选项**(*如 `data`、`computed`、`watch`、`methods` 以及生命周期钩子* )，除了`el`这种根实例特有选项

+ **`data`必须是个函数：**

    这样规定保证每个实例可以维护一份<span style="color:red">被返回对象的独立拷贝</span>



+ <span style="font-size:22px">组件的组织：</span>

    通常一个应用会以一棵嵌套组件树形式来组织

    例如：我们可能会有页头、侧边栏、内容区等组件，每个组件又包含了其它的像导航链接、博文之类的组件：<img src="https://gitee.com/ethereal-bang/images/raw/master/20211022224446.png" alt="image-20211022224446602" style="zoom:50%;" />

    为了能在模板中使用，这些组件必须先注册以便 Vue 能够识别。组件有两种注册类型：<span style="color:red">全局注册</span>和<span style="color:red">局部注册</span>

    **全局注册组件使用场景：**通过`Vue.component`全局注册的组件可以用作其被注册之后的任何 Vue 根实例==根实例不是有且仅有一个吗？==，也包括组件树中所有子组件模板中



+ <span style="font-size:22px">通过 Props 向子组件传递数据：</span>

    **Prop：** 是可以在组件上注册的一些自定义 **attribute**，当值传递给该组件时就变成了一个 **property**

    **基础用法（传递静态值）：**

    ```jsx
    Vue.component('blog-post', {
      props: ['title'],
      template: '<h3>{{ title }}</h3>'
    })
    // HTML
    <blog-post title="My journey with Vue"></blog-post>
    <blog-post title="Blogging with Vue"></blog-post>
    ```

    **典型应用（动态赋值）：**开始并不清楚要渲染的具体内容时（比如从 API 获取博文列表）非常有用，**利用`v-bind`动态传递 prop**：

    ```jsx
    new Vue({
      el: '#blog-post-demo',
      data: {
        posts: [
          { id: 1, title: 'My journey with Vue' },
          { id: 2, title: 'Blogging with Vue' },
          { id: 3, title: 'Why Vue is so fun' }
        ]
      }
    })
    
    // HTML
    <blog-post
      v-for="post in posts"
      v-bind:key="post.id"
      v-bind:title="post.title"
    ></blog-post>
    ```



## 组件注册

+ <span style="font-size:22px">组件名：</span>

    **直接在 DOM 中使用一个组件时** (而不是在字符串模板或[单文件组件](https://cn.vuejs.org/v2/guide/single-file-components.html)) ，Vue.js 强烈推荐遵循 [W3C 规范](https://html.spec.whatwg.org/multipage/custom-elements.html#valid-custom-element-name)中的自定义组件名 (**字母全小写且必须包含一个连字符**)。这会帮助避免和 HTML 元素相冲突

    

+ <span style="font-size:22px">全局注册</span>

    例如：

    ```jsx
    Vue.component('component-a', { /* ... */ })
    Vue.component('component-b', { /* ... */ })
    Vue.component('component-c', { /* ... */ })
    
    new Vue({ el: '#app' })
    
    // HTML
    <div id="app">
      <component-a></component-a>
      <component-b></component-b>
      <component-c></component-c>
    </div>
    ```

    这些组件是全局注册的，可以用在任何新创建的 Vue 根实例模板中，在其所有子组件中也是如此，（即三个组件各自内部也都可以相互使用）

    **全局注册的行为必须在根 Vue 实例 (通过 `new Vue`) 创建之前发生**

+ <span style="font-size:22px">局部注册：</span>

    **全局注册往往不够理想：**意味着即使不再使用某一组件，其仍会被包含再最终 Webpack 构建结果，造成用户下载 JS 的无谓增加。

    **定义局部注册的组件**：通过普通的 JS 对象定义：

    ```jsx
    let ComponentA = { /* ... */ }
    let ComponentB = { /* ... */ }
    
    new Vue({
      el: '#app',
      components: {
        'component-a': ComponentA,
        'component-b': ComponentB
      }
    })
    ```

    对于**`components`对象**每个 property，property 名为自定义元素名，值为组件的选项对象

​		局部注册组件**在其子组件不可用**



## Prop

==。。。==



## 自定义事件

+ <Span style="font-size:22px">事件名：</span>

    不同于组件和 Prop，事件名不存任何自动化大小写转换；又因为`v-on`在 DOM 模板会自动转换为小写

    因此始终推荐**使用 kebab-case 的事件名**(*连字符* )



+ <span style="font-size:22px">自定义组件的`v-model`：</span>==？==

    一个组件上的 `v-model` 默认会利用名为 `value` 的 prop 和名为 `input` 的事件，但是像单选框、复选框等类型的输入控件可能会将 `value` attribute 用于不同的目的	==？==

    **`model`选项**可用来避免这样的冲突：

    ```vue
    Vue.component('base-checkbox', {
      model: {
        prop: 'checked',
        event: 'change',
      },
      props: {
        checked: Boolean
      },
      template: `
    		<input
          type="checkbox"
          v-bind:checked="checked"
          v-on:change="$emit('change', $event.target.checked)"
        >
    	`
    })
    
    // 现在在这个组件使用`v-model`时：
    <base-checkbox v-model="lovingVue"></base-checkbox>
    ```

    这里的`loginVue`值会传入这个名为`checked`的 prop，同时`<base-checkbox>`会触发一个`change`事件并附带一个新的值时，这个`loginVue`的property 将被更新



## 插槽

Vue 将**`<slot>`**元素作为承载分发内容的出口



### 插槽内容

==？==允许像这样合成组件：

```jsx
<navigation-link url="/profile">
  Your Profile
</navigation-link>
```

然后在 `<navigation-link>` 的模板中可能会写为：

```jsx
<a
  v-bind:href="url"
  class="nav-link"
>
  <slot></slot>
</a>
```

当组件渲染时，`<slot></slot>`将会被替换成”Your Profile“。



插槽内可包含任何模板代码，包括 HTML、其余组件



### 默认内容

有时给插槽设置具体默认内容是很有用的，它只会在没有提供内容的时候被渲染



+ 例如在一个`<submit-button>`组件中：

    我们希望这个`<button>`内绝大多数情况都渲染文本"Submit"，为了将其作为默认内容将它放入`<slot>`标签内：

    ```jsx
    <button type="submit">
    	<slot>Submit</slot>
    </button>
    ```

    现在在一个父级组件中使用`<submit-button>`且不提供任何插槽内容：

    ```jsx
    <submit-button></submit-button>
    ```

    后备内容”Submit“将会被渲染。



### 具名插槽

**使用场景：**有时一个组件内需要多个插槽：

```jsx
<div class="container">
  <header>
    <!-- 我们希望把页头放这里 -->
  </header>
  <main>
    <!-- 我们希望把主要内容放这里 -->
  </main>
  <footer>
    <!-- 我们希望把页脚放这里 -->
  </footer>
</div>
```

对于这样的情况，**`<slot>` 元素有一个特殊的 attribute：`name`**。这个 attribute 可以用来定义额外的插槽：

```jsx
<div class="container">
  <header>
    <slot name="header"></slot>
  </header>
  <main>
    <slot></slot>
  </main>
  <footer>
    <slot name="footer"></slot>
  </footer>
</div>
```

**向具名插槽提供内容**时：可以在一个`<template>` 元素上使用 **`v-slot`** 指令，并以 `v-slot` 的参数的形式提供其名称，任何没有包裹在带有 `v-slot` 的 `<template>` 中的内容都会被视为默认插槽的内容

```jsx
<base-layout>
  <template v-slot:header>
    <h1>Here might be a page title</h1>
  </template>

  <p>A paragraph for the main content.</p>
  <p>And another one.</p>

  <template v-slot:footer>
    <p>Here's some contact info</p>
  </template>
</base-layout>
```



### 作用域插槽

**使用场景：**让插槽内容能够访问子组件中才有的数据

实例：

使用含插槽的组件`<current-user>`时，

```jsx
<current-user>
  {{ user.firstName }}
</current-user>
```

上述代码不会正常工作，因为只有 `<current-user>` 组件可以访问到 `user`，而我们提供的内容是在父级渲染的。

为了让`user`在父级的插槽中可用，将`user`作为`<slot>`元素的一个 attribute 绑定上去：

```jsx
<span>
  <slot v-bind:user="user">
    {{ user.lastName }}
  </slot>
</span>
```

绑定在`<slot>`元素上的 attribute 称为**插槽 prop**。现在在父级作用域中可以使用**`v-slot`**定义我们提供的插槽 prop 的名字：

```jsx
<current-user>
  <template v-slot:default="slotProps">
    {{ slotProps.user.firstName }}
  </template>
</current-user>
```

这里将包含所有插槽 prop 的对象命名为`slotProps`，但也可以使用其他名字。



## 动态组件 & 异步组件



## 处理边界情况



# 过渡 & 动画

Vue 在插入、更新或者移除 DOM 时，提供多种不同方式的应用过渡效果。包括以下工具：

- 在 CSS 过渡和动画中自动应用 class
- 可以配合使用第三方 CSS 动画库，如 Animate.css
- 在过渡钩子函数中使用 JavaScript 直接操作 DOM
- 可以配合使用第三方 JavaScript 动画库，如 Velocity.js

==。。。==



# 可复用性 & 组合

## 混入 Mixin

混入 (mixin) 提供了一种非常灵活的方式，来分发 Vue 组件中的可复用功能。一个混入对象可以包含任意组件选项。当组件使用混入对象时，所有混入对象的选项将被“混合”进入该组件本身的选项



## 自定义指令



## 钩子函数



## 渲染函数 & JSX

有时比起模板语法，使用 JS 的完全编程，`render`函数更合适。

```jsx
<script type="text/x-template" id="anchored-heading-template">
  <h1 v-if="level === 1">
    <slot></slot>
  </h1>
  <h2 v-else-if="level === 2">
    <slot></slot>
  </h2>
  <h3 v-else-if="level === 3">
    <slot></slot>
  </h3>
  <h4 v-else-if="level === 4">
    <slot></slot>
  </h4>
  <h5 v-else-if="level === 5">
    <slot></slot>
  </h5>
  <h6 v-else-if="level === 6">
    <slot></slot>
  </h6>
</script>
```

```jsx

Vue.component('anchored-heading', {
  render: function (createElement) {
    return createElement(
      'h' + this.level,   // 标签名称
      this.$slots.default // 子节点数组
    )
  },
  props: {
    level: {
      type: Number,
      required: true
    }
  }
})
```

可以看出，这个场景用模板代码冗长，且每一个级别的标题中重复书写了`<slot></slot>`。使用`render`函数精简很多，需要非常熟悉 Vue 的 [实例 property](https://cn.vuejs.org/v2/api/#%E5%AE%9E%E4%BE%8B-property)。



# 规模化

## 路由

对于大多数单页面应用，推荐使用[vue-router 库](https://router.vuejs.org/zh/installation.html)。

+ <span style="font-size:20px">使用方式：</span>

    ```bash
    npm i vue-router
    ```

    ```js
    import VueRouter from "vue-router";
    Vue.use(VueRouter);	// 明确安装路由功能
    ```



使用 Vue.js ，已经可以通过**组合组件来组成应用程序**；

要把 Vue Router 添加进来，我们需要做的是：将 components 映射到路由 (*routes* )，然后告诉 Vue Router 在哪里渲染它们



## 状态管理——Vuex

如果应用简单，一个简单的 store 模式足够；但构建一个中大型单页应用，Vuex能更好地在组件外部管理状态

状态自管理应用包含几个部分：

+ **state：**驱动应用的**数据源**
+ **view：**以声明方式将 state 映射到视图
+ **actions：**响应在 view 上的用户输入导致的状态变化

<hr>

+ <span style="font-size:20px">安装：</span>

    ```bash
    npm i vuex --save
    ```

    ```js
    import Vuex from 'vuex';
    Vue.use(Vuex);
    ```

+ <span style="font-size:20px">Demo：</span>

    1. **创建 store**

        需提供`state`——初始化状态、`mutations`——触发突变

        ```js
        import Vue from 'vue'
        import Vuex from 'vuex'
        
        Vue.use(Vuex)
        
        const store = new Vuex.Store({
          state: {
            count: 0
          },
          mutations: {
            increment (state) {
              state.count++
            }
          }
        })
        ```

    2. **`store.state`**获取状态对象、**`store.commit`**触发状态变更

        ```js
        store.commit('increment')	// 改变 state
        
        console.log(store.state.count) // -> 1
        ```

    3. **注入 store**

        从根组件向所有子组件，以 `store` 选项的方式注入：

        ```js
        new Vue({
          el: '#app',
          store: store,
        })
        ```

    4. **从组件的方法中提交变更**：

         Vue 组件中访问 `this.$store` property

        ```js
        methods: {
          increment() {
            this.$store.commit('increment')
            console.log(this.$store.state.count)
          }
        }
        ```



### Action

Action 类似于 mutation，不同在于：

- Action 提交的是 mutation，而不是直接变更状态。
- Action 可以包含任意异步操作。

<hr>

+ <span style="font-size:20px">Demo：</span>

    ```js
    const store = new Vuex.Store({
      state: {
        count: 0
      },
      mutations: {
        increment (state) {
          state.count++
        }
      },
      actions: {
        increment (context) {
    ####      context.commit('increment')
        }
      }
    })
    ```

    

Action 函数接受一个与 store 实例具有相同方法和属性的 context 对象



# Vue 的网络应用

现在很少有纯本地应用，或多或少都会进行网络数据的交互

Vue 结合网络数据开发应用



## axios + Vue

首先打包，官网地址：

`<script src="https://unpkg.com/axios@0.21.1/dist/axios.min.js"><script>`









# 参考链接

+ ：

    [介绍——Vue.js](https://cn.vuejs.org/v2/guide/)

+ Vue 配置：

    [推荐-Vue项目目录结构 | springleo`s blog](https://lq782655835.github.io/blogs/team-standard/recommend-vue-project-structure.html)

    [VUE项目中，html 的CSS写在哪里比较好？- SegmentFault 思否](https://segmentfault.com/q/1010000022159166)

    [Vue 项目CSS组织 - 简书](https://www.jianshu.com/p/8defdc61ae00)

    [vue.js - What is the purpose of main.js & App.vue in Vue App - Stack overflow](https://stackoverflow.com/questions/58972232/what-is-the-purpose-of-main-js-app-vue-in-vue-app)
    
    [Index.html 和 main.js是怎么关联起来的 - 中文 - Vue Forum](https://forum.vuejs.org/t/index-html-main-js/39778/5)
    
+ 规模化：

    [Vue Router](https://router.vuejs.org/zh/guide/#html)

