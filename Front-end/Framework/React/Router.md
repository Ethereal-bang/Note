# Router

**React-router-dom：**

用于浏览器，处理 Web App 的路由。会自动安装 React-router 核心框架

- 使用`<Link />`组件可以渲染出`<a />`标签
- `<BrowserRouter />`组件利用 H5 API 实现路由切换
- `<HashRouter />`组件利用原生 JS 中`window.location.hash`实现路由切换

> 其余扩展性框架：
>
> - React-router-redux 提供路由中间件，处理 redux 的集成
> - React-router-config 用来配置静态路由

**BrowserRouter vs HashRouter:**

BrowserRouter 使用 history api。意味着服务端需要支持返回每一路径对应的页面——服务端渲染

HashRouter 使用 `/#` 拼接路径，利用锚点实现路由跳转



# 路由配置

<span style="font-size:20px">初始化: </span>

```tsx
function App() {
    return (
        <Provider store={store}>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<HomePage />} />
                    <Route path="/show" element={<ShowPage />} />
                    <Route path="*" element={<h1>404</h1>} />
                </Routes>
            </BrowserRouter>
        </Provider>
    );
}
```

> **Notes:**
>
> + BrowserRouter / HashRouter 内还要包裹一层 Routes 组件
>
> + 没有 exact 属性，现在默认精准匹配路径
>
> + Route 顺序在一下情况起作用：
>
>     ```jsx
>     <Route path={"/"} element={<h1>默认</h1>} />
>     <Route path={"*"} element={<h1>404</h1>} />
>     ```

<span style="font-size:20px">子路由配置: </span>

依旧用 Routes 组件包裹：

```jsx
<Routes>
	<Route path="manage" element={<Manage />} />
</Routes>
```

> 因为这里是 /home 的子路由，path 不需要写 /manage



# 路由跳转

+ 组件：

    ```tsx
    const ShowPage = () => {
        return (
            <>
                <ShowCnt />
                <Link to={"/"}>回到首页</Link>
            </>
        )
    }
    ```
    
+ JS：

    ```js
    import { useNavigate } from "react-router-dom";
    
    const navigate = useNavigate();
    navigate("/path", {	// 路径不是相对于当前
      replace: false,	// 默认false，true则不能回退
    	state: { name: 'x' }	// 路由传参
    })
    navigate(-1);	// 回退，数字代表回退层数    
    ```

+ 重定向

    ```jsx
    <Route path='/' element={<Navigate to={"/home"} replace /> }/>
    ```



# 路由参数传递

+ **路由匹配：**

    ```tsx
    {/* App.tsx */}
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/show" element={<ShowPage />}>
          <Route path=":showId" element={<ShowPage />} />
        </Route>
      </Routes>
    </BrowserRouter>
    
    // ShowPage.tsx
    const ShowPage = () => {
        const params = useParams();
        return (
            <>
                <h2>{params.showId}</h2>
                <Link to={"/"}>回到首页</Link>
            </>
        )
    }
    ```

    > 效果：
    >
    > URL 为 "/show" 时不显示`<h2>`内容，为 "/show/2" 时显示出 2.

+ 获取路由参数：

    ```tsx
    import {useLocation} from "react-router-dom";
    
    const location = useLocation();
    ```

    > 获取到的 location 有如下字段：`pathname`、`search`、`state`。



# 获取路由

**组件内监听路由：**

```jsx
const location = useLocation();
// 监听路由
useEffect(() => {
  console.log(location.pathname)
}, [location.pathname])
```



# 子路由渲染 \<Outlet />

引入 Outlet 组件占位功能，更方便地配置路由结构

Eg——二级路由，一级路由无内容：

```jsx
<Route path="manage" element={<Outlet/>}>
  <Route path={"station"} element={<Station/>} />
  <Route path={"director"} element={<Director/>} />
</Route>
```

> 效果：'/manage'——空白页；'/manage/station' 显示对应组件

父路由没有 `Outlet` 则子路由内组件不能渲染



# useRoutes

> 功能与 `<Routes>` 相同，但使用 JS 对象代替 `<Route>` 来定义路由

```tsx
function App() {
  const routes: RouteObject[] = [
    {
      path: "/",
      element: login ? <Outlet /> : <Navigate to={"/login"} />,
      children: [
        {
          path: "", // 不等同*
          element: <h1>Home page</h1>
        },
        {
          path: "message",
          eleement: <Message />,
        },
      ],
    },
  ]
  return useRoutes(routes);
}
```

```jsx
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <App/>
    </BrowserRouter>
  </React.StrictMode>
);
```



# 路由鉴权

## Auth 组件包裹

```jsx
// /componenets/auth/Auth.jsx
export default function Auth(props) {
    // 已登录
    if (idGetter()) {
        return <>{props.children}</>
    }
    // 未登录-重定向到login
    else {
        return <Navigate to={"/login"} replace />
    }
}

// App.js
<Route path={"/"} element={<Auth><Home /></Auth>} />
```



## Guard Route

```jsx
<Route element={<StuGuard />}>
  <Route path={"stu"} element={<h2>Stu page</h2>} />
	{/*...*/}
</Route>
```

```jsx
const StuGuard = () => {
  return getAuth() === "stu" ? <Outlet /> : <Navigate to={"/login"} />
}
```


