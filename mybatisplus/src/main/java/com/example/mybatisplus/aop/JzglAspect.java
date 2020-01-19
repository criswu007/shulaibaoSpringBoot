package com.example.mybatisplus.aop;

import com.example.mybatisplus.shard.AutowiredShardData;
import com.example.mybatisplus.shard.IAutowiredShardData;
import com.example.mybatisplus.utils.ReflectionCacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;

/**
 * Description: springAop 切点通知，处理逻辑
 *
 * @author wudb
 * Modification History:
 * Date             Author      Version     Description
 * ------------------------------------------------------------------
 * 2020-1-19 11:22  use      1.0        1.0 Version
 */
@Aspect
@Component
@Slf4j
public class JzglAspect {

    @Before("com.example.mybatisplus.aop.PointCuts.valid()")
    public void doValid(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        log.info("args:{}", args);

        //获取参数类型
        Class[] clazzArr = new Class[args.length];

        if (args != null && args.length > 0) {
            for (int i=0; i<args.length; i++) {
                if (args[i] == null) {
                    continue;
                }
                clazzArr[i] = args[i].getClass();
            }
        }

        Parameter[] parameters = ReflectionCacheUtil.getParameters(joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), clazzArr);

        for (int i=0; i<parameters.length; i++) {
            AutowiredShardData autowiredShardData = parameters[i].getAnnotation(AutowiredShardData.class);
            if (autowiredShardData != null && autowiredShardData.isAutowiredShardData()) {
                IAutowiredShardData shardData = (IAutowiredShardData) ReflectionCacheUtil.newInstance(autowiredShardData.autowiredShardData());
                shardData.init(autowiredShardData);
                boolean hasWired = shardData.doAutowiredShardData(joinPoint, args[i]);
                if (hasWired) {
                    log.info("类{}的方法{}已设置分表数据", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
                } else {
                    log.error("类{}的方法{}未设置分表数据", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
                }
            }
        }
    }
}
