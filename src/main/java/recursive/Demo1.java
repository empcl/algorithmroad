package recursive;

/**
 * @Author empcl
 * @Description
 * @Date 2019/5/19
 * @Time 23
 */
public class Demo1 {
    public static void main(String[] args) {
        CommonDemo commonDemo = new CommonDemo();
//        int factorial = commonDemo.getFactorial(4);
//        System.out.println(factorial);
        int[] a = {1,2,3,5,6,7};
//        int twoPoint = commonDemo.findTwoPoint(a, 3, 0, 5);
//        System.out.println(twoPoint);
//        commonDemo.move(3,"A","B","C");
        int pow = commonDemo.pow(2, 4);
        System.out.println(pow);
    }
}
