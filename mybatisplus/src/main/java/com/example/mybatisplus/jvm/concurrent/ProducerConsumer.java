package com.example.mybatisplus.jvm.concurrent;

import com.example.mybatisplus.utils.ConcurrentUtils;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer {
    /**
     * 协调多个线程对全局变量的访问
     */
    private ReentrantLock lock = new ReentrantLock();

    /**
     * 存放阻塞线程
     */
    private Condition push = lock.newCondition();

    /**
     * 存放阻塞线程
     */
    private Condition take = lock.newCondition();

    private int count = 0;

    private static final int MAX_COUNT = 10;

    public void task() {
        try {
//            System.out.println("消费者尝试拿锁");
            lock.lock();
//            System.out.println("消费者拿到锁");
            if (count < 1) {
                System.out.println("当前数量小于1，不拿，消费线程进入阻塞队列");
                take.await();
            }
            // do something
            Thread.sleep(1000);
            System.out.println(count--);
            System.out.println("拿走一个，还剩" + count + "个");
            push.signal();
//            System.out.println("唤醒一个生产者成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void push() {
        try {
//            System.out.println("生产者尝试拿锁");
            lock.lock();
//            System.out.println("生产者拿锁到");
            if (count >= MAX_COUNT) {
                System.out.println("当前数量满了，不生产，生产线程进入阻塞队列");
                push.await();
            }
            // do something
            Thread.sleep(1000);
            count++;
            System.out.println("加入一个，还剩" + count + "个");
            take.signal();
//            System.out.println("唤醒一个消费者成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ProducerConsumer producerConsumer = new ProducerConsumer();
        for (int i=0; i<2; i++) {
            ConcurrentUtils.getInstance().submitRunTask(
                new Producer(producerConsumer)
            );
        }

        for (int i=0; i<2; i++) {
            ConcurrentUtils.getInstance().submitRunTask(
                new Consumer(producerConsumer)
            );
        }
    }
}

class Producer implements Runnable {
    private ProducerConsumer producerConsumer;

    public Producer (ProducerConsumer producerConsumer) {
        this.producerConsumer = producerConsumer;
    }

    @Override
    public void run() {
        while (true) {
            producerConsumer.push();
        }
    }
}

class Consumer implements Runnable {
    private ProducerConsumer producerConsumer;

    public Consumer (ProducerConsumer producerConsumer) {
        this.producerConsumer = producerConsumer;
    }

    @Override
    public void run() {
        while (true) {
            producerConsumer.task();
        }
    }
}
