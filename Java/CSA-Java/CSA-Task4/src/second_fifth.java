import java.util.Scanner;

public class second_fifth {
    public static void main(String[] args) {
        score();

        average();

        MyDate birth = new MyDate(2002, 10, 12);
        Employee emp = new Employee("Jim", 100, birth.showBirthday());
        emp.myToString();
    }

    public static void score() {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入分数：");
        int score = scan.nextInt();
        if (score <= 100 && score >= 0) {
            System.out.println("成绩为：" + score);
        } else {
            System.out.println("分数必须再 0——100 之间！");
        }
    }

    public static void average() {
        Scanner scan = new Scanner(System.in);
        System.out.println("计算N个整数平均值，请输入N的值为：");
        int n = scan.nextInt();
        while (n < 0) {
            System.out.println("N必须是正数或者 0！");
            n = scan.nextInt();
        }
        // 输入：
        int count = 0, sum = 0;
        while (count++ < n) {
            System.out.print("输入第" + count + "个整数：");
            sum += scan.nextInt();
        }
        // 打印平均值：
        System.out.println("平均值为：" + sum / n);
    }

    abstract static class Person {
        public abstract void earnings();
    }
    public static class Employee extends Person {
        private String name;
        private int number;
        private String birthday;
        public Employee(String name, int number, String birthday) {
            this.name = name;
            this.number = number;
            this.birthday = birthday;
        }
        void myToString() {
            System.out.printf("%s %d ", this.name, this.number);
            System.out.printf("%s", this.birthday);
        }
        @Override
        public void earnings() {
            System.out.println("earnings:" + this.number);
        }
    }
    public static class MyDate {
        public int year, month, day;
        public MyDate(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        public String showBirthday() {
            return this.year + "-" + this.month + "-" + this.day;
        }
    }
}



