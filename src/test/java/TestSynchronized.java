/**
 * Module Desc:
 * User: empcl
 * DateTime: 2019/7/31 22:16
 */
public class TestSynchronized {
    public synchronized void method1() {
        System.out.println("Hello method1");
    }

    public void method2() {
        synchronized (this) {
            System.out.println("Hello method2");
        }
    }
    public static void main(String[] args) {

    }
}
