package list;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author empcl
 * @since 2019/6/16 22:54
 */
public class Solution {

    public ArrayList<Integer> printListFromTailToHead(Node listNode) {
        Node head = new Node(-1);
        Node currentNode = listNode;
        Node tmpNode = null;
        while (currentNode != null) {
            tmpNode = currentNode;
            currentNode = currentNode.next;
            tmpNode.next = head.next;
            head.next = tmpNode;
        }

        ArrayList<Integer> list = new ArrayList<Integer>();
        currentNode = head.next;
        while (currentNode != null) {
            list.add(currentNode.data);
            currentNode = currentNode.next;
        }
        return list;
    }


    private static Node deleteDuplication(Node pHead)
    {
        Node first = new Node(-1);
        first.next = pHead;
        Node last = first;
        Node p = pHead;

        while (p != null && p.next != null) {
            if (p.data == p.next.data) {
                int val = p.data;
                while (p != null && val == p.data) {
                    p = p.next;
                    last.next = p;
                }
            } else {
                last = p;
                p = p.next;
            }
        }
        return first.next;
    }


    public static void main(String[] args) {
        ListOper listOper = new ListOper();
        listOper.addNode(1);
        listOper.addNode(1);
        listOper.addNode(1);
        listOper.addNode(1);
        listOper.addNode(1);
        listOper.addNode(1);
//        listOper.addNode(3);
//        listOper.addNode(3);
//        listOper.addNode(4);
//        listOper.addNode(5);
//        listOper.addNode(5);
//        listOper.addNode(6);
        Node node = deleteDuplication(listOper.head);
        System.out.println(node);
    }
}