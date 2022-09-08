package com.example.mybatisplus.utils;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Description: 多线程工具类-单例
 *
 * @author use
 * Modification History:
 * Date             Author      Version     Description
 * ------------------------------------------------------------------
 * 2020-1-20 11:16  use      1.0        1.0 Version
 */
public class ConcurrentUtils {
    private static ExecutorService pool = null;

    public static ExecutorService getPool() {
        return pool;
    }

    public static void setPool(ExecutorService pool) {
        ConcurrentUtils.pool = pool;
    }

    private static ConcurrentUtils concurrentUtils;

    private ConcurrentUtils() {
    }

    /**
     * 双检查锁机制确保ConcurrentUtils为单例
     * @return
     */
    public static ConcurrentUtils getInstance() {
        if (concurrentUtils == null) {
            synchronized (ConcurrentUtils.class) {
                if (concurrentUtils == null) {
                    concurrentUtils = new ConcurrentUtils();
                    pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
                }
            }
        }
        return concurrentUtils;
    }

    public static <T> Future<T> submitTask(Callable<T> task) {
        return pool.submit(task);
    }

    public static Future<?> submitRunTask(Runnable task) {
        return pool.submit(task);
    }

    public static <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws Exception{
        return pool.invokeAll(tasks);
    }

    public static <T> List<Future<T>> invokeAllCall(Collection<Callable<T>> tasks) throws Exception{
        return pool.invokeAll(tasks);
    }
}
