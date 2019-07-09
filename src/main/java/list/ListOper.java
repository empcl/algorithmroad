package list;

import java.util.HashMap;

/**
 * https://mp.weixin.qq.com/s/Q_3gi94u0lNSsJhKgAULTA
 *
 * @Author empcl
 * @Description
 * @Date 2019/5/8
 * @Time 20
 */
public class ListOper {
    Node head = null;

    /**
     * 添加节点到链表中
     *
     * @param val 需要添加节点的值
     */
    public void addNode(int val) {
        Node node = new Node(val);
        if (head == null) {
            head = node;
            return;
        }
        Node newNode = head;
        while (newNode.next != null) {
            newNode = newNode.next;
        }
        newNode.next = node;
    }

    /**
     * 返回链表的长度
     *
     * @return 链表的长度
     */
    public int length() {
        int length = 0;
        Node tmp = head;
        while (tmp != null) {
            tmp = tmp.next;
            length++;
        }
        return length;
    }

    /**
     * 删除第index个节点
     *
     * @param index
     * @return 如果删除成功将会返回true，如果失败则返回false
     */
    public Boolean deleteNode(int index) {
        int length = length();
        // 判断index是否合规
        if (index < 1 || index > length)
            return false;
        // index = 1，直接删除
        if (index == 1) {
            head = head.next;
            return true;
        }
        // index != 1，需要遍历链表，找到删除的前一个节点
//        int i = 2; // 此处为2，因为curNode初始化位置位于第二个节点
//        Node preNode = head;
//        Node curNode = preNode.next;
//        while (curNode != null) {
//            if (i == index) {
//                preNode.next = curNode.next;
//                return true;
//            }
//            preNode = curNode;
//            curNode = curNode.next;
//            i++;
//        }
//        return false;
        int i = 1;
        Node preNode = head;
        Node curNode = head;
        while (i < index) {
            preNode = curNode;
            curNode = curNode.next;
            i++;
        }
        preNode.next = curNode.next;
        return true;
    }

    /**
     * 对链表进行排序
     *
     * @return 返回排序后的链表
     */
    public Node orderList() {
        Node curNode = head;
        Node nextNode = head;
        int tmp = 0;
        while (curNode.next != null) {
            nextNode = curNode.next;
            while (nextNode != null) {
                if (curNode.data > nextNode.data) {
                    tmp = curNode.data;
                    curNode.data = nextNode.data;
                    nextNode.data = tmp;
                }
                nextNode = nextNode.next;
            }
            curNode = curNode.next;
        }
        return head;
    }

    /**
     * 打印链表中的元素
     */
    public void printList() {
        Node tmp = head;
        while (tmp != null) {
            System.out.println(tmp.data);
            tmp = tmp.next;
        }
    }

    /**
     * 通过查看当前节点是否出现过，如果出现过就删除当前节点
     *
     * @return
     */
    public Node deleteDuplicateNode1() {
        if (length() == 0)
            return null;
        if (length() == 1)
            return head;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        Node tmp = head;
        Node preNode = null;
        while (tmp != null) {
            if (map.containsKey(tmp.data)) {
                preNode.next = tmp.next;
            } else {
                map.put(tmp.data, 1);
                preNode = tmp;
            }
            tmp = tmp.next;
        }
        return head;
    }


    /**
     * 双层循环
     * 时间复杂度 o(n^2)
     *
     * @return
     */
    public Node deleteDuplicateNode2() {
        Node p = head;
        while (p != null) {
            Node q = p;
            while (q.next != null) {
                if (p.data == q.next.data) {
                    q.next = q.next.next;
                } else {
                    q = q.next;
                }
            }
            p = p.next;
        }
        return head;
    }

    /**
     * 寻找第index个节点
     *
     * @param index
     * @return
     */
    public Node findElem(int index) {
        if (index < 1 || length() < index)
            return null;
//        int tmp = 1;
//        Node p = head;
//        while (tmp <= index) {
//            if (tmp == index) {
//                return p;
//            }
//            p = p.next;
//            tmp++;
//        }
//        return p;
        Node curNode = head;
        int i = 1;
        while (i < index) {
            curNode = curNode.next;
            i++;
        }
        return curNode;
    }

    /**
     * 寻找倒数第index个节点
     *
     * @return
     */
    public Node findElemTurnOver(int index) {
        Node p = head;
        Node q = head;
        for (int i = 0; i < index - 1; i++) {
            q = q.next;
        }
        while (q.next != null) {
            p = p.next;
            q = q.next;
        }
        return p;
    }

    /**
     * 实现链表的反转
     *
     * @return
     */
    public Node reverseIteratively() {
        Node pReservedHead = head;
        Node pNode = head;
        Node pPrev = null;
        while (pNode != null) {
            Node pNext = pNode.next;
            if (pNext == null) {
                pReservedHead = pNode;
            }
            pNode.next = pPrev;
            pPrev = pNode;
            pNode = pNext;
        }
        this.head = pReservedHead;
        return head;
    }

    /**
     * 通过递归从尾到头输出单链表
     *
     * @param head
     */
    public void printListReversely(Node head) {
        if (head != null) {
            printListReversely(head.next);
            System.out.println(head.data);
        }
    }

    /**
     * 查询单链表的中间节点
     */
    public Node searchMid(Node node) {
        Node p = node; // p走一步
        Node q = node; // q走两步
        while (q != null && q.next != null && q.next.next != null) {
            p = p.next;
            q = q.next.next;
        }
        return p;
    }

    /**
     * 在不知道头指针的情况下删除指定节点
     *
     * @param node 需要删除的节点
     * @return 如果删除成功返回true，如果返回失败则返回false
     * 注意：
     * 这个方法仅适用于删除非尾节点的节点
     * 有个方法是这样的，将要删除节点的的内存只想null，这样前一个节点指向的是一个null，
     * 这样就可以删除了最后一个节点。
     * 但是，这个方法是不可行的。因为null在系统中也是一个有地址的区域，也需要前一个节点的指针指向
     * 这块区域，但是现在拿不到前一个节点，因此还是没有办法删除。
     */
    public boolean deleteNode(Node node) {
        if (node == null || node.next == null) {
            return false;
        }
        int tmp = node.next.data;
        node.data = tmp;
        node.next = node.next.next;
        return true;
    }

    // https://blog.csdn.net/fengxinlinux/article/details/78885764

    /**
     * 判断两个链表是否相交
     *
     * @param node1
     * @param node2
     * @return
     */
    public boolean isIntersect(Node node1, Node node2) {
        if (node1 == null || node2 == null)
            return false;
        Node tail1 = node1;
        while (tail1.next != null)
            tail1 = tail1.next;
        Node tail2 = node2;
        while (tail2.next != null)
            tail2 = tail2.next;
        return tail1 == tail2;
    }

    /**
     * 找出相交的第一个节点
     *
     * @param node1
     * @param node2
     * @return
     */
    public Node getFirstMeetNode(Node node1, Node node2) {
        int len1 = 1;
        Node tail1 = node1;
        while (tail1.next != null) {
            tail1 = tail1.next;
            len1++;
        }
        int len2 = 1;
        Node tail2 = node2;
        while (tail2.next != null) {
            tail2 = tail2.next;
            len2++;
        }
        if (len2 > len1) {
            int dis = len2 - len1;
            while (dis > 0) {
                node2 = node2.next;
                dis--;
            }
        } else {
            int dis = len1 - len2;
            while (dis > 0) {
                node1 = node1.next;
                dis--;
            }
        }

        while (node1 != node2) {
            node1 = node1.next;
            node2 = node2.next;
        }

        return node1;
    }

    /**
     * 判断链表是否有环,并返回入环节点
     * 步骤1：
     * 设置两个指针，慢的p走一步，快的q走两步
     * 步骤2：
     * 如果相遇（p == q），则表示有环
     * 步骤3：
     * 在相遇点，将快的指针q返回到头指针，此时，快慢指针同时走动，再次相遇表示当前为入环点
     * <p>
     * 以下是证明，a表示离入环点的功能，x表示入环后已经走过的节点数，y表示环内还没有走过的节点数。
     * 根据下面文章说的a = (n - 1)r + y，可以知道，当快的指针从头结点走a [ =(n-1)r + y]个节点的时候，将会到达入环节点，
     * 这个时候慢的节点也会走完剩下的y个节点数或者也可能需要绕圈。当两个指针再次相遇的时候，就是入环节点了。
     * https://www.cnblogs.com/mukekeheart/p/5664321.html
     */
    public Node judgeRing() {
        Node p = head; // 慢走一步
        Node q = head; // 快走两步
        while (q != null && q.next != null) {
            p = p.next;
            q = q.next.next;
            if (p == q) {
                q = head;
                while (p != q) {
                    p = p.next;
                    q = q.next;
                }
                return p;
            }
        }
        return null;
    }
}
