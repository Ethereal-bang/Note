# Package Manager

包管理器

# npm

+ **查看 npm 全局安装位置：**

    ```shell
    $npm root -g
    ```
+ <span style="font-size:22px">-S 选项——依赖包安装位置：</span>

    + --save-dev：编辑代码的辅助工具——只在开发阶段起作用，开发完成后不需存在项目里

        > Eg：CSS 预处理器、语法规则检查工具 ESlint、打包工具  Webpack

    + --save：项目需要的依赖——缺少后项目报错

        > Eg：前端框架 React、HTTP 请求库 axios、UI 组件库 BootStrap

    + --global：不需再项目里就可能使用的工具依赖

        > Eg：项目脚手架 Vue CLI、服务型工具依赖 http-server

+ npm5 之后的版本除了把包下载到 `node_modules/` 中还会修改 package.json。

# Yarn

+ 初始化项目——`yarn init`


# REF

+ PackageManager：

    [怎么知道依赖包需要安装到生产还是开发环境或者是安装到全局](https://yogwang.site/2019/nodejs-npm-save-dev-global/)
    
    [npm 的 --save 选项_sigmarising的博客-CSDN博客](https://blog.csdn.net/sigmarising/article/details/88532197?spm=1001.2101.3001.6661.1&utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1.pc_relevant_paycolumn_v3&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1.pc_relevant_paycolumn_v3&utm_relevant_index=1)
