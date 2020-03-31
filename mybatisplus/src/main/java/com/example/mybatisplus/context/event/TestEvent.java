package com.example.mybatisplus.context.event;

import org.springframework.context.ApplicationEvent;

public class TestEvent extends ApplicationEvent {
    public TestEvent(Object source) {
        super(source);
    }
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
