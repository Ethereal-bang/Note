> [Less.js](https://lesscss.org/)

# 配置

```shell
$ yarn add less
```



# 变量

定义变量:

```less
@font-green: rgb(34, 95, 52);
```

函数参数:

```less
.btn-with-bg (@url, @width, @height) {
  background: @url no-repeat;
  background-size: 120%;
  border-style: none;
  width: @width;
  height: @height;
  background-position: center center;
  cursor: pointer;
}
```



# 引入样式文件

```less
@import '../../assets/style/common.less';
```



# 函数

```less
width: calc(~'100% - @{left-menu-width}');
height: calc(~'100vh - 70px');
```

