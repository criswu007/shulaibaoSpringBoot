package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.designPattern.strategy.CalculateStrategy;
import com.example.mybatisplus.service.IUserService;
import com.example.mybatisplus.utils.ConcurrentUtils;
import io.netty.util.concurrent.CompleteFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    /**
     * 使用spring的自动注入，实现CalculateStrategy的所有接口类放入map中
     */
    @Autowired
    Map<String, CalculateStrategy> calculateStrategyMap = new ConcurrentHashMap<>();

    @Autowired
    private CalculateStrategy calculateStrategy;

//    public UserService(List<CalculateStrategy> calculateStrategyList) { //利用Spring的特性--在容器中的所有实现类拿出来  放入list中
//        for (CalculateStrategy calculateStrategy : calculateStrategyList) {
//            calculateStrategyMap.put(calculateStrategy.getUserType(), calculateStrategy);
//        }
//    }

    //计算
    @Override
    public Double calculate(String userType, Double fee) {

        try {
            List<Callable<String>> callableList = new ArrayList<>();
            callableList.add(() -> {
                return "";
            });
            ConcurrentUtils.getInstance();
            List<Future<String>> futureList = ConcurrentUtils.invokeAllCall(callableList);

//            future.get();

//            CountDownLatch countDownLatch = new CountDownLatch(10);
//
//            countDownLatch.await();

            for (Future<String> future : futureList) {
                while (future.isDone()) {

                }
            }

            CompletableFuture.runAsync(() -> {
                log.info("111");
            });
            CompletableFuture.runAsync(() -> {
                log.info("111");
            }, ConcurrentUtils.getPool());



        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }










        log.info(calculateStrategy.calculatePrice(fee).toString());
        CalculateStrategy calculateStrategy = calculateStrategyMap.get(userType);
        return calculateStrategy.calculatePrice(fee);
    }
}
