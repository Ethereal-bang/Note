# Package Manager

包管理器



> WebStorm 设置里可更改创建项目默认的包管理器



+ <span style="font-size:22px">-S 选项——依赖包安装位置：</span>

    + --save-dev / -D：编辑代码的辅助工具——只在开发阶段起作用，开发完成后不需存在项目里

        > Eg：CSS 预处理器、语法规则检查工具 ESlint、打包工具  Webpack

    + --save / -S：项目需要的依赖——缺少后项目报错

        > Eg：前端框架 React、HTTP 请求库 axios、UI 组件库 BootStrap

    + --global / -g：不需再项目里就可能使用的工具依赖

        > Eg：项目脚手架 Vue CLI、服务型工具依赖 http-server



# npm

+ 更新依赖——`npm update`

+ **查看 npm 全局安装位置：**

    ```shell
    $npm root -g
    ```
    
+ npm5 之后的版本默认带 -S



## 配置

<span style="font-size:20px">修改全局包安装位置</span>

**修改默认全局目录：**

```shell
npm config set prefix D:/nodejs/node_global
npm config set cache D：/nodejs/node_cache
```

**环境变量：**

用户变量下编辑 path，添加 global

```shell
PATH: D:\nodejs\node_global
```



<span style="font-size:20px">修改镜像源</span>

```shell
npm config get registry
npm config set registry https://registry.npm.taobao.org
```



# Yarn

+ 初始化项目——`yarn init`
+ 安装依赖——`yarn install`：创建 lock file
+ 安装依赖——`yarn run`
+ 运行命令——`yarn run <command>`
+ 删除依赖——`yarn remove`
+ 更新依赖——`yarn upgrade`



# [pnpm](https://pnpm.io)

## Install

```shell
npm i -g pnpm
```



## Usage

`pnpm install`




# REF
