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
    @Autowired
    private Map<String, VerifyHandler> verifyHandlerMapInit = new ConcurrentHashMap<>();

    @Autowired
    private List<VerifyHandler> verifyHandlerListInit = new ArrayList<>();

    private List<VerifyHandler> verifyHandlers = new ArrayList<>();

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, VerifyHandler> verifyHandlerMap = applicationContext.getBeansOfType(VerifyHandler.class);
        verifyHandlerMap.forEach((key, value) -> {
            verifyHandlers.add(value);
        });
    }

    public List<Object> verify(List<Object> paramList) {
        List<Object> objects = null;
        for (VerifyHandler verifyHandler : verifyHandlers) {
            objects = verifyHandler.verify(paramList);
        }
        return objects;
    }
}
