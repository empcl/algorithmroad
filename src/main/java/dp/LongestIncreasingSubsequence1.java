package dp;

/**
 * https://blog.csdn.net/feengg/article/details/80866483
 *
 * 题目描述
 * 计算最少出列多少位同学，使得剩下的同学排成合唱队形
 * 说明：
 * N位同学站成一排，音乐老师要请其中的(N-K)位同学出列，使得剩下的K位同学排成合唱队形。 
 * 合唱队形是指这样的一种队形：设K位同学从左到右依次编号为1，2…，K，他们的身高分别为T1，T2，…，TK,
 * 则他们的身高满足存在i（1<=i<=K）使得T1<T2<......<Ti-1<Ti>Ti+1>......>TK。 
 * 你的任务是，已知所有N位同学的身高，计算最少需要几位同学出列，可以使得剩下的同学排成合唱队形。
 *
 * 思路：
 *  出列最少同学，使得剩下同学形成合唱队形。意思就是说，尽可能留下更多的学生完成先升后降的队形。记某个学生为A点，
 *  A之前的同学是一个升序的，A之后的同学是一个降序的。因为要是留下最多的学生，也就是意味着（A之前升序的学生） + （A之后降序的学生）
 *  应该是最大值。就可以将这道题抽象为，对一个数组进行分别进行最长递增子序列，最长递减子序列，然后求和，求得最大值，即为留下的同学数。
 *
 * @Author empcl
 * @Description
 * @Date 2019/5/31
 * @Time 16
 */
public class LongestIncreasingSubsequence1 {
    static int[] lis;
    static int[] lds;
    static int len;

    public static void getLis(int[] ints) {
        lis = new int[len];
        lis[0] = 1;
        for (int i = 1; i < len; i++) {
            lis[i] = 1;
            for (int j = 0; j < i; j++) {
                if (ints[i] > ints[j] && lis[j] > lis[i] - 1) {
                    lis[i] = lis[j] + 1;
                }
            }
        }
    }

    public static void getLds(int[] ints) {
        lds = new int[len];
        lds[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            lds[i] = 1;
            for (int j = len - 1; j > i; j--) {
                if (ints[i] > ints[j] && lds[j] > lds[i] - 1) {
                    lds[i] = lds[j] + 1;
                }
            }
        }
    }

    public static int getNums() {
        int max = 0;
        for (int i = 0; i < len; i++) {
            if (lis[i] + lds[i] > max)
                max = lis[i] + lds[i];
        }
        return len - (max - 1); // 因为最大点操作了两次，所以要减去一个
    }

    public static void main(String[] args) {
        int[] ints = {186, 186, 150, 200, 160, 130, 197, 200};
        len = ints.length;
        getLis(ints);
        getLds(ints);
        int nums = getNums();
        System.out.println(nums);

    }

}
