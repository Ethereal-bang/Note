> [深入理解TypeScript](https://jkchao.github.io/typescript-book-chinese/)



<img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/d88a00458ce14c73bd3114239f5cb7e2~tplv-k3u1fbpfcp-watermark.awebp" alt="image" style="zoom:200%;" />

# 准备阶段

<span style="font-size:22px">安装：</span>

1. 安装

    ```shell
    npm i -g typescript
    ```

2. 验证

    ```
    tsc -v
    ```



<span style="font-size:22px">.ts 的编译：</span>

**编译器**：ts-loader、awesome-typescript-loader、**babel-loader**。

**编译器配置文件**：tsconfig.json（可显示声明文件）（`tsc --init` 创建）

编译器启动时，首先读取`tsconfig.json`，以获取有关如何编译项目的说明

`"complilerOptions"`定义编译器的工作方式，在其中加上一个属性`noImplicitAny: false`意为不需要显示地声明变量类型`any`，实现 js 与 ts 的混合编写

各语句含义：<img src="C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20210603200542608.png" alt="image-20210603200542608" style="zoom: 80%;" />



<span style="font-size:22px">典型 TypeScript 工作流程：</span>

![img](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/dea0cbad55b246a8a7e65aec57273ade~tplv-k3u1fbpfcp-watermark.awebp)

对于大多数使用 TypeScript 开发的 Web 项目，还会对编译生成的 js 文件进行打包处理，然后在进行部署



<span style="font-size:22px">TS 中的声明文件：</span>

在 src 文件夹下新建一个`.d.ts`

只包含类型声明，不含逻辑

不会被编译、打包



# CLI

编译 TypeScript 文件

```shell
$ tsc test.ts
# test.ts => test.js
```



```shell
$ tsc --init # 生成tsconfig.json
$ tsc # 识别tsconfig.json配置文件中内容，本质上同直接使用tsc -xxx待选项命令
```



# 类型声明

+ <span style="font-size:20px">变量：</span>

    ```ts
    const res: number[] = [];
    ```

    > TS 变量声明后将不能像 JS 一样随意更改类型——报错类型不匹配。

+ <span style="font-size:20px">返回值：</span>

    + 箭头函数：

        ```ts
        const bubbleSort = (nums: number[]): void => {}
        ```

+ <span style="font-size:20px">type vs interface: </span>

    interface 描述<span style="color:orange">数据结构</span>，用 type 描述<span style="color:orange">类型关系</span>


# 类型

## 基础类型

+ <span style="font-size:22px">boolean</span>

+ <span style="font-size:22px">number</span>

+ <span style="font-size:22px">string</span>

+ <span style="font-size:22px">Symbol</span>

+ <span style="font-size:22px">Array：</span>


+ <span style="font-size:22px">enum：</span>

    TS 支持数字的和基于字符串的枚举

    可 for...in 遍历

    枚举可以清晰定义一组用例：

    ```typescript
    enum Direction {
      NORTH,
      SOUTH,
      EAST,
      WEST,
    }
    let direc: Direction = Direction.NORTH;
    
    enum Num {
      ZERO = 0,
    }
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

    `object`表示**非原始类型**(*除 `string`、`boolean`、`number`、`bigint`、`symbol`、`null` 和 `undefined`* )

    `Object`表示 Object 类的实例的类型
    
    {} 指没有成员的空对象：
    
    ```ts
    // Type {}
    const obj = {};
    
    // Error: Property 'prop' does not exist on type '{}'.
    obj.prop = "semlinker";
    ```

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



## 联合类型与交叉类型

联合类型`|`、交叉类型`&`



## 断言

+ <span style="font-size:20px">类型断言：</span>

    **`<>`：**

    **`as`：**

    ```typescript
    interface LocationState {
        name: string,
        num: number,
    }
    
    const state = useLocation().state as LocationState;
    ```
    
    > 避免后来使用 state 时提示 ” Argument of type 'unknown' is not assignable to parameter of type ... “

## 接口 Interface

Interface 是一种描述对象或函数的东西

+ <span style='font-size:20px'>对象 Interface：</span>

    ```tsx
    interface Person {
      name?: string;	// 可选属性
      readonly age: number;	// 只读属性
    }
    ```
    
    
    
+ <span style="font-size:20px">函数 Interface：</span>

    ```typescript
    interface Func {
        (x: number, y?: string): void
    }
    const show: Func = (x, y?) => {
        console.log(y, x);
    }
    sum(1);
    
    // 结合泛型?
    interface Constructor<T, R> {
      new (...args: T[]): R;
      (...args: T[]): R;
    }
    ```



+ <span style="font-size:20px">类 Interface：</span>

    类 Interface 只会检查实例的属性，静态属性需要额外定义一个 Interface ==？==

<hr>

+ **`new`操作符**：

    想要执行`new`操作符，就得定义`new`的 interface：

    ```typescript
    interface Person {
      name: string;
    }
    
    interface Constructor<T, R> {
      new (...args: T[]): R;  // new 是一个构造函数，要定义new接口
    }
    
    const person: Constructor<string, Person> = function (this: Person, name: string) {
      this.name = name;
    } as any;
    const a = new person()
    ```

    1. 行 9，为什么函数`person`要两处类型定义：

        `Constructor`Interface 是为了告诉编译器这是一个构造函数，传参`string[]`，`new`返回值`Person`。不加`Constructor`（或`as any`就不能`new`它。

    2. 行 11，`as any`的作用：

        解决报错：加了`Constructor`报错前后类型不匹配：

        ![image-20211108214547984](https://gitee.com/ethereal-bang/images/raw/master/20211108214548.png)

    3. 创建的实例的类型：

        ![image-20211108215250905](https://gitee.com/ethereal-bang/images/raw/master/20211108215250.png)

        没有`Constructor`，显示`aa`的类型是 any。



# 类型兼容性

TypeScript 类型兼容性是基于**结构类型**——只使用其成员来描述类型的方式

> **结构性类型、名义类型：**
>
> 名义类型：数据类型的兼容性或等价性是通过明确的声明和/或类型的名称来决定的
>
> 结构性类型：基于类型的组成结构，且不要求确切地声明



例如判断两函数是否兼容：

```typescript
let x = (a: number) => 0;
let y = (b: number, s: string) => 0;

y = x; // √
x = y; // Error
```

要查看`x`是否能赋值给`y`，首先看它们的参数列表。 `x`的每个参数必须能在`y`里找到对应类型的参数。 注意的是：参数的名字相同与否无所谓，只看它们的类型。这里，`x`的每个参数在`y`中都能找到对应的参数，所以允许赋值。



## 协变和逆变

具有父子关系的类型，再通过某种构造关系构造成的新类型：如果还具父子关系则是“**协变**”的；关系逆转则是“**逆变**”的

+ <span style="font-size:20px">协变：</span>

    下例中具有父子关系的`Animal`和`Dog`，构造的`animal`和`dog`还具父子关系，因此是协变的。

    ```typescript
    interface Animal {
      name: string;
    }
    interface Dog extends Animal {
      break(): void;
    }
    
    let dog: Dog = {
      name: "dog",
      break() {},
    }
    let animal: Animal;
    
    // 子类型可以赋值给父类型
    animal = dog;
    // 父类型不可以赋值给子类型
    dog = animal;
    ```

    ![image-20211109090225408](https://gitee.com/ethereal-bang/images/raw/master/20211109090232.png)



+ <span style="font-size:20px">逆变：</span>

    ```typescript
    interface Animal {
      name: string;
    }
    interface Dog extends Animal {
      break(): void;
    }
    
    type AnimalFn = (arg: Animal) => void;
    type DogFn = (arg: Dog) => void;
    
    let fa: AnimalFn = function (Animal) {};
    let fd: DogFn;
    
    // 逆变——可以赋值
    fd = fa;
    // 类型AnimalFn = DogFn不可以赋值了：
    // fa = fd 
    ```

    行 17 报错：![image-20211109224323392](https://gitee.com/ethereal-bang/images/raw/master/20211109224330.png)



# 泛型

泛型使类型声明更加灵活，泛型是允许同一个函数接受不同类型参数的一种模板，提高了复用性（，从下面的例子可以看出）

+ **泛型语法：**

    ```typescript
    function identity<T> (value: T): T {
      return value;
    }
    ```

    这里的`<T>`就像传递参数一样传递了想用于特定函数调用的类型

    调用时：

    ![image-20211108171529754](https://gitee.com/ethereal-bang/images/raw/master/20211108171536.png)

+ **泛型变量：**

    上面的`<T>`（代表 type）是泛型变量，实际上`T`可以用任何有效名称代替，常见的泛型变量有：

    + K（Key）：表示对象中的键类型
    + V（Value）：对象中的值类型
    + E（Element）：元素类型



类型像传递参数一样传递：

![image-20211108172248966](https://gitee.com/ethereal-bang/images/raw/master/20211108172249.png)

更简洁的写法通常是让编译器自动判断类型：

```tsx
function identity <T, U>(value: T, message: U) : T {
  console.log(message);
  return value;
}

identity(68, "Semlinker");
```



## 泛型约束

+ <span style="font-size:20px">extends：</span>

    通过`extends`关键字添加泛型约束。

    + `extends`用于**三目表达式**：

        `A extends B ? "xxx" : "xxx"`即`A`能否赋值给`B`。

    + **判断能否赋值：**

        + 更**具体**的可以给更**宽泛**的赋值：

            ```typescript
            type D = 1 | 2 extends 1 | 2 | 3 ? number : string;
            // number
            ```

        + A、B 都是函数则主要看**参数、返回值**：

            参数：

            + A 参数多与 B，A 不能赋值给 B
            + A、B 参数类型不一致，不能赋值
            + 参数类型有一个是`any`，相互赋值
            + 整体类型一致才能进行以上三种方式的比较

            返回值：

            + 类型不同，不能赋值
            + 参数类型有一个是`any`，相互赋值

            函数**参数**满足**逆变**，**返回值**满足**协变**



+ **使用场景：**

    在下例`loggingIdentity`中，想要访问`arg`的`length`属性，但 TS 编译器不能证明每种类型都有`length`属性所以报错：

    ```typescript
    function loggingIdentity<T>(arg: T): T {
        console.log(arg.length);  // Error: T doesn't have .length
        return arg;
    }
    ```

    相比于操作any所有类型，我们想要限制函数去处理任意带有`.length`属性的所有类型，即至少包含这一属性。为此，需列出对`T`的约束要求。

+ **使用：**

    ```typescript
    interface Lengthwise {
        length: number;
    }
    
    function loggingIdentity<T extends Lengthwise>(arg: T): T {
        console.log(arg.length);  
        return arg;
    }
    ```

    现在这个泛型函数被定义了约束，因此它不再是适用于任意类型：

    ```ts
    loggingIdentity(3);  // Error, number doesn't have a .length property
    ```



## 泛型条件类型

TS 引入了条件类型使得我们可以根据某些条件得到不同的类型，这里的条件是**类型兼容性约束**——（即便使用了`extends`，也不一定强制满足继承关系，而是检查是否满足**结构兼容性**）



+ 条件类型会以一个**条件表达式**进行类型关系检测，从而在两种类型中选择其一：

    ```ts
    T extends U ? X : Y
    ```

    若 `T` 能够赋值给 `U`，那么类型是 `X`，否则为 `Y`。



+ 条件类型表达式中，通常还会结合 **`infer`** 关键字与**泛型<>**，实现**类型抽取**：

    ```ts
    interface Dictionary<T> {
      [key: string]: T;
    }
    type StrDict = Dictionary<string>;
    
    type DictMember<T> = T extends Dictionary<infer V> ? V : never;
                                              
    type strDict = DictMember<string> // never
    type StrDictMember = DictMember<StrDict> // string
    ```

    当类型`T`满足`T extends Dictionary`约束，我们会用`infer`声明类型变量`V`并返回；否则返回`never`（使用到类型`DictMember`时(*行 8、9*)我们才知道这里的`T`是多少，才能得知`DictMember`类型），如上例的`V`相当于行 9 得到的`string`。

    

+ 利用条件类型和**`infer`**，还可以方便地**获取 Promise 对象返回值类型**：

    ==。。。==



+ `infer`推导的名称相同且都处于逆变位置：推导结果交叉类型（行 7、12）：

    ```ts
    type Bar<T> = T extends {
      a: (x: infer U) => void;
      b: (x: infer U) => void;
    } ? U : never;
    
    type B1 = Bar<{
      // type B1 = string
      a: (x: string) => void;
      b: (x: string) => void;
    };
    type B2 = Bar<{
      // type B2 = never(string&number)	
      a: (x: string) => void;
      b: (x: number) => void;
    }>; 
    ```

    

+ `infer`推导的名称相同且都处于协变位置：推导结果联合类型

    ```ts
    type Bar<T> = T extends {
      a: infer U;	
      b: infer U;
    } ? U : never;
    
    type B2 = Bar<{
      // type B2 = string | number
      a: string; 
      b: number 
    }> 
    ```

    



## 泛型内置工具类型

## 操作符

+ <span style="font-size:20px">typeof</span>

+ <span style="font-size:20px">keyof：</span>

    返回一个联合类型——获取某类型的所有键

    ```ts
    interface Person { name: string, age: number; }
    type K1 = keyof Person; // "name" | "age"
    ```

    

### Partial

将所有属性全部变为可选属性`?`

+ <span style="font-size:20px">源码：</span>

    ```ts
    type Partial<T> = {
      [p in keyof T]?: T[p];
    };
    ```

    首先通过`keyof`拿到`T`的所有属性名，用`in`遍历并赋值给`p`，通过`T[p]`取得相应属性值。中间的`?`将所有属性变为可选属性。

+ <span style="font-size:20px">使用实例：</span>

    ```ts
    interface Todo {
      title: string;
      description: string;
    }
    function updateTodo (todo: Todo, fieldsToUpdate: Partial<Todo>) {
      return { ...todo, ...fieldsToUpdate };
    }
    ```

    在`updateTodo`中，利用`Partial<T>`工具类型，定义`fieldsToUpdate`类型为`Partial<Todo>`，即：

    ```ts
    {
      title?: string | undefined;
      description?: string | undefined;
    }
    ```



### Readonly

将所有属性变为只读属性

+ <span style="font-size:20px">源码：</span>

    ```ts
    type Readonly<T> = {
      readonly [p in keyof T]: T[p];
    };
    ```

+ <span style="font-size:20px">使用：</span>

    ````ts
    type A = Readonly<{ name: string }>;
    // type A = { readonly name: string }; 
    ````



### Required

将所有属性变为必选属性

+ <span style="font-size:20px">源码：</span>

    ```ts
    type Required<T> = {
      [p in keyof T]-?: T[p];
    }
    ```

+ <span style="font-size:20px">使用：</span>

    ```ts
    type C = Required<{ name?: string }>;
    // type C = { name: string }
    ```

    

### Record

<span style="font-size:20px">源码</span>

**`Record<K extends keyof any, T> `** 的作用是将 `K` 中所有属性的值转化为 `T` 类型

索引 - 值 的类型（其中`T`表示传入的键（联合类型）、`K`表示键值的类型

```ts
type Record<T extends keyof any, K> = {
  [p in T]: K;
}
```

<span style="font-size:20px">使用</span>

<span style="color:blue">EG1: 约束键对应值类型</span>

```ts
type E = Record<"name" | "age", string>
/* type E = {
    name: string;
    age: string;
} */
```

<span style="color:blue">EG2: 代替 object</span>

```typescript
type E = Record<string, any>
/**
 * type E = {
 *  [x: string]: any;
 * }
 */
```



### Pick

选择需要的键组成对象（淘汰掉不需要的键）。

+ <span style="font-size:20px">源码：</span>

    ```ts
    type Pick<T, K extends keyof T> = {
      [p in K]: T[p]; 
    }
    ```

+ <span style="font-size:20px">使用：</span>

    ```ts
    type F = Pick<{ name: string, age: number }, "age">
    // type F = { age: number }
    ```



### Exclude

剔除不想要的联合类型（与`Pick`功能相对应）。

+ <span style="font-size:20px">源码：</span>

    从`T`找出`K`中没有的元素返回。

    ```ts
    type Exclude<T, K> = T extends K ? never : T;
    ```

+ <span style="font-size:20px">使用：</span>

    (区分于`Pick`的使用)。

    ```ts
    type G = Exclude<{ "name" | "age"}, "name">
    // type G = "age"
    ```



### Omit

删除对象中的键。

+ <span style="font-size:20px">源码：</span>

    ```ts
    type Omit<T, K extends keyof T> = Pick<T, Exclude<keyof T, K>
    ```

    `Pick`从对象`T`中选择需要的键——`Exclude<keyof T, K`。

+ <span style="font-size:20px">使用：</span>

    ```ts
    type H = Omit<{ name: string; age: number }, "age">;
    // type H = {name: string}
    ```

 

# 类

类里面必须写清楚具体实现（与抽象类不同的地方）

`typeof Myclass`类本身，`Myclass`修饰的是作为值的实例对象

抽象类不可以赋值给类

+ Demo:

    ```tsx
    class Solution {
        nums: number[];
      
        constructor(nums: number[]) {
            this.nums = nums;
        }
    
        pick(target: number): number {
    			return 0;
        }
    }
    ```

    > **Note：与** JS 不同的是，属性必须先在类里面声明类型。



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

抽象类可以接受所有赋值（要保证里面是一样的）



# 命名空间 `namescpace`

组织代码，保证其不会命名冲突。相当于一个模块。

```typescript
namespace X {
    let age: number;
}
namespace Y {
    let age: number;
}
```



# 自定义类型方法

+ 推断返回值类型：

    ```ts
    function getValue<T, K extends keyof T>(obj: T, key)
    
    let res = getValue({ name: "Jim", age: 18 }, "age")
    ```

    这时停在`res`，就会显示`let res: number`；如果没有用`ts`自定义方法实现，显示的是`let res: any`。

+ 推出元组的第一个元素：

    ```ts
    type Shift<T extends Array<any>> = ((...args: T) => any) extends (
                rest: infer R,
                ...args: any
            ) => any
            ? R
            : any;
    
    // type A = number
    type A = Shift<[number, boolean, string]>;
    ```

+ 取代：

    ```ts
    type RepalceValueByOwnKey<T, K> = {
            [p in keyof T]:  p extends keyof K ? K[p] : never;
        };
    type B = RepalceValueByOwnKey<[string, number], [number, string, number]>
    ```



# REF

+ ：

    [一份不可多得的 TS 学习指南（1.8W字）- 掘金](https://juejin.cn/post/6872111128135073806)

    [TypeScript中文网 - TypeScript——JavaScript的超集](https://www.tslang.cn/docs/handbook/basic-types.html)

+ 泛型：

    [一文读懂 TypeScript 泛型及应用（7.8K字）- 掘金](https://juejin.cn/post/6844904184894980104)

