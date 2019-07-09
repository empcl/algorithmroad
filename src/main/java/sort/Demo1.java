package sort;

import java.util.Stack;

/**
 * @Author empcl
 * @Description
 * @Date 2019/5/14
 * @Time 21
 */
public class Demo1 {

    public int minNumberInRotateArray(int [] array) {
        int len = array.length;
        if (len == 0)
            return 0;
        int midIndex = 0;
        int low = 0;
        int high = len - 1;
        midIndex = low;
        while (array[low] >= array[high]){
            if (low + 1 == high){
                midIndex = high;
                break;
            }
            midIndex = (high - low) / 2 + low;
            if (array[midIndex] == array[low] && array[midIndex] == array[high])
                return  minNumber(array,low,high);
            if (array[midIndex] >= array[low])
                low = midIndex;
            if (array[midIndex] <= array[high])
                high = midIndex;
        }
        return array[midIndex];
    }

    private int minNumber(int[] a,int low,int high){
        int min = a[low];
        for (int i = low + 1;i <= high;i++){
            if (a[i] < min)
                min = a[i];
        }
        return min;
    }

    public static String replaceSpace(StringBuffer str) {
        char[] chars = str.toString().toCharArray();
        int len = chars.length;
        int countBlank = 0;
        for (int i = 0;i < len;i++){
            if (chars[i] == ' ')
                countBlank++;
        }
        int newLen = countBlank * 3 + len;
        char[] newChars = new char[newLen];
        int newIndex = newLen - 1;
        for (int i = len - 1;i >= 0;i--){
            if (chars[i] == ' '){
                newChars[newIndex--] = '0';
                newChars[newIndex--] = '2';
                newChars[newIndex--] = '%';
            }else {
                newChars[newIndex--] = chars[i];
            }
        }
        String res = "";
        for (char c:newChars){
            res += c;
        }
        return res;
    }

    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        int[] a = {3, 4, 5, 1, 2};
//        int i = demo1.minNumberInRotateArray(a);
//        System.out.println(i);
        SortOper sortOper = new SortOper();
        int[] ints1 = sortOper.mergeSort(a,0,4);
        System.out.println(ints1);
//        int[] a = {77,78,45,444,332,111};
//        int[] a = {8, 4, 9, 3, 2};
        int[] ints = sortOper.mergeSort(a,0,4);
//        int[] a = {45,77,78,111,444};
//        int[] ints = sortOper.quickSort(a, 0, 4);
//        int[] ints = sortOper.heapSort(a);
//        int[] ints = sortOper.radixSort(a,100);
        System.out.println(ints);
    }
}

//import java.io.*;
//        import java.util.*;
//public class Main {
//    public static int InversePairs(int [] array) {
//        if(array==null||array.length==0)
//        {
//            return 0;
//        }
//        int[] copy = new int[array.length];
//        for(int i=0;i<array.length;i++)
//        {
//            copy[i] = array[i];
//        }
//        int count = InversePairsCore(array,copy,0,array.length-1);//数值过大求余
//        return count;
//
//    }
//    private  static int InversePairsCore(int[] array,int[] copy,int low,int high)
//    {
//        if(low==high)
//        {
//            return 0;
//        }
//        int mid = (low+high)>>1;
//        int leftCount = InversePairsCore(array,copy,low,mid)%1000000007;
//        int rightCount = InversePairsCore(array,copy,mid+1,high)%1000000007;
//        int count = 0;
//        int i=mid;
//        int j=high;
//        int locCopy = high;
//        while(i>=low&&j>mid)
//        {
//            if(array[i]>array[j])
//            {
//                count += j-mid;
//                copy[locCopy--] = array[i--];
//                if(count>=1000000007)//数值过大求余
//                {
//                    count%=1000000007;
//                }
//            }
//            else
//            {
//                copy[locCopy--] = array[j--];
//            }
//        }
//        for(;i>=low;i--)
//        {
//            copy[locCopy--]=array[i];
//        }
//        for(;j>mid;j--)
//        {
//            copy[locCopy--]=array[j];
//        }
//        for(int s=low;s<=high;s++)
//        {
//            array[s] = copy[s];
//        }
//        return (leftCount+rightCount+count)%1000000007;
//    }
//
//    public static void main (String[] args) throws java.lang.Exception
//    {
//        int[] array={4,3,2,1};
//
//        //System.out.println(InversePairs(array));
//        System.out.println(2519);
//        System.out.println(24903408);
//        System.out.println(493330277);
//        System.out.println(988418660);
//    }
//
//}
