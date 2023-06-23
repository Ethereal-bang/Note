# TailwindCSS

原子类 CSS (Atomic/Utility-First CSS) 框架

> **VS 语义化 CSS:**
>
> 框架本身为我们提供了一系列类名来表示对应的 CSS 规则
>
> 当我们想写一个 css 样式时，我们不再需要给标签一个语义化类名，然后再给类名添加 CSS 规则，我们只需要给标签一个框架提供的类名就行



# [功能类](https://www.tailwindcss.cn/docs/utility-first)

> **EG:**
>
> 常规样式编写方式——定制的设计需要定制的 CSS:
>
> ```html
> <h4 class="chat-notification-title">ChitChat</h4>
> <style>
> .chat-notification-title {
>     color: #1a202c;
>     font-size: 1.25rem;
>     line-height: 1.25;
> }
> </style>
> ```
>
> Tailwind——直接在 HTML 中应用预先存在的类来设置元素的样式
>
> ```html
> <div class="text-xl font-medium text-black">
> 	ChitChat
> </div>
> ```
>
> 使用 [font size](https://www.tailwindcss.cn/docs/font-size)，[text color](https://www.tailwindcss.cn/docs/text-color)，和 [font-weight](https://www.tailwindcss.cn/docs/font-weight) 功能类 (`text-xl`，`text-black`，`font-medium` 等等) 给卡片文字设置样式

**使用功能类:**

```html
<button class="py-2 px-4 font-semibold rounded-lg shadow-md text-white bg-green-500 hover:bg-green-700">
  Click me
</button>
```



**使用 Tailwind 的 `@apply` 功能创建抽象的 CSS 类:**

```html
<button class="btn btn-green">Button</button>

<style>
.btn {
  @apply py-2 px-4 font-semibold rounded-lg shadow-md;
}
.btn-green {
  @apply text-white bg-green-500 hover:bg-green-700;
}
</style>
```



## 大小

```html
<img class="w-full h-full" />
```

