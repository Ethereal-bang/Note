import java.lang.reflect.Array;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        // 题1
        int ret1 = Solution.reverse(0);
        // 题2
        int ret2 = Solution.climbStairs(1);
        // 题3
        List<List<Integer>> ret3 = subsets(new int[] {1, 2, 3});

        System.out.print(ret1 + "\n" + ret2 + "\n" + ret3);
    }

    // 整数反转
    public static int reverse(int x) {
        int last = 0, ret = 0;
        while (x != 0) {
            last = x % 10; // 获取最低位
            x /= 10;

            ret = ret * 10 + last;
        }

        return ret;
    }

    // 爬楼梯
    public static int climbStairs(int steps) {
        // dp[i]——爬到i层的方法数
        int[] dp = new int[steps + 2]; // 为了steps=1时下面初始化[2]不报错
        // 初始化：
        dp[1] = 1;
        dp[2] = 2;
        // 递推公式：dp[i] = dp[i-1] + dp[i - 2];
        for (int i = 3; i <= steps; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[steps];
    }

    // 求子集
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>(); // 存放结果集合
        List<Integer> path = new ArrayList<>();    // 存放当前集合
        dfs(res, path, nums, 0);
        return res;
    }

    public static void dfs(List<List<Integer>> res, List<Integer> path, int[] nums, int startIndex) {
//        System.out.println("res:"+ res + ";path:" + path + ";startIndex:" + startIndex);
        // 保存每个当前节点：
        res.add(new ArrayList<>(path));
        // 从上一层节点之后（startIndex）开始遍历所有
        for (int i = startIndex; i < nums.length; i++) {    // 横向遍历
            // 添加到当前路径
            path.add(nums[i]);
            // 递归下一层
            dfs(res, path, nums, i + 1);    // 深度优先遍历
            path.remove(path.size() - 1);   // 回滚继续遍历
        }
    }

}
