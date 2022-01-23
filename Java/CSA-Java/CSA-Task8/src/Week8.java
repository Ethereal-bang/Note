import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;

public class Week8 {
    static Random random = new Random();

    public static void test() {
        System.out.println(task1("abbc", "dog cat cat fish"));
        System.out.println(task2(new int[]{1, 2, 2, 3, 0}));
        for (int i = 0; i < 5; i++) {
            int target = random.nextInt(15) - 3;
            System.out.println("target:" + target + "\tresult" + task3(new int[]
                    {0, 4, 5, 6, 8}, target));
        }
    }

    public static void main(String[] args) {
        test();
    }

    public static boolean task1(String pattern, String str) {
        HashMap<Character, String> hashMap = new HashMap<>();
        String[] words = str.split("\\s");  // 用空格将str分隔为单词数组
        if (pattern.length() != words.length) { // 保证长度相同
            return false;
        }
        for (int i = 0; i < pattern.length(); i++) {
            char character = pattern.charAt(i);
            String word = words[i];
//            System.out.println(character + words[i]);
            // 若该key、value未存入哈希，则将character-word对应关系存入hash
            if (!hashMap.containsKey(character) && !hashMap.containsValue(word)) {
                hashMap.put(character, word);
            } else {    // 若已存入对应关系，将其与word匹配
                if (!Objects.equals(hashMap.get(character), word)) {   // 若不满足
                    return false;
                }
            }
        }
        return true;
    }

    public static int task2(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashSet.contains(nums[i])) {
                return nums[i];
            } else {
                hashSet.add(nums[i]);
            }
        }
        return 0;
    }

    public static int task3(int[] nums, int target) {
        return recurse(nums, target, 0, nums.length - 1);
    }
    static int recurse(int[] nums, int target, int start, int end) { // 递归二分查找
        int midIndex = (start + end) / 2;
//        System.out.println("start: " + start + ", end: " + end + ", mid" + midIndex);
        if (start > end)
            return -1;
        else if (nums[midIndex] == target)
            return midIndex;
        else if (nums[midIndex] < target)
            return recurse(nums, target, midIndex + 1, end);
        else
            return recurse(nums, target, start, midIndex - 1);
    }
}