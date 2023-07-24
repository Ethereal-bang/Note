# 搭建环境

## Python 基本开发环境

![image-20210304220854041](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20210304220854041.png)

Python： 命令行模式为实时编译，适合语句练习

cmd 内 输入`python` 进入交互环境，`exit()` 退出

IDLE:  打开默认为 IDLE Shell 模式，实时编译；或打开 `.py`后缀文件，Run 才运行

## 基于 Anaconda 的 Spyder

### Anaconda

包管理与环境管理 + Spyder 开发环境

**下载地址**：  [官网下载地址](www.anaconda.com/products/individual)
[清华大学开源软件镜像站](https://mirrors.tuna.tsinghua.edu.cn/anaconda/archive/)
[Anaconda官方镜像地址](https://repo.anaconda.com/archive/)



安装<img src="C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20210304223630353.png" alt="image-20210304223630353" style="zoom:50%;" />

### Spyder

Spyder 是为数据科学而开发的。它是开源工具，能够与大量平台兼容，因而成为 IDE 新手用户的更好选择。为实现完美开发，它合并了多个关键库，如 NumPy、Matplotlib 和 SciPy。	

安装：[官网](https://www.spyder-ide.org/)



**Spyder 的三个工作空间**：

![image-20210305220449496](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20210305220449496.png)

蓝色窗格有四个标签页：查看系统帮助文件、查看并管理变量、查看绘图以及管理文件

Spyder Ipython 控制台，在“In [*]:”提示符后输入代码即可运行



## VS Code

执行单元	==？==

报错：![image-20210306092725180](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20210306092725180.png)

更新 pip

![image-20210306092638863](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20210306092638863.png)

安装 ipykernel

报错

![image-20210306093132227](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20210306093132227.png)



# 语法规则

Python 是面向对象的编程语言

**单行注释**

Python **注释语句**以`#`开头



**多行注释**：除了用多个`#`，还有`''' `和 `"""`	*单引号双引号*

``` python
'''
多行
注释
'''
```



**行与缩进**：其他每一行都是一个语句，当语句以`:`结尾时，缩进的语句视为代码块。Python使用缩进来组织代码块。



# 数据类型

Python3 的六个标准数据类型中：

+ **不可变数据**：Number、String、Tuple
+ **可变数据**：List、Dictionary、Set



## 数字类型

### 整数

对于很大的数，（*例如 `1000000`*)，很难数清楚0的个数，python 允许在数字中间以`_`分隔



### 浮点数

**运算**：

浮点数之间运算存在不确定位数



浮点数的**科学计数法**：

用`e`替代`10`

1.23 x 10^9^ 就成了`1.23e9`，0.012可以写成`1.2e-2`。



### 复数类型

复数类型中实数和虚数部分的数值都是浮点类型

对于复数`z`，可以用`z.real`和`z.imag`分别获得实数部分和虚数部分

``` python
>>>(1.23e-4+5.76e+89j).imag
5.76e+89
```



## 组合数据类型

### 序列类型

无论是哪一种序列类型，都可以使用相同的索引体系，即正向从 0 递增，反向从 -1 递减

#### Str 字符串

字符串是以``'``或`"`括起来的任意文本，  
如果`'`本身也是一个字符，那就可以用`""`括起来，  
如果字符串内部既包含`'`又包含`"`，可以用转义字符`\`来标识



**转义字符**：转义字符可以转义很多字符，比如`\n`表示换行，`\t`表示制表符，字符`\`本身也要转义，所以`\\`表示的字符就是`\`



<span style='font-size: 19px'>提取字符串</span>

语法格式：`str[头下标：尾下标]`



python 的字符串是有序集合，我们可以通过索引来提取想要获取的字符

python的字串列表有2种取值顺序：

1. 从左到右以 0 开始
2. 从右到左以 -1 开始

下面是图解：

![image-20210321204918751](https://i.loli.net/2021/03/21/uNx1ya9VtMsrJCd.png)

```python
s = 'ilovepython'
s[0]	
# i

s[-11]
# i

# 提取一段字符串
s[0:3]
# ilov
```

有些数字可省略：	==?==

``` python
a = ['python', 123, '.io']
a[::-1]
```



注意：`input`得到的数据类型是`str`



#### List 列表

List 是 Python 中使用最频繁的数据类型。
列表可以完成大多数集合类的数据结构实现，  
列表中元素的类型可以不相同，支持数字、字符串、甚至列表（*嵌套*）



**定义**：列表是写在`[]`之间，用`,`分隔开的元素列表



<span style='font-size: 19px'>截取字符串</span>

图例。

![image-20210321210405379](https://i.loli.net/2021/03/21/hjlkM6xpaTcmV9U.png)



**序列类型的通用操作符**：

序列类型的**通用函数**：



#### Tuple 元组

Tuple 与列表类似，元素类型可以各不相同。不同之处在于 Tuple 的元素不能改。

元组写在`()`里，元素间用`,`隔开



元组与字符串类似，截取等索引规则都是相同的，这里不再赘述。  
其实可以把字符串看作一种特殊的元组。



虽然元组的元素不可变，但能够包含可变的对象，如 list。

### 集合类型：Set



### 映射类型：Map



## 数据类型转换



``` python
tempStr = input("What is the temperature?")
if tempStr[-1] in ['F', 'f']:
    C = (eval(tempStr[0:-1]) - 32) / 1.8
    C1 = int(C)
    print("The converted temperature is {}C".format(C1))
elif tempStr[-1] in ['C', 'c']:
    F = (eval(tempStr[0:-1]) * 1.8) + 32
    F1 = int(F)
    print("The converted temperature is {}F".format(F1))
else:
    print("输入格式错误")
```



# 数值运算操作符

基本操作符

`-x`：`x` 的负值

`+x`：`x `本身

`x**y`：`x `的 `y `次幂

``` python
>>>10/3
3.3333333333333335

>>>10//3
3
>>>10 % 3
1
```







增强赋值操作符



数学函数提供的数值运算功能



# 输入与输出

input('')

`eval()`：去掉引号，直接执行引号内的内容



有无`eval`时，输入语句的区别：	==?==

``` python
a = input('name')
b = eval(input('name'))
```



print()



## 格式化字符串输出值

基本语法是通过`{}`和`:`替代以前的`%`



format 函数可以接受不限个参数，位置无顺序要求 

``` python
>>>"{} {}".format（“hello", "world")	# 不指定位置，则按默认顺序
'hello world'

>>>"{0} {1}".format("hello", "world")	# 指定位置

>>>print("{0}{1} {0}".format("hello", "world"))
# helloworld hello
```



还可设置参数

``` python
print("网站名：{name}, 地址 {url}".format(name="菜鸟教程", url="www.runoob.com")
      
# 通过字典设置参数 ？
      
# 通过列表索引设置参数 ？
```



## 数字格式化

``` python
>>> print("{:.2f}".format(3.141592))
3.14
```





## 字符串的复杂化输出格式

+ **格式化字符串字面值**==?==，简称为 f-字符串	（*可理解为字符串模板*）

  在字符串前添加前缀`f`或`F`，表达式`{}`

  ``` python
  import math	# 引入 math 库
  print(f'The value of pi is approximately {math.pi:.3f}.')	# 
  # The value of pi is approximately 3.142
  
  # 刚才的输出语句中的表达式改动为 {math.pi} 后结果：3.1415926等等
  ```

  







# 变量

赋值：

在Python中，等号`=`是赋值语句，可以把任意数据类型赋值给变量，同一个变量可以反复赋值，甚至可以是不同类型

# 条件判断

## 布尔值

布尔值不属于标准数据类型。

**布尔运算**：

+ 与运算`and`

+ 或运算`or`

+ 非运算`not`

布尔值常用于条件判断中



### if 语句

``` python
s = input('birth: ')
birth = int(s)
if birth < 2000:
    print('00前')
else:
    print('00后')
```



## 紧凑表达形式

适用于二分支结构。



## 条件判断及组合



# 分支结构

## try-except

与 if-else 相似，多分支结构。可执行多个 except 语句



# 循环结构

## 应用

### 字符串遍历

``` python
for c in "python123":	# 在每个字结尾加 ，
    print(c, end=",")
    # p,y,t,h,o,n,1,2,3
 
# 实现这个功能还有另一种更简便的写法
print(','.join('python123'), end=',')
```



## continue 与 break



# 参考链接

[Python 基础教程 | 菜鸟教程](https://www.runoob.com/python/python-tutorial.html)

[Python Documentation contents — Python 3.9.2 documentation](https://docs.python.org/3/contents.html)         

《Python语言程序设计基础（第二版）嵩天 礼欣 黄天羽 著》

