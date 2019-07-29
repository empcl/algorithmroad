
import org.junit.platform.commons.util.StringUtils;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String strA = scanner.nextLine();
        String strB = scanner.nextLine();

        String[] as = strA.split(" ");
        String[] bs = strB.split(" ");

        int index = 0;
        int lenA = as.length;
        int lenB = as.length;

        for (int i = 1;i < lenA;i++) {
            if (Integer.parseInt(as[i - 1]) >= Integer.parseInt(as[i])) {
                index = i;
                break;
            }
        }
        if (index > 0 && index < lenA) {
            int p = Integer.parseInt(as[index - 1]);
            int q = Integer.parseInt(as[index + 1]);
            for (int i = lenB - 1;i >= 0;i--) {
                if (Integer.parseInt(bs[i]) > p && Integer.parseInt(bs[i]) < q) {
                    as[index] = bs[i];
                    for (int j = 0;j < lenA;j++) {
                        System.out.print(as[j] + " ");
                    }
                    return;
                }
            }
        }
        System.out.println("NO");
    }
}

//public class Main {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String str = scanner.nextLine();
//        StringUtils.isBlank()
//        String[] words = str.split(" ");
//        int len = words.length;
//        if (words[0].charAt(0) != words[len - 1].charAt((words[len - 1]).length() - 1)){
//            System.out.println("false");
//            return;
//        }
//        for (int i = 0;i < len - 1;i++) {
//            if (words[i].charAt((words[i]).length() - 1) != words[i + 1].charAt(0)) {
//                System.out.println("false");
//                return;
//            }
//        }
//        System.out.println("true");
//    }
//}

//public class Main {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int len = scanner.nextInt();
//        Integer[] A = new Integer[len];
//        Integer[] B = new Integer[len];
//        int count = Integer.MIN_VALUE;
//
//        for (int i = 0;i < len;i++) {
//            A[i] = scanner.nextInt();
//        }
//        for (int i = 0;i < len;i++) {
//            B[i] = scanner.nextInt();
//        }
//        ArrayList<Integer[]> list = new ArrayList<Integer[]>();
//        list = permutation(B);
//        for (Integer[] ints: list) {
//            int currentCount = getCurrentMaxCount(A,ints,len);
//            if (currentCount > count)
//                count = currentCount;
//        }
//
//        System.out.println(count);
//    }
//
//    private static int getCurrentMaxCount(Integer[] A,Integer[] ints,int len) {
//        int count = 0;
//        for (int i = 0;i < len;i++) {
//            if (A[i] > ints[i])
//                count++;
//            else if (A[i] < ints[i])
//                count--;
//        }
//        return count;
//    }
//
//    public static ArrayList<Integer[]> permutation(Integer[] ints) {
//        List<Integer[]> resultList = new ArrayList<>();
//        if(ints.length == 0)
//            return (ArrayList)resultList;
//        fun(ints,resultList,0);
//        return (ArrayList)resultList;
//    }
//
//    private static void fun(Integer[] ints,List<Integer[]> list,int i){
//        if(i == ints.length-1){
//            //判断一下是否重复
//            if(!list.contains(ints)){
//                list.add(ints);
//                return;
//            }
//        }else{
//            for(int j=i;j<ints.length;j++){
//                swap(ints,i,j);
//                fun(ints,list,i+1);
//                swap(ints,i,j);
//            }
//        }
//    }
//
//    //交换数组的两个下标的元素
//    private static void swap(Integer[] ints, int i, int j) {
//        if (i != j) {
//            int t = ints[i];
//            ints[i] = ints[j];
//            ints[j] = t;
//        }
//    }
//}