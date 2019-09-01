package com.example.demo.Exception;


import com.example.demo.Enums.ExceptionEnums;
import org.springframework.web.bind.annotation.ControllerAdvice;


public class ClientException extends  RuntimeException{

    private  Integer code;

    public ClientException(ExceptionEnums exceptionEnums) {
        super(exceptionEnums.getMsg());
        this.code = exceptionEnums.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}


