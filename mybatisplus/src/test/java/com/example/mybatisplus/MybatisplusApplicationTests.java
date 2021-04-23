package com.example.mybatisplus;

import com.example.mybatisplus.designPattern.chain.VerifyHandlerChain;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisplusApplicationTests {

    @Autowired
    private VerifyHandlerChain verifyHandlerChain;

    @Test
    void contextLoads() {
        String[] params = new String[]{"1", "2", "3"};
        System.out.println(verifyHandlerChain.verify(Arrays.asList(params)));
    }

}
