package com.example.mybatisplus.jvm.concurrent;


import com.example.mybatisplus.utils.ConcurrentUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentTest {
    /**
     * AQS框架下的锁，会先尝试CAS乐观锁去获取锁，获取不到，才会转换为悲观锁，如ReentrantLock
     *
     * // 如果获取不到锁，则线程阻塞，进入阻塞队列；阻塞队列会不断轮转去获取锁：AbstractQueuedSynchronizer.acquireQueued();
     * lock.lock();
     *
     */
    private static Lock lock = new ReentrantLock();

    private static Condition condition = lock.newCondition();

    private static int CNT = 0;

    private static Stack stack = new Stack();

    public static void main(String[] args) {
        for (int i=0; i<2; i++) {
            ConcurrentUtils.getInstance().submitRunTask(
                new Producer()
            );
        }
    }

    static class Producer implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("尝试获取锁...");
                lock.lock();
                System.out.println("获取锁成功！");

                // 1、线程释放lock 并 加入到等待队列的结尾（这时候其他线程可以获取lock）
                // 2、（自旋） **直到被其他线程唤醒** 加入到同步队列
                // 3、进入同步队列后，一直自旋，直到竞争到lock，才返回，否则一直阻塞
                condition.await();


                // 执行业务逻辑
                System.out.println("开始执行业务。。。");
                Thread.sleep(3000);
                System.out.println(CNT++);
                System.out.println("结束执行业务。。。");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("解锁成功！");
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {

        }
    }
}
