import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 5, 4};
//        Sort.bubbleSort(arr);
//        Sort.insertSort(arr);
//        Sort.quickSort(arr, 0, 4);
        arr = Sort.mergeSort(arr);
//        Sort.heapSort(arr);

        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr) {
        int len = arr.length;
        // 外层——比较len-1次
        for (int i = 0; i < len - 1; i++) {
            // 内层——每一遍循环需两两比较的次数
            for (int j = 0; j < len - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void insertSort(int[] arr) {
        // 从下标1开始为待插入数列:
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];  // 记录待插数
            // 从后往前遍历找到应插位置j——j-1对应小于该数的位置:
            int j = i;
            while (j > 0 && arr[j - 1] > temp) {    // 首先满足j>0
                arr[j] = arr[j - 1];  // 腾位置——待插位置之后的元素均后移一位
                j--;
            }
            // 插入：
            arr[j] = temp;
        }
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right || arr.length <= 1) { // 递归的终止条件
            return;
        }
        int mid = partition(arr, left, right);
        // 递归对基准左右两边重复以上操作
        quickSort(arr, left, mid - 1);
        quickSort(arr, mid + 1, right);
    }
    // 分治——使基准数左边全是小于的数、右边全是大于的数
    private static int partition(int[] arr, int left, int right) { // 分治法
//        System.out.println("left=" + left + ",right=" + right);
        int temp = arr[left];   // 基准数
        while (left < right) {
            // 由后向前找比基准数小的数填入坑arr[left]:
            while (temp <= arr[right] && left < right) {
                right--;
            }
            // 基准数大于arr[right]，填坑入left
            if (left < right) { // 不加这个条件left可能导致left>right使最后赋值temp的left位置错误
                arr[left] = arr[right];
                left++;
            }

            // 由前向后找比基准数大的数填入坑a[right]
            while (temp >= arr[left] && left < right) {
                left++;
            }
            if (left < right) {
                arr[right] = arr[left];
                right--;
            }
        }
        // left==right说明已经分治完毕
        arr[left] = temp;
//        System.out.println("mid=" + left + ",arr=" + Arrays.toString(arr));
        return left;    // 返回基准数下标
    }

    public static int[] mergeSort(int[] arr) {
        // 递归终止：
        if (arr.length <= 1) {
            return arr;
        }
        // 切割为子序列：
        int middle = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);
        // 递归切割合并：
        return mergeTwo(mergeSort(left), mergeSort(right));
    }
    // 合并两个有序列表
    private static int[] mergeTwo(int[] left, int[] right) {
        int[] res = new int[left.length + right.length];    // 申请空间

        // 依次选取两序列中较小值不断放入新数组：
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                res[k++] = left[i++];
            } else {
                res[k++] = right[j++];
            }
        } // 当left或right数组取完后while循环停止
        // 存入未完数组剩余元素：
        while (i < left.length) {
            res[k++] = left[i++];
        }
        while (j < right.length) {
            res[k++] = right[j++];
        }

        return res;
    }

    // 堆排序
    public static void heapSort(int[] arr) {
        for (int cnt = 1; cnt < arr.length; cnt++) {    // cnt记录第几次构建最大堆
            // 1.构建最大堆:
            buildMaxHeap(arr, arr.length - cnt + 1);
            // 2.将最大元素交换到末尾:
            swap(arr, 0, arr.length - 1 - cnt + 1);
            // 3.每构建一次就相当于排好一个最大值
            System.out.println("heapSort, " + Arrays.toString(arr));
        }
    }
    /**
     * 将一看作完全二叉树的数组构建为最大堆
     * @param size 节点个数——待调整的数组长度，不一定等于arr.length
     * */
    private static void buildMaxHeap(int[] arr, int size) {
        // 从最后一个元素开始按大小调整父子结点 构建最大堆
        for (int i = size - 1; i >= 0; i--) {
            adjustToMaxHeap(arr, i, size);
        }
    }
    /**
     * 调整父子树——只涉及三个节点
     * @param arr 看作完全二叉树
     * @param curRootNode 当前父节点下标
     * */
    private static void adjustToMaxHeap(int[] arr, int curRootNode, int size) {
        // 左右子结点位置
        int left = curRootNode * 2 + 1;
        int right = curRootNode * 2 + 2;
        if (left > size - 1 || right > size - 1) {
            return;
        }

        int max = curRootNode; // 记录最大值下标

        // 记录最大结点位置：
        if (arr[max] < arr[left]) {
            max = left;
        }
        if (arr[max] < arr[right]) {
            max = right;
        }

        // 最大元素交换到此树杈的根结点位置：
        swap(arr, max, curRootNode);
//        System.out.println("adjust, " + Arrays.toString(arr));
    }
}
