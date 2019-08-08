package interview;

import java.util.Stack;

/**
 * Module Desc:
 * 给个字符串，只有(){}[]，看看是否是匹配的
 * User: empcl
 * DateTime: 2019/8/8 15:15
 */
public class SymbolMatching {
    public boolean match(String str) {
        char[] chars = str.toCharArray();
        int len = chars.length;
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < len; i++) {
            if (chars[i] == '{' || chars[i] == '[' || chars[i] == '(') {
                stack.push(chars[i]);
            } else {
                // ]123 为了防止出现开局就是]})的情况
                if (!stack.isEmpty()) {
                    if ((chars[i] == '}' && stack.peek() != '{') ||
                            (chars[i] == ']' && stack.peek() != '[') ||
                            (chars[i] == ')') && stack.peek() != '(') {
                        return false;
                    } else if ((chars[i] == '}' && stack.peek() == '{') ||
                            (chars[i] == ']' && stack.peek() == '[') ||
                            (chars[i] == ')') && stack.peek() == '(') {
                        stack.pop();
                    } else {}
                } else {
                    if (chars[i] < '0' || chars[i] > '9')
                        return false;
                }

            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String str = "123()[[])";
        SymbolMatching matching = new SymbolMatching();
        boolean match = matching.match(str);
        System.out.println(match);
    }
}
