# Echarts

[Apache ECharts](https://echarts.apache.org/zh/index.html)——一个基于 JavaScript 的开源可视化图表库

实质仍是渲染 canvas



# 上手

### 安装

可以根据项目的实际情况选择以下任意一种方式安装。从 GitHub 获取、从 npm 获取、从 CDN 获取、在线定制。

+ CDN 获取：

    JsDelivr 中[引用](https://www.jsdelivr.com/package/npm/echarts)，下载`echarts/dist/echarts.js`到项目中。

    以`<script src="./script/echarts.min.js"></script>`的形式引用

## Demo——绘制简单图表

1. 准备一个宽高已定义的 DOM 容器。

    ```html
    <body>
      <!-- 为 ECharts 准备一个定义了宽高的 DOM -->
      <div id="main" style="width: 600px;height:400px;"></div>
    </body>
    ```

2. 通过 [echarts.init](https://echarts.apache.org//api.html#echarts.init) 方法初始化一个 echarts 实例并通过 [setOption](https://echarts.apache.org//api.html#echartsInstance.setOption) 方法生成一个简单的柱状图

    ```js
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    
    // 指定图表的配置项和数据
    var option = {
      title: {
        text: 'ECharts 入门示例'
      },
      tooltip: {},
      legend: {
        data: ['销量']
      },
      xAxis: {
        data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子']
      },
      yAxis: {},
      series: [
        {
          name: '销量',
          type: 'bar',
          data: [5, 20, 36, 10, 10, 20]
        }
      ]
    };
    
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    ```

3. 一个简单的图表就渲染出来了。

 

# 尺寸

容器的尺寸必须在渲染前确定，意味着 width 要显式地设置出来

+ 可以在 css 给该容器设置 width

+ 也可以在 js 里初始化 canvas 实例时指定：

    ```js
    const myWindEcharts = echarts.init(windEcharts, null, {
      width: 1000,
      height: 110,
    })
    ```

    

# 位置

位置可以从以下配置项设置：

## 图表位置

+ grid

    ```js
    grid: {
      left: 10,
      bottom: 90,
    }
    // 或
    grid: {
      x: 10,
      y: 90,
    }
    ```

    

## x、y 轴位置

```js
xAxis: {
  // ...
  offset: 27,	// 调整x轴位置
}
```



# xAxis

```js
xAxis: {
  type: "category",	// x轴样式
  data: arr,	// x轴数据集
  axisLine: {}	// 坐标轴线
  offset: 20,	// 调整x轴位置
  bourdaryGap: false,	// 留白
  axisTick: {}	// 坐标轴刻度线
}
```



# series

```js
series: [{
  type: "line",	// 曲线类型
  data: tempArr,	// 数据集
  lineStyle: {	// 曲线样式
    color: "#fff",
    width: '1',
  },
  color: "#fff"	// 曲线上拐点样式
 	label: {}	// 拐点标签
}]
```

+ <span style="font-size:20px">单独配置数据项：</span>

    以下实现只对第一个拐点显示标签

    ```js
    tempArr[0] = {
      value: tempArr[0],
      label: {	// 拐点标签
        show: true,
        color: "#fff"
      },
    }
    const options = {
      series: [{
        data: tempArr,
      }]
    }
    ```

    



# 参考

+ 总：

    [Handbook - Apache Echarts](https://echarts.apache.org/handbook/zh/get-started/)

