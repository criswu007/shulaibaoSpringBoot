package com.example.mybatisplus;

public class Test {
    Integer count = 0;

    Integer MAX = 10;

    synchronized void add(Integer i) {
        while  (count == MAX) {
            System.out.println("MAX");
            try {
                System.out.println("add thread wait...");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count += 1;
        System.out.println("生产 " + i + ":  缓冲区大小: " + count);
        this.notifyAll();
    }

    synchronized void minus(Integer i) {
        while (count == 0) {
            System.out.println("ZERO");
            try {
                System.out.println("minus thread wait...");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count -= 1;
        System.out.println("消费 " + i + ":  缓冲区大小: " + count);
        this.notifyAll();
    }

    public static void main(String[] args) {
        Test test = new Test();

        new Thread(() -> {
            for (int i=0; i<50; i++) {
                test.minus(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },  "minusThread-").start();
        System.out.println("minusThread- start");


        new Thread(() -> {
            for (int i=0; i<50; i++) {
                test.add(i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },  "addThread-").start();
        System.out.println("addThread- start");
    }

    public static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
