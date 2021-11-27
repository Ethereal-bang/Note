# HTML

## vscode

**新建html文件**：新建文件保存后缀.html，在vscode里输入!tab
**浏览器运行：**alt+b

## html5文档结构

1. `<!DOCTYPE html>`:文档类型声明，表示该文件为 HTML5文件。

2. `<html></html>`

3. `<head></head>`标签对：标签包含有关HTML文档信息，可包含一些辅助性标签如`<title></title>`，`<link />`，`<meta />`，`<style></style>`，`<script></script>`等

  注：浏览器除了会在标题栏只显示`<title>`元素的内容外，不会向用户显示head元素内的其他任何内容。

4. `<body></body>`标签对：
    注：**body**的大小是由**内部元素**撑开，不是默认占满整个页面



## html文件注释

语法
<!--注释文字 -->



## 标签

**标签语义化**好处
1. 更容易被搜索引擎收录
2. 更容易让屏幕阅读器读出网页内容
    标签的语法
4.  HTML标签不区分大小写，<h1>和<H1>是一样的，但建议**小写**，（因大部分程序员都以小写为准


+ `<nav>`标签定义导航栏

+ `<header>`标签定义头部区域

+ `<footer>`标签定义底部区域

+ `<aside>`定义侧边栏区域

+ `<br/>`标签换行

+ `&nbsp;`实现空格标签
+ `<hr>`标签水平分隔线



+ <span style="font-size:20px">列表：</span>

    `<ul><li>`标签实现无序列表：
    ul-li在网页中显示的默认样式一般为：每项li前都自带一个圆点

    `<ol><li>`标签实现有序列表：
    默认样式一般为：每项<li>前都自带一个**序号**，序号默认从1开始



+ <span style="font-size:20px">图片：</span>

    `<img>`
    **`<img src="图片地址" alt="下载失败时的替换文本" title = "提示文本">`**
    讲解：
    1、**src**：标识图像的位置；
    2、**alt**：指定图像的描述性文本，当图像不可见时（下载不成功时），可看到该属性指定的文本；
    3、**title**：提供在图像可见时对图像的描述(鼠标滑过图片时显示的文本)；
    4、图像可以是**GIF，PNG，JPEG格式**的图像文件
    图片与文字同行显示：`vertical-align:middle/top`

    

+ <span style="font-size:20px">超链接：</span>

    语法
    **`<a  href="目标网址"   title="鼠标滑过显示的文本">` 链接显示的文本</a>**
    **`title`**属性：鼠标滑过链接文字时会显示这个属性的文本内容
    **`target`**属性：打开网页的方式，**self**(*默认* )和**blank**，当前页面打开链接、新窗口打开链接

    **a href="#"** 时表示一个空链接，点击时链接停留在当前页面，相当于刷新当前页面，有时使用该方法实现刷新本页面功能。如果没有设置特别的链接效果，那么点击效果和默认的点击链接效果一样
    一般用于返回当前页的某一点
    `#`后面可以跟任意标签的id，或者任意a标签的id或者name,点击链接就可以跳转到当前页面的这个节点的位置

    **伪类：**



### 表格

table家族

创建表格四元素：table、tr、th、td
说明
1、**`<table>…</table>`**：**整个表格**以<table>**标记开始**、</table>标记**结束**
2、**`<tr>…</tr>`**：表格的**一行**，所以有几对tr 表格就有几行。
3、**`<td>…</td>`**：表格的一个单元格，一行中包含几对<td>...</td>，说明一行中就有几**列**。
4、**`<th>…</th>`**：表格的头部的一个单元格，表格**表头**。
5、**border属性**可以为表格**添加边框**，属性值为数字
注意：
1、table标签用来定义整个表格，为双标签，必须有结束标签。
2、table标签里面可以放caption标签和tr标签。
3、caption标签用来定义表格的标题。
4、tr标签用来设置表格的行，tr里面只能放th或者td标签，一组tr标签代表一行。
5、th用来设置表格的标题，会加粗居中显示。也就是th标签中的文本默认为粗体并且居中显示。
6、td同来设置表格的列，一组td标签代表一列。
7、table表格在没有添加border属性之前, 在浏览器中显示是没有表格线的。



### 表单

`<form>`创建表单，与用户交互

表单是可以把浏览者输入的数据传送到服务器端，这样服务器端程序就可以处理表单传过来的数据。
语法：

`<form   method="传送方式"   action="服务器文件">`



# CSS
## 注释语句

`/*注释语句*/`



## CSS样式
### 内联式css样式
内联式就是把css代码直接写在现有的HTML标签中
注：写在元素的开始标签里
写在style=""双引号中，如果有多条css样式代码设置可以写在一起，中间用分号隔开
如下代码：

`<p style="color:red`;`font-size:12px">这里文字是红色。</p>`


###  嵌入式css样式
定义：把css样式代码写在`<style type="text/css"></style>`标签之间,一般情况下嵌入式css样式写在``<head></head>`之间
Eg:

```html
<style type="text/css">
  span{
  color:red;
  }
</style>
```



###  外部式css样式
定义：把css代码写一个单独的外部文件中，这个css样式文件以“.css”为扩展名，在<head>内使用`<link>`标签将css样式文件链接到HTML文件内
Eg：`<link href="base.css" rel="stylesheet" type="text/css" />`
注：1、css样式文件名称以有意义的英文字母命名，如 main.css。
2、`rel="stylesheet" type="text/css"` 是**固定写法**不可修改。
3、`<link>`标签位置一般写在`<head>`标签之内



###  三种链接方式的优先级
内联式 > 嵌入式 > 外部式
就近原则（离被设置元素越近优先级别越高）前提：内联式、嵌入式、外部式样式表中css样式是在的相同权值的情况下



###  样式的继承
继承是一种规则，它允许样式不仅应用于某个特定html标签元素，而且应用于其后代

但注意有一些css样式是**不具有继承性**。如**border**:1px **solid** red;



## 选择器

### `>`子选择器

用于选择指定标签元素的第一代子元素即大于符号(>)
Eg：.food**>**li{border:1px solid red;}
这行代码会使class名为food下的子元素li（水果、蔬菜）加入红色实线边框



###  ` `后代选择器

包含选择器，即加入空格,用于选择指定标签元素下的后辈元素
Eg：.first  span{color:red;}
注：>作用于元素的第一代后代，空格作用于元素的所有后代



### 属性选择器

具有特定属性的HTML元素样式
属性选择器样式无需使用class或id的形式

Eg：把包含标题（title）的所有元素变为蓝色
`[title]{    color:blue;}`



### *通用选择器

功能最强大的选择器，它使用一个（*）号指定，它的作用是匹配html中所有标签元素



### 伪类选择器

允许给html不存在的标签（标签的某种状态）设置样式

常用：

`:first-child`、`:nth-child()`



### ,分组选择器

当想为html中多个标签元素设置同一个样式时，可以使用分组选择符（，）
Eg：h1,span{color:red;}
它相当于下面两行代码：
h1{color:red;}
span{color:red;}



### 选择器的优先级

1、如果一个元素使用了多个选择器,则会按照选择器的优先级来给定样式。
2、选择器的优先级依次是: 内联样式 > id选择器 > 类选择器 > 标签选择器 > 通配符选择器



### 权值计算

为浏览器是根据权值来判断使用哪种css样式的，权值高的就使用哪种css样式。
下面是权值的规则：
标签的权值为1，类选择符的权值为10，ID选择符的权值最高为100。
**继承也有权值但最低**



### 选择器最高层级!important

有些特殊的情况需要为某些样式设置具有最高权值

浏览器默认的样式 < 网页制作者样式 < 用户自己设置的样式，但!important优先级样式是个例外，权值高于用户自己设置的样式



## 字体样式

### font-family设置字体
Eg:body{font-family:"宋体";}
兼容性更好
### font-size设置字体大小
Eg:body{font-size:12px;}
### font-weight设置字体粗细
### font-style设置字体样式
Eg:
![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1601878334131-ad17a23a-efec-468a-a7dd-142a388f8979.jpeg#align=left&display=inline&height=311&margin=%5Bobject%20Object%5D&originHeight=311&originWidth=550&size=0&status=done&style=none&width=550)
![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1601878334108-3b244d8c-7454-41f4-92b4-d33a04095ec1.jpeg#align=left&display=inline&height=320&margin=%5Bobject%20Object%5D&originHeight=320&originWidth=1107&size=0&status=done&style=none&width=1107)
**解释****：**
1、font-style可以设置字体样式，并且有种3设置方式。
2、正常字体为normal,也是font-style的默认值。
3、italic为设置字体为斜体，用于字体本身就有倾斜的样式。
4、oblique为设置倾斜的字体，强制将字体倾斜。
### color设置字体颜色
**解释：**
1、color属性可以设置字体颜色。
2、color的值有3种设置方式：

- **英文命令颜色**

p{color:red;}

- **RGB颜色**

这个与 photoshop 中的 RGB 颜色是一致的，由 R(red)、G(green)、B(blue) 三种颜色的比例来配色。
p{color:rgb(133,45,200);}
每一项的值可以是 0~255 之间的整数，也可以是 0%~100% 的百分数。如：
p{color:rgb(20%,33%,25%);}

- **十六进制颜色**

这种颜色设置方法是现在比较普遍使用的方法，其原理其实也是 RGB 设置，但是其每一项的值由 0-255 变成了十六进制 00-ff。
Eg:p{color:#00ffff;}
###  font样式的简写方式
Eg:_body{_
_    font-style:italic;_
_    font-weight:bold;  _
_    font-size:12px;  _
_    line-height:1.5em;  _
_    font-family:"宋体",sans-serif;_
_}_
_这么多行的代码其实可以缩写为一句：_
_body{_
_    font:italic  bold  12px/1.5em  "宋体",sans-serif;_
_}_
注意：
1、使用这一简写方式你至少要指定 font-size 和 font-family 属性，其他的属性(如 font-weight、font-style、font-variant、line-height)如未指定将自动使用默认值。
2、在缩写时 font-size 与 line-height 中间要加入“/”斜扛。
一般情况下因为对于中文网站，英文还是比较少的，所以下面缩写代码比较常用：
_body{_
_    font:12px/1.5em  "宋体",sans-serif;_
_}_
只是有字号、行间距、中文字体、英文字体设置
## 文本样式
### 用text-decoration添加文本修饰     
Eg：        [![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1601879246655-d6652b0f-94c9-4b9c-b284-2858e7bd6daf.jpeg#align=left&display=inline&height=389&margin=%5Bobject%20Object%5D&originHeight=389&originWidth=644&size=0&status=done&style=none&width=644)](http://img2.mukewang.com/5e9564020001d57406440389.jpg)
[![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1601879246675-0696356c-91d8-46f5-a10f-e6c8d040d33b.jpeg#align=left&display=inline&height=377&margin=%5Bobject%20Object%5D&originHeight=377&originWidth=1184&size=0&status=done&style=none&width=1184)](http://img2.mukewang.com/5e95640f0001a82011840377.jpg)
**解释：**
1、text-decoration可以设置添加到文本的修饰。
2、text-decoration默认值为none, 定义标准的文本。
3、text-decoration的值为**underline**为定义**文本下的一条线**。
4、text-decoration的值为**overline**为定义**文本上的一条线**。
5、text-decoration的值为**line-through**为定义穿过**文本的一条线**，一般用于商品折扣价
### text-indent为文本添加首行缩进
中文文字中的段前习惯空两个文字的空白，这个特殊的样式可以用下面代码来实现：
_p{text-indent:2em;}_
_<p>1922年的春天，一个想要成名名叫尼克卡拉威（托比?马奎尔Tobey Maguire 饰）的作家，离开了美国中西部，来到了纽约。那是一个道德感渐失，爵士乐流行，走私为王，股票飞涨的时代。为了追寻他的美国梦，他搬入纽约附近一海湾居住。</p>_
注意：2**em**的意思就是文字的2倍大小
### line-height为文字设置行间间距
_p{line-height:1.5em;}_
### letter/word-spacing增减字符间的空白
使用：在网页排版中设置文字间隔或者字母间隔：** letter-spacing**
设置英文单词之间的间距：** word-spacing**
### text-align设置文本对齐方式
使用：为块状元素中的文本、图片设置居中样式
** text-align:center**
** text-align:left**
** text-align:right**
**

### list-style设置列表属性
_list-style：none    _**去除**每项自带的小**圆点**
### 长度值
常用px（像素）、em、%注：这三种单位都是相对单位    
**1、像素**与浏览器会使用显示器的实际像素值有关
**2、em1** 1em=本元素给定字体的 font-size 值
_p{font-size:12px;text-indent:2em;}_
特殊情况：
当给 font-size 设置单位为 em 时，此时计算的标准以 p 的父元素的 font-size 为基础
_html:_
_<p>以这个<span>例子</span>为例。</p>_
_css:_
_p{font-size:14px}_
_span{font-size:0.8em;}_
**3、百分比**
_p{font-size:12px; line-height:130%}_
_设置行高（行间距）为字体的130%_
_
## 水平/垂直居中
法一：


## 盒模型
![](https://cdn.nlark.com/yuque/0/2020/gif/2617721/1603183304416-4a408d18-d3f9-4296-b153-ca22ea302eb0.gif#align=left&display=inline&height=289&margin=%5Bobject%20Object%5D&originHeight=289&originWidth=536&size=0&status=done&style=none&width=536)
###  元素分类
CSS中，html中的标签元素大体被分为三种不同的类型：**块状元素、内联元素**(又叫**行内元素**)和**内联块状元素**
**常用的块状元素**有：

```
<div>、<p>、<h1>...<h6>、<ol>、<ul>、<dl>、<table>、<address>、<blockquote> 、<form>
```



**常用的内联元素**有：

```
<a>、<span>、<br>、<i>、<em>、<strong>、<label>、<q>、<var>、<cite>、<code>
```



**常用的内联块状元素**

```
<img>、<input>
```



#### 块级元素 
块级元素特点：
1、一个块级元素独占一行
2、元素的高度、宽度、行高以及顶和底边距都可设置。
3、元素宽度在不设置的情况下，是它本身父容器的100%（和父元素的宽度一致），除非设定一个宽度

#### 内联元素

**display:inline**将元素设置为内联元素
内联元素特点：
1、和其他元素都在一行上；
2、元素的高度、宽度及顶部和底部边距不可设置；
3、元素的宽度就是它包含的文字或图片的宽度，不可改变

####  内联块状元素

定义：同时具备内联元素、块状元素的特点，代码**display:inline-block**就是将元素设置为内联块状元素。
特点：
1、和其他元素都在一行上；
2、元素的**高度、宽度、行高以及顶和底边距**都可设置
#### none元素隐藏
使用：当想要元素不显示的时候
**display: none;**
### 宽度和高度
定义：css内定义的宽（width）和高（height），指的是填充以里的内容范围
指定一个 CSS 元素的宽度和高度属性时，只是设置内容区域的宽度和高度。完整大小的元素，还必须添加内边距，边框和边距
**元素实际宽度**（盒子的宽度）=                                                                                        （高度也是同理。
![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1601881765769-69680d17-fbc8-4b1d-b2f5-3f18b91f9c1a.jpeg#align=left&display=inline&height=259&margin=%5Bobject%20Object%5D&originHeight=259&originWidth=557&size=0&status=done&style=none&width=557)
Eg：
![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1601881830940-3c43a886-ebe7-4414-b16c-3c02e4ea18a9.jpeg#align=left&display=inline&height=350&margin=%5Bobject%20Object%5D&originHeight=350&originWidth=430&size=0&status=done&style=none&width=430)
### 背景色
行内元素还是块状元素都可以给它设置一个背景色
**background-color:颜色值**
### border为盒子添加边框
定义：盒子模型的边框就是围绕着内容及补白的线，这条线你可以设置它的粗细、样式和颜色(边框三个属性)
注意：
1、**border-style**（边框样式）常见样式有：
dashed（虚线）| dotted（点线）| solid（实线）。
2、**border-color**中的颜色可设置为十六进制颜色，如:
border-color:#888;//前面的井号不要忘掉。
3、**border-width**中的宽度也可以设置为：
thin | medium | thick（不常用），常用像素（px）
border 代码的**缩写形式：**
_div{_
_    border:2px  solid  red;                      (顺序可倒，可少）_
_}_1
可以分开写


---

css 样式中允许**只为一个方向的边框设置**样式
_div{border-bottom:1px solid red;}_
### border-radius设置圆角
圆角可分为左上、右上、右下、左下：
_div { border-radius: 20px 10px 15px 30px; }_

---

可以分开写：
_div{_
_    border-top-left-radius: 20px;_
_   border-top-right-radius: 10px;_
_   border-bottom-right-radius: 15px;                       （右下圆角_
_   border-bottom-left-radius: 30px;_
_}_

---

如果四个圆角都为10px;可以这么写：
div{ border-radius:10px;}

---

左上角和右下角圆角一样，右上角和左下角圆角一样，可以这么写：
div{ border-radius:10px 20px;}

---

特殊：显示效果为**圆形**
：圆角效果值为元素宽度一半时、百分比50%
### padding为盒子设置内边距（填充）
填充也可分为上、右、下、左(顺时针)
_div{padding:20px 10px 15px 30px;}            （写法类比上一节_
### margin为盒子设置外边距（边界）
元素与其它元素之间的距离可以使用边界（margin）来设置   (写法类比上一节
padding和margin的区别：padding在边框里，margin在边框外
## css布局模型

在网页中，元素有三种布局模型：
1、流动模型（Flow）
2、浮动模型 (Float)
3、层模型（Layer）

+ <span style="font-size:20px">文档流：</span>

    文档流指的是元素排版布局过程中，元素会**默认**自动从左往右，从上往下的<span style="color:red">流式排列方式</span>



### 流动模型（Flow）

Flow是默认的网页布局模式。也就是说网页在默认状态下的 HTML 网页元素都是根据流动模型来分布网页内容的特征：**块状元素**都会在所处的包含元素内**自上而下按顺序垂直延伸分布**，因为 块状元素都会以行的形式占据位置；
在流动模型下，**内联元素**都会在所处的包含元素内**从左到右水平分布**显示



### 浮动模型

**侧边栏**：浮动元素写在要并排显示的另一元素前面，因为该元素浮动后后面的元素才能上去

脱离文档流。



### 层模型

作用：让html元素在网页中精确定位
CSS定义了一组定位（**positioning**）属性来支持层布局模型
层模型有三种形式：
1、绝对定位(position: absolute)
2、相对定位(position: relative)
3、固定定位(position: fixed)

- `absolute`、`relative`和`fixed`脱离文档流，子元素无法撑起父元素的高度

#### 层模型之绝对定位

**position:absolute**
定义：将元素从文档流中拖出来，然后使用**left、right、top、bottom**属性**相对**于其最接近的一个**具有定位属性（**position值不是sta..的）的**父包含块**进行绝对定位。如果不存在这样的包含块，则**相对**于**body**元素，即相对于浏览器窗口

#### 层模型之相对定位

通过left、right、top、bottom属性确定元素在正常文档流中的偏移位置
定义：**相对**于**以前的位置**移动,偏**移前的位置保留**不动

Eg：虽然div元素相对于以前的位置产生了偏移，但是div元素以前的位置还是保留着，所以后面的span元素是显示在了div元素以前位置的后面

<img src="https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1601886027728-4617a57b-0f32-4ec6-9ead-1c7b80dbc159.jpeg#align=left&display=inline&height=489&margin=%5Bobject%20Object%5D&originHeight=489&originWidth=594&size=0&status=done&style=none&width=594" style="zoom:50%;" />



#### 层模型之固定定位

固定定位的元素会**始终位于**浏览器窗口内**视图**的**某个位置**，**不受文档流动（滚轮）影响，**与background-attachment:fixed;属性功能相同

#### Relative与Absolute组合使用position:relative
**相对**于**其它元素**进行定位
使用规范：1、参照定位的元素必须是相对定位元素的前辈元素：

## 弹性盒模型

