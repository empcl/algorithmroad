package tree;

/**
 * @Author empcl
 * @Description
 * @Date 2019/5/18
 * @Time 20
 */
public class TreeNode {
    public int data;
    public TreeNode leftChild;
    public TreeNode rightChild;
    int maxLeft;
    int maxRight;

    public TreeNode(int data) {
        this.data = data;
    }
}
