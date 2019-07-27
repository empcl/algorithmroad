package other;


// 统计一个数字在排序数组中出现的次数。
public class ComputeSameNum {

    public static void main(String[] args) {
        ComputeSameNum computeSameNum = new ComputeSameNum();
        int[] array = {1,2,3,3,3,3,5};
        int nums = computeSameNum.GetNumberOfK(array, 3);
        System.out.println(nums);
    }

    private int GetNumberOfK(int [] array , int k) {
        int len = array.length;
        int last = getLastPositionForK(array,0,len - 1,k);
        int first = getFirstPositionForK(array,0,len - 1,k);
        if (last > -1 && first > - 1)
            return last - first + 1;
        else
            return 0;
    }

    // 获得第一个k出现的位置
    private int getFirstPositionForK(int[] array,int start,int end,int k) {
        if (start > end)
            return -1;
        while (start <= end) {
            int mid = (end - start) / 2 + start;
            if (array[mid] > k)
                end = mid - 1;
            else if (array[mid] < k)
                start = mid + 1;
            else { // 如果k与中间值相等
                // 查看mid左边的第一个数是不是等于k，如果是的话，则说明k所在的第一个位置在当前的左边，
                // 如果不想等的话，则说明当前位置就是k第一个位置。
                if (mid == 0 || array[mid - 1] != array[mid])
                    return mid;
                else
                    end = mid - 1;

            }
        }
        return -1;
    }
    // 获得第二个k出现的位置
    private int getLastPositionForK(int[] array,int start,int end,int k) {
        if (start > end)
            return -1;
        while (start <= end) {
            int mid = (end - start) / 2 + start;
            if (array[mid] > k)
                end = mid - 1;
            else if (array[mid] < k)
                start = mid + 1;
            else {
                if (mid == array.length - 1 || array[mid] != array[mid + 1])
                    return mid;
                else
                    start = mid + 1;
            }
        }
        return -1;
    }
}