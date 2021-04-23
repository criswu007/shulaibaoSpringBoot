package com.example.mybatisplus.service;

import com.example.mybatisplus.entity.EajDqxEntity;
import com.example.mybatisplus.mapper.EajDqxMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EajDqxService {
    @Autowired
    private EajDqxMapper eajDqxMapper;

    @Transactional(value = "masterTransactionManager", rollbackFor = Exception.class)
    public void insertEajDqx() {
        EajDqxEntity eajDqxEntity = new EajDqxEntity();
        eajDqxEntity.setAhdm("12312312321");
        eajDqxEntity.setFydm("000000");
        eajDqxMapper.insert(eajDqxEntity);
    }
}
