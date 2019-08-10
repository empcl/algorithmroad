package interview;

/**
 * Module Desc:
 *  两个36进制，相加之和。
 * User: empcl
 * DateTime: 2019/8/10 14:04
 */
public class ThirtySixHexSum {
    public int add(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int sum = 0;
        char[] num1 = new char[len1];
        char[] num2 = new char[len2];
        int index = 0;
        for (int i = len1 - 1;i >= 0;i--)
            num1[index++] = str1.charAt(i);
        index = 0;
        for (int i = len2 - 1;i >= 0;i--)
            num2[index++] = str2.charAt(i);
        int i = 0;
        for (;i < len1 && i < len2;i++) {
            int a = num1[i];
            int b = num2[i];
            int num_a = 0;
            int num_b = 0;
            if (a < '0' || a > '9')
                num_a = a - 'A' + 10;
            else
                num_a = a - '0';
            if (b < '0' || b > '9')
                num_b = b - 'A' + 10;
            else
                num_b = b - '0';
            sum = sum + (num_a + num_b) * (int)(Math.pow(36,i));
        }
        for (;i < len1;i++) {
            int a = num1[i];
            int num_a = 0;
            if (a < '0' || a > '9')
                num_a = a - 'A' + 10;
            else
                num_a = a - '0';
            sum = sum + num_a * (int)(Math.pow(36,i));
        }

        for (;i < len2;i++) {
            int b = num2[i];
            int num_b = 0;
            if (b < '0' || b > '9')
                num_b = b - 'A' + 10;
            else
                num_b = b - '0';
            sum = sum + num_b * (int)(Math.pow(36,i));
        }
        return sum;
    }

    private String int2hex(int num) {
        String res = "";
        while (num != 0) {
            int tmp = compute(num);
            if (tmp >= 10)
                res = (char)(tmp - 9 + 64) + res;
            else
                res = (char)(tmp + 48) + res;
            num = num / 36;
        }
        return res;
    }

    private int compute(int num) {
        if (num <= 36)
            return num;
        int tmp = num;
        while (num > 36) {
            num = num / 36;
        }
        return tmp - num * 36;
    }
    public static void main(String[] args) {
        ThirtySixHexSum sixHexSum = new ThirtySixHexSum();
        int sum = sixHexSum.add("AW", "4");
        System.out.println("sum = " + sum);
        String res = sixHexSum.int2hex(sum);
        System.out.println("res = " + res);
    }

}
