package recursive;

/**
 * @Author empcl
 * @Description
 * @Date 2019/5/19
 * @Time 23
 */

// https://www.cnblogs.com/ysocean/p/8005694.html#_label5
public class CommonDemo {
    // n!
    public int getFactorial(int n) {
        if (n >= 0) {
            if (n == 0) {
                System.out.println(n + "!=" + 1);
                return 1;
            } else {
                System.out.println(n);
                int temp = n * getFactorial(n - 1);
                System.out.println(n + "!=" + temp);
                return temp;
            }
        }
        return -1;
    }

    // 二分查找 -- 查找数组必须有序
    public int findTwoPoint(int[] a, int k, int low, int high) {
        int mid = (high - low) / 2;
        if (a[mid] == k) {
            return mid;
        } else if (low > high) {
            return -1;
        } else {
            if (a[mid] < k) {
                return findTwoPoint(a, k, mid + 1, high);
            }
            if (a[mid] > k) {
                return findTwoPoint(a, k, low, mid - 1);
            }

        }
        return -1;
    }

    // Hannota 汉罗塔
    public void move(int dish, String from, String temp, String to) {
        if (dish == 1) {
            System.out.println(dish + " from " + from + " to " + to);
        } else {
            move(dish - 1, from, to, temp);
            System.out.println(dish + " from " + from + " to " + to);
            move(dish - 1, temp, from, to);
        }
    }

    // x ^ y
    // x ^ y = (x^2)^(y/2) = a^b = (a^2)^(b/2) = c ^ d = ....
    public int pow(int x, int y) {
        if ((x == 0 && y == 0) || (x == 1)) {
            return 1;
        }
        if (x == 0 && y > 0) {
            return 0;
        }
        if (y > 1){
            int a = x * x;
            int b = y / 2;
            if (y % 2 == 1){
                return pow(a,b) * x;
            }else{
                return pow(a,b);
            }
        }else if (y == 1){
            return x;
        }else { // y = 0
            return 1;
        }
    }

    //
}
