/**
 * Module Desc:
 * User: empcl
 * DateTime: 2019/8/16 15:47
 */
public class B {
    public static void main(String[] args) {
        A.a = 2;
        A A = new A();
        int res = A.getA();
        System.out.println(res);
    }
}
