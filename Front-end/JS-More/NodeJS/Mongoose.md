# Mongoose

[Mongoose](https://www.npmjs.com/package/mongoose)：一款为异步工作环境设计的 [MongoDB](https://www.mongodb.org/) 对象建模工具。

+ <span style="font-size:22px">数据库交互：</span>

    与数据库交互有两种方法：

    - 使用数据库的**原生查询语言**（例如SQL）
    - 使用**对象数据模型**（Object Data Model，简称 ODM）或对象关系模型（Object Relational Model，简称 ORM）。 ODM / ORM 能将网站中的数据表示为 JavaScript 对象，然后将它们映射到底层数据库。一些 ORM 只适用某些特定数据库，还有一些是普遍适用的。

    >使用 ODM / ORM 通常可以降低开发和维护成本！除非你非常熟悉本地查询语言，或者项目对性能要求很高，否则强烈推荐使用 ODM。

+ <span style="font-size:22px">哪种 ORM/ODM：</span>

    Mongoose 是最受欢迎的 ODM，选用 MongoDB 数据库时，它是一个合理的选择。

Mongoose 作为 [MongoDB](https://www.mongodb.com/what-is-mongodb)（面向文档数据模型的开源 [NoSQL](https://en.wikipedia.org/wiki/NoSQL) 数据库）的前端。

这种 ODM 和数据库的结合方式在 Node 社区中非常流行，一定程度上是因为文档存储和查询系统与 JSON 十分相似，因此 JavaScript 开发人员会非常熟悉。

## 安装

安装 Mongoose 和 MongoDB

1. <span style="font-size:20px">安装 Mongoose：</span>

    ```shell
    $ npm i mongoose -S
    ```

    安装 Mongoose 会添加所有依赖项，包括 MongoDB 数据库驱动程序，但不会安装 MongoDB 本身。

2. <span style="font-size:20px">准备 MongoDB 服务器：</span>




# 一、连接

```js
// 导入 mongoose 模块
const mongoose = require('mongoose');

// 设置默认 mongoose 连接
const mongoDB = 'mongodb://127.0.0.1/my_database';
mongoose.connect(mongoDB);
// 让 mongoose 使用全局 Promise 库
mongoose.Promise = global.Promise;
// 取得默认连接
const db = mongoose.connection;

// 将连接与错误事件绑定（以获得连接错误的提示）
db.on('error', console.error.bind(console, 'MongoDB 连接错误：'));
```



# 二、定义和添加模型

> 这部分文件可以存入 "/models" 文件夹

MongoDB 数据库中，每个模型都映射至一组文档。这些文档包含 `Schema` 模型定义的字段名/模式类型。

1. <span style="font-size:20px">定义模式：</span>——`Schema`构造函数

    模型使用 `Schema` 接口进行定义。 `Schema` 可以定义每个文档（*数据库*）中存储的字段，及字段的验证要求和默认值。

    ```js
    const mongoose = require('mongoose');
    // 定义一个模式
    const Schema = mongoose.Schema;
    
    const BookSchema = new Schema({
      title: {type: String, required: true,},
    	// ...
    })
    ```
    
2. <span style="font-size:20px">创建模型：</span>——`mongoose.model()`从模式创建模型

    ```js
    const BookModel = mongoose.model('Book', BookSchema);
    ```

    这里的`Book`时创建的数据库集合的别名，第二个参数时创建模型使用到的模式
    
    > 接受第三个参数——命名 Collection（默认在第一个参数小写后加 's'

定义模型类后，可以使用它们来创建、更新或删除记录，以及通过查询来获取所有记录或特定子集

+ <span style="font-size:20px">模式类型（字段）：</span>

    下面是一个模式的示例，含有许多常见字段类型和声明方式：

    ```js
    const Schema = new Schema({
      name: String,
      binary: Buffer,
      updated: { type: Date, default: Date.now },
      age: { type: Number, min: 18, max: 65, required: true },
      mixed: Schema.Types.Mixed,
      _someId: Schema.Types.ObjectId,
      array: [],
      ofString: [String], 
      nested: { stuff: { type: String, lowercase: true, trim: true } }
    })
    ```

    + 模式类型：

        + `ObjectId`——数据库中某一模型的特定实例

            ```js
            const BookSchema = new Schema({
                author: { 
                  type: Schema.ObjectId, 
                  ref: 'Author', 
                  required: true },
            // 这里的author是对Anthor模型对象的引用
            ```

        + Mixed——任意

+ <span style="font-size:20px">验证器：</span>

    + required 验证器
    + 数值范围器 min、max —— Number 类型
    + enum（*指定当前字段允许值的集合*）、match（*指定字符串必须匹配的正则表达式*）、maxLength、minLength —— String 类型

    类型验证其和错误消息的设定：

    ```js
    const breakfastSchema = new Schema({
      eggs: {
        type: Number,
        min: [6, '鸡蛋太少'],
        max: 12
      },
      drink: {
        type: String,
        enum: ['咖啡', '茶']
      }
    });
    ```

+ <span style="font-size:20px">虚拟属性：</span>——可以获取和设置、但不会保存到 MongoDB 的文档属性

    + getter——用于格式化或组合字段
    + setter——用于将单个值分解为多个值从而便于存储

    ```js
    // 虚拟属性name：表示作者全名
    AuthorSchema
      .virtual('name')	// 设置虚拟属性
      .get(function () {
      return this.family_name + ', ' + this.first_name;
    });
    // 这里的getter从名字、姓氏字段构造一个全名虚拟属性
    
    BookInstanceSchema
        .virtual('url')
        .get(function () {
            return '/catalog/bookinstance/' + this._id;
        });	// _id是文档记录的id独一无二
    ```

    

# 三、使用模型

> 操作数据库的文件可以放入 "/controllers" 文件夹

模型即数据库中可以搜索的一类文档，模型的实例即可以存取的单个文档。

在 "/model" 文件夹中导出模型后，在其他地方`require`使用该模型

## 创建、修改文档

+ **`Model.prototype.save()`**——定义模型的实例并调用`.save()`创建记录:

    ```js
    await new User({
      name: req.query.name,
      pwd: req.query.pwd,
    })
    	.save((err, user) => {
      // ...
    })
    ```

+ **`Model.create`:** 

    ```js
    await User.create({
      name: req.query.name,
      pwd: req.query.pwd,
    })
      .then(user => {
      res.send("注册成功: " + user);
    })
    ```

    

## 查询记录

+ `.find(filter, projection)`，`.findOne()`——返回查询记录结果
+ `.findById()`（*接受字符串参数*）
+ `.populate()`——连表查询 联合其他文档
+ `.exec()`——执行查询（*放在查询链的末端*）

下例是以上查询函数的综合运用：

```js
// models/book.js:
const BookSchema = new Schema({
    title: {type: String, required: true},
  
// bookInstanceController.js:
// 回调函数形式
exports.bookinstance_list = function(req, res, next) {
    BookInstance.find({bo})
        .populate('book')
        .exec(function (err, list_bookinstances) {/*...*/}
// async await形式
exports.bookinstance_list = async (req, res, next) {
		const list_bookinstances = await BookInstance.find({bo}).populate('book').exec()
    // ...
}
```



+ `populate`：

    有了 populate 后查询结果中 ObjectId 自动转换为相应实例字段

    ```js
    const ShoppingCartSchema = new Schema({
        goodsList: {
            type: [Schema.Types.ObjectId],
            ref: "Goods",
        },
    })
    
    ShoppingCart.findById(id)
      .populate("goodsList").exec();
    ```

    > populate 的 path 参数对应的是该 Model 字段



+ `.sort`——按排序返回记录

    ```js
    // 1.<Object> 支持asc/ascending/1、desc/descending/-1
    query.sort({ field: "ascending", age: -1 });
    // 2.<String> 默认升序，-表降序
    query.sort("filed -age");
    ```

+ `.count()`——模型的记录数



+ <span style="font-size:20px">条件查询：</span>

    ```js
    // 相当于SQL的WHERE AND
    .findOne({ "name": req.query.name, "pwd": req.query.pwd })
    
    // OR 条件
    .find({
      $or: [
        {key1: val1},
        {key2, val2},
      ]
    })
    ```




## 修改记录

+ updateOne():

    ```js
    await ShoppingCart
      .updateOne(
      {tel},	// filter
      shoppingCart)	// fields
      .exec();
    ```

    > 其中，shoppingCart 对象对应着全部字段：
    >
    > ```js
    > const shoppingCart = await ShoppingCart
    > 	.findOne({tel}).populate("goodsList")
    > 	.exec();
    > // 1.调整购物车商品总数
    > shoppingCart.count += num;
    > // 2.添加该商品ID
    > shoppingCart.goodsList.push(id);
    > ```



## 移除记录

```js
Goods.deleteMany({})
  .exec((error, result) => {
  if (error) {
    res.send(error);
  } else {
    res.send("删除成功" + result.deletedCount);
  }
})
```



# API

## Model

+ **find()：**

    ```js
    // find all documents
    await MyModel.find({});
    
    // filter, [fileds]
    MyModel.find({name: "J"}, "age money")
    ```

+ **findByIdAndRemove(id, options, callback):**




# Ref

+ 使用模型：

    [Mongoose v6.1.8: API docs](https://mongoosejs.com/docs/api.html)



# DEBUG

+ <span style="font-size:20px">查询不到数据库中 documents：</span>

    + Q_Desc：MongoDB Compass 中有数据但查询结果为空
+ S_R：数据字段与定义的 Schema 字段不匹配（凡是 Mongoose 操作涉及到的字段都要在 Schema 中有定义）
  
+ <span style="font-size:20px">populate 查询出错：</span>

    + Q_Desc：加上 populate 后查询结果 undefined，去掉正常（相应字段显示 ObjectId

        ```js
        User.findOne({ tel })
          .populate("ShoppingCart")    
          .exec((err, shoppingCartList) => {
          console.log(shoppingCartList);
        ```

    + S：对应参数改为小写：`populate("shoppingCart")`

    + S_R：参数路径对应的是 `User` Schema 下对应属性。

