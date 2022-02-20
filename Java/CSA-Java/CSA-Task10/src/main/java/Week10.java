import org.junit.Test;

import java.util.*;

public class Week10 {
    static Random random = new Random();
    public static void printArr(int[] nums){
        System.out.println(Arrays.toString(nums));
    }
    public static void test(){
        int[]nums1=new int[random.nextInt(20)+1];
        for(int i=0;i<nums1.length;i++)
            nums1[i]=random.nextInt(30)-10;
        printArr(nums1);
        printArr(task1(nums1));
        System.out.println("======================");
        int[]nums2=new int[random.nextInt(20)+1];
        for(int i=0;i<nums2.length;i++)
            nums2[i]=random.nextInt(3);
        printArr(nums2);
        task2(nums2);
        printArr(nums2);
    }
    public static void main(String[] args) {
        test();
    }
    public static int[] task1(int[]nums) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[nums.length];

        for (int i = 0 ; i < nums.length; i++) {
            // 碰到大于栈顶的num，说明num为栈顶的下一更大数
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                int index = stack.pop();
                res[index] = i - index; // 存入下标差
            }
            stack.add(i);   // 存入下标，便于处理res下标
        }

        return res;
    }
    public static void task2(int[] nums) {
        int[] cnt = new int[3]; // 初始化0
        // 依次统计012个数
        for (int i = 0; i < cnt.length; i++) {
            // 遍历该数出现次数
            for (int right = 0; right < nums.length; right++) {
                if (nums[right] == i) {
                    cnt[i]++;
                }
            }
        }

        // 依次按次数填0、1、2
        int left = 0;
        // 遍历0、1、2
        for (int i = 0; i < cnt.length; i++) {
            // 该数的次数
            for (int c = 0; c < cnt[i]; c++) {
                nums[left++] = i;
            }
        }
    }
}