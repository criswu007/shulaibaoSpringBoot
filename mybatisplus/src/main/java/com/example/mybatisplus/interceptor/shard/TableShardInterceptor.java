package com.example.mybatisplus.interceptor.shard;

import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.example.mybatisplus.annotation.TableShard;
import com.example.mybatisplus.utils.ReflectionCacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.util.Properties;

/**
 * Description: 分表拦截器
 *
 * @author wudb
 * Modification History:
 * Date             Author      Version     Description
 * ------------------------------------------------------------------
 * 2019-11-20 10:24  use      1.0        1.0 Version
 */
@Intercepts({
    @Signature(
            type = StatementHandler.class,
            method = "prepare",
            args = {Connection.class, Integer.class}
    )
})
@Slf4j
public class TableShardInterceptor implements Interceptor {

    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");

        String id = mappedStatement.getId();
        id = id.substring(0, id.lastIndexOf("."));
        Class clazz = Class.forName(id);

        //获取TableShard注解
        TableShard tableShard = (TableShard) clazz.getAnnotation(TableShard.class);
        if (tableShard != null) {
            String tableName = tableShard.tableBaseName().toUpperCase();
            Class<? extends IShardStrategy> strategyClazz = tableShard.shardStrategy();

            IShardStrategy strategy = (IShardStrategy) ReflectionCacheUtil.newInstance(strategyClazz);
            Object parameterObject = metaObject.getValue("delegate.boundSql.parameterObject");
            String newTableName = strategy.generateTableName(tableName, parameterObject);

            //未设置分表数据，新表为null,直接返回
            if (StringUtils.isEmpty(newTableName)) {
                return invocation.proceed();
            }

            newTableName = newTableName.toUpperCase();
            //获取源sql
            String sql = ((String) metaObject.getValue("delegate.boundSql.sql")).toUpperCase();
            log.info("表{}替换前sql============>:{}", tableName, sql);
            //避免二次分表
            if (!sql.contains(newTableName)) {
                sql = sql.replaceAll(tableName, newTableName);
            }
            log.info("替换后sql==========>:{}", sql);
            metaObject.setValue("delegate.boundSql.sql", sql);
        }

        //传递给下一个拦截器处理
        return invocation.proceed();
    }

    /**
     * 返回对象本身还是对象的代理类，只有是代理类时拦截才能生效
     * @param target 被代理的对象
     * @return
     */
    @Override
    public Object plugin(Object target) {
        // 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身, 减少目标被代理的次数
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
