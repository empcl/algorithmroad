package interview;

import tree.TreeNode;

/**
 * Module Desc:
 *  给出一个排序好的数组，将它转换成平衡二叉树。
 * User: empcl
 * DateTime: 2019/8/13 14:49
 * https://blog.csdn.net/woaily1346/article/details/80927278
 */
public class ArrayToBST {
    public TreeNode sortedArrayToBST(int[] a, int len) {
        if (len == 0)
            return null;
        int index = len / 2;
        TreeNode root = new TreeNode(a[index]);
        int[] leftArr = new int[index];
        for (int i = 0;i < index;i++)
            leftArr[i] = a[i];
        root.leftChild = sortedArrayToBST(leftArr,index);
        int[] rightArr = new int[len - index - 1];
        for (int i = 0;i < (len - index - 1);i++)
            rightArr[i] = a[index + 1 + i];
        root.rightChild = sortedArrayToBST(rightArr,len - index - 1);
        return root;
    }

    public static void main(String[] args) {
        int[] a = {-10,-3,0,5,9};
        ArrayToBST arrayToBST = new ArrayToBST();
        TreeNode treeNode = arrayToBST.sortedArrayToBST(a, a.length);
        System.out.println(treeNode);
    }
}
