package com.example.mybatisplus.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Description: ADD Description(可选). <br/>
 *
 * @author use
 * Modification History:
 * Date             Author      Version     Description
 * ------------------------------------------------------------------
 * 2020-3-25 20:11  use      1.0        1.0 Version
 */
@Component
public class SpringContext implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContextParam) throws BeansException {
        applicationContext = applicationContextParam;
    }

    /**
     * 根据ID获取bean实例对象
     * @param id bean id
     * @return
     */
    public static Object getBean(String id) {
        return applicationContext.getBean(id);
    }

    /**
     * 根据类型获取bean实例对象
     * @param clazz 类型
     * @param <T>
     * @return
     */
    public <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
}
