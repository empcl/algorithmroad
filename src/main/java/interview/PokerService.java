package interview;

import java.util.LinkedList;


// https://blog.csdn.net/qinfeng9988/article/details/83868644
public class PokerService {
    /**
     * 正向，从手到桌子 t2 = {1,12,2,8,3,11,4,9,5,13,6,10,7};
     * 返回 ｛13,12,11,10,9,8,7,6,5,4,3,2,1};
     *
//     * @param pokers
     */
    private static LinkedList<Integer> sort2(LinkedList<Integer> pokerList) {
        //13张牌转换成数组  方便操作，不用考虑太多
//        LinkedList<Integer> pokerList = new LinkedList<>();
//        for (int poker : pokerList) {
//            pokerList.add(poker);
//        }
        //声明一个新的容器，在这里可以理解成桌子
        LinkedList<Integer> newPokers2 = new LinkedList<>();
        int len = pokerList.size();
        for (int i = 0; i < len; i++) {
            newPokers2.addFirst(pokerList.pollFirst());
            if (pokerList.size() > 1)
                pokerList.addLast(pokerList.pollFirst());
        }
        for (int ele : newPokers2)
            System.out.print(ele + " ");
        return newPokers2;
    }

    /**
     * 这里的操作是从桌子把牌拿回到手上
     * 从桌子 到 手上 int[] t = {13,12,11,10,9,8,7,6,5,4,3,2,1};
     * 返回 {1,12,2,8,3,11,4,9,5,13,6,10,7}
     */
    private static LinkedList<Integer> sort1(int[] pokers) {
        //从数组转换成list,只是为了方便操作，不用考虑其它的
        LinkedList<Integer> pokerList = new LinkedList<>();
        for (int ele : pokers)
            pokerList.add(ele);
        //声明一个目标容器，理解成手
        LinkedList<Integer> newPokers2 = new LinkedList<>();
        // 这是一个从桌子到手的过程，在这个反向的过程中，我们需要顺序做两件事：
        //   1.在正向（从手到桌子）的操作中，我们将扑克牌放到桌子上后，会将当前手中扑克牌的下一张放在底部。
        //     这个时候，我们反向操作的时候，会将这个底部的牌放在顶端。
        //   2.再将牌放在顶端。这样反复进行下去，知道遍历完数字。

        for (int ele : pokerList) {

            if (newPokers2.size() > 1) {
                newPokers2.addFirst(newPokers2.pollLast());
            }
            newPokers2.addFirst(ele);
        }
        for (int ele : newPokers2)
            System.out.print(ele + " ");
        System.out.println();
        return newPokers2;
    }

    public static void main(String[] args) {
        int[] a = {13,12,11,10,9,8,7,6,5,4,3,2,1};
        sort2(sort1(a));
    }
}
