# 配置

## Java SE

+ JDK 和 JRE：

    > JDK是Java的开发工具，它不仅提供了Java程序运行所需的JRE，还提供了一系列的编译，运行等工具，如javac，java，javaw等。 JRE只是Java程序的运行环境，它最核心的内容就是JVM（Java虚拟机）及核心类

    

+ 环境变量：

    1. 设置一个`JAVA_HOME`的环境变量，指向JDK的安装目录
    2. `JAVA_HOME`的`bin`目录附加到系统环境变量`PATH`上




## IDEA



# Java 程序编译

创建文件 `.java`（文件名需与类名一致）

执行文件：（`javac`接文件名，`java`接类名

```shell
$ javac Hello.java
$ java Hello
```



# 基本语法

+ <span style="font-size:20px">命名规范：</span>

    ​	（如不遵守，编译亦可通过）

    + 包名：多单词组成时所有字母都小写（xxxyyyzzz）
    + 类名、接口名：多单词组成时，所有单词的首字母大写（XxxYyyZzz）
    + 变量名、方法名：多单词组成时，第一个单词首字母小写，第二个单词开始每个单词首字母大写（xxxYyyZzz）
    + 常量名：所有字母都大写。多单词时每个单词用下划线连接（XXX_YYY_ZZZ）



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
+ 一个`.java`文件可以有很多类，但只能有一个`public`类



## 数据类型

+ **类型转换：**

    转换规则：从低位类型到高位类型自动转换；从高位类型到低位类型需要强制类型转换

    ![image-20211106104104976](https://gitee.com/ethereal-bang/images/raw/master/20211106104112.png)



## 输入输出

+ <span style="font-size:20px">输出：</span>

    + `println`：换行输出

        连接用`+`。

    + `print`：不换行

    + `printf`：格式化输出

        ```java
        System.out.printf("%4d", tri[i][j]);
        ```

+ <span style="font-size:20px">输入：</span>

    + 从键盘获取不同类型的变量，需要使用`Scanner`类

    1. 导包：

        ```java
        import java.util.Scanner;
        ```

        

    2. `Scanner`的实例化：

        ```java
        Scanner scan = new Scanner(System.in);
        ```

    3. 调用`Scanner`类相关方法获取指定类型变量，如：

        ```java
        int num = scan.nextInt();
        ```

        | Method          | Description                           |
        | :-------------- | :------------------------------------ |
        | `nextBoolean()` | Reads a `boolean` value from the user |
        | `nextByte()`    | Reads a `byte` value from the user    |
        | `nextDouble()`  | Reads a `double` value from the user  |
        | `nextFloat()`   | Reads a `float` value from the user   |
        | `nextInt()`     | Reads a `int` value from the user     |
        | `nextLine()`    | Reads a `String` value from the user  |
        | `nextLong()`    | Reads a `long` value from the user    |
        | `nextShort()`   | Reads a `short` value from the user   |

    





## 运算符

+ `/`除法
+ `%`取余



## 数组

+ **长度**：`.length`：

    数组一旦初始化完成，其长度就确定了



+ <span style="font-size:22px">一维数组：</span>

    ```java
    int arr;	// 声明
    arr = new int[]{1001,1002,1003,1004};//静态初始化：数组的初始化和数组元素的赋值操作同时进行
    
    String[] arr = new String[5];//动态初始化：数组的初始化和数组元素的赋值操作分开进行
    ```

    数组元素**默认初始值**：

    | 数组元素类型 |      默认初始化值      |
    | :----------: | :--------------------: |
    |     整型     |           0            |
    |    浮点型    |          0.0           |
    |    字符型    | 0（ASCII码）或'\u0000' |
    |    布尔型    |         false          |
    | 引用数据类型 |          null          |



+ <span style="font-size:22px">二维数组：</span>

    **初始化**：

    ```java
    //静态初始化
    int[][] arr1 = new int[][]{{1,2,3},{4,5},{6,7,8}};
    
    //动态初始化
    String[][] arr2 = new String[3][2];
    String[][] arr2 = new String[3][];
    ```

    **默认初始化值**：

    + 对于初始化方式一 `String[][] arr2 = new String[3][2]`：

        外层元素的初始化值：地址值

        内层元素的初始化值：与一维数组初始化情况相同

    + 对于初始化方式二 `String[][] arr2 = new String[3][]`：

        外层元素的初始化值：`null`

        内层元素的初始化值：不能调用，否则报错



+ **传值：**

    向函数传递时：

    ```java
    zeroToRear({1, 2, 0, 3, 4});	// ×
    
    int nums[] = {1, 2, 0, 3, 4}
    zeroToRear(nums);	// √
    
    zeroToRear(new int[]{1, 2, 0, 3, 4});	// √
    ```

    


## 数组遍历

+ <span style="font-size:20px">for each：</span>

    ```java
    public static void zeroToRear(int[] nums) {
      for (int n : nums) {
        // 这里取得的`n`是数组元素而不是索引
      }
    ```

    

# 类与对象

面向对象的步骤：

1.创建一个类

2.创建类的对象

+ <span style="font-size:20px">类的实例化：</span>

    创建类的对象即类的实例化

    ```java
    Person p1 = new Person()
    ```

    匿名对象：

    ```java
    new Phone().sendEmail();
    new Phone().price = 1999;
    ```

+ <span style="font-size:20px">public 与 private：</span>

    属性：`public`定义的属性外部可以直接访问，`private`定义的属性外部只能通过调用方法间接修改

    方法：`private`方法只能在内部方法调用

+ <span style="font-size:20px">this 变量：</span>

    

## 构造方法

+ <span style="font-size:20px">定义构造方法：</span>

    `public <类名>() {}`

    ```java
    class Person {
        private String name; 
      	// 构造方法：
        public Person() {	
        }
    }
    ```

+ <span style="font-size:20px">多构造方法：</span>

    

## 继承

`class A extends B`



## 包装类

每个基本类型都在 java.lang 包中有一个响应的包装类。包装类式一个基本类型的**对象**，具有属性和方法。在基本数据类型需要用对象表示时使用

```java
Integer i = new Integer(1);
```

装箱：基本类型转换为包装类的对象

拆箱：包装类对象转换为基本类型的值



+ <span style="font-size:20px">String、StringBuffer：</span>

    经常改变内容的字符串最好不使用`String`；字符串经常改变的情况可使用`StringBuffer`，更高效.



# 参考链接

[Java教程 - 廖雪峰的官方网站](https://www.liaoxuefeng.com/wiki/1252599548343744)

