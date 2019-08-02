package interview;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Module Desc:
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组.
 * 注意：答案中不可以包含重复的三元组。
 * 另外，三次for循环的做法不作考虑
 * User: empcl
 * DateTime: 2019/8/2 14:53
 */

public class ThreeSum {
    public ArrayList<List> threeSum(int[] a,ArrayList<List> result) {
        int len = a.length;
        for (int i = 0;i < len;i++) {
            // 去重
            if (i > 0 && a[i] == a[i - 1])
                continue;
            int b = i + 1;
            int c = len - 1;
            while (b < c) {
                if (a[i] + a[b] + a[c] < 0)
                    b++;
                else if (a[i] + a[b] + a[c] > 0)
                    c--;
                else {
                    result.add(Arrays.asList(a[i],a[b],a[c]));
                    // 去重
                    while (b < c && a[b] == a[b + 1]) b++;
                    while (b < c && a[c] == a[c - 1]) c--;
                    b++;
                    c--;
                }

            }
        }
        return null;
    }

    // 快排 升序
    public void sort(int[] a,int start,int end) {
        if (start < end) {
            int pivot = getPivotIndex(a,start,end);
            sort(a, start, pivot - 1);
            sort(a,pivot + 1,end);
        }
    }

    private int getPivotIndex(int[] a,int start,int end) {
        int tmp = a[start];
        while (start < end) {
            while (start < end && a[end] >= tmp)
                end--;
            a[start] = a[end];
            while (start < end && a[start] <= tmp)
                start++;
            a[end] = a[start];
        }
        a[start] = tmp;
        return start;
    }

    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        int[] a = {-1, 0, 1, 2, -1, -4};
        threeSum.sort(a,0,a.length - 1);
        ArrayList<List> result = new ArrayList<List>();
        threeSum.threeSum(a,result);
        System.out.println(result);
    }
}
