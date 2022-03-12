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



### 兄弟选择器

+ [相邻兄弟选择器 +](https://developer.mozilla.org/zh-CN/docs/Web/CSS/Adjacent_sibling_combinator)：介于两个选择器之间，当第二个元素紧跟在第一个元素<span style="color:red">之后</span>，并且两个元素都是属于同一个父元素的子元素，则第二个元素将被选中
+ [通用兄弟选择器 ~](https://developer.mozilla.org/zh-CN/docs/Web/CSS/General_sibling_combinator)：位置无须紧邻，只须同层级，`A~B` 选择`A`元素<span style="color:red">之后</span>所有同层级`B`元素
+ [为什么都只能选择后面的元素](https://www.zhangxinxu.com/wordpress/2016/08/css-parent-selector/)——浏览器解析HTML文档，是从前往后，由外及里的。所以，我们时常会看到页面先出现头部然后主体内容再出现的加载情况。



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



## CSS 属性

+ **border：**

      



# CSS 定位

## 水平垂直居中

+ <span style="font-size:22px">Flex：</span>

    ```css
    .outer {
    	display: flex;  
    	justify-content: center; /* 水平居中 */
      align-items: center; /* 垂直居中 */
    }
    ```

    此方案的另一种用法：

    ```css
    .outer {
      display: flex;
    }
    .inner {
      margin: auto;
    }
    ```

    <span style="color:green">不需固定居中元素宽高</span>

+ <span style="font-size:22px">absolute + transform：</span>

    ```css
    #content {
      position: absolute;
      left: 50%;
      top: 50%;
      transform: translate(-50%, -50%);	/* 偏移自身宽、高的一半 */ 
    }
    ```

    >CSS3 新增的**`transform`**属性允许你旋转，缩放，倾斜或平移给定元素
    >
    >**`translate()`** 这个 CSS 函数在水平、垂直方向上重新定位元素，是根据元素自身计算的

    <span style="color:green">不需固定居中元素宽高</span>

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

- `absolute`、和`fixed`脱离文档流，子元素无法撑起父元素的高度

#### 绝对定位

**position:absolute**
定义：将元素从文档流中拖出来，然后使用**left、right、top、bottom**属性**相对**于其最接近的一个**具有定位属性（**position值不是sta..的）的**父包含块**进行绝对定位。如果不存在这样的包含块，则**相对**于**body**元素，即相对于浏览器窗口

#### 相对定位

通过left、right、top、bottom属性确定元素在正常文档流中的偏移位置
定义：**相对**于**以前的位置**移动,偏**移前的位置保留**不动

Eg：虽然div元素相对于以前的位置产生了偏移，但是div元素以前的位置还是保留着，所以后面的span元素是显示在了div元素以前位置的后面

<img src="https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1601886027728-4617a57b-0f32-4ec6-9ead-1c7b80dbc159.jpeg#align=left&display=inline&height=489&margin=%5Bobject%20Object%5D&originHeight=489&originWidth=594&size=0&status=done&style=none&width=594" style="zoom:50%;" />



#### 固定定位

固定定位的元素会**始终位于**浏览器窗口内**视图**的**某个位置**，**不受文档流动（滚轮）影响，**与background-attachment:fixed;属性功能相同

#### Relative与Absolute组合使用position:relative

**相对**于**其它元素**进行定位
使用规范：1、参照定位的元素必须是相对定位元素的前辈元素：

## 弹性盒模型



# REF

+ CSS 定位：

    [CSS 拷问：水平垂直居中方法你会几种？| Liuyib`s Blog](https://liuyib.github.io/2020/04/07/css-h-and-v-center/)

    