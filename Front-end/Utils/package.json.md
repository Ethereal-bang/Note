> [package.json 文件 -- JavaScript 标准参考教程 - 阮一峰](https://javascript.ruanyifeng.com/nodejs/packagejson.html)



# config 字段

用于添加命令行的环境变量

```json
"config": { "port": "8080" }
```

```js
http
  .createServer(...)
  .listen(process.env.npm_package_config_port)
```



