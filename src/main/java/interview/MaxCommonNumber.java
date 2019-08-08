package interview;

import java.math.BigInteger;

/**
 * Module Desc:
 *  求最大公约数
 * User: empcl
 * DateTime: 2019/8/3 15:07
 */
public class MaxCommonNumber {
    public static void main(String[] args) {
        MaxCommonNumber maxCommonNumber = new MaxCommonNumber();

//        long lcm = maxCommonNumber.GCD(1L, 2L);

//        System.out.println(lcm);
    }

    private BigInteger GCD(BigInteger a, BigInteger b) {
        return a.gcd(b);
    }
}
