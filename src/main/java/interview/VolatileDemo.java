package interview;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Module Desc:
 *  写一个保证可见性的例子
 * User: empcl
 * DateTime: 2019/8/3 10:18
 */

class ShareData4Volatile {
    volatile int number = 0;
    public void add2Number() {
        this.number = 60;
    }


    public void addPlus() {
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAtomicPlus() {
        atomicInteger.getAndIncrement();
    }
}

public class VolatileDemo {
    public static void main(String[] args) {
        ShareData4Volatile shareData = new ShareData4Volatile();
        long startTime = System.currentTimeMillis();
//        new Thread(() -> {
//            System.out.println(Thread.currentThread().getName() + " come in...");
//            // 暂停一下线程，让main线程从主内存中取得数据。
//            try {
//                Thread.sleep(3l);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            shareData.add2Number();
//            System.out.println(Thread.currentThread().getName() + " updated number,it's value is " + shareData.number + " now");
//        },"A").start();
//
//        while (shareData.number == 0) {}
//        System.out.println(Thread.currentThread().getName() + " mission is over,and number is " + shareData.number);

        for (int i = 0;i < 200;i++) {
            new Thread(() -> {
                for (int j = 0;j < 10000;j++)
                    shareData.addAtomicPlus();
            },String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println("number finally value is: " + shareData.atomicInteger);

        long endTime = System.currentTimeMillis();
        System.out.println("total time is: " + (endTime - startTime));
    }

}
