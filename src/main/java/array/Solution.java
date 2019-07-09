package array;

import java.util.ArrayList;

/**
 * @author empcl
 * @note
 * @since 2019/6/10 21:43
 */
public class Solution {
    public static int Add(int num1, int num2) {
        while (num2 != 0) {
            int temp = num1 ^ num2; // 不考虑进位
            num2 = (num1 & num2) << 1; // 考虑进位
            num1 = temp;
        }
        return num1;
    }

    public static int NumberOf1Between1AndN_Solution(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            while (i != 0) {
                int tmp = i % 10;
                if (tmp == 1)
                    sum = sum + 1;
                i = i / 10;
            }
        }
        return sum;
    }

    public static String LeftRotateString(String str,int n) {
        char[] chars = str.toCharArray();
        int len = chars.length;
        if (n >= len)
            return str;
        char[] partition = partition(chars,0,n - 1,len - 1);
        StringBuffer sb = new StringBuffer();
        for (char c:partition)
            sb.append(c);
        return sb.toString();
    }

    private static char[] partition(char[] chars,int low,int k,int high){
        if (k - low + 1 == high - k){
            swap(chars,low,k,k+1,high);
        } else if (k - low + 1 < high - k){
            swap(chars,low,k,high + low - k,high);
            partition(chars, low, k, high + low - k - 1);
        } else {
            swap(chars,low,low + high - k - 1,k + 1,high);
            partition(chars,low + high - k,k,high);
        }
        return chars;
    }

    private static void swap(char[] chars,int low1,int high1,int low2,int high2){
        while (low1 <= high1){
            char temp = chars[low1];
            chars[low1] = chars[low2];
            chars[low2] = temp;
            low1++;
            low2++;
        }
    }

    public static String ReverseSentence(String str) {
        String[] strs = str.split(" ");
        int len = strs.length;
        int low = 0;
        int high = len - 1;
        while (low < high){
            swap(strs,low,high);
            low++;
            high--;
        }
        StringBuffer sb = new StringBuffer();
        for (String c : strs)
            sb.append(c);
        return sb.toString().substring(0,sb.toString().lastIndexOf(" "));
    }

    private static void swap(String[] strs,int low,int high){
        String temp = strs[low] + " ";
        strs[low] = strs[high] + " ";
        strs[high] = temp;
    }

    public static boolean isContinuous(int [] numbers) {
        int len = numbers.length;
        insertionSort(numbers,len);
        int count = 0;
        for (int i = 0;i < len;i++){
            if (numbers[i] == 0)
                count++;
            else
                break;
        }
        int diff = 0;
        for (int i = count;i < len - 1;i++)
            diff = diff + numbers[i + 1] - numbers[i] - 1;
        if (count >= diff)
            return true;
        else
            return false;
    }

    // 直插法
    private static void insertionSort(int[] numbers,int len){
        for (int i = 1;i < len;i++){
            int temp = numbers[i];
            int j;
            for (j = i - 1;j >= 0;j--){
                if (temp > numbers[j])
                    break;
                else
                    numbers[j + 1] = numbers[j];
            }
            numbers[j + 1] = temp;
        }
    }

    public static int LastRemaining_Solution(int n, int m) {
        if (n == 0)
            return -1;
        int res = 0;
        for (int i = 2;i <= n;i++){
            res = (res + m) % i;
        }
        return res;
    }

    public static boolean duplicate(int numbers[],int length,int [] duplication) {
        for (int i = 1;i < length;i++){
            int temp = numbers[i];
            int j = 0;
            for (j = i - 1;j >= 0;j--){
                if (temp <= numbers[j])
                    numbers[j + 1] = numbers[j];
                else
                    break;
            }
            numbers[j + 1] = temp;
        }
        int index= 0;
        boolean flag = false;
        for (index = 0;index < length - 1;index++){
            if (numbers[index] == numbers[index+1]){
                flag = true;
                break;
            }
        }
        duplication[0] = numbers[index];
        if (flag)
            return true;
        else
            return false;
    }

    public static ArrayList<Integer> maxInWindows(int [] num, int size) {
        int len = num.length;
        ArrayList<Integer> list = new ArrayList<Integer>();
        int[] newNum = new int[size];
        for (int i = 0;i < len - (size - 1);i++) {
            for (int j = i;j < i +size;j++)
                newNum[j - i] = num[j];
            list.add(max(newNum,size));
        }
        return list;
    }

    private static int max(int[] a,int len){
        int max = a[0];
        for (int i = 1;i < len;i++)
            if (max < a[i])
                max = a[i];
        return max;
    }

    ArrayList<Integer> list = new ArrayList<Integer>();
    public void Insert(Integer num) {
        list.add(num);
    }

    public Double GetMedian() {
        int size = list.size();
        int[] ints = new int[size];
        int index = 0;
        for (Integer ele:list)
            ints[index++] = ele;
        sort(ints,0,size - 1);
        if (size % 2 == 1)
            return 1.0 * ints[size / 2];
        else
            return 1.0 * (ints[size / 2] + ints[size / 2 - 1]) / 2;
    }

    // 快排
    private void sort(int[] ints,int start,int end){
        if (start < end){
            int pivotIndex = getPivotIndex(ints,start,end);
            sort(ints,start,pivotIndex - 1);
            sort(ints,pivotIndex + 1,end);
        }
    }

    private int getPivotIndex(int[] ints,int start,int end){
        int temp = ints[start];
        while (start < end){
            while (start < end && ints[end] >= temp)
                end--;
            ints[start] = ints[end];
            while (start < end && ints[start] <= temp)
                start++;
            ints[end] = ints[start];
        }
        ints[start] = temp;
        return start;
    }

    public static void main(String[] args) {
//        int[] test = {1,2,3,4,5,6,7,0};
        int[] test = {7,5,6,4};
        int i1 = inversePairs(test);
        System.out.println(i1);
        int[] num = {2,3,4,2,6,2,5,1};
        ArrayList<Integer> integers = maxInWindows(num, 3);
        System.out.println(integers);
        int[] ints = {1,3,4,2,4};
        int[] duplication = new int[1];
        boolean duplicate = duplicate(ints, 5, duplication);
        System.out.println(duplicate);
//        int add = Add(10, 12);
//        System.out.println(add);
//        int i = NumberOf1Between1AndN_Solution(13);
//        System.out.println(i);
        String str = "I am a student.";
//        String s = LeftRotateString(str, 8);
        String s = ReverseSentence(str);
        System.out.println(s);

        int[] numbers = {0,3,1,6,4};
        boolean continuous = isContinuous(numbers);
        System.out.println(continuous);

        int i = LastRemaining_Solution(5, 2);
        System.out.println(i);
    }

    public static int inversePairs(int [] array) {
        int len = array.length;
        if (len == 0)
            return 0;
        int[] copy = new int[len];
        int index = 0;
        for (int ele : array)
            copy[index++] = ele;
        return countInversePairs(array,copy,0,len - 1) % 1000000007;
    }

    private static int countInversePairs(int[] array,int[] copy,int start,int end) {
        if (start == end) {
            copy[start] = array[start];
            return 0;
        }
        int half = (end - start) / 2 + start;
        int left = countInversePairs(copy,array,start,half);
        int right = countInversePairs(copy,array,half + 1,end);

        int i = half;
        int j = end;
        int copyIndex = end;
        int count = 0;

        while (i >= start && j >= half + 1) {
            if (array[i] > array[j]) {
                count = count + j - half;
                copy[copyIndex--] = array[i--];
            } else {
                copy[copyIndex--] = array[j--];
            }
        }

        while (i >= start)
            copy[copyIndex--] = array[i--];
        while (j >= half + 1)
            copy[copyIndex--] = array[j--];
        return left + right + count;
    }
}
