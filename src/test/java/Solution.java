import java.util.Stack;

public class Solution {

    Stack<Integer> stack_num = new Stack<Integer>();
    Stack<Integer> stack_min = new Stack<Integer>();

    public void push(int node) {
        stack_num.push(node);
        if (stack_min.isEmpty()) {
            stack_min.push(node);
        } else {
            if (node <= stack_min.peek())
                stack_min.push(node);
        }
    }

    public void pop() {
        int tmp = stack_min.peek();
        int pop = stack_num.pop();
        if (tmp == pop)
            stack_min.pop();
    }

    public int top() {
        return stack_num.peek();
    }

    public int min() {
        return stack_min.peek();
    }

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        Solution solution = new Solution();
        solution.push(3);
        System.out.println("1-----" + solution.min());
        solution.push(4);
        System.out.println("2-----" + solution.min());
        solution.push(2);
        System.out.println("3-----" + solution.min());
        solution.push(3);
        System.out.println("4-----" + solution.min());
        solution.pop();
        System.out.println("5-----" + solution.min());
        solution.pop();
        System.out.println("6-----" + solution.min());
        solution.pop();
        System.out.println("7-----" + solution.min());
        solution.push(0);
        System.out.println("8-----" + solution.min());

    }
}