package tree;

import java.util.ArrayList;

/**
 * @Author empcl
 * @Description
 * @Date 2019/5/18
 * @Time 20
 */
public class Demo1 {
    public static void main(String[] args) {
        TreeOper treeOper = new TreeOper();
        treeOper.insert(10);
        treeOper.insert(8);
        treeOper.insert(5);
        treeOper.insert(3);
        treeOper.insert(6);
        treeOper.insert(9);
        treeOper.insert(12);
        treeOper.insert(11);
        treeOper.insert(13);
        treeOper.findMaxLen(treeOper.root);
        System.out.println(TreeOper.maxLen);
//        ArrayList<Integer> integers = treeOper.searchRange(treeOper.root, 5, 12);
//        System.out.println(integers);
//        treeOper.insert(15);
//        treeOper.insert(14);
//        treeOper.insert(16);
//        treeOper.insert(17);
//        treeOper.postOrder(treeOper.root);
//        int maxDepthRecursive = treeOper.getMinDepthRecursive(treeOper.root);
//        System.out.println(maxDepthRecursive);
//        treeOper.preOrderRecursive(treeOper.root);
//        treeOper.findMaxLen(treeOper.root);
//        System.out.println(TreeOper.maxLen);


//        int i = treeOper.numTrees(3);
//        System.out.println(i);
//        boolean validBST = treeOper.isValidBST(treeOper.root);
//        System.out.println(validBST);
    }
}
