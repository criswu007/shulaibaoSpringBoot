package com.example.mybatisplus.pojo.enums;

public enum Status {

    SUCCESS(0,"成功"),FAIL(1,"失败"),UNLOGIN(401,"未登录");

    private int code;

    private String detail;

    public int getCode() {
        return code;
    }

    public String getDetail() {
        return detail;
    }

    Status(int code, String detail){
        this.code = code;
        this.detail = detail;
    }

}
