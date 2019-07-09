package sort;

import java.util.Arrays;

/**
 * @Author empcl
 * @Description
 * @Date 2019/5/14
 * @Time 21
 */

// https://www.cnblogs.com/onepixel/articles/7674659.html
public class SortOper {
    /**
     * 冒泡排序
     * 每进行以此排序，将会固定一个数的位置
     * 时间复杂度：
     * 平均：o(n^2)
     * 最好：o(n) // 一趟过
     * 最坏：o(n^2)
     * 空间复杂度：
     * o(1)
     * 稳定性：
     * 稳定
     */
    public int[] bubbleSort(int[] a) {
        int len = a.length;
        int temp = 0;
        for (int i = 0;i < len - 1;i++){
            for (int j = 0;j < len - 1 -i;j++) {
                if (a[j] > a[j + 1]){
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        return a;
    }

    /**
     * 选择排序
     * 每次遍历找到最小（大）的元素，然后放到相应的位置上。下一次遍历从这个最值位置的下一个位置开始遍历。
     * 时间复杂度：
     * 平均：o(n^2)
     * 最好：o(n^2)
     * 最坏：o(n^2)
     * 空间复杂度：
     * o(1)
     * 稳定性：
     * 不稳定（举个例子：5,8,5,2,9，当我们进行第一轮遍历的时候5会和2调换位置）
     *
     * @param a
     * @return
     */
    public int[] selectionSort(int[] a) {
        int length = a.length;
        int temp = 0;
        int minIndex = 0;
        for (int i = 0; i < length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            temp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = temp;
        }
        return a;
    }

    /**
     * 插入排序
     * 数列分为两部分：有序、无序。从无序中拿出一个元素，从后到头的方向插入有序的数列中
     * 时间复杂度：
     * 平均：o(n^2)
     * 最好：o(n)
     * 最坏：o(n^2)
     * 空间复杂度：
     * o(1)
     * 稳定性：
     * 稳定
     */
    public int[] insertionSort(int[] a) {
        int length = a.length;
        int current = 0;
        int i, j;
        for (i = 1; i < length; i++) {
            current = a[i];
            for (j = i - 1; j >= 0; j--) {
                if (a[j] > current) // 该处必须是current，因为如果使用a[i]的话，就会使得程序逻辑出错，因为a[j+1] = a[j]的缘故，a[i]表示的值并不是原来的值。
                    a[j + 1] = a[j];
                else
                    break;
            }
            a[j + 1] = current;
        }
        return a;
    }

    /**
     * 希尔排序 -- 缩小增量排序
     * 从名字我们就可以知道了这个排序算法的核心点：缩小增量。
     * 缩小的是两个比较元素之间的间距，按照r/2的速度向下缩小，当缩到1的时候，就对全表做直接插入排序。
     * 时间复杂度：
     * 平均：o(nlgn)
     * 最好：o(n)
     * 最坏：o(n^2)
     * 空间复杂度：
     * o(1)
     * 稳定性：
     * 不稳定
     */
    public int[] shellSort(int[] a) {
        // i 表示希尔排序中的第(n/2,n/4...)个元素
        // j 表示希尔排序中的从0到n/2(n/4...)个元素
        // r 表示比较的两个值跨越的间距
        int length = a.length;
        int i, j, tmp, r = length / 2;
        for (; r >= 1; r = r / 2) { // 用于控制循环的次数
            for (i = r; i < length; i++) {
                tmp = a[i];
                j = i - r;
                while (j >= 0 && a[j] < tmp) {
                    a[j + r] = a[j];
                    j = j - r;
                }
                a[j + r] = tmp;
            }
        }
        return a;
    }

    /**
     * 归并排序 -- 分治法思想
     * 时间复杂度：
     * 平均：o(nlog2n)
     * 最好：o(nlog2n)
     * 最坏：o(nlog2n)
     * 空间复杂度：
     * o(n)
     * 稳定性：
     * 稳定
     */
    public int[] mergeSort(int[] a, int left, int right) {
        int mid = (right + left) / 2;
        int[] tmp = new int[a.length];
        if (left < right) {
            mergeSort(a, left, mid);
            mergeSort(a, mid + 1, right);
            merge(a, left, right,tmp);
        }
        return a;
    }

    private void merge(int[] a, int left, int right,int[] tmp) {
        int mid = (right + left) / 2;
        int p1 = left;
        int k = left;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            if (a[p1] > a[p2])
                tmp[k++] = a[p2++];
            else
                tmp[k++] = a[p1++];
        }
        while (p1 <= mid)
            tmp[k++] = a[p1++];
        while (p2 <= right)
            tmp[k++] = a[p2++];

        for (int i = left; i <= right; i++)
            a[i] = tmp[i];
    }

    /**
     * https://www.cnblogs.com/xiaoming0601/p/5862860.html
     * 快速排序 -- 通过一趟排序找到pivot，并将待排记录分割成独立的两部分，其中一部分记录的关键字均比pivot小，
     * 另一部分均比pivot大。
     * 时间复杂度：
     * 平均：o(nlog2n)
     * 最好：o(nlog2n)
     * 最坏：o(n^2)
     * 空间复杂度：
     * o(1)
     * 稳定性：
     * 不稳定
     */
    public int[] quickSort(int[] a, int left, int right) {
        if (left < right) {
            int pivotIndex = getPivotIndex(a, left, right);
            quickSort(a, left, pivotIndex - 1);
            quickSort(a, pivotIndex + 1, right);
        }
        return a;
    }

    private int getPivotIndex(int[] a, int left, int right) {
        int tmp = a[left];
        while (left < right) {
            // 将大于pivot的值放在一边，跳出循环，就表示，当前的这个值比基准值小，需要放到基准值的左边去
            /**
             * 对最下面的这个<= 举个例子就一目了然了
             *  如 6,8,6,4在第一次遍历时候，会进行到这一步：4,6l,[6]r,8 6
             *  对上述的说明，6l表示当前下标对应的值为6，且left已经指向了这个位置,
             *  [6]r表示当前下标对应的值并不是确定的值，即未被赋新值，且right已经指向了这个位置。
             *  此时l<r,满足while循环条件，但是，因为tmp<a[right]或者tmp > a[left]使得left或者right将不会发生改变。直接导致了死循环。
             */
            while (left < right && tmp <= a[right]) // 如果数组中出现了重复的值，如果只是<号的话，就会出现死循环，变成<=就好了
                right--;
            a[left] = a[right];
            // 将小于pivot的值放在另一边，跳出循环，就表示，当前的这个值比基准值大，需要放到基准值的右边去
            while (left < right && tmp >= a[left])
                left++;
            a[right] = a[left];
        }
        a[left] = tmp;
        return left;
    }

    /**
     * 堆排序 -- 将数组看成完全二叉树的结构
     * https://www.cnblogs.com/CherishFX/p/4643940.html
     * 任一节点i：
     * 父节点：i == 0 ? null:(i-1)/2
     * 左孩子：2*i + 1
     * 右孩子：2*i + 2
     * 堆的定义：
     * n个关键字序列array[0，...，n-1]，当且仅当满足下列要求：(0 <= i <= (n-1)/2)
     * ① array[i] <= array[2*i + 1] 且 array[i] <= array[2*i + 2]； 称为小根堆；
     * ② array[i] >= array[2*i + 1] 且 array[i] >= array[2*i + 2]； 称为大根堆；
     * 时间复杂度：
     * 建堆：o(n)
     * 每次调整：o(lgn)
     * 平均，最好，最坏：o(nlgn)
     * 空间复杂度：
     * o(1)
     * 稳定性：
     * 不稳定
     *
     * @param a
     * @return
     */
    public int[] heapSort(int[] a) {
        a = buildMaxHeap(a); // 构建最大堆，满足父节点大于子节点，但是并不保证堆是有序的
        int length = a.length;
        int temp = 0;
        for (int i = length - 1; i >= 0; i--) {
            temp = a[0]; // 将堆顶元素与堆底元素交换，保证最大的元素在正确的位置上
            a[0] = a[i];
            a[i] = temp;
            adjustUpToDown(a, 0, i); // 调整剩余的堆元素
        }
        return a;
    }

    private int[] buildMaxHeap(int[] a) {
        int length = a.length;
        // 从最后一个节点的父节点开始，直到根节点0，反复调整堆
        for (int i = (length - 2) / 2; i >= 0; i--) {
            adjustUpToDown(a, i, length);
        }
        return a;
    }

    // 将数组元素a自上向下逐步调整树形结构
    private int[] adjustUpToDown(int[] a, int k, int length) {
        int temp = a[k];
        for (int i = 2 * k + 1; i < length - 1; i = 2 * i + 1) { // i 为初始节点k的左孩子节点，沿节点较大的子节点向下调整
            if (i < length - 1 && a[i] < a[i + 1])
                i++; // 如果右孩子节点 > 左孩子节点，则取右孩子节点坐标
            if (temp >= a[i]) { // 如果根节点 >= 左右孩子中最大的节点，调整结束。
                // 因为左右孩子节点是其子树的最大值，故根节点大于左右子节点，也就意味着大于以子节点为根的子树了
                break;
            } else {
                a[k] = a[i]; // 将左右子节点中较大值调整到双亲节点上
                k = i;  // 修改k值，以便继续向下调整
            }
            a[k] = temp; // 被调整的节点的值放入最终的位置上
        }
        return a;
    }

    /**
     * 计数排序
     * https://www.cnblogs.com/developerY/p/3166462.html
     * 时间复杂度：
     * 最好、最坏，平均：o(n+k)
     * 空间复杂度：
     * o(n+k)
     * 稳定性：
     * 稳定
     */
    public int[] countSort(int[] a, int k) {
        int length = a.length;
        int[] b = new int[length]; // b用于存储a排序后的元素
        int[] c = new int[k + 1]; // c用于存储当前下标在数组a中出现的次数，k+1是因为数组从0开始的
        int sum = 0; // sum用于表示包含当前这个元素总共出现了多少个元素，用户后期定位使用

        for (int i = 0; i < length; i++)
            c[a[i]] += 1;
        for (int i = 0; i <= k; i++) {
            sum = sum + c[i];
            c[i] = sum;
        }
        for (int i = length - 1; i >= 0; i--) { // 为了稳定性，采用倒序遍历的方式，详见注释中链接
            b[c[a[i]] - 1] = a[i];
            c[a[i]]--;
        }
        return b;
    }

    /**
     * 桶排序
     * https://blog.51cto.com/flyingcat2013/1286645
     * 时间复杂度：
     * 平均：o(n+k)
     * 最好：o(n)
     * 最坏：o(n^2)
     * 空间复杂度：
     * o(n+k)
     * 稳定性：
     * 稳定
     */
    public Integer[][] bucketSort(int[] a) { //输入的数只能是0-99
        int bucketCount = 10; // 默认十个桶
        int length = a.length;
        Integer[][] bucket = new Integer[bucketCount][length];
        // 入桶
        for (int i = 0; i < length; i++) {
            int q = a[i] / bucketCount;
            for (int j = 0; j < length; j++) {
                if (bucket[q][j] == null) {
                    bucket[q][j] = a[i];
                    break;
                }
            }
        }

        // 对小桶进行排序 -- 直接插入排序
        for (int i = 0; i < bucketCount; i++) {
            for (int j = 1; j < length; j++) {
                if (bucket[i][j] == null)
                    break;
                int value = bucket[i][j];
                int position = j;
                while (position > 0 && bucket[i][position - 1] > value) {
                    bucket[i][position] = bucket[i][position - 1];
                    position--;
                }
                bucket[i][position] = value;
            }
        }
        return bucket;
    }

    /**
     * 基数排序
     * https://www.cnblogs.com/developerY/p/3172379.html
     * 时间复杂度：k表示关键数的个数
     * 最好：o(n*k)
     * 最坏：o(n*k)
     * 平均：o(n*k)
     * 空间复杂度：
     * o(n+k)
     * 稳定性：
     * 稳定
     */
    public int[] radixSort(int[] a, int d) {
        int n = 1; // 代表位数对应的数：1，10，100
        int k = 0;
        int length = a.length;
        int[][] bucket = new int[10][length]; // 二维数组，第一个[]表示的是以0-9为下标对应出现的数的集合,
        // 第二个[]表示的是当前0-9下标出现了多少个数。
        int[] counts = new int[10];
        while (n <= d) {
            // 入二维数组
            for (int num : a) {
                int digit = (num / n) % 10;
                bucket[digit][counts[digit]] = num;
                counts[digit]++;
            }
            // 出二维数组，入原先的数组中
            for (int i = 0; i < 10; i++) {
                if (counts[i] != 0) {
                    for (int j = 0; j < counts[i]; j++) {
                        a[k] = bucket[i][j];
                        k++;
                    }
                }
                counts[i] = 0;
            }
            n = n * 10;
            k = 0;
        }
        return a;
    }
}

