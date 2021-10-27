# 准备阶段

+ <span style="font-size:22px">安装：</span>

    1. 安装

        ```
        npm i -g typescript
        ```

    2. 验证

        ```
        tsc -v
        ```

    3. 编译 TypeScript 文件

        ```
        $ tsc test.ts
        # test.ts => test.js
        ```

    

+ <span style="font-size:22px">.ts 的编译：</span>

    **编译器**：ts-loader、awesome-typescript-loader、**babel-loader**。因为本处使用`create-react-app`，因此默认的编译器是`babel-loader`。

    **编译器配置文件**：tsconfig.json（可显示声明文件）

    编译器启动时，首先读取`tsconfig.json`，以获取有关如何编译项目的说明

    `"complilerOptions"`定义编译器的工作方式，在其中加上一个属性`noImplicitAny: false`意为不需要显示地声明变量类型`any`，实现 js 与 ts 的混合编写

    各语句含义：<img src="C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20210603200542608.png" alt="image-20210603200542608" style="zoom: 80%;" />



+ <span style="font-size:22px">典型 TypeScript 工作流程：</span>

    ![img](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/dea0cbad55b246a8a7e65aec57273ade~tplv-k3u1fbpfcp-watermark.awebp)

    对于大多数使用 TypeScript 开发的 Web 项目，还会对编译生成的 js 文件进行打包处理，然后在进行部署



# 基础类型

+ <span style="font-size:22px">boolean</span>

+ <span style="font-size:22px">number</span>

+ <span style="font-size:22px">string</span>

+ <span style="font-size:22px">Symbol</span>

+ <span style="font-size:22px">Array：</span>

    ```typescript
    let list: number[] = [1, 2, 3];
    
    // Array<number>泛型语法：
    let list: Array<number> = [1, 2, 3]; 
    ```

<hr>

+ <span style="font-size:22px">enum：</span>

    TS 支持数字的和基于字符串的枚举

    枚举可以清晰定义一组用例：

    ```typescript
    enum Direction {
      NORTH,
      SOUTH,
      EAST,
      WEST,
    }
    let direc: Direction = Direction.NORTH;
    ```

    

+ <span style="font-size:22px">any</span>

+ <span style="font-size:22px">unknown</span>：

    就像所有类型都可以赋值给 `any`，所有类型也都可以赋值给 `unknown`。这使得 `unknown` 成为 TypeScript 类型系统的另一种顶级类型

    `any`类型允许更改，但类型设置为`unknown`后不允许再修改



+ <span style="font-size:22px">Tuple：</span>

    元组可用于定义具有有限数量的未命名属性的类型，**单个变量中存储不同类型值**，这时候我们就可以使用元组

    ```typescript
    let tupleType: [string, boolean];
    tupleType = ["semlinker", true];
    ```

    可以像 Array 一样通过下标访问元组中元素

+ <span style="font-size:22px">object、Object、{}</span>



+ <span style="font-size:22px">void：</span>

    ```typescript
    // 声明函数返回值为void
    function warnUser(): void {
      console.log("This is my warning message");
    }
    ```

+ <span style="font-size:22px">null、undefined</span>

+ <span style="font-size:22px">never：</span>

    `never` 类型表示的是那些永不存在的值的类型



# 联合类型与交叉类型

## 联合类型 |



## 交叉类型 &





# TS 的定义声明

## TS 中的声明文件

在 src 文件夹下新建一个`.d.ts`

只包含类型声明，不含逻辑

不会被编译、打包



# Interface

+ <span style='font-size:20px'>对象 Interface</span>

    <hr>

    作用：

    1. 设置需要存在的普通属性
    2. 设置可选属性
    3. 设置只读属性
    



# 类

## 抽象类

**抽象类**：使用**`abstract`**关键字声明的类

抽象类**不能实例化**，因为里面包含多个**抽象方法**（不包含具体实现的方法）

```js
abstract class Person {
  constructor(public name: string) {}

  abstract say(words: string) :void;
}

// Cannot create an instance of an abstract class
const lolo = new Person(); // Error
```

**实例化抽象类的子类**：

```js
```





# 参考链接

[一份不可多得的 TS 学习指南（1.8W字）- 掘金](https://juejin.cn/post/6872111128135073806)

