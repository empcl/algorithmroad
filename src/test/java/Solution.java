import java.util.ArrayList;
import java.util.Stack;

/**
 * @Author empcl
 * @Description
 * @Date 2019/5/28
 * @Time 16
 */
public class Solution {
    public static boolean find(int target, int[][] array) {
        int row = array.length;
        int col = array[0].length;
        while (row >= 0 && col - 1 >= 0) {
            int value = array[row - 1][col - 1];
            if (target == value)
                return true;
            else if (target > value)
                col--;
            else
                row--;
        }
        return false;
    }

    public static String replaceSpace(StringBuffer str) {
        String s = str.toString();
        return s.replaceAll(" ", "%20");
    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        Stack<Integer> stack = new Stack<Integer>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            list.add(pop);
        }
        return list;
    }

    ArrayList<Integer> list = new ArrayList<Integer>();

    public ArrayList<Integer> printListFromTailToHeadRecursive(ListNode listNode) {
        if (listNode.next == null)
            list.add(listNode.val);
        printListFromTailToHead(listNode.next);
        return list;
    }

    public static int NumberOf1(int n) {
//        n.toBinaryString().toCharArray();
        char[] chars = Integer.toBinaryString(n).toCharArray();
        int count = 0;
        int len = chars.length;
        for (int i = 0; i < len; i++) {
            if (chars[i] == 1)
                count++;
        }
        return count;
    }

    public ListNode ReverseList(ListNode head) {
        ListNode pPrev = null;
        ListNode pNode = head;
        ListNode pReversedHead = head;

        while (pNode.next != null) {
            ListNode pNext = pNode.next;
            if (pNext == null)
                pReversedHead = pNode;
            pNode.next = pPrev;
            pPrev = pNode;
            pNode = pNext;
        }
        return pReversedHead;
    }

    public static boolean isContinuous(int[] numbers) {
        int len = numbers.length;
        int count = numbers[0] == 0 ? 1 : 0;
        for (int i = 1; i < len; i++) {
            int current = numbers[i];
            int j;
            if (current == 0)
                count++;
            for (j = i - 1; j >= 0; j--) {
                if (current < numbers[j])
                    numbers[j + 1] = numbers[j];
                else if (current > numbers[j])
                    break;
                else {
                    if (numbers[i] != 0 && numbers[j] == numbers[i])
                        return false;
                }
            }
            numbers[j + 1] = current;
        }
        int diff = 0;
        for (int i = count; i < len - 1; i++) {
            diff = numbers[i + 1] - numbers[i] - 1;
        }
        if (count >= diff)
            return true;
        else
            return false;
    }

    public static int findSameNumber(int[] a,int length) {
        for (int i = 0;i < length;i++) {
            int index = a[i];
            if (index >= length)
                index -= length;
            if (a[index] >= length)
                return index;
            a[index] = a[index] + length;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] numbers = {0,1,1,2,2};
        int sameNumber = findSameNumber(numbers, 5);
        System.out.println(sameNumber);
        boolean continuous = isContinuous(numbers);
        System.out.println(continuous);
//        int[][] ints = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
//        System.out.println(find(20, ints));
//        StringBuffer buffer = new StringBuffer("t o m");
//        String s = replaceSpace(buffer);
//        System.out.println(s);
//
//        int i = NumberOf1(-5);
        ListNode node = new ListNode(1);

    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
