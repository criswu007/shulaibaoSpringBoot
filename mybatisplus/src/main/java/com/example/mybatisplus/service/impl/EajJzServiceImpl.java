package com.example.mybatisplus.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisplus.entity.EajJzEntity;
import com.example.mybatisplus.mapper.EajJzMapper;
import com.example.mybatisplus.service.IEajJzService;
import com.example.mybatisplus.shard.AutowiredShardData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wudb
 * @since 2019-11-26
 */
@Service("JzglService")
@Slf4j
public class EajJzServiceImpl extends ServiceImpl<EajJzMapper, EajJzEntity> implements IEajJzService {
    @Override
    public void test(JSONObject jo, @AutowiredShardData EajJzEntity eajJzEntity) {
    }
}
