package com.example.mybatisplus.pojo.eo;

import com.example.mybatisplus.pojo.enums.Status;

public class ResultEO extends Result {


    public static ResultEO success() {
        ResultEO result = new ResultEO();
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getDetail());
        return result;
    }

    public static ResultEO success(Object data) {
        ResultEO result = new ResultEO();
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getDetail());
        result.setData(data);
        return result;
    }


    public static ResultEO success(String message, int responseCode) {
        ResultEO result = ResultEO.success();
        result.setMsg(message);
        result.setCode(responseCode);
        return result;
    }

    public static ResultEO success(String message, int responseCode, Object data) {
        ResultEO result = ResultEO.success();
        result.setMsg(message);
        result.setCode(responseCode);
        result.setData(data);
        return result;
    }

    public static ResultEO fail() {
        ResultEO result = new ResultEO();
        result.setCode(Status.FAIL.getCode());
        result.setMsg(Status.FAIL.getDetail());
        return result;
    }

    public static ResultEO fail(String message) {
        ResultEO result = fail();
        result.setMsg(message);
        return result;
    }

    public static ResultEO fail(int responseCode, String message) {
        ResultEO result = fail();
        result.setMsg(message);
        result.setCode(responseCode);
        return result;
    }


    public static ResultEO fail(String msg, int responseCode) {
        ResultEO result = fail(msg);
        result.setCode(responseCode);
        return result;
    }
}
