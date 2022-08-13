# 算法设计与分析基础

> **题目组成：**
>
> 单项选择 30分（15小题，每小题2分）
>
> 判断题 20 分（10小题，每小题2分）
>
> 简答题（10分）
>
> 综合题（40分）



# 绪论

> **范围：**
>
> **算法概念**、算法问题求解基础；欧几里得法求最大公约数
>
> 重要的问题类型、基本数据结构；
>
> 算法效率分析框架、符号表示、基本效率类型、递归和非递归算法的分析；
>
> 用算法求 Fibonacci 数列

+ **<span style="color:red">算法概念</span>：**一系列解决问题的明确指令
+ **<span style="color:red">欧几里得</span>：**gcd(m, n) = gcd(n, m mod n)

<hr/>

+ **<span style="color:red">重要问题类型</span>：**排序，查找，字符串处理，图，组合问题，几何问题，数值问题

+ **<span style="color:red">基本数据结构:</span>**——线性，图，树，集合与字典



# 算法效率与分析基础

+ **<span style="color:red">算法效率分析框架</span>：**最优、最差、平均效率

+ **<span style="color:blue">四种渐进符号：</span>**
  
    + O——给出上界。存在 c，n~0~，对于 n ≥ n~0~，有 f(n) ≤ cg(n)
    + Ω——给出下界。 ...，f(n) ≥ cg(n)
    + θ——上界和下界。c~1~g(n) ≤ f(n) ≤ c~2~g(n)
    
+ **<span style="color:blue">基本效率类型及其比较</span>**

    logn < n < nlogn < n^3^ < 2^n^ < n!

    lim~n->∞~t(n) / g(n)。洛必达法则 => 导数之比的极限

    > n^x^ 求导：n^x^ ln(n)
    >
    > log~a~n: 1/n ln(a)

+ **==简单的递归和非算法分析?==**

+ **<span style="color:red">Fibonacci</span>: **F(n) = F(n - 1) + F(n - 2)



# 蛮力法

## 排序

<span style="font-size:20px;color:red">选择排序:</span>

> 每一遍选择最小值交换到首位

<span style="color:blue">O(n^2^)</span>

<span style="font-size:20px;color:red">冒泡排序:</span>

> 比较相邻

O(n^2^); Best: O(n)



## 查找，字符匹配

<span style="color:red">顺序查找</span>

<span style="color:red">蛮力字符匹配</span>



## 最近对，凸包

<span style="font-size:20px;color:red">最近对问题</span>

> n 点集合中找出最近两点

<span style="font-size:20px;color:red">凸包</span>

一个点集合的凸包是包含这些点的最小凸多边形

<span style="color:blue">O(n^3^)</span>



## 穷举查找



## DFS BFS



# 减治法

<span style="color:blue">三种变种：</span>减去一个常量；减去一个常量因子；减去的规模是可变的

<span style="font-size:20px;color:red">插入排序</span>

<span style="font-size:20px;color:red">拓扑排序</span>

> 对有向无环图找到一个可执行的线性顺序

+ 基于 DFS
+ <span style="color:blue">**源删除法:**</span>每次迭代时删除入度为 0 的节点，被删次序就是一个解

==<span style="font-size:20px;color:red">生成组合对象的算法</span>?==

<span style="font-size:20px;color:red">减常因子算法</span>

<span style="color:blue">折半查找比较次数</span>

O(logn)



# 分治法

## 排序

+ 通用分治递推式：T(n) = aT(n / b) + f(n)

    ==主定理?==：
    
    

<span style="font-size:20px;color:red">合并排序</span>

> 按照元素的位置划分

O(n) = nlogn

<span style="color:blue">写排序过程</span>



<span style="font-size:20px;color:red">快速排序</span>

> 按照元素的值划分

<span style="color:blue">时间效率:</span> Best / Avg: O(nlog~2~n)；Worst: O(n^2^)

<span style="color:blue">不稳定</span>

<span style="color:blue">写排序过程</span>

1. 以第一个为基准
2. i++ 直到找到大于，j-- 直到找到小于等于
3. 若 i < j —— 交换；若 j < i ——交换基准与 j，得到数组一个划分；i == j —— 找到基准 p，得到一个划分



## 乘法

<span style="font-size:20px;color:red">大整数乘法</span>

<span style="color:blue">三次</span>乘

> 减少位乘次数

<span style="font-size:20px;color:red">Strassen 矩阵乘法</span>

<span style="color:blue">七次</span>乘算两个 2 阶方针的积



# 变治法

<span style="font-size:20px;color:red">预排序</span>

> 基于预排序——解决最坏情况的查找、排序算法

<span style="font-size:20px;color:red">霍纳法则: </span>

> 计算多项式

不断把 x 作为公因子从多项式提出来



# 动态规划

<span style="color:blue">动态规划算法的基本要素：最优子结构和重叠子问题</span>

<span style="font-size:20px;color:red">Warshall 算法</span>

> 计算有向图传递闭包——对 DFS 或 BFS 生成传递闭包的算法优化

1. R^(0)^初始矩阵：1 表示 i, j 节点有路径（k = 0)

2. R^(1)^ ——包含 编号不大于 1 的中间顶点 的路径

    <span style="color:orange">r~ij~ 置为 1 <== 在 R(k - 1) 中 r(i, k), r(k, j) 都是 1</span>

3. R^(k)^ ，k ≤ 节点数



<span style="font-size:20px;color:blue">Floyd 算法</span>

> 计算两点间最短路径

思想：更新最短为两点直接路径或基于已知路径

1. D^(0)^: 权重矩阵
2. D(k): <span style="color:orange">d~ij~ ^k^ = min{d~ij~ ^(k-1)^, d~ik~ ^(k-1)^ + d~kj~ ^(k-1)^}</span>



<span style="font-size:20px;color:blue">最优二叉查找树:</span>

> **二叉查找树**——任意节点，左子树上所有节点值均小于根节点，右子树上所有节点值均大于根节点
>
> **最优**——使成功查找的平均比较次数最小

<span style="color:blue">求解方法：</span>

+ 填写主表：
    1. i（竖）为层数，j 为节点
    2. 对角线为 0，<span style="color:orange">C(i, i) = p~i~ </span>
    3. （向对角线右上方）递推公式填写 <span style="color:orange">C(i, j) = min~i≤k≤j≤~{C(i, k - 1) + C(k + 1, j)} + Σ^j^ ~s=i~p~s~ </span>

+ 填写根表：

    R(i, j) = 递推公式达到最小值时 k 的取值
    
+ 由根表构造出最优树： 

    R(i,j)——i 和 j 的根节点



# 贪婪

<span style="color:blue">基本要素:</span>贪心选择

<span style="font-size:20px;color:red">Prim 算法</span>——构造最小生成树

基于点选最小权边

>  选取顶点 V1，余下顶点 V3(-,∞)（V1 和 V3 不是直接相连）

<span style="font-size:20px;color:red">Kruskal 算法</span>——构造最小生成树

对边排序选边

> 树中边（有序列表中选的最小的）；边的有序列表

<span style="font-size:20px;color:red">Dijkstra算法</span>——单起点最短路径

> 选取顶点 V1，余下顶点 V3(-,∞)（V1 和 V3 不是直接相连）

每条路径长度加上相邻节点距离和，比较选出最短路径

<span style="font-size:20px;color:red">哈夫曼树</span>——较短的代码字赋给更常用的字段

构造步骤：不断组合两个概率较低子树

平均位长：Σ(概率×代码子长度)（左子树 0，右 1）

<span style="color:blue">哈夫曼编码是自由前缀码</span>



# 算法能力的极限

+ **P问题**：<span style="color:blue">可以用确定性的算法在多项式的时间内求解</span>

+ **NP问题**：能在多项式时间内验证得出一个正确解的问题

    > P 类是 NP 类问题子集

+ **NP 完全问题**：属于 NP 问题，且属于NP-hard问题。

+ **NP 困难**



# REF

[《算法设计与分析基础》 Anany Levitin]()