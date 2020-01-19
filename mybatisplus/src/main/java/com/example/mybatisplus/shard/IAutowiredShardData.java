package com.example.mybatisplus.shard;

import org.aspectj.lang.JoinPoint;

public interface IAutowiredShardData {
    /**
     * 初始化
     * @param autowiredShardData
     */
    void init(AutowiredShardData autowiredShardData);

    boolean doAutowiredShardData(JoinPoint joinPoint, Object param);
}
