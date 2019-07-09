package dp;

/**
 * 0-1背包问题
 * https://blog.csdn.net/likunkun__/article/details/80223003
 *
 * @Author empcl
 * @Description
 * @Date 2019/6/2
 * @Time 10
 */
public class Knapsack {
    public static int knapsack(int[] weights, int[] values, int maxWeight) {
        int w_len = weights.length;
        int[][] dp = new int[w_len + 1][maxWeight + 1]; // 注意这个列长度为最大重量+1
        for (int i = 0; i <= w_len; i++)
            dp[i][0] = 0;
        for (int i = 0; i <= maxWeight; i++)
            dp[0][i] = 0;
        for (int i = 1; i <= w_len; i++) {
            for (int j = 1; j <= maxWeight; j++) {
                dp[i][j] = dp[i - 1][j]; // 当前最大价值等于放上一件的最大价值
                if (weights[i - 1] <= j) { // 如果当前件的重量小于总重量，可以放进去或者拿出别的东西再放进去
                    // dp[i - 1][j - weights[i - 1]] 因为我们要放下这一件物品，所以我们要考虑在能够放得下这一件物品的前提下，
                    // 求出能够i - 1件物品最大的价值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
                }
            }
        }
        return dp[w_len][maxWeight];
    }

    public static void main(String[] args) {
        int weight[] = {2, 3, 4, 5};
        int value[] = {3, 4, 5, 7};
        int maxweight = 9;
        System.out.println(knapsack(weight, value, maxweight));
    }

}
