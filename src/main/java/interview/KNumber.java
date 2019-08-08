package interview;

/**
 * Module Desc:
 *  给出一组数据，找出第k个大的数
 *  利用快排的思想，时间复杂度是o(n)
 *  https://blog.csdn.net/orangefly0214/article/details/86527462
 * User: empcl
 * DateTime: 2019/8/5 13:02
 */
public class KNumber {
    private static int findKNumber(int[] a,int k,int start,int end) {
        int pivot = getPivotIndex(a,start,end);
        if (pivot == k - 1) {
            return a[pivot];
        } else if (pivot > (k - 1)) {
            return findKNumber(a,k,start,pivot - 1);
        } else
            return findKNumber(a,k,pivot + 1,end);
    }

    private static int getPivotIndex(int[] a,int start,int end) {
        int tmp = a[start];
        while (start < end) {
            while (start < end && a[end] <= tmp)
                end--;
            a[start] = a[end];
            while (start < end && a[start] >= tmp)
                start++;
            a[end] = a[start];
        }
        a[start] = tmp; // 当前位置start是pivot应该在的位置。
        return start;
    }

    public static void main(String[] args) {
        int[] a = {5,2,3,4,4,1,2};
        System.out.println(findKNumber(a,4,0,4));
    }
}
