# 正则方法

+ <span style="font-size:22px">选取原则：</span>

    需要知道一个字符串是否与一个正则表达式匹配 [`RegExp`](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/RegExp) ，可使用 [`RegExp.test()`](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/RegExp/test) 。

    只是需要第一个匹配结果，也可以使用 [`RegExp.exec()`](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/RegExp/exec) 。

    如果想要获得捕获组，并且设置了全局标志，你需要用 [`RegExp.exec()`](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/RegExp/exec) 或者 [` String.prototype.matchAll()`](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/String/matchAll)

+ **String.prototype.match(regexp)**——检索返回一个字符串匹配正则表达式的结果

    

# 字符匹配

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



# 参考

+ 总：

    [JS正则表达式完整教程（略长）- 掘金](https://juejin.cn/post/6844903487155732494#heading-1)
    
    