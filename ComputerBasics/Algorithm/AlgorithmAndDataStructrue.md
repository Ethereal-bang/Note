# 字符串

## To 回文

双指针

[剑指 Offer II 018. 有效的回文](https://leetcode.cn/problems/XltzEq/)

> **Tips：——判断是否属于数字或字母**
>
> Character.isLetterOrDigit(char ch)
>
> /[a-zA-Z0-9]/.test()

[剑指 Offer II 019. 最多删除一个字符得到回文](https://leetcode.cn/problems/RQku0D/)——尝试删除一个字符判断



## To 下一个字典序排列

**字典序：**

> Eg: [1,2,3] 的下一排列为 [1,3,2]。特殊地，[3,2,1] 的下一排列 为 [1,2,3]

如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列

**思路：**

找到 ( 较小数, 较大数 ) 交换使序列变大，且要使变大幅度最小

1. 从后往前<span style="color:orange">找较小数</span>：满足 arr[left] < arr[left + 1]

2. 从 end -> left 找最靠右的<span style="color:orange">较大数</span>：满足 arr[right] > arr[left]

    > 因为此时已有 arr[left] < arr[left + 1]，所以至少有一个 right = left + 1 满足，只是还要找尽可能靠右的使变大幅度尽可能小

3. <span style="color:orange">交换</span> (arr[left], arr[right])

4. 对 (left, end] 逆序翻转，使其变为<span style="color:orange">升序</span>

    > 易得此时 (left, end] 为降序列，因此采用双指针翻转

```java
int i = nums.length - 2;
while (i >= 0 && nums[i] >= nums[i + 1]) {	// 1.找left
  i--;
}
if (i >= 0) {	// 小于0即说明此时已是最大序列,没找到满足的left
  int j = nums.length - 1;	// 2.找right
  while (j >= 0 && nums[i] >= nums[j]) {
    j--;
  }
  swap(nums, i, j);	// 3.交换
}
reverse(nums, i + 1);	// 4.变为升序
```



[31. 下一个排列](https://leetcode.cn/problems/next-permutation/)

[556. 下一个更大元素 III](https://leetcode.cn/problems/next-greater-element-iii/)



# 链表

**虚拟头节点：**简化创建或删除链表头结点时的特殊情况

<span style="font-size:20px">指针指向:</span>

[剑指 Offer II 026. 重排链表](https://leetcode.cn/problems/LGjMqU/)——交换链表节点

[剑指 Offer II 028. 展平多级双向链表](https://leetcode.cn/problems/Qv1Da2/)——prev, next, child

<span style="font-size:20px">反转链表:</span>

迭代: 

```js
let cur = head, pre = null;
while (cur) {
  const next = cur.next;
	cur.next = pre;
  pre = cur; cur = next;
}
```

>  保存 next 节点快照后更改 cur.next 指向 pre

递归——反转以 `head`  为起点的链表

> 比起迭代空间复杂度更高，重在递归思想

图解：

<img src="https://labuladong.github.io/algo/images/%e5%8f%8d%e8%bd%ac%e9%93%be%e8%a1%a8/2.jpg" alt="img" style="zoom:25%;" />

<img src="https://labuladong.github.io/algo/images/%e5%8f%8d%e8%bd%ac%e9%93%be%e8%a1%a8/4.jpg" alt="img" style="zoom:25%;" />

<img src="https://labuladong.github.io/algo/images/%e5%8f%8d%e8%bd%ac%e9%93%be%e8%a1%a8/5.jpg" alt="img" style="zoom:25%;" />

[206. 反转链表](https://leetcode.cn/problems/reverse-linked-list/)

[92. 反转链表 II](https://leetcode.cn/problems/reverse-linked-list-ii/)——反转部分链表

[234. 回文链表](https://leetcode.cn/problems/palindrome-linked-list/)——快慢指针 + 反转部分

[25. K 个一组翻转链表](https://leetcode.cn/problems/reverse-nodes-in-k-group/)

<span style="font-size:22px">找到某位置:</span>

**倒数第 k 个节点:**

思路——<span style="color:orange">快慢指针相距 k 步：</span>

<img src="https://labuladong.github.io/algo/images/%e9%93%be%e8%a1%a8%e6%8a%80%e5%b7%a7/2.jpeg" alt="img" style="zoom: 25%;" />

<img src="https://labuladong.github.io/algo/images/%e9%93%be%e8%a1%a8%e6%8a%80%e5%b7%a7/2.jpeg" alt="img" style="zoom:25%;" />

[19. 删除链表的倒数第 N 个结点](https://leetcode.cn/problems/remove-nth-node-from-end-of-list/)——找倒数 N+1 节点

**单链表的中间：**

+ 思路——`slow` 每走一步 `fast` 走两步

[876. 链表的中间结点](https://leetcode.cn/problems/middle-of-the-linked-list/)

**链表的环：**<span style="color:blue">Floyd 判圈法</span>

+ 是否有环——`slow` 每走一步 `fast` 走两步，相遇即有环

    K 是环的整数倍

    ![img](https://labuladong.github.io/algo/images/%e5%8f%8c%e6%8c%87%e9%92%88/3.jpeg)

+ 找到环的起点——相遇后让 `slow` 回到 head，两指针同速前进

    ![img](https://labuladong.github.io/algo/images/%e5%8f%8c%e6%8c%87%e9%92%88/2.jpeg)

    > 设相遇时 `slow` 走了 k 步，相遇点与环起点距离 m

[202. 快乐数](https://leetcode.cn/problems/happy-number/description/)——将检测死循环问题转换为检测环问题

[剑指 Offer II 022. 链表中环的入口节点](https://leetcode.cn/problems/c32eOV/)

[287. 寻找重复数](https://leetcode.cn/problems/find-the-duplicate-number/description/)——需转换成成环问题

**链表相交：**

+ 思路——让双指针同时指向相交节点

    A 链表遍历完遍历 B，同理 B 链表遍历完遍历 A。让其同时遍历到相同节点 `c1`

    <img src="https://labuladong.github.io/algo/images/%e9%93%be%e8%a1%a8%e6%8a%80%e5%b7%a7/6.jpeg" alt="img" style="zoom:25%;" />

[160. 相交链表](https://leetcode.cn/problems/intersection-of-two-linked-lists/)

<span style="font-size:20px">多链表:</span>

[21. 合并两个有序链表](https://leetcode.cn/problems/merge-two-sorted-lists/)——合二为一

[86. 分隔链表](https://leetcode.cn/problems/partition-list/)——一分为二

<span style="font-size:20px">双向链表和循环链表:</span>

循环链表的每一个节点都可做 HEAD

[剑指 Offer II 029. 排序的循环链表](https://leetcode.cn/problems/4ueAj6/)——有序循环列表的插入操作



# 数组



## 二维矩阵

### To 二维数组历

<span style="color:blue">**花式遍历:**</span>

[48. 旋转图像](https://leetcode.cn/problems/rotate-image/)——原地顺时针旋转二维矩阵：依次沿做对角线和 y 轴翻转

[54. 螺旋矩阵](https://leetcode.cn/problems/spiral-matrix/) / [59. 螺旋矩阵 II](https://leetcode.cn/problems/spiral-matrix-ii/)——模拟形状 / 方向

<span style="color:blue">**To 行列有序矩阵查找:**</span>

<span style="color:blue">Z 字形查找:</span> 

[240.搜索二维矩阵 II](https://leetcode.cn/problems/search-a-2d-matrix-ii)

```java
int x = 0, y = n - 1;	// 右上角开始 根据大小排除行列 (/左下角
while (x < m && y > -1) {
  if (matrix[x][y] == target) return true;
  if (matrix[x][y] < target) {
    ++x; // 作为本行最大值 则这行都小于t
  } else {
    --y; // 作为本列最小值 则这列都大于t
  }
}
return false;	// 超出边界 说明不存在
```

<span style="color:blue">二分查找：</span>

[74. 搜索二维矩阵](https://leetcode.cn/problems/search-a-2d-matrix)——每行升序；每行第一个整数大于前一行的最后一个整数。第一次二分确定行，第二次二分确定列。（也可用 Z 字形查找 但时间复杂度更高）



# 栈和队列

## 栈

**表尾称为栈顶：**相应地，表头称为栈底。

[剑指 Offer II 036. 后缀表达式](https://leetcode.cn/problems/8Zf90G/)——数字压入栈，符号取出数字计算，运算结果压入栈

[剑指 Offer II 037. 小行星碰撞](https://leetcode.cn/problems/XagZNi/)——与栈顶元素符号作判断



## 队列

先进先出性质，适用找出第一个满足某个条件的元素

**延迟删除**技巧：

[387. 字符串中的第一个唯一字符](https://leetcode.cn/problems/first-unique-character-in-a-string)



# 串

## 数组填充 ==?==

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



# 树、图

## 二叉树

<span style="font-size:22px">分类：</span>

**满二叉树：**

每一层结点数都达到最大值——结点总数 <span style="color:red">2^k - 1</span>(k 层数)

**完全二叉树：**

满二叉树是完全二叉树的特殊形态——除了最后一层之外其他的每一层都被完全填充，并且所有的节点都向左对齐

<span style="font-size:20px">存储方式：</span>

链式存储（指针）、顺序存储（数组）

如何遍历：父节点`i`，则左孩子`i * 2 +1`，右孩子`i * 2 + 2`



### 二叉树的遍历

<span style="font-size:20px">前后序:</span>

**前中后序位置**——处理每一节点的时间点

```java
void traverse(TreeNode root) {
 if (root == null) return;
 // 前序位置
 traverse(root.left);
 // 中序位置
 traverse(root.right);
 // 后序位置
}
```

+ 前序位置代码——刚刚进入一个二叉树节点的时执行
+ 中序位置代码——一个二叉树节点左子树都遍历完，即将开始遍历右子树的时执行
+ 后序位置代码——将要离开一个二叉树节点的时执行

**前中后序位置使用场景：**

+ 中序——BST 二叉搜索树遍历有序数组

+ 前序

+ 后序——只有后序才能通过返回值获取子树的信息

    [剑指 Offer II 047. 二叉树剪枝](https://leetcode.cn/problems/pOCWxh/)——判断子树全为 0
    
    > 题目和子树有关，那大概率要给函数设置合理的定义和返回值，在后序位置写代码

<span style="font-size:20px">DFS</span>

<img src="https://labuladong.github.io/algo/images/%e4%ba%8c%e5%8f%89%e6%a0%91%e7%b3%bb%e5%88%972/1.jpeg" alt="img" style="zoom:35%;" />

从递归的角度看，这三种算法的访问路径是相同的，只是访问结点的时机不同。

<span style="font-size:20px">BFS</span>（迭代法）



<span style="font-size:20px">二叉树的迭代：</span>(Stack 实现) ==不太熟练==

前序遍历：（中-左-右，入栈顺序 中-右-左）

```java
TreeNode node = root;
stack.add(node);
while (node != null) {  
  // ...每一次循环：将中节点弹出并存入，左右节点入栈
}
```

中序遍历：（左-中-右，入栈顺序  中-左-出栈-右入栈

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

[剑指 Offer II 055. 二叉搜索树迭代器](https://leetcode.cn/problems/kTOapQ/)

后序遍历：（颠倒前序结果集）

<span style="font-size:20px">[二叉树的层序遍历](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/)：</span>

> root = [3,9,20,null,null,15,7]
> 输出：[ [3], [9,20], [15,7] ]

遍历节点，按照层数存入数组——纵向 while，横向 for



#### 二叉树的递归

**思路：**(*以求二叉树最大深度为例* )

1. 遍历一遍二叉树得出答案——后序位置回溯

    ```js
       let depth = 0, maxDepth = 0;    // depth记录当前节点深度
        dfs(root);
        return maxDepth;
    
        function dfs(root: TreeNode | null) {   // preOrder
            if (!root) return;
            depth++;
            if (!root.left && !root.right) {    // 叶子节点，更新最大深度
                maxDepth = Math.max(maxDepth, depth);
            }
            dfs(root.left);
            dfs(root.right);
            depth--;    // 回溯
        }
    ```

2. 分解问题得出答案——动态规划 后序位置主逻辑

    ```js
    function maxDepth(root: TreeNode | null): number {
        if (!root) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right)); // 当前最大深度由子树最大深度推出
    };-
    ```

[236. 二叉树的最近公共祖先](https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/description/)——递归



### 二叉搜索树

**特性:** 

+ 任意节点，左子树上所有节点值均小于根节点，右子树上所有节点值均大于根节点
+ 中序遍历结果 为升序



### 平衡二叉树 AVL

左右子树深度插不超过 1



## 堆

**适用：**求一个动态集合中最值

**堆: **存储在<span style="color:orange">数组</span>里的 具有<span style="color:orange">堆属性</span>的<span style="color:orange">完全二叉树</span>

+ 数组：

    <img src="https://labuladong.github.io/algo/images/heap/1.png" alt="img" style="zoom:13%;" />

+ 堆属性：每个节点的值大于或等于左右孩子节点值（最大堆）

    arr[i] >= arr[2i+1] && arr[i] >= arr[2i+2]

+ 完全二叉树：除了最后一层之外其他的每一层都被完全填充



<span style="font-size:20px">实现优先级队列:</span>Priority Queue

> 堆是优先队列的一种实现方式

主要实现几个重要的 API——sink (*下沉* ) , swim (*上浮* ) , insert, delMax

**sink, swim**——当 insert, del 时维护堆结构

```java
// 上浮——不断与父节点比较
private void swim(int index) {
    // 如果浮到堆顶，就不能再上浮了
    while (index > 1 && less(parent(index), index)) {
        // 如果第index个元素比上层大
        swap(parent(index), index);
        index = parent(index);
    }
}
// 下沉——与两个子节点比较 与更大值交换
private void sink(int index) {
    // 如果沉到堆底，就沉不下去了
    while (left(index) <= size) {
        // 先假设左边节点较大
        int max = left(index);
        // 如果右边节点存在，比一下大小
        if (right(index) <= size && less(max, right(index)))
            max = right(index);
        // 结点 x 比俩孩子都大，就不必下沉了
        if (less(max, index)) break;
        // 否则，不符合最大堆的结构，下沉 x 结点
        swap(index, max);
        index = max;
    }
}
```

**delMax, insert**

insert——插入元素添加到堆底的最后，上浮到正确位置

delMax——堆定元素 `A` 与堆底最后元素 `B` 交换，删除 `A`，让 `B` 下沉到正确位置



**常用方法**——构建优先队列、支持堆排序、快速找出一个集合中最值

<span style="color:blue">构建优先队列：</span>

[215. 数组中的第K个最大元素](https://leetcode.cn/problems/kth-largest-element-in-an-array/)——（常考）小顶堆，筛掉小值，留下 k 个最大值

[剑指 Offer II 060. 出现频率最高的 k 个数字](https://leetcode.cn/problems/g5c51o/)——PriorityQueue<Map.Entry<Integer,Integer>>

[1845. 座位预约管理系统](https://leetcode.cn/problems/seat-reservation-manager/)

<span style="color:blue">快速得出集合最值：</span>

[剑指 Offer II 078. 合并排序链表](https://leetcode.cn/problems/vvXgSW/)——K 个节点放入最小堆，每选取最小节点后加入其 next

[295. 数据流的中位数](https://leetcode.cn/problems/find-median-from-data-stream)——维护一个大顶堆储存有序列表前半部分，一个小顶堆储存后半部分



## 字典树/前缀树

Trie是一种树状信息检索数据结构

字典树设计的核心思想是空间换时间，所以数据结构本身比较消耗空间。但它利用了字符串的**共同前缀（Common Prefix）**作为存储依据，以此来节省存储空间，并加速搜索时间。Trie 的字符串搜索时间复杂度为 **O(m)**，m 为最长的字符串的长度，其查询性能与集合中的字符串的数量无关。其在搜索字符串时表现出的高效，使得特别适用于构建文本搜索和词频统计等应用

字典树的**性质**：
+ Root 不含字符，除 root 外每个节点含一个字符
+ 根节点到某一节点路径上所经过字符连接，为该节点对应字符串
+ 任意节点所有子节点包含字符各不相同

<img src="https://images0.cnblogs.com/blog/175043/201410/242258574966066.jpg" alt="img" style="zoom:50%;" />

Trie **实现：**

```java
// java
class TrieNode {
  TrieNode children[];
  boolean isWord = false;
  
  public TrieNode() {
    children = new TrieNode[26];
  }
}
```

```js
// js
Trie.prototype.insert = function(word) {
    let node = this.children;	// new时新建this.children = {}
    for (let ch of word) {
        if (!node[ch]) {    // 没有节点则新建分支
            node[ch] = {};
            node = node[ch];
        } else {    // 存在该分支则沿分支继续存储
            node = node[ch];
        }
    }
    node.isEnd = true;
};
```

> Why `isEnd`:
>
> isEnd 表示该节点为单词结尾，不用 `Object.keys(node).length` 是因为单词结尾不一定没有子节点。Eg：insert app, apple

Trie **关键词查找过程：**

1. 根节点开始搜索
2. 获取关键词第一字符，选择对应子节点，转到该子节点继续检索
3. 迭代
4. 某节点处，关键词所有字母已被取出，读取附在该节点上信息，查找完成

**应用：**

+ 字符串检索
+ 字符串最长公共前缀
+ 排序——先序遍历后便是字典序排序结果
+ 其他数据结构和算法的辅助结构——后缀树，AC 自动机等



<span style="color:blue">实现：</span>

[剑指 Offer II 062. 实现前缀树](https://leetcode.cn/problems/QC3q1f/)——搜索单词，前缀

<span style="color:blue">应用：</span>

[剑指 Offer II 063. 替换单词](https://leetcode.cn/problems/UhWRSj/)——匹配前缀并替换

[剑指 Offer II 065. 最短的单词编码](https://leetcode.cn/problems/iSwD2y/)——共同后缀 + DFS 计算单词长度

[剑指 Offer II 064. 神奇的字典](https://leetcode.cn/problems/US1pGT/)

[212. 单词搜索 II](https://leetcode.cn/problems/word-search-ii/)——Trie 匹配并剪枝 + DFS + 回溯，矩阵

<span style="color:blue">非典型应用：</span>

[剑指 Offer II 067. 最大的异或](https://leetcode.cn/problems/ms70jA/)==?==——位运算

[211. 添加与搜索单词 - 数据结构设计](https://leetcode.cn/problems/design-add-and-search-words-data-structure/)==?==——搜索时存在适配符 '.' 



## 图

<span style="font-size:20px">存储方式:</span>

```java
List<Integer>[] graph;	// 邻接表 gragh[i]——i的所有邻居节点
boolean[][] matrix;	// 邻接矩阵 matrix[i][j]——i是否有一条边指向j
```

借助哈希表来记录被遍历过的节点来避免陷入死循环

<span style="font-size:20px">遍历方式:</span>

DFS: 递归调用节点的邻节点（根节点 -> 邻节点1 -> 邻节点1的邻接点 -> ...）

BFS: 遍历邻节点（根节点 -> 邻接点1 -> 邻接点2 -> 邻接点1的邻接点 -> ...）

<span style="font-size:20px">二分图:</span>

图的节点集合分割成两个独立的子集 `A` 和 `B` ，并使图中的每一条边的两个节点一个来自 `A` 集合，一个来自 `B` 集合，就将这个图称为二分图

> <span style="color:blue">Eg:</span> 
>
> <img src="https://assets.leetcode.com/uploads/2020/10/21/bi1.jpg" alt="img" style="zoom:25%;" />
>
> 节点能分成两组：{0, 2} 和 {1, 3}，因此是二分图



# 排序

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

<span style="font-size:20px">冒泡排序——重复访问数列，两两比较交换顺序：</span>

**主要代码：**

```java
// 外层——第i次比较 得到len-1个最值
for (int i = 0; i < len - 1; i++) {
  // 内层——每一遍循环得到最值需两两比较的次数
  for (int j = 0; j < len - i；j++) {
		// ...
  }
}
```

+ 每一次外层循环得到的都是最大值，将其放到末尾。

<span style="font-size:20px">直接插入排序——后续元素依次与前面有序数列比较、插入：</span>

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



<span style="font-size:20px">简单选择排序——未排序列中找到最小值排到序列首部</span>

```java
// 1.遍历所有数
for (int i = 0; i < nums.length - 1; i++) {
  // 2.遍历未排序列找出最小值
  for (int j = i + 1; j < nums.length; j++) {
		// ...
  }
  // 3.得到的最小值与未排序列首部交换
  swap(nums, minIndex, i);
}
```



## 归并排序

重复将两个有序表合成新有序表

<img src="https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2018/9/10/165c2d849cf3a4b6~tplv-t2oaga2asx-watermark.awebp" style="zoom:40%;" >

**自顶向下：**

```java
public static int[] mergeSort(int[] arr) {
  // 1....递归终止:
  // 2...切割成两子序列:
  int[] left = Arrays.copyOfRange(arr, 0, middle);
  // 3.递归切割合并：
  return mergeTwo(mergeSort(left), mergeSort(right));
}
private static int[] mergeTwo(int[] left, int[] right) {
  // 申请空间
  int[] res = new int[left.length + right.length];
  
  // ...依次选取两序列中较小值不断放入新数组
}
```



## 快速排序

分治基于基准左右排序

```java
public static void quickSort(int[] arr, int left, int right) {
  // ...递归终止条件 left >= right
  int mid = partition(arr, left, right);	// 以mid大小划分出两区域
  // 递归对基准左右两边重复以上操作
  quickSort(arr, left, mid - 1);
  quickSort(arr, mid + 1, right);
}

// 分治——使基准数左边全是小于的数、右边全是大于的数
private static int partition(int[] arr, int left, int right) { // 分治法
  int random = left + new Random(right - left + 1);
  swap(nums, random, end);	// 基准交换到尾部
  int small = left - 1;
  // 小于基准的交换到前部
  for (int i = left; i < right; i++) {
		if (nums[i] < nums[right]) swap(nums, ++small, i);    
  }
  // 基准交换到中间(大小)
  swap(nums, ++small, right);
  return small;
}
```

- 相当于每次基准按大小将整个数列分为左右两个数列——左数列都比基准小*但不一定依次*
- 每一次 partition 的过程：以随机数下标为基准，基准交换到尾部，小于它的交换到前面

[剑指 Offer II 076. 数组中的第 k 大的数字](https://leetcode.cn/problems/xx4gT2/)——基于快速排序的快速选择



## 堆排序

利用堆概念的选择排序

1. 建立一个最大堆——最大值在堆的根节点
2. 数组根节点与末尾替换——把最大值放到后面，这样就完成了一趟排序
3. 自上至下调整
4. 重复以上步骤

```java
public static void heapSort(int[] arr) {
  // 1.构造初堆
  buildMaxHeap(arr, arr.length);
  for (int sortedCnt = 0; sortedCnt < arr.length; cnt++) {
    // 2.将最大元素交换到末尾(每构建一次就相当于排好一个最大值
    swap(arr, 0, arr.length - sortedCnt - 1);
    // 3.从上向下调整(sorted是上一轮 现在s+1)
    adjustToHeap(arr, 0, arr.length - sortedCnt - 1);
}
private static void buildMaxHeap(int[] arr, int size) {
  // ...从最后一个分支结点——n/2 开始按大小调整父子结点 构建最大堆:
  for (int i = size - 2 >> 1; ) {adjustToHeap}
}
private static void adjustToHeap(int[] arr, int i, int size) {
  // ...最大元素交换到此树杈的根位置：
  if (max !=== i) {
    adjustToHeap(arr, max, size);	// 继续向下调整
  }
```

  

## 桶排序

[剑指 Offer II 057. 值和下标之差都在给定的范围内](https://leetcode.cn/problems/7WqeDu/)——==不是很懂正负数怎么分桶?==



## 计数排序

**应用场景：**数组中最大最小整数差值 k 远小于 n （如排序员工年龄）

**复杂度：**时间 O(n + k) 空间 O(k)

**基本思想：**统计每个整数在数组中出现次数，以小到大顺序按照出现次数填入数组



[剑指 Offer II 075. 数组相对排序](https://leetcode.cn/problems/0H97ZC/)



# 哈希表

## 理论基础

<span style="font-size:22px">哈希函数：</span>

+ **hashCode**——通过特定编码方式，将其他数据格式转化为不同数值

将学生姓名映射到哈希表就涉及到 hash function。

如下图，通过 hashCode 把名字转化为数值，hash function 把学生的姓名直接映射为哈希表上的索引。

![img](https://img-blog.csdnimg.cn/2021010423484818.png)

+ hashCode() 数值大于 tableSize——为保证映射出的索引数值都落于哈希表，再次对数值做<span style="color:red">取模</span>的操作
+ 学生数量 dataSize大于 tableSize——就算 hash function 计算再均匀，也避免不了几位同学同时映射到统一索引，所以需要**哈希碰撞**

<span style="font-size:22px">哈希碰撞：</span>

<img src="https://img-blog.csdnimg.cn/2021010423494884.png" alt="img" style="zoom:43%;" />

一般有两种解决方法：拉链法、线性探测法

<span style="font-size:20px">拉链法：</span>

<img src="https://img-blog.csdnimg.cn/20210104235015226.png" alt="img" style="zoom:40%;" />

发生冲突元素存储在链表

拉链法就是要选择适当的哈希表的大小，这样既不会因为数组空值而浪费大量内存，也不会因为链表太长而在查找上浪费太多时间

<span style="font-size:20px">线性探测法：</span>

+ **前提：**tableSize > dataSize —— 依靠哈希表空位解决碰撞问题

<span style="font-size:20px">常见哈希结构：</span>

数组、Set——集合、Map——映射



# 回溯

> 回溯的本质是<span style="color:red">穷举</span>——穷举所有可能选出想要的答案；其实是暴力查找并不是什么高效算法

> **主要解决：**
>
> + 子集——有多少符合条件的子集
>
> + 组合——N 个数找符条件的 k 个数的集合
> + 切割——一个字符串有几种符条件的切割方式
> + 排列——N 个数按一定规则全排列有几种方式
> + 棋盘——N 皇后、解数独...

**理解回溯：**

决策树的遍历过程，站在回溯树的一个节点上，只需思考 3 个问题：

1. 路径——已经做出的选择。

2. 选择列表——当前可以做的选择。

3. 结束条件——到达决策树底层，无法再做选择的条件。

回溯搜索的**遍历过程**：

> 因为回溯解决的都是<span style="color:red">在集合中递归查找子集</span>，集合的大小构成树的宽度，递归的深度构成树的深度

<img src="https://img-blog.csdnimg.cn/20210130173631174.png" height="250px">

由图可以看出 for 循环可以理解是<span style="color:red">横向遍历</span>，递归 (backtracing) 就是<span style="color:red">纵向遍历</span>，这样就把这棵树全部遍历。



<span style="font-size:20px">子集/组合:</span>

> 子集——返回树的所有节点
>
> 组合——返回符合某条件的节点

[78. 子集](https://leetcode.cn/problems/subsets/)——给定一组不含重复元素的整数数组 `nums`，返回该数组所有可能的子集。

[77. 组合](https://leetcode.cn/problems/combinations/)——返回所有可能的 `k` 个数的组合。

[90. 子集 II](https://leetcode.cn/problems/subsets-ii/)——含重复元素

[40. 组合总和 II](https://leetcode.cn/problems/combination-sum-ii/)——含重复元素

[39. 组合总和](https://leetcode.cn/problems/combination-sum/)——元素无重可复选

[22. 括号生成](https://leetcode.cn/problems/generate-parentheses/)

> To **重复元素类型**：
>
> 1. 排序
>
> 2. 剪枝（同一层不含重复元素
>
>     ```js
>     if (i > start && nums[i] === nums[i - 1]) continue;
>     ```

> To **可复选类型:**
>
> ```js
> for (int i = start, i < nums.length; i++) {
> 	// ...
>   backtrack(i);	// 是i不是i+1了
>   // ...
> }
> ```
>
> 下一回溯的参数还是 `i`，因为当前元素下一子树仍可复选

+ **分析：**

    <img src="https://img-blog.csdnimg.cn/202011232041348.png">

<span style="font-size:20px">排列:</span>

> 返回所有叶子节点 
>
> 穷举元素的位置，额外使用 `used` 避免重复选择。

[46. 全排列](https://leetcode.cn/problems/permutations/)

[47. 全排列 II](https://leetcode.cn/problems/permutations-ii/)——含重复元素。列举位置

> 1. 排序
>
> 2. 重复元素的**剪枝**逻辑：
>
> ```js
> if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]{
>     // 如果前面的相邻相等元素没有用过
>     continue;
> }
> ```




# 贪心算法

选择每一阶段的局部最优，从而达到全局最优。

**贪心算法步骤：**

1. 问题分解为若干子问题
2. 求解每一子问题最优解
3. 局部最优解堆叠成全局最优解



# 动态规划

> 可以优化重叠子问题

> For: 解决问题需 n 步，每一步有多重选择。因为不列出所有方案因此用动规不是回溯

## 01 背包

> 每个物体数量只有一个（不选，选一个）

<span style="font-size:20px">二维 dp\[][] 数组:</span>

1. **数组含义：**dp\[i][j]——从下标 0~i 的物品里任取 放进容量为 j 的背包，达到的最大价值

2. **状态转移方程：**

    1. 不放该物品： = dp\[i-1][j]
    2. 放：= dp\[i - 1][j - w(i)] + v(i)

    ∴ dp\[i][j] = max(dp\[i - 1][j], dp\[i - 1][j - w(i)] + v(i))

3. **初始化：**

    dp\[0][j] = 0;	dp\[i][0] = 0;



# 数学

Java 为例

int 32 位 (<span style="color:orange">-2^31^ ~ 2^31^ - 1</span>) （十进制 8 位）

有符号整数，最高位 0 为正 1 负



[剑指 Offer II 001. 整数除法](https://leetcode.cn/problems/xoh6Oh/)——减法代替除法；时间复杂度优化——减去除数的次方；注意溢出



## 位运算

基于整数的二进制表示进行的运算（均是转换为二进制计算）

<span style="font-size:20px">位操作符: </span>

<span style="color:orange">**& | ~ ^**</span>: 按位 与 或 非 异或(*相异为 1*)

```
  111
& 010
= 010
```

<span style="color:orange">**<< >>**</span>: 移位（可用于对 2 次方数的运算）

对于右移，

+ 有符号数 算术右移：补符号位（负数补码运算）
+ 无符号数 逻辑右移：补 0  `>>>`

```
unsigned int a = 4; a >> 2 = 1;	// 右移两位相当于除以2^2
Before:  0100
After:   0001

int b = -4; b >> 1 = -2;
B: 11011
A: 11101
```



**异或 性质:** [136. 只出现一次的数字](https://leetcode.cn/problems/single-number/description/?favorite=2ckc81c)

+ 交换律 a ^ b ^ c = a ^ c ^ b
+ 相同数异或为 0
+ 0 ^ n => n



**取得 Low Bit：**最低位的 1 [x & -x 表示含义 - CSDN](https://blog.csdn.net/oyoung_2012/article/details/79932394)

`x & -x`



<span style="color:blue">**题目：**</span>

[191. 位1的个数](https://leetcode.cn/problems/number-of-1-bits/description/)——与运算

```java
// 法一:循环遍历检查二进制位
for (int i = 0; i < 32; ++i) {
  if ((n & (1 << i)) != 0) ++cnt;	// ..01左移i位,i位为1则返回大于0
}
// 位运算优化:
while (n != 0) {
  n = n & (n - 1);    // 运算后最低位的1变0
  ++cnt;
}
```

[338. 比特位计数](https://leetcode.cn/problems/counting-bits/description/?favorite=2cktkvj)——动规推导得 1 的个数。偶数 `dp[i]=dp[i/2]`，奇数 `dp[i]=dp[i-1]+1`

[371. 两整数之和](https://leetcode.cn/problems/sum-of-two-integers/description/?favorite=2ckc81c)——位运算实现加法。a ^ b (无进位相加) + ((a & b) << 1) (进位)

[190. 颠倒二进制位](https://leetcode.cn/problems/reverse-bits/description/?favorite=2ckc81c)

<span style="color: blue">只出现一次的数字：</span>

[136. 只出现一次的数字](https://leetcode.cn/problems/single-number/description/?favorite=2ckc81c)——其余 2 次，直接利用异或性质

[137.只出现一次的数字 II](https://leetcode.cn/problems/single-number-ii)——其余 3 次。利用其余数都出现 3 次的特点，每一二进制位都是该位和 mod 3 得到的数

[260.只出现一次的数字 III](https://leetcode.cn/problems/single-number-iii)——其余出现 2 次，找两个出现一次的数字。根据 （`x1 ^ x2` 的）low bit 将元素分为两类分别异或得到两元素





## 概率

<span style="color:blue">RandA() -> RandB() :</span> 通过 RandA() 随机出 RandB() 的分布



### 拒绝采样

> **举例：**只要[1,n] 的结果就把 (n, m] 结果再次随机，从而使随机结果落在 [1, n]

适用大多情况的构造方法——多次采样结果相乘得到的概率映射到目的区间

（理论支撑——独立事件古典概型 P(AB) = P(A) * P(B)）



<span style="color:blue">例解：</span>[470. 用 Rand7() 实现 Rand10()](https://leetcode.cn/problems/implement-rand10-using-rand7/description/)

R(10) = 1/2 * 1/5

```java
public int rand10() {
  int n1, n2;
  while ((n1 = rand7()) > 6); // 第一次采样 拒绝7 奇数偶数作俩种结果
  while ((n2 = rand7()) > 5); // 第二次采样 拒绝6/7 5种结果
  return (n1 - 1) * 5 + n2; // 把两次的10种结果映射到[1,10]
}
```

> **重点：**
>
> + 因子的选取（2, 5)：不能大于 A
> + 拒绝采样（7 -> 5）：用 Rand7() 随机出 5 种结果
> + 映射

<span style="color:blue">例 2:</span>Rand7()实现Rand100()

```java
int rand100() {
  int n1, n2, n3;
  while ((n1 = rand7()) >4);
  while ((n2 = rand7()) > 5);
  while ((n3 = rand7()) > 5);
  return (n1 - 1) * 25 + (n2 - 1) * 5 + n3;
}
```



### Knuth 洗牌算法

随机乱置算法——将一个数组打乱后输出。

**实现：**靠随机选取元素交换来获取随机性

```js
for (let i = 0; i < len; ++i) {	// 从[0,len)依次填充
  const random = i + Math.floor((len - i) * Math.random());   // 从[i,len)抽取一个与i交换
  [res[i], res[random]] = [res[random], res[i]];
}
```

**验证：**产生 n! 种结果

[384. 打乱数组](https://leetcode.cn/problems/shuffle-an-array)——洗牌算法模板题



## 摩尔投票

**对拼消耗原理**用 O(1) 空间复杂度得出众数

<span style="color:blue">Eg——[169.多数元素](https://leetcode.cn/problems/majority-element)找出 nums 中出现次数最多元素:</span>

```java
public int majorityElement(int[] nums) {
  int candidate = nums[0];
  int cnt = 0;
  for (int n : nums) {
    // 若当前candidate票数为0，它来竞选
    if (cnt == 0) {
      candidate = n;
    }
    cnt += candidate == n ? 1 : -1; // 投票机制-相同则计票
  }
  return candidate;
}
```

[229. 多数元素 II](https://leetcode.cn/problems/majority-element-ii)——三个不同元素相抵消



## 唯一分解定理

又称算术基本定理

**定理：**任何一个大于 1 的正整数可唯一分解成若干质数幂的乘积，称为标准分解式

N = P~1~^α1^ P~2~^α2^ P~3~^α3^ ... P~k~^αk^ （其中 p1, p2, ... 依次表示 2, 3, 5 ... 等质数

> <span style="color:blue">**Eg:**</span>
>
> 24 = 2^3^ x 3^1^ x 5^0^ x 7^0^
>
> 70 = 2^1^ x 3^0^ x 5^1^ x 7^1^



<span style="color:blue">**整除:**</span>

定理可用于判断 a 能否被 b 整除：a % b == 0 <=> 对于所有 i 有 α~i~ >= β~i~ 

即将乘式化为标准分解式再比较指数大小



## 因子

所有能整除该数的数

`8: 1, 2, 4, 8`



# 数据结构设计

[155.最小栈](https://leetcode.cn/problems/min-stack)——满足栈功能同时要能读取当前栈内最小值。栈内储存于最小值的差值 / 辅助栈储存当前最小值

[705. 设计哈希集合](https://leetcode.cn/problems/design-hashset/description/)



# LRU

LRU (*Least Recently Used* ) 是一种缓存淘汰策略

**特征：**

+ 元素有顺序——以区分最近使用和久未使用，容量满时删除最久未使用

+ 查找元素 O(1)
+ O(1) 下插入、删除元素——每次访问 Cache 将该 key 元素变为最近使用

**数据结构：**

哈希链表 LinkedHashMap——Hash<key, Node>（快速查找）+ 双向 LinkedList（有顺序）

<img src="https://labuladong.github.io/algo/images/LRU%e7%ae%97%e6%b3%95/4.jpg" alt="img" style="zoom:40%;" />

+ Node {key, val, prev, next}
+ O(1) 插入、删除——采用<span style="color:orange">双向链表</span>才能快速获取 prev, next

[剑指 Offer II 031. 最近最少使用缓存](https://leetcode.cn/problems/OrIXps/)



# Solution

## 双指针

### 常规双指针

<span style="font-size:20px">链表:</span>

+ 单链表位置：

    [19. 删除链表的倒数第 N 个结点](https://leetcode.cn/problems/remove-nth-node-from-end-of-list/)——找倒数 N+1 节点

    [876. 链表的中间结点](https://leetcode.cn/problems/middle-of-the-linked-list/)——`fast` 到末尾时 `slow` 到中间

    [剑指 Offer II 022. 链表中环的入口节点](https://leetcode.cn/problems/c32eOV/)——快慢指针相遇

+ 两链表：

    [21. 合并两个有序链表](https://leetcode.cn/problems/merge-two-sorted-lists/)——合二为一

    [86. 分隔链表](https://leetcode.cn/problems/partition-list/)——一分为二

+ 两链表位置

    [160. 相交链表](https://leetcode.cn/problems/intersection-of-two-linked-lists/)——让双指针同时指向相交节点

<span style="font-size:20px">数组:</span>

**快慢指针:**

+ 原地修改数组

    [27. 移除元素](https://leetcode.cn/problems/remove-element/)

    ```js
    while (fast < nums.length) {
      if (nums[fast] != val) {
    nums[slow] = nums[fast];
        slow++;
      }
      fast++;
    }
    ```

    [26. 删除有序数组中的重复项](https://leetcode.cn/problems/remove-duplicates-from-sorted-array/)

    <img src="https://labuladong.github.io/algo/images/%e6%95%b0%e7%bb%84%e5%8e%bb%e9%87%8d/1.gif" alt="img" style="zoom:25%;" />

    [905.按奇偶排序数组 - 力扣（LeetCode）](https://leetcode-cn.com/problems/sort-array-by-parity/submissions/)

    [443. 压缩字符串](https://leetcode.cn/problems/string-compression/)——类似与 26，找重复项

    [75. 颜色分类](https://leetcode.cn/problems/sort-colors/)——排序 0, 1, 2

+ 滑动窗口

**左右指针**——常用于有序数组

+ 二分查找

+ n 数之和——通过调节 `left` 和 `right` 调整 `sum` 的大小

    [167. 两数之和 II - 输入有序数组](https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/)

    [剑指 Offer II 007. 数组中和为 0 的三个数](https://leetcode.cn/problems/1fGaJU/)——固定 `i`, 移动 `left`, `right`

    [18. 四数之和](https://leetcode.cn/problems/4sum)——排序再双指针，剪枝

+ [344. 反转字符串](https://leetcode.cn/problems/reverse-string/)

+ [5. 最长回文子串](https://leetcode.cn/problems/longest-palindromic-substring/)——中心向两边移动

**逆向双指针**——避免后面值被前面覆盖：

+ [88.合并两个有序数组 - 力扣（LeetCode）](https://leetcode-cn.com/problems/merge-sorted-array/)

### 滑动窗口

> **O(n):**
>
> 因为数组中的每个元素都只会进入窗口一次，然后被移出窗口一次，不会说有某些元素多次进入和离开窗口
>
> **前提：**
>
> 所有数字均为正数，才保证右指针前进是增加，左指针前进减少

**框架:**

```js
function slidingWindow(s) {
  let window = new Map<string, number>();
	let left = 0, right = 0;	// 滑动窗口[l,r) 元素个数r-l
	while (right < s.length) {
  	let r = s.charAt(right);	// 将移入窗口字符
    right++;	// 增大窗口
    // ...数据更新
    
    while (window needs shrink) {
      let l = s.charAt(left);	// 将移出窗口字符
      left++;	// 缩小窗口
      // ...数据更新
    }
	}
}
```

**常用变量：**`window`——窗口内数据, `need`——所需数据, `valid`——已满足数据

+ **needed：**当题意是要找到满足字符出现次数时，可以只用一个哈希表 `need` 解决。加入一个字符时 need.get(ch) - 1，移走一个时 need.get(ch)++。不必担心 -1 时出现负数的情况，只需判断 = 0 时 valid +/- 即可



[剑指 Offer II 008. 和大于等于 target 的最短子数组](https://leetcode.cn/problems/2VG8Kg/)——维护窗口内 sum

[剑指 Offer II 009. 乘积小于 K 的子数组](https://leetcode.cn/problems/ZVAVXX/)——不那么常规的滑动窗口

[3. 无重复字符的最长子串](https://leetcode.cn/problems/longest-substring-without-repeating-characters/)——维护窗口内字符出现次数

[438. 找到字符串中所有字母异位词](https://leetcode.cn/problems/find-all-anagrams-in-a-string/)——找到全部定长窗口内指定字符组合（同 [567. 字符串的排列](https://leetcode.cn/problems/permutation-in-string/)）

[76. 最小覆盖子串](https://leetcode.cn/problems/minimum-window-substring/)——维护窗口内含指定字符



## 动态规划

> 动态规划即具有很多重叠子问题的问题，每一个状态一定是由上一个状态推导出来的
>
> **动态规划**是由前一个状态推导出来的，而**贪心算法**是局部直接选最优的

**步骤：**

1. **确定 dp 数组**(*dynamic programming table* )及下标含义
2. 确定**递推公式**（每一个状态由上一个状态推导）
3. dp **数组初始化**
4. 确定**遍历顺序**

对于 **dp[i] = {dp[i - 1], dp[i - 2]}** 类型的**空间复杂度优化 滚动数组**：

对每个下标<span style="color:orange">执行 % 2 操作</span>，int[nums.length] 优化为 int[2] / 重新赋值

```java
dp[i % 2] = Math.max(dp[(i - 2) % 2] + nums[i], dp[(i - 1) % 2]);
```

**针对二维 dp 数组的空间压缩技巧——空间复杂度优化**

> 以[剑指 Offer II 098. 路径的数目](https://leetcode.cn/problems/2AoeFn/)为例：
>
> dp\[i][j] 只与 `dp\[i][j-1]` (左) 和 `dp\[i -1][j]` (上) 有关，因此每遍历一个就覆盖一个

```java
for (int i = 0; i < m; i++) {	// 行
  for (int j = 1; j <= n; j++) {	// 列
    dp[j] = dp[j - 1] + dp[j];  // =左+上 并覆盖j(原上)
  }
}
```

For 特殊情况：dp[j] 仍可能被后序使用不能直接覆盖——从后往前遍历

<span style="color:blue">进阶</span>：**输出路径**

记录每步选择的上一节点



<span style="color:blue">**例题：**</span>

[70.爬楼梯 - 力扣（LeetCode）](https://leetcode-cn.com/problems/climbing-stairs/submissions/)

[剑指 Offer II 089. 房屋偷盗](https://leetcode.cn/problems/Gu0c2T/)——不能偷相邻两家，可一维可二维。输出路径：利用最大金额对DP数组反推，每次找到上一次的最大金额和下标

[96. 不同的二叉搜索树](https://leetcode.cn/problems/unique-binary-search-trees/)——不容易想到的动态规划

[983. 最低票价](https://leetcode.cn/problems/minimum-cost-for-tickets/)——复杂一维，哪些天买持续几天的火车通行证

<span style="color:blue">矩阵路径:</span>

[剑指 Offer II 098. 路径的数目](https://leetcode.cn/problems/2AoeFn/)——m x n 格子路径数。优化为一维 dp

[剑指 Offer II 099. 最小路径之和](https://leetcode.cn/problems/0i0mDW/)

[剑指 Offer II 100. 三角形中最小路径之和](https://leetcode.cn/problems/IlPe0q/)

<span style="color:blue">字符串动态规划:</span>

+ [剑指 Offer II 095. 最长公共子序列](https://leetcode.cn/problems/qJnOS7/)——两个字符串找公共子序列。典型二维动规
+ [剑指 Offer II 092. 翻转字符](https://leetcode.cn/problems/cyJERH/)
+ [剑指 Offer II 096. 字符串交织](https://leetcode.cn/problems/IY6buf/)
+ [剑指 Offer II 097. 子序列的数目](https://leetcode.cn/problems/21dk04/)

<span style="color:blue">01 背包: </span>

[剑指 Offer II 101. 分割等和子集](https://leetcode.cn/problems/NUPfPr/)——分成元素和相等两部分

[剑指 Offer II 102. 加减的目标值](https://leetcode.cn/problems/YaVDxD/)——+ / - 元素组合表达式结果 target 的不同式子数

<span style="color:blue">完全背包:</span>

[剑指 Offer II 103. 最少的硬币数目](https://leetcode.cn/problems/gaM7Ch/)——凑成目标金额最少数。一维 dp 稍复杂的状态转移方程。

[剑指 Offer II 104. 排列的数目](https://leetcode.cn/problems/D0F0SV/)——与上一题同种状态转移方程.注意是排列不是组合。

<span style="color:blue">More：</span>

[剑指 Offer II 090. 环形房屋偷盗](https://leetcode.cn/problems/PzWKhm/)——打家劫舍进阶，拆分为两个打家劫舍问题，算前 n-1 与后 n-1 中更大值

[337.打家劫舍 III](https://leetcode.cn/problems/house-robber-iii)——树形动规

[413. 等差数列划分](https://leetcode.cn/problems/arithmetic-slices/)——数学

[剑指 Offer II 093. 最长斐波那契数列](https://leetcode.cn/problems/Q91FMA/)——不易想到状态转移方程



### 记忆化搜索

**概念：**（与常规动规比较）

记忆化搜索的编程模式是<span style="color:orange">递归</span>

<span style="color:orange">自顶向下</span>，而常规动规自底向上

不需严格<span style="color:orange">计算顺序</span>，而常规动规根据 base case 递推 DP table

<span style="color:blue">**例解:**</span>——对于需频繁判断 [i, j] 是否回文

```js
// 记忆化搜索中，f[i][j] = 0 表示未搜索，1 表示是回文串，-1 表示不是回文串
const isPalindrome = (i, j) => {
  if (f[i][j] !== 0) {	// 已经搜索过 直接返回
    return f[i][j];
  }
  if (i >= j) {
    f[i][j] = 1;
  } else if (s[i] === s[j]) {
    f[i][j] = isPalindrome(i + 1, j - 1);	// 自顶向下
  } else {
    f[i][j] = -1;
  }
  return f[i][j];
}
```

<span style="color:blue">题目：</span>

[131. 分割回文串](https://leetcode.cn/problems/palindrome-partitioning/description/)——分割成不同回文串的方案



### To 股票

**状态转移方程:**

<span style="color:orange">dp\[i]\[k][0 or 1]</span>

> i——0 <= i <= n - 1 (n 为天数)
>
> k——1 <= k <= K (K 为允许交易的最大次数) <span style="color:red">注意 k 表示当前最多还能交易 k 次，而不是当前剩几次</span>
>
> 01——当前是否持有股票 (buy, sell, rest)

> 最终题解: dp\[n - 1]\[k][0]

<span style="color:orange">dp\[i]\[k][0] = max{ dp\[i-1]\[k][0], dp\[i-1]\[k][1] + prices[i] }</span>——今天不持有股票

> ​				 max{ 今天 rest,       今天 sell                         }

<span style="color:orange">dp\[i]\[k]\[1] = max{ dp\[i-1]\[k][1], dp\[i-1]<span style="color:red">\[k-1]</span>[0] - prices[i] }</span>——今天持有股票

> ​                 max{ 今天 rest,       今天 buy                         }

**Base case:**

dp\[-1]\[...][0] = dp\[...]\[0][0] = 0

dp\[-1]\[...][1] = dp\[...]\[0][1] = -Infinity



[122. 买卖股票的最佳时机 II](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/)——K = +∞

[121. 买卖股票的最佳时机](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/)——K = 1 第二个状态省略

[714. 买卖股票的最佳时机含手续费](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/)——K = +∞，buy 时利润 - 手续费

[309. 最佳买卖股票时机含冷冻期](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/)——K = +∞，第 i 天 buy 时，从 i - 2 天转移

[123. 买卖股票的最佳时机 III](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/)——K = 2

[188. 买卖股票的最佳时机 IV](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/)——任意给定 K



## DFS / BFS

### BFS

> 一般用于找最短路径

**思路：**

Queue 存储每一层节点，遍历其中每一节点加入其子节点

**模板：**

```typescript
let queue: Node[],	// 核心数据结构
	visited: Set<Node>;	// 避免重复访问节点
queue.push(root);	// 加入起点

while (queue.length !== 0) {
  let lastSize = queue.length;
  for (let i = 1; i <= lastSize; i++) {	// i仅为了控制遍历节点个数
 		/* 取出节点并加入其邻接节点 */
    let node = queue.shift();
    // ...
    /* 进入下一层 */
  }
}
```

> **Note:**
>
> + 如果遍历的是树的话不需要维护 `visited`

**例:**

[111. 二叉树的最小深度](https://leetcode.cn/problems/minimum-depth-of-binary-tree/)

[剑指 Offer II 043. 往完全二叉树添加节点](https://leetcode.cn/problems/NaqhDT/)——队列维护逻辑略为不同

[279. 完全平方数](https://leetcode.cn/problems/perfect-squares/)——能凑成 n 的最少的平方数之和

[752. 打开转盘锁](https://leetcode.cn/problems/open-the-lock/)——找到最少步骤



### DFS

<span style="font-size:20px; color:blue">To 图:</span>

**思路: **

1. 外层循环遍历每一个节点
2. 对节点深度遍历——其联通节点

**二维数组遍历模板：**

```ts
function maxAreaOfIsland(grid: number[][]): number {
    let m = grid.length, n = grid[0].length;
  	// ...由题意排除岛屿
    // 遍历每一块，遇到土地dfs留最大面积
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (grid[i][j] === 1) { // dfs开始遍历土地
                // ...操作
              	dfs(i, j);
            }
        }
    }
    return max;

    function dfs(x: number, y: number): void {
        // 超出边界或遇到海洋返回0
        if (x >= m || x < 0 || y >= n || y < 0 || grid[x][y] !== 1) return;
        // ...操作
        // 遍历过的土地置为0避免重复遍历
        grid[x][y] = 0;
        // ...从上下左右四个方向遍历
        return num;
    }
};
```

**例题：**

<span style="color:blue">岛屿类:</span>

[200.岛屿数量](https://leetcode.cn/problems/number-of-islands/)——模板题，节点连通即是组成一个岛屿

[1254. 统计封闭岛屿的数目](https://leetcode.cn/problems/number-of-closed-islands/)——排除靠边岛屿 先将沿岸岛屿淹没

[695. 岛屿的最大面积](https://leetcode.cn/problems/max-area-of-island/)

[1905. 统计子岛屿](https://leetcode.cn/problems/count-sub-islands/)——两个数组 排除不为子岛的岛屿

<span style="color:blue">图类==?==</span>

[547. 省份数量](https://leetcode.cn/problems/number-of-provinces/)——节点连通即是组成一个省份 遍历节点不是边



<span style="font-size:20px; color:blue">To 树:</span>

[129. 求根节点到叶节点数字之和](https://leetcode.cn/problems/sum-root-to-leaf-numbers/)

[剑指 Offer II 050. 向下的路径节点之和](https://leetcode.cn/problems/6eUYwP/)



<span style="font-size:20px; color:blue">后序遍历:</span>

> 题目和子树有关，那大概率要给函数设置合理的定义和返回值，在后序位置写代码

[543. 二叉树的直径](https://leetcode.cn/problems/diameter-of-binary-tree/)——任意两个结点路径长度中的最大值

[剑指 Offer II 051. 节点之和最大的路径](https://leetcode.cn/problems/jC7MId/)——思路有点绕



## To 二叉树

> **解题思维模式:** (不是拿递不递归区分)
>
> + 是否可以通过遍历一遍二叉树得到答案——遍历: traverse 函数 + 外部变量实现
> + 是否可以定义一个递归函数 通过子问题 (子树) 的答案推导出原问题的答案——递归: 利用递归函数返回值
>
> 无论哪一种思维模式，都要考虑——单独抽出一个节点需要做什么？需要什么时候 (前 / 中 / 后 序位置) 做

[226. 翻转二叉树](https://leetcode.cn/problems/invert-binary-tree/)——遍历 / 分解

[116. 填充每个节点的下一个右侧节点指针](https://leetcode.cn/problems/populating-next-right-pointers-in-each-node/)——遍历

[114. 二叉树展开为链表](https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/)——分解



### To 二叉树构造

> 一般都是使用「分解问题」的思路：构造整棵树 = 根节点 + 构造左子树 + 构造右子树

[654. 最大二叉树](https://leetcode.cn/problems/maximum-binary-tree/)——由数组最大值构造

[105. 从前序与中序遍历序列构造二叉树](https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)——由前中序特点分解构造，分解即不断更新序列构造子树

[106. 从中序与后序遍历序列构造二叉树](https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/)

[剑指 Offer II 048. 序列化与反序列化二叉树](https://leetcode.cn/problems/h54YBf/)——前序遍历，并记录 null 子树 为 '#'



### To 二叉搜索树 BST

+ 利用**中序遍历特性**满足题目要求：

    [230. 二叉搜索树中第K小的元素](https://leetcode.cn/problems/kth-smallest-element-in-a-bst/)——O(N)

    [538. 把二叉搜索树转换为累加树](https://leetcode.cn/problems/convert-bst-to-greater-tree/)

+ 利用左小右大特性**优化**算法效率==?==： 

    每个 TreeNode 节点额外 `size` 字段维护以自己为根的这棵二叉树有多少个节点

    > 有了 `size` 字段外加左小右大的性质，对于每个节点 `node` 就可以通过 `node.left` 推导出 `node` 的排名，从而能将 230 题优化为对数级算法。

+ **基础操作**——<span style="color:orange">判断合法性</span>、增<span style="color:orange">删</span>改查

    [98. 验证二叉搜索树](https://leetcode.cn/problems/validate-binary-search-tree/)——参数传递节点值大小范围约束

    [剑指 Offer 33. 二叉搜索树的后序遍历序列](https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/)——由后序遍历验证是否满足 BST

    [700. 二叉搜索树中的搜索](https://leetcode.cn/problems/search-in-a-binary-search-tree/)

    <span style="color:blue">BST 中插入一个数: </span>——先找再改

    ```java
    TreeNode insertIntoBST(TreeNode root, int val) {
      // 找到空位置插入新节点
      if (root == null) return new TreeNode(val);
      if (root.val < val) 
        root.right = insertIntoBST(root.right, val);
      if (root.val > val) 
        root.left = insertIntoBST(root.left, val);
      return root;
    }
    ```

    [450. 删除二叉搜索树中的节点](https://leetcode.cn/problems/delete-node-in-a-bst/)——先找再改

    > **删除节点:**——多种情况
    >
    > + 末端节点——直接 `return null`
    >
    > + 有一个子节点——子节点接替其位置
    >
    > + 两子节点——找到左子树中最大节点 或 右子树中最小节点来接替
    >
    >     ![img](https://labuladong.github.io/algo/images/BST/bst_deletion_case_3.png)

+ **构造** BST:

    [96. 不同的二叉搜索树](https://leetcode.cn/problems/unique-binary-search-trees/)——返回多少种 动态规划

    [95. 不同的二叉搜索树 II](https://leetcode.cn/problems/unique-binary-search-trees-ii/)——返回所有 BST
    
+ **平衡 BST**——Java TreeSet / TreeMap

    涉及到大量查找 大于 / 小于 某值操作时

    [剑指 Offer II 058. 日程表](https://leetcode.cn/problems/fi9suh/)

    [剑指 Offer II 057. 值和下标之差都在给定的范围内](https://leetcode.cn/problems/7WqeDu/)



### To 最近公共祖先==...==



## To 图

**建图时的注意：**

有些点不与其它点连通，不能用 putIfAbsent() 创建，而应先对每个节点执行 `graph.put(<>, new <>)`



### 图的搜索

见 #[DFS / BFS]() 

```java
boolean[] visited;	// 记录被遍历过的节点（无环则不用
boolean[] onPath; 	// 记录从起点到当前节点的路径

/* 图遍历框架DFS */
void traverse(Graph graph, int s) {
    if (visited[s]) return;
    // 经过节点 s，标记为已遍历
    visited[s] = true;
    // 做选择：标记节点 s 在路径上
    onPath[s] = true;
    for (int neighbor : graph.neighbors(s)) {
        traverse(graph, neighbor);
    }
    // 撤销选择：节点 s 离开路径
    onPath[s] = false;
}
```

[797. 所有可能的路径](https://leetcode.cn/problems/all-paths-from-source-to-target/)——无环，当作多叉树遍历邻接表

<span style="color:blue">距离:</span>

**时间优化: To 单一起始节点，单一目标节点的最短距离问题：==...==**

[剑指 Offer II 107. 矩阵中的距离](https://leetcode.cn/problems/2bCMpM/)——0 1 矩阵找出每个1到 0 最小距离。<span style="color:orange">超级源点</span>——先加入所有 0 再遍历 queue。

[剑指 Offer II 112. 最长递增路径](https://leetcode.cn/problems/fpTFWP/)——矩阵中最长递增路径。看作小值到大值单向边，转化为有向无环图遍历

[剑指 Offer II 108. 单词演变](https://leetcode.cn/problems/om3reC/)——给定字典中单词演变的最少次数。可双向 BFS 优化

[剑指 Offer II 109. 开密码锁](https://leetcode.cn/problems/zlDJc7/)——四位数切换。

<span style="color:blue">有向图的环检测:</span> [207. 课程表](https://leetcode.cn/problems/course-schedule/)——建图 + DFS 环检测 

<span style="color:blue">二分图:</span>

[剑指 Offer II 106. 二分图](https://leetcode.cn/problems/vEAB3K/)——验证是否构成二分图。<span style="color:orange">涂色法</span>。

<span style="color:blue">权：</span>

[剑指 Offer II 111. 计算除法](https://leetcode.cn/problems/vlzXQL/)——建立并遍历有向带权图



### 拓扑排序

> **概念：**
>
> 对一个有向无环图排序得到的序列。A -> B 则序列中 A 在 B 前

**思路：**

1. 取出入度 0 节点加入序列，删除该节点及其边
2. 重复步骤 1，直到不存在入度 0
3. 此时图空 => 有向无环 / 图不空 => 有环

**实现：**

DFS==。。。== 逆向思维

BFS：(更易理解)

```java
// 1...加入所有入度0节点
Map<Integer, List<Integer>> graph = new HashMap<>();
Map<Integer, Integer> inDegree = new HashMap<>();
// 2.BFS
while (!queue.isEmpty()) {
  int cur = queue.poll();
  order.add(cur);	// 加入序列
  // 邻接节点入度-1后为0的加入队列
  for (int next : graph.get(cur)) {/*...*/}
}
```

**用途：**

还可用于判断有向图是否有环

**题目：**

<span style="color:blue">有向图环检测</span>：[207. 课程表](https://leetcode.cn/problems/course-schedule/)

<span style="color:blue">排序:</span>

[210. 课程表 II](https://leetcode.cn/problems/course-schedule-ii/)——返回正确的上课顺序（先修后修）

[剑指 Offer II 114. 外星文字典](https://leetcode.cn/problems/Jf1JuT/)——由字典序得出依赖关系建图后拓扑排序

[剑指 Offer II 115. 重建序列](https://leetcode.cn/problems/ur2n8P/description/)——不明显的拓扑排序序列问题。验证是否为唯一拓扑排序序列。



### 并查集 Union-Find

**概念：**

<span style="color:orange">树形</span>的数据结构，用来表示不相交集合的数据

节点有一个指向<span style="color:orange">父节点的指针</span>，根节点指向自己

<span style="color:orange">子集</span>用树的根节点代表

两种<span style="color:orange">操作</span>：合并、查找

+ 合并——两个子集合并：将一个子集根节点指针指向另一子集根节点

+ 查找——元素处于哪个子集：元素节点沿父指针查找直到根节点。

    常用于判断两元素是否属于同一个子集

**技巧 路径压缩：**

fathers 数组存储根节点。第一次查找到 i 的根后更新 i 和 i 到根路径上所有节点的父节点为根，避免后续重复查找

**框架：**

```java
// 1.Init Union——均指向自身 各自为单独子集
int[] fathers = new int[citys];   // fathers[i]——i所处子集的父节点 经过路径压缩优化后储存根节点
for (int i = 0; i < citys; i++) {
  fathers[i] = i;
}
// 2.连接并统计
int cnt = citys;
for (int i = 0; i < citys; i++) {
  for (int j = i + 1; j < citys; j++) {
    if (isConnected[i][j] == 1 && union(fathers, i, j)) {
      cnt--;
    }
  }
}
return cnt;
```

+ 合并：

    ```java
    // 合并
    private boolean union(int[] fathers, int i, int j) {
      int fatherI = findFather(fathers, i),
      fatherJ = findFather(fathers, j);
      if (fatherI == fatherJ) return false;   // 已处于同一子集
      // 合并到同一子集
      fathers[fatherI] = fatherJ; // 注意更改的是父节点的
      return true;
    }
    ```

+ 查找 基于路径压缩

    ```java
    private int findFather(int[] fathers, int i) {
      if (fathers[i] == i) return i;
      fathers[i] = findFather(fathers, fathers[i]);   // i的父节点即i父节点的...的父节点(根)
      return fathers[i]; 
    }
    ```



**<span style="color:blue">题目：</span>**

[剑指 Offer II 116. 省份数量](https://leetcode.cn/problems/bLyHh0/description/)——<span style="color:blue">例题。</span>连通城市构成省份的数量。

[剑指 Offer II 117. 相似的字符串](https://leetcode.cn/problems/H6lPxb/description/)——类似上一题，连通用相似自行判断。

[剑指 Offer II 118. 多余的边](https://leetcode.cn/problems/7LpjUW/description/)——不明显的并查集问题，去掉哪条边能构成树。

[剑指 Offer II 119. 最长连续序列](https://leetcode.cn/problems/WhsWhI/description/)——找未排序元素连续的最长序列。大小连续则合并子图，返回最大子图节点数。



### To 欧拉回路

==...==

[332. 重新安排行程](https://leetcode.cn/problems/reconstruct-itinerary)



## 贪心

[334. 递增的三元子序列](https://leetcode.cn/problems/increasing-triplet-subsequence/description/)

[122. 买卖股票的最佳时机 II](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/)——不限交易次数股票问题的特例解法

[134. 加油站](https://leetcode.cn/problems/gas-station/description/?favorite=2ckc81c)——难想到

[135. 分发糖果](https://leetcode.cn/problems/candy/)——两次遍历满足不同左右规则（思路难想到）



## 二分搜索

> **搜索区间与 while 条件对应关系:**
>
> 搜索区间为 [l, r]——搜索条件 l <= r
>
> 搜索区间 [l, r)——l < r
>
> 因为搜索区间为空时停止

### 典型二分

<span style="font-size:20px">寻找一相等值:</span>

```js
while (l <= r) {
  const mid = Math.floor((l + r) / 2);
  if (nums[mid] === target) {
    return mid;    
  } else if (nums[mid] < target) {
    l = mid + 1;
  } else {
    r = mid - 1;
  }
}
```

[剑指 Offer II 068. 查找插入位置](https://leetcode.cn/problems/N6YdxV/)——元素在有序数组中位置

[剑指 Offer II 070. 排序数组中只出现一次的数字](https://leetcode.cn/problems/skFtm2/)——找数组规律转换为二分问题

<span style="font-size:20px">寻找最左 (/ 右)相等元素:</span>——寻找左-(最左的大于 t 元素) / 右边界-(最右的小于 t 元素)

1. 寻找最左——区间向左收缩，正确下标 `l`
2. 寻找最右——区间向右收缩，正确下标 `r`

+ 没找到值情况——越界 || 返回下标不为 target

```js
let l = 0, r = nums.length - 1, res = [];
// 第一次二分——寻找左边界
while (l <= r) {
  const mid = Math.floor((l + r) / 2);
  if (nums[mid] === target) { // 向左收缩
#    r = mid - 1;  
  } /*...else if*/
}
// 没找到:
if (l === nums.length || target !== nums[l]) return [-1, -1];
res[0] = l;
// 第二次——寻找右边界
l = 0, r = nums.length - 1;
while (l <= r) {
  const mid = Math.floor((l + r) / 2);
  if (nums[mid] === target) { // 向右收缩
#    l = mid + 1;
  } /*...else if*/
}
// 没找到:
if (r === -1 || target !== nums[r]) return [-1, -1];
res[1] = r;
```

[34. 在排序数组中查找元素的第一个和最后一个位置](https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/)——找左边界+右边界

[剑指 Offer II 071. 按权重生成随机数](https://leetcode.cn/problems/cuyjEf/)——前缀和 + 找到第一个 >= target

### 抽象二分

**步骤：**

1. 确认搜索对象 (return)、搜索范围 (l, r 边界)、参考对象 (target)
2. 搜索范围内对搜索对象二分查找
3. 根据二分的搜索对象值与参考对象比较并调整边界

```ts
/*875.*/
// 1.搜索对象-速度,搜索范围-[1,pile最大值],参考对象-h
let l = 1, r = 1;
for (const pile of piles) r = Math.max(r, pile);
// 2.
while (l <= r) {
  let speed =  Math.floor((r + l) / 2);
  let time = 0;
  for (const pile of piles) {
    time += Math.ceil(pile / speed);
  }
  // 3.
  if (time <= h) {    // 速度快了或符合,向左收缩
    r = speed - 1;
  } else {    // 速度慢了,向右收缩
    l = speed + 1;
  }    
}
return l;
```

 [875.爱吃香蕉的珂珂](https://leetcode.cn/problems/koko-eating-bananas/)

[1011. 在 D 天内送达包裹的能力](https://leetcode.cn/problems/capacity-to-ship-packages-within-d-days/)



### 不完全有序

<img src="https://pic.leetcode-cn.com/1608987789-aGIDWv-file_1608987789057" alt="img" style="zoom:33%;" />

看作两个有序数组的拼接，切数组 2 最大值小于数组 1 的最小值

**思路 1: **——将无序看作 有序+无序，一分为二递归

**思路 2：**——分情况移动边界

1. mid 几种情况——于 left/right 在同一数组：

    ![mid值情况](https://pic.leetcode-cn.com/1608987789-igcnYQ-file_1608987789058)

2. mid 数组 1 (与 left 在同一数组) 时，target 几种情况：

    <img src="https://pic.leetcode-cn.com/1608987789-XiztGV-file_1608987789059" alt="left左" style="zoom:43%;" />

3. mid 数组 2 (与 right在同一数组) 时，target 几种情况：

    ![right右](https://pic.leetcode-cn.com/1608987789-DyzcDG-file_1608987789060)

[33. 搜索旋转排序数组](https://leetcode.cn/problems/search-in-rotated-sorted-array/)

[81. 搜索旋转排序数组 II](https://leetcode.cn/problems/search-in-rotated-sorted-array-ii/)——含重复元素。



## 排序

### 归并排序

<span style="color:blue">求逆序对：</span>

[剑指 Offer 51. 数组中的逆序对](https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/)

[315. 计算右侧小于当前元素的个数](https://leetcode.cn/problems/count-of-smaller-numbers-after-self/)——上一题的基础上新增了索引数组，对索引数组排序，从而不改变原数组情况下能获得本来元素的下标

<span style="color:blue">链表排序:</span>

[剑指 Offer II 077. 链表排序](https://leetcode.cn/problems/7WHec2/)

[剑指 Offer II 078. 合并排序链表](https://leetcode.cn/problems/vvXgSW/)——合并 k 个排序列表。每次 mergeTwo，逐步增大步长



## 滑动窗口

### 单调队列

**使用场景：**满足元素先进先出，能维护队列中最值

**框架：**

```java
function maxSlidingWindow(nums: number[], k: number): number[] {
    const window = new MonotonicQueue();    
    const res: number[] = [];
    for (let i = 0; i < nums.length; i++) {
        const cur = nums[i];
        if (i < k - 1) {    // 先填k-1个元素
            window.push(cur);
        } else {
            window.push(cur);   // 得到完整的一个窗口
            res.push(window.max()); // 获取此时窗口最大值
            window.pop(nums[i + 1 - k]);    // 弹出窗口左边
        }
    }
    return res;
};
// 单调队列
class MonotonicQueue {
    private queue = [];
    private peek() {/*...*/}

    push(val) {	// 向窗口加入元素
        // 删除比val小的元素
        while (this.queue.length > 0 && this.peek() < val) {
            this.queue.pop();
        }
        this.queue.push(val);
    }

    pop(val) {	// 弹出元素
        if (val === this.queue[0]) {    // 不在优先级队列首则是已被push时移开
            this.queue.shift();
        }
    }

    max() {return this.queue[0];}

}
```

[239. 滑动窗口最大值](https://leetcode.cn/problems/sliding-window-maximum/)——将窗口维护为单调队列



## 单调栈

**单调栈：**栈内顺序要么从大到小、要么从小到大

**使用场景：**通常一维数组，要寻找每一元素的右/左边第一个比自己大/小的元素位置时。

**本质：**空间换时间

**注意：**

+ 单调栈里存放的元素——数组下标
+ 单调栈里元素递增/递减——从栈头到栈底

**单调栈原理：**单调栈从后往前维护，对于当前元素来说，栈内只存储其后比它大的元素

<img src="https://labuladong.github.io/algo/images/%e5%8d%95%e8%b0%83%e6%a0%88/1.jpeg" alt="img" style="zoom:33%;" />

**模板：**

```java
int[] nextGreaterElement(int[] nums) {
    int n = nums.length;
    int[] res = new int[n];
    Stack<Integer> s = new Stack<>(); 
    // 倒着往栈里放
    for (int i = n - 1; i >= 0; i--) {
        // 判定个子高矮
        while (!s.isEmpty() && s.peek() <= nums[i]) {
						// 移走比它矮的
            s.pop();
        }
        // nums[i] 身后的更大元素
        res[i] = s.isEmpty() ? -1 : s.peek();
        s.push(nums[i]);
    }
    return res;
}
```

[496. 下一个更大元素 I](https://leetcode.cn/problems/next-greater-element-i/)

[739. 每日温度](https://leetcode.cn/problems/daily-temperatures/)

<span style="color:blue">柱形问题：</span>

[84. 柱状图中最大的矩形](https://leetcode.cn/problems/largest-rectangle-in-histogram)——数学思维转换为单调栈问题

> **思考过程：**
>
> 求以每根柱子为顶的最大面积——h * (两侧延申至更矮柱)
>
> 要得每个元素左边第一个小于它的元素位置，单调递增栈。而让该元素弹出的位置即右边第一个小于它的。
>
> **Tips：**
>
> 数组前后末尾增加哨兵

[剑指 Offer II 040. 矩阵中最大的矩形](https://leetcode.cn/problems/PLYXKQ/)——以每一行为基线转化成上一问题

[42. 接雨水](https://leetcode.cn/problems/trapping-rain-water/description/)——柱形图问题



## 回溯法

**递归函数：**

1. <span style="color:orange">横向遍历</span>，选择
2. <span style="color:orange">纵向递归</span>，下一元素选择
3. <span style="color:orange">回溯</span>，撤销子树处理结果(*当前 path和 used 只作用于子树* )

**框架：**

```python
def backtrack(路径, 选择列表):
    if 满足结束条件:
        res.add(路径)
        return
    
    for 选择 in 选择列表:
        做选择
        backtrack(路径, 选择列表)
        撤销选择
```

> 核心就是 for 循环里面的递归，在递归调用之前「做选择」，在递归调用之后「撤销选择」
>

**组合/子集：**

> + 递归：使用 `start` 控制树枝生长 避免产生重复子集
> + 递归终止：`i = nums.length`

```typescript
// 1.1 无重复 不复选
  function backtrack(start: number) {
    for (let i = start; i < nums.length; i++) {
      path.push(nums[i]); // 做选择
      backtrack(i + 1);
      path.pop(); // 撤销选择
    }
  }
// 2.1 有重复 不复选
  nums.sort();
  function backtrack(start: number) {
    for (let i = start; i < nums.length; i++) {
      // 剪枝逻辑，跳过值相同的相邻树枝
  #   if (i > start && nums[i] == nums[i - 1]) continue;
      path.push(nums[i]); // 做选择
      backtrack(i + 1);
      path.pop(); // 撤销选择
    }
  }
// 3.1 无重复 可复选
	backtrack(i); // 在1.1基础上修改参数传递
```

**排列:**

```js
// 1.2 无重复 不复选
  for (int i = 0; i < nums.length; i++) {
    // 剪枝
  # if (used[i]) continue;
    used[i] = true;
    track.addLast(nums[i]);
    backtrack();
    track.removeLast();
    used[i] = false;
  }
// 2.2 有重复 不复选
  nums.sort();
  function backtrack() {
    for (let i = 0; i < nums.length; i++) {
      // 剪枝，跳过值相同的相邻树枝
  #   if (// 2.1 有重复 不复选
  nums.sort();
  function backtrack(start: number) {
    for (let i = 0; i < nums.length; i++) {
      // 剪枝逻辑，跳过值相同的相邻树枝
  #   if (i > start && nums[i] == nums[i - 1]) continue;
      path.push(nums[i]); // 做选择
      backtrack();
      path.pop(); // 撤销选择
    }
  }
// 3.2 无重复 可复选——1.2基础上删除去重剪枝
```

<span style="color:blue">其他应用: </span>

[剑指 Offer II 085. 生成匹配的括号](https://leetcode.cn/problems/IDBivT/)

[剑指 Offer II 086. 分割回文子字符串](https://leetcode.cn/problems/M99OJA/)

## To 随机算法

从所有样本中抽取若干个，要求每个样本被抽到的概率相等。

### 设计数据结构

+ 高效地，等概率地随机获取元素——数组作底层容器
+ 保持数组元素的紧凑性，删除元素 O(1)——使用额外哈希表记录索引值，把待删除元素换到最后，然后 pop 掉末尾的元素
+ 数组中含空洞 (*黑名单* )——利用哈希表处理映射关系，让数组在逻辑上紧凑

[380. O(1) 时间插入、删除和获取随机元素](https://leetcode.cn/problems/insert-delete-getrandom-o1/)——不定长数组 + 哈希

[710. 黑名单中的随机数](https://leetcode.cn/problems/random-pick-with-blacklist/)——哈希处理黑名单数的映射



### 定长数据流——哈希表预处理

### 不定长数据流——蓄水池抽样

> 无限序列中等概率选取元素

**思路：**从前往后处理每个样本，每个样本成为答案的概率为 1/i ，其中 i 为样本编号 (编号从1开始)，最终 (i = n) 可以确保每个样本成为答案的概率均为 1/ n

**步骤：**

1. 对每一个 nums[i] = target 的下标执行 [0, i)  的随机操作

    > 每个样本（*满足条件——nums[i] = target*）成为答案的概率为 1 / i

2. 随机结果为 0 时，将该下标作为最新的答案候选

3. 返回最后一次覆盖答案为本次抽样结果

    > 最终每个样本概率为 1 / n



### 带权重的随机选择

前缀和 + 二分

<span style="color:blue">以权重数组 w: [1,2,3] 为例 :</span>

1. 构造权重前缀和序列 [1, 3, 6]
2. 随机数 p 从 [1, 10) 抽取
3. 在 w[] 找到第一个 >= p 值的下标 

[剑指 Offer II 071. 按权重生成随机数](https://leetcode.cn/problems/cuyjEf/)



## To 数组区间

**不同题目 方案的选择：**

+ <span style="color:blue">数组不变，求区间和：</span>「前缀和」、「树状数组」、「线段树」
+ <span style="color:blue">频繁修改某个数（单点），求区间和：</span>「树状数组」、「线段树」
+ <span style="color:blue">频繁修改某个区间，输出最终结果：</span>「差分」
+ <span style="color:blue">频繁修改某区间，求区间和：</span>「线段树」、「树状数组」（看修改区间范围大小）
+ <span style="color:blue">频繁将某个区间变成同一个数，求区间和：</span>「线段树」、「树状数组」（看修改区间范围大小）



### 前缀和

[剑指 Offer II 012. 左右两边子数组的和相等](https://leetcode.cn/problems/tvdfij/)——利用前缀和获取子数组和关系

[238. 除自身以外数组的乘积](https://leetcode.cn/problems/product-of-array-except-self/)——左右前缀乘积数组



#### 前缀和 + 哈希表

**步骤：**

1. Map 维护各前缀和及其出现次数，pre 记录前缀和 [0,i]，res 记录满足 pre - key = k 的次数

    > **重点：**目标等式 pre[i] - pre[key] = k => 求得 pre[key] 在哈希中的映射

2. 遍历数组
    1. 更新 pre
    2. 存在 key = k-pre：res 累加该 pre 出现次数
    3. 统计当前 pre 出现次数

> **补充：**
>
> pre 的意义随题意变化，不一定为 [0, i] 的和

**模板：**

```java
/* 560. */
function subarraySum(nums: number[], k: number): number {
    let preSum = 0, res = 0;
    let map = new Map([[0, 1]]);
    for (const n of nums) {
      	// 1.
        preSum += n;
      	// 2.
        if (map.has(preSum - k)) {  
            res += map.get(preSum - k);
        }
      	// 3.
        if (map.has(preSum)) {
            map.set(preSum, map.get(preSum) + 1);
        } else {
            map.set(preSum, 1);
        } 
    }
    return count;
};
```

**例题：**

[1.两数之和 - 力扣（LeetCode）](https://leetcode.cn/problems/two-sum/submissions/)——pre 理解为当前数值

[560.和为k的子数组 - 力扣（LeetCode）](https://leetcode.cn/problems/subarray-sum-equals-k/)——pre 为 [0, i] 的和

[1248. 统计「优美子数组」- 力扣（LeetCode）](https://leetcode.cn/problems/count-number-of-nice-subarrays/submissions/)——pre 统计奇数出现次数

[剑指 Offer II 011. 0 和 1 个数相同的子数组](https://leetcode.cn/problems/A1NYOS/)——0 看作 -1，求 pre[i] - pre[target] = 0

[525. 连续数组](https://leetcode.cn/problems/contiguous-array/)——不明显的满足条件



#### 前缀和数组

> **适用场景:**原始数组不会被修改的情况下，频繁查询某个区间的累加和

[303. 区域和检索 - 数组不可变](https://leetcode.cn/problems/range-sum-query-immutable/)——快捷计算区间元素和 `preSum[right + 1] - preSum[left]`

> preSum[i] = [0, i) 的和

[304. 二维区域和检索 - 矩阵不可变](https://leetcode.cn/problems/range-sum-query-2d-immutable/)

> <img src="https://labuladong.github.io/algo/images/%e5%89%8d%e7%bc%80%e5%92%8c/5.jpeg" alt="img" style="zoom:25%;" />
>
> 以 preSum\[][] = 以原点为顶点矩阵元素和 



### 差分数组

> **适用场景:** 频繁对原始数组的某个区间的元素进行增减

+ **图解：**

    <img src="https://labuladong.github.io/algo/images/%e5%b7%ae%e5%88%86%e6%95%b0%e7%bb%84/2.jpeg" alt="img" style="zoom:25%;" />

    diff 反推 nums——<span style="color:orange">nums[i] =  nums[i - 1] + diff[i]</span>

    Eg：[1, 3] 元素全部加 3，只需让 `diff[i] + 3`，`diff[j + 1] - 3`

    > `diff[i] + 3` —— nums [i, ...] 所有的元素加 3
    >
    > `diff[j + 1] - 3` —— nums [j+1, ...] 所有元素减 3
    >
    > 结果——[i, j] 所有元素加 3

    <img src="https://labuladong.github.io/algo/images/%e5%b7%ae%e5%88%86%e6%95%b0%e7%bb%84/3.jpeg" alt="img" style="zoom:25%;" />

+ **increment 方法:**

    ```java
    /* 区间 [i, j] 均 + val */
    public void increment(int i, int j, int val) {
      diff[i] += val;
      if (j + 1 < diff.length) {
        diff[j + 1] -= val;
      }
    }
    ```

**实例：**

> 看到题目有关于三元数组 [left, right, val] 的表述时想到差分数组

[370. 区间加法](https://leetcode.cn/problems/range-addition/)——差分数组直接应用

变种：

[1109. 航班预订统计](https://leetcode.cn/problems/corporate-flight-bookings/)——由参得出 diff 后反推出 nums

[1094. 拼车](https://leetcode.cn/problems/car-pooling/)——由参得出 diff 后反推出 nums



### 线段树

> **选择用线段树：**代码很长，而且常数很大，实际表现不算很好。在不得不用的时候才考虑「线段树」。

**Status:**

```java
//区间信息分别对应 左端点最大子段和 右端点最大子段和 最大子段和 区间和（不一定每题都需要这些字段
public class Status { int lSum, rSum, mSum, iSum;}
```

**思路：**

对每一个区间的检索分治为 [left, mid], (mid, right]。再通过分治返回的 `subL`, `subR` `Status` 得到当前区间的 `Status`



[53. 最大子数组和](https://leetcode.cn/problems/maximum-subarray)



## To 括号匹配

[20. 有效的括号](https://leetcode.cn/problems/valid-parentheses/)——{} () [] 的匹配，利用 ASCII 差的绝对值

[678. 有效的括号字符串](https://leetcode.cn/problems/valid-parenthesis-string/)——'('  ')'  '*' 带有万能字符 * 的匹配



## To 田忌赛马

[870. 优势洗牌](https://leetcode.cn/problems/advantage-shuffle/)



## 扫描线

一般被用来解决图形面积，周长等问题



[218. 天际线问题](https://leetcode.cn/problems/the-skyline-problem/)——建筑轮廓。扫描 + 优先队列

> 每一座建筑的左边缘信息只被用作加入优先队列时的依据，当其加入优先队列后，我们只需要用到其高度信息（对最大高度有贡献）以及其右边缘信息（弹出优先队列的依据）



## 数学分析

[754. 到达终点数字](https://leetcode.cn/problems/reach-a-number/description/)



# Other

## ACM 模式

```java
import java.util.*;
public class Main{
   public static void main(String[] args){
   		Scanner sc = new Scanner(System.in);
      while (sc.hasNext()) {
      	String str = sc.nextLine();
        String[] arr = str.split(" ");
      }
   }        
}
```

```js
while (line = readline()) {	// 任意行数输入
  const arr = line.split(' ');
}
```



# REF

[代码随想录](https://programmercarl.com/)

[labuladong 的算法小抄](https://labuladong.github.io/algo/)

[《剑指Offer：专项突破版. 数据结构与算法名企面试题精讲》 - 何海涛]()