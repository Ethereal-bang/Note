# 链表

单链表中插入或删除结点时，仅需修改指针不需移动元素

+ <span style="font-size:20px">节点插入：</span>

    先操作待插节点：

    ![image-20211124201659202](https://gitee.com/ethereal-bang/images/raw/master/20211124201706.png)

    ```c
    s->next = p->next;	// 待插节点指针域指向原节点的指针域
    p->next = s;	// 原节点指针域指向待插节点
    ```

    双向链表同理。



# 栈和队列

## 栈——Stack

<img src="https://gitee.com/ethereal-bang/images/raw/master/20211219135933.jpg" alt="img" style="zoom:43%;" />

+ **后进先出**
+ **栈也是线性表：**栈是仅限在表尾进行插入或删除元素的线性表
+ **表尾称为栈顶：**相应地，表头称为栈底。

### 单调栈

+ **单调栈：**栈内顺序要么从大到小、要么从小到大
+ **使用场景：**通常一维数组，要寻找任一元素的右/左边第一个比自己大/小的元素位置时。

+ **本质：**空间换时间

+ **注意：**
    + 单调栈里存放的元素——数组下标
    + 单调栈里元素递增/递减——从栈头到栈底

## 队列

### 单调队列



# 串

## 数组填充

+ **预先**给数组**扩容**带填充后的大小，然后在**从后向前**进行操作：

    这样有两个好处：不用申请新数组；从后向前填充元素避免了从前先后填充元素那样 每次添加元素都要将添加元素之后的所有元素向后移动的问题。



## KMP

KMP 主要用在字符串匹配

+ **主要思想**：当出现字符串不匹配时，可以知道一部分之前已经匹配的文本内容，可以利用这些信息避免从头再去做匹配

+ **重点：**如何记录已经匹配的文本

    

+ <span style="font-size:20px">什么是前缀表</span>：

    **作用**：前缀表（next 数组）是用来回退的，记录了模式串与主串(*文本串* )不匹配时，模式串应该从哪里开始重新匹配

    例子：

    <img src="https://code-thinking.cdn.bcebos.com/gifs/KMP%E7%B2%BE%E8%AE%B21.gif" alt="gif" style="zoom: 67%;" />

    以上图为例，匹配到模式串第 6 个字符`f`时发现不匹配。暴力匹配的话会从头开始匹配；但使用前缀表会从上次已经匹配的内容开始匹配，找到模式串第 3 个字符`b`开始匹配

    **本质**：记录下标`i`之前（包括`i`）的字符串中有多大长度的**相同前缀后缀**

    

+ <span style="font-size:20px">最长公共前后缀</span>：

    **前缀**：<span style="color:red">不包含</span>最后一个字符的所有以第一个字符开头的连续子串

    **后缀**：<span style="color:red">不包含</span>第一个字符的所有以最后一个字符结尾的连续子串

    **最长公共前后缀**：最大相同前缀后缀的长度，例如`a`的最长相等前后缀 0、`aa`为 1；`aaa`为 2。

    前缀表要求的就是相同前后缀的长度

    

+ <span style="font-size:20px">为什么要用前缀表</span>：

    理解为什么使用前缀表可以得知失配后跳到哪里匹配

    例子：依然以![image-20211022090217300](https://gitee.com/ethereal-bang/images/raw/master/20211022090217.png)

    为例，下标 5 之前的最大公共前后缀为`aa`长度为 2，因此从下标 2 字符`b`开始匹配，因为此时文本串的后缀与模式串的前缀相同

    

+ <span style="font-size:20px">计算前缀表</span>：

    求得的最长相同前后缀的长度就是对应前缀表的元素

    如图：![image-20211022094206178](https://gitee.com/ethereal-bang/images/raw/master/20211022094206.png)

    前缀表对应位置的数字表示的就是：下标`i`之前（包括`i`）的字符串中有多大长度的相同前后缀	==为什么不直接存不包括？==

    失配的位置要看它的前一个字符的前缀表的数值，把下标移动到下标`2`继续匹配

    

+ <span style="font-size:20px">前缀表与 next 数组</span>：

    很多 KMP 算法的实现都是使用 next 数组做回退操作，next 数组与前缀表有什么关系呢

    next 数组可以就是前缀表，但很多实现都是把前缀表统一减一（右移一位，初始位置 -1）之后作 next 数组

    至于为什么这样，并不涉及 KMP 的原理而是具体实现，两者均可。

    

+ <span style="font-size:20px">使用 next 数组匹配</span>：

    以下均已前缀表减一的 next 数组演示。



+ <span style="font-size:20px">构造 next 数组</span>：

    构造 next 数组其实是计算模式串`s`前缀表的过程：

    1. 初始化：

        ```js
        let j = -1;	// 前缀表统一减一
        next[0] = j; 
        ```

        next[i] 表示 i（包括i）之前最长相等的前后缀长度`j`。

    2. 处理前后缀不相同情况：

        

    3. 处理前后缀相同情况

    ==。。。==



### 查找子串

在一个串中查找是否出现另一个串，这是KMP的看家本领



# 数组



# 树

## 二叉树

+ <span style="font-size:22px">分类：</span>

    + **满二叉树：**

        每一层结点数都达到最大值——结点总数 <span style="color:red">2^k - 1</span>(k 层数)

    + **完全二叉树：**

        满二叉树是完全二叉树的特殊形态——除了最后一层之外其他的每一层都被完全填充，并且所有的节点都向左对齐

+ <span style="font-size:20px">存储方式：</span>

    链式存储（指针）、顺序存储（数组）

    如何顺序存储：

    <img src="https://gitee.com/ethereal-bang/images/raw/master/20211106180600.png" alt="image-20211106180600214" style="zoom:40%;" />

    如何遍历：父节点`i`，则左孩子`i * 2 +1`，右孩子`i * 2 + 2`


### 二叉树的遍历

+ <span style="font-size:20px">二叉树节点表示：</span>

    ```java
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {};
        TreeNode(int val) { // 叶子结点
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    ```

+ <span style="font-size:20px">遍历方式：</span>

    主要有两种遍历方式：

    + 深度优先遍历（递归法、迭代法）：前序遍历、中序遍历、后序遍历

        这里的“前”、“中”、“后”指的是二叉树根节点的遍历顺序，例如：

        <img src="https://gitee.com/ethereal-bang/images/raw/master/20211106180259.png" alt="image-20211106180259744" style="zoom:50%;" />

        <img src="C:/Users/HP/AppData/Roaming/Typora/typora-user-images/image-20220106174406085.png" alt="image-20220106174406085" style="zoom:33%;" />

        从递归的角度看，三种算法是完全相同的，或说这三种算法的访问路径是相同的，只是访问结点的时机不同。

    + 广度优先遍历，又译作宽度优先搜索（迭代法）

        + 层次遍历

            沿着树的宽度遍历树的节点(*横向* )

+ <span style="font-size:20px">二叉树的递归遍历：</span>

    ```java
    // 前序
      if (root != null) { // 以防二叉树为空
        res.add(root.val);	// b只需调整这句话的位置
        if (root.left != null)
          res.addAll(preorderTraversal(root.left));
        if (root.right != null)
          res.addAll(preorderTraversal(root.right));
      }
    ```

+ <span style="font-size:20px">二叉树的迭代遍历：</span>

  用 Stack 实现。
  
  前序遍历：（中-左-右，入栈顺序 中-右-左）
  
  ```java
  TreeNode node = root;
  stack.add(node);
  while (node != null) {  // 每一次循环：将中节点弹出并存入，左右节点入栈
    res.add(stack.pop().val);   // 中 出栈
    // 将左右孩子压入栈（先右后栈从而使出栈顺序中左右
    if (node.right != null)
      stack.add(node.right);
    if (node.left != null)
      stack.add(node.left);
    if (stack.isEmpty())
      break;
    node = stack.peek();
  }
  ```
  
  中序遍历：（左-中-右，入栈顺序 
  
  ```java
  TreeNode curNode = root;
  while (curNode != null || !stack.isEmpty()) {
    if (curNode != null) {  // 一直遍历到左叶子节点，持续入栈
      stack.add(curNode);
      curNode = curNode.left;
    } else {    // 遍历到叶子节点
      curNode = stack.pop();
      res.add(curNode.val);   // 中出栈后遍历右孩子入栈
      curNode = curNode.right;
    }
  }
  ```
  
  ==后序遍历?==：（颠倒前序结果集）
  
+ <span style="font-size:20px">二叉树的层序遍历：</span>

    > root = [3,9,20,null,null,15,7]
    > 输出：[ [3], [9,20], [15,7] ]

    

## 堆

堆是用数组实现（*所以没有使用父/子指针*）的二叉树，根据“堆属性”来排序，“堆属性”决定树中节点位置。

+ **常用方法**：构建优先队列、支持堆排序、快速找出一个集合中最值

+ <span style="font-size:20px">堆与数组：</span>

    堆是一种具以下特点的的**完全二叉树**（*有顺序的完全二叉树*）：

    每个节点的值大于或等于左右孩子节点值——最大堆

    ![image-20211208183051861](https://gitee.com/ethereal-bang/images/raw/master/20211208183059.png)

    对节点按层继续编号，将堆逻辑结构映射到数组中就是如下：

    ![image-20211208183144244](https://gitee.com/ethereal-bang/images/raw/master/20211208183144.png)

    得出，大顶堆：**arr[i] >= arr[2i+1] && arr[i] >= arr[2i+2]**

    堆属性非常有用，堆常被作为优先队列使用——可以快速访问到最重要元素

    要注意，堆的根节点存放的是<span style="color:red">最值</span>，但其他节点排序顺序未知



# 排序算法

> **排序算法**——交换——**冒泡排序**
>
> ​					|			|—**快速排序**
>
> ​                    |—插入——**直接插入排序**
>
> ​					|			|—**希尔排序**
>
> ​					|—选择——**简单选择排序**
>
> ​					|			|—**堆排序**
>
> ​					|——**归并排序**
>
> ​					|——**基数排序**

（以从小到大为例）

+ <span style="font-size:20px">冒泡排序——重复访问数列，两两比较交换顺序：</span>

    **主要代码：**

    ```java
    // 外层——比较len-1次
    for (int i = 0; i <= len; i++) {
      // 内层——每一遍循环需两两比较的次数
      for (int j = 0; j <= len - i；j++) {
    		// ...
      }
    }
    ```

    + 每一次外层循环得到的都是最大值，将其放到末尾。

+ <span style="font-size:20px">直接插入排序——所有元素依次与前面有序数列比较、插入：</span>

    ```java
    // 从下标1开始为待插入数列:
    for (int i = 1; i < arr.length; i++) {
    	// 从后往前遍历找到应插位置j——j-1对应小于该数的位置:
      int j = i;
      while (j > 0 && arr[j - 1] > temp) {
    		// ...待插位置之后元素后移一位
      }
      // 插入：
      arr[j] = temp;
    ```

    + `temp`记录待插数（不然会被前面后移来的元素覆盖）
    + `while`循环找待插位置时要首先满足`j>0`

+ <span style="font-size:20px">快速排序——分治基于基准左右排序：</span>

    ```java
    public static void quickSort(int[] arr, int left, int right) {
      // ...递归终止条件
      
      int mid = partition(arr, left, right);
      // 递归对基准左右两边重复以上操作
      quickSort(arr, left, mid - 1);
      quickSort(arr, mid + 1, right);
    }
    
    // 分治——使基准数左边全是小于的数、右边全是大于的数
    private static int partition(int[] arr, int left, int right) { // 分治法
      int temp = arr[left];   // 基准数
      while (left < right) {
        // 由后向前找比基准数小的数填入坑arr[left]:
        while (temp <= arr[right] && left < right) {
          right--;
        }
        // 基准数大于arr[right]，填坑入left
        if (left < right) { // 不加这个条件left可能导致left>right使最后赋值temp的left位置错误
          arr[left] = arr[right];
          left++;
        }
    
        // ...由前向后找比基准数大的数填入坑a[right]
        
      }
      // left==right说明已经分治完毕
      arr[left] = temp;
      return left;
    }
    ```
  
  + 相当于每次基准按大小将整个数列分为左右两个数列——左数列都比基准小*但不一定依次*
  
  + 19 行判断语句的必要性：
  
      例如数组`{5, 4, 3, 2, 1}`，没有条件判断：<img src="https://gitee.com/ethereal-bang/images/raw/master/20211208130603.png" alt="image-20211208130556482" style="zoom:53%;" />
  
      有条件判断：<img src="https://gitee.com/ethereal-bang/images/raw/master/20211208130618.png" alt="image-20211208130618651" style="zoom:50%;" />
  
+ <span style="font-size:20px">归并排序——重复将两个有序表合成新有序表：</span>

    <img src="https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2018/9/10/165c2d849cf3a4b6~tplv-t2oaga2asx-watermark.awebp" style="zoom:50%;" >

    ```java
    public static int[] mergeSort(int[] arr) {
      // ...递归终止:
      
      // ...切割成两子序列:
      int[] left = Arrays.copyOfRange(arr, 0, middle);
      
      // 递归切割合并：
      return mergeTwo(mergeSort(left), mergeSort(right));
    }
    private static int[] mergeTwo(int[] left, int[] right) {
      // 申请空间
      int[] res = new int[left.length + right.length];
      
      // ...依次选取两序列中较小值不断放入新数组
    }
    ```

+ <span style="font-size:20px">堆排序——利用堆概念的选择排序：</span>

    1. 建立一个最大堆——最大值在堆的根节点
    2. 数组根节点与末尾替换——把最大值放到后面，这样就完成了一趟排序
    3. 重复以上步骤：<img src="https://gitee.com/ethereal-bang/images/raw/master/20211208233135.png" alt="image-20211208233135322" style="zoom:53%;" />

    ```java
    public static void heapSort(int[] arr) {
      for (int cnt = 1; cnt < arr.length; cnt++) {
        // 1.建初堆——构建最大堆:
        buildMaxHeap(arr, arr.length - cnt + 1);
        // 2.将最大元素交换到末尾:
        swap(arr, 0, arr.length - 1 - cnt + 1);
        // 3.每构建一次就相当于排好一个最大值
        System.out.println("heapSort, " + Arrays.toString(arr));
      }
    }
    private static void buildMaxHeap(int[] arr, int size) {
      // ...从最后一个分支结点——n/2 开始按大小调整父子结点 构建最大堆
    }
    private static void adjustToMaxHeap(int[] arr, int curRootNode, int size) {
      // ...最大元素交换到此树杈的根位置：
    ```




# 哈希表

## 理论基础

+ <span style="font-size:22px">概念：</span>

    哈希表是根据关键码的值而直接进行访问的数据结构

    其实数组就是一张哈希表。哈希表中关键码就是数组的索引下标，然后通过下标直接访问数组中的元素

下面将会以储存学生姓名为例。只需初始化把学生名字都存在哈希表，查询时直接通过索引（复杂度O(n)）。

+ <span style="font-size:22px">哈希函数：</span>

    + **hashCode**——通过特定编码方式，将其他数据格式转化为不同数值

    将学生姓名映射到哈希表就涉及到 hash function。

    如下图，通过 hashCode 把名字转化为数值，hash function 把学生的姓名直接映射为哈希表上的索引。

    ![img](https://img-blog.csdnimg.cn/2021010423484818.png)

    + hashCode() 数值大于 tableSize——为保证映射出的索引数值都落于哈希表，再次对数值做<span style="color:red">取模</span>的操作
    + 学生数量 dataSize大于 tableSize——就算 hash function 计算再均匀，也避免不了几位同学同时映射到统一索引，所以需要**哈希碰撞**

+ <span style="font-size:22px">哈希碰撞：</span>

    <img src="https://img-blog.csdnimg.cn/2021010423494884.png" alt="img" style="zoom:43%;" />

    一般有两种解决方法：拉链法、线性探测法

    + <span style="font-size:20px">拉链法：</span>

        <img src="https://img-blog.csdnimg.cn/20210104235015226.png" alt="img" style="zoom:40%;" />

        发生冲突元素存储在链表

        拉链法就是要选择适当的哈希表的大小，这样既不会因为数组空值而浪费大量内存，也不会因为链表太长而在查找上浪费太多时间

    + <span style="font-size:20px">线性探测法：</span>

        + **前提：**tableSize > dataSize —— 依靠哈希表空位解决碰撞问题

+ <span style="font-size:20px">常见哈希结构：</span>

    数组、Set——集合、Map——映射




# 回溯算法

## 理论基础

回溯法是一种搜索方式，只要有递归就会有回溯

回溯的本质是<span style="color:red">穷举</span>——穷举所有可能选出想要的答案；其实是暴力查找并不是什么高效算法

+ **主要解决：**
    + 组合问题——N 个数找符条件的 k 个数的集合
    + 切割——一个字符串有几种符条件的切割方式
    + 子集——有多少符合条件的子集
    + 排列——N 个数按一定规则全排列有几种方式
    + 棋盘——N 皇后、解数独...

+ **理解回溯：**

    所有回溯法解决的问题都可抽象为树形结构

    因为回溯解决的都是<span style="color:red">在集合中递归查找子集</span>，集合的大小构成树的宽度，递归的深度构成树的深度

+ <span style="font-size:22px">回溯法模板：</span>

    1. 回溯函数模板——**返回值、参数：**

        习惯函数名取为`backtracking`；返回值一般 void；参数不容易一次性确定，一般先写逻辑、需要什么参数填什么参数

        `void backtraking(<参数>)`

    2. 回溯函数**终止条件：**

        树中可以看出，一般来说搜到<span style="color:red">叶子叶节点</span>就结束本层递归==？==

        ```javascript
        if (<终止条件>) {
        	// 存放结果
        	return;
        }
        ```

    3. 回溯搜索的**遍历过程**：

        <img src="https://img-blog.csdnimg.cn/20210130173631174.png" height="250px">

        ```javascript
        for (/* 选择：本层集合中元素——树中节点孩子的数量就是集合的大小==？== */) {
          // 处理节点
          backtraking(/* 路径， 选择列表 */);
          // 回溯，撤销处理结果==？==
        }
        ```

        for 循环就是遍历集合区间，可理解成一个节点有多少个孩子 这个 for 循环就执行多少次。

        由图可以看出 for 循环可以理解是<span style="color:red">横向遍历</span>，递归 (backtracing) 就是<span style="color:red">纵向遍历</span>，这样就把这棵树全部遍历。

    4. 综上，回溯算法模板框架：

        ```java
        void backtracking(<参数>) {
          if (终止条件) {
            // 存放结果;
            return;
          }
          // 1.横向遍历——取第一个数
          for (/* 选择：本层集合中元素——树中节点孩子的数量就是集合的大小 */) {
          // 处理节点
          // 2.递归纵向遍历——取后一个数
          backtraking(/* 路径， 选择列表 */);
          // 3.回溯——撤销上次处理结果
        	}
        }
        ```
        
        > **回溯——撤销上次处理结果的作用：**
        >
        > ![image-20220210101606145](https://gitee.com/ethereal-bang/images/raw/master/20220210101613.png)

## 子集问题

[力扣](https://leetcode-cn.com/problems/subsets/)——给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集。

求子集问题不同于组合问题、分割问题，组合、分割相当于收集树的叶子节点，子集问题是找树的所有节点

+ **分析：**

    <img src="https://img-blog.csdnimg.cn/202011232041348.png">

+ <span style="font-size:22px">解题步骤：</span>

    1. 递归函数参数：

        全局变量数组`path`为子集收集元素==？==，二维数组`result`存放子集组合



# 贪心算法

选择每一阶段的局部最优，从而达到全局最优。

+ **贪心算法步骤：**
    1. 问题分解为若干子问题
    2. 求解每一子问题最优解
    3. 局部最优解堆叠成全局最优解



# 动态规划

动态规划——背包问题——背包

​                  |                     |—完全背包

​                  |                     |—多重背包

​				  |—打家劫舍

​				  |—股票问题

​				  |—子序列问题——子序列（不连续）

​											  |—子序列（连续）

​										      |—编辑距离

​											  |—回文

+ <span style="font-size:20px">概念：</span>

    动态规划即具有很多重叠子问题的问题

    动态规划中每一个状态一定是由上一个状态推导出来的

    **动态规划**是由前一个状态推导出来的，而**贪心算法**是局部直接选最优的

+ <span style="font-size:20px">步骤：</span>

    1. **确定 dp 数组**(*dynamic programming table* )及下标含义
    2. 确定**递推公式**（每一个状态由上一个状态推导）
    3. dp **数组初始化**
    4. 确定**遍历顺序**
    
+ Eg——斐波拉契：

    ```java
    // 1.确定dp数组
    int[] db = new int[n + 1];
    // 2.确定递推公式——db[i]= db[i-1]+db[i-2]
    // 3.db初始化
    db[0] = 0;
    if (n > 0)
      db[1] = 1;
    // 4.确定遍历顺序
    for (int i = 2; i <= n; i++) {
      db[i] = db[i - 1] + db[i - 2];
    }
    
    return db[n];
    ```




## 背包问题

![img](https://img-blog.csdnimg.cn/20210117171307407.png)

+ <span style="font-size:22px">01 背包:</span>

    每一件物品只有两个状态——取或不取



# REF

+ 总：

    [代码随想录](https://programmercarl.com/)
    
+ 栈：

    [《数据结构（C语言版）》——严蔚敏 吴伟民]()

+ 树：

    [满二叉树_百度百科](https://baike.baidu.com/item/%E6%BB%A1%E4%BA%8C%E5%8F%89%E6%A0%91/7773283)

    [完全二叉树_百度百科](https://baike.baidu.com/item/%E5%AE%8C%E5%85%A8%E4%BA%8C%E5%8F%89%E6%A0%91/7773232)

    [十二种排序算法包你满意（带GIF图解）- 排序数组 - 力扣（LeeCode）](https://leetcode-cn.com/problems/sort-an-array/solution/shi-er-chong-pai-xu-suan-fa-bao-ni-man-yi-dai-gift/)

    [完全二叉树之堆 - 简书](https://www.jianshu.com/p/5e4d17271d52)

+ 排序算法：

    [面试必备：八种排序算法原理及Java实现 - 掘金](https://juejin.cn/post/6844903687932887053)

    [面试 12：玩转 Java 快速排序 - 掘金](https://juejin.cn/post/6844903642042990599)

    [算法 | 菜鸟分类 | 菜鸟教程](https://www.runoob.com/w3cnote_genre/algorithm)
    
    [堆排序就这么简单 - SegmentFault思否](https://segmentfault.com/a/1190000013960582)

