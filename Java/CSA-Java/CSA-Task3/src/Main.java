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
        String ret = getSum("88888888888888888", "25461213124533465");
        System.out.println(ret);
        System.out.println("<-------第四题------->");
        System.out.println("<-------第五题------->");

    }
    public static String getSum(String a, String b) {
        if (a.length() < b.length()) {
            String temp = b;
            b = a;
            a = temp;
        } // 默认a更长，便于后面操作
        List<Integer> la =  new ArrayList<Integer>();
        List<Integer> lb =  new ArrayList<Integer>();
        StringBuffer c = new StringBuffer("");
        List<Integer> lc =  new ArrayList<Integer>();
        // 将a、b分别存入数组la、lb
        for (int i = a.length() - 1; i >= 0; --i) {
            la.add(a.charAt(i) - '0');  // -'0'使char变Integer
        }
        for (int i = b.length() - 1; i >= 0; --i) {
            lb.add(b.charAt(i) - '0');
        }
        // la、lb逐位相加存入lc
        for (int i = a.length() - 1; i >= 0; --i) {
            for (int j = b.length() - 1; j >= 0; --j) {
                int sum = a.charAt(i) - '0' + (b.charAt(j) - '0');  // 每位相加的结果
                boolean flag = false;   // 标志是否需进位
                if (sum >= 10) {
                    flag = true;
                    sum %= 10;  // 十位数变个位
                }
                lc.add(sum);
            }
        }
        return new String(c);
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
