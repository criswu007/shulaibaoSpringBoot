package com.example.mybatisplus.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.mybatisplus.entity.EajJzEntity;
import com.example.mybatisplus.service.IEajJzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wudb
 * @since 2019-11-26
 */
@RestController
@RequestMapping("/jzgl")
public class EajJzController {

    @Autowired
    @Qualifier("JzglService")
    private IEajJzService eajJzService;

    @PostMapping("test.do")
    public void Test(@RequestBody JSONObject jo) {
        EajJzEntity eajJzEntity = new EajJzEntity();
        eajJzEntity.setAhdm("123");
        eajJzService.test(jo, eajJzEntity);
    }
}
