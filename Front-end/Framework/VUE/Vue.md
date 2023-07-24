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



## Vue 安装

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



## 项目配置

+ <span style="font-size:22px">vue.config.js：</span>

    + **改变默认端口号：**

        ```js
        const {defineConfig} = require('@vue/cli-service')
        module.exports = defineConfig({
            transpileDependencies: true,
        #    devServer: {
        #        port: 8081, // 不与java后端端口号8080冲突
        #    }
        })
        ```




# VUE3

**API 风格：**

V3 两种 API 风格。Options API VS Composition API，Options API 与 V2 差异不大，Composition API 与 React 风格类似



**Composition API:**

```vue
<script setup>
import { ref, onMounted } from 'vue'

// 响应式状态
const count = ref(0)

// 修改状态、触发更新
function increment() {
  count.value++
}

// 生命周期钩子
onMounted(() => {
  console.log(`The initial count is ${count.value}.`)
})
</script>

<template>
  <button @click="increment">Count is: {{ count }}</button>
</template>
```



<span style="font-size:20px">script setup</span>

**执行时机：**

+ `<script>`： 只在组件被首次引入的时候执行一次

+ `<script setup>` 每次组件实例被创建的时执行



## Composition API

使用导入的 API 函数来描述组件逻辑，通常与 [`<script setup>`](https://cn.vuejs.org/api/sfc-script-setup.html) 搭配使用

### ref

`ref()` 将传入参数的值包装为一个带 `.value` 属性的 ref 对象

```vue
<template>	
	<div ref="el" />
</template>

<script setup>
import{ ref } from "vue";
    
const cnt = ref(1);
const el = ref();
el.value // 该元素
</script>
```



### 生命周期钩子

<span style="font-size:20px">onMounted, onUnmounted</span>

```vue
<script setup>
import { ref, onMounted } from 'vue'

const el = ref()
onMounted(() => {
  el.value // <div>
})
</script>

<template>
  <div ref="el"></div>
</template>
```




#  Vue 基础

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

```vue
<!--Options API-->
<script>
export default {
	data() {
        return {
            msg: "Foo",
        }	
    }
}
</script>

<!--Composition API-->
<script setup>
import { ref } from "vue";
const msg = ref("Foo");
</script>
```



## v 指令

通过 Vue 提供的各种指令，在渲染的 DOM 上应用特殊地响应式行为

+ <span style="font-size:20px">v-text  / v-html 设置 innerText / innerHTML：</span>

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

    

+ <span style="font-size:22px">v-on / @ 为元素绑定事件：</span>

    作用：为元素绑定事件

    ```vue
    <div id="a">
        <input type="button" value="事件绑定" v-on:click="show('传的参数')">
        <input type="button" value="事件绑定" v-on:monseenter="show">	// 鼠标移入事件
        <input type="button" value="事件绑定" v-on:click="show">	// 双击事件
        <input type="button" value="事件绑定" @click="show">
    </div>
    ```

    总结：

    + `v-on`指令的作用是：为元素绑定事件
    + 事件名不需写 on   *click*
    + 指令可简写为`@:`
    + 绑定的方法定义在`methods`属性中
    + 方法内部可通过`this`关键字访问定义在`data`中数据

    + 可传自定义参数，事件修饰符



+ <span style="font-size:22px">v-if / v-show：</span>

    **本质区别**：

    `v-show`改变元素的`display`属性,而`v-if`通过直接增删 dom 元素实现

    `v-if`对性能消耗较大，需要频繁更改的元素则使用`v-show`。

    

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

        

+ <span style="font-size:22px">v-for 列表渲染：</span>

    根据数据生成列表结构

    ``` vue
    <template>
        <ul id="app">
          <li v-for="index in arr">
            {{ index }}
          </li>
        </ul>
    </template>
    ```

    

+ <span style="font-size:22px">v-model 双向数据绑定：</span>

    **使用场景：**

    用法1：设置及获取<span style="color:orange">表单元素</span>的值

    用法2：<span style="color:orange">组件</span>

    ```vue
    <!-- CustomInput.vue -->
    <script setup>
    defineProps(['val'])
    defineEmits(['update:val'])
    </script>
    
    <template>
      <input
        :value="val"
        @input="$emit('update:val', $event.target.value)"
      />
    </template>
    
    <!-- Parent.vue -->
    <CustomInput v-model="text" />
    ```

    **多个 v-model 绑定**：

    ```vue
    <UserName
      v-model:first-name="first"
      v-model:last-name="last"
    />
    ```



## 方法

```vue
<script>
export default {
	methods: {
        add() {}
    },
    mounted() {	// 可在生命周期中调用方法
        this.add();
    }
}
</script>
```



# Vue 实例

## 创建 Vue 实例

每个 Vue 应用都是通过`new Vue`创建新的 Vue 实例开始：

```jsx
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
let data = { 
  a: 1, 
	fn,	// 可以是一个函数
}

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



## 计算属性  computed

> 对于任何复杂逻辑都应使用计算属性。

```jsx
let vm = new Vue({
  el: '#example',
  computed: {
    reversedMessage: function() {	// 为计算属性,使用同data
      return this.message.split('').rever().join('');
    }
  }
})
```

**setter, getter: **

> 为计算属性赋值需设置 setter

```vue
<script setup>
	const fullName = computed({
        get() {
            return firstName.value + ' ' + lastName.value;
        },
        set(newVal) {
            [firstName.value, lastName.value] = newValue.split(' ');
        },
    })
</script>
<script>
	computed: {
        foo: {
            get(){},
            set(){},
        }
    }
</script>
```



## watch

监听响应式变量的变化

默认数据源变化时执行回调，传入 `immediate: true` 获得初始数据 

```js
watch(source, (newValue, oldValue) => {
  // 立即执行，且当 `source` 改变时再次执行
}, { immediate: true })
```



# .vue

## refs

使用 ref 获取 DOM 元素

> VUE 不建议直接操作 DOM 元素

```vue
<!-- 设置dom元素,设置ref属性 -->
<h3 ref="test">dom元素中的内容</h3>

<!-- 在Vue方法中调用使用`this.$refs`来获取dom元素 -->
this.$refs.test
```



## 组件基础

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



## Props

与 TypeScript:

```vue
<script setup lang="ts">
interface Props {
    foo: string,
	bar?: number[],
}
const props1 = defineProps<Props>();
// 声明默认值
const props2 = widthDefaults(defineProps<Props>(), {
    foo: 'foo',
    bar: () => [0, 1],
})        
</script>
```



## 自定义事件

推荐**使用 kebab-case 的事件名**

<span style="color:blue">Eg:</span>

```vue
<!-- Child.vue -->
<script setup>
const emit = defineEmits(["update"]);
watch(
	foo,
    value => emit(value);
)
</script>
<!-- Parent.vue -->
<component @update="val => handleUpdate(val)"/>
```



# style

**引入 Sass：**

```shell
npm i node-sass sass-loader -D
```

```vue
<style lang="scss">
</style>
```



<span style="font-size:20px">[Scoped CSS](https://vue-loader.vuejs.org/zh/guide/scoped-css.html)</span>

scoped 属性的 `<style>` 标签的 CSS 只作用于当前组件中元素。父组件的样式将不会渗透到子组件中

**深度作用选择器：**

希望一个选择器作用得更深，影像子组件时

```css
.a >>> .b { /*...*/ }
/* 编译为 .a[data-v-f3f3eg9] .b */
```

> **`/deep/` / `::v-deep`：**
>
> 有些 Sass 之类的预处理器无法正确解析 `>>>`，可用以上两种代替



# 可复用性 & 组合

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

可以看出，这个场景用模板代码冗长，且每一个级别的标题中重复书写了`<slot></slot>`。使用`render`函数精简很多



# 内置特殊元素

## \<component>

渲染动态组件，渲染的实际组件由 `is` prop 决定

```typescript
interface DynamicComponentProps {
  is: string | Component
}
```

<span style="color:blue">Eg:</span>

```jsx
<!-- 渲染组件 -->
<component :is="Math.random() > 0.5 ? Foo : Bar" />

<!-- 渲染组件 -->
<component :is="href ? 'a' : 'span'" />
```



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



# [vue-property-decorator](https://github.com/kaorun343/vue-property-decorator)

VUE + TS 开发时，使用装饰器简化书写的库

```typescript
import {Vue, Component} from "vue-property-decorator";
@Component({})
export default class Foo extends Vue {}
```

**computed:**

```js
get fullname() {
    return this.familyName + this.lastName;
}
```

**watch:**

```js
@Watch('modifyForm.color')
onChange(newVal, val) {}
```



# 开箱即用组件

## vue-easy-slider

[vue-easy-slider](https://link.juejin.cn/?target=https%3A%2F%2Fgithub.com%2Fshhdgit%2Fvue-easy-slider) 是一个简单的 Vue 滑块组件，可与鼠标和触摸屏一起使用。可自定义，并带有动画效果

+ **安装：**

    ```shell
    npm i -S vue-easy-slider
    ```

+ **使用:**

    ```vue
    <template>
      <Slider
        v-model="sliderValue"
        animation="fade"
        :duration="5000"
        :speed="2000"
      >
        <SliderItem
          v-for="(i, index) in list"
          :key="index"
          @click="changeIndex(1);"
          :style="i"
        >
          <p style="line-height: 280px; font-size: 5rem; text-align: center;">
            Page{{ index + 1 }}
          </p>
        </SliderItem>
      </Slider>
    </template>
    
    <script>
    import {Slider, SliderItem} from "vue-easy-slider";
    
    export default {
      name: "Carousel",
      components: {
        Slider,
        SliderItem,
      },
      data() {
        return {
          list: [
            {
              backgroundColor: "#3f51b5",
              width: "100%",
              height: "100%"
            },
            {
              backgroundColor: "#eee",
              width: "100%",
              height: "100%"
            },
            {
              backgroundColor: "#f44336",
              width: "100%",
              height: "100%"
            },
          ],
          sliderValue: 1
        };
      },
      methods: {
        changeIndex(index) {
          this.sliderValue = index;
        }
      }
    };
    </script>
    
    <style scoped>
    </style>
    ```

+ **Slider 组件配置项:**

    `:speed`——切换速度

    `animation: "fade"`——渐变



## [Element Plus](https://element-plus.gitee.io/zh-CN)

**安装：**`npm i element-plus`



<span style="font-size:20px">按需引入：</span>

1. **安装插件：** 

    ```shell
    npm install -D unplugin-vue-components unplugin-auto-import
    ```

2. **修改配置文件：**

    ```js
    // vite.config.js
    import AutoImport from 'unplugin-auto-import/vite'
    import Components from 'unplugin-vue-components/vite'
    import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'
    
    plugins: [
      [
          AutoImport({
            resolvers: [ElementPlusResolver()],
          }),
          Components({
            resolvers: [ElementPlusResolver()],
          }),
      ]
    ],
    ```

3. **在 template 中直接使用组件**



# REF

：

[介绍——Vue.js](https://cn.vuejs.org/v2/guide/)

Vue 配置：

[推荐-Vue项目目录结构 | springleo`s blog](https://lq782655835.github.io/blogs/team-standard/recommend-vue-project-structure.html)

[VUE项目中，html 的CSS写在哪里比较好？- SegmentFault 思否](https://segmentfault.com/q/1010000022159166)

[Vue 项目CSS组织 - 简书](https://www.jianshu.com/p/8defdc61ae00)

[vue.js - What is the purpose of main.js & App.vue in Vue App - Stack overflow](https://stackoverflow.com/questions/58972232/what-is-the-purpose-of-main-js-app-vue-in-vue-app)

[Index.html 和 main.js是怎么关联起来的 - 中文 - Vue Forum](https://forum.vuejs.org/t/index-html-main-js/39778/5)

规模化：

[Vue Router](https://router.vuejs.org/zh/guide/#html)

