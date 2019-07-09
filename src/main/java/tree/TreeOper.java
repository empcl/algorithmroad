package tree;

/**
 * https://www.cnblogs.com/ysocean/p/8032642.html
 * https://blog.csdn.net/qq_33591903/article/details/83660837
 * https://blog.csdn.net/boguesfei/article/details/80378463
 *
 * @Author empcl
 * @Description
 * @Date 2019/5/18
 * @Time 20
 */

import java.util.*;

/**
 * 二叉搜索树
 * 左节点 < 根节点 < 右节点
 */
public class TreeOper {
    public TreeNode root = null;

    // 插入操作
    public boolean insert(int data) {
        TreeNode newNode = new TreeNode(data);
        if (root == null) {
            root = newNode;
        }
        TreeNode current = root;
        TreeNode preNode = root;
        while (current != null) {
            int currentData = current.data;
            preNode = current;
            if (currentData > data) {
                current = current.leftChild;
                if (current == null) {
                    preNode.leftChild = newNode;
                    return true;
                }
            } else if (currentData < data) {
                current = current.rightChild;
                if (current == null) {
                    preNode.rightChild = newNode;
                    return true;
                }
            } else {
                return true;
            }
        }
        return false;
    }

    // 查询操作
    public TreeNode find(int key) {
        if (root == null)
            return null;
        TreeNode current = root;
        while (current != null) {
            int data = current.data;
            if (key < data)
                current = current.leftChild;
            else if (key > data)
                current = current.rightChild;
            else // key = data
                return current;
        }
        return null;
    }

    // 遍历操作
    // 中序遍历 -- 递归
    public void infixOrderRecursive(TreeNode node) {
        if (node != null) {
            infixOrderRecursive(node.leftChild);
            System.out.print(" " +node.data);
            infixOrderRecursive(node.rightChild);
        }
    }

    // 二叉树的中序遍历 - 非递归
    public ArrayList<Integer> infixOrder(TreeNode node) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (node == null)
            return null;
        TreeNode current = node;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.leftChild;
            }
            current = stack.pop();
            list.add(current.data);
            current = current.rightChild;
        }
        return list;
    }


    //     先序遍历 -- 递归
    public void preOrderRecursive(TreeNode node) {
        if (node != null) {
            System.out.print(" " + node.data);
            preOrderRecursive(node.leftChild);
            preOrderRecursive(node.rightChild);
        }
    }

    // 先序遍历 --非递归
    public ArrayList<Integer> preOrder(TreeNode node) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (node == null)
            return list;
        stack.push(node);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            list.add(pop.data);
            // 以下是关键点，因为栈是先进后出，考虑到先序遍历的顺序，我们先要将右子节点入栈，再将左子节点入栈。
            if (pop.rightChild != null)
                stack.push(pop.rightChild);
            if (pop.leftChild != null)
                stack.push(pop.leftChild);
        }
        return list;
    }


    //     后序遍历 -- 递归
    public void postOrder(TreeNode node) {
        if (node != null) {
            postOrder(node.leftChild);
            postOrder(node.rightChild);
            System.out.print(" " + node.data);
        }
    }

    // 查找最大值
    public TreeNode getMaxNode() {
        TreeNode current = root;
        TreeNode maxNode = current;
        while (current != null) {
            maxNode = current;
            current = current.rightChild;
        }
        return maxNode;
    }

    // 查找最小值
    public TreeNode getMinNode() {
        TreeNode current = root;
        TreeNode minNode = current;
        while (current != null) {
            minNode = current;
            current = current.leftChild;
        }
        return minNode;
    }

    /**
     * 删除节点
     * 1）删除的节点没有左右子结点
     * 2）删除的节点只有一个子节点
     * 3）删除的节点有两个子节点
     */
    public boolean delete(int key) {
        boolean isLeftChild = false;
        TreeNode current = root;
        TreeNode preNode = root;
        while (current.data != key) {
            int data = current.data;
            preNode = current;
            if (key < data) {
                isLeftChild = true;
                current = current.leftChild;
            } else if (key > data) {
                isLeftChild = false;
                current = current.rightChild;
            }
        }
        if (current.leftChild == null && current.rightChild == null) {
            if (current == root)
                root = null;
            if (isLeftChild) {
                preNode.leftChild = null;
            } else {
                preNode.rightChild = null;
            }
            return true;
        }
        if (current.leftChild != null && current.rightChild == null) {
            if (current == root)
                root = current.leftChild;
            if (isLeftChild) {
                preNode.leftChild = current.leftChild;
            } else {
                preNode.rightChild = current.leftChild;
            }
            return true;
        }
        if (current.leftChild == null && current.rightChild != null) {
            if (current == root)
                root = current.rightChild;

            if (isLeftChild)
                preNode.leftChild = current.rightChild;
            else
                preNode.rightChild = current.rightChild;
            return true;
        }
        if (current.leftChild != null && current.rightChild != null) {
            TreeNode successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            }
            if (isLeftChild) {
                preNode.leftChild = successor;
            } else {
                preNode.rightChild = successor;
            }
            successor.leftChild = current.leftChild;
            return true;
        }
        return false;
    }

    private TreeNode getSuccessor(TreeNode delNode) {
        TreeNode preSuccessor = delNode;
        TreeNode successor = delNode;
        TreeNode current = delNode.rightChild;
        while (current != null) {
            preSuccessor = successor;
            successor = current;
            current = current.leftChild;
        }
        if (successor != delNode.rightChild) {
            preSuccessor.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }
        return successor;
    }

    // 求二叉树最大深度
    // 想要求一颗二叉树的高度，即求孩子节点的最大深度，要么是左孩子，要么是右孩子，那么我们只需要对传入的孩子节点递归调用即可。
    // https://blog.csdn.net/qq_33591903/article/details/83660837
    public int getMaxDepthRecursive(TreeNode node) {
        if (node == null)
            return 0;
        int leftDepth = getMaxDepthRecursive(node.leftChild);
        int rightDepth = getMaxDepthRecursive(node.rightChild);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    //求二叉树最小深度
    // https://blog.csdn.net/boguesfei/article/details/80378463
    public int getMinDepthRecursive(TreeNode node) {
        if (node == null)
            return 0;
        int leftDepth = getMinDepthRecursive(node.leftChild);
        int rightDepth = getMinDepthRecursive(node.rightChild);
        if (leftDepth == 0 || rightDepth == 0) // 关键
            return leftDepth + rightDepth + 1;
        return Math.min(leftDepth, rightDepth) + 1;
    }

    // 求二叉树中节点的个数
    public int numOfTreeNodeRecursive(TreeNode node) {
        if (node == null)
            return 0;
        int left = numOfTreeNodeRecursive(node.leftChild);
        int right = numOfTreeNodeRecursive(node.rightChild);
        return (left + right + 1);
    }

    // 求二叉树中叶子节点的个数
    public int numOfNoChildNodeRecursive(TreeNode node) {
        if (node == null)
            return 0;
        if (node.leftChild == null && node.rightChild == null)
            return 1;
        int left = numOfNoChildNodeRecursive(node.leftChild);
        int right = numOfNoChildNodeRecursive(node.rightChild);
        return left + right;
    }

    // 求二叉树中第k层节点的个数 从第1层开始
    public int numsOfkLevelTreeNodeRecursive(TreeNode node, int k) {
        if (node == null || k < 1)
            return 0;
        if (k == 1)
            return 1;
        return numsOfkLevelTreeNodeRecursive(node.leftChild, k - 1) + numsOfkLevelTreeNodeRecursive(node.rightChild, k - 1);
    }

    /**
     * 平衡二叉树
     * https://blog.csdn.net/qq_39429962/article/details/82953014
     * 旋转 关键词： “最低失衡根结点”
     * 当二叉树失衡的原因出现在“最低失衡根结点左子树的左子树”
     * （所谓“最低失衡根结点”，则是从新增结点开始向根部回溯，所遇到的第一个失衡的根节点）时，则使用LL旋转来调整；
     * 当失衡出现在“最低失衡根节点左子树的右子树”，则使用LR旋转调整；
     * RR旋转，RL旋转同理。
     * LL、RR操作首先找的是第一个字母代表的位置（从最低失衡根节点开始算），然后进行单旋转
     * LR、RL操作首先找的是第二个字母Y（第一个字母以X代表）代表的位置（从最低失衡根节点开始算），然后在当前节点进行YY操作，之后再对失衡节点进行XX操作。
     */
    // 判断二叉树是否是平衡二叉树
    public boolean isBalanced_1(TreeNode node) { // way01
        if (node == null) {
            return true;
        }
        if (Math.abs(getHeight(node.leftChild) - getHeight(node.rightChild)) > 1)
            return false;
        return isBalanced_1(node.leftChild) && isBalanced_1(node.rightChild);
    }

    private int getHeight(TreeNode node) {
        if (node == null)
            return 0;
        return Math.max(getHeight(node.leftChild), getHeight(node.rightChild)) + 1;
    }

    // 从下向上遍历，如果子树是平衡二叉树，则返回当前子树的高度，如果不是，返回-1，则直接停止遍历，这样至多只对每个结点访问一次
    public boolean isBalanced_2(TreeNode node) {  // way 2
        return getDepth(node) != -1;
    }

    private int getDepth(TreeNode node) {
        if (node == null)
            return 0;
        int left = getDepth(node.leftChild);
        if (left == -1)
            return -1;
        int right = getDepth(node.rightChild);
        if (right == -1)
            return -1;
        return Math.abs(left - right) > 1 ? -1 : Math.max(left, right) + 1;
    }

    // 判断二叉树是否是完全二叉树

    /**
     * 完全二叉树
     * 层次遍历
     */
    boolean isCompleteTreeNode(TreeNode node) {
        if (node == null)
            return true;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode leftChild = null;
        TreeNode rightChild = null;
        boolean left = false; // 记录当前节点只存在左子节点或者是个叶子节点
        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode head = queue.poll();
            leftChild = head.leftChild;
            rightChild = head.rightChild;
            if ((leftChild == null && rightChild != null) // 当前节点的左节点不存在，但是右节点存在，则当前树并不是完全二叉树
                    || (left && (leftChild != null || rightChild != null))) { // 当前节点的前一个节点只存在左子节点或者是个叶子结点，如果当前节点包含子节点，则当前树并不是完全二叉树
                return false;
            }
            if (leftChild != null)
                queue.add(leftChild);
            if (rightChild != null)
                queue.add(rightChild);
            else
                left = true; // 表示当前节点为叶子节点
        }
        return true;
    }

    // 两个二叉树是否完全相同
    public boolean isSameTreeNode(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        } else if (node1 == null || node2 == null)
            return false;
        if (node1.data != node2.data)
            return false;
        boolean left = isSameTreeNode(node1.leftChild, node2.leftChild);
        boolean right = isSameTreeNode(node1.rightChild, node2.rightChild);
        return left && right;
    }

    // 两个二叉树是否互为镜像

    /**
     * 解释：
     * 第一棵树的左子树 与 第二棵树的右子树相等的话，就是镜像
     */
    public boolean isMirror(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null)
            return true;
        if (node1 == null || node2 == null)
            return false;
        if (node1.data != node2.data)
            return false;
        return isMirror(node1.leftChild, node2.rightChild) && isMirror(node1.rightChild, node2.leftChild);
    }

    // 翻转二叉树or镜像二叉树
    public TreeNode mirrorTreeNode(TreeNode node) {
        if (node == null)
            return null;
        TreeNode left = mirrorTreeNode(node.leftChild);
        TreeNode right = mirrorTreeNode(node.rightChild);
        node.leftChild = right;
        node.rightChild = left;
        return node;
    }

    // 求二叉树搜索树中两个结点的最低公共祖先
    // https://blog.csdn.net/hansionz/article/details/82718086
    public TreeNode getLastCommonParent(TreeNode root, int a, int b) {
        if (root == null)
            return null;
        int data = root.data;
        if (data > a && data > b)
            return getLastCommonParent(root.leftChild, a, b);
        else if (data < a && data < b)
            return getLastCommonParent(root.rightChild, a, b);
        else
            return root;
    }

    /**
     * 如果将上述的二叉搜索树的条件进行改变，所给的树不是一棵二叉搜索树，甚至不是一棵二叉树，只是一棵简单的树，
     * 但是树中的结点存在着指向父节点的指针，那么要怎么求出两个结点的最低公共祖先呢？
     * 这个时候，我们就可以将题目的思路进行变一变了，
     * https://blog.csdn.net/hansionz/article/details/82718086
     * 根据这个网址中给出的例子，3 -> 12 -> 32,78 -> 45 -> 32，求出他们的最小公共父节点，即32.
     * 这个时候，题目也就改为了求出两个单链表的最小公共节点，那么此题解法就可参考
     * list.ListOper#getFirstMeetNode(list.Node, list.Node)
     */

    /**
     * 如果将上述题目在进行改变，所给的树只是一个二叉树，树中的节点并不存在着指向父节点的指针，那么怎么求出两个节点的最低公共祖先呢？
     * https://blog.csdn.net/qq_25827845/article/details/74612786
     * 根据下面算法，自己画图推导
     */
    public TreeNode getLastCommonParent4SimpleTreeRecursive(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null || node == p || node == q)
            return node;
        TreeNode left = getLastCommonParent4SimpleTreeRecursive(node.leftChild, p, q);
        TreeNode right = getLastCommonParent4SimpleTreeRecursive(node.rightChild, p, q);
        if (left == null)
            return right;
        if (right == null)
            return left;
        return node;
    }

    // 上述算法的非递归实现

    /**
     * https://blog.csdn.net/qq_25827845/article/details/74612786
     * 需要我们保存下由root根节点到p和q节点的路径，并且将路径存入list中，则问题转化为求两个list集合的最后一个共同元素。
     */
    public TreeNode getLastCommonParent4SimpleTree(TreeNode node, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null)
            return null;
        List<TreeNode> pathP = new ArrayList<TreeNode>();
        List<TreeNode> pathQ = new ArrayList<TreeNode>();
        pathP.add(node);
        pathQ.add(node);
        getPath(node, p, pathP);
        getPath(node, q, pathQ);

        int pLen = pathP.size();
        int qLen = pathQ.size();
        TreeNode lca = null;
        for (int i = 0; i < pLen && i < qLen; i++) {
            if (pathP.get(i) == pathQ.get(i))
                lca = pathP.get(i);
            else
                break;
        }
        return lca;
    }

    // 获得一个点到另一个点的路径
    private boolean getPath(TreeNode node, TreeNode n, List<TreeNode> paths) {
        if (node == n)
            return true;
        if (node.leftChild != null) {
            paths.add(node.leftChild);
            if (getPath(node.leftChild, n, paths))
                return true;
            paths.remove(paths.size() - 1);
        }

        if (node.rightChild != null) {
            paths.add(node.rightChild);
            if (getPath(node.rightChild, n, paths))
                return true;
            paths.remove(paths.size() - 1);
        }

        return false;
    }

    // 中序遍历和先序遍历构造二叉树
    TreeNode buildTreeNode(int[] inOrder, int[] preOrder) {
        if (preOrder.length != inOrder.length) {
            return null;
        }
        return buildMyTree(inOrder, 0, inOrder.length - 1, preOrder, 0, preOrder.length - 1);
    }

    private TreeNode buildMyTree(int[] inOrder, int inStart, int inEnd, int[] preOrder, int preStart, int preEnd) {
        if (inStart > inEnd)
            return null;
        TreeNode root = new TreeNode(preOrder[preStart]);
        int position = getPosition(inOrder, inStart, inEnd, preOrder[preStart]);
        root.leftChild = buildMyTree(inOrder, inStart, position - 1, preOrder, preStart + 1, preEnd + position - inEnd); // preStart+position-inStart
        root.rightChild = buildMyTree(inOrder, position + 1, inEnd, preOrder, preEnd + position - inEnd + 1, preEnd);
        return root;
    }

    private int getPosition(int[] inOrder, int inStart, int inEnd, int key) {
        int loc;
        for (loc = inStart; loc <= inEnd; loc++) {
            if (inOrder[loc] == key)
                return loc;
        }
        return -1;
    }

    // 输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
    // 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
    // https://blog.csdn.net/u014525494/article/details/80978647
    // 见tree.Solution.FindPath()

    /**
     * 给定两个值 k1 和 k2（k1 < k2）和一个二叉查找树的根节点。
     * 找到树中所有值在 k1 到 k2 范围内的节点。即打印所有x (k1 <= x <= k2),
     * 其中 x 是二叉查找树的中的节点值。返回所有升序的节点值 -- 想到中序遍历
     */
    ArrayList<Integer> result;

    public ArrayList<Integer> searchRange(TreeNode node, int p, int q) {
        result = new ArrayList<Integer>();
        searchHelper(node, p, q);
        return result;
    }

    private void searchHelper(TreeNode node, int p, int q) {
        if (node == null)
            return;
        if (node.data > p)
            searchHelper(node.leftChild, p, q);
        if (node.data >= p && node.data <= q)
            result.add(node.data);
        if (node.data < q)
            searchHelper(node.rightChild, p, q);
    }


    // 二叉树的层次遍历
    public ArrayList<Integer> levelOrder(TreeNode node) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (node == null)
            return result;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(node);
        TreeNode current = null;
        while (!queue.isEmpty()) {
            current = queue.poll();
            result.add(current.data);
            if (current.leftChild != null)
                queue.add(current.leftChild);
            if (current.rightChild != null)
                queue.add(current.rightChild);
        }
        return result;
    }

    /**
     * 题目：给出 n，问由 1...n 为节点组成的不同的二叉查找树有多少种？
     * 关键点：这棵树的不同形态的二叉查找树的个数，就是根节点的 左子树的个数*右子树的个数
     * 使用的算法：动态规划
     * 参考的链接：https://blog.csdn.net/sinat_20177327/article/details/81676199
     */
    public int numTrees(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        int[] nums = new int[n + 1];
        nums[0] = 1;
        nums[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) { // 表示左孩子的节点数
                nums[i] = nums[i] + nums[j] * nums[i - j - 1]; // nums[j] 可以认识是左子树只有j个节点时候的情况个数
            }
        }
        return nums[n];
    }


    /**
     * https://blog.csdn.net/guoyuguang0/article/details/51018249
     * 判断二叉树是否是合法的二叉查找树(BST)
     * 规则：
     * 节点的左子树中的值要严格小于该节点的值。
     * 节点的右子树中的值要严格大于该节点的值。
     * 左右子树也必须是二叉查找树。
     * 一个节点的树也是二叉查找树。
     */
    public boolean isValidBST(TreeNode node) {
        return validateRange(node, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validateRange(TreeNode node, long minValue, long maxValue) {
        if (node == null)
            return true;
        if (node.data >= maxValue || node.data <= minValue)
            return false;
        return validateRange(node.leftChild, minValue, node.data) && validateRange(node.rightChild, node.data, maxValue);
    }

    /**
     * https://blog.csdn.net/stonetudou/article/details/8071085
     * 二叉树的最大距离,即相距最远的两个叶子节点，
     * 一个二叉树中节点的最大距离由三部分综合求得：
     * 一部分是左子树中节点的最大距离，
     * 另一部分是右子树中节点的最大距离，
     * 最后一部分是左边的最大深度加上右边的最大深度。
     */
    static int maxLen = 0;

    public void findMaxLen(TreeNode node) {
        if (node == null)
            return;
        if (node.leftChild == null)
            node.maxLeft = 0;
        if (node.rightChild == null)
            node.maxRight = 0;
        if (node.leftChild != null)
            findMaxLen(node.leftChild);
        if (node.rightChild != null)
            findMaxLen(node.rightChild);
        if (node.leftChild != null) {
            int temp = 0;
            temp = node.leftChild.maxLeft > node.leftChild.maxRight ? node.leftChild.maxLeft : node.leftChild.maxRight;
            node.maxLeft = temp + 1;
        }
        if (node.rightChild != null) {
            int temp = 0;
            temp = node.rightChild.maxLeft > node.rightChild.maxRight ? node.rightChild.maxLeft : node.rightChild.maxRight;
            node.maxRight = temp + 1;
        }

        if (node.maxLeft + node.maxRight > maxLen)
            maxLen = node.maxLeft + node.maxRight;
    }

    /**
     * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
     * https://www.nowcoder.com/practice/6e196c44c7004d15b1610b9afca8bd88?tpId=13&tqId=11170&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
     */
    public boolean HasSubTree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null)
            return false;
        return isSubTree(root1, root2) || HasSubTree(root1.leftChild, root2) || HasSubTree(root1.rightChild, root2);
    }

    private boolean isSubTree(TreeNode root1, TreeNode root2) {
        if (root2 == null) return true;
        if (root1 == null) return false;
        if (root1.data == root2.data) {
            return isSubTree(root1.leftChild, root2.leftChild) && isSubTree(root1.rightChild, root2.rightChild);
        } else {
            return false;
        }
    }
}