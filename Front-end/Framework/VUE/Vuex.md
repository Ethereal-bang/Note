# Vuex



# Module

Vuex 允许我们将 store 分割成 module

每个模块拥有自己的 state、mutation、action、getter、甚至是嵌套子模块

```js
const moduleA = {
  state: () => ({ ... }),
  mutations: { ... },
  actions: { ... },
  getters: { ... }
}

const moduleB = { /*...*/ }

const store = createStore({
  modules: {
    a: moduleA,
    b: moduleB
  }
})

store.state.a // -> moduleA 的状态
```

