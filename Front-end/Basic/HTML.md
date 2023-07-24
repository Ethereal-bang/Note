# HTML

+ **html文件注释:**<!--注释文字 -->



## 文档结构

1. `<!DOCTYPE html>`:文档类型声明，表示该文件为 HTML5文件。

2. `<html>`

3. `<head>`：可包含一些辅助性标签如`<title></title>`，`<link />`，`<meta />`，`<style></style>`，`<script></script>`等

    >   注：浏览器除了会在标题栏只显示`<title>`元素的内容外，不会向用户显示head元素内的其他任何内容。

4. `<body>`
    注：**body**的大小是由**内部元素**撑开，不是默认占满整个页面



**结构语义化**






# HTML 标签

+ **标签语义化**好处
    1. 搜索引擎友好，利于 SEO
    2. 无障碍阅读支持
+ **标签的语法**——HTML标签不区分大小写

+ <span style="font-size:20px">列表：</span>

    `<ul><li>`标签实现无序列表：
    
    `<ol><li>`标签实现有序列表


+ <span style="font-size:20px">超链接：</span>

    语法
    **`<a  href="目标网址"   title="鼠标滑过显示的文本">` 链接显示的文本**
    **`target`**属性：打开网页的方式，**self**(*默认* )和**blank**，当前页面打开链接、新窗口打开链接
    
    **a href="#"** 时表示一个空链接，点击时链接停留在当前页面，相当于刷新当前页面，有时使用该方法实现刷新本页面功能。如果没有设置特别的链接效果，那么点击效果和默认的点击链接效果一样
    一般用于返回当前页的某一点
    `#`后面可以跟任意标签的id，或者任意a标签的id或者name,点击链接就可以跳转到当前页面的这个节点的位置



## 表格 Table

**基本标签：**

1. `<table>…</table>`

2. `caption` 表格的标题

3. `thead`、`tbody` 语义化分区

4. `tr` 行、`td` 一行中的单元格、`th` 作为表头单元格（行或列的表头）

    > tr 用来设置表格的行，tr 里只能放 th 或者 td 标签，一组 tr 标签代表一行

**其他标签：**

+ colgroup:——对表格中的列进行组合，便于设置样式

    ```html
    <table>
      <colgroup>
      	<col />
        <col span=2 style=background: "red" />
        <col span=2 style=background: "blue" />
      </colgroup>
      <tr>
        
      </tr>
    </table>
    ```

    > 效果：2、3 列红，4、5 列蓝

**属性：**

+ widith、valign 垂直方向对齐方式、align 水平位置
+ rowSpan、colSpan 合并单元格



## 表单 Form

`<form>`创建表单，与用户交互

表单是可以把浏览者输入的数据传送到服务器端，这样服务器端程序就可以处理表单传过来的数据。
语法：

`<form method="传送方式" action="服务器文件">`

+ <span style="font-size:22px">input:</span>

    autoFocus 属性为 true 时，页面加载后输入框获取焦点（默认 false）



# REF

+ [写给女朋友的中级前端面试密集（含详细答案，15k级别）- 掘金](https://juejin.cn/post/6844904115428917255)

