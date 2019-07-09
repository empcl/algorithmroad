package dp;

/**
 * 最大连续子序列积
 * 1）数组是正浮点数数组
 * 2）数组是可正可负可为0的浮点数数组
 * https://blog.csdn.net/liangzhaoyang1/article/details/51049211
 *
 * @Author empcl
 * @Description
 * @Date 2019/6/1
 * @Time 20
 */
public class MaxSubArrayMut {
    // 给定一个正浮点数数组，求它的一个最大连续子序列乘积的值。
    public static double getMaxMut1(double[] doubles) {
        int len = doubles.length;
        double[] dp = new double[len];
        dp[0] = doubles[0];
        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(dp[i - 1] * doubles[i], doubles[i]);
        }
        double max = 0;
        for (int i = 0; i < len; i++) {
            if (max < dp[i])
                max = dp[i];
        }
        return max;
    }

    // 给定一个浮点数数组，其值可正可负可零，求它的一个最大连续子序列乘积的值。

    /**
     * 因为有负数的存在，之前的最大乘积*当前的数（负数）可能立马变为了当前最小的乘积，
     * 也有可能是之前的最小的乘积*当前的数（负数）可能变成了当前最大的乘积，这也就意味着，
     * 需要记录当前下标之前，最大乘积和最小乘积，通过与当前下标对应的值相乘与对比，得到最大的值。
     */
    public static double getMaxMut2(double[] doubles) {
        int len = doubles.length;
        double maxEnd = doubles[0];
        double minEnd = doubles[0];
        double maxResult = doubles[0];
        for (int i = 1; i < len; i++) {
            double end1 = maxEnd * doubles[i];
            double end2 = minEnd * doubles[i];
            maxEnd = Math.max(Math.max(end1, end2), doubles[i]);
            minEnd = Math.min(Math.min(end1, end2), doubles[i]);
            maxResult = Math.max(maxEnd, maxResult);
        }
        return maxResult;
    }

    public static void main(String[] args) {
        double[] doubles1 = {0, 1, 0.5, 2, 3, 0.5, 0, 8, 0.5};
        double[] doubles2 = {-2, 3, -1, 4, 0, 25};
        System.out.println(getMaxMut1(doubles1));
        System.out.println(getMaxMut2(doubles2));
    }
}
