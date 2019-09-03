package com.example.demo.Enums;

public enum ExceptionEnums {
    UNKNOW_ERRPR(-1,"未知错误"),
    DEL_SUCCESS(0,"删除成功"),
    PRIMAKET_SCHOOL(100,"未达到指年龄"),
    MIDDLE_SCHOOL(101,"收入不足"),
    UNFIND_ERROR(110,"未发现记录"),
    PASS_ERROR(2,"用户名或密码错误"),
    USER_TEL_ERROR(3,"手机号错误"),
    PASSWORD_ERROR(1,"用户名密码错误"),
    LOGIN_ERROR(10,"登录错误，请重新登录"),
    UPDATE_ERROR(11,"更新错误"),
    THING_UNFIND(3,"商品以下架"),
    USER_EXIST_ERROR(6,"用户不存在")

    ;

    private  Integer code;
    private  String msg;
    ExceptionEnums(Integer code,String msg) {
        this.code=code;
        this.msg=msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
