package dp;

/**
 * 最小路径和
 * https://blog.csdn.net/qq_17525769/article/details/74907177
 *
 * @Author empcl
 * @Description
 * @Date 2019/6/2
 * @Time 12
 */
public class ShortDistance {
    public static int getShortDis(int[][] ints, int row, int col) {
        int[][] dp = new int[row][col];
        dp[0][0] = ints[0][0];
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + ints[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    public static void main(String[] args) {
        int[][] ints = new int[7][7];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                ints[i][j] = 9999;
            }
        }
        ints[0][1] = ints[1][0] = 2;
        ints[1][2] = ints[2][1] = 3;
        ints[2][6] = ints[6][2] = 4;
        ints[0][3] = ints[3][0] = 3;
        ints[3][6] = ints[6][3] = 3;
        ints[0][4] = ints[4][0] = 1;
        ints[4][5] = ints[5][4] = 2;
        ints[5][6] = ints[6][5] = 3;

        System.out.println(getShortDis(ints, 7, 7));
    }
}
