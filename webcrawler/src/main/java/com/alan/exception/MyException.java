package com.alan.exception;

/**
 * Created by Ke Zhang on 2017/10/13.
 */
public class MyException extends RuntimeException {

    private static final long serialVersionUID = 5719639814985692478L;

    private ReturnCodeModel exception_type;


    public MyException(ReturnCodeModel type) {
        super(type.getMsg());

        this.exception_type = type;
    }

    public String getErrorCode() {
        return this.exception_type.getCode();
    }

    public String getErrorDeclare() {
        return this.exception_type.getMsg();
    }

    public ReturnCodeModel getException_type() {
        return exception_type;
    }
}
