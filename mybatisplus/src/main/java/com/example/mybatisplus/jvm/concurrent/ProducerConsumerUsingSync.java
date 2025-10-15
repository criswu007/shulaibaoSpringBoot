package com.example.mybatisplus.jvm.concurrent;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerUsingSync {
    private static final int MAX_SIZE = 5;
    private final Queue<Integer> buffer = new LinkedList<>();
    private final Object lock = new Object(); // 专用锁对象

    boolean isFull = false;

    // 生产者方法
    public void produce(int value) throws InterruptedException {
        synchronized (lock) {
            // 缓冲区满时等待
            while (buffer.size() == MAX_SIZE) {
                System.out.println("缓冲区已满，生产者等待...");
                isFull = true;
                lock.wait();
            }

            buffer.add(value);
            System.out.println("生产: " + value + " | 缓冲区大小: " + buffer.size());

            lock.notifyAll(); // 唤醒所有等待线程（消费者）
        }
    }

    // 消费者方法
    public void consume() throws InterruptedException {
        synchronized (lock) {
            // 缓冲区空时等待
            while (buffer.isEmpty()) {
                System.out.println("缓冲区为空，消费者等待...");
                lock.wait();
            }

            int value = buffer.poll();
            System.out.println("消费: " + value + " | 缓冲区大小: " + buffer.size());

            lock.notifyAll(); // 唤醒所有等待线程（生产者）
        }
    }

    public static void main(String[] args) {
        ProducerConsumerUsingSync pc = new ProducerConsumerUsingSync();

        // 生产者线程
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 40; i++) {
                    pc.produce(i);
                    Thread.sleep(!pc.isFull ? 50 : 200); // 模拟生产耗时
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 消费者线程
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 40; i++) {
                    pc.consume();
                    Thread.sleep(100); // 模拟消费耗时
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();
    }
}