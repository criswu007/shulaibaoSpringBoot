package com.example.mybatisplus.shard;

import com.example.mybatisplus.context.TableShardContext;
import com.example.mybatisplus.utils.ReflectionCacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * Description: ADD Description(可选). <br/>
 *
 * @author use
 * Modification History:
 * Date             Author      Version     Description
 * ------------------------------------------------------------------
 * 2020-1-19 14:12  use      1.0        1.0 Version
 */
@Slf4j
public class DefaultAutowiredShardData implements IAutowiredShardData{

    /**
     * 获取分表数据的方法名称
     */
    private String getMethodName = "";

    @Override
    public void init(AutowiredShardData autowiredShardData) {
        getMethodName = autowiredShardData.getMethodName();
    }

    /**
     * 自动注入分表业务数据
     * @param joinPoint 切点连接线
     * @param param 有AutowiredShardData注解的参数
     */
    @Override
    public boolean doAutowiredShardData(JoinPoint joinPoint, Object param) {
        Method method = null;
        if (!StringUtils.isEmpty(getMethodName)) {
            method = ReflectionCacheUtil.getMethod(param.getClass().getName(), getMethodName);
        }

        if (method != null) {
            String ajbs = (String) ReflectionUtils.invokeMethod(method, param);
            TableShardContext.setShardData(ajbs);
            log.info("设置{}.{}分表业务数据：ajbs = {}", new Object[]{
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    ajbs
            });
            return true;
        }
        return false;
    }
}
