package com.example.mybatisplus.designPattern.chain;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 责任链设计模式
 */
@Component
public class VerifyHandlerChain implements InitializingBean {

    /**
     * 第一种实现，使用自动注入，map收集所有的验证类，key:bean.name，value:bean，应用于策略设计模式，传入key调用不同实现类
     */
    @Autowired
    private Map<String, VerifyHandler> verifyHandlerMapInit = new ConcurrentHashMap<>();

    /**
     * 第二种实现，使用自动注入，加@Order注解，基于顺序实现list收集所有的验证类
     */
    @Autowired
    private List<VerifyHandler> verifyHandlerListInit = new ArrayList<>();

    private List<VerifyHandler> verifyHandlers = new ArrayList<>();

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 第三种实现，基于InitializingBean接口，在spring注入完成后，将所有的验证类手动放入verifyHandlers中
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, VerifyHandler> verifyHandlerMap = applicationContext.getBeansOfType(VerifyHandler.class);
        verifyHandlerMap.forEach((key, value) -> {
            verifyHandlers.add(value);
        });
    }

    /**
     * 通过责任链路调用验证接口
     * @param paramList
     * @return
     */
    public List<Object> verify(List<Object> paramList) {
        List<Object> objects = null;
        for (VerifyHandler verifyHandler : verifyHandlers) {
            objects = verifyHandler.verify(paramList);
        }
        return objects;
    }
}
