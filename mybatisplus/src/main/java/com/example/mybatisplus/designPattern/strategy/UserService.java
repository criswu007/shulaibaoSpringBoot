package com.example.mybatisplus.designPattern.strategy;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    Map<String, CalculateStrategy> calculateStrategyMap = new HashMap<>();

    public UserService(List<CalculateStrategy> calculateStrategyList) { //利用Spring的特性--在容器中的所有实现类拿出来  放入list中
        for (CalculateStrategy calculateStrategy : calculateStrategyList) {
            calculateStrategyMap.put(calculateStrategy.getUserType(), calculateStrategy);
        }
    }

    //计算
    public Double calculate(String userType, Double fee) {
        CalculateStrategy calculateStrategy = calculateStrategyMap.get(userType);
        return calculateStrategy.calculatePrice(fee);
    }
}
