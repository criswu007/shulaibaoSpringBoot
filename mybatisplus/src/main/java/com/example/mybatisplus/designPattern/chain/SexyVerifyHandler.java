package com.example.mybatisplus.designPattern.chain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@Order(1)
public class SexyVerifyHandler implements VerifyHandler {
    @Override
    public List<Object> verify(List<Object> list) {
        log.info("-----------验证敏感----------");
        return list;
    }
}
