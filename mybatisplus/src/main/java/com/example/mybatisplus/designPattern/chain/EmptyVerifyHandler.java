package com.example.mybatisplus.designPattern.chain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@Order(3)
public class EmptyVerifyHandler implements VerifyHandler {
    @Override
    public List<Object> verify(List<Object> list) {
        log.info("-----------验证空----------");
        return list;
    }
}
