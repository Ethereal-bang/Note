# 项目启动

1. `npm run dev:weapp`
2. 微信开发者工具选择项目目录 /dist 文件夹



# 项目初始化

技术栈：Vue + TS + Sass + taro-ui-vue（使用 taro-ui-vue 的模板）

项目初始目录如下：<img src="https://gitee.com/ethereal-bang/images/raw/master/20220122122655.png" alt="image-20220122122655444" style="zoom:53%;" />

# 页面分配

1. 在`app.config.ts`内设置字段`pages`、`tabBar`：

    注意`tabBar`内的所有`pagePath`都必须在`pages`内有对应。



# 页面细节

1. 每个页面对应一个`pages`内的文件夹，`.vue`内设置主体、`.config.js`内设置页面配置项：

    + `navigationBarTitleText`设置的文本显示在对应 tab 页顶部标题

2. 完善`.vue`：

    + `.vue`可由以下部分构成：

        ```vue
        <template>	<!-- 或许可以理解成html-->
        </template>
        
        <script>
          // ...
          export default {}
        </script>
        
        <style></style>
        ```

        

# 问题记录

+ <span style="font-size:20px">[HTML 标签使用：](https://nervjs.github.io/taro/docs/use-h5#%E4%BD%BF%E7%94%A8%E6%96%B9%E6%B3%95)</span>

    1. 想在`template`标签内直接使用 HTML 需要安装插件：

        ```
        yarn add @tarojs/plugin-html
        ```

    2. 在项目配置中添加使用插件

        ```js
        // config/index.js
        config = {
          plugins: ['@tarojs/plugin-html']
        }
        ```

    3. 如果此时微信开发者工具内还是报错"Template `tmpl_0_div` not found"，更新所有项目依赖：

        ```
        yarn upgrade-interactive  --latest
        ```

+ <span style="font-size:20px">[Maximum call stack size exceeded](https://www.jianshu.com/p/1fa961806fcc)</span>

    问题描述：![img](https://upload-images.jianshu.io/upload_images/551421-3f4acde4ab958f93.png?imageMogr2/auto-orient/strip|imageView2/2/w/566/format/webp)

    组件名称与引用的`Slider`组件重名，导致不断循环调用。改变组件名解决。
    
+ <span style="font-size:20px">[组件样式不能修改](https://www.cnblogs.com/fightjianxian/p/11920913.html)</span>

    使用`<style>`标签而不是`<style scope>`设置。

+ <span style="font-size:20px">引用 Taro UI VUE 的ts 文件编译出错</span>

    + Taro UI Vue 源码部分使用Taro Ui Vue 提供的是源文件，源文件部分代码使用 `ts` 编写，需要项目支持 ts。
    + 在`app.scss`而不是`app.js`中全局引用样式

+ <span style="font-size:20px">`<style>`标签样式不生效==？==</span>

    项目换成Sass + taro-ui-vue 后`<style scope>`标签样式不生效，需在同文件夹内`.scss`设置样式。
    
+ <span style="font-size:20px">关于除 Tab 对应的其他页面:</span>

    + Desc: " [ app.json 文件内容错误] app.json: 未找到 ["pages"][3] 对应的 pages/publish/out.js 文件 "
    + R: "app.config.js" pages 字段下每个路径要在 /pages 文件夹下有对应 js 文件
    
+ <span style="font-size:20px">`navigator`组件点击路径没变：</span>

    `url`属性设置错误。

    A: `url="./page/index/index"`改为`/page/index/index`

+ <span style="font-size:20px">`AtTabsPane`组件下文字不显示</span>

    + Q_Desc：组件下`<view>Text</view>`形式的文子不予显示，只显示诸如：

        ```vue
        <AtListItem
        	note="Some text"
        />
        ```

        

    

    

# Ref

+ 问题：

    [出现 Template `tmpl_0_div` not found. - githubmemory](https://githubmemory.com/repo/NervJS/taro/issues/10539)



# Other

![image-20220209105209959](https://gitee.com/ethereal-bang/images/raw/master/20220209105217.png)

![image-20220209113053923](https://gitee.com/ethereal-bang/images/raw/master/20220209113054.png)