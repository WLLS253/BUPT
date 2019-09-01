package com.example.demo.Exception;

import com.example.demo.Enums.ExceptionEnums;

public class LoginException extends RuntimeException{
    private Integer code;



    public LoginException(ExceptionEnums exceptionEnums){
        super(exceptionEnums.getMsg());
        this.code=exceptionEnums.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


}
