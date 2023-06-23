# [Vite](https://cn.vitejs.dev/)

前端构建工具



## Start

```shell
$ pnpm i vite
```

+ 模板搭建项目:

  ```shell
  $ yarn create vite
  $ pnpm crete vite
  ```

+ 新建 vite.config.js

  ```js
  export default {
  }
  ```

  

# vite.config

**获取类型提示:**

```js
import { defineConfig } from 'vite'

export default defineConfig({
  // 
})
```



# Less

Vite 已集成了 CSS Module 功能 (module.css)，但是想要使用 Less 还需要安装 less 这个库
# index.html

在一个 Vite 项目中，`index.html` 在项目最外层而不是在 `public` 文件夹内



# TS

Vite 天然支持引入 `.ts` 文件
