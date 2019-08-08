package interview;

import tree.TreeNode;
import tree.TreeOper;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Module Desc:
 *  打印树节点
 * User: empcl
 * DateTime: 2019/8/3 9:19
 */
public class PrintTree {
    public void printTreeByLevel(TreeNode treeNode) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode current = null;
        queue.add(treeNode);
        int size = 1;
        int currentSize = 0;
        while (!queue.isEmpty()) {
            currentSize++;
            current = queue.poll();
            if (current.leftChild != null) {
                queue.add(current.leftChild);
            }
            if (current.rightChild != null) {
                queue.add(current.rightChild);
            }
            System.out.print(current.data + " ");
            if (currentSize == size) {
                size = queue.size();
                currentSize = 0;
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        TreeOper treeOper = new TreeOper();
        PrintTree printTree = new PrintTree();
        treeOper.insert(10);
        treeOper.insert(8);
        treeOper.insert(5);
        treeOper.insert(3);
        treeOper.insert(6);
        treeOper.insert(9);
        treeOper.insert(12);
        treeOper.insert(11);
        treeOper.insert(13);

        printTree.printTreeByLevel(treeOper.root);
    }
}
