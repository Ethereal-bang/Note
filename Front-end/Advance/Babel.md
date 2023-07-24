# Babel

JavaScript 编译器，把新版的 JS 编译成当下可以执行的版本

+ **Babel 运行原理：**

    ![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/1/14/1684a0e6de6509d6~tplv-t2oaga2asx-zoom-in-crop-mark:1304:0:0:0.awebp)

    1. Babel/parser——解析
    2. Babel/traverse——遍历
    3. Babel/generator——生成



## AST

[AST Explorer](http://astexplorer.net/) 可以帮助理解 AST 节点。

+ <span style="font-size:20px">Node 节点：</span>

    AST 的每一层具有相同的结构称为节点。组合在一起可以描述用于静态分析的程序语法

    + 每一个节点都有如下 Interface：

        ```ts
        interface Node {
          type: string;
        }
        ```

    + 每一个节点都会有`start`、`end`、`loc`属性——描述在原始代码中位置



## Babel 处理步骤

### Parse——@babel/parser

接收代码输出为 AST。这个步骤分为**词法分析**[^ 1]、**语法分析**

#### Lexical Analysis

将原始代码转化成 **Tokens**(*最小单元的词语数组* ) 流，这里 Token 都会加上相应的类型。

+ Eg：

    ```js
    n * n
    ```

    Tokens like：

    ![image-20220320142115127](https://gitee.com/ethereal-bang/images/raw/master/20220320142122.png)

    每一个 type 有一组属性描述该 token：

    ```js
    {
      type: {
        label: 'name',
        keyword: undefined,
        beforeExpr: false,
        startsExpr: true,
        rightAssociative: false,
        isLoop: false,
        isAssign: false,
        prefix: false,
        postfix: false,
        binop: null,
        updateContext: null
      },
      ...
    }
    ```

#### Synatactic Analysis

将 Tokens 流转换成 AST 形式，易于后续操作



### Transform——@babel/traverse

接收 AST 并对其遍历过程中对节点进行添加、更新、移除等操作。是编译器中最复杂的部分。

+ <span style="font-size:22px">递归的树形遍历：</span>



### Generator——@babel/generator

将上一步转换之后的 AST 转换成字符串形式代码，同时创建源码映射 Source maps。



# REF

+ Babel：

    [[实践系列]Babel原理 - 掘金](https://juejin.cn/post/6844903760603398151)

    [babel-handbook/plugin-handbook.md at master · GitHub](https://github.com/jamiebuilds/babel-handbook/blob/master/translations/zh-Hans/plugin-handbook.md#%E6%8A%BD%E8%B1%A1%E8%AF%AD%E6%B3%95%E6%A0%91asts)



[^ 1]: Lexical Analysis