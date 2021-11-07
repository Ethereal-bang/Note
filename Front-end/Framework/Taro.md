# Taro 介绍

## 简介

Taro 是一个开放式跨段跨框架解决方案，支持使用 React/Vue/Nerv 等框架来开发 微信 / 京东 / 百度 / 支付宝 / 字节跳动 / QQ 小程序 / H5 / RN(*React Native[^ 1]*) 等应用——只编写一套代码就能适配到多端



## Taro UI

Taro 3 只能配合使用 **taro-ui@next** 版本，安装命令：

```
npm i taro-ui@next
```



**[Taro UI](https://taro-ui.jd.com/#/)特性**：

+ 基于 Taro 开发 UI 组件
+ 一套组件可在多端适配运行（React Natvie 端暂不支持）
+ 提供友好的 API，可灵活使用组件



## 环境准备

目前 Taro 仅提供一种开发方式：安装 Taro 命令行( *Taro CLI* )进行开发



1. 安装 **Node.js** 环境

2. 安装 **Taro CLI**：

    ```
    npm i -g @tarojs/cli
    ```

    安装完毕后输入`taro`出现相关信息说明安装成功

3. 可使用**`taro init`**创建一个全新项目

    最小版本的 Taro 项目会包括以下文件

    ```
    ├── babel.config.js             # Babel 配置
    ├── .eslintrc.js                # ESLint 配置
    ├── config                      # 编译配置目录
    │   ├── dev.js                  # 开发模式配置
    │   ├── index.js                # 默认配置
    │   └── prod.js                 # 生产模式配置
    ├── package.json                # Node.js manifest
    ├── dist                        # 打包目录
    ├── project.config.json         # 小程序项目配置
    ├── src # 源码目录
    │   ├── app.config.js           # 全局配置
    │   ├── app.css                 # 全局 CSS
    │   ├── app.js                  # 入口组件
    │   ├── index.html              # H5 入口 HTML
    │   └── pages                   # 页面组件
    │       └── index
    │           ├── index.config.js # 页面配置
    │           ├── index.css       # 页面 CSS
    │           └── index.jsx       # 页面组件，如果是 Vue 项目，此文件为 index.vue
    ```

    ![image-20211014085949767](https://gitee.com/ethereal-bang/images/raw/master/20211014085956.png)



## 运行 Taro 项目

+ **微信小程序**
    选择微信小程序模式，需要自行下载并打开**微信开发者工具**，然后选择项目根目录进行预览。
    微信小程序编译预览及打包

    ```
    $ npm run dev:weapp
    $ npm run build:weapp
    ```
    
    微信开发者工具需要的是端代码，即项目内`src/dist`文件夹。该文件夹内是转换后的代码
    
+ **H5**
    H5 模式，无需特定的开发者工具，在执行完下述命令之后即可通过浏览器进行预览
    H5 编译预览及打包

    ```
    $ npm run dev:h5
    ```
    
+ **React Native**
    React Native 端运行需执行如下命令，React Native 端相关的运行说明请参见 React Native 教程

    ```
    $ npm run dev:rn
    ```
    
+ **百度小程序**
    选择百度小程序模式，需要自行下载并打开百度开发者工具，然后在项目编译完后选择项目根目录下 dist 目录进行预览。
    百度小程序编译预览及打包

    ```
    $ npm run dev:swan
    $ npm run build:swan
    ```
    
    
    
+ **支付宝小程序**
    选择支付宝小程序模式，需要自行下载并打开支付宝小程序开发者工具，然后在项目编译完后选择项目根目录下 dist 目录进行预览。
    支付宝小程序编译预览及打包

    ```
    $ npm run dev:alipay
    $ npm run build:alipay
    ```
    
    

Taro **更新**较快，要记得更新Taro项目

Taro 提供了更新命令来更新 CLI 工具自身和项目中 Taro 相关的依赖
更新 Taro CLI 工具

```crystal
# taro
$ taro update self
# npm
npm i -g @tarojs/cli@latest
# yarn
yarn global add @tarojs/cli@latest
```



# 基础教程

## 目录结构

和小程序规范一样，Taro 包含一个描述整体程序的`app`和多个描述各自页面的`page`



+ <span style="font-size:22px">app</span>

    ```
    └── src                         源码目录
        ├── app.js                  项目入口逻辑
        ├── app.css                 项目全局样式
        └── app.config.js           项目入口配置
    ```

    + **小程序全局样式**：

        该文件可通过`import`引入：

        ```jsx
        import './app.css'
        ```

+ <span style="font-size:22px">page</span>

    ```
    └── src                         
        └── pages                   
            └── index               
                ├── index.js        index 页面逻辑
                ├── index.css       index 页面样式
                └── index.config.js index 页面配置
    ```

    + **页面路由**：

        页面路由需要在小程序全局配置`app.config.js`中进行配置

        所有在`pages`字段出现的都是页面，其余都会编译为 component

        例如：
        
        ```jsx
        // app.config.js
        export default {
          pages: [
            'pages/index/index',
            'pages/logs/logs'
          ]
        }
        ```
        
        

## 全局配置

`app.config.js`文件用于对小程序进行全局配置



### 通用配置项

在 H5、React Native、所有小程序均支持的配置

| 属性   | 类型         | 必填 | 描述               |
| ------ | ------------ | ---- | :----------------- |
| pages  | String Array | 是   | 页面路径列表       |
| window | Object       | 否   | 全局的默认窗口表现 |
| tabBar | Object       | 否   | 底部 tab 栏的表现  |

+ <span style="font-size:22px">[pages](https://docs.taro.zone/docs/app-config#pages)</span>

    **页面的路径列表**——指定小程序由哪些页面构成，数组的每一项对应一个页面的路径信息，文件名不需要后缀

    数组的第一项代表小程序的初始页面(*首页* )。小程序中新增/减少页面，都需要对`pages`数组进行修改

    ```js
    export default {
      pages: [
        'pages/index/index',
        'pages/logs/logs'
      ]
    }
    ```

+ <span style="font-size:20px">[window](https://docs.taro.zone/docs/app-config#window)</span>

    **全局的默认窗口表现——**设置小程序的状态栏、导航条、标题、窗口背景色

+ <span style="font-size:22px">[tabBar](https://docs.taro.zone/docs/app-config#tabbar)</span>

    **底部 tab 栏的表现**——如果小程序是多 tab 应用[^ 2]

    必填配置项：

    | 属性                | 类型                       | 默认值 | 描述                                                     |
    | ------------------- | -------------------------- | ------ | -------------------------------------------------------- |
    | **color**           | HexColor（十六进制颜色值） |        | tab 上的文字默认颜色，仅支持十六进制颜色                 |
    | **selectedColor**   | HexColor（十六进制颜色值） |        | tab 上的文字选中时的颜色，仅支持十六进制颜色             |
    | **backgroundColor** | HexColor（十六进制颜色值） |        | tab 的背景色，仅支持十六进制颜色                         |
    | **borderStyle**     | String                     | black  | tabbar 上边框的颜色， 仅支持 black / white               |
    | **list**            | Array                      |        | tab 的列表，详见 list 属性说明，最少 2 个、最多 5 个 tab |

    其中`list`接受一个数组，配置最少 2 个、最多 5 个 tab，必填属性值如下：

    | 属性     | 类型   | 描述                            |
    | -------- | ------ | ------------------------------- |
    | pagePath | String | 页面路径，必须在 pages 中先定义 |
    | text     | String | tab 上按钮文字                  |



### 小程序端特有属性



## 页面配置

每一个小程序页面都可以使用 **`.config.js`** 文件来对本页面的窗口表现进行配置。页面中配置项在当前页面会覆盖全局配置 `app.config.json` 的 `window` 中相同的配置项

文件需要 `export` 一个默认对象



## [项目配置](https://docs.taro.zone/docs/project-config)



# Taro 中 React 的差异

Taro 支持将 Web 框架直接运行在各平台，开发者使用的是真实的 React 和 Vue 等框架

但 Taro 在组件、API、路由等规范上遵循微信小程序规范，所以在 Taro 中使用 React 和 Web 端有一些差异，以下会详细列出



## React API

React 的 API 如 `Component`、`useState`、`useEffect` 等都需要从 React 包中获取

```jsx
import React, { Component, useState, useEffect } from 'react'
```



## 入口组件、页面组件

Taro 引入[入口组件](https://docs.taro.zone/docs/react-entry)和[页面组件](https://docs.taro.zone/docs/react-page)的概念，分别对应小程序规范的入口组件 `app` 和页面组件 `page`



## 内置组件

Taro 中可以使用小程序规范的内置组件开发，如 `<View>`、`<Text>`、`<Button>` 等



+ <span style="font-size:20px">Taro 规范</span>：
    1. React 中使用这些内置组件前，必须从**`@tarojs/components`**引入
    2. 组件属性遵从**大驼峰式命名规范**
    3. 事件规范



## 事件

事件和 Web 端一样，事件回调函数中，第一个参数是事件对象，回调中调用`stopPropagation`可以阻止冒泡



+ <span style="font-size:20px">Taro 规范</span>
    1. 内置事件名`on`开头，遵从**小驼峰式**（camelCase）命名规范
    2. React 中点击事件使用`onClick`

+ <span style="font-size:20px">示例代码==?==</span>

    ```jsx
    function Comp () {
      function clickHandler (e) {
        e.stopPropagation() // 阻止冒泡
      }
    
      function scrollHandler () {}
    
      // 只有小程序的 bindtap 对应 Taro 的 onClick
      // 其余小程序事件名把 bind 换成 on 即是 Taro 事件名（支付宝小程序除外，它的事件就是以 on 开头）
      return <ScrollView onClick={clickHandler} onScroll={scrollHandler} />
    }
    ```

    

## 生命周期触发机制

在 Taro3 中，React 的生命周期触发时机和我们平常在 Web 开发中理解的概念有一些偏差



### React 的生命周期

React 组件的生命周期方法在 Taro 中都支持使用

+ **componentWillMount()**：

    `onLoad`之后，页面组件**渲染到 Taro 的虚拟 DOM 前**触发

+ **componentDidMount()**

    页面组件**渲染到 Taro 的虚拟 DOM 之后**触发

    此时能访问到 Taro 的虚拟 DOM（使用 React ref、document.getElementById 等手段），并支持对其进行操作（设置 DOM 的 style 等）

    但此时不代表 Taro 的虚拟 DOM 数据已经完成从逻辑层 `setData` 到视图层。因此这时**无法通过 `createSelectorQuery` 等方法获取小程序渲染层 DOM 节点。** 只能在 [onReady](https://docs.taro.zone/docs/react-page#onready-) 生命周期中获取



### 小程序页面的方法

小程序页面的方法，在 Taro 页面中同样可以使用：在 Class Component 中书写同名方法、在 Function Component 中使用对应的 Hooks

注意：

+ 使用 **HOC** 包裹的小程序**页面组件**，必须处理**`forwardRef`**或使用**继承组件**的方式，而不是返回组件的方式，否则小程序页面方法可能不会被触发	==？==



## Ref

Taro 中 ref 的用法和 React 完全一致，但获取到的 DOM 和浏览器环境还有小程序环境都有所不同==？==



### React Ref

React Ref 获取到的是 Taro 的虚拟 DOM，和浏览器的 DOM 相似，可以操作它的`style`，调用它的 API 等

但 Taro 的虚拟 DOM 运行在小程序的逻辑层，不是真实的小程序渲染层节点，没有尺寸宽高等信息



```jsx
import React, { createRef } from 'react'
import { View } from '@tarojs/components'

export default class Test extends React.Component {
  el = createRef()

  componentDidMount () {
    // 获取到的 DOM 具有类似 HTMLElement 或 Text 等对象的 API
    console.log(this.el.current)
  }

  render () {
    return (
      <View id='only' ref={this.el} />
    )
  }
}
```



### 获取小程序 DOM==？==

**获取真实的小程序渲染层节点**，需要在**`onReady`**生命周期中，调用小程序中用于获取 DOM 的 API

```jsx
import React from 'react'
import { View } from '@tarojs/components'
import Taro from '@tarojs/taro'

export default class Test extends React.Component {
  onReady () {
    // onReady 触发后才能获取小程序渲染层的节点
    Taro.createSelectorQuery().select('#only')
      .boundingClientRect()
      .exec(res => console.log(res))
  }

  render () {
    return (
      <View id='only' />
    )
  }
}
```



## Hooks



## dangerouslySetInnerHTML

小程序端使用`gerouslySetInnerHTML`时有一些额外的配置选项和需注意的地方



## Minified React error



## 其它限制

+ 小程序不支持动态引入，因此小程序无法使用`React.lazy` API	==？==
+ 不能在页面组件的 DOM 树之外插入元素，因此不支持`<Portal>`
+ 所有组件的`id`必须在整个应用中保持唯一（即使他们在不同页面），否则可能导致事件不触发



# React

## React 基本用法

+ <span style="font-size:20px">State 与 Props</span>

    ```jsx
    // index.jsx
    export default class Index extends Component {
      state = {
        name: "one",
      }
      render () {
        let { name } = this.state;
        return (
          <View className='index'>
            <Text>Hello world!</Text>
            <Child name={name}>子组件</Child>
          </View>
        )
      }
    }
    // child.jsx
    export default class Child extends Component {
      render() {
        let { name } = this.props;
        return (
          <View>我是子节点{name}</View>
        )
      }
    }
    ```

    Props 如果在传值前调用会产生报错，可以使用`Child.defaultProps`



## 入口组件

可以在**入口组件中设置全局状态或访问全局生命周期**，一个最小化的入口组件会是这样：

```react
import React, { Component } from 'react'
import './app.css'

class App extends Component {
  render () {
    // this.props.children 是将要会渲染的页面
    return this.props.children
  }
}

export default App
```



### 生命周期方法

入口组件除支持 React 的生命周期方法外，还根据小程序的标准，额外支持以下生命周期：

+ **onLaunch()**

    在此生命周期中通过访问 `options` 参数或调用 `getCurrentInstance().router`，可以访问到程序初始化参数

+ **componentDidShow()**

    程序启动，或切前台时触发

+ **componentDidHide()**

    程序切后台时触发

+ **onPageNotFound()**

    程序瑶打开的页面不存在时触发



## 页面组件

页面组件可以通过 Taro 路由，访问小程序页面的生命周期进行跳转



一个简单的页面组件如下：

```react
import { View } from '@tarojs/components'
class Index extends Component {
  state = {
    msg: 'Hello World!'
  }

  onReady () {
    console.log('onReady')
  }

  render () {
    return <View>{ this.state.msg }</View>
  }
}

export default Index
```

+ **`onReady`**生命周期函数：

    来源于微信小程序规范的生命周期，表示**组件首次渲染完毕**，准备与视图交互

    Taro运行时将大部分小程序规范页面生命周期注入到页面组件，同时 React 或 Vue 自带的生命周期也可正常使用

+ **`View`组件**：

    来源于`@tarojs/components`的跨平台组件（相当于`div`、`span`元素），在 Taro 要全部使用这样的跨平台组件开发 

+ **页面配置文件**：

    和入口组件一样，每一个页面组件也会又一个页面配置文件(*`index.config.js`*)

    可以在页面配置文件中设置页面的导航栏、背景颜色等参数	==how？==

    最简单的页面配置如下：

    ```js
    export default {
      navigationBarTitleText: '首页'
    }
    ```
    
    

### 生命周期方法

页面组件除支持 React 的生命周期方法外，还根据小程序标准，额外支持以下生命周期：

+ **componentWillReciveProps(nextProps)**:

    接收到新属性/任何属性变化时调用，

    注意即使属性未有任何改变，Taro 可能也会调用该方法，因此若你想要处理改变，需确保比较当前和之后的值



+ **onLoad(options)**:

    在此生命周期中通过访问 `options` 参数或调用 `getCurrentInstance().router`，可以访问到页面路由参数。

+ **onReady()**：

    从此生命周期开始可以使用 `createCanvasContext` 或 `createSelectorQuery` 等 API 访问小程序渲染层的 DOM 节点

    只有页面组件才会触发`onReady`生命周期，**子组件**可以使用 Taro 内置的**消息机制**监听页面组件的`onReady()`生命周期	==？==

+ **componentDidShow()**: 

    页面显示、切入前台时触发

    只在页面组件才会触发 `onShow` 生命周期。**子组件**可以使用 Taro 内置的**[消息机制](https://docs.taro.zone/docs/apis/about/events)**监听页面组件的 `onShow()` 生命周期

+ **componentDidHide()**:

    页面隐藏、切入后台时触发

    **子组件**解决方法同上

+ **onPullDownRefresh()**:

    监听用户下拉动作

    + 需要在全局配置的 window 选项中或页面配置中设置 `enablePullDownRefresh: true`
    + 可以通过 [Taro.startPullDownRefresh](https://docs.taro.zone/docs/apis/ui/pull-down-refresh/startPullDownRefresh) 触发下拉刷新，调用后触发下拉刷新动画，效果与用户手动下拉刷新一致
    + 处理完数据刷新后，[Taro.stopPullDownRefresh](https://docs.taro.zone/docs/apis/ui/pull-down-refresh/stopPullDownRefresh) 可以停止当前页面的下拉刷新

+ **onReachBottom()**: 

    监听用户上拉触底事件

+ **onPageScroll(Object)**:

    监听用户滑动页面事件

    H5 暂时没有同步实现，可以通过给 window 绑定 scroll 事件来进行模拟

+ **onAddToFavorites(Object)**

    监听用户点击右上角菜单“收藏”按钮的行为，并自定义收藏内容

+ **onShareAppMessage(Object)**:

    监听用户点击页面内转发按钮（Button 组件 openType='share'）或右上角菜单“转发”按钮的行为，并自定义转发内容

+ ==。。。==



## Hooks

`Hooks`是一套全新 API，可以在不编写类、state 的情况使用 Class 状态管理、生命周期等功能

这里主要介绍 Taro 中可用的 Hooks API 和部分与 React 不一致的行为，内容大体和 [Hooks Reference](https://zh-hans.reactjs.org/docs/hooks-reference.html) 相同



### 用法

+ Taro 的专有 Hooks（例如 `usePageScroll`, `useReachBottom`）从 `@tarojs/taro` 中引入

+ 框架自己的 Hooks （例如 `useEffect`, `useState`）从对应的框架引入

```jsx
import { usePageScroll, useReachBottom } from '@tarojs/taro' // Taro 专有 Hooks
import { useState, useEffect } from 'react' // 框架 Hooks （基础 Hooks）
```



### Taro Hooks

+ **useRouter**



### React Hooks



### React DevTools

+ <span style="font-size:20px">使用方法</span>



# 路由功能

Taro 中，路由功能默认自带（Taro 默认根据配置路径生成了 Route），不需开发者额外配置，我们只需入口文件指定好 pages，就可通过 Taro 提供的 API 跳转到目的页面

+ <span style="font-size:22px">页面跳转：</span>

    可通过 Taro 提供的 API 来跳转到目的页面

    + Taro.navigateTo()、Taro.redirectTo

        ```jsx
        // 跳转到目的页面，打开新页面（相当于`history.push`，能回退）
        Taro.navigateTo({
          url: '/pages/page/path/name'
        })
        
        // 跳转到目的页面，在当前页面打开（回退要重新加载
        Taro.redirectTo({
          url: '/pages/page/path/name'
        })
        ```

        注意区别和路径。

+ <span style="font-size:22px">传参 & 获取路由参数：</span>

    可通过在`url`后添加查询字符串参数进行跳转传参；跳转成功后在目标页面的生命周期方法中，可通过**`Taro.getCurrentInstance().router.params`** 获取路由参数

    例如，在`.navigateTo()`里：

    ```jsx
    // 传入参数 id=2&type=test
    Taro.navigateTo({
      url: '/pages/page/path/index?id=2&type=test'
    })
    ```

    生命周期方法中获取：

    ```react
    class Index extends Component {
      // 应在页面初始化时把`getCurrentInstance()`结果保存下来供后面使用，而不是频繁地调用此API
      $instance = getCurrentInstance()
    
      componentDidMount () {
        // 获取路由参数
        console.log($instance.router.params) // 输出 { id: 2, type: 'test' }
      }
    
      render () {
        return (
          <View className='index' />
        )
      }
    }
    ```

    也可以解构赋值取得参数：`let {id,type} = this.$router.params`。



# 静态资源引用

在 Taro 中可以像使用 [Webpack](https://webpack.js.org/) 那样自由地`import`或`require`等引用静态资源，而且不需要安装任何 Loaders

+ **引用本地资源：**==？==





# 参考链接

[Taro 文档](https://nervjs.github.io/taro/docs/README)

[Taro 快速上手教程（一） - SegmentFault](https://segmentfault.com/a/1190000016766906)





[^ 1]:React 在元素移动应用平台的衍生产物
[^ 2]:客户端窗口底部或顶部有 tab 栏切换页面

