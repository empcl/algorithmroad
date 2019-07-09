package list;

/**
 * @Author empcl
 * @Description
 * @Date 2019/5/8
 * @Time 20
 */
public class Node {
    private int val;
    Node next;
    int data;

    public Node(int val) {
        this.val = val;
        this.data = val;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                ", next=" + next +
                ", data=" + data +
                '}';
    }
}
