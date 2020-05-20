package com.example.mybatisplus.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.example.mybatisplus.context.EventPublisher;
import com.example.mybatisplus.context.SpringContext;
import com.example.mybatisplus.context.event.TestEvent;
import com.example.mybatisplus.entity.EajDqxEntity;
import com.example.mybatisplus.entity.EajJzEntity;
import com.example.mybatisplus.mapper.EajDqxMapper;
import com.example.mybatisplus.mapper.EajJzMapper;
import com.example.mybatisplus.service.IEajJzService;
import com.example.mybatisplus.shard.AutowiredShardData;
import com.example.mybatisplus.utils.ConnectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
    private JdbcTemplate masterJdbcTemplate;

    @PostConstruct
    public void testPostConstruct() {
        System.out.println("---------------------------");
    }

    public EajJzServiceImpl() {
        System.out.println("------------------construct");
    }

    static {
        System.out.println("-------------static---------------");
    }

    @Override
    public void test(JSONObject jo, @AutowiredShardData EajJzEntity eajJzEntity) {
        byte[] obj = SerializationUtils.serialize(eajJzEntity);
        EajJzEntity eajJzEntity1 = SerializationUtils.deserialize(obj);
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
        EajDqxEntity eajDqxEntity = new EajDqxEntity();
        eajDqxEntity.setFydm("999999");
        eajDqxEntity.setAhdm("123");
        eajDqxMapper.insert(eajDqxEntity);
        test5();
    }

    @Transactional(value = "masterTransactionManager")
    public void test5() {
        EajJzEntity eajJzEntity = new EajJzEntity();
        eajJzEntity.setAhdm("0009909090");
        eajJzMapper.insert(eajJzEntity);
    }
}
