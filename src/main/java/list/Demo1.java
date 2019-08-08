package list;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author empcl
 * @Description
 * @Date 2019/5/8
 * @Time 23
 */
public class Demo1 {
    public static void main(String[] args) {

        ListOper listOper = new ListOper();
        listOper.addNode(1);
//        listOper.addNode(1);
        listOper.addNode(2);
        listOper.addNode(6);
        listOper.addNode(3);
        listOper.addNode(2);
        listOper.addNode(4);
        listOper.addNode(5);
        Solution solution = new Solution();
        ArrayList<Integer> integers =
                solution.printListFromTailToHead(listOper.head);
//        Node elem = listOper.findElem(6);
//        System.out.println(elem);
//        listOper.orderList();
//        listOper.printList();
//        listOper.addNode(6);
//        listOper.addNode(5);
//        listOper.addNode(3);
//        listOper.addNode(5);
//        listOper.addNode(4);
//        Boolean aBoolean = listOper.deleteNode(4);
//        System.out.println(aBoolean);
//        Node node = listOper.orderList();
//        System.out.println(node);
        Node node = listOper.deleteDuplicateNode2();
        System.out.println(node);
//        Node node = listOper.findElemTurnOver(3);
//        Node node = listOper.reverseIteratively();
//        listOper.printListReversely(listOper.head);
//        Node node = listOper.searchMid(listOper.head);
//        System.out.println(node);
//        Node elem = listOper.findElem(13);
//        Node node = listOper.findElemTurnOver(1);
//        Node node = listOper.reverseIteratively();
//        listOper.printListReversely(listOper.head);
//        Node node = listOper.searchMid(listOper.head);
//        Node node = listOper.findElem(3);
//        boolean b = listOper.deleteNode(node);
//        listOper.printList();
//        boolean b = listOper.deleteNode(listOper.head);
//        System.out.println(b);
    }
}
