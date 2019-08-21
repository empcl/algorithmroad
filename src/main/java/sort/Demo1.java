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
        int[] a = {3, 5, 4, 1, 2};
        SortOper sort = new SortOper();
        int[] ints = sort.heapSort(a);
        System.out.println(ints);
    }
}
