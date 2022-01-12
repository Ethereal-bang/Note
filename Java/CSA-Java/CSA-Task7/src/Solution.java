import java.util.*;

public class Solution {
    public static void main(String[] args) {
        // No.1
        int[] res1 = new Solution().nextGreaterElement(new int[]{1,3,5,2,4}, new int[]{6,5,4,3,2,1,7});
        // No.2
        boolean res2 = new Solution().validateStackSequences(new int[]{1,2,3,4,5}, new int[]{4,3,5,1,2});
        // No.3
        int res3 = new Solution().sumOfUnique(new int[]{1, 2, 3, 2});

        System.out.println(Arrays.toString(res1) + "" + res2 + "" + res3);
    }
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<Integer>();
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

        // 1.用单调栈遍历nums2
        for (int num : nums2) {
            // 栈顶元素<num元素 => num为栈顶元素的nextGreaterElement
            while (!stack.isEmpty() && stack.peek() < num) {
                // 2.持续弹出，对应结果存入哈希
                hashMap.put(stack.pop(), num);
            }
            stack.push(num);
//            System.out.println("stack " + stack);
//            System.out.println("hash " + hashMap);
        }
        // 3.按hash结果存入结果数组
        // 结果数组:
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            //getOrDefault为不存在的key返回指定默认值
            res[i] = hashMap.getOrDefault(nums1[i], -1);
        }
        return res;
    }
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0, j = 0;   // 分别表示pushed和popped索引
        while (i < pushed.length) {
            // 1.先逐个push
            stack.push(pushed[i++]);
            // 2.栈顶与popped[j]匹配时一直弹出直到不匹配
            while (!stack.isEmpty() && popped[j] == stack.peek()) {
                stack.pop();
                j++;
            }
//            System.out.println(stack);
        }
        return (stack.isEmpty() ? true : false);
    }
    public int sumOfUnique(int[] nums) {
        int sum = 0;
        // 存入hash表
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) { // ?
            if (!map.containsKey(nums[i])) {    // 索引不存在说明map中不存在此元素:
                map.put(nums[i], 1);    // 用key记录数值
            } else {   // 说明该数不是出现恰好一次的元素
                map.put(nums[i], -1);
            }
        }
        // 遍历哈希
        for (int i :map.keySet()) {
            if (map.get(i) == 1)  // value为1的是不重复元素
                sum += i;
        }
        return sum;
    }
}
