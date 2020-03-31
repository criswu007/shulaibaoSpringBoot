package com.example.mybatisplus.designPattern.strategy;

public interface CalculateStrategy {
    String getUserType();

    Double calculatePrice(Double fee);
}
