package dp;

/**
 * java 动态规划解决最大连续子数列和
 * https://www.cnblogs.com/zheng123/p/10183310.html
 *
 * @Author empcl
 * @Description
 * @Date 2019/6/1
 * @Time 15
 */
public class MaxSubArraySum {
    public static int getMaxSum(int[] ints) {
        int length = ints.length;
        int[] dp = new int[length];
        dp[0] = ints[0];
        int max = 0;
        for (int i = 1; i < length; i++) {
            dp[i] = Math.max(0, dp[i - 1]) + ints[i];
            if (max < dp[i])
                max = dp[i];
        }
        // all data are negative
        if (max == 0) {
            max = ints[0];
            for (int i = 1;i < length;i++) {
                if (max < ints[i])
                    max = ints[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
//        int[] ints = {-2, 11, -4, 13, 5, -2};
        int[] ints = {-2,-5,-4,-3};
        System.out.println(getMaxSum(ints));
    }
}
