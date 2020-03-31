package com.example.mybatisplus.context.listener;

import com.example.mybatisplus.context.event.TestEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TestListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof TestEvent) {
            System.out.println("监听成功！！！");
        }
    }

    @Bean
    public TestListener getTestListener() {
        return new TestListener();
    }
}
