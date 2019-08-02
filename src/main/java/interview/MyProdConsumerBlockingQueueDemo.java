package interview;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Module Desc:
 * 这是一个使用阻塞队列的生产者消费者例子
 * User: empcl
 * DateTime: 2019/8/2 11:52
 */

class MyResource {
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    private BlockingQueue<String> blockingQueue;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void myProd() throws Exception {
        String data = null;
        boolean retVal;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            retVal = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (retVal) {
                System.out.println(Thread.currentThread().getName() + "生产数据成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "生产数据失败");
            }
        }
        System.out.println("生产数据结束");
    }

    public void myCons() throws Exception {
        String result = null;
        while (FLAG) {
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (result == null || result.equalsIgnoreCase("")) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "在规定时间内没有消费到数据，退出消费");
                return;
            }
            System.out.println(Thread.currentThread().getName() + "消费成功数据");
        }
    }

    public void stop() {
        this.FLAG = false;
    }
}

public class MyProdConsumerBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        MyResource resource = new MyResource(new ArrayBlockingQueue<String>(5));
        new Thread(() -> {
            try {
                resource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "product").start();

        new Thread(() -> {
            try {
                resource.myCons();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "consumer").start();

        TimeUnit.SECONDS.sleep(5L);

        System.out.println("停止生产数据和消费数据");
        resource.stop();
    }
}
