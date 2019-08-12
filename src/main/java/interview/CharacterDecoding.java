package interview;

import java.util.Scanner;

/**
 * Module Desc:
 *  A -> 1
 *  B -> 2
 *  ...
 *  Z -> 26
 *  输入12，将会有两种解码方法：AB,K
 *  求输入一个数，解码方法共有多少个
 * User: empcl
 * DateTime: 2019/8/12 19:57
 * https://www.nowcoder.com/practice/b83b126603dd4e63bc4287d32d754886?tpId=98&tqId=32868&tPage=3&rp=3&ru=/ta/2019test&qru=/ta/2019test/question-ranking
 */
public class CharacterDecoding {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int n = str.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            String sub = str.substring(i - 2, i);
            int a = Integer.parseInt(sub);
            if (a >= 10 && a <= 26)
                dp[i] = dp[i - 1] + dp[i - 2];
            else
                dp[i] = dp[i - 1];
        }
        System.out.println(dp[n]);
    }
}