package com.alan.exception;

/**
 * Created by Ke Zhang on 2017/10/13.
 */
public enum  ReturnCodeModel {

    URI_IS_NULL("0001", "uri为空");

    private String code;
    private String msg;
    // 构造方法
    private ReturnCodeModel(String code, String msg) {
        this.code=code;
        this.msg=msg;

    }
    private ReturnCodeModel() {}

    public static ReturnCodeModel getByCode(String code) {
        for (ReturnCodeModel param: ReturnCodeModel.values()) {
            if (param.getCode().equals(code)) {
                return param;
            }
        }
        return null;
    }
    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
