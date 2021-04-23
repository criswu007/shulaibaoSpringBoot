package com.example.mybatisplus.controller;

import com.example.mybatisplus.service.IUserService;
import com.example.mybatisplus.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description: ADD Description(可选). <br/>
 *
 * @author use
 * Modification History:
 * Date             Author      Version     Description
 * ------------------------------------------------------------------
 * 2020-9-23 20:32  use      1.0        1.0 Version
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private RedisUtils redisUtils;

    @PostMapping("/test")
    @ResponseBody
    public Double calculate(String type, Double fee) {
        redisUtils.set("111", 123, 10);
//        long rtn = redisUtils.incr("111", 1000);
//        System.out.println(rtn);
//        return userService.calculate(type, fee);
        return 0.0;
    }

}
