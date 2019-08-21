import tree.TreeNode;
import tree.TreeOper;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;


public class Solution {
    // 中序遍历
    private String inOrder(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        if (root == null)
            return null;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.leftChild;
            }
            current = stack.pop();
            sb.append("," + current.data);

            current = current.rightChild;
        }

        return sb.toString().substring(1);
    }

    // 前序遍历
    private String preOrder(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        if (root == null)
            return null;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode current = root;
        queue.add(current);
        while (!queue.isEmpty()) {
            current = queue.poll();
            sb.append("," + current.data);

            if (current.rightChild != null)
                queue.add(current.rightChild);
            if (current.leftChild != null)
                queue.add(current.leftChild);
        }
        return sb.toString().substring(1);
    }


    // 根据字符串数组构造二叉树
    private TreeNode buildMyTree(String[] preStrs,int preStart,int preEnd,String[] inStrs,int inStart,int inEnd) {
        if (preStart > preEnd)
            return null;
        int data = Integer.parseInt(preStrs[preStart]);
        TreeNode root = new TreeNode(data);
        int position = getPosition(inStrs,inStart,inEnd,data);

        root.leftChild = buildMyTree(preStrs,preStart + 1,preEnd + position - inEnd,inStrs,inStart,position - 1);
        root.rightChild = buildMyTree(preStrs,preEnd + position - inEnd + 1,preEnd,inStrs,position + 1,inEnd);

        return root;
    }

    private int getPosition(String[] inStrs,int inStart,int inEnd,int data) {
        int loc = inStart;
        for (;loc <= inEnd;loc++){
            if (Integer.parseInt(inStrs[loc]) == data)
                return loc;
        }
        return -1;
    }

    String Serialize(TreeNode root) {
        if (root == null)
            return null;
        String preStr = preOrder(root);
        String inStr = inOrder(root);
        return preStr + "|" + inStr;
    }
    TreeNode Deserialize(String str) {
        String[] preStrs = str.split("\\|")[0].split(",");
        String[] inStrs = str.split("\\|")[1].split(",");
        int inLen = inStrs.length;
        int preLen = preStrs.length;
        return buildMyTree(preStrs,0,preLen - 1,inStrs,0,inLen - 1);
    }

    public static void main(String[] args) {
        TreeOper treeOper = new TreeOper();
        treeOper.insert(10);
        treeOper.insert(7);
        treeOper.insert(6);
        treeOper.insert(8);
        treeOper.insert(13);
        treeOper.insert(12);
        treeOper.insert(14);
        ArrayList<Integer> preOrder = treeOper.preOrder(treeOper.root);
        ArrayList<Integer> inOrder = treeOper.infixOrder(treeOper.root);
        Solution solution = new Solution();
        String str = solution.Serialize(treeOper.root);
        TreeNode deserialize = solution.Deserialize(str);
        System.out.println(deserialize);
    }
}