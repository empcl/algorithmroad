package dp;

import java.util.HashMap;

/**
 * @Author empcl
 * @Description
 * @Date 2019/5/30
 * @Time 11
 */

// 硬币找零：动态规划算法
// https://blog.csdn.net/vayawu/article/details/52168147
public class CoinsChange {
    /**
     * @param values     保存每一种硬币的币值的数组
     * @param valueKinds 币值不同的硬币种类数量，即values[]数组的大小
     * @param money      需要找零的面值
     * @param coinsValue 保存面值为i的纸币找零所需的最小硬币数
     * @return 满足最小硬币数对应的数组
     */
    public static HashMap<Integer, int[]> makeChange(int[] values, int valueKinds, int money, int[] coinsValue) {
        coinsValue[0] = 0;
        HashMap<Integer, int[]> coinsMap = new HashMap<Integer, int[]>();
        int[] ori = {0};
        coinsMap.put(0, ori);
        for (int cent = 1; cent <= money; cent++) {
            int minCoin = cent;
            for (int kind = 0; kind < valueKinds; kind++) {
                if (values[kind] <= cent) {
                    int temp = coinsValue[cent - values[kind]] + 1;
                    if (temp <= minCoin) {
                        int[] ints = coinsMap.get(cent - values[kind]);
                        int length = ints.length;
                        int[] selected = new int[length + 1];
                        int index = 0;
                        for (int i = 0; i < length; i++)
                            if (ints[i] != 0)
                                selected[index++] = ints[i];
                        selected[index] = values[kind];
                        coinsMap.put(cent, selected);
                        minCoin = temp;
                    }
                }
            }
            coinsValue[cent] = minCoin;
        }
        return coinsMap;
    }

    public static void main(String[] args) {
        int[] values = {1, 5, 10, 21, 25};
        int t = 63;
        int[] coinsValue = new int[t + 1];
        HashMap<Integer, int[]> hashMap = makeChange(values, values.length, t, coinsValue);
        System.out.println(hashMap);
    }

}