import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("<-------第一题------->");
        Monkey monkey = new Monkey("Mon");
        People person = new People("person");
        monkey.speak();
        person.speak();
        person.think();

        System.out.println("<-------第二题------->");
        Car car = new Car(4, 1150.0, 3);
        Truck trunk = new Truck(6, 15000.0, 1, 3000.0);
        car.showInfo();
        System.out.println("------------------------");
        trunk.showInfo();

        System.out.println("<-------第三题------->");
        String ret3 = getSum("13829579081298345918257", "438823897418920918472193");
        System.out.println("输出：c=\"" + ret3 + "\"");

        System.out.println("<-------第四题------->");
        System.out.printf("路径条数为：%d\n", uniquePaths(3, 7));

        System.out.println("<-------第五题------->");
        System.out.println("输出：" + longestCommonPrefix(new String[]{"ab", "a"}));
    }
    public static String getSum(String a, String b) {
        List<Integer> la = new ArrayList<Integer>();
        List<Integer> lb = new ArrayList<Integer>();
//        String c = "";
        // 将a、b分别存入数组la、lb
        for(int i = a.length()-1;i >= 0;--i){
            la.add(a.charAt(i)-'0');    // -'0'使char变Integer
        }
        for(int i = b.length()-1;i >= 0;--i){
            lb.add(b.charAt(i)-'0');
        }

        System.out.printf("输入：a=\"%s\",b=\"%s\"\n", a, b);
        // la、lb逐位相加存入lc
        StringBuffer c = new StringBuffer("");
        int i = 0, j = 0, carry = 0;  // carry表示是否进位
        while (i <= a.length() - 1 || j <= b.length() - 1) {
            int temp = carry;
            int na = i <= a.length() - 1 ? la.get(i++) : 0;    // 为什么与0比较：ab位数不同时有一个会更早超出遍历范围
            int nb = j <= b.length() - 1 ? lb.get(j++) : 0;
            int add = na + nb;    // 相加结果

            if (add > 9) {  // 相加后如要进位：
                add %= 10;  //取余
                carry = 1;
            } else {
                carry = 0;
            }
            temp += add;
            c.append(temp);
        }
        // 加完最后一位后：
        if (carry == 1) {
            c.append(1);
        }

        return new String(c.reverse());
    }

    public static int uniquePaths(int m, int n) {
        System.out.printf("m=%d, n=%d\n", m, n);

        int[][] dp = new int[m][n]; // 1. dp[i][j]：到(i,j)坐标路径个数
        // 2. 递推公式：dp[i][j] = dp[i-1][j]+dp[i][j-1] (正上方和左方）
        // 3. 初始化：最左方和最上方为1
        for (int i = 0; i <= m - 1; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i <= n - 1; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {   // 从左到右层层遍历确保dp[i][j]的上、左方都有数值
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    public static String longestCommonPrefix(String[] strs) {
        String ans = "";

        // 打印字符串数组：
        System.out.print("输入：strs = [");
        for (String str : strs) {
            System.out.print(str + ",");
        }
        System.out.print("]\n");

        // 思路：取第一个字符串依次与其余字符串逐一比较得到的公共前缀最小值
        int lcp = strs[0].length() - 1;  // 总的公共前缀
        // 遍历其余字符串
        for (int i = 1; i < strs.length; i++) {
            int index = 0;    // 与该字符串的公共前缀下标
            for (; index <= lcp && index < strs[i].length(); index++) {   // 不用比较整个字符串
                if (strs[0].charAt(index) != strs[i].charAt(index)) {   // 停下的位置即与该字符串比较得到的最长公共前缀
                    System.out.println("index:" + index);
                    break;
                }
            }
            lcp = Math.min(lcp, index - 1); // 取更小的那个进入下一轮比较
            if (lcp < 0)   // 第一个字符就不一样：
                return " "; // 输出""为啥不显示?
        }

        ans = strs[0].substring(0, lcp + 1);    // 因为subString输出不包括endIndex
        return ans;
    }

    public static class Monkey {
        static String name;
        // 构造方法：
        public Monkey(String s) {}
        // 方法：
        public void speak() {
            System.out.println("咿咿呀呀......");
        }
    }
    public static class People extends Monkey{
        public People(String s) {
            super(s);
        }

        @Override
        public void speak() {
//        super.speak();    // 调用父类方法
            System.out.println("小样的，不错嘛！会说话了！");
        }
        public void think() {
            System.out.println("别说话！认真思考！");
        }
    }

    public static class Vehicle {
        public int wheels;
        public double weight;
        public Vehicle(int wheels, double weight) {
            this.wheels = wheels;
            this.weight = weight;
        }
        public void showInfo() {
            System.out.printf("车轮的个数是%d 车重：%.1f\n", this.wheels, this.weight);
        }
    }
    public static class Car extends Vehicle {
        static int loader = 5;
        private int actualLoader;
        public Car(int wheels, double weight, int actualLoader) {
            super(wheels, weight);
            super.showInfo();
            this.actualLoader = actualLoader;
        }
        public void showInfo() {
            if (loader > this.actualLoader) {
                System.out.printf("这是一辆小车，能载%d人,实载%d人\n", loader, this.actualLoader);
            } else {
                System.out.printf("这是一辆小车，能载%d人,实载%d人,你超员了！！！\n", loader, this.actualLoader);
            }
        }
    }
    public static class Truck extends Vehicle {
        static int loader = 3;
        static double payload = 5000;
        private int actualLoader;
        private double actualPayload;
        public Truck(int wheels, double weight, int loader, double payload) {
            super(wheels, weight);
            this.actualLoader = loader;
            this.actualPayload = payload;
        }
        public void showInfo() {
            super.showInfo();
            if (loader > this.actualLoader) {
                System.out.printf("这是一辆卡车，能载%d人,实载%d人\n", loader, this.actualLoader);
            } else {
                System.out.printf("这是一辆卡车，能载%d人,实载%d人,你超员了！！！\n", loader, this.actualLoader);
            }
            if (payload > this.actualPayload) {
                System.out.printf("这是一辆卡车,核载%.1fkg,你已装载%.1fkg\n", payload, this.actualPayload);
            } else {
                System.out.printf("这是一辆卡车,核载%.1fkg,你已装载%.1fkg,你超载了！！！\n", payload, this.actualPayload);
            }
        }
    }
}
