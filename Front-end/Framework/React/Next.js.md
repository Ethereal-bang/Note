# [Next.js](https://www.nextjs.cn/)

轻量级的 React 服务端渲染应用框架

> **内置功能:**
>
> - 直观的、 [基于页面](https://www.nextjs.cn/docs/basic-features/pages) 的路由系统（并支持 [动态路由](https://www.nextjs.cn/docs/routing/dynamic-routes)）
> - [预渲染](https://www.nextjs.cn/docs/basic-features/pages#pre-rendering)。支持在页面级的 [静态生成](https://www.nextjs.cn/docs/basic-features/pages#static-generation-recommended) (SSG) 和 [服务器端渲染](https://www.nextjs.cn/docs/basic-features/pages#server-side-rendering) (SSR)
> - 自动代码拆分，提升页面加载速度
> - 具有经过优化的预取功能的 [客户端路由](https://www.nextjs.cn/docs/routing/introduction#linking-between-pages)
> - [内置 CSS](https://www.nextjs.cn/docs/basic-features/built-in-css-support) 和 [Sass 的支持](https://www.nextjs.cn/docs/basic-features/built-in-css-support#sass-support)，并支持任何 [CSS-in-JS](https://www.nextjs.cn/docs/basic-features/built-in-css-support#css-in-js) 库
> - 开发环境支持 [快速刷新](https://www.nextjs.cn/docs/basic-features/fast-refresh)
> - 利用 Serverless Functions 及 [API 路由](https://www.nextjs.cn/docs/api-routes/introduction) 构建 API 功能
> - 完全可扩展



# Install

```bash
npx create-next-app@latest
```

根据提示配置安装选项，其中:

+ --tailwind: Initialize with Tailwind CSS config. (default) —— postcss



# CSS

全局 CSS 文件只能在 `pages/_app.js` 根文件引入

```jsx
import './styles/globals.css'

export default function MyApp() {
  return <p />
}
```



**CSS in Module:**

```js
import styles from './Button.module.css'
```



## Tailwind

替换掉原来的文件内容

```css
/* ./styles/globals.css */
@tailwind base;
@tailwind components;
@tailwind utilities;
```



# Component

## 内置组件

**[next/image](https://nextjs.org/docs/api-reference/next/image):**

```jsx
import Image from 'next/image'
import img from "../assets/"
return (
	<Image src={img} alt="" />
  <Image src={"xxx.com/img"} alt="" width={120} height={20} />
)
```

+ 本地图片可省略 width / height prop
+ 远程图片必须 width + height / fill



## @alifd/next 组件库

```powershell
npm i @alifd/next
```

