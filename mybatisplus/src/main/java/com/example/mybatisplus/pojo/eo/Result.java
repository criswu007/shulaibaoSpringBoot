package com.example.mybatisplus.pojo.eo;

public class Result {

    /**
     * 返回码
     */

    private int code;
    /**
     * 返回信息
     */

    private String msg;

    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 请求成功code
     */
    protected static final int DEFAULT_SUCCESS_CODE = 1;

    /**
     * 请求失败code
     */
    protected static final int DEFAULT_FAIL_CODE = 0;
    /**
     * 请求成功message
     */
    protected static final String DEFAULT_SUCCESS_MESSAGE = "成功";
    /**
     * 请求失败Message
     */
    protected static final String DEFAULT_FAIL_MESSAGE = "失败";


}
