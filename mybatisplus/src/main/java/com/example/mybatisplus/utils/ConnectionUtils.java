package com.example.mybatisplus.utils;

import com.example.mybatisplus.context.SpringContext;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Description: ADD Description(可选). <br/>
 *
 * @author use
 * Modification History:
 * Date             Author      Version     Description
 * ------------------------------------------------------------------
 * 2020-3-25 20:10  use      1.0        1.0 Version
 */
public class ConnectionUtils {
    public static Connection getConnection(String dataSourceName) throws Exception {
        DataSource dataSource = (DataSource) SpringContext.getBean(dataSourceName);
        return dataSource.getConnection();
    }
}
