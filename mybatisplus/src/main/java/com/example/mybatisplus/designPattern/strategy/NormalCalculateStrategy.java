package com.example.mybatisplus.designPattern.strategy;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("normal")
@Primary
public class NormalCalculateStrategy implements CalculateStrategy {
    @Override
    public String getUserType() {
        return "normal";
    }

    @Override
    public Double calculatePrice(Double fee) {
        return fee;
    }
}
