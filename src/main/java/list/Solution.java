package list;

import tree.TreeNode;

import java.util.Stack;

/**
 * @author empcl
 * @note
 * @since 2019/6/16 22:54
 */
public class Solution {
    public static Node deleteDuplication(Node pHead) {
        if (pHead == null) {
            return null;
        }
        Node preNode = null;
        Node node = pHead;
        while (node != null) {
            if (node.next != null && node.data == node.next.data) {
                int value = node.data;
                while (node.next != null && node.next.data == value) {
                    node = node.next;
                }
                if (preNode == null) {
                    pHead = node.next;
                } else {
                    preNode.next = node.next;
                }
            } else {
                preNode = node;
            }
            node = node.next;
        }
        return pHead;
    }


    public static void main(String[] args) {
        ListOper listOper = new ListOper();
        listOper.addNode(1);
//        listOper.addNode(1);
        listOper.addNode(3);
//        listOper.addNode(3);
        listOper.addNode(4);
//        listOper.addNode(5);
        listOper.addNode(5);
        listOper.addNode(6);

    }
}