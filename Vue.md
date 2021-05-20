#  Vue基础



## 创建一个Vue程序

代码实现浏览器显示：`hello,vue`

步骤：

1. 导入开发版本的`Vue.js`：https://cdn.jsdelivr.net/npm/vue/dist/vue.js
2. 创建`Vue`实例对象，设置`el`和`data`属性
3. 使用简介的**模板语法**把数据渲染到页面

```html
<body>
  <div id="app">
    {{ message }}
  </div>

  <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
  <script>
    var app = new Vue({	
      el:"#app",	// #为id选择器
      data:{
        message:" hello,vue "
      }
    })
  </script>
</body>
```

空格只是格式



## el 挂载点

Vue的**作用范围**：

```html
<body>
  {{ message }}
    
  <div id="a">
    {{ message }}
    <span>{{ message }}</span>
  </div>
    
  <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
  <script>
    let app = new Vue({
      el:"#a",
      data:{
        message:" Hello "
      }
    })
  </script>  
        
</body>
```

以上代码在浏览器中运行显示：

``` 
{{ message }}
Hello Hello
```

因此，`Vue`会作用于`el`选项命中的元素及其内部的后代元素



是否**支持其他选择器**：

```html
<body>
  {{ message }}
    
  <div id="a" class="b">
    {{ message }}
    <span>{{ message }}</span>
  </div>
    
  <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
  <script>
    let app = new Vue({
      el:".b",
      data:{
        message:" Hello "
      }
    })
  </script>  
        
</body>
```

浏览器中显示效果不变。

因此，`el`属性支持其他选择器，但建议使用 ID 选择器



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



# Vue 的本地应用

通过 Vue 提供的各种指令，对本地的数据进行操作

## v 指令

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



## Vue 的网络应用

现在很少有纯本地应用，或多或少都会进行网络数据的交互

Vue 结合网络数据开发应用



### axios	**?**

首先打包，官网地址：

`<script src="https://unpkg.com/axios@0.21.1/dist/axios.min.js"><script>`



1. axios 必须先导入再使用
2. 使用 get 或 post 方法即可发送对应的请求
3. then 方法中的回调函数会在请求成功或失败时触发
4. 通过回调函数的1形参可获取响应内容或错误信息



[axios详细介绍](https://github.com/axious/axious)



## axios + Vue

axios如何结合Vue获取网络应用。



