package com.example.mybatisplus.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.mybatisplus.service.IEajJzService;
import com.example.mybatisplus.utils.ConcurrentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wudb
 * @since 2019-11-26
 */
@RestController
@RequestMapping("/jzgl")
public class EajJzController {

    private Integer count = 0;

    @Autowired
    @Qualifier("JzglService")
    private IEajJzService eajJzService;

    @PostMapping("test.do")
    public void Test(@RequestBody JSONObject jo) throws Exception {
        synchronized (this) {
            Thread.sleep(10000);
        }
        System.out.println("test.do");
//        EajJzEntity eajJzEntity = new EajJzEntity();
//        eajJzEntity.setAhdm("123");
//        eajJzService.test(jo, eajJzEntity);

//        ArrayList<Callable> tasks = new ArrayList<Callable>();

//        Integer taskSize = 5;
//
//        CountDownLatch countDownLatch = new CountDownLatch(taskSize);
//
//        StopWatch stopWatch = new StopWatch("test");
//        stopWatch.start();
//
//        ConcurrentUtils.getInstance();
//        for (int i=0; i<taskSize; i++) {
//            ConcurrentUtils.submitRunTask(() -> {
//                synchronized (this) {
//                    count++;
//                    try {
//                        Thread.sleep(2000);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
////                calculateCount();
//                countDownLatch.countDown();
//            });
//        }
//
//        countDownLatch.await();
//
//        System.out.println("count:" + count);
//        stopWatch.stop();
//        System.out.println(stopWatch.prettyPrint());
    }

    public synchronized void calculateCount() {
        count ++;
    }

    @PostMapping("test1.do")
    public void Test1() {
        System.out.println("test1.do");
    }
}
