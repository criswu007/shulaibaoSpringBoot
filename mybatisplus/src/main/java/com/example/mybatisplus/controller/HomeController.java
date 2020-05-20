package com.example.mybatisplus.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Api(tags = "主页", hidden = true)
@ApiSort(0)
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "forward:doc.html";
    }
}
