# 准备工作

1. 安装 Ruby
2. 安装基于 Ruby 的 Sass 和 Compass

cmd 里输入以下俩条命令。

``` 
gem install sass
gem install compass
```

> sass 的常用命令
>
> ```
> gem update sass
> 
> sass -v
> 
> sass -h
> ```

3. VScode里安装插件 Easy Sass
4. 对插件进行配置，选择编译风格
5. 新建文件`.scss`，保存后自动新建`.css`文件，实际应用中只需引入该`.css`文件即可



# 变量

Sass 为 css 引入了变量，可以把反复使用的`css`属性值定义成变量，然后通过变量名引用它们，而无需重复书写这一属性值；或者，你可以赋予仅使用一次的属性值一个易懂的变量名，让人一眼就知道这个属性值的用途



sass 使用`$`来标识变量



声明变量时，变量值也可以引用其他变量

``` scss
$highlight: #F90;
$highlight-border: 1px solid $highlight-color;
```



引用变量：

``` scss
$highlight: #F90;

div {
    color: $highlight;
}
```

如果变量需要镶嵌在字符串中，就必须写在`#{}`内

``` scss
$side: left;

.rounded {
    border-#{$side}-radius: 5px;
}
```



# 嵌套

在 Sass 中，你可以像俄罗斯套娃那样在**规则块中嵌套规则块**，使样式可读性更高



## 选择器嵌套

``` scss
#content {
    article {
        h1 { color: #333 }
        p { margin-bottom: 1.4em }
    }
    #content aside { background-color: #EEE }
}

// 编译后
#content article h1 { color: #333 }
#content article p { margin-bottom:1.4em }
#content aside { background-color: #EEE }
```



## &引用父选择器

在嵌套的代码块内，可以使用`&`引用父元素

如`a:hover`**伪类**，可以写成。

``` scss
a {
    &:hover { color: #ffb3ff; }
}
```



**引用父选择器**

``` scss
.button {
    font-weight: bold;
    text-decoration: none;
    &_underline {
        text-decoration: underline
    }
}

// CSS
.button {
    // ...
}
.button_underline {
    text-decoration: underline;
}
```



## 群组选择器的嵌套

``` scss
.container {
    h1, h2, h3 { margin-bottom: .8em }
}
```



## 子组合选择器 和 同层组合选择器：> 、+、 ~

上边这三个组合选择器必须和其他选择器配合使用，以指定浏览器仅选择某种特定上下文中的元素



**介绍**：

子组合选择器`>`选择 该元素的直接子元素

同层相邻组合选择器`+`选择 该元素后紧跟的一个元素	==?==

同层全体组合器`~`，选择 所有跟在该元素后的同层元素，不管它们之间隔了多少其他元素



**应用**

可以把这些组合选择器应用到`sass`的嵌套规则中，外层选择器后边或是层选择器前边==？==

``` scss
article {
    ~ article { border-top: 1px dashed #ccc }
    > section { background: #eee }
    dl > {
        dt { color: #333 }
        dd { color: #555 }
    }
    nav + & { margin-top: 0 }
}
```



## 属性嵌套

属性也可以嵌套，如`border-color`属性，可以写成。

``` scss
p {
    border: {
		color: red;
    }
}
```

注意，属性（*也就是上例的 `border`*）后面必须加上冒号



除此，你还可以像下面这样，**指明例外规则**

``` scss
nav {
    border: 1px solid #ccc {
        left: 0px;
        right: 0px;
    }
}

// 这比下面这种同等样式的写法要好
nav {
    border: 1px solid #ccc;
  	border-left: 0px;
    border-right: 0px;
}
```



# 导入 SASS 文件

CSS 有一不常用的特性，即`@import`规则，它允许在一个`css`文件中导入其他`css`文件。然而，只有执行到`@import`时，浏览器才会去下载其他`css`文件，这导致页面加载起来特别慢

Sass 也有一个`@import`规则，但不同的是，sass 的`@import`规则在生成 `css`文件时就把相关文件导入进来。意味着所有相关的样式被归纳到了同一个`css`文件中，而无需发起额外的下载请求。另外，所有在被导入文件中定义的变量和混合器均可在导入文件中使用



**sass 的`@import`规则的使用**：

使用`sass`的`@import`规则并不需要指明被导入文件的全名，你可以省略`.sass`或`.scss`文件后缀



## SASS 局部文件

当通过`@import`把 sass 样式分散到多个文件时，我们通常只想生成少数几个 css 文件。那些专门为`@import`命令而编写的 sass 文件并不需要生成对应的独立 css 文件，这样的 sass 文件称为局部文件。对此，sass 有一个特殊的约定命名这些文件



这个特殊的约定即是，sass 局部文件的文件名以下划线开头

这样 sass 只把这个文件用作导入而不编译输出 css



当你`@import`一个局部文件时，还可**省略**文件名开头的下划线

举例来说，如果想导入`themes/_night-sky.scss`这个局部文件里的变量，只需在样式表中写`@import"themes/night-sky";`。



局部文件可被多个**不同的文件引用**，当一些样式需要在多个页面甚至多个项目中使用时，这非常有用

在这种情况下，有时需要在样式表中对导入的样式稍作修改，sass 有一个功能可以解决这个问题，即下面的 默认变量值。



## 默认变量值

一般情况，我们反复声明一个变量，只有最后一处声明有效且覆盖前边的声明值



但是有时这并不是我们想要的效果，假如写了一个可被他人通过`@import`导入的`sass`库文件，我们可能希望导入者可以自己修改`sass`库文件中的某些值。使用`sass`的`!default`标签可以实现这个目的。



`!default`的**含义**是：

如果这个变量被声明赋值了，那就用它声明的值；如果没有别声明赋值，则用此默认值



实际例子。

``` scss
$fancybox-width: 400px !default;

.fancybox {
	width: $fancybox-width;
}
```

上例中，如果用户导入此`sass`局部文件前声明了一个`fancybox-width`变量，那么你的局部文件中对`$fancybox-width`赋值`400px`的操作就无效。



## 文件导入

### Sass 的嵌套导入

sass 允许`@import`命令写在 css 规则内。这种嵌套导入方式下生成对应的 css 文件时，局部文件会被直接插入到 css 规则内导入它的地方



例子。

``` scss
// _blue-theme.scss 局部文件
aside {
    background: blue;
    color: white;
}

// main.css
.blue-theme { @import "blue-theme" }
```



被导入的局部文件中定义的所有变量和混合器，不会全局生效

这样我们就可以通过嵌套导入只对站点中某一特定区域运用某种颜色主题或其他的样式。



### 原生的 CSS 导入

由于 sass 兼容原生 css，所以它也支持原生的 css`@import`。尽管通常 sass 中使用`@import`时，sass 会尝试找到对应的 sass 文件并导入进来，但在下列三种情况会生成原生 css`@import`，尽管会造成浏览器解析 css 时的额外下载：

+ 被导入文件后缀`.css`
+ 被导入文件名字是个 URL 地址（*如http://www.sass.hk/css/css.css*），由此可用谷歌字体 API 提供的相应服务==？==
+ 被导入文件名字是 css 的 url() 值==？==

这就是说，你不能用 sass 的`import`来直接导入一个原始的 css 文件，因为 sass 会认为你想用 css 原生`@import` 	*因为这样就不能满足 sass 的 `@import`*规则了

但是，因为 sass 的语法**完全兼容 css**，所以可以把原始 css 文件名改为 `.scss`后缀，即可直接导入原生 css 了



# 静默注释

作用：

Css 中注释的作用有着帮助组织样式以及简单的样式说明等作用。但或许，你不希望每个浏览网站源码的人都能看到所有注释。



**格式**：

Sass 另外提供了一种不同于 css 标准注释格式`/* ... */`的注释语法，即静默注释，其内容不会出现在生成的 css 文件中

``` scss
body {
    color: #333;	// 不会出现在 .css 文件里
    paddingL 0;		/* 会出现在 .css 文件里
}
```



有种特殊情况，`/* ... */`注释内容也会被抹去：当注释出现在原生 css 不允许的地方（*如 css 属性或选择器中*）



# 混合器

## 如何使用

可以通过 sass 的混合器实现大段样式的重用



混合器使用 `@mixin`标识符定义，这个标识符给一大段样式赋予一个名字，这样就可以轻易地通过引用这个名字重用这段样式



下面地这段 sass 代码**定义**了一个非常简单的混合器，目的是添加跨浏览器的圆角边框。

``` scss
@mixin rounded-corners {
    -moz-border-radius: 5px;
  	-webkit-border-radius: 5px;
  	border-radius: 5px;
}
```

然后就可以在样式表中通过`@include`来**使用**这个混合器。

``` scss
notice {
    background-color: green;
    border: 2px solid #00aa00;
    @include rounded-corners;	// 调用
}
```



## 何时使用

大量的重用可能会导致生成的样式表过大，导致加载缓慢。因此要避免滥用



那么什么时候使用混合器呢。

当一段需要不停重复的样式本身就是一个逻辑单元（*放在一起有意义的属性*），那就应该把这段样式构造成优良的混合器



铭记**混合器和类的区别**

最重要的区别就是，类名是在 html 文件中应用，而混合器是在样式表中应用的。这就意味着，

+ 类名具有语义化含义，而不仅仅是一种展示性的描述，用来描述`html`元素的含义而不是外观
+ 混合器是展示性的描述，用来描述一条 css 规则应用之后产生怎样的效果



## 混合器中的 css 规则

混合器中不仅可以包含属性，也可以包含 css 规则



如下代码，

``` scss
@mixin no-bullets {
    list-style: none;
    li {
        list-style-image: none;
        list-style-type: none;
        margin-left: 0px;
    }
}
```

上面的代码中，就包含了选择器和选择器中的属性。



## 给混合器传参

混合器并不一定总得生成完全相同的样式，可以通过在`@include`引用混合器时给混合器传参，来定制混合器生成的精确样式。这种方式和 JS 的`function`很相似



**用法**：

当混合器被引用时，你可以把它当作一个 css 函数来传参

为了解决参数之间顺序难以辨认的问题，sass 允许通过`$name: value`的形式指定每个参数的值。这种形式的传参，参数顺序就不重要了，只需要保证没有漏掉参数即可

``` scss
@mixin link-colors($normal, $hover, $visited) {
    color: $normal;
    &:hover { color: $hover; }
    &:visited { color: $visited; }
}

// 引用
a {
    @include link-colors(
        $normal: blue,
        $visited: green,
        $hover: red
    );
}
// 等同于以下写法
a {
  @include link-colors(blue, red, green);
}
```



## 默认参数值

尽管给混合器加参数来实现定制很好，但有时有些参数没有定制的需要，这时候也需要赋值一个变量==？== 就时很痛苦的事了。

所以 sass 允许混合器声明时给参数赋默认值，从而不必在引用混合器时传入所有的参数



**用法**：

参数默认值采用`$name: default-value`的声明形式，默认值可以是属性值甚至其他参数的引用



# 选择器继承

选择器继承是说一个选择器可以继承为另一个选择器定义的所有样式，通过`@extend`语法实现

``` scss
.error {
    border: 1px solid red;
    background-color: #fdd;
}
.seriousError {
    @extend .error;	// 通过选择器继承来继承样式
    border-width: 3px;
}
```



## 何时使用

因为继承是基于类的（*也有时基于其他类型选择器*），所以继承应该是建立在语义化的关系上

当一个元素拥有的类（*比如 `.seriousError`*）表明它属于另一个类（*如 .error*），这时使用继承再合适不过了。



如果你不小心，可能会让生成的 css 中包含大量的选择器复制，避免这种情况出现的最好方法就是不要在 css 规则中使用后代选择器去继承 css 规则



## 继承的高级用法

大多数情况下我们可能只用对类继承，但有些场合可能会想要做得更多。

最常用的一种高级用法是继承一个`html`元素的样式。

尽管默认的浏览器样式不会被继承，因为它们不属于样式表中的样式，但对`html`元素添加的所有样式都会被继承。

``` scss
.box {
    @extend a;
}
// 只有自己对该html标签有定义的样式时，才不会报错
a {
    color: hotpink;
}
```



# 参考链接

[Sass基础教程 Sass快速入门 Sass中文手册 | Sass中文网](https://www.sass.hk/guide/)

[SASS用法指南 - 阮一峰的网络日志](http://www.ruanyifeng.com/blog/2012/06/sass.html)

