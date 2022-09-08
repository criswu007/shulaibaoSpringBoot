package com.example.mybatisplus.context.listener;

import com.example.mybatisplus.context.event.TestEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 自定义监听器
 */
@Component
public class TestListener implements ApplicationListener<TestEvent> {
    @Override
    public void onApplicationEvent(TestEvent testEvent) {
        testEvent.print();
        System.out.println("监听成功！！！");
    }
}
