package com.example.mybatisplus.aop;

import org.aspectj.lang.annotation.Pointcut;

/**
 * AOP 切点定义
 */
public class PointCuts {
    @Pointcut("execution(public * com.example.mybatisplus.service.impl..*.*(..))")
    public void valid(){}
}
