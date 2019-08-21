import java.util.HashMap;

/**
 * Module Desc:
 * User: empcl
 * DateTime: 2019/8/16 15:46
 */
public class A {
    public static int a = 1;

    public int getA() {
        return a;
    }

    public void my(String s) {
        System.out.println("String");
    }

    public void my(StringBuffer sb) {
        System.out.println("String Buffer");
    }

    public void my(StringBuilder s) {
        System.out.println("String Builder");
    }


    private int getSum(int num) {
        int sum = 0;
        while (num != 0) {
            sum = sum + num % 10;
            num = num / 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1,"a");
        hashMap.put(2,"a");
        hashMap.put(3,"a");
        hashMap.put(4,"a");
        hashMap.put(5,"a");
        hashMap.put(6,"a");
        hashMap.put(7,"a");
        hashMap.put(8,"a");
        hashMap.put(9,"a");
        hashMap.put(10,"a");
        hashMap.put(11,"a");
        hashMap.put(12,"a");
        hashMap.put(13,"a");
        hashMap.put(14,"a");

        String res = hashMap.get(1);
        System.out.println(res);
        A a = new A();
        int sum = a.getSum(15);
        System.out.println(sum);
        HashMap<Integer, String> map = new HashMap<>();
        map = null;
        System.out.println(map);
        long round = Math.round(1.5);
        long round1 = Math.round(-1.5);
        System.out.println(round);
        System.out.println(round1);
    }
}
