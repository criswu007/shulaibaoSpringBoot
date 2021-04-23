package com.example.mybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.context.TableShardContext;
import com.example.mybatisplus.entity.EajJzEntity;
import com.example.mybatisplus.mapper.EajJzMapper;
import com.example.mybatisplus.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/redis")
@Slf4j
public class RedisController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private EajJzMapper eajJzMapper;

    @RequestMapping("/test")
    @ResponseBody
    public void test(HttpServletRequest request) {
        String ahdm = "300020200202000001";
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(EajJzEntity.Fields.ahdm, ahdm);
        TableShardContext.setShardData(ahdm);
        Page<EajJzEntity> page = new Page<EajJzEntity>(0, 5).addOrder(OrderItem.asc(EajJzEntity.Fields.lastupdate));
        IPage<EajJzEntity> eajJzEntityPage = eajJzMapper.selectPage(page, queryWrapper);
//        redisUtils.set("EAJ_JZ_1_" + ahdm, eajJzEntityPage.getRecords());
//        redisUtils.lSet("EAJ_JZ_1_" + ahdm, eajJzEntityPage.getRecords());
//        redisUtils.lSet("EAJ_JZ_1_" + ahdm, "123");

//        List<String> list = new ArrayList<>();
//        list.add("111");
//        redisUtils.lSet("EAJ_JZ_1_" + ahdm, list);
//        eajJzEntityPage.getRecords().stream().forEach(item -> {
//            redisUtils.lSet("EAJ_JZ_1_" + ahdm, item);
//        });

//        List<EajJzEntity> list = redisUtils.lGet(EajJzEntity.class, "EAJ_JZ_1_" + ahdm, 0, 4);
//        log.info(list.toString());

        eajJzEntityPage.getRecords().stream().forEach(item -> {
            redisUtils.sSet("EAJ_JZ_1_111", item);
        });

        Set<EajJzEntity> set = redisUtils.sGet(EajJzEntity.class, "EAJ_JZ_1_111");
        log.info(set.toString());
    }
}
