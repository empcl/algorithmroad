package other;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String strA = scanner.nextLine();
        String strB = scanner.nextLine();

        String[] as = strA.split(" ");
        String[] bs = strB.split(" ");

        int index = 0;
        int lenA = as.length;
        int lenB = bs.length;
        boolean flag = false;

        for (int i = 1;i < lenA;i++) {
            if (Integer.parseInt(as[i - 1]) >= Integer.parseInt(as[i])) {
                index = i;
                break;
            }
        }
        if (index > 0 && index < lenA) {
            int p = Integer.parseInt(as[index - 1]);
            int q = Integer.parseInt(as[index + 1]);
            for (int i = lenB - 1;i >= 0;i--) {
                if (Integer.parseInt(bs[i]) > p && Integer.parseInt(bs[i]) < q) {
                    as[index] = bs[i];
                    flag = true;
                    break;
                }
            }
        }
        // if flag is false,it represents that it not found
        if (flag == false) {
            for (int i = 1;i < lenA - 1;i++) {
                if (Integer.parseInt(as[i - 1]) <= Integer.parseInt(as[i]) && Integer.parseInt(as[i + 1]) <= Integer.parseInt(as[i])) {
                    index = i;
                    break;
                }
            }
            if (index > 0 && index < lenA) {
                int p = Integer.parseInt(as[index - 1]);
                int q = Integer.parseInt(as[index + 1]);
                for (int i = lenB - 1;i >= 0;i--) {
                    if (Integer.parseInt(bs[i]) > p && Integer.parseInt(bs[i]) < q) {
                        as[index] = bs[i];
                        flag = true;
                        break;
                    }
                }
            }
        }
        if (flag) {
            for (int i = 0;i < lenA;i++)
                System.out.print(as[i] + " ");
        }else {
            System.out.println("NO");
        }
    }
}
