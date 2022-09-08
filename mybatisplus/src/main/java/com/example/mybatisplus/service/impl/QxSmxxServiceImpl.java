package com.example.mybatisplus.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.lang.UUID;
import com.example.mybatisplus.entity.cluster.QxSmxxEntity;
import com.example.mybatisplus.mapper.cluster.QxSmxxMapper;
import com.example.mybatisplus.service.IQxSmxxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class QxSmxxServiceImpl implements IQxSmxxService {

    @Autowired
    private QxSmxxMapper qxSmxxMapper;

    @Override
    @Transactional(value = "clusterTransactionManager", rollbackFor = Exception.class)
    public void insert() {
        QxSmxxEntity qxSmxxEntity = new QxSmxxEntity();
        qxSmxxEntity.setFwlsh(new Snowflake(1, 2).nextIdStr());
        qxSmxxEntity.setFwbh(UUID.randomUUID().toString(true));
        qxSmxxMapper.insert(qxSmxxEntity);
    }

}
