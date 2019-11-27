package com.example.mybatisplus.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.annotation.TableShard;
import com.example.mybatisplus.entity.EajJzEntity;
import com.example.mybatisplus.interceptor.shard.AjbsShardStrategy;

/**
 * 卷宗表mapper
 * 添加分表注解，策略为案件标识分表策略
 *
 * @author wudb
 * @since 2019-11-26
 */
@TableShard(tableBaseName = "EAJ_JZ_", shardStrategy = AjbsShardStrategy.class)
public interface EajJzMapper extends BaseMapper<EajJzEntity> {

}
