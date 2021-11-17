public class Main {
    public static void main(String[] args) {
        printTriangle(6);
        zeroToRear(new int[]{1, 2, 0, 3, 4});
        generateMatrix(2);
    }
    public static void printTriangle(int n) {   // 杨辉三角
        int[][] tri = new int[n][n];
        // 数组赋值
        for (int i = 0; i <= n - 1; i++) {
            for (int j = 0; j <= i; j++) {
                if (i == 0 || j == 0 || i == j) {   // 最外层三角为1
                    tri[i][j] = 1;
                } else {
                    tri[i][j] = tri[i - 1][j - 1] + tri[i - 1][j];
                }
            }
        }

        // 输出
        for (int i = 0; i <= n - 1; i++) {
            // 输出每行开头空格
            for (int j = n - 1; j >= i; j--) {
                System.out.print("  ");
            }
            // 输出数字
            for (int j = 0; j <= i; j++) {
                System.out.printf("%4d", tri[i][j]);
            }
            System.out.println();
        }
    }
    public static void zeroToRear(int[] nums) {
        System.out.println("初始数组");
        for (int num : nums) {
            System.out.print(num+" ");
        }

        int len = nums.length;
        for (int i = 0; i <= len - 1; i++) {
            if (nums[i] == 0) {  // 当匹配到元素0
                // 找出一个不为0的索引
                int j = i;
                while (nums[j] == 0) {
                    if (j == len - 1) { // 说明0已全部移到后面
                            break;
                    }
                    j++;
                }
                // 交换
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }

        System.out.println("\n新数组");
        for (int num : nums) {
            System.out.print(num+" ");
        }
    }
    public static void generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int[][] direction =  {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};    // 用来控制行，列索引移动
        int x = 1, i = 0, j = 0, d = 0; // d用来记录direction索引
        matrix[i][j] = x++;
        while (x <= n * n) {
            i += direction[d][0];
            j += direction[d][1];
            // 超出边界或已被填充过时
            if (i >= n || j >= n || i < 0 || j < 0 || matrix[i][j] != 0) {
                i -= direction[d][0];
                j -= direction[d][1];
                if (++d ==  4) {    // d超出direction索引
                    d = 0;
                }
                i += direction[d][0];
                j += direction[d][1];
            }
            matrix[i][j] = x++;
        }

        // 输出
        System.out.println();
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
}
