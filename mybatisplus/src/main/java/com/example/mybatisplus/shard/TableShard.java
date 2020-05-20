package com.example.mybatisplus.shard;

import com.example.mybatisplus.mybatisplus.interceptor.shard.IShardStrategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 分表注解，作用在类上
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableShard {
    /**
     * 去除序号的表名
     * @return
     */
    String tableBaseName();

    /**
     * 分表逻辑实现类
     */
    Class<? extends IShardStrategy> shardStrategy();
}
