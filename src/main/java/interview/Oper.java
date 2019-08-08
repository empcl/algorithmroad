package interview;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Module Desc:
 * User: empcl
 * DateTime: 2019/8/3 15:35
 */
public class Oper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        int lineNum = Integer.parseInt(scanner.nextLine());
        for (int i = 0;i < lineNum;i++) {
            String line = scanner.nextLine();
            String[] nums = line.split(" ");
            int id = Integer.parseInt(nums[0]);
            int num = Integer.parseInt(nums[1]);
            if (id == 1) {
                list.add(num);
                continue;
            } else { // id == 2 // 对list中元素使用异或，然后如果异或为0，
                int size = list.size();
                for (int j = 0;j < size;j++) {
                    num = num ^ list.get(j);
                    if (num == 0) {
                        System.out.println("YES");
                        break;
                    } else {
                        num = num & list.get(j);
                    }
                }
                if (num != 0) {
                    System.out.println("NO");
                }
            }
        }
    }
}
