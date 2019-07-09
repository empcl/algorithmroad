package stack;

/**
 * @Author empcl
 * @Description
 * @Date 2019/5/29
 * @Time 17
 */
public class Demo1 {
    public static void main(String[] args) {
        StackOper stackOper = new StackOper();
//        stackOper.testStringReversal("hello world!");
        boolean match = stackOper.isMatch("12<[aba]{}>");
        System.out.println(match);

    }
}
