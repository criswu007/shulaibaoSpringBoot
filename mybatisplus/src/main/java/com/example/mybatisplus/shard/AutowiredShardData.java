package com.example.mybatisplus.shard;

import java.lang.annotation.*;

/**
 * 分表注解，参数上使用
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AutowiredShardData {
    boolean isAutowiredShardData() default true;

    String getMethodName() default "getAhdm";

    /**
     * 分表逻辑的实现类
     * @return
     */
    Class<? extends IAutowiredShardData> autowiredShardData() default DefaultAutowiredShardData.class;
}
