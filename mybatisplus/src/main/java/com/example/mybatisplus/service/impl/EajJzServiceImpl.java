package com.example.mybatisplus.service.impl;


import cn.hutool.core.convert.impl.UUIDConverter;
import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.example.mybatisplus.context.EventPublisher;
import com.example.mybatisplus.context.SpringContext;
import com.example.mybatisplus.context.TableShardContext;
import com.example.mybatisplus.context.event.TestEvent;
import com.example.mybatisplus.entity.EajCsEntity;
import com.example.mybatisplus.entity.EajDqxEntity;
import com.example.mybatisplus.entity.EajJzEntity;
import com.example.mybatisplus.mapper.EajCsMapper;
import com.example.mybatisplus.mapper.EajDqxMapper;
import com.example.mybatisplus.mapper.EajJzMapper;
import com.example.mybatisplus.pojo.eo.ResultEO;
import com.example.mybatisplus.service.EajDqxService;
import com.example.mybatisplus.service.IEajJzService;
import com.example.mybatisplus.service.IQxSmxxService;
import com.example.mybatisplus.shard.AutowiredShardData;
import com.example.mybatisplus.utils.ConnectionUtils;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.sqlite.jdbc3.JDBC3ResultSet;
import org.sqlite.jdbc4.JDBC4ResultSet;
import sun.net.util.IPAddressUtil;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

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

    @Autowired
    private EventPublisher eventPublisher;

    @Autowired
    private EajJzMapper eajJzMapper;

    @Autowired
    private EajDqxMapper eajDqxMapper;

    @Autowired
    private EajCsMapper eajCsMapper;

    @Autowired
    private JdbcTemplate masterJdbcTemplate;

    @Autowired
    private EajDqxService eajDqxService;

    @Autowired
    private IQxSmxxService qxSmxxService;

    static {
        System.out.println("-------------static---------------");
    }

    public EajJzServiceImpl() {
        System.out.println("------------------construct");
    }

    //该注解的方法在整个Bean初始化中的执行顺序：
    //Constructor(构造方法) -> @Autowired(依赖注入) -> @PostConstruct(注释的方法)
    @PostConstruct
    public void testPostConstruct() {
        System.out.println("---------------------------");
    }

    @Override
    public void test(JSONObject jo, @AutowiredShardData EajJzEntity eajJzEntity) {
        byte[] obj = SerializationUtils.serialize(eajJzEntity);
        EajJzEntity eajJzEntity1 = SerializationUtils.deserialize(obj);
        String str = JSON.toJSONString(eajJzEntity);
        log.info(str);
        EajJzEntity eajJzEntity2 = JSON.parseObject(str, EajJzEntity.class);
        log.info(eajJzEntity2.getNr().toString());
    }

    @Override
    public void test1() {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            connection = ConnectionUtils.getConnection("masterDataSource");
            System.out.println(connection.getTransactionIsolation());
//            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            connection.setAutoCommit(false);
            String sql = "select * from EAJ_DQX where AHDM='111'";
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();
            System.out.println(connection.getTransactionIsolation());
            while (rs.next()) {
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
            }



            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                pst.close();
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void test2() {
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = ConnectionUtils.getConnection("masterDataSource");
            connection.setAutoCommit(false);


            String sql = "update EAJ_DQX set FYDM='0000' where AHDM='111'";
            pst = connection.prepareStatement(sql);
            int row = pst.executeUpdate();
            System.out.println(row);
            System.out.println(connection.getTransactionIsolation());
            Thread.sleep(10000);

            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                pst.close();
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void test3() {
//        try {
//            MybatisSqlSessionFactoryBean sqlSessionFactoryBean = (MybatisSqlSessionFactoryBean) SpringContext.getBean("masterSqlSessionFactory");
//            sqlSessionFactoryBean.getObject().openSession();
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }

        //测试事件监听
        eventPublisher.publish(new TestEvent(new Object(), "dadada"));
    }


    @Transactional(value = "masterTransactionManager")
    @Override
    public void test4() {
        int i=100;
        while (i > 0) {
            EajCsEntity eajCsEntity = new EajCsEntity();
            eajCsEntity.setFydm("999999");
            eajCsEntity.setAhdm(UUID.randomUUID().toString(true));
            eajCsMapper.insert(eajCsEntity);

//            EajDqxEntity eajDqxEntity = new EajDqxEntity();
//            eajDqxEntity.setAhdm(UUID.randomUUID().toString(true));
//            eajDqxMapper.insert(eajDqxEntity);
            i--;
        }
    }

    @Transactional(value = "masterTransactionManager")
    public void test5() {
        EajDqxEntity eajDqxEntity = new EajDqxEntity();
        eajDqxEntity.setAhdm(UUID.randomUUID().toString(true));
        eajDqxEntity.setFydm("330300");
        eajDqxMapper.insert(eajDqxEntity);

        qxSmxxService.insert();
    }

    /**
     * 测试数据库的事务隔离级别
     */
    @Transactional(value = "masterTransactionManager")
    @Override
    public ResultEO test6() {
//        List<EajDqxEntity> list = eajDqxMapper.selectList(new QueryWrapper<EajDqxEntity>());
//        if (CollectionUtils.isEmpty(list)) {
//            eajDqxService.insertEajDqx();
//        } else {
//            eajDqxMapper.delete(new QueryWrapper<EajDqxEntity>());
//        }
//        list = eajDqxMapper.selectList(new QueryWrapper<EajDqxEntity>());

        TableShardContext.setShardData("132720190109000030");
        List<EajJzEntity> list = eajJzMapper.selectList(new QueryWrapper<EajJzEntity>().eq(EajJzEntity.Fields.ahdm.toUpperCase(), "132720190109000030"));
//        list.stream().forEach(item->{
//            log.info(item.toString());
//        });
        return ResultEO.success(list);
    }

    private Map<String, RateLimiter> rateLimiterMap = new ConcurrentHashMap<>();

    @Override
    public void test7() {
        String key = "2.103";
        RateLimiter rateLimiter;
        if (rateLimiterMap.containsKey(key)) {
            rateLimiter = rateLimiterMap.get(key);
        } else {
            rateLimiter = RateLimiter.create(5);
            rateLimiterMap.put(key, rateLimiter);
        }
        log.info("rate:{}", rateLimiter.getRate());

        int index = 5;
        while (index>0) {
            boolean b = rateLimiter.tryAcquire();
            log.info("tryAcquire:{}", b);
            if (b) {
                log.info("rate:{}", rateLimiter.getRate());
                rateLimiter.acquire();
                log.info("rate:{}", rateLimiter.getRate());
            }
            index--;
        }
        log.info("rate:{}", rateLimiter.getRate());
        try {
            Thread.sleep(1000);
            log.info("rate:{}", rateLimiter.getRate());

            Thread.sleep(1000);
            log.info("rate:{}", rateLimiter.getRate());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
