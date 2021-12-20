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

+ `public static void main(String[] args)`固定的必要写法，程序的入口方法（必须是静态）

+ 一个`.java`文件可以有很多类，但只能有一个`public`类

+ 一个 Java 程序往往只需要一个入口（即只一个类包含`main`方法），其他类用于被`main`方法直接或间接调用

+ <span style="font-size:20px">主类：</span>

    类的名字和文件名一致，并且包含`main`函数的类，叫做主类

    - 类名字和文件名一致
    - 包含`public static void main(String[] args) {}`



## 数据类型

+ **类型转换：**

    转换规则：从低位类型到高位类型自动转换；从高位类型到低位类型需要强制类型转换

    ![image-20211106104104976](https://gitee.com/ethereal-bang/images/raw/master/20211106104112.png)



+ **按值传递**：==?==

    Java 是按值传递的，只不过对于对象参数，值的内容是对象的引用

    

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

+ **打印数组：**

    ```java
    Arrays.toString(arr);	// j
    ```

    

+ <span style="font-size:22px">一维数组：</span>

    ```java
    int arr;	// 声明
    arr = new int[]{1001,1002,1003,1004};//静态初始化：数组的初始化和数组元素的赋值操作同时进行
    
    String[] arr = new String[5];//动态初始化：数组的初始化和数组元素的赋值操作分开进行（长5）
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

    

### 数组遍历

+ <span style="font-size:20px">for each：</span>

    ```java
    public static void zeroToRear(int[] nums) {
      for (int n : nums) {
        // 这里取得的`n`是数组元素而不是索引
      }
    ```



## 集合

### List

+ **集合框架：**

    <img src="https://gitee.com/ethereal-bang/images/raw/master/20211203212945.png" height="250px">

集合类中，List 是最基础的一种集合——有序列表。

```java
List<List<Integer>> list = new ArrayList<>();
```

+ <span style="font-size:22px">ArrayList——数组队列：</span>

    ArrayList 类是一个可以动态修改的数组队列，与普通数组的区别就是它是没有固定大小的限制，可以添加或删除元素

    + **适用场景：**频繁访问列表中元素；只需在列表末尾进行添加删除元素操作

    + 添加元素：`add()`
    + 访问元素：`get()`

+ <span style="font-size:22px">LinkedList——链表：</span>

    链表类似于 ArrayList，是一种常用的数据容器

    + **适用场景：**需要通过循环迭代访问列表中元素；频繁在列表开头、中间、末尾等位置添加删除元素操作



### 栈

```java
Stack<Integer> stack = new Stack<Integer>();
```

+ stack.peek()：获得栈顶值
+ stack.pop()：删除并返回栈顶值
+ stack.add()：压入栈顶



# 类与对象



## 属性和方法

### 方法重载 Oveload

方法的重载是类名的不同表现形式，其实构造函数也是重载的一种，另一种就是方法的重载

```java
public class Apple {
  int sum;
  String color;
  
  // 构造函数`Apple`的重载
  public Apple() {}
  public Apple(int sum) {}	
  
  // 方法`getApple`的重载
  public int getApple(int num) {
    return 1;
  }
  public String getApple(String color) {
    return "color";
  }
}
```

几个相同的名字 Java 是如何判断调用的是哪一个方法呢：每个重载的方法都有独一无二的参数列表。如上例的`int num`与` `，使用时：

![image-20211129183650384](https://gitee.com/ethereal-bang/images/raw/master/20211129183657.png)

```java
a.getApple(1)	// 1
a.getApple("red")	// color
```

+ 重载的**条件**：
    1. <span style="color:red">方法名称</span>必须相同
    2. <span style="color:red">参数列表</span>必须不同——个数、类型、参数类型排列顺序满足一个因素就能构成
    3. 仅仅返回类型不同不足成为方法的重载
    4. 重载发生在<span style="color:red">编译时</span>——因为编译器可以根据参数的类型选择使用哪个方法



### 方法重写 Override

+ **重写 与 重载**：Override 指的是<span style="color:red">子类和父类之间</span>，而重载指的是<span style="color:red">同一类中</span>
+ **重写的条件：**
    1. <span style="color:red">外壳相同</span>——Override 的方法（返回值类型、方法名、参数列表）要和父类保持一致
    2. 子类中重写方法的<span style="color:red">访问权限</span>不能低于父类方法的访问权限

Override 的好处是子类可以根据需要定义实现父类方法



### 构造方法

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



## 包装类

每个基本类型都在 java.lang 包中有一个响应的包装类。包装类式一个基本类型的**对象**，具有属性和方法。在基本数据类型需要用对象表示时使用

```java
Integer i = new Integer(1);
```

装箱：基本类型转换为包装类的对象

拆箱：包装类对象转换为基本类型的值

+ Type argument cannot be of primitive type——类型参数不能是原始类型：见[Why don't Java Generics support primitive types? - Stack Overflow](https://stackoverflow.com/questions/2721546/why-dont-java-generics-support-primitive-types)

+ <span style="font-size:20px">String、StringBuffer：</span>

    经常改变内容的字符串最好不使用`String`；字符串经常改变的情况可使用`StringBuffer`，更高效.



## static 关键字

可以用来修饰：属性、方法、代码块、内部类

+ <span style="font-size:22px">修饰属性：</span>

    静态属性（类变量）、非静态属性（实例变量）

    + **实例变量**：类的每个对象都拥有一套类的<span style="color:red">非静态属性</span>。在一个对象中修改不会影响到其他对象

    + **静态变量**：多个对象共享类的同一静态变量

        随着类的加载而加载，可通过`<类>.<静态变量>`调用

        因为类只加载一次，静态变量在内存中也只存一份——存在于<span style="color:red">方法区的静态域</span>中

    + 变量的**调用权限**：

        <table>
          <thead>
            <tr>
              <td> </td>
              <td><strong>类变量</strong></td>
              <td><strong>实例变量</strong></td>
            </tr>
          </thead>
          <tbody>
          	<tr>
            	<td>类</td>
              <td>√</td>
              <td> </td>
            </tr>
            <tr>
            	<td>实例对象</td>
              <td>√</td>
              <td>√</td>
            </tr>
          </tbody>
        </table>

    + **使用场景**——如何确定一个属性是否要声明为 static：

        属性可被多个对象所共享的；类中的常量也常声明为 static。

+ <span style="font-size:22px">修饰方法：</span>

    + **静态方法**只能调用静态的方法或属性

        其内不能使用`this`、`super`关键字

    + **非静态方法**既可调用非静态的方法或属性，也可以调用静态的方法或属性

    + **使用场景**——如何确定一个方法是否要声明为 static 的：

        操作静态属性的方法；工具类中的方法(*如`Math`、`Arrays`*)==？==

+ <span style="font-size:22px">静态代码块：</span>

    可用于类的初始化操作，进而提升程序性能：

    ```java
    public class StaticBlock {
      static {
        System.out.println("I`m a static code block");
      }
    }
    ```

    **使用场景：**由于静态代码块随类的加载指向，很多时候将<span style="color:red">只需进行一次的初始化操作</span>放在 static 代码块中执行



## package 包

+ **包的作用：**

    功能相近的类放到同一个包方便查找和使用；一定程度避免命名冲突；访问权限可以以 package 为单位

```java
import java.util.*;
```





## Interface 与 Abstract

+ <span style="font-size:22px">接口 interface：</span>

    接口相当于对外的一种约定和标准

    + **定义**：我们可以像下面这样定义一个接口：

        ```java
        public interface Compute {}
        ```

        Interface 只能使用两种访问修饰符——static 对整个项目可见、default 只具有==包访问权限？==

    + **特征**：

        Interface 是完全<span style="color:red">抽象的类</span>，不会提供方法的实现，只是进行方法的定义

        接口允许<span style="color:red">多继承</span>，一个子类可以同时使用`implements`实现若干个接口

    + **使用：**

        当一个具体的`class`去实现一个`interface`时，需要使用<span style="color:red">`implements`</span>关键字

        当子类需要<span style="color:red">既继承抽象类又实现接口</span>时，先`extends`一个抽象类而后使用`implements`实现多个接口

        <span style="color:red">接口的实现</span>必须实现接口的全部方法，否则应该定义为抽象类

+ <span style="font-size:22px">抽象类——abstract：</span>

    抽象类是一种抽象能力弱于 Interface 的类

    + **定义——**可以像这样定义抽象类：

        ```java
        public class Main {
          public static void main(String[] args) {
            Person p = new Student();
         	  p.run();
          }
        }
        abstract class Person {
          public abstract void run();
        }
        class Student extends Person {
          @override
          public void run() {
            System.out.println("Student.run");
          }
        }
        ```

        

        

# 面向对象

+ 面向对象的**基本概念**：类、实例、方法

+ 面向对象的**实现方式**：继承、多态

+ 面向对象的三大**基本特性**：封装、继承、多态

    

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

    

## 封装——访问控制权限

Java 中成员的控制权限共有四种，`public`、`protected`、`default`、`private`，可见性如下：

![image-20211128140531598](https://gitee.com/ethereal-bang/images/raw/master/20211128140538.png)



## 类间的关系模型

### 继承

`class A extends B`

### 组合

就是将对象引用置于新类中。如果想让类具有更多扩展功能：多用组合，少用继承

```java
public class SoccerPlayer {
  private String name;
  private Soccer soccer;
}

public class Soccer {
  private String soccerName;
}
```

`SoccerPlayer`引用了`Soccer`类，通过引用`Soccer`类，来达到调用`soccer`中属性和方法的效果

+ <span style="font-size:20px">组合与继承的区别：</span>

    ![image-20211128154552889](https://gitee.com/ethereal-bang/images/raw/master/20211128154553.png)

### 代理

代理的大致描述是：A 想要调用 B 类中方法，不直接调用，在自己的类中创建一个 B 对象的<span style="color:red">代理</span>，再由代理调用 B 的方法



## 多态——Polymorphic

+ <span style="font-size:22px">多态：</span>

    多态是指，针对某个类型的方法调用，其真正执行的方法取决于运行时<span style="color:red">实际类型</span>的方法——运行时期才能动态决定调用的子类方法

    对某个类型调用某种方法，执行的实际方法可能是某个子类的 override 方法

    封装和继承是多态的基础，也就是说多态只是一种<span style="color:red">表现形式</span>

+ <span style="font-size:20px">多态的作用：</span>

    多态具有一个非常强大的功能，就是允许添加更多类型的子类实例实现功能扩展，却不需修改基于父类的代码

+ <span style="font-size:20px">实现多态：</span>

    实现多态具有三种**充要条件**：继承、重写父类方法、父类引用指向子类对象：

    ```java
    public class Fruit {
       int num;
       public void eat() {
       		System.out.println("eat Fruit");
     	 }
    }
    public class Apple extends Fruit{
     	@Override
     	public void eat() {
     		super.num = 10;
     		System.out.println("eat " + num + " Apple");
     	}
     	public static void main(String[] args) {
     		Fruit fruit = new Apple();	// 虽然是Fruit类型但实际调用的是Apple的方法
     		fruit.eat();
     	}
    }
    ```

    `Fruit fruit = new Apple()`，`Fruit`类型的对象指向了`Apple`对象的引用，这就是多态——父类引用指向子类对象，因为`Apple`继承于`Fruit`，且覆写了`eat`方法，所以能表现除多种状态的形式

 

# 注解和反射

## 注解——Java.Annotation

注解是放在 Java 源码的类、方法、字段、参数前的一种特殊“注释”

**注释**会被编译器直接忽略，**注解**则可以被编译器打包进入class文件，因此，注解是一种用作标注的“元数据”。

+ <span style="font-size:22px">注解分类：</span>

    1. 编译器使用的注解：

        如`@Override`、`@SuppressWarnings`——告诉编译器忽略此处代码产生的警告

        这类注解不会被编译进入`.class`文件

    2. 工具处理`.class`文件使用的注解：

        这类注解会被编译进入`.class`文件，但加载结束后并不会存在于内存中。只被一些底层库使用，一般我们不必自己处理。

    3. 第三类是在程序运行期能够读取的注解：

        加载后一直存在于 JVM 中，这也是最常用的注解

+ <span style="font-size:22px">元注解——meta-annotation：</span>

    元注解负责注解其他注解，Java 定义了 4 个标准 meta-annotation 类型 提供对其它 annotation 类型的说明，这些类型和它们支持的类在 java.lang.annotation 包可以找到：

    + **@Target**——描述注解使用范围
    + **@Retention**——描述注解生命周期，（SOURCE<CLASS<RUNTIME）
    + **@Document**——注解将被包含在 javadoc
    + **@Inherited**——子类可以继承父类中的该注解

    ```java
    @Target(ElementType.METHOD)
    public @interface Report {
        int type() default 0;
        String level() default "info";
        String value() default "";
    }
    ```

+ <span style="font-size:22px">定义注解：</span>

    1. 使用`@interface`定义注解：

        ```java
        public @interface Report {
        }
        ```

    2. 添加参数、默认值：

        ```java
        public @interface Report {
          	// 注解参数：<参数类型> <参数名>()
            int type() default 0;
            String level() default "info";
            String value() default "";
        }
        ```

        这里的`type`、`level`、`value`都是注解的参数，最常用的参数定义为`value()`，所有参数都尽量设置默认值。

    3. 元注解配置注解：

        ```java
        @Target(ElementType.TYPE)
        @Retention(RetentionPolicy.RUNTIME)
        public @interface Report {
            int type() default 0;
            String level() default "info";
            String value() default "";
        }
        ```

        必须设置`@Target`和`@Retention`，`@Retention`一般设置为`RUNTIME`，因为我们自定义的注解通常要求在运行期读取。一般情况下，不必写`@Inherited`和`@Repeatable`。

## 反射——Java.Reflection

+ <span style="font-size:22px">什么是 Reflection：</span>

    由于 JVM 为每个加载的`class`创建了对应的`Class`实例，并在实例中保存了该`class`的所有信息(*包括类名、包名、父类、实现的接口、所有方法、字段等* )，因此，如果获取了某个`Class`实例，我们就可以通过这个`Class`实例获取到该实例对应的`class`的所有信息。

    这种通过`Class`实例获取`class`信息的方法称为反射

    反射是为了解决在运行期，对某实例一无所知情况下，如何调用其方法。

+ <span style="font-size:22px">反射相关主要 API：</span>

    + java.lang.Class——代表一个类
    + java.lang.reflect.Method——代表类的方法
    + java.lang.reflect.Field——类的成员变量
    + java.lang.Constructor——类的构造器

+ <span style = "font-size:22px">获得反射对象：</span>

    1. 若已知具体的类。

        ```java
        Class clazz = Person.class;
        ```

        最安全可靠、性能最高。

    2. 已知某类实例

        ```java
        Class clazz = person.getClass();
        ```

    3. 已知类的全类名

        ```java
        Class cls = Class.forName("com.user.domain.Account")	// "完整类名"
        ```

        这里得到的`cls`就可以通过各种方法获得该类的所有信息。

    4. 内置基本数据类型，直接用“类名.Type”

    5. 利用 ClassLoader

        

# 参考

[Java教程 - 廖雪峰的官方网站](https://www.liaoxuefeng.com/wiki/1252599548343744)

+ 基本语法：

    [Java文件的命名，Java主类，Java中的public类 - CSDN](https://blog.csdn.net/darlingwood2013/article/details/96105142)

    [使用LIst - 廖雪峰的官方网站](https://www.liaoxuefeng.com/wiki/1252599548343744/1265112034799552)

    [Java ArrayList | 菜鸟教程](https://www.runoob.com/java/java-arraylist.html)

    [什么是值传递，什么是引用传递．为什么说Ｊａｖａ中只有值传递 - CSDN](https://blog.csdn.net/w372426096/article/details/82216742)

+ 类与对象：

    [《Java 基础核心总结》- cxuan]()

+ 面向对象：

    [《Java 基础核心总结》- cxuan]()
    
    [多态 - 廖雪峰的官方网站](https://www.liaoxuefeng.com/wiki/1252599548343744/1260455778791232)

+ 注解和反射：

    [【狂神说Java】注解和反射_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1p4411P7V3?p=1&share_medium=android&share_plat=android&share_session_id=925bf718-1fd1-4813-b18d-bf4c4f0fa085&share_source=QQ&share_tag=s_i&timestamp=1639368905&unique_k=KZPiLTv)

    [使用注解 - 廖雪峰的官方网站](https://www.liaoxuefeng.com/wiki/1252599548343744/1265102413966176)

    
