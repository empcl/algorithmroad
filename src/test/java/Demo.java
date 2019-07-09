import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        int[] A = new int[len];
        int[] B = new int[len];
        int count = 0;
        for (int i = 0;i < len;i++) {
            A[i] = scanner.nextInt();
        }
        for (int i = 0;i < len;i++) {
            B[i] = scanner.nextInt();
        }
        for (int i = 0;i < len;i++) {
            if (A[i] > B[i])
                count++;
            else if (A[i] < B[i])
                count--;
        }
        System.out.println(count);
    }
}