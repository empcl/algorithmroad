package interview;

/**
 * Module Desc:
 * numCovertLetter:
 *  根据excel列号计算列数以及根据列号计算列数，excel中的列数是使用字母表示的，
 *  即A,B,C...Z,AA...,ZZ....这种情况，假设我们输入1，那么输出结果为A，输入27，输出结果为AA，一次类推。
 *
 * letterConvertNum:
 *  根据上面的说明，由列号求列数
 * User: empcl
 * DateTime: 2019/7/27 10:17
 * https://msd.misuland.com/pd/3148108429789233728
 */
public class NumConvertLetter {
    private static String numCovertLetter(int num) {
       if (num < 0)
           throw new IllegalArgumentException("num < 0");
       String str = "";
       while (num != 0) {
           int res = num % 26;
           if (res == 0)
               res = 26;
           str = (char)(res + 64) + str; // 因为A的ASCII是65.
           num = (num - res) / 26; // num - res 表示num减去当前正在算的数，也就是说，这个差就是下一步要循环计算的数。
                                    // / 26 是因为根据列的排序情况可以知道，当A...Z结束之后，下一个范围就是AA,AB,,,AZ。
                                    // / 26是要知道我下一个数的开头是以A，还是以B，还是以其他的什么字母表示。
       }
       return str;
    }

    private static int letterConvertNum(String str) {
        int len = str.length();
        int res = 0;
        int pos = 1;
        for (int i = len - 1;i >= 0;i--) {
           int tmp = str.charAt(i) - 64;
           res = res + (tmp * pos);
           pos = pos * 26; // 下一个变化1个数，实际上变化了26倍。比如BC说，当遍历第一个C的时候，倍数是1，但是，当遍历B的时候，实际上就会包含两个26；
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(letterConvertNum("BC"));
        System.out.println(numCovertLetter(letterConvertNum("BC")));
    }
}
