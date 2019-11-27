package com.example.mybatisplus.controller;


import com.example.mybatisplus.entity.EajDqxEntity;
import com.example.mybatisplus.mapper.EajDqxMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wudb
 * @since 2019-11-22
 */
@RestController
@RequestMapping("/eaj-dqx-entity")
public class EajDqxController {

    @Value("${master.datasource.url}")
    private String url;

    @Autowired
    private EajDqxMapper eajDqxMapper;

    @PostMapping("insert.do")
    public void insert() {
        EajDqxEntity eajDqxEntity = new EajDqxEntity();
        eajDqxEntity.setAhdm("789");
        eajDqxEntity.setFydm("330000");
        eajDqxMapper.insert(eajDqxEntity);

//        int count = eajDqxMapper.selectCount(new QueryWrapper<EajDqxEntity>().eq("AHDM", "111"));
//        System.out.println(count);
//
//        int effectedRows = eajDqxMapper.update(new EajDqxEntity().setFydm("320100"), new QueryWrapper<EajDqxEntity>().eq("AHDM", "111"));
//        System.out.println(effectedRows);
    }
}
