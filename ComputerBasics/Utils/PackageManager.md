# Package Manager

包管理器



> WebStorm 设置里可更改创建项目默认的包管理器



<span style="font-size:22px">-S 选项——依赖包安装位置：</span>

+ --save-dev / -D：编辑代码的辅助工具——只在开发阶段起作用，开发完成后不需存在项目里

    > Eg：CSS 预处理器、语法规则检查工具 ESlint、打包工具  Webpack

+ --save / -S：项目需要的依赖——缺少后项目报错

    > Eg：前端框架 React、HTTP 请求库 axios、UI 组件库 BootStrap

+ --global / -g：不需再项目里就可能使用的工具依赖

    > Eg：项目脚手架 Vue CLI、服务型工具依赖 http-server



**-E / --save-exact:**

> [What is the purpose of using --save-exact](https://stackoverflow.com/questions/58638817/what-is-the-purpose-of-using-save-exact)

锁定依赖的版本号，也就是版本号中的 `^` 会消失，利于版本统一



# npm

更新依赖：`npm update`

**查看 npm 全局安装位置：**

```shell
$npm root -g
```

npm5 之后的版本默认带 -S

npm uninstall



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



## npx

软件包运行器，主要优势是能够执行没有安装的软件包，而不需全局安装

```shell
npx <pkg>
```



# Yarn

+ 初始化项目——`yarn init`
+ 安装依赖——`yarn install`：创建 lock file
+ 安装依赖——`yarn run`
+ 运行命令——`yarn run <command>`
+ 删除依赖——`yarn remove`
+ 更新依赖——`yarn upgrade`

更新 yarn: `npm upgrade yarn`



# [pnpm](https://pnpm.io)

**Store + Links**

特点: 每次安装依赖的时候，多个项目都用到相同版本同一依赖，则实际只用安装一次

> 而在 npm 和 yarn 中，如何一个依赖被多个项目使用，会发生多次下载和安装



## 配置

```shell
npm i -g pnpm
```



## Usage

`pnpm install`

`pnpm add <pkg>`



## ERROR

[ERR_PNPM_UNEXPECTED_STORE](https://pnpm.io/zh/errors#err_pnpm_unexpected_store):

存在模块目录并链接到不同的存储目录



# REF