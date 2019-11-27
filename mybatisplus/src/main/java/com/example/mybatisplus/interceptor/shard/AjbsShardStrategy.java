package com.example.mybatisplus.interceptor.shard;

import com.example.mybatisplus.context.TableShardContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * Description: 案件标识分表逻辑实现类
 *
 * @author use
 * Modification History:
 * Date             Author      Version     Description
 * ------------------------------------------------------------------
 * 2019-11-20 11:01  use      1.0        1.0 Version
 */
@Slf4j
public class AjbsShardStrategy implements IShardStrategy{
    @Override
    public String generateTableName(String tableName, Object paramObject) {
        if (StringUtils.isEmpty(TableShardContext.getShardData())) {
            log.error(tableName + "未设置分表数据，请设置");
            return null;
        }

        String data = TableShardContext.getShardData();
        tableName += data.substring(data.length() - 1);
        return tableName;
    }
}
