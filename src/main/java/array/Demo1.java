package array;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author empcl
 * @Description
 * @Date 2019/5/11
 * @Time 19
 */
public class Demo1 {
    public static void main(String[] args) {
        String str = "";
        str = str + 'a';
        System.out.println(str);
        boolean[] booleans = new boolean[5];
        System.out.println(booleans);
        ArrayOper arrayOper = new ArrayOper();
        Integer[] per = {1,3,2};
        ArrayList<String> permutation =
                arrayOper.permutation(per);
        System.out.println(permutation);
        int[] a = {1, 3, 5, 6, 7, 8, 9};
//        boolean twoPoint = arrayOper.findTwoPoint(a, 10);
//        System.out.println(twoPoint);
//
//        int[] b = {2, 21, 211};
//        String s = arrayOper.minMixedNumber_1(b);
//        System.out.println(s);
//
//        int[] c = {1, 3, 5, 4};
//        System.out.println(arrayOper.getMax_2(c));
//
        int[] d = {1, 2, 3, 4, 5, 6, 7};
        int[] ints = arrayOper.subArraySwap(d, 5);
        System.out.println(ints);
//        int[] ints = arrayOper.subArraySwap(d, 2);
//        System.out.println(ints);

//        int[] array = {6501, 6828, 6963, 7036, 7422, 7674, 8146, 8468, 8704, 8717,
//                9170, 9359, 9719, 9895, 9896, 9913, 9962, 154, 293, 334,
//                492, 1323, 1479, 1539, 1727, 1870, 1943, 2383, 2392, 2996,
//                3282, 3812, 3903, 4465, 4605, 4665, 4772, 4828, 5142, 5437,
//                5448, 5668, 5706, 5725, 6300, 6335};
//        int i = arrayOper.minNumberInRotateArray(array);
//        System.out.println(i);
    }
}
