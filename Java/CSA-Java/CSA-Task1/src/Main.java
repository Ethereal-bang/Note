public class Main {
    public static void showTriangle(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = n; j >= i; j--) {  // 输出前面的空格
                System.out.print(" ");
            }
            for (int j = 1; j <= 2*i-1; j++) {  // 输出*
                System.out.print("*");
            }
            for (int j = n; j >= i; j--) {  // 输出前面的空格
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    public static void reverseString(String s) {
        char[] chars = s.toCharArray(); // 字符串转为数组
        // 翻转数组
        int len = chars.length;
        for (int i = 0; i < len / 2; i++) {
            char x = chars[i];
            char y = chars[len-1-i];
            chars[i] = y;
            chars[len-1-i] = x;
        }
        String s1 = String.valueOf(chars);  // 字符数组转为字符串
        System.out.println(s1);
    }
    public static void isPalindrome(int num) {
        boolean flag = false;

        // 取模翻转整数
        int n, sum = 0, initNum = num;
        while (true) {
            n = initNum % 10;
            sum = sum * 10 + n;
            initNum /= 10;
            System.out.println("1sum:" + sum);
            if (initNum == 0) {
                break;
            }
        }
        // 比较翻转后与原数
        if (sum == num) {
            flag = true;
        }

        if (flag) {
            System.out.println("是的");
        } else {
            System.out.println("不是");
        }
    }
    public static void main(String[] args){
        System.out.println("1:B, 2:B, 3:D, 4:C");
        showTriangle(4);
        reverseString("Hello world");
        isPalindrome(121);
    }
}
