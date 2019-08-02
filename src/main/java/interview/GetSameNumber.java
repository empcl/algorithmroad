package interview;

//在一个长度为n的数组里的所有数字都在0到n-1的范围内。
// 数组中某些数字是重复的，但不知道有几个数字是重复的。
// 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
// 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
public class GetSameNumber {
    public static void main(String[] args) {
    }
    public final boolean duplicate(int numbers[],int length,int [] duplication) {
        for (int i = 0;i < length;i++) {
            int num = numbers[i];
            if ( num >= length)
                num -= length;
            if (numbers[num] > length){
                duplication[0] = num;
                return true;
            }
            numbers[num] = numbers[num] + length;
        }
        return false;
    }

    public final boolean duplicate(){
        return false;
    }
}
