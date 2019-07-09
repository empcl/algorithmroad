package dp;

import java.util.TreeSet;

/**
 * 最长递增子序列
 * https://www.cnblogs.com/coffy/p/5878915.html
 *
 * @Author empcl
 * @Description
 * @Date 2019/5/31
 * @Time 15
 */
public class LongestIncreasingSubsequence {
    static int len1;
    static int len2;
    static int[] ints1;
    static int[] ints2;
    static int[][] dp;
    static TreeSet<String> set = new TreeSet<String>();

    // way1 lcs + 排序
    public static int getLCS(int[] chars1, int[] chars2) {
        ints1 = chars1;
        ints2 = chars2;
        len1 = chars1.length;
        len2 = chars2.length;
        dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++)
            for (int j = 0; j <= len2; j++)
                dp[i][j] = 0;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (chars1[i - 1] == chars2[j - 1])
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[len1][len2];
    }

    // 获得所有最长递增序列
    public static void getAllLIS(int m, int n, String lis_str) {
        while (m > 0 && n > 0) {
            if (ints1[m - 1] == ints2[n - 1]) {
                lis_str += "," + ints1[m - 1];
                m--;
                n--;
            } else {
                if (dp[m - 1][n] > dp[m][n - 1]) {
                    m--;
                } else if (dp[m - 1][n] < dp[m][n - 1]) {
                    n--;
                } else {
                    getAllLIS(m - 1, n, lis_str);
                    getAllLIS(m, n - 1, lis_str);
                    return;
                }
            }
        }
        set.add(reserve(lis_str));
    }

    // ,200,200,186,186
    private static String reserve(String lis_str) {
        String[] split = lis_str.substring(1).split(",");
        int len = split.length;
        int[] ints = new int[len];
        int index = 0;
        for (int i = len - 1; i >= 0; i--) {
            ints[index++] = Integer.parseInt(split[i]);
        }
        String str = "";
        for (int i = 0; i < len; i++) {
            str = str + " " + ints[i];
        }
        return str;
    }

    // 排序 -- 冒泡排序
    public static int[] sort(int[] ints) {
        int length = ints.length;
        int temp = 0;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i; j < length; j++) {
                if (ints[i] > ints[j]) {
                    temp = ints[j];
                    ints[j] = ints[i];
                    ints[i] = temp;

                }
            }
        }
        return ints;
    }

    // way2
    public static int lis(int[] L) {
        int n = L.length;
        int[] f = new int[n];//用于存放f(i)值；
        f[0] = 1;//以第a1为末元素的最长递增子序列长度为1；
        for (int i = 1; i < n; i++)//循环n-1次
        {
            f[i] = 1;//f[i]的最小值为1；
            for (int j = 0; j < i; j++)//循环i 次
            {
                /**
                 *  f[j] > f[i] - 1这个条件必须加上，否则结果将会出错
                 *  为什么要这么写？
                 *  我们知道，递增子序列的后一个值是大于前一个值的，那我们能不能只用这个条件作为筛选条件的呢？显然，是不可能的。
                 *  以本例数据（{186, 186, 150, 200, 160, 130, 197, 120,200},len = 9）来说，按照当前的这个条件，f[8] = 2(f[8] = f[7] + 1 );
                 *  显然，这是不对的。
                 *  这个时候我们需要加上f[j] > f[i] - 1这个条件。当前的这个f[i]的值（可能不是当前i位的最终值），是由j之前对应的值决定的。在满足a[j] < a[i]条件的时候，
                 *  我们也要看看f[j] 与 f[j-x](即j之前的f对应的值,f[i] - 1)，看他们对应的关系。只有 f[j] > f[j - x]的时候，我们才可以知道，当前这个j也属于i这个范围的子序列的一部分。
                 */
                if (L[j] < L[i] && f[j] > f[i] - 1)
                    f[i] = f[j] + 1;//更新f[i]的值。
            }
        }
        return f[n - 1];
    }

    public static void main(String[] args) {
        int[] ints = {186, 186, 150, 200, 160, 130, 197, 120,200};
        int len = ints.length;
        int[] ints1 = new int[len];
        for (int i = 0; i < len; i++) {
            ints1[i] = ints[i];
        }
        int[] sort = sort(ints);
        int lcs = getLCS(ints1, sort);
        System.out.println(lcs);
        getAllLIS(ints.length, ints.length, "");
        for (String s : set) {
            System.out.println(s);
        }
        int lis = lis(ints1);
        System.out.println(lis);

    }
}
