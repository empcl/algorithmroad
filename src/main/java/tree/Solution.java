package tree;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @Author empcl
 * @Description
 * @Date 2019/5/28
 * @Time 10
 */

public class Solution {
    ArrayList<Integer> path = new ArrayList<Integer>();
    ArrayList<ArrayList<Integer>> pathList = new ArrayList<ArrayList<Integer>>();

    public ArrayList<ArrayList<Integer>> findPath(TreeNode node, int target) {
        if (node == null)
            return pathList;
        path.add(node.data);
        if (node.leftChild == null && node.rightChild == null && node.data == target)
            pathList.add(new ArrayList<Integer>(path));
        if (node.data < target && node.leftChild != null)
            findPath(node.leftChild, target - node.data);
        if (node.data < target && node.rightChild != null)
            findPath(node.rightChild, target - node.data);
        path.remove(path.size() - 1); // 无论当前节点是否满足条件，都需要将当前节点从list中剔除，以便其他节点的遍历
        return pathList;
    }

    public static boolean VerifySquenceOfBST(int [] sequence) {
        if (sequence.length == 0)
            return true;
        return isTreeBST(sequence,0,sequence.length - 1);
    }

    private static boolean isTreeBST(int[] sequence,int start,int end){
        if (end <= start) return true;
        int i = 0;
        for (;i < end;i++)
            if (sequence[i] > sequence[end])
                break;
        // 从当前i开始的值就大于了end代表的值
        for (int j = i;j < end; j++)
            if (sequence[j] < sequence[end])
                return false;
        return isTreeBST(sequence,start,i - 1) && isTreeBST(sequence,i,end - 1);
    }

    // 前序遍历
    private String preOrder(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        if (root == null)
            return null;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode current = root;
        stack.add(current);
        while (!stack.isEmpty()){
            current = stack.pop();
            sb.append("," + current.data);
            if (current.rightChild != null)
                stack.add(current.rightChild);
            if (current.leftChild != null)
                stack.add(current.leftChild);
        }
        return sb.toString().substring(1);
    }

    // 中序遍历
    private String inOrder(TreeNode root){
        StringBuffer sb = new StringBuffer();
        if (root == null)
            return null;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()){
            while (current != null){
                stack.push(current);
                current = current.leftChild;
            }
            current = stack.pop();
            sb.append("," + current.data);
            current = current.rightChild;
        }
        return sb.toString().substring(1);
    }

    private int getPosition(int[] inOrder,int start,int end,int key){
        for (int loc = start;loc <= end;loc++){
            if (key == inOrder[loc])
                return loc;
        }
        return -1;
    }

    private TreeNode buildMyTree(int[] preOrder,int preStart,int preEnd,int[] inOrder,int inStart,int inEnd){
        if (preStart > preEnd)
            return null;
        TreeNode root = new TreeNode(preOrder[preStart]);
        int position = getPosition(inOrder,inStart,inEnd,preOrder[preStart]);
        root.leftChild = buildMyTree(preOrder,preStart + 1,preEnd + position - inEnd,inOrder,inStart,position - 1);
        root.rightChild = buildMyTree(preOrder,preEnd + position - inEnd + 1,preEnd,inOrder,position + 1,inEnd);
        return root;
    }

    String Serialize(TreeNode root) {
        if (root == null)
            return null;
        // 谦虚遍历 + “|” + 中序遍历
        String preStr = preOrder(root);
        String inStr = inOrder(root);
        return preStr + "|" + inStr;

    }
    TreeNode Deserialize(String str) {
        if (str == null)
            return null;
        String[] strs = str.split("\\|");
        String[] preOrder = strs[0].split(",");
        String[] inOrder = strs[1].split(",");
        int len = preOrder.length;
        int[] preOrder_1 = new int[len];
        int[] inOrder_1 = new int[len];
        for (int i = 0;i < len;i++){
            preOrder_1[i] = Integer.parseInt(preOrder[i]);
            inOrder_1[i] = Integer.parseInt(inOrder[i]);
        }
        return buildMyTree(preOrder_1,0,len - 1,inOrder_1,0,len - 1);
    }



    public static void main(String[] args) {
//        int[] a = {4,8,6,12,16,14,10};
//        boolean b = VerifySquenceOfBST(a);
//        System.out.println(b);
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
        treeOper.preOrderRecursive(treeOper.root);
        treeOper.infixOrderRecursive(treeOper.root);
        Solution solution = new Solution();
        String serialize = solution.Serialize(treeOper.root);
//        System.out.println(serialize);
        TreeNode deserialize = solution.Deserialize(serialize);
        System.out.println(deserialize);
    }
}
