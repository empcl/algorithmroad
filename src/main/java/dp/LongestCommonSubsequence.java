package dp;

import java.util.TreeSet;

/**
 * 最长公共子序列
 * https://blog.csdn.net/qbyhqp/article/details/82050535
 * https://blog.csdn.net/lisonglisonglisong/article/details/41596309
 *
 * @Author empcl
 * @Description
 * @Date 2019/5/30
 * @Time 21
 */

public class LongestCommonSubsequence {
    static char[] chars1;
    static char[] chars2;
    static int[][] dp; // 数据表
    static TreeSet<String> set = new TreeSet<String>(); // 用于存储lcs

    // 在 θ(mn) 的时间里求出 LCS 的长度
    public static int getLongestLength(String str1, String str2) {
        chars1 = str1.toCharArray();
        chars2 = str2.toCharArray();
        int len1 = chars1.length;
        int len2 = chars2.length;
        dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++)
            dp[i][0] = 0;
        for (int j = 0; j <= len2; j++)
            dp[0][j] = 0;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (chars1[i - 1] == chars2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[len1][len2];
    }

    // 回溯遍历dp表格
    public static void traceBack(int i, int j, String lcs_str) {
        while (i > 0 && j > 0) {
            if (chars1[i - 1] == chars2[j - 1]) {
                lcs_str += chars1[i - 1];
                i--;
                j--;
            } else {
                if (dp[i - 1][j] > dp[i][j - 1])
                    i--;
                else if (dp[i - 1][j] < dp[i][j - 1])
                    j--;
                else {
                    traceBack(i - 1, j, lcs_str);
                    traceBack(i, j - 1, lcs_str);
                    return;
                }
            }
        }
        set.add(reverse(lcs_str));
    }

    private static String reverse(String lcs_str) {
        StringBuilder builder = new StringBuilder(lcs_str);
        return builder.reverse().toString();
    }

    public static void main(String[] args) {
        String str1 = "12asdfa";
        String str2 = "we2rasdaswer";
//        String str1 = "ABCBDAB";
//        String str2 = "BDCABA";
        int len1 = str1.length();
        int len2 = str2.length();
        int longestLength = getLongestLength(str1, str2);
        System.out.println(longestLength);
        String lcs_str = "";
        traceBack(len1, len2, lcs_str);
        for (String s : set) {
            System.out.println(s);
        }

    }
}
