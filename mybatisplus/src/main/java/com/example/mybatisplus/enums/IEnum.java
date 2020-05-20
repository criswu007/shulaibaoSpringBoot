package com.example.mybatisplus.enums;

import java.io.Serializable;

/**
 * Description:枚举接口
 * Project Name:stzx
 * File Name:IEnum
 * Package Name:com.zjpth.stzx.jzgl.api.domain.enums
 * Copyright (c) 2019,南京通达海信息科技有限公司 All Rights Reserved.
 * <p>
 * Modification History:
 * Date             Author      Version     Description
 * ------------------------------------------------------------------
 * 2019/7/16 15:47         廖齐龙      1.0        1.0 Version
 **/
public interface IEnum<T extends Serializable> {
    /**
     * 获取注释
     */
    String getComment();

    /**
     * 获取值
     */
    T getValue();
}
