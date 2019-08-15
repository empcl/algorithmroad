package interview;

import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Module Desc:
 *  简易线程池手写要点:
 *      1.初始化线程池的时候新建几个线程
 *      2.execute(runnable) 将任务放到blockingqueue
 *      3.线程的run()方法，从blockingqueue中取出任务。
 *      4.定义一个任务实现runnable，完成我们要做的事。
 * User: empcl
 * DateTime: 2019/8/15 10:26
 */

class MySelfThreadPool {
    // 默认线程数
    private static final int WORK_THREAD_NUM = 5;
    // 阻塞队列长度
    private static final int TASK_NUM = 100;
    // 用于记录线程
    private HashSet<WorkThread> workThreads;
    // 存储task的阻塞队列
    private BlockingQueue<Runnable> taskQueue;

    // 初始化的时候，启动若干数量的线程
    public MySelfThreadPool() {
        taskQueue = new LinkedBlockingQueue<Runnable>(TASK_NUM);
        workThreads = new HashSet<WorkThread>();
        for (int i = 0; i < WORK_THREAD_NUM; i++) {
            WorkThread workThread = new WorkThread();
            workThread.start();
            workThreads.add(workThread);
        }
    }

    public void execute(Runnable runnable) {
        try {
            taskQueue.put(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class WorkThread extends Thread {
        public void run() {
            while (!isInterrupted()) {
                Runnable runnable = null;
                try {
                    runnable = taskQueue.take();
                    if (runnable != null) {
                        runnable.run();
                    }
                    runnable = null; // help gc
                } catch (InterruptedException e) {
                    interrupt();
                    e.printStackTrace();
                }
            }
        }
    }
}

public class TestMySelfThreadPool {
    public static void main(String[] args) {
        MySelfThreadPool threadPool = new MySelfThreadPool();
        for (int i = 0;i < 10;i++) {
            threadPool.execute(new MyTask("task" + i));
        }
    }

    static class MyTask implements Runnable {

        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println("即将执行 " + name);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("------执行" + name + "结束");
        }

        @Override
        public String toString() {
            return "MyTask{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}