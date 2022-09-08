package com.example.mybatisplus;

import cn.hutool.core.thread.ThreadUtil;
import com.example.mybatisplus.designPattern.chain.VerifyHandlerChain;
import com.example.mybatisplus.pojo.eo.ResultEO;
import com.example.mybatisplus.service.IEajJzService;
import com.example.mybatisplus.utils.ConcurrentUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.function.BiConsumer;

@SpringBootTest
@Slf4j
class MybatisplusApplicationTests {

    @Autowired
    private VerifyHandlerChain verifyHandlerChain;

    @Autowired
    private IEajJzService eajJzService;

    @Value("${tempFile.path.dir}")
    private String fileDir;

    @Test
    void contextLoads() throws Exception {
//        String[] params = new String[]{"1", "2", "3"};
//        System.out.println(verifyHandlerChain.verify(Arrays.asList(params)));

        log.info("111");

        eajJzService.test6();


        // 初始化线程池
//        ConcurrentUtils.getInstance();

//        // runAsync异步调度，无返回值作为下次入参， pool使用默认的
//        CompletableFuture.runAsync(() -> {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            log.info(String.valueOf(System.currentTimeMillis()));
//        }).get();
//
//        // runAsync异步调度，无返回值作为下次入参， pool使用自定义的
//        CompletableFuture.runAsync(() -> {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            log.info(String.valueOf(System.currentTimeMillis()));
//        }, ConcurrentUtils.getPool()).get();
//
//        // supplyAsync异步调度，有返回值作为下次入参， pool使用默认的,thenAccept,前后两个任务属于同一线程
//        CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            String time = String.valueOf(System.currentTimeMillis());
//            log.info(time);
//            return time;
//        }).thenAccept(result -> {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            log.info(result);
//        }).get();

//        // supplyAsync异步调度，有返回值作为下次入参， pool使用默认的,thenAcceptAsync,前后两个任务属于不同线程
//        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            String time = String.valueOf(System.currentTimeMillis());
//            log.info(time);
//            return time;
//        }).thenApplyAsync(result -> {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            log.info(result);
//            return result;
//        });
//
////        boolean result = completableFuture.complete("111");
////        if (result) {
////            log.info("result:{}", result);
////        }
//
//        completableFuture.whenComplete(new BiConsumer() {
//            @Override
//            public void accept(Object o, Object o2) {
//                log.info(o.toString());
//                log.info(o2.toString());
//            }
//        }).get();

//        ThreadUtil.sleep(7000);

        ResultEO resultEO = new ResultEO();

//        CompletableFuture<ResultEO> completableFuture1 = (CompletableFuture<ResultEO>) ConcurrentUtils.getPool().submit(() -> {
//            log.info(String.valueOf(System.currentTimeMillis()));
//        }, resultEO);


//        CountDownLatch countDownLatch = new CountDownLatch(2);
//        for (int i=0; i<=1 ; i++) {
//            Future<ResultEO> future1 = ConcurrentUtils.getPool().submit(() -> {
//                String ahdm = new String("1110");
//                  //锁字符串
//                synchronized (ahdm.intern()) {
//                    log.info(String.valueOf(System.currentTimeMillis()));
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    log.info(String.valueOf(System.currentTimeMillis()));
//                }
//                countDownLatch.countDown();
//            }, resultEO);
//        }
//
//        countDownLatch.await();

        byte[] array = ByteBuffer.allocate(1000).putInt(1).putInt(2).array();
        String s1 = String.format("%s", Integer.toBinaryString(10 & 0xFF));
        log.info(new String(array, "UTF-8"));
        log.info(s1);
    }
}
