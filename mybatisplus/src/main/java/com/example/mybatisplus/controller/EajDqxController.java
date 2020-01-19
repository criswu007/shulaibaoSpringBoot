package com.example.mybatisplus.controller;


import com.example.mybatisplus.entity.EajDqxEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wudb
 * @since 2019-11-22
 */
@Slf4j
@RestController
@RequestMapping("/eaj-dqx-entity")
public class EajDqxController {

    @Autowired
    @Qualifier("masterJdbcTemplate")
    private JdbcTemplate masterJdbcTemplate;

    @PostMapping("insert.do")
    public void insert() {
//        EajDqxEntity eajDqxEntity = new EajDqxEntity();
//        eajDqxEntity.setAhdm("789");
//        eajDqxEntity.setFydm("330000");
//        eajDqxMapper.insert(eajDqxEntity);

        List<EajDqxEntity> eajDqxEntityList =  masterJdbcTemplate.query("select * from EAJ_DQX", new BeanPropertyRowMapper(EajDqxEntity.class));
        System.out.println(eajDqxEntityList.size());
        log.info("---");

//        int count = eajDqxMapper.selectCount(new QueryWrapper<EajDqxEntity>().eq("AHDM", "111"));
//        System.out.println(count);
//
//        int effectedRows = eajDqxMapper.update(new EajDqxEntity().setFydm("320100"), new QueryWrapper<EajDqxEntity>().eq("AHDM", "111"));
//        System.out.println(effectedRows);
    }
}
