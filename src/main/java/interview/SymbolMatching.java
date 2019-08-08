package interview;

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
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            char l = chars[left];
            char r = chars[right];
            if ((l == '{' || l == '[' || l == '(') || (r == '{' || r == '[' || r == '(')) {
                if ((l == '{' && r != '}') || (l != '{' && r == '}') ||
                        (l == '[' && r != ']') || (l != '[' && r == ']') ||
                        (l == '(' && r != ')') || (l != '(' && r == ')'))
                    return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "((([))])";
        SymbolMatching matching = new SymbolMatching();
        boolean match = matching.match(str);
        System.out.println(match);
    }
}
