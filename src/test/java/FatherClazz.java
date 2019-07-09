public class FatherClazz {
    int one = 1;

    int two = getTwo();

    // 静态代码块
    static {
        System.out.println("父类静态代码块被实例化了...");
    }

    {
        int three = 3;
        System.out.println("FatherOne:" + one + "," + "FatherTwo:" + two + "," + "FatherThree" + three);
    }

    // 构造函数
    public FatherClazz() {
        this(4);
        System.out.println("父类无参构造函数...");
    }

    public FatherClazz(int num) {
        System.out.println("父类带参数的构造函数..." + num);
    }

    int getTwo() {
        System.out.println("父类getTwo方法...");
        return 2;
    }

    public void methodFirst() {
        System.out.println("Hi,我是methodFirst...");
    }

}