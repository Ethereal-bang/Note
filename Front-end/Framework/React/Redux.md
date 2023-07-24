# Redux

**配置：**

```shell
$yarn add react-redux
```



# 基础

核心概念：Action, Reducer, Store

## Action

**Action 对象**，把数据从应用传到 store 的有效载荷 

```typescript
export const CHANGE_CNT = "change_cnt";

interface ChangeCntAction {
    type: typeof CHANGE_CNT; // type字段表示将要执行的动作 通常定义为字符串常量
    payload: number;
}
```

Action 创建**工厂函数：**

```typescript
export const changeCntActionCreator = (num: number) : ChangeCntAction => {
    return {
        type: CHANGE_CNT,
        payload: num, // 除了type其它字段自定义
    }
}
```



## Reducer

Reducer——根据 action 更新 state

**Reducer 的基本框架：**

```js
(state, action) => state
```

> **参数 state 和 action：**
>
> state 是 store 中的旧数据，而 action 是指挥 reducer 函数作数据变换的指令

**编写一个 Reducer：**（根据 `action.type` 返回新 state，值根据 `action` 中其它自定义字段

```typescript
// Redux 首次执行时，state 为 undefined 所以设置初始值
const cntReducer = (state = initState, action) => {
  switch (action.type) {
    case ADD:
      return {
        ...state,// 不能对state参数修改
        cnt: state.cnt + action.payload
      }
		/* ...其它type */
    default: return state;
  }  
}
```

**Reducer 合成——`combineReducers()`：**

一个函数来做为主 reducer，它调用多个子 reducer 分别处理 state 中的一部分数据，然后再把这些数据合成一个大的单一对象

> 每个 reducer 只负责管理全局 state 中它负责的一部分。每个 reducer 的  `state` 参数都不同，分别对应它管理的那部分 state 数据

```js
const reducer = combineReducers({
  a: aReducer,
  b: bReducer,
})
```



## Store

**`createStore()`: **

```js
const store = createStore(combinedReducer);
```

**`getState()`**——获取 store 对象

**dispatch(action)**——更新 state

```js
store.dispatch(actionCreater(1));
```



# React Thunk 处理异步 action

> 使用 RTK 则不需要这种做法

**redux-thunk** 中间件，让 dispatch 支持了**函数类型**

以请求为例

**action:**

```js
const requestJokes = () => {
    return {
        type: REQUEST_JOKE,
    }
}
const receiveJokes = (data) => {
    return {
        type: RECEIVE_JOKE,
        data: data.jokes,
    }
}
// thunk action 创建函数:
export const fetchJokes = (num) => {
    // 把dispatch通过参数的形式传给返回的函数，让它自己也能dispatch action
    return (dispatch) => {
        // 第一个通知API请求发起了
        dispatch(requestJokes());
        return fetch(`https://autumnfish.cn/api/joke/list?num=${num}`)
            .then(response => response.json()
                .then(json => {
                    // 第二个通知 拿到响应数据
                    dispatch(receiveJokes(json));
                }))
    }
}
```

**reducer:**

```js
const jokeReducer = (state = [], action) => {
    switch (action.type) { // 实际场景里此时应该弄个LOADING
        case REQUEST_JOKE:	// 此时对应才开始请求
            return []; 
        case RECEIVE_JOKE:
            return action.data;	// 请求完毕
        default: return state;
    }
}
```

**dispatch action**: `store.dispatch(fetchJokes(2));`



# RTK

`@reduxjs/toolkit`



## Slice

> 使用 RTK 后 slice 将自动包含 reducer 和 action 的映射关系，不再需要分别创建

```tsx
export const counterSlice = createSlice({
    name: "counterSlice",
    initialState,
    reducers: {
        addCnt: (state, action:PayloadAction<number>) => {
            state.cnt += action.payload;
        },
        // ...
    }
})
```

> **action 的类型：**
>
> RTK 已经定义好了 action 的类型 `payload: any; type: string;`，如果需要自定义 action 类型可以使用`PayloadAction`



## 异步操作 createAsyncThunk

> 原来的 extraReducers 写法已被弃用

```jsx
export const searchProduct = createAsyncThunk(
  "productSearch/searchProduct",
  async () => {
    const response = await axios.get(url);
    return {
      data: response.data,
      pagination: JSON.parse(response.headers["x-pagination"]),
    };
  }
);

export const productSearchSlice = createSlice({
  name: "productSearch",
  initialState,
  reducers: {},
  extraReducers: builder => {
    builder
      .addCase(searchProduct.pending, state => {
        state.loading = true;
      })
      .addCase(searchProduct.fulfilled, (state, action) => {
        state.data = action.payload.data;
        state.pagination = action.payload.pagination;
        state.loading = false;
        state.error = null;  
      })
      .addCase(searchProduct.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload as string;
      })
  },
})
```



## Store

1. <span style="font-size:22px">创建 Store：</span> **configureStore()**

    ```ts
    import {applyMiddleware, combineReducers} from "@reduxjs/toolkit";
    
    export default configureStore(combineReducers({
        counter: counterSlice.reducer,
    }));;
    ```
+ <span style="font-size:22px">Middleware：</span>

    以 log 功能为例——每次 state 改变打印 state：

    ```typescript
    // redux/middlewares/actionLog.ts
    import {Middleware} from "@reduxjs/toolkit";
    
    export const actionLog : Middleware = (store) => (next) => (action) => {
                console.log("当前state ", store.getState());
                next(action);
                console.log("更新state ", store.getState());
            }
        }
    }
    ```
    
    创建 store 时加入 middleware：

    ```typescript
    const store = configureStore({
      reducer: persistedReducer,
      middleware: (getDefaultMiddleware) => getDefaultMiddleware().concat(actionLog)
    });
    ```

2. <span style="font-size:22px">组件与 Store 的连接：</span>

    **Provider 组件**包裹根组件，并传入 Store：`Provider`组件创建了一个 react-redux 上下文

    `<Provider store={store}>`
    
3. <span style="font-size:22px">访问组件:</span>

    **useSelector()**——获得相应字段

    ```tsx
    export default function Counter() {
      const cnt = useSelector(state => state.counter.cnt);
      const clickState = useSelector(state => state.counter.clickState);
    
      return (
        <div>
          <p>Clicked {cnt} times.</p>
          <p>It`s {clickState} state.</p>
        </div>
      )
    }
    ```

    > **让 state 拥有智能联想：**
    >
    > ```typescript
    > // redux/store.ts
    > export type RootState = ReturnType<typeof store.getState>;
    > ```
    >
    > ```ts
    > // redux/hooks.ts
    > import {
    >  TypedUseSelectorHook,
    >  useSelector as useReduxSelector,
    > } from "react-redux";
    > import {RootState} from "./store";
    > 
    > export const useSelector: TypedUseSelectorHook<RootState> = useReduxSelector;
    > ```
    >
    > 然后引用 useSelector 时从上述文件引用。

4. <span style="font-size:22px">Dispatch action：</span>

    ```js
    // reducers中:
    dispatch(counterSlice.actions.add(2))	
    // extraReducers中
    dispatch(login(name));	
    ```



# [redux-persist](https://github.com/rt2zz/redux-persist/tree/master)

数据持久化

## Baisc Usage

**persistReducer(*config*, *reducer*)**, **persistStore(*store*)**:

```js
import storage from "redux-persist/lib/storage";	// 默认存到localStorage

const persistConfig = {
  key: "root",
  storage,	// 存储位置
}

const persistReducer = persistReducer(persistConfig, rootReducer);	// 创建store时替代掉rootReducer
// const store = ...
const persistor = persistStore(store);

export default { store, persistor }
```

**\<PersistGate>** ——延迟 UI 的呈现，直到 persisted state 被保存到 redux 

```jsx
<Provider store={store}>
  <PersistGate persistor={persistor} loading={<Loading />}>
    <App />
  </PersistGate>
</Provider>
```

> loading prop can be null



## API

### persistReducer(config, reducer)

**@param config:**

```typescript
interface PersistConfig = {	// 简化
	key: string,
  storage: Storage,
  blacklist?: Array<string>;	// 数组项是对应state名
  whitelist?: Array<string>;	// 每当白名单中的数据发生变化，才会进行一次更新缓存的操作
}
```

