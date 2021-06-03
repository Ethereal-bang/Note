# TypeScript 的编译

+ **编译器**：ts-loader、awesome-typescript-loader、**babel-loader**。因为本处使用`create-react-app`，因此默认的编译器是`babel-loader`。



+ <span style='font-size:20px'>编译器配置文件：tsconfig.json</span>

    <hr>

    编译器启动时，首先读取`tsconfig.json`，以获取有关如何编译项目的说明

    `"complilerOptions"`定义编译器的工作方式，在其中加上一个属性`noImplicitAny: false`意为不需要显示地声明变量类型`any`，实现 js 与 ts 的混合编写

    各语句含义：<img src="C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20210603200542608.png" alt="image-20210603200542608" style="zoom: 80%;" />



# Interface

