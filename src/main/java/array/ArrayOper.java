package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * https://www.cnblogs.com/chenny7/p/4121696.html
 *
 * @Author empcl
 * @Description
 * @Date 2019/5/11
 * @Time 16
 */
public class ArrayOper {

    /**
     * 连续子数组的最大和
     * 输入一个整型数组，数组中连续的一个或多个整数组成一个子数组，每个子数组都有一个和，求所有子数组和的最大值。
     * 例如输入的数组为1,-2,3,10,-4,7,2,-5，和最大的子数组为3,10,-4,7,2，因此输出为该子数组的和18。
     * 另一种解法是用dp解决的，见dp.MaxSubArraySum#getMaxSum(int[])
     *
     * @param a a代表一个整形数组
     * @return 返回连续数组的最大和
     */
    public int max_sub(int[] a) {
        int max = 0;
        int temp_sum = 0;
        int length = a.length;
        int i;
        for (i = 0; i < length; i++) {
            temp_sum += a[i];
            if (temp_sum < 0) {
                temp_sum = 0;
            }
            if (temp_sum > max) {
                max = temp_sum;
            }
        }
        // if all data are negative
        if (max == 0) {
            max = a[0];
            for (i = 1; i < length; i++) {
                if (max < a[i])
                    max = a[i]; // 查看之后的数有没有比当前的数更小了
            }
        }
        return max;
    }


    /**
     * 数对之差的最大值
     * 问题描述：
     * 在数组中，数字减去它右边的数字得到一个数对之差，求所有数对之差的最大值。
     * 例如在数组{2,4,1,16,7,5,11,9}中，数对之差的最大值是11，是16-5的结果。
     *
     * @param a
     * @return
     */

    // way 1
    public int max_diff(int[] a) {
        int length = a.length;
        int[] diff = new int[length - 1];
        for (int i = 0; i < length - 1; i++) {
            diff[i] = a[i] - a[i + 1]; // 求出数组中两两之差，存入数组中，此时，就将问题转换为求出当前数组中连续子数组的最大和
        }
        return max_sub(diff);
    }

    /**
     * 数对之差的最大值
     * 该解法的思想是，找出当前下标下之前的最大值last_max，找到后，last_max - a[i]求出差，与最大差相比较。
     * 当然，这个last_max有两处可能取得，一个是i-1节点前的最大值，也可能是i-1节点处的最大值。
     *
     * @param a
     * @return
     */
    // way 2
    public int max_diff_1(int[] a) {
        int last_max = a[0];
        int max_diff = a[0] - a[1];
        int length = a.length;
        for (int i = 2; i < length; i++) {
            last_max = Math.max(last_max, a[i - 1]);
            int diff = last_max - a[i];
            if (diff > max_diff)
                max_diff = diff;
        }
        return max_diff;
    }

    /**
     * 把数组排成最小的数
     * 输入一个正整数数组，将它们连接起来排成一个数，输出能排出的所有数字中最小的一个。
     * 例如输入数组{32,321}，则输出这两个能排成的最小数字32132.
     *
     * @param a
     * @return
     */
    // way1
    public String minMixedNumber_1(int[] a) {
        String s = "";
        int length = a.length;
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < length; i++)
            list.add(a[i]);
        Collections.sort(list, new Comparator<Integer>() { // 根据比较规则（即两两组合，找出组合最小的那个）对list中的元素进行排序
            // 两两相互比较取值，o1取的是后一个值，o2取得是前一个值。比如说，list={1,2,3}，那么此时，o1=2，o2=1
            // 如果compare方法返回的是>0，则表示o1的值比o2的大，这个时候，继续向后遍历；
            // 如果compare方法返回的是<0，则表示o1的值比o2的小，这个时候，需要将o1与o2之前的值相比较，知道找到compare返回>0的元素，
            // 然后将这两个元素调换位置，继续向后遍历。
            public int compare(Integer o1, Integer o2) {
                String s1 = o1 + "" + o2;
                String s2 = o2 + "" + o1;
                return s1.compareTo(s2);
            }
        });
        for (int j : list)
            s += j; // 将排序后的list逐一输出
        return s;
    }

    // 把数组排成最小的数
    // way2
    public String minMixedNumber_2(int[] a) {
        if (a == null || a.length == 0)
            return "";
        int length = a.length;
        String[] strs = new String[length];
        for (int i = 0; i < length; i++)
            strs[i] = "" + a[i];
        Arrays.sort(strs, new Comparator<String>() {
            public int compare(String o1, String o2) {
                String str1 = o1 + o2;
                String str2 = o2 + o1;
                return str1.compareTo(str2);
            }
        });
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++)
            builder.append(strs[i]);
        return builder.toString();
    }

    // 把数组排成最小的数
    // way 3
    // 与上述做法类似，只不过，自实现了sort排序方法
    public String minMixedNumber_3(int[] a) {
        if (a == null || a.length == 0)
            return "";
        if (a.length == 1)
            return "" + a[0];
        int length = a.length;
        StringBuilder builder = new StringBuilder();
        String[] strs = new String[length];
        for (int i = 0; i < length; i++) {
            strs[i] = "" + a[i];
        }
        strs = sort(strs);
        for (int i = 0; i < length; i++) {
            builder.append(strs[i]);
        }
        return builder.toString();
    }

    public String[] sort(String[] strs) {
        int length = strs.length;
        for (int i = 0; i < length - 1; i++) { // 控制整体循环的次数
            boolean flag = true;
            for (int j = 0; j < length - 1; j++) { // 每一次循环，调整元素的位置
                int res = (strs[j] + strs[j + 1]).compareTo(strs[j + 1] + strs[j]);
                if (res > 0) {
                    String temp = strs[j + 1];
                    strs[j + 1] = strs[j];
                    strs[j] = temp;
                    flag = false;
                }
            }
            if (flag)
                return strs;
        }
        return strs;
    }

    /**
     * 有序数组中和为给定值的两个数
     * 输入一个已经按升序排序过的数组和一个数字，在数组中查找两个数，使得它们的和正好是输入的那个数字。要求时间复杂度是O(n)。
     * 如果有多对数字的和等于输入的数字，输出任意一对即可。例如输入数组1、2、4、7、11、15和数字15。由于4+11=15，因此输出4和11。
     * 由于，该数组是升序的，我们可以准备两个下标s,t，s指向第一个元素，t指向最后一个元素，求出这两个下标代表的值和A。
     * 如果A > target，那么说明t指向的数过大，则将t向后移动一个位置，然后在比较。如果A < target，那么说明s指向的数小了，则要将s向前移动一个位置。
     */
    public void findTwoNumber(int a[], int target) {
        int length = a.length;
        int small = 0;
        int big = length - 1;
        while (small < big) {
            int value = a[small] + a[big];
            if (value == target) {
                System.out.println(a[small] + "," + a[big]);
                return;
            } else if (value > target) {
                big = big - 1;
            } else {
                small = small + 1;
            }
        }
        return;
    }


    /**
     * 扑克牌的顺子
     * 问题描述：
     * 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2-10为数字本身，A为1，J为11，Q为12，K为13，而大小王可以看成任意数字。
     * 因为大小王可以看成任意数字，我们可以将其设置为0。
     * 这道题的思路很简单，先将数组从小到大进行排序，计算出有多少个0，计算出非0有序数之间需要多少个数填，再计算出0有多少个，就可以计算出来了。
     *
     * @param a
     * @return
     */
    public boolean isContinous(int[] a) {
        if (a == null || a.length == 0)
            return false;
        int dis = 0;
        int length = a.length;
        int count_0 = a[0] == 0 ? 1 : 0;
        for (int i = 1; i < length; i++) {
            if (a[i] == 0)
                count_0++;
            int j = 0;
            int t = a[i];
            for (j = i - 1; j >= 0; j--) {
                if (t != 0 && t == a[j])
                    return false;
                else if (t > a[j])
                    break;
                else
                    a[j + 1] = a[j];
            }
            a[j + 1] = t;
        }
        for (int i = 0; i < length - 1; i++) {
            if (a[i] != 0)
                dis = a[i + 1] - a[i] - 1;
        }
        if (dis <= count_0)
            return true;
        else
            return false;
    }

    /**
     * 子数组换位问题
     * 问题描述：
     * 设a[0:n-1]是一个有n个元素的数组，k(0<=k<=n-1)是一个非负整数。
     * 试设计一个算法将子数组a[0:k]与a[k+1,n-1]换位。
     * PS：要求算法在最坏情况下耗时O(n)，且只用到O(1)的辅助空间。
     * 例如，数组 {a0, a1, a2, a3, a4, a5, a6, a7, a8, a9}，
     * 1、若k=4（两个子数组等长），即需要将数组变成：{a5, a6, a7, a8, a9, a0, a1, a2, a3, a4}，两个子数组的长度一样，直接两两交换a[i]与a[i+k]即可；
     * 2、若k=1（后面的子数组更长），即需要将数组变成：{a2, a3, a4, a5, a6, a7, a8, a9, a0, a1}，可以先把第一个子数组交换到整个数组的最后，得到：
     * {a8, a9, a2, a3, a4, a5, a6, a7,  a0, a1}，然后对前面的子数组再交换一次，得到：{a2, a3, a4, a5, a6, a7, a8, a9,  a0, a1}
     * 3、若k=6（前面的子数组更长），即需要将数组变成：{a8, a9, a0, a1, a2, a3, a4, a5, a6, a7}，可以先把第二个子数组交换到整个数组的前面，得到：
     * {a8, a9, a2, a3, a4, a5, a6, a7, a0, a1}，然后问题就变成了怎么把{a2, a3, a4, a5, a6, a7}与{a0, a1}交换了，递归处理即可。
     *
     * @param a
     * @param k
     * @return
     */
    public int[] subArraySwap(int[] a, int k) {
        int[] partition = partition(a, 0, k, a.length - 1);
        return partition;
    }

    public int[] swap(int[] a, int low1, int high1, int low2, int high2) {
        int temp = 0;
        while (low1 <= high1) {
            temp = a[low1];
            a[low1] = a[low2];
            a[low2] = temp;
            low1++;
            low2++;
        }
        return a;
    }

    public int[] partition(int[] a, int low, int k, int high) {
        // 以下标k进行分割，分割后的子数组两两相等
        if ((k - low + 1) == (high - k))
            swap(a, low, k, k + 1, high);
            // 以下标k进行分割，分割后的左边子数组的长度大于右数组的长度
        else if ((k - low + 1) > (high - k)) {
            swap(a, low, low + high - k - 1, k + 1, high);
            partition(a, low + high - k, k, high);
        }
        // 以下标k进行分割，分割后的左边子数组的长度小于右数组的长度
        else {
            swap(a, low, k, high + low - k, high);
            partition(a, low, k, high + low - k - 1);
        }
        return a;
    }

    // 一个数组先递增后递减，要求找到最大值
    // way 1
    public int getMax_1(int[] a) {
        int length = a.length;
        int value = 0;
        for (int i = 1; i < length - 1; i++) {
            if (a[i] >= a[i - 1] && a[i] > a[i + 1]) {
                value = a[i];
                break;
            }
        }
        return value;
    }

    /**
     * 一个数组先递增后递减，要求找到最大值
     *
     * @param a
     * @return 找到返回值，如果没找到则返回-1
     */
    // way 2
    public int getMax_2(int[] a) {
        if (a == null || a.length == 0)
            return -1;
        int length = a.length;
        if (length == 1)
            return a[0];
        if (length == 2)
            return a[0] > a[1] ? a[0] : a[1];
        int low = 0;
        int high = length - 1;
        int mid = low + (high - low) / 2;
        while (low < high) {
            mid = low + (high - low) / 2;
            if (a[mid] > a[mid + 1] && a[mid] > a[mid - 1]) {
                return a[mid];
            } else if (a[mid] < a[mid + 1]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 连续数打乱判断少了哪个数
     * 问题描述：
     * N个连续的数（比如0～999）打乱 之后，随机取出1个数 ，问如何最快速的判断出少了哪一个？
     * https://www.cnblogs.com/chenny7/p/4121696.html
     * 该做法类似于先计算出数组a中的所有元素之和，然后减去b中的所有元素，就得到了少了的那个元素
     */
    public int findLackNumber(int[] a, int[] b) {
        // a未被打散且元素是连续的，b表示被打散且少了一个元素
        int temp = 0;
        int length = a.length;
        for (int i = 0; i < length; i++)
            temp = temp ^ a[i];
        for (int i = 0; i < length - 1; i++)
            temp = temp ^ b[i];
        return temp;
    }

    /**
     * 在一个整型数组中，除了2个数字之外，其他的数字都出现了两次，怎么找到这2个只出现一次的数字？
     * 该做法与上述想法类似。
     * 将所有元素进行异或，因为除了两个数字之外，其他数字都出现了两次。比如说相同的两个元素记为A，那么A^A=0。
     * 根据上面所示，我们知道所有元素的异或之和x就是这两个数字异或的结果，记为x = a^b。因为a!=b,故x != 0，
     * 其二进制表示中至少就有一位1，我们在x中找到第一个为1的位的位置，记为N位。
     * 现在我们根据N位是否为1，将原来的整形数组分为两部分，子数组A中N位都是为1的，子数组B中N为都是为0的。
     * 子数组A/B中，除了一个数以外，其他的数字都是出现两次的。那么对数组A/B进行异或，得到的结果就是出现一次的那个数。
     */
    public String printTwoSingleNumber(int[] a) {
        int length = a.length;
        int s = 0;
        int t = 0;
        int temp = 0; // temp = s ^ t;
        int index = 0; // 记录第一个位为1的下标的位置
        for (int i = 0; i < length; i++)
            temp = temp ^ a[i];
        for (int i = 0; ; i++) {
            index = temp & (0x1 << i);
            if (index > 0)
                break;  // 这个保证index除了其中一个位是1，其他的位都是0
        }

        for (int i = 0; i < length; i++) { // index 000100这种格式
            if ((index & a[i]) > 0) { // 如果数组中的元素在index为1位的对应的位上值是1，则&之后，也是1，那么整体值也是>1的。
                s = s ^ a[i];
            } else {
                t = t ^ a[i];
            }
        }
        return s + "," + t;
    }

    public int findOne(int sum) { // sum = a ^ b
        int index = 0;
        for (int i = 0; ; i++) {
            index = sum & (0x1 << i);
            if (index > 0)
                break;
        }
        return index;
    }

    /**
     * 在一个整型数组中，除了3个数字(a,b,c)之外，其他的数字都出现了两次，怎么找到这3个只出现一次的数字？
     * 该题的思路与上面两题的思路差不多，但是也更复杂。
     * 我们对上面的数字进行异或，得到的结果是x = a ^ b ^ c。
     * 容易证明，x与a、b、c任意一个都不相等。这个我们可以通过反证法进行证明。加入x=a(or b or c)，那么，
     * x ^ a = a ^ b ^ c ^ a ==> 0 = b ^ c => b = c，这与实际条件并不符合故我们可以知道，x与a、b、c都不相等，
     * 此时，a ^ x,b ^ x,c ^ x都不等于0。
     * 这里，我们将上述方法findOne() 记为f()。
     * 考虑f(a^x)^f(b^x)^f(c^x)的值，因为其他的data^x都是出现了两次，异或后的值为0，不做考虑。
     * 根据f(data)方法我们可以知道，当data!=0时候，f(data)返回的是data二进制位上最低位的位1的下标（从0开始）。
     * 考虑三个f(data)的1的位置，如果都是在同一个位置（即f(data)的返回值相等），三个f异或后的值也不为0；如果都不在同一个
     * 位置，三个f异或后的值也不为0，有两个在同一个位置的话，异或后的值也不为0,所以m=f(x^a)^(x^b)^(x^c)!=0。
     * 那么，m的结果二进制中至少有一位是1。假设低位时1的位是第t位，那么x^a,x^b,x^c的结果中，有一个或者三个的第t位是1.
     * 此时，我们假设三个第t位都是等于1的，则a，b，c在t位与x的值相反，a，b，c在t位是相同的。
     * 1）如果a,b,c在t位都等于0，由于x = a ^ b ^ c，则x在t位置也是0，那么，a^x,b^x,c^x在t位异或后也是0，这个与假设矛盾。
     * 2）如果a,b,c在t位都等于1，由于x = a ^ b ^ c，则x在t位置也是1，那么，a^x,b^x,c^x在t位异或后也是0，这个与假设矛盾。
     * 所以，不可能在t位都是为1。
     * 也就是说，a^x,b^x,c^x在t处只有一个为1,根据m=f(a^x)^f(b^x)^f(c^x)(00001000)形式进行分组，首先找到t位为1的那个单独在一组，
     * 然后从所有数中排除出去，然后，剩下的数组中，就剩下两个唯一的数，其他的数都是出现两次。然后，我们就可以运用上述的方法获得a^x,b^x了，
     * 这个时候在异或一下x，就可以得到a、b了。
     * 步骤：
     * 1）计算出三个唯一的数a,b,c异或的值，记为temp = a ^ b ^ c
     * 2）计算出数组中所有的数分别^temp后再^后的值，记为f_temp = f(a^temp) ^ f(b^temp) ^ f(c^temp)
     * 3）找出f_temp中低位第一个为1的二进制（00001000形式），记为index
     * 4）找出((数组中的数)^temp)&index大于1的的所有的数归于一个数组，这样就计算出三个唯一个数中的唯一一个
     * 5）将上述找的那一个数从数组中剔除了，这样，就满足了从一堆出现两次的数，找到两个唯一的数的情况了。
     * 注意：4,5的操作都是在基于^temp的基础上进行的，在取得值了之后，还是需要再^temp，得到原有的值。
     *
     * @param a
     */
    public void printThreeSingleNumber(int[] a) {
        // s,t,r记录三个唯一的数
        int s = 0;
        int t = 0;
        int r = 0;

        int temp = 0;
        int f_temp = 0;
        int lenght = a.length;
        int index = 0;
        int[] a1 = new int[lenght - 1];

        for (int i = 0; i < lenght; i++)
            temp = temp ^ a[i]; // temp = a ^ b ^ c != 0
        for (int i = 0; i < lenght; i++)
            f_temp = f_temp ^ findOne(a[i] ^ temp); // f_temp = f(a^x) ^ f(b^x) ^ f(c^x) != 0
        index = findOne(f_temp); // 记录f_temp位中第一个低位等于1的二进制（是a^x,b^x,c^x中的结果，二进制中一个位是1，其他都是0）
        for (int i = 0; i < lenght; i++) {
            if ((index & (a[i] ^ temp)) > 0) { // 满足条件的都是在某一位上都是为1的，异或的结果就是a^x
                s = s ^ (a[i] ^ temp);
            }
        }
        s = s ^ temp; // 因为从循环得到的结果是a^x,这个时候我们需要再^x，这样才能得到结果a

        // 计算剩下两个唯一的数
        int count = 0;
        for (int i = 0; i < lenght; i++) {
            if (a[i] == s)
                continue;
            a1[count] = a[i];
            count++;
        }
        String[] split = printTwoSingleNumber(a1).split(",");
        r = Integer.parseInt(split[0]) ^ temp;
        t = Integer.parseInt(split[1]) ^ temp;

        System.out.println(s + "," + r + "," + t);
    }

    /**
     * 求数组的主元素  -- 使用hashMap方式不作讨论
     * 我们使用一个计数器count记录元素出现的次数，将第一个元素设置为候补元素，计数为1。
     * 若下一个元素还是相同的数，则count++，若是不相同的数，则count--。
     * 当count为0，则换当前下标的元素为候补元素，以此循环。
     * 我们知道如果一堆数中含有主元素，那么这个计数器肯定是大于0的。但是，count > 0就意味着一堆数中就含有主元素么？
     * 答案肯定是否定的，比如说，4,4,5,5,6。这个时候count>0，但是主元素存在么？不存在的
     * https://www.cnblogs.com/ssyfj/p/9569087.html
     */
    public int majorityNumber(int[] a) {
        int count = 1;
        int majority = a[0];
        int length = a.length;
        for (int i = 1; i < length; i++) {
            if (majority == a[i])
                count++;
            else {
                if (count > 0) {
                    count--;
                } else {
                    majority = a[i];
                    count = 1;
                }
            }
        }
        if (count > 0) {
            count = 0;
            for (int i = 0; i < length; i++) {
                if (a[i] == majority)
                    count++;
            }
            if (count > length / 2)
                return majority;
        }
        return -1;
    }

    /**
     * 二分查找
     */
    public boolean findTwoPoint(int[] a, int k) {
        int length = a.length;
        int low = 0;
        int high = length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] == k) {
                System.out.println("找到了");
                return true;
            } else if (a[mid] > k) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }

    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
     * 要求：时间复杂度为o(lgn)
     * https://www.nowcoder.com/practice/9f3231a991af4f55b95579b44b7a01ba?tpId=13&tqId=11159&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking
     */
    public int minNumberInRotateArray(int[] array) {
        int len = array.length;
        int index1 = 0;
        int index2 = len - 1;
        int midIndex = index1;
        while (array[index1] >= array[index2]) {
            if (index2 - index1 == 1) {
                midIndex = index2;
                break;
            }
            midIndex = (index2 - index1) / 2 + index1;
            if (array[midIndex] == array[index1] && array[midIndex] == array[index2]) {
                return minNumber(array, index1, index2);
            }
            if (array[index1] <= array[midIndex])
                index1 = midIndex;
            if (array[index2] >= array[midIndex])
                index2 = midIndex;
        }
        return array[midIndex];
    }

    private int minNumber(int[] arr, int index1, int index2) {
        int min = arr[index1];
        for (int i = index1 + 1; i <= index2; i++) {
            if (arr[i] < min)
                min = arr[i];
        }
        return min;
    }

    public ArrayList<String> permutation(Integer[] a) {
        ArrayList<String> list = new ArrayList<>();
        func(a,list,0);
        return list;
    }

    private void func(Integer[] a,ArrayList<String> list,int pos) {
        if (pos == a.length - 1) {
            String tmp = "";
            for (Integer ele:a) {
                tmp += ele + "|";
            }
            if (!list.contains(tmp)) {
                list.add(tmp);
                return;
            }
        } else {
            for (int i = pos;i < a.length;i++) {
                swap(a,pos,i);
                func(a,list,pos + 1);
                swap(a,pos,i);
            }
        }
    }

    private void swap(Integer[] a,int pos,int i) {
        if (pos != i) {
            Integer temp = a[pos];
            a[pos] = a[i];
            a[i] = temp;
        }
    }

}
