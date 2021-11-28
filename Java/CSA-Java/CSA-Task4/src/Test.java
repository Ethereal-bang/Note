public class Test {
    public static void main(String[] args) {
        UseCompute c = new UseCompute();
        c.useCom(new Divide(), 1, 0);
    }
    interface Compute {
        int computer(int n, int m);
    }
    public static class Add implements Compute {
        @Override
        public int computer(int n, int m) {
            return n + m;
        }
    }
    public static class Reduce implements Compute {
        @Override
        public int computer(int n, int m) {
            return n - m;
        }
    }
    public static class Multiply implements Compute {
        @Override
        public int computer(int n, int m) {
            return n * m;
        }
    }
    public static class Divide implements Compute {
        @Override
        public int computer(int n, int m) {
            if (m == 0) {
                System.out.println("参数m不能为0哟");
                return 0;
            }
            return n / m;
        }
    }
    public static class UseCompute {
        public void useCom(Compute com, int one, int two) {
            System.out.println(com.computer(one, two));
        }
    }
}

