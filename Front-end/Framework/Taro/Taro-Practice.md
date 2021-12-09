# 项目初始化



# 页面分配

1. 在`app.config.js`内设置字段`pages`、`tabBar`：

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

        

# 参考

+ 问题：

    [出现 Template `tmpl_0_div` not found. - githubmemory](https://githubmemory.com/repo/NervJS/taro/issues/10539)