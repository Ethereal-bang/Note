import java.util.ArrayList;
import java.util.Scanner;

public class Answer {
    //第三题的输入处理
    public static int getN() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入 N 的值");
        int n = 0;
        try {
            n = scanner.nextInt();
            if (n < 0) {
                throw new Exception();
            } else {
                return n;
            }
        } catch (Exception e) {
            System.out.println("N 必须是正数或者 0");
            return getN();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("<-------第一题------->");
//        TODO:在此编写第一题测试代码
        UseCompute use = new UseCompute();
        use.useCom(new Add(), 1, 2);
        use.useCom(new Sub(), 3, 4);
        use.useCom(new Mtp(), 5, 6);
        use.useCom(new Div(), 0, 0);
        use.useCom(new Div(), 7, 8);
        System.out.println("<-------第二题------->");
        System.out.println("请输入分数：");
        try {
            Double x = scanner.nextDouble();
            if (x < 0 || x > 100) {
                throw new Exception();
            } else {
                System.out.println(x);
            }
        } catch (Exception e) {
            System.out.println("分数必须在 0—100 之间");
        }
        System.out.println("<-------第三题------->");
        int n = getN();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += scanner.nextInt();
            n--;
        }
        if (n == 0) {
            System.out.println(0);
        } else {
            System.out.println(sum / n);
        }
        System.out.println("<-------第四题------->");
        MyDate mydate = new MyDate(2021, 11, 25);
        //抽象类的匿名子类实例化
        Employee employee = new Employee("wsd", 10086, mydate) {
            @Override
            double earnings() {
                return this.getNumber();
            }
        };
        System.out.println(employee);
        System.out.println("<-------第五题------->");
        //leetcode.792题，可以自行查看题解，因为不要求复杂度，使用暴力解法也可以
        String s = "abcde";
        String[] words = new String[]{"a", "bb", "acd", "ace"};
        int ans = 0;
        ArrayList<node>[] heads = new ArrayList[26];
        for (int i = 0; i < heads.length; i++) {
            heads[i] = new ArrayList<>();
        }
        for (String word : words) {
            heads[word.charAt(0) - 'a'].add(new node(word, 0));
        }
        for (char ch : s.toCharArray()) {
            ArrayList<node> old = heads[ch - 'a'];
            heads[ch - 'a'] = new ArrayList<node>();

            for (node no : old) {
                no.index++;
                if (no.index == no.word.length()) {
                    ans++;
                } else {
                    heads[no.word.charAt(no.index) - 'a'].add(no);
                }
            }
            old.clear();
        }
        System.out.println(ans);
    }
}

class node {
    String word;
    int index;

    public node(String word, int index) {
        this.word = word;
        this.index = index;
    }
}

interface Compute {
    int computer(int n, int m);
}

class Add implements Compute {
    public Add() {
    }

    @Override
    public int computer(int n, int m) {
        return n + m;
    }
}

class Sub implements Compute {
    @Override
    public int computer(int n, int m) {
        return n - m;
    }
}

class Mtp implements Compute {
    @Override
    public int computer(int n, int m) {
        return n * m;
    }
}

class Div implements Compute {
    @Override
    public int computer(int n, int m) {
        return m != 0 ? (n / m) : Integer.MAX_VALUE;
    }
}

class UseCompute {
    public UseCompute() {
    }

    public void useCom(Compute com, int one, int two) {
        System.out.println(com.computer(one, two));
    }
}

abstract class Employee {
    private String name;
    private double number;
    private MyDate birthday;

    abstract double earnings();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public MyDate getBirthday() {
        return birthday;
    }

    public void setBirthday(MyDate birthday) {
        this.birthday = birthday;
    }

    public Employee() {
    }

    public Employee(String name, double number, MyDate birthday) {
        this.name = name;
        this.number = number;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "姓名：" + name +
                ", 余额：" + number +
                ", 生日：" + birthday.year + "年" + birthday.month + "月" + birthday.day + "日" +
                '}';
    }
}

class MyDate {
    int year;
    int month;
    int day;

    public MyDate() {
    }

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
}