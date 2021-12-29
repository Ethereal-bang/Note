# 正则方法

+ <span style="font-size:22px">选取原则：</span>

    需要知道一个字符串是否与一个正则表达式匹配 [`RegExp`](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/RegExp) ，可使用 [`RegExp.test()`](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/RegExp/test) 。

    只是需要第一个匹配结果，也可以使用 [`RegExp.exec()`](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/RegExp/exec) 。

    如果想要获得捕获组，并且设置了全局标志，你需要用 [`RegExp.exec()`](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/RegExp/exec) 或者 [` String.prototype.matchAll()`](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/String/matchAll)

+ **String.prototype.match(regexp)**——检索返回一个字符串匹配正则表达式的结果

    

# 字符匹配

正则表达式是匹配模式，要么匹配字符，要么匹配位置

## 精准匹配

匹配确切的一段子串，字符串只有包含该子串才匹配成功

```js
reg = /precise/;
reg.test('precisetest')	// true
```

## 模糊匹配

+ <span style="font-size:22px">横向模糊：</span>

    横向模糊匹配——一个正则可匹配字符串<span style="color:red">长度不固定</span>

    实现方式是使用<span style="color:red">量词</span>，如`{m,n}`表示连续出现最少 m 次，最多 n 次。（m、n 之间不能有空格！）

+ <span style="font-size:22px">纵向模糊：</span>

    纵向模糊匹配——具体到某一位字符时可以有多种可能

    实现方式是使用<span style="color:red">字符组</span>，如`[abc]`表示该字符可以是字符“a”、“b”、“c”中的任何一个。

```js
// 横向模糊
reg = /ab{2,4}c/g;
str = "abbbcc+abcc";
str.match(reg);	// [ 'abbbc' ]
// 纵向模糊
reg = /a[012]b/g;
str = "a01b a0b a3b";
str.match(reg);	// [ 'a0b', 'a2b']
```

+ **正则修饰符`g`**——表示全局匹配，在目标字符串按顺序找到满足匹配模式的所有子串。强调的是“所有”，而不是“第一个”

## 字符组

表示匹配字符组里的一个字符，如`[abc]`表示该字符可以是字符“a”、“b”、“c”中的任何一个。

+ <span style="font-size:22px">范围表示法：</span>

    如果字符组里的字符特别多，可以使用范围表示法

    **连字符`-`来简写：**如`[123456abcdefGHIJKLM]`，可以写成`[1-6a-fG-M]`

+ <span style="font-size:22px">排除字符组/反义字符组：</span>

    **字符组第一位放`^`(脱字符)：**如`[^ab]`，表示是一个除"a"、"b"之外的任意一个字符

+ <span style="font-size:22px">常见简写形式：</span>

    + **`\d`**——[0-9]，digit 数字

    + **`\D`**——\[^0-9]
    + **`\w`**——[0-9a-zA-Z_]，word，数字、大小写字母、下划线
    + **`\W`**——\[^0-9a-zA-Z_]，非单词字符
    + `.`——\[^\n\r\u2028\u2029]，通配符

## 量词

`{m,n}`

+ <span style="font-size:22px">常见简写形式：</span>
    + **`{m,}` / `{m}`**——至少出现 m 次
    + **`?`**——可有可无
    + **`+`**——`{1,}`，至少一个
    + **`*`**——出现任意次(*包括 0*)

+ <span style="font-size:22px">贪婪匹配和惰性匹配：</span>

    

# 位置匹配

+ <span style="font-size:22px">位置的特性：</span>

    把位置理解为空字符`""`

    ```js
    "hello" == "" + "" + "he" + "" + "llo"; 
    ```

    也就是说字符之间的位置可以写成多个。

## 如何匹配位置

ES5 中共有 6 个锚字符：`^`  `$` `\b` `\B` `(?=p)` `(?!p)`

+ <span style="font-size:22px">`^`、`$` ——开头、结尾</span>

    `^`（脱字符）——匹配开头，多行匹配中匹配行开头

    `$`——匹配结尾，多行匹配行结尾

    如，将字符串开头和结尾位置替换为`#`（注意替换的是位置，不是字符，所以被替换的位置本身没有字符）：

    ```js
    var result = "hello".replace(/^|$/g, '#');
    console.log(result); 	// "#hello"
    ```

+ <span style="font-size:22px">`\b`——单词边界、`\B` </span>

    `\b`——单词边界；`\B`——非单词边界

    ```js
    "[JS] Lesson_01.mp4".replace(/\b/g, '#'); // "[#JS#] #Lesson_01#.#mp4#"
    ```

+ <span style="font-size:22px">`?=p`——`p`前的位置、`?!p`：</span>

    其中`p`是一个子模式，`?=p`表示`p`前面的位置

    如，

    ```js
    "hello".replace(/(?=l)/g, '#');	// "he#l#lo"
    "hello".replace(/(?!=l)/g, '#');	// "#h#ell#o#"
    ```

    



# 正则中的括号()

## 分组和分支结构

+ <span style="font-size:20px">分组：</span>

    `/a+/`匹配连续出现的“a”，而要匹配连续出现的“ab”时，需要使用`/(ab)+/`。

    其中括号是提供分组功能，使量词`+`作用于“ab”这个整体

    如`/(^$)/g`：![image-20211221135228098](https://gitee.com/ethereal-bang/images/raw/master/20211221135235.png)

+ <span style="font-size:20px">分支：</span>

    在多选分支结构`(p1|p2)`中，此处括号的作用也是不言而喻的，提供了子表达式的所有可能

    ```js
    var regex = /^I love (JavaScript|Regular Expression)$/;
    console.log( regex.test("I love JavaScript") );
    console.log( regex.test("I love Regular Expression") );
    ```

    如`/(^|$)/g`：![image-20211221135331123](https://gitee.com/ethereal-bang/images/raw/master/20211221135331.png)



# 参考

+ 总：

    [JS正则表达式完整教程（略长）- 掘金](https://juejin.cn/post/6844903487155732494#heading-1)
    
    