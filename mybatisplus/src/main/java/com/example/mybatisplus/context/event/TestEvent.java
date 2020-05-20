package com.example.mybatisplus.context.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义需要发布到ApplicationContext的事件
 */
public class TestEvent extends ApplicationEvent {
    public TestEvent(Object source, String id) {
        super(source);
        this.id = id;
    }

    private String id;

    public void print() {
        System.out.println("有人访问：id: " + this.id);
    }
}
