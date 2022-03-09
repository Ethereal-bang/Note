const path = require('path');

// 连接路径
console.log(path.join("/path", "/user")); // \path\user

// 构建绝对路径
console.log(path.resolve(__dirname, "./2.json"));   // F:\FE-Develop\test-node\2.json

console.log(__dirname, " ", __filename)
// F:\FE-Develop\test-node  F:\FE-Develop\test-node\test-path.js