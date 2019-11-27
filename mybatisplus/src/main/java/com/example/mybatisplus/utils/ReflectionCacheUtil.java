package com.example.mybatisplus.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:缓存JAVA反射信息，以提高效率
 * Project Name:stzx
 * File Name:ReflectionCacheUtils
 * Package Name:com.zjpth.stzx.jzgl.bp.common.util
 * Copyright (c) 2019,南京通达海信息科技有限公司 All Rights Reserved.
 * <p>
 * Modification History:
 * Date             Author      Version     Description
 * ------------------------------------------------------------------
 * 2019/7/15 14:42         廖齐龙      1.0        1.0 Version
 **/
public class ReflectionCacheUtil {

    private static final Logger log = LoggerFactory.getLogger(ReflectionCacheUtil.class);

    /**
     * 缓存参数对象
     */
    private static final Map<String, Parameter[]> MAP_PARAMETER = new HashMap<>();

    /**
     * 缓存类对象
     */
    private static final Map<String, Class<?>> MAP_CLASS = new HashMap<>();

    /**
     * 缓存方法对象
     */
    private static final Map<String, Method> MAP_METHOD = new HashMap<>();


    /**
     * 缓存类实例对象
     */
    private static final Map<Class<?>, Object> MAP_INSTANCE = new HashMap<>();


    /**
     * 缓存父类及当前类的所有方法对象
     */
    private static final Map<Class<?>, List<Field>> MAP_FIELD = new HashMap<>();

    /**
     * 获取指定类指定方法的参数
     *
     * @param className  类名
     * @param methodName 方法名
     * @param paramTypes 参数类型
     * @return:
     * @Author 廖齐龙
     * @date 2019/7/15 14:46
     */
    public static Parameter[] getParameters(String className, String methodName, Class<?>... paramTypes) {
        Parameter[] parameters = new Parameter[0];
        try {
            if (StringUtils.isEmpty(className) || StringUtils.isEmpty(methodName) || paramTypes == null) {
                return new Parameter[1];
            }
            String key = buildParamKey(className, methodName, paramTypes);
            log.debug("获取参数信息：{}", key);
            parameters = MAP_PARAMETER.get(key);
            if (parameters == null) {
                //当前方法
                Method curMethod = getMethod(className, methodName, paramTypes);
                if(curMethod != null) {
                    parameters = curMethod.getParameters();
                    MAP_PARAMETER.put(key, parameters);
                }
            } else {
                log.debug("从缓存获取参数信息：{}", key);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return parameters;
    }

    /**
     * 拼接参数主键字符串
     *
     * @param className  类名
     * @param methodName 方法名
     * @param paramTypes 参数类型
     * @return:
     * @Author 廖齐龙
     * @date 2019/7/15 16:05
     */
    private static String buildParamKey(String className, String methodName, Class<?>... paramTypes) {
        StringBuilder key = new StringBuilder(className).append("-").append(methodName).append("-");
        for (Class<?> clazz : paramTypes) {
            key.append(clazz.getTypeName()).append(";");
        }
        return key.toString();
    }

    /**
     * @param className
     * @return:
     * @Author 廖齐龙
     * @date 2019/7/15 15:03
     */
    public static Class<?> getClass(String className) throws ClassNotFoundException {
        if (StringUtils.isEmpty(className)) {
            return null;
        }
        Class<?> clazz = MAP_CLASS.get(className);
        log.debug("获取类信息：{}", className);
        if (clazz == null) {
            clazz = Class.forName(className);
            MAP_CLASS.put(className, clazz);
        } else {
            log.debug("从缓存获取类信息：{}", className);
        }
        return clazz;
    }

    /**
     * 获取指定类的指定方法
     *
     * @param className  类名
     * @param methodName 方法名
     * @param paramTypes 参数类型
     * @return:
     * @Author 廖齐龙
     * @date 2019/7/15 14:46
     */
    public static Method getMethod(String className, String methodName, Class<?>... paramTypes) {
        Method method = null;
        try {
            if (StringUtils.isEmpty(className) || StringUtils.isEmpty(methodName) || paramTypes == null) {
                return null;
            }
            String key = buildParamKey(className, methodName, paramTypes);
            log.debug("获取方法信息：{}", key);
            method = MAP_METHOD.get(key);
            if (method == null) {
                method = ReflectionUtils.findMethod(getClass(className), methodName, paramTypes);
                MAP_METHOD.put(key, method);
            } else {
                log.debug("从缓存获取方法信息：{}", key);
            }
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage(), e);
        }
        return method;
    }

    /**
     * 获取无参数的指定方法
     *
     * @param className  类名
     * @param methodName 方法名
     * @Author 廖齐龙
     * @date 2019/7/15 14:46
     */
    public static Method getMethod(String className, String methodName) {
        return getMethod(className, methodName, new Class<?>[0]);
    }

    /**
     * 获取指定类实例
     * @param clazz
     * @return:
     * @Author 廖齐龙
     * @date 2019/7/15 15:21
     */
    public static Object newInstance(Class<?> clazz) {
        Object obj = null;
        try {
            if (clazz == null) {
                return new Object();
            }
            log.debug("获取对象实例：{}", clazz.getName());
            obj = MAP_INSTANCE.get(clazz);
            if (obj == null) {
                obj = clazz.newInstance();
                MAP_INSTANCE.put(clazz, obj);
            } else {
                log.debug("从缓存获取实例:{}", clazz.getName());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return obj;
    }
    /**
     * 获取父类及当前类的所有方法
     * @param cls
     * @return
     * @Author 顾锡明
     * @date 2019/7/22 09:21
     */
    public static List<Field> getAllFieldsList(final Class<?> cls) {
        if(cls == null){
            return new ArrayList<>();
        }
        log.debug("获取类：{}", cls);
        List<Field> allFields = MAP_FIELD.get(cls);
        if (allFields == null) {
            allFields= new ArrayList<>();
            Class<?> currentClass = cls;
            while (currentClass != null) {
                final Field[] declaredFields = currentClass.getDeclaredFields();
                for (final Field field : declaredFields) {
                    allFields.add(field);
                }
                currentClass = currentClass.getSuperclass();
            }
            MAP_FIELD.put(cls,allFields);
        } else {
            log.debug("从缓存获取父类及当前类的所有方法:{}", allFields);
        }
        return allFields;
    }

    /**
     * 获取字段
     * @param cls
     * @param name
     * @return
     */
    public static Field findField(Class<?> cls,String name){
        List<Field> allFieldsList = getAllFieldsList(cls);
        if(allFieldsList.isEmpty()){
            return null;
        }
        for(Field field:allFieldsList){
            if(name == null || name.equals(field.getName())){
                return field;
            }
        }
        return null;
    }

    /**
     * 获取类的变量值
     * @param name 变量名
     * @param target 目标类
     * @return
     * @throws NoSuchFieldException
     */
    public static Object getField(String name,Object target) throws NoSuchFieldException {
        if(target == null){
            return null;
        }

        Field field = findField(target.getClass(),name);
        if(field == null){
            throw new NoSuchFieldException("类"+target.getClass().getTypeName() +"没有此变量"+name);
        }
        boolean isAccessible = field.isAccessible();
        if(!isAccessible) {
            field.setAccessible(true);
        }
        Object param = ReflectionUtils.getField(field,target);
        if(!isAccessible) {
            field.setAccessible(false);
        }
        return param;
    }

    private ReflectionCacheUtil() {
    }
}
