package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.designPattern.strategy.CalculateStrategy;
import com.example.mybatisplus.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {
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
        log.info(calculateStrategy.calculatePrice(fee).toString());
        CalculateStrategy calculateStrategy = calculateStrategyMap.get(userType);
        return calculateStrategy.calculatePrice(fee);
    }
}
