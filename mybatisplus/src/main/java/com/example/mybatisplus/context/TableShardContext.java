package com.example.mybatisplus.context;

/**
 * Description: ADD Description(可选). <br/>
 *
 * @author use
 * Modification History:
 * Date             Author      Version     Description
 * ------------------------------------------------------------------
 * 2019-11-20 11:23  use      1.0        1.0 Version
 */
public class TableShardContext {
    private static ThreadLocal<String> contextLocal = new ThreadLocal<>();

    public TableShardContext() {

    }

    public static String getShardData() {
        return contextLocal.get();
    }

    public static void setShardData(String data) {
        contextLocal.set(data);
    }

    public static void cleanContext() {
        contextLocal.remove();
    }
}
