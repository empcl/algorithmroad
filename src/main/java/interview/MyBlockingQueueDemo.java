package interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Module Desc:
 * User: empcl
 * DateTime: 2019/8/7 10:00
 */
public class MyBlockingQueueDemo {
    private final List<String> list = new ArrayList<String>();
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private final int MIN_SIZE = 0;
    private int MAX_SIZE;
    private AtomicInteger count = new AtomicInteger(0);

    public MyBlockingQueueDemo(int maxSize) {
        this.MAX_SIZE = maxSize;
    }

    public void put(String str) {
        lock.lock();
        try {
            if (count.get() == MAX_SIZE) {
                System.out.println("添加数据线程阻塞");
                condition.await();
            }
            list.add(str);
            count.getAndIncrement();
            condition.signal();
            System.out.println("添加数据成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public String get() {
        String str = null;
        lock.lock();
        try {
            if (count.get() == MIN_SIZE) {
                System.out.println("取线程阻塞");
                condition.await();
            }
            str = list.remove(0);
            count.getAndDecrement();
            condition.signal();
        } catch (Exception e) {
            lock.unlock();
        }
        return str;
    }

    public static void main(String[] args) {
        MyBlockingQueueDemo queueDemo = new MyBlockingQueueDemo(2);
        new Thread(() -> {
            while (true) {
                String res = queueDemo.get();
                System.out.println(Thread.currentThread().getName() + "取出的值是：" + res);
            }
        }, "Get Thread").start();


        queueDemo.put("A");
        queueDemo.put("B");
        queueDemo.put("C");
        queueDemo.put("D");
        queueDemo.put("E");
    }
}
