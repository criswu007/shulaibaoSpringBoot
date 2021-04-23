package com.example.mybatisplus.designPattern.chain;

import java.util.List;

public interface VerifyHandler {
    List<Object> verify(List<Object> list);
}
