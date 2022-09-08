package com.example.mybatisplus.jvm.concurrent;

import com.example.mybatisplus.utils.ConcurrentUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ProducerConsumer {
    /**
     * 协调多个线程对全局变量的访问，公平锁
     */
    private ReentrantLock lock = new ReentrantLock(true);

    /**
     * 存放阻塞线程
     */
    private Condition push = lock.newCondition();

    /**
     * 存放阻塞线程
     */
    private Condition take = lock.newCondition();

    private AtomicInteger count = new AtomicInteger(0);

    private static final int MAX_COUNT = 10;

    public void task() {
        lock.lock();
        try {
            if (count.intValue() < 1) {
                log.info("当前数量小于1，不拿，消费线程进入阻塞队列");
                take.await();
            }
            // do something
            int random = RandomUtils.nextInt(1, 3);
            if (random % 2 == 0) {
                Thread.sleep(2000);
            } else {
                Thread.sleep(1000);
            }
            count.decrementAndGet();
            log.info("拿走一个，还剩" + count.intValue() + "个");
            push.signal();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            lock.unlock();
        }
    }

    public void push() {
        lock.lock();
        try {
            int prePreCnt = count.intValue();
            boolean flag = count.intValue() >= MAX_COUNT;
            if (flag) {
                log.info("当前数量满了，不生产，生产线程进入阻塞队列");
                push.await();
            }
            int preCnt = count.intValue();
            // do something
            int random = RandomUtils.nextInt(1, 3);
            if (random % 2 == 0) {
                Thread.sleep(600);
            } else {
                Thread.sleep(300);
            }
            count.addAndGet(1);
            log.info("加入一个，还剩" + count.intValue() + "个：" + Thread.currentThread().getName() + ", preCnt:" + preCnt + ", prePreCnt:" + prePreCnt + ", flag:" + flag);
            take.signal();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ProducerConsumer producerConsumer = new ProducerConsumer();
        for (int i=0; i<2; i++) {
            ConcurrentUtils.getInstance().submitRunTask(
                new Consumer(producerConsumer)
            );
        }
        for (int i=0; i<2; i++) {
            ConcurrentUtils.getInstance().submitRunTask(
                    new Producer(producerConsumer)
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
