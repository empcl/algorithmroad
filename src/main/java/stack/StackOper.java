package stack;

import java.util.Stack;

/**
 * @Author empcl
 * @Description
 * @Date 2019/5/29
 * @Time 11
 */
public class StackOper {
    public void testStringReversal(String str) {
        Stack<Character> stack = new Stack<Character>();
        char[] chars = str.toCharArray();
        for (Character c : chars) {
            if (c == ' ')
                continue;
            stack.push(c);
        }
        while (!stack.isEmpty())
            System.out.print(stack.pop());
    }

    // 利用栈判断分隔符是否匹配　　
    public boolean isMatch(String str) {
        Stack<Character> stack = new Stack<Character>();
        char[] chars = str.toCharArray();
        for (Character c : chars) { // 左符号入栈，右符号出栈
            switch (c) {
                case '{':
                case '<':
                case '[':
                    stack.push(c);
                    break;
                case '}':
                case '>':
                case ']':
                    if (!stack.isEmpty()) {
                        Character pop = stack.pop();
                        if ((pop == '{' && c != '}') || (pop == '<' && c != '>') || (pop == ']' && c != ']')) {
                            System.out.println("Error");
                            return false;
                        }
                    } else {
                        return false;
                    }
                    break;
                default:
                    break;
            }
        }
        return true;
    }
}
