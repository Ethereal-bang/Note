# html

## vscode
**新建html文件**：新建文件保存后缀.html，在vscode里输入!tab
**浏览器运行：**alt+b

## html5文档结构
1.** <!DOCTYPE html>**:**文档类型声明**，表示该文件为 HTML5文件。<!DOCTYPE> 声明必须是 HTML 文档的**第一行**，位于 <html> 标签之前
2. `<html></html>`标签对：`<html>`标签位于**HTML文档的最前面**，用来**标识HTML文档的开始**；`</html>`标签位于HTML文档的**最后面**，用来标识HTML 文档的**结束**；这两个标签对成对存在，中间的部分是文档的头部和主题。
3.`<head></head>`标签对：标签**包含有关HTML文档信息**，可包含一些辅助性标签如**_<title></title>（_**网页的标题信息）**_，<link />，<meta />，<style></style>，__<script></script>_**等，但是浏览器除了会在标题栏**只显示<title>元素的内容**外，不会向用户显示head元素内的其他任何内容。
4.**<body></body>**标签对：它是HTML文档的**主体部分**，在此标签中可以包含<p><h1><br>等众多标签，<body>标签出现在</head>标签之后，且必须在闭标签</html>之前闭合head标签
注：**body**的大小是由**内部元素**撑开，不是默认占满整个页面



## html文件注释
语法
**<!--注释文字 -->**

## 标签
**标签语义化**好处
1. 更容易被搜索引擎收录
2. 更容易让屏幕阅读器读出网页内容
标签的语法
1. 标签由英文尖括号**<**和**>**括起来，如<html>就是一个标签。
2. html中的标签一般都是**成对**出现的，分开始标签和结束标签。结束标签比开始标签多了一个**/**
3. 标签与标签之间是**可以嵌套**的，但先后**顺序**必保一致
4.  HTML标签不区分大小写，<h1>和<H1>是一样的，但建议**小写**，（因大部分程序员都以小写为准
空标签**没有**HTML**内容**的标签就是空标签，
    只需要一个开始标签，这样的标签有**_
`<hr/>`，`<img />`

### `<head>`头部标签

作用嵌套**meta、title、style**等标签
<title>标签：在<title>和</title>标签之间的文字**内容**，会出现在**浏览器的标题栏**中
<meta charset="UTF-8">设置当前文件字符编码
<style>标签：双标签中设置当前文件样式   （css
### 
### <body>页面内容标签
#### <p>段落标签
语法
**<p>段落文本</p>**
**一段**文字**一个**<p>标签
<p>标签的默认样式，段前段后**留白**，如果不喜欢这个空白，可以用css样式来删除或改变它
### 
#### <span>标签自定义文字样式
作用设置**单独样式**
语法：
**<span>文本</span>**
### 
#### <hx>标签增加标题
标题标签一共有6个，h1、h2、h3、h4、h5、h6  ，  <h1>是最高的等级。标题标签的样式都会**加粗**
Eg：
                 h1一般用在网站名称  如下腾讯网

![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1602401847975-f1a9bb8a-32dc-45a7-ad0e-4737aabbf331.jpeg#align=left&display=inline&height=572&margin=%5Bobject%20Object%5D&originHeight=572&originWidth=1274&size=0&status=done&style=none&width=1274)
**语法****：**
**<hx>标题文本</hx>**
Eg：
            ![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1601600346342-90471550-4b7f-4b19-b04c-24bf5eea2d5b.jpeg#align=left&display=inline&height=125&margin=%5Bobject%20Object%5D&originHeight=125&originWidth=172&size=0&status=done&style=none&width=172)
            ![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1601600386669-b04b2719-adeb-4da0-8c47-a20a1f23b713.jpeg#align=left&display=inline&height=399&margin=%5Bobject%20Object%5D&originHeight=399&originWidth=596&size=0&status=done&style=none&width=596)
### 
#### <div>标签自定义块
把一些**独立的逻辑部分**划分出来，放在一个<div>标签中
语法：
`<div>` `</div>`
注<header>，<footer>，<aside>本质等同<div>，只是具**语义化**

#### <nav>标签定义导航栏
#### <header>标签定义头部区域
#### <footer>标签定义底部区域
#### <aside>定义侧边栏区域

### <br/>标签换行

在需要加**回车换行**的地方加入**
**
注：在 html 代码中输入回车、空格都是没有作用的。在html文本中想输入回车换行，就必须输入

### 
### &nbsp;实现空格标签
一个**%nbsp；**表示输入一个空格


### <hr>标签水平分隔线
Eg

---

### 列表
![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1603502583907-0f70f47b-a31c-40e5-b4e4-e58200a4acda.png#align=left&display=inline&height=274&margin=%5Bobject%20Object%5D&name=image.png&originHeight=547&originWidth=1116&size=191774&status=done&style=none&width=558)
### ：<ul><lia>标签实现无序列表
举例**：**
_<ul>_
_  <li>_[_精彩少年_](http://www.zuowen.com/e/20130805/51ff0eacd8d21.shtml)_</li>_
_  <li>_[_美丽突然出现_](http://www.zuowen.com/e/20130802/51fb0a622ef8d.shtml)_</li>_
_  <li>_[_触动心灵的旋律_](http://www.zuowen.com/e/20130713/51e0a742d81db.shtml)_</li>_
_</ul>_
ul-li在网页中显示的默认样式一般为：每项li前都自带一个圆点

### ：使用<ol><li>标签实现有序列表**
举例**：**_<ol>_
_   <li>前端开发面试心法 </li>_
_   <li>_[_零基础学习html_](http://www.zuowen.com/e/20130802/51fb0a622ef8d.shtml)_</li>_
_   <li>JavaScript全攻略</li>_
_</ol>_
默认样式一般为：每项<li>前都自带一个**序号**，序号默认从1开始
### <img>标签为网页添加图片**
语法：
**<img src="图片地址" alt="下载失败时的替换文本" title = "提示文本">**
讲解：
1、**src**：标识图像的位置；
2、**alt**：指定图像的描述性文本，当图像不可见时（下载不成功时），可看到该属性指定的文本；
3、**title**：提供在图像可见时对图像的描述(鼠标滑过图片时显示的文本)；
4、图像可以是**GIF，PNG，JPEG格式**的图像文件
**图片与文字同行显示vertical-align:**middle/top
### `<a>`标签为网页添加超链接

语法
**`<a  href="目标网址"   title="鼠标滑过显示的文本">` 链接显示的文本</a>**
**title**属性的作用：鼠标滑过链接文字时会显示这个属性的文本内容
新建浏览器窗口中打开链接<a>标签中的**target**属性
Eg

![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1601605003157-e0e1c2a3-4a3e-464c-b099-75705d98c3d0.jpeg#align=left&display=inline&height=92&margin=%5Bobject%20Object%5D&originHeight=92&originWidth=307&size=0&status=done&style=none&width=307)代码如下
![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1601604960266-47afc519-c78b-4ae0-ad72-a5d9f84f73cf.jpeg#align=left&display=inline&height=555&margin=%5Bobject%20Object%5D&originHeight=555&originWidth=1110&size=0&status=done&style=none&width=1110)**技术点的解释**
a标签有的**target**属性，代表打开网页的方式
可选值为”**_self**和**_blank**”，默认值为_self，代表在当前页面打开链接，_blank代表在新窗口打开链接。

**a href="#"** 时表示一个空链接，点击时链接停留在当前页面，相当于刷新当前页面，有时使用该方法实现刷新本页面功能。如果没有设置特别的链接效果，那么点击效果和默认的点击链接效果一样
一般用于返回当前页的某一点
#后面可以跟任意标签的id，或者任意a标签的id或者name,点击链接就可以跳转到当前页面的这个节点的位置
_<a href="#abc"></a>      /*跳转到div id="abc"></*/_
_<a href="#bcd"></a>       /*跳转到a name="bcd"></*/_
_<a href="#"></a>         /*跳转到当前页面的顶部*/_

---

**a:link **{color: red} /* 未访问的链接 */
 **a:visited** {color: green} /* 已访问的链接 */
** a:hover** {color: black} /* 鼠标移动到链接上 */
 **a:active** {color: yellow} /* 选定的链接，即鼠标按下去的时候不松开显示的状态 */
注意：有时候我们发现设置了上面的属性但是没有效果，这是因为他们是有顺序的
1、a:hover 必须被置于 a:link 和 a:visited 之后，才是有效的。
2、a:active 必须被置于 a:hover 之后，才是有效的。


### 表格
### ：table家族
创建表格四元素：table、tr、th、td
说明
1、**<table>…</table>**：**整个表格**以<table>**标记开始**、</table>标记**结束**
2、**<tr>…</tr>**：表格的**一行**，所以有几对tr 表格就有几行。
3、**<td>…</td>**：表格的一个单元格，一行中包含几对<td>...</td>，说明一行中就有几**列**。
4、**<th>…</th>**：表格的头部的一个单元格，表格**表头**。
5、**border属性**可以为表格**添加边框**，属性值为数字
注意：
1、table标签用来定义整个表格，为双标签，必须有结束标签。
2、table标签里面可以放caption标签和tr标签。
3、caption标签用来定义表格的标题。
4、tr标签用来设置表格的行，tr里面只能放th或者td标签，一组tr标签代表一行。
5、th用来设置表格的标题，会加粗居中显示。也就是th标签中的文本默认为粗体并且居中显示。
6、td同来设置表格的列，一组td标签代表一列。
7、table表格在没有添加border属性之前, 在浏览器中显示是没有表格线的。
![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1601606958127-26f5b81d-efb3-4b3d-82a6-9cf21a11baab.png#align=left&display=inline&height=366&margin=%5Bobject%20Object%5D&name=image.png&originHeight=731&originWidth=1126&size=67921&status=done&style=none&width=563)
### thead、tbody、tfoot定义表格？
<thead>标签定义表格头部,<tbody>标签来定义表格的内容,<tfoot>来定义表格的底部
## 表单
## :<form>创建表单，与用户交互 
表单是可以把浏览者输入的数据传送到服务器端，这样服务器端程序就可以处理表单传过来的数据。
语法：

`<form   method="传送方式"   action="服务器文件">`
**讲解：**
1.**<form> ：**<form>标签是成对出现的，以<form>开始，以</form>结束。
2.**action** **：**浏览者输入的数据被传送到的地方,比如一个PHP页面(save.php)。
3.**method** **：** 数据传送的方式（get/post）
Eg:

<form    **method="post"   action="save.php"**>
        <label for="username">用户名:</label>
        <input type="text" name="username" />
        <label for="pass">密码:</label>
        <input type="password" name="pass" />
</form>
## 文本输入框、密码输入框-**text/password**
**语法**：

`<form>`
   <input type="text/password" name="名称" value="文本" />
</form>
`**1**``**、type：**`
`   当type="**text**"时，输入框为**文本**`**输入框**`**;**`
`   当type="**password**"时,` `输入框为**密码输入框。**`
`**2**``**、name：**``为文本框命名，以备后台程序ASP 、PHP使用。`
`**3**``**、value：**``为文本输入框设置默认值。(一般起到提示作用)`

## 占位符-placeholder
![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1601633065486-b9e512c8-0033-49d3-86b5-653f344e58bd.jpeg#align=left&display=inline&height=493&margin=%5Bobject%20Object%5D&originHeight=493&originWidth=1165&size=0&status=done&style=none&width=1165)
**解释：**
1、placeholder属性为输入框占位符,里面可以放提示的输入信息。
2、placeholder属性的值可以任意填写,当输入框输入内容时,占位符内容消失,输入框无内容时,占位符内容显示。
3、占位符内容不是输入框真正的内容。
## 数字输入框-number
## 网址输入框-url类型
**技术点的解释：**
1、input的type属性设置为url,则表示该输入框的类型为网址。
2、数字框的值需以http://或者https://开头,且后面必须有内容,否则表单提交的时候会报错误提示
##  邮箱输入框email类型
解释：
1、Input的type属性设置为email,则表示该输入框的类型为邮箱。
2、数字框的值必须包含@。
3、数字框的值@之后必须有内容,否则会报错误提示
## <textarea>标签创建文本域
不在表单内也能用
--[用法W3school](https://www.w3school.com.cn/tags/tag_textarea.asp)--
**<textarea  rows="行数" cols="列数">文本</textarea>**
_<form  method="post" action="save.php">_
_        <label>联系我们</label>_
_        <textarea cols="50" rows="10" >在这里输入内容...</textarea>_
_</form>_
1、<textarea>标签是成对出现的，以<textarea>开始，以</textarea>结束。
2、cols ：多行输入域的列数。
3、rows ：多行输入域的行数。
4、在<textarea></textarea>标签之间可以输入默认值
## label
标签的 for 属性中的值应当与相关控件的 id 属性值一定要相同。
例子：
<form
  <label for="email">输入你的邮箱地址</label>点击后焦点移到框内（不常用
  <input type="email" id="email" placeholder="Enter email">
</form>
##  单选框、复选框
语法：
<input   type="radio/checkbox"   value="值"    name="名称"   checked="checked"/>
**注：**1、type:
   当 type="radio" 时，控件为单选框
   当 type="checkbox" 时，控件为复选框
2、value：提交数据到服务器的值（后台程序PHP使用）
3、name：为控件命名，以备后台程序 ASP、PHP 使用
4、checked：当设置 checked="checked" 时，该选项被默认选中
**同一组**的单选按钮，name 取值一定要一致，这样同一组的单选按钮才可以起到单选的作用。
![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1601640410768-eb262260-bbb2-41ae-9134-d1ff5f4ec41e.png#align=left&display=inline&height=109&margin=%5Bobject%20Object%5D&name=image.png&originHeight=218&originWidth=549&size=16466&status=done&style=none&width=274.5)![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1601640433105-f3ab54eb-02f9-4d75-997b-ec69c147c845.png#align=left&display=inline&height=61&margin=%5Bobject%20Object%5D&name=image.png&originHeight=122&originWidth=274&size=2415&status=done&style=none&width=137)
## select、option标签创建下拉菜单
1、select和option标签都是双标签，它总是成对出现的，需要首标签和尾标签。
2、select标签里面只能放option标签，表示下拉列表的选项。
3、option标签放选项内容，不放置其他标签。
4、value：![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1601642934408-cd1b85e6-de83-4a0e-8a2e-62573fd05282.jpeg#align=left&display=inline&height=165&margin=%5Bobject%20Object%5D&originHeight=165&originWidth=503&size=0&status=done&style=none&width=503)
5、selected="selected"：
设置selected="selected"属性，则该选项就被默认选中
**Eg**:![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1601642989062-0d0f39c5-b283-46c5-a56d-a15ca45f6e07.jpeg#align=left&display=inline&height=735&margin=%5Bobject%20Object%5D&originHeight=735&originWidth=1121&size=0&status=done&style=none&width=1121)
浏览器中显示的结果：
![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1601643001517-f908107e-9ffe-4304-994d-54a9ee5bd7df.jpeg#align=left&display=inline&height=66&margin=%5Bobject%20Object%5D&originHeight=66&originWidth=120&size=0&status=done&style=none&width=120)
##  提交按钮
语法：
<input   type="submit"   value="提交">
注：type：只有当type值设置为submit时，按钮才有提交作用
value：按钮上显示的文字
## 重置按钮
语法：
<input type="reset" value="重置">
## 按钮 <button> 标签
`<button type="button">Click Me!</button>`

# CSS
## CSS代码语法
![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1601691360599-cc8ba8f9-0a13-4c85-86b8-a33aed56d861.jpeg#align=left&display=inline&height=117&margin=%5Bobject%20Object%5D&originHeight=117&originWidth=303&size=0&status=done&style=none&width=303)
选择符：又称选择器，指明网页中要应用样式规则的元素，如本例中是网页中所有的段（p）的文字将变成蓝色，而其他的元素（如ol）不会受到影响。
声明：在英文大括号“｛｝”中的的就是声明，属性和值之间用英文冒号“：”分隔。当有多条声明时，中间可以英文分号“;”分隔 
## 注释语句
/*注释语句*/
## CSS样式
### 内联式css样式
内联式就是把css代码直接写在现有的HTML标签中
注：写在元素的开始标签里
写在style=""双引号中，如果有多条css样式代码设置可以写在一起，中间用分号隔开
如下代码：

`<p style="color:red`;`font-size:12px">这里文字是红色。</p>`


###  嵌入式css样式
定义：把css样式代码写在<style type="text/css"></style>标签之间,一般情况下嵌入式css样式写在<head></head>之间
Eg:<style type="text/css">
span{
color:red;
}
</style>
###  外部式css样式
定义：把css代码写一个单独的外部文件中，这个css样式文件以“.css”为扩展名，在<head>内使用<link>标签将css样式文件链接到HTML文件内
Eg：<link href="base.css" rel="stylesheet" type="text/css" />
注：1、css样式文件名称以有意义的英文字母命名，如 main.css。
2、**rel="stylesheet" type="text/css**" 是**固定写法**不可修改。
3、<link>标签位置一般写在<head>标签之内
###  三种链接方式的优先级
内联式 > 嵌入式 > 外部式
就近原则（离被设置元素越近优先级别越高）前提：内联式、嵌入式、外部式样式表中css样式是在的相同权值的情况下
###  样式的继承
css的某些样式是具有继承性的，那么什么是继承呢？继承是一种规则，它允许样式不仅应用于某个特定html标签元素，而且应用于其后代
Eg:如某种颜色应用于p标签，这个颜色设置不仅应用p标签，还应用于p标签中的所有子元素文本，这里子元素为span标签。
_p{color:red;}_


_<p>三年级时，我还是一个<span>胆小如鼠</span>的小女孩。</p>_
p中的文本与span中的文本都设置为了红色。

---

但注意有一些css样式是**不具有继承性**。如**border**:1px **solid** red;
_p{border:1px solid red;}_


_<p>三年级时，我还是一个<span>胆小如鼠</span>的小女孩。</p>_
代码的作用只是给p标签设置了边框为1像素、红色、实心边框线，而对于子元素span没起作用
## 选择器

每一条css样式声明（定义）由两部分组成，形式如下：
选择器{
    样式;
}
“选择器”指明了{}中的“样式”的作用对象，也就是“样式”作用于网页中的哪些元素
Eg:“选择器”指明了{}中的“样式”的作用对象，也就是“样式”作用于网页中的哪些元素
![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1601703529992-9aee8675-29c2-4e4d-82fb-d0343b3e137e.png#align=left&display=inline&height=129&margin=%5Bobject%20Object%5D&name=image.png&originHeight=257&originWidth=531&size=20349&status=done&style=none&width=265.5)
### 标签选择器
标签选择器就是html代码中的标签。如<html>、<body>、<h1>、<p>、<img>
### .类选择器
语法：
.类选器名称{css样式代码;}
注：1、英文圆点开头
2、其中类选器名称可以任意起名（但不要起中文
使用方法：
第一步：使用合适的标签把要修饰的内容标记起来，如下：
_<span>胆小如鼠</span>_
第二步：使用class="类选择器名称"为标签设置一个类，如下：
**_<span class="_**_stress_**_">_**_胆小如鼠_**_</span>_**
第三步：设置类选器css样式，如下：
**_._**_stress _**_{ _**_color_**_:_**_red;_**_ }_**_  /*类前面要加入一个英文圆点*/_
_
拓展：多个类名![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1608550232457-3d67338a-4430-4f22-b3f2-b1e97d03fdc5.png#align=left&display=inline&height=92&margin=%5Bobject%20Object%5D&name=image.png&originHeight=123&originWidth=256&size=7162&status=done&style=none&width=192)   
 //js获取时![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1608550274723-ac55d856-e8db-4eb7-a2cc-8576b8008c59.png#align=left&display=inline&height=24&margin=%5Bobject%20Object%5D&name=image.png&originHeight=26&originWidth=472&size=3265&status=done&style=none&width=436)
### #ID选择器
注：1、使用ID选择器，必须给标签添加上id属性，为标签设置id="ID名称"，而不是class="类名称"。
2、ID选择符的前面是井号（#）号，而不是英文圆点（.）。
3、id属性的值既为当前标签的id，尽量见名思意，语义化
方法：
![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1601705052242-161c8705-db44-4bbf-9d79-70caf47e66ae.jpeg#align=left&display=inline&height=158&margin=%5Bobject%20Object%5D&originHeight=158&originWidth=503&size=0&status=done&style=none&width=503)![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1601705059101-0181ec64-533f-4353-907f-6ff45dcd688d.jpeg#align=left&display=inline&height=168&margin=%5Bobject%20Object%5D&originHeight=168&originWidth=621&size=0&status=done&style=none&width=621)
### 类和ID选择器的区别
相同点：可以应用于任何元素
不同点：
1、ID选择器只能在文档中使用一次。与类选择器不同，在一个HTML文档中，ID选择器只能使用一次，而且仅一次。而类选择器可以使用多次。
2、一个元素可有多个class，但只能有一个id
eg：
.stress{
    color:red;
}
.bigsize{
    font-size:25px;
}
<p>到了<span class="stress bigsize">三年级</span>下学期时，我们班上了一节公开课...</p>


上面代码的作用是为“三年级”三个文字设置文本颜色为红色并且字号为25px
### >子选择器
用于选择指定标签元素的第一代子元素即大于符号(>)
Eg：.food**>**li{border:1px solid red;}
这行代码会使class名为food下的子元素li（水果、蔬菜）加入红色实线边框
###   后代选择器
包含选择器，即加入空格,用于选择指定标签元素下的后辈元素
Eg：.first  span{color:red;}
注：>作用于元素的第一代后代，空格作用于元素的所有后代
### 属性选择器
具有特定属性的HTML元素样式
属性选择器样式无需使用class或id的形式
Eg把包含标题（title）的所有元素变为蓝色
[title]{    color:blue;}
### *通用选择器
功能最强大的选择器，它使用一个（*）号指定，它的作用是匹配html中所有标签元素
### 伪类选择器
允许给html不存在的标签（标签的某种状态）设置样式
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
Eg：![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1601707421435-8501318f-9875-4879-8d29-0ce0d4e39f43.png#align=left&display=inline&height=117&margin=%5Bobject%20Object%5D&name=image.png&originHeight=234&originWidth=441&size=13025&status=done&style=none&width=220.5)
：浏览器默认的样式 < 网页制作者样式 < 用户自己设置的样式，但!important优先级样式是个例外，权值高于用户自己设置的样式
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

<div>、<p>、<h1>...<h6>、<ol>、<ul>、<dl>、<table>、<address>、<blockquote> 、<form>
**常用的内联元素**有：
<a>、<span>、<br>、<i>、<em>、<strong>、<label>、<q>、<var>、<cite>、<code>
**常用的内联块状元素**
<img>、<input>
#### 块级元素 
设置**`display:block`**就是将元素显示为块级元素
_a{display:block;}    _将**内联元素a转换为块状元素**，从而使a元素具有块状元素特点。
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
![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1603612417923-3e9322bd-3e09-4937-9a53-e24573d8da93.png#align=left&display=inline&height=352&margin=%5Bobject%20Object%5D&name=image.png&originHeight=703&originWidth=1223&size=273729&status=done&style=none&width=611.5)
在网页中，元素有三种布局模型：
1、流动模型（Flow）
2、浮动模型 (Float)
3、层模型（Layer）
### 流动模型（Flow）
Flow是默认的网页布局模式。也就是说网页在默认状态下的 HTML 网页元素都是根据流动模型来分布网页内容的特征：**块状元素**都会在所处的包含元素内**自上而下按顺序垂直延伸分布**，因为 块状元素都会以行的形式占据位置；
         在流动模型下，**内联元素**都会在所处的包含元素内**从左到右水平分布**显示
### 浮动模型
![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1603613135442-d0f8434b-e6cb-4b10-8207-1098b4eb3e42.png#align=left&display=inline&height=342&margin=%5Bobject%20Object%5D&name=image.png&originHeight=683&originWidth=762&size=239559&status=done&style=none&width=381)
 div、p、table、img 等元素都可以被定义为浮动
同时设置两个元素右/左浮动可实现一行显示。
_div{_
_    width:200px;_
_    height:200px;_
_    border:2px red solid;_
_   _**_ float:right_**_;_
_}_
效果图![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1601884908915-1d009b6f-80a6-491d-90f1-ec75138bc46f.jpeg#align=left&display=inline&height=417&margin=%5Bobject%20Object%5D&originHeight=417&originWidth=676&size=0&status=done&style=none&width=676)

---

设置两个元素一左一右实现一行显示：
_div{_
_    width:200px;_
_    height:200px;_
_    border:2px red solid;_
_}_
**_#div1{float:left;}_**
**_#div2{float:right;}_**
_侧边栏__：浮动元素写在要并排显示的另一元素前面，因为该元素浮动后后面的元素才能上去_
### 层模型
作用：让html元素在网页中精确定位
CSS定义了一组定位（**positioning**）属性来支持层布局模型
层模型有三种形式：
1、绝对定位(position: absolute)
2、相对定位(position: relative)
3、固定定位(position: fixed)
#### 层模型之绝对定位
**position:absolute**
定义：将元素从文档流中拖出来，然后使用**left、right、top、bottom**属性**相对**于其最接近的一个**具有定位属性（**position值不是sta..的）的**父包含块**进行绝对定位。如果不存在这样的包含块，则**相对**于**body**元素，即相对于浏览器窗口
_div{_
_    width:200px;_
_    height:200px;_
_    border:2px red solid;_
_   _**_ position:absolute;_**
**_    left:100px;_**
**_    top:50px;_**
_}_
#### 层模型之相对定位
通过left、right、top、bottom属性确定元素在正常文档流中的偏移位置
定义：**相对**于**以前的位置**移动,偏**移前的位置保留**不动
Eg：虽然div元素相对于以前的位置产生了偏移，但是div元素以前的位置还是保留着，所以后面的span元素是显示在了div元素以前位置的后面![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1601886027728-4617a57b-0f32-4ec6-9ead-1c7b80dbc159.jpeg#align=left&display=inline&height=489&margin=%5Bobject%20Object%5D&originHeight=489&originWidth=594&size=0&status=done&style=none&width=594)
#### 层模型之固定定位
固定定位的元素会**始终位于**浏览器窗口内**视图**的**某个位置**，**不受文档流动（滚轮）影响，**与background-attachment:fixed;属性功能相同
![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1601886399783-c59e5b57-0bdd-499e-9b26-082f78742922.png#align=left&display=inline&height=294&margin=%5Bobject%20Object%5D&name=image.png&originHeight=587&originWidth=1210&size=60197&status=done&style=none&width=605)
#### Relative与Absolute组合使用position:relative
**相对**于**其它元素**进行定位
使用规范：1、参照定位的元素必须是相对定位元素的前辈元素：
_<div id="box1">        <!--参照定位的元素-->_
_    <div id="box2">相对参照元素进行定位</div>        <!--相对定位元素-->_
_</div>_
2、参照定位的元素必须加入position:relative;
_#box1{_
_    width:200px;_
_    height:200px;_
_    position:relative;        _
_}_
3、定位元素加入position:absolute，便可以使用top、bottom、left、right来进行偏移定位了。
_#box2{_
_    position:absolute;_
_    top:20px;_
_    left:30px;          _
_}_
_Eg:![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1601887184709-5158e78c-342b-4aa3-94de-b49688dadec4.png#align=left&display=inline&height=108&margin=%5Bobject%20Object%5D&name=image.png&originHeight=215&originWidth=286&size=9269&status=done&style=none&width=143)![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1601887204126-259d34e8-9221-4408-a8eb-5bbdff22ba9d.png#align=left&display=inline&height=44&margin=%5Bobject%20Object%5D&name=image.png&originHeight=88&originWidth=693&size=11677&status=done&style=none&width=346.5)_
## 弹性盒模型
### 弹性盒模型之flex属性
使用div本身是块级元素，现需他们在一起排列      （一个块级元素占一行
![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1602254989720-48934593-9227-4e88-9833-b30f93f2325e.jpeg#align=left&display=inline&height=440&margin=%5Bobject%20Object%5D&originHeight=440&originWidth=1633&size=0&status=done&style=none&width=1633)
解释：
1、设置**display: flex**属性可以把块级元素在一排显示。
2、flex需要添加在父元素上，改变子元素的排列顺序。
3、默认为从左往右依次排列,且和父元素左边没有间隙 
Eg：
[![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1602303380210-526ef1d6-beba-4777-9b36-031c8ee864ff.jpeg#align=left&display=inline&height=207&margin=%5Bobject%20Object%5D&originHeight=207&originWidth=467&size=0&status=done&style=none&width=467)](https://img1.mukewang.com/5e958fbf0001227004670207.jpg)
[![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1602303380248-84c230af-7bbf-4095-ad2f-378155603cb6.jpeg#align=left&display=inline&height=701&margin=%5Bobject%20Object%5D&originHeight=701&originWidth=666&size=0&status=done&style=none&width=666)](https://img1.mukewang.com/5e958fd3000112dd06660701.jpg)
### justify-content属性设置横轴排列方式
作用：本属性定义了项目在主轴上的对齐方式
属性值分别为：
 justify-content: flex-start | flex-end | center | space-between | space-around
**`flex-start`：交叉轴的起点对齐**
_ .box {_
_        background: blue;_
_        display: flex;_
_        justify-content: flex-start;_
_    }_
实现效果：
[![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1602303643847-11ae518a-43ef-410c-87b0-e582c668abab.jpeg#align=left&display=inline&height=322&margin=%5Bobject%20Object%5D&originHeight=322&originWidth=2534&size=0&status=done&style=none&width=2534)](https://img.mukewang.com/5e959b080001a38d25340322.jpg)

---

**`flex-end`：右对齐**
_ .box {_
_        background: blue;_
_        display: flex;_
_        justify-content: flex-end;_
_    }_
实现效果
[![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1602303643946-746e6f8d-1cae-479c-ad97-f61dad4ef06d.jpeg#align=left&display=inline&height=308&margin=%5Bobject%20Object%5D&originHeight=308&originWidth=2542&size=0&status=done&style=none&width=2542)](https://img1.mukewang.com/5e959b8b0001d43b25420308.jpg)

---

**`center`： 居中**
_ .box {_
_        background: blue;_
_        display: flex;_
_        justify-content: center;_
_    _}
实现效果：
[![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1602303643838-2068d024-6181-45bb-87bf-f5a73f19cd31.jpeg#align=left&display=inline&height=303&margin=%5Bobject%20Object%5D&originHeight=303&originWidth=2530&size=0&status=done&style=none&width=2530)](https://img2.mukewang.com/5e959bdd0001ad2125300303.jpg)

---

**`space-between`：两端对齐，项目之间的间隔相等**
_ .box {_
_        background: blue;_
_        display: flex;_
_        justify-content: space-between;_
_    }_
实现效果：
[![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1602303643830-064d13ee-6c6b-4487-b74e-3bbb148bc883.jpeg#align=left&display=inline&height=313&margin=%5Bobject%20Object%5D&originHeight=313&originWidth=2553&size=0&status=done&style=none&width=2553)](https://img2.mukewang.com/5e959c6400017b1c25530313.jpg)

---

**`space-around`：每个项目两侧的间隔相等**。所以，项目之间的间隔比项目与边框的间隔大一倍。
_.box {_
_        background: blue;_
_        display: flex;_
_        justify-content: space-around;_
_    }_
实现效果：
[![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1602303643832-c1ec34be-411e-4b6e-9fe1-5704c8543d99.jpeg#align=left&display=inline&height=303&margin=%5Bobject%20Object%5D&originHeight=303&originWidth=2537&size=0&status=done&style=none&width=2537)](https://img2.mukewang.com/5e959caf000113b125370303.jpg)
### align-items属性设置纵轴排列方式
属性值分别为：
align-items: flex-start | flex-end | center | baseline | stretch
**`flex-start`**：默认值，**左对齐**
_   .box {_
_        height: 700px;_
_        background: blue;_
_        display: flex;_
_        align-items: flex-start;_
_    }_
实现效果：
[![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1602304668213-1160d237-c6b8-40c0-9ca1-eeef2f178c84.jpeg#align=left&display=inline&height=1051&margin=%5Bobject%20Object%5D&originHeight=1051&originWidth=2538&size=0&status=done&style=none&width=2538)](https://img3.mukewang.com/5e95a3720001140325381051.jpg)

---

[![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1602304668213-1160d237-c6b8-40c0-9ca1-eeef2f178c84.jpeg#align=left&display=inline&height=1051&margin=%5Bobject%20Object%5D&originHeight=1051&originWidth=2538&size=0&status=done&style=none&width=2538)](https://img3.mukewang.com/5e95a3720001140325381051.jpg)**`flex-end`**：交叉轴的**终点对齐**
 .box {
        height: 700px;
        background: blue;
        display: flex;
        align-items: flex-end;
    }
实现效果：
[![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1602304668193-53feca34-b37c-486a-b46a-465bc8ae08a3.jpeg#align=left&display=inline&height=1056&margin=%5Bobject%20Object%5D&originHeight=1056&originWidth=2538&size=0&status=done&style=none&width=2538)](https://img2.mukewang.com/5e95a3ca0001550a25381056.jpg)
**`center`**： 交叉轴的**中点对齐**
.box {
        height: 700px;
        background: blue;
        display: flex;
        align-items: center;
    }
实现效果：
[![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1602304668196-3c6f2d5f-bd6e-41c5-ad80-8091fd2f9aef.jpeg#align=left&display=inline&height=1056&margin=%5Bobject%20Object%5D&originHeight=1056&originWidth=2537&size=0&status=done&style=none&width=2537)](https://img3.mukewang.com/5e9667880001796c25371056.jpg)
**`baseline`**：项目的**第一行文字的基线对齐**。
.box {
        height: 700px;
        background: blue;
        display: flex;
        align-items: baseline;
    }
三个盒子中可设置不同的字体大小
实现效果：
[![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1602304668214-4d91829f-445d-412f-8ce6-f96b4c204459.jpeg#align=left&display=inline&height=1053&margin=%5Bobject%20Object%5D&originHeight=1053&originWidth=2534&size=0&status=done&style=none&width=2534)](https://img3.mukewang.com/5e9668ff0001f8f125341053.jpg)
**`stretch`**`（默认值）`：如果项目**未设置高度**或设为**auto**，将**占满整个容器的高度**
_ .box {_
_        height: 300px;_
_        background: blue;_
_        display: flex;_
_        align-items: stretch;_
_    }_


   _ .box div {_
_        /*不设置高度，元素在垂直方向上铺满父容器*/_
_        width: 200px;_
_    }_
实现效果：
[![](https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1602304668179-86860ec0-a2df-4a06-aa78-e687543fd61c.jpeg#align=left&display=inline&height=453&margin=%5Bobject%20Object%5D&originHeight=453&originWidth=2539&size=0&status=done&style=none&width=2539)](https://img2.mukewang.com/5e9669ef00017e0e25390453.jpg)
# 水平导航栏
![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1606976280378-b7903232-02d8-4fd9-9798-04023a91d109.png#align=left&display=inline&height=80&margin=%5Bobject%20Object%5D&name=image.png&originHeight=159&originWidth=331&size=8902&status=done&style=none&width=165.5)![image.png](https://cdn.nlark.com/yuque/0/2020/png/2617721/1606976651637-b5565b2d-133c-47da-9885-f02797861faa.png#align=left&display=inline&height=266&margin=%5Bobject%20Object%5D&name=image.png&originHeight=532&originWidth=725&size=42993&status=done&style=none&width=362.5)






