package com.work.test.tools;

import org.apache.log4j.spi.ErrorCode;

public class BaseException extends RuntimeException {
    /**
     * 默认错误号，没有明确定义的错误使用该错误号
     */
    public static int EC_Sys_Err = 10000;
    
    private int errCode;

    public BaseException(Throwable cause, int errCode, String errMessage) {
        super(errMessage, cause);
        this.errCode = errCode;
    }

    // 用于已知错误的抛出处理
    public BaseException(Throwable cause, int errCode) {
        super("ERR_" + errCode, cause);
        this.errCode = errCode;
    }

    // 不是系统错误，需要定义一个错误号业务需要
    public BaseException(int errCode) {
        super("ERR_" + errCode);
        this.errCode = errCode;
    }

    public BaseException(int errCode, String errMessage) {
        super(errMessage);
        this.errCode = errCode;
    }

    public BaseException(String errMessage) {
        super(errMessage);
        this.errCode = EC_Sys_Err;
    }

    public BaseException(Throwable cause, String errMessage) {
        super(errMessage, cause);
        this.errCode = EC_Sys_Err;
    }

    // 直接抛出原有的错误
    public BaseException(Throwable cause) {
        super(cause);
        this.errCode = EC_Sys_Err;
    }

    public int getErrCode() {
        return errCode;
    }

}