# Web Scraping

网页抓取

+ **数据爬取的思路**可简单概括如下：

    1. 通过一个或多个入口地址，获取初始数据。例如一个文章列表页，或者具有某种规则的页面，例如带有分页的列表页；

    2. 根据入口页面的某些信息，例如链接指向，进入下一级页面，获取必要信息

    3. 根据上一级的链接继续进入下一层，获取必要信息（此步骤可以无限循环下去）



# Web Scraper

Web Scraper 是适用于普通用户的爬虫工具，可以方便的通过鼠标和简单配置获取所想要数据。

+ <span style="font-size:22px">选项：</span>

    + **Sitemap**——入口地址
    + **Create new sitemap**——新建的 sitemap 在 sitemaps 找到
    + **Sitemaps**——进入某个 sitemap 进行具体操作

+ <span style="font-size:22px">实践:</span>

    以抓取豆瓣读书 Top250 为例。

    1. **Create new sitemap：**

        ![image-20220404204500314](https://gitee.com/ethereal-bang/images/raw/master/20220404204507.png)

    2. **Add selector：**

        ![image-20220404205821392](https://gitee.com/ethereal-bang/images/raw/master/20220404205821.png)

        1. Id——只是用来标识的名字

        2. Type——获取节点类型

            > 自动提取名字和链接
            >
            > ![image-20220404210416124](https://gitee.com/ethereal-bang/images/raw/master/20220404210416.png)

        3. Selector——页面上选中多个所需节点（*变红* ），自动推断出当前页面所需。选择 Done selecting

            > 图中 P、C、S 分别指 Parent、Child、Hovered 节点

            > Multiple 选项一般只在父节点勾选

        4. Save selector




# Puppeteer

一个 Node 库，执行浏览器自动化操作

+ **Demo：**

    ```js
    const puppeteer = require("puppeteer");
    
    (async () => {
        // Create browser instance
        const browser = await puppeteer.launch();
        // Create new page in browser
        const page = await browser.newPage();
        // Open page by given url
        await page.goto("https://book.douban.com/top250");  // page to 
    
        // Do something
        
        // Close browser instance
        await browser.close();
    })()
    	.then(res => {/* ... */})
    ```
    
+ **爬虫：**——对应上面 do something

    ```js
    await page.waitForSelector("tbody .item");   // wait book info node appear
    const bookData = await page.evaluate(() => { // do something by own js code
      const data = [];
      const img_element = document.querySelectorAll("tbody>.item> :nth-child(1)>a>img"),
            name_element = document.querySelectorAll("tbody>.item> td:nth-of-type(2)>.pl2>a");
      for (let i = 0; i < img_element.length; i++) {
        const obj = {
          img: img_element[i].currentSrc,
          name: name_element[i].innerText,
        };
        data.push(obj);
      }
      return data;
    })
    ```



# REF

+ Web Scraper:

    [网页数据抓取工具，webscraper 最简单的数据抓取教程，人人都用得上 ](https://www.cnblogs.com/fengzheng/p/8440806.html)

+ Puppeteer:

    [puppeteer实现浏览器自动化和爬虫](https://juejin.cn/post/6908624048083697672)



# DEBUG

+ [Could not find expected browser chrome locally](https://stackoverflow.com/questions/68051648/could-not-find-expected-browser-chrome-locally)
    + S_Desc：`yarn add puppeteer -D` 改为 `-S`
