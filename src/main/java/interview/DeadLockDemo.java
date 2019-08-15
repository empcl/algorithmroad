package interview;

import java.util.concurrent.TimeUnit;

/**
 * Module Desc:
 * User: empcl
 * DateTime: 2019/8/15 10:16
 */


class HoldLockThread implements Runnable {
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized(lockA) {
            System.out.println(Thread.currentThread().getName() + "持有" + lockA + ",尝试获得" + lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "持有" + lockA + "和" + lockB);
            }
        }
    }
}

public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA,lockB),"A").start();
        new Thread(new HoldLockThread(lockB,lockA),"B").start();
    }
}
