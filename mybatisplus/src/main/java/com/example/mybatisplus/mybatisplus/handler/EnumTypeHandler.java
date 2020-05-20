package com.example.mybatisplus.mybatisplus.handler;

import com.example.mybatisplus.enums.IEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Description:枚举类型处理器，自动将枚举值存入数据库，将数据库值转换成对应枚举
 * Project Name:stzx
 * File Name:EnumTypeHandler
 * Package Name:com.zjpth.stzx.jzgl.bp.handle
 * Copyright (c) 2019,南京通达海信息科技有限公司 All Rights Reserved.
 * <p>
 * Modification History:
 * Date                 @Author       Version     Description
 * ------------------------------------------------------------------
 * 2019/7/16 16:09         廖齐龙      1.0        1.0 Version
 **/
@Slf4j
@MappedTypes({IEnum.class})
@Component
public class EnumTypeHandler<T extends IEnum> extends BaseTypeHandler<T> {
    /**
     * 具体枚举类型
     */
    private Class<T> type;

    /**
     * 无参构造，必须声明
     */
    public EnumTypeHandler() {
    }

    /**
     * 构造方法接收具体的Class类型
     *
     * @param type 枚举具体类型
     */
    public EnumTypeHandler(Class<T> type) {
        if (type == null) {
            throw new RuntimeException("类型不能为null");
        }
        this.type = type;
    }

    /**
     * 将枚举塞入数据库
     *
     * @param preparedStatement pst对象
     * @param i                 参数索引
     * @param t                 枚举对象
     * @param jdbcType          数据库字段类型
     * @return:
     * @Author 廖齐龙
     * @date 2019-7-17 上午 09:14
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, T t, JdbcType jdbcType) throws SQLException {
        log.debug("设置枚举：注释={},值={},class={}", t.getComment(), t.getValue(), type.getName());
        if (jdbcType == null) {
            preparedStatement.setObject(i, t.getValue());
        } else {
            preparedStatement.setObject(i, t.getValue(), jdbcType.TYPE_CODE);
        }
    }

    /**
     * 将数据库值转换成对应枚举
     *
     * @param resultSet rs对象
     * @param s         字段名
     * @return:
     * @Author 廖齐龙
     * @date 2019-7-17 上午 09:16
     */
    @Override
    public T getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return getEnumByValue(resultSet.getObject(s));
    }

    /**
     * 将数据库值转换成对应枚举
     *
     * @param resultSet rs对象
     * @param i         字段索引
     * @return:
     * @Author 廖齐龙
     * @date 2019-7-17 上午 09:16
     */
    @Override
    public T getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return getEnumByValue(resultSet.getObject(i));
    }

    /**
     * 将数据库值转换成对应枚举
     *
     * @param callableStatement 调用存储过程对象
     * @param i                 字段索引
     * @return:
     * @Author 廖齐龙
     * @date 2019-7-17 上午 09:16
     */
    @Override
    public T getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return getEnumByValue(callableStatement.getObject(i));
    }

    /**
     * 将值转成对应枚举
     *
     * @param val 值
     * @return:T
     * @Author 廖齐龙
     * @date 2019-7-17 上午 09:16
     */
    public T getEnumByValue(Object val) {
        T[] tar = type.getEnumConstants();
        if (tar == null) {
            log.error("类{}不是枚举", type.getName());
            throw new RuntimeException("类" + type.getName() + "不是枚举");
        }

        for (T t : tar) {
            if (val == null || t.getValue().equals(val)) {
                log.debug("值：{}转换为枚举：{}", val, t.getComment());
                return t;
            }
        }
        return null;
    }
}
