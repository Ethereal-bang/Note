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



### v-text	重置标签文本

在标签内部使用，重置标签的文本值(*textcontent*)

```vue
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



### v-html 设置元素 innerHTML

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



### v-on 为元素绑定事件

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

### v-show 切换元素显示状态

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



### v-if 切换元素显示状态

根据表达式的真假，切换元素的显示和隐藏（操纵 dom 元素）



与`v-show`的**本质区别**：

`v-show`改变元素的`display`属性,而`v-if`通过直接增删 dom 元素实现

`v-if`对性能消耗较大，需要频繁更改的元素则使用`v-show`。



###  v-bind 设置元素属性

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

  

### v-for 操纵列表

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



### v-model 双向数据绑定

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



## 数据与方法

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



# Vue 的网络应用

现在很少有纯本地应用，或多或少都会进行网络数据的交互

Vue 结合网络数据开发应用



## axios

首先打包，官网地址：

`<script src="https://unpkg.com/axios@0.21.1/dist/axios.min.js"><script>`



1. axios 必须先导入再使用
2. 使用 get 或 post 方法即可发送对应的请求
3. then 方法中的回调函数会在请求成功或失败时触发
4. 通过回调函数的形参可获取响应内容或错误信息



[axios详细介绍](https://github.com/axious/axious)



## axios + Vue

axios如何结合Vue获取网络应用。



