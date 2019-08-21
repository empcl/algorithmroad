/**
 * Module Desc:
 * User: empcl
 * DateTime: 2019/8/15 17:38
 */
public class Demo {
    public static void main(String[] args) {
        String str = "google";
        int index = FirstNotRepeatingChar(str);
        System.out.println(index);
    }

    public static int FirstNotRepeatingChar(String str) {
        if (str == null)
            return -1;
        char[] chars = str.toCharArray();
        int len = chars.length;
        int count = 1;
        int index = 0;
        for (int i = 0;i < len - 1;i++) {
            index = i;
            for (int j = 1;j < len;j++) {
                if (chars[i] == chars[j]) {
                    count++;
                }
            }
            if (count == 1)
                return index;
        }
        return -1;
    }
}
