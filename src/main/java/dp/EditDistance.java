package dp;

/**
 * @Author empcl
 * @Description
 * @Date 2019/5/30
 * @Time 17
 */
// https://blog.csdn.net/ac540101928/article/details/52786435
public class EditDistance {
    public static int getEditDistance(char[] char1, char[] char2) {
        int len1 = char1.length;
        int len2 = char2.length;
        int[][] edits = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++)
            edits[i][0] = i;
        for (int j = 0; j <= len2; j++)
            edits[0][j] = j;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                int costs = char1[i - 1] == char2[j - 1] ? 0 : 1;
                int deletion = edits[i][j - 1] + 1;
                int insertion = edits[i - 1][j] + 1;
                int substitution = edits[i - 1][j - 1] + costs;
                edits[i][j] = getMin(deletion, insertion, substitution);
            }
        }
        return edits[len1][len2];
    }


    private static int getMin(int a, int b, int c) {
        if (a >= b)
            a = b;
        if (a >= c)
            a = c;
        return a;
    }

    public static void main(String[] args) {
        char[] char1 = {'c', 'a', 'f', 'e'};
        char[] char2 = {'c', 'o', 'f', 'f', 'e', 'e'};
        int editDistance = getEditDistance(char1, char2);
        System.out.println(editDistance);
    }
}
