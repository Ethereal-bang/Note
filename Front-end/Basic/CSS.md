# CSS

+ <span style="font-size:22px">style 位置：</span>

    ```html
    <!DOCTYPE html>
    <html>
      <head>
          <link href="base.css" rel="stylesheet" type="text/css" />
        	<style type="text/css">
        		/*嵌入式*/
        	</style>
      </head>
      <body>
          <p style="color:red; font-size:12px">内联式</p>	</body>
    </html>
    ```



## 选择器

### 选择器种类

+ `>`子选择器——用于选择指定标签元素的第一代子元素

+ `  `后代选择器——用于选择指定标签元素下的后辈元素

    > 注：> 作用于元素的第一代后代，空格作用于元素的所有后代

+ 属性选择器——具有特定属性的HTML元素样式

    > **Eg：**把包含标题（title）的所有元素变为蓝色
    > `[title]{color:blue;}`

+ *通用选择器

+ 伪类选择器

    `:first-child`、`:nth-child()`

    + `<父元素> :nth-child()`
    + `<当前元素>:nth-child()`——当前元素的**兄弟元素**中第几个标签
    + `:nth-of-type()`——当前元素的**兄弟元素**中同类型的第几个标签

    > `td:nth-of-type(n + 2)` n 从 0 开始取值，即选择除第一个外所有 td

+ 兄弟选择器

    + [相邻兄弟选择器 +](https://developer.mozilla.org/zh-CN/docs/Web/CSS/Adjacent_sibling_combinator)：介于两个选择器之间，当第二个元素紧跟在第一个元素<span style="color:red">之后</span>，并且两个元素都是属于同一个父元素的子元素，则第二个元素将被选中
    + [通用兄弟选择器 ~](https://developer.mozilla.org/zh-CN/docs/Web/CSS/General_sibling_combinator)：位置无须紧邻，只须同层级，`A~B` 选择`A`元素<span style="color:red">之后</span>所有同层级`B`元素
    + [为什么都只能选择后面的元素](https://www.zhangxinxu.com/wordpress/2016/08/css-parent-selector/)——浏览器解析HTML文档，是从前往后，由外及里的。

+ `,` 分组选择器



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

<span style="font-size:20px">[border](https://developer.mozilla.org/zh-CN/docs/Web/CSS) 、[outline](https://developer.mozilla.org/zh-CN/docs/Web/CSS/outline)：</span>

border 和 outline 很类似，但有如下区别：

- outline不占据空间，绘制于元素内容周围。
- 根据规范，outline通常是矩形，但也可以是非矩形的。

>  如图黑色的就是 input 的默认 outline 轮廓：
>
> ![image-20220407133048136](https://gitee.com/ethereal-bang/images/raw/master/20220407133055.png)   

<span style="font-size:20px">[box-sizing](https://developer.mozilla.org/zh-CN/docs/Web/CSS/box-sizing)</span>

定义如何计算一个元素的总宽度和总高度

border-box：会包括 border 宽度

<span style="font-size:20px">background:</span>

元素默认背景色是 transparent 透明，意味着如果不设置元素背景色将会显示为下方元素（*z-index*) 的颜色

### 颜色

<span style="font-size:20px">透明 </span>

**opacity**——不透明度，默认 1; **transparent**——透明

```css
#demo {
 opacity: 0;	// 元素完全透明 不可见
 background: transparent;	// 背景透明
}
```

### 文本

多行文本溢出后省略

```css
p {
  width: 250px;
	height: 20px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
```



<span style="font-size:20px">cursor:</span>

+ pointer：鼠标指向变小手

    > 通过这种方式美化交互按钮





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



## 水平定位

+ <span style="font-size:20px">text-align:</span>



## 垂直定位

+ <span style="font-size:20px">[vertical-align](https://developer.mozilla.org/zh-CN/docs/Web/CSS/vertical-align):</span>

    只作用于 inline、table-cell 元素，决定垂直对齐方式

    + 相对于同一行其他元素定位——`top`、`bottom`

        > **解决：**同一行中高度较小的会因为其余元素显示在容器下方

    

## 布局模型

在网页中，元素有三种布局模型：
1、流动模型（Flow）
2、浮动模型 (Float)
3、层模型（Layer）

+ <span style="font-size:20px">文档流：</span>

    文档流指的是元素排版布局过程中，元素会**默认**自动从左往右，从上往下的<span style="color:red">流式排列方式</span>



### 浮动模型 float

脱离文档流，但浮动后仍保持流动性（*占位* ）：

`float: left`



### 层模型 position

position 属性

+ 具有以下属性的元素称为定位元素（*即除了 static* ）
    + 绝对定位(position: absolute)
    + 相对定位(position: relative)
    + 固定定位(position: fixed)

    > `absolute`、和`fixed`脱离文档流，子元素无法撑起父元素的高度

+ **fixed**

    固定定位的元素会**始终位于**浏览器窗口内**视图**的**某位置**

+ **position:absolute**
    **相对**于最近的一个**具有定位属性（**position值不是sta..的）的**父包含块**进行绝对定位。如果不存在这样的包含块，则**相对**于**body**元素，即相对于浏览器窗口

+ **relative**

    **相对**于**以前的位置**移动,偏**移前的位置保留**

    > **Eg：**后面的 `span` 元素显示在 `div` 元素以前位置的后面

<img src="https://cdn.nlark.com/yuque/0/2020/jpeg/2617721/1601886027728-4617a57b-0f32-4ec6-9ead-1c7b80dbc159.jpeg#align=left&display=inline&height=489&margin=%5Bobject%20Object%5D&originHeight=489&originWidth=594&size=0&status=done&style=none&width=594" style="zoom:50%;" />



## Flexbox

+ **align-content、align-items**——分别定义了<span style="color: orange">主轴</span>和<span style="color: orange">交叉轴</span>上对齐方式



## z 方向

+ `z-index` 控制重叠元素间 z-order
+ `z-index` 不能使 <span style="color:orange">Flow 布局</span>元素先于<span style="color:orange">脱离文档流的元素</span>显示



# Other

<span style="font-size:20px">盒模型</span>

<img src="https://cdn.nlark.com/yuque/0/2020/gif/2617721/1603183304416-4a408d18-d3f9-4296-b153-ca22ea302eb0.gif#align=left&display=inline&height=289&margin=%5Bobject%20Object%5D&originHeight=289&originWidth=536&size=0&status=done&style=none&width=536" style="zoom: 33%;" />



<span style="font-size:20px">Sprite</span>

又称雪碧图。把多张图片进行合成，使用时通过 css 进行定位从而减少请求次数的有效手段



# REF

+ CSS 定位：

    [CSS 拷问：水平垂直居中方法你会几种？| Liuyib`s Blog](https://liuyib.github.io/2020/04/07/css-h-and-v-center/)

+ Other:

    [css中雪碧图(sprite)的使用及制作方法](https://blog.csdn.net/z591102/article/details/106993310)

