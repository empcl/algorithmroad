package other;

import java.util.ArrayList;

/**
 * Module Desc:
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * User: empcl
 * DateTime: 2019/7/29 16:49
 */
public class DistinctNum {

    public static void main(String[] args) {
//        int[] a = {1,2,2,2,3,3,4,4,4,5,5};
        int[] a = {1};
        distinct(a);
        System.out.println(a);
    }

    private static void distinct(int[] a) {
        int len = a.length;
        int i = 0;
        int k = 0;
        for (int j = 1;j < len;j++) {
            if (a[i] != a[j]) {
                a[k++] = a[i];
                i = j;
            }
        }
        a[k] = a[i]; // 通过调试我们知道，当执行到最后一个不同的数的时候，就直接跳出了。所以，我们需要额外的写上这个数。
    }
}
