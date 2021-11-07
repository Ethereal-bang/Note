# 配置

## Java SE

+ JDK 和 JRE：

    > JDK是Java的开发工具，它不仅提供了Java程序运行所需的JRE，还提供了一系列的编译，运行等工具，如javac，java，javaw等。 JRE只是Java程序的运行环境，它最核心的内容就是JVM（Java虚拟机）及核心类

    

+ 环境变量：

    1. 设置一个`JAVA_HOME`的环境变量，指向JDK的安装目录
    2. `JAVA_HOME`的`bin`目录附加到系统环境变量`PATH`上

    配置失败 VS 配置成功：

    <img src="https://gitee.com/ethereal-bang/images/raw/master/20211105151520.png" alt="image-20211105151520808" style="zoom: 67%;" />



## IDEA



# Java 程序编译

创建文件 `.java`（文件名需与类名一致）

执行文件：（`javac`接文件名，`java`接类名

```shell
$ javac Hello.java
$ java Hello
```



# 基本语法

## 程序结构

程序的基本单位是`class`

```java
// Hello.java
public class Hello {
  // 方法：
  public static void showTriangle(int n) {
    // code...
  }
  // 入口方法：
  public static void main(String[] args) {
    showTriangle(4);	// 调用方法
  }
}
```

+ 类名同文件名
+ `public static void main(String[] args)`固定的必要写法，程序的入口方法



## 数据类型

+ **类型转换：**

    转换规则：从低位类型到高位类型自动转换；从高位类型到低位类型需要强制类型转换

    ![image-20211106104104976](https://gitee.com/ethereal-bang/images/raw/master/20211106104112.png)



## 输入输出

+ `println`：换行输出
+ `print`：不换行



## 运算符

+ `/`除法
+ `%`取余



# 参考链接

[Java教程 - 廖雪峰的官方网站](https://www.liaoxuefeng.com/wiki/1252599548343744)

