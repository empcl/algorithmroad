import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        Integer[] A = new Integer[len];
        Integer[] B = new Integer[len];
        int count = Integer.MIN_VALUE;

        for (int i = 0;i < len;i++) {
            A[i] = scanner.nextInt();
        }
        for (int i = 0;i < len;i++) {
            B[i] = scanner.nextInt();
        }
        ArrayList<Integer[]> list = new ArrayList<Integer[]>();
        list = permutation(B);
        for (Integer[] ints: list) {
            int currentCount = getCurrentMaxCount(A,ints,len);
            if (currentCount > count)
                count = currentCount;
        }

        System.out.println(count);
    }

    private static int getCurrentMaxCount(Integer[] A,Integer[] ints,int len) {
        int count = 0;
        for (int i = 0;i < len;i++) {
            if (A[i] > ints[i])
                count++;
            else if (A[i] < ints[i])
                count--;
        }
        return count;
    }

    public static ArrayList<Integer[]> permutation(Integer[] ints) {
        List<Integer[]> resultList = new ArrayList<>();
        if(ints.length == 0)
            return (ArrayList)resultList;
        fun(ints,resultList,0);
        return (ArrayList)resultList;
    }

    private static void fun(Integer[] ints,List<Integer[]> list,int i){
        if(i == ints.length-1){
            //判断一下是否重复
            if(!list.contains(ints)){
                list.add(ints);
                return;
            }
        }else{
            for(int j=i;j<ints.length;j++){
                swap(ints,i,j);
                fun(ints,list,i+1);
                swap(ints,i,j);
            }
        }
    }

    //交换数组的两个下标的元素
    private static void swap(Integer[] ints, int i, int j) {
        if (i != j) {
            int t = ints[i];
            ints[i] = ints[j];
            ints[j] = t;
        }
    }
}