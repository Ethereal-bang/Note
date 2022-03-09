# 移动端

## 前置概念

### 像素

+ <span style="font-size:22px">设备独立像素——dip：</span>

    现在使用的智能手机，不管分辨率多高，他们所展示的界面比例都是基本类似的。

    <img src="https://www.yuque.com/api/filetransfer/images?url=http%3A%2F%2Fcdn.pannnda.com%2Fcourse%2Fmobile%2Fassets%2Fdip2.png&sign=a8eb6d37036a7196fe57c98a1d1f1469b4724f67b6a86efa58751b0d55fba8df#align=left&display=inline&height=1126&margin=%5Bobject%20Object%5D&originHeight=1126&originWidth=1294&status=done&style=none&width=1294" alt="img" style="zoom: 25%;" />

    所以我们必须用一种单位来同时告诉不同分辨率的手机，它们在界面上显示元素的大小是多少，这个单位就是设备独立像素(Device Independent Pixels)，简称 DIP。

    比如列表的宽度为 300 个像素，那么在一条水平线上，白色手机会用 300 个物理像素去渲染它，而黑色手机实际上会用 600 个物理像素去渲染它。实际上我们可以说：列表的宽度为 300 个设备独立像素。

+ <span style="font-size:22px">设备像素比——dpr：</span>

    设备像素比(device pixel ratio)简称dpr，即物理像素和设备独立像素的比值

    浏览器为我们提供了 window.devicePixelRatio 来帮助我们获取 dpr

+ <span style="font-size:22px">CSS 像素：</span>

    在写 CSS 时，我们用到最多的单位是 px，即 CSS 像素，当页面缩放比例为 100% 时，一个 <span style="color:red">CSS 像素</span>等于一个<span style="color:red">设备独立像素</span>。

    但是 CSS 像素是很容易被改变的，当用户对浏览器进行了放大，CSS 像素会被放大，这时一个 CSS 像素会跨越更多的物理像素。

    **页面的缩放系数 = CSS像素 / 设备独立像素**

### 视口

视口(viewport)代表当前可见的计算机图形区域。在 Web 浏览器术语中，通常与浏览器窗口相同，但不包括浏览器的 UI

一般我们所说的视口共包括三种：布局视口、视觉视口和理想视口，它们在屏幕适配中起着非常重要的作用。

1. <span style="font-size:22px">布局视口——layout viewport：</span>

    布局视口是网页布局的基准窗口

    <img src="https://cdn.nlark.com/yuque/0/2021/png/21635775/1638536360741-8a14c800-11c1-466c-9f0e-531af0e94d1d.png?x-oss-process=image%2Fresize%2Cw_497%2Climit_0" alt="18030682-07a1ff787499c9f2.png" style="zoom:53%;" />

    在 **PC 浏览器**上，布局视口就等于当前浏览器的窗口大小（不包括borders 、margins、滚动条）

    在**移动端**，布局视口被赋予一个默认值，大部分为 980px，这保证 PC 的网页可以在手机浏览器上呈现，但是非常小，用户可以手动对网页进行放大

    **`document.documentElement.clientWidth / clientHeight`**获取布局视口大小

2. <span style="font-size:22px">视觉视口——visual viewport：</span>

    <img src="https://cdn.nlark.com/yuque/0/2021/png/21635775/1638536415726-efb060d7-6a57-46ce-a036-5b169697101c.png?x-oss-process=image%2Fresize%2Cw_500%2Climit_0" alt="18030682-d92903bf680a85cd.png" style="zoom:53%;" />

    用户通过屏幕真实看到的区域，默认等于当前浏览器的窗口大小（包括滚动条宽度）

    **`window.innerWidth / innerHeight`**来获取视觉视口大小

    用户对浏览器进行缩放时，不会改变布局视口的大小，所以页面布局是不变的，但是缩放会改变视觉视口的大小

    **布局视口会限制你的 CSS 布局而视觉视口决定用户具体能看到什么**

3. <span style="font-size:22px">理想视口：</span>

    网站页面在移动端展示的理想大小，即不需要用户缩放和横向滚动条就能正常的查看网站的所有内容，显示的文字的大小是合适的

+ <span style="font-size:22px">Mate viewport：</span>

    可以借助`<meta>`元素的 viewport 来帮助我们设置视口、缩放等，从而让移动端得到更好的展示效果

    ```html
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    ```

    | `Value`         | 可能值                 | 描述                                                      |
    | --------------- | ---------------------- | --------------------------------------------------------- |
    | `width`         | 正整数或`device-width` | 以`pixels`（像素）为单位， 定义布局视口的宽度。           |
    | `initial-scale` | `0.0 - 10.0`           | 定义页面初始缩放比率。                                    |
    | `minimum-scale` | `0.0 - 10.0`           | 定义缩放的最小值；必须小于或等于`maximum-scale`的值。     |
    | `maximum-scale` | `0.0 - 10.0`           | 定义缩放的最大值；必须大于或等于`minimum-scale`的值。     |
    | `user-scalable` | `yes / no`             | 如果设置为 `no`，用户将不能放大或缩小网页。默认值为 yes。 |

    为了在移动端让页面获得更好的显示效果，让布局视口、视觉视口都尽可能等于理想视口

    `device-width`就等于理想视口的宽度，所以设置`width=device-width`就相当于让布局视口等于理想视口

### CSS 单位 rem

rem 就是相对于根元素`<html>`的 font-size 来做计算

rem 使用保证根据窗口大小改变所有元素能等比例缩放。



## 移动端适配方案

### flexble（已弃用，仅了解）

引用 flexible 后，在页面上统一使用 rem 来布局

随着时代在变化，前端技术在不断的变化，viewport 单位得到众多浏览器的兼容， 上面这种方案现在已经被**官方弃用**。Flexible 已经不是最佳方案，Flexible 已经完成了他自身的历史使命，我们可以放下 Flexible，拥抱新的变化。

### vw、vh

`vh、vw`方案即将视觉视口宽度 `window.innerWidth`和视觉视口高度 `window.innerHeight` 等分为 100 份。

> 上面的 flexible 方案就是模仿这种方案，因为早些时候`vw`还没有得到很好的兼容。

+ **`vw`**（Viewport's width)：`1vw`等于<span style="color:red">视觉视口</span>的`1%`
+ **`vh`**（Viewport's height)：`1vh` 为<span style="color:red">视觉视口高度</span>的`1%`

![image-20211219001627753](https://gitee.com/ethereal-bang/images/raw/master/20211219001634.png)

+ **使用：**

    比例关系不用自己换算，可以使用 PostCSS 的 postcss-px-to-viewport 插件帮我们完成这个过程。写代码时，我们只需要根据 UI 给的设计图写 px 单位即可。

    碰到`vw`和`px`混合使用的时候，可以结合`calc()`函数一起使用。



## 横屏适配



## IPhone X 适配



## 移动端事件





# 参考

+ 总：

    [移动端 · 语雀](https://www.yuque.com/ldfgqb/fpkor3/vhuagw)