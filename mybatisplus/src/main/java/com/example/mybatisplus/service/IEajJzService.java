package com.example.mybatisplus.service;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.entity.EajJzEntity;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wudb
 * @since 2019-11-26
 */
public interface IEajJzService extends IService<EajJzEntity> {
    void test(JSONObject jo, EajJzEntity eajJzEntity);

    void test1();

    void test2();

    void test3();

    void test4();
}
