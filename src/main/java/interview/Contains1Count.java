package interview;

/**
 * Module Desc:
 *  计算一个数的二进制中1的个数
 *  NumberOf1_test()这个方法有一个问题，当这个数是整数的时候能够正确的计算出来结果。但是，如果是负数的话，将会出现死循环的问题。
 * User: empcl
 * DateTime: 2019/8/8 22:40
 */
public class Contains1Count {
    public int NumberOf1_test(int n) {
        int count = 0;
        while (n != 0) {
            if ((n & 1) != 0)
                count++;
            n = n >> 1;
        }
        return count;
    }

    public int NumberOf1_final(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }

    public static void main(String[] args) {
        Contains1Count contains1Count = new Contains1Count();
        int number = contains1Count.NumberOf1_final(-128);
        System.out.println(number);
    }
}
