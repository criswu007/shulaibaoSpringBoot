package com.example.mybatisplus.designPattern.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Description: ADD Description(可选). <br/>
 *
 * @author use
 * Modification History:
 * Date             Author      Version     Description
 * ------------------------------------------------------------------
 * 2020-9-23 20:26  use      1.0        1.0 Version
 */
@Service("vip")
@Slf4j
public class VipCalculateStrategy implements CalculateStrategy {
    @Override
    public String getUserType() {
        return "vip";
    }

    @Override
    public Double calculatePrice(Double fee) {
        return fee * 0.8;
    }
}
