package com.example.mybatisplus.mybatisplus.interceptor.shard;

/**
 * 分表逻辑接口
 */
public interface IShardStrategy {
    /**
     * 生成分表名称
     * @param tableName     表名
     * @param paramObject   DAO层方法参数
     * @return
     */
    String generateTableName(String tableName, Object paramObject);
}
