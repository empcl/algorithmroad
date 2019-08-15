package interview;

import java.util.Scanner;

/**
 * Module Desc:
 *  给定n*m大小的棋盘，以及马的初始位置(x，y)，要求不能重复经过棋盘上的同一个点。
 *  计算马可以有多少途径遍历棋盘上的所有点。
 *  输入：
 *      第一行为整数T(T < 10)，表示测试数据组数。
 *      每一组测试数据包含一行，为四个整数，分别为棋盘的大小以及初始位置坐标n,m,x,y。
 *      (0<=x<=n-1,0<=y<=m-1, m < 10, n < 10)
 *  输出：
 *      每组测试数据包含一行，为一个整数，表示马能遍历棋盘的途径总数，0为无法遍历一次
 *  示例：
 *  输入：
 *      1
 *      5 4 0 0
 * 输出：
 *      32
 * User: empcl
 * DateTime: 2019/8/13 15:09
 * https://blog.csdn.net/yuanxu716/article/details/78086978
 */
public class HorseWalking {
    private static final int[][] dis = {{1,2},{-1,2},{1,-2},{-1,-2},
                                        {2,1},{-2,1},{2,-1},{-2,-1}};
    private static int ans = 0;
    private static int m;
    private static int n;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] split = line.split(" ");
        m = Integer.parseInt(split[0]);
        n = Integer.parseInt(split[1]);
        int x0 = Integer.parseInt(split[2]);
        int y0 = Integer.parseInt(split[3]);

        boolean[][] vis = new boolean[m][n];
        vis[x0][y0] = true;
        dfs(x0,y0,1,vis);
        System.out.println(ans);
    }

    private static void dfs(int x,int y,int step,boolean[][] vis) {
        if (step == m * n) {
            ans++;
            return;
        }
        for (int i = 0;i < 8;i++) {
            int tmpx = x + dis[i][0];
            int tmpy = y + dis[i][1];
            if (tmpx >= 0 && tmpy >= 0 && tmpx < m && tmpy < n && !vis[tmpx][tmpy]) {
                vis[tmpx][tmpy] = true;
                dfs(tmpx,tmpy,step + 1,vis);
                vis[tmpx][tmpy] = false;
            }
        }
    }
}
