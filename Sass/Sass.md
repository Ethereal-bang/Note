# 配置

> Ruby、Sass、Compass

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

VS Code：

1. VScode里安装插件 Easy Sass

2. 对插件进行配置，选择**编译风格**

3. 新建文件`.scss`，保存后自动新建`.css`文件，**实际应用中只需引入该`.css`文件**即可

    > 因为不支持执行`.scss`文件



**编译风格**：

SASS 提供四个编译风格选项：

+ **nested**

    默认值，嵌套缩进

+ **expanded**

    没有缩进、扩展==？==

+ **compact**

    简洁格式

+ **compressed**

    压缩后，生产环境一般选择此



# 基本用法

变量、计算功能、



## 变量

Sass 为 css 引入了变量

**用途**：

+ 把反复使用的`css`属性值定义成变量，然后通过变量名引用它们，而无**需重复书写**这一属性值
+ 可以赋予仅使用一次的属性值一个**易懂的变量名**，让人一眼就知道这个属性值的用途



sass 使用**`$`为开头标识变量**



**引用变量**：

```scss
$highlight: #F90;

div {
    color: $highlight;
}
```



**声明变量时引用**：变量值也可以引用其他变量

``` scss
$highlight: #F90;
$highlight-border: 1px solid $highlight-color;
```



如果变量需要**镶嵌在字符串**中，就必须写在**`#{}`**内

``` scss
$side : left;
div {
  background-color: $yellow;
  margin-#{$side}: 20px;
}
```



## 计算功能

允许在代码中**使用算式**：

```scss
div {
  background-color: $yellow;
  margin-#{$side}: 20+30px;
  margin-top: (20px+30);
}
```



## 嵌套

在 Sass 中，可以像俄罗斯套娃那样在**规则块中嵌套规则块**，使样式可读性更高



+ **选择器嵌套**：

    ```scss
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

+ **属性嵌套**:

    ```scss
    p {
        border: {
          color: black;
          style: dotted;
        }
    }
    ```

+ **引用父选择器**：

    **`&`**引用父元素

    ```scss
    a {
     	&:hover {
        background-color: blueviolet;
    }
    ```

    直接写`:hover `不行，不是个属性不能像嵌套那样写

+ **群组选择器的嵌套**：

    ``` scss
    div {
    	p, a {background-color: aliceblue}
    }
    ```




## 注释

SASS 共两种注释风格

+ **标准 CSS 注释 `/* comment */`**：

    保留到编译后文件

+ **单行注释`// comment`**：

    编译后省略

+ **重要注释`/*! comment */`**：

    即使压缩模式编译也会保留，通常可用于声明版权信息



## 子组合选择器 和 同层组合选择器：> 、+、 ~

上边这三个组合选择器必须和其他选择器配合使用，以指定浏览器仅选择某种特定上下文中的元素



**介绍**：

**子组合选择器`>`**选择 该元素的**直接子元素**

**同层相邻组合选择器`+`**选择 该元素后**紧跟的一个同级**元素	==?==

**同层全体组合器`~`**，选择 所有跟在**该元素后的同层元素**，不管它们之间隔了多少其他元素



**应用**

可以把这些组合选择器应用到`sass`的嵌套规则中

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



# 代码的重用

## 继承

允许选择器间的继承，使用**`@extend`命令**

```scss
p {
  @extend div;
  margin-top: 0;
}
```



+ **继承的高级用法**

    大多数情况下我们可能只用对类继承，但有些场合可能会想要做得更多。

    最常用的一种高级用法是**继承一个 HTML 元素的样式**。

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

    

+ **何时使用**：

    因为继承是基于类的（*也有时基于其他类型选择器*），所以继承应该是建立在语义化的关系上

    当一个元素拥有的类（*比如 `.seriousError`*）表明它属于另一个类（*如 .error*），这时使用继承再合适不过了。

    

    如果你不小心，可能会让生成的 css 中包含大量的选择器复制，避免这种情况出现的最好方法就是不要在 css 规则中使用后代选择器去继承 css 规则

    

## Mixin 混合器

+ **`@mixin`**定义一个可以重用的代码块：

    ```scss
    @mixin left {
      float: left;
      margin-left: 10px;
    }
    ```

    

+ **`@include`命令调用这个`mixin`**：

    ```scss
    div {
      @include left;
    }
    ```

    

+ **可指定参数和默认值**给混合器传参：

    混合器并不一定总得生成完全相同的样式，可以通过在`@include`引用混合器时给混合器传参，来定制混合器生成的精确样式。这种方式和 JS 的`function`很相似

    ```scss
    @mixin left($value: 10px) {
      float: left;
      margin-right: $value;
    }
    
    // 使用时按需加入参数
    div { @include left(20px) };
    ```

    为了解决**参数之间顺序**难以辨认的问题，sass 允许通过**`$name: value`**的形式指定每个参数的值。这种形式的传参，参数顺序就不重要了，只需要保证没有漏掉参数即可，例如：

    ``` scss
    a {
        @include link-colors(
            $normal: blue,
            $visited: green,
        );
    }
    ```

    

+ 铭记**混合器和类的区别**：

    最重要的区别就是，类名是在 html 文件中应用，而混合器是在样式表中应用的。这就意味着，

    + 类名具有语义化含义，而不仅仅是一种展示性的描述，用来描述`html`元素的含义而不是外观
    + 混合器是展示性的描述，用来描述一条 css 规则应用之后产生怎样的效果

    

+ **什么时候使用混合器**：

    当一段需要不停重复的样式**本身就是一个逻辑单元**（*放在一起有意义的属性*），那就应该把这段样式构造成优良的混合器

    


## 颜色函数

提供了一些内置颜色函数，以便生成系列颜色

```scss
lighten(#cc3, 10%) // #d6d65c
darken(#cc3, 10%) // #a3a329
grayscale(#cc3) // #808080
complement(#cc3) // #33c	返回一个补充色
// ...
```



## 插入文件

**`@import`**命令用来插入外部文件

CSS 有一不常用的特性，即`@import`规则，它允许在一个`css`文件中导入其他`css`文件。然而，只有执行到`@import`时，浏览器才会去下载其他`css`文件，这导致页面加载起来特别慢

Sass 的`@import`规则不同的是，sass 的`@import`规则在**生成 `css`文件时就把相关文件导入**进来。意味着所有相关的样式被归纳到了同一个`css`文件中，而无需发起额外的下载请求。另外，所有在被导入文件中**定义的变量和混合器均可在导入文件使用**



**sass 的`@import`规则的使用**：

使用`sass`的`@import`规则并不需要指明被导入文件的全名，你可以**省略**`.sass`或`.scss`文件**后缀**



### SASS 局部文件

当通过`@import`把 sass 样式分散到多个文件时，我们通常只想生成少数几个 css 文件。那些**专门为`@import`命令而编写的 sass 文件并不需要生成对应的独立 css 文件**，这样的 sass 文件称为局部文件。对此，sass 有一个特殊的约定命名这些文件：sass 局部文件的文件名以**下划线开头**

这样 sass **只把这个文件用作导入而不编译输出 css**



当你`@import`一个局部文件时，可**省略**文件名开头的下划线

举例来说，如果想导入`themes/_night-sky.scss`这个局部文件里的变量，只需在样式表中写`@import"themes/night-sky";`。



局部文件可**被不同的文件引用**，当一些样式需要在多个页面甚至多个项目中使用时，这非常有用

在这种情况下，有时需要在样式表中对导入的样式稍作修改，sass 有一个功能可以解决这个问题，即后面的 默认变量值。



### Sass 的嵌套导入

sass **允许`@import`命令写在 css 规则内**。这种嵌套导入方式下生成对应的 css 文件时，局部文件会被直接插入到 css 规则内导入它的地方



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



被导入的局部文件中定义的所有变量和混合器，**不会全局生效**

**适用场景**：可以通过嵌套导入只对站点中某一特定区域运用某种颜色主题或其他的样式



### 原生的 CSS 导入

由于 sass 兼容原生 css，所以它也支持原生的 css`@import`。尽管通常 sass 中使用`@import`时，sass 会尝试找到对应的 sass 文件并导入进来，但在下列三种情况会生成原生 css `@import`，尽管会造成浏览器解析 css 时的额外下载：

+ 被导入文件后缀`.css`
+ 被导入文件名字是个 URL 地址（*如http://www.sass.hk/css/css.css*），由此可用谷歌字体 API 提供的相应服务==？==
+ 被导入文件名字是 css 的 url() 值==？==

这就是说，不能用 sass 的`import`来直接导入一个原始的 css 文件，因为 sass 会认为你想用 css 原生`@import` 	因为这样就不能满足 sass 的 `@import`规则了

但是，因为 sass 的语法**完全兼容 css**，所以可以把原始 css 文件名改为 `.scss`后缀，即可直接导入原生 css 了



## 默认变量值

一般情况，我们**反复声明一个变量**，只有最后一处声明有效且覆盖前边的声明值



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



# 高级用法

## 条件语句

`()`可省略

```scss
ol {
  @if (false) {
     background-color: darken(yellow, 10%);
  } @else {
    background-color: lighten(yellow, 10%);
  }
}
```



## 循环语句

+ **for 循环**：

    ```scss
    @for $i from 1 to 10 {
      .border-#{$i} {
        border: #{$i}px solid blue;
      }
    }
    ```

+ **while 循环**：

    ```scss
    $i: 6;
    @while $i > 0 {
      .item-#{$i} { width: 2em * $i; }
      $i: $i - 2;
    }
    ```

+ **each 命令**：

    作用与`for`类似

    ```scss
    @each $member in a, b, c, d {
      .#{$member} {
        background-image: url("/image/#{$member}.jpg");
      }
    }
    ```



## 自定义函数

```scss
@function double($n) {
  @return $n * 2;
}

#sidebar {
  width: double(5px);
}
```



# 参考链接

[Sass基础教程 Sass快速入门 Sass中文手册 | Sass中文网](https://www.sass.hk/guide/)

[SASS用法指南 - 阮一峰的网络日志](http://www.ruanyifeng.com/blog/2012/06/sass.html)

