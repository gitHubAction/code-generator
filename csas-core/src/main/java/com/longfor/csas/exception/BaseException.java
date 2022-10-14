package com.longfor.csas.exception;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description
 * @date 2021/2/7 14:17
 */
public class BaseException extends RuntimeException implements CommonError {

    private CommonError commonError;

    private String errorMsg;

    public BaseException(CommonError commonError){
        super();
        this.commonError = commonError;
    }

    /** 可以设置自定义提示信息，不影响原始code码的信息*/
    public BaseException(CommonError commonError, String errorMsg){
        super();
        this.commonError = commonError;
        this.errorMsg = errorMsg;
    }

    @Override
    public String getCode() {
        return this.commonError.getCode();
    }

    @Override
    public String getMsg() {
        return this.commonError.getMsg();
    }

    @Override
    public CommonError setMsg(String errMsg) {
        this.commonError.setMsg(errMsg);
        return this;
    }

    @Override
    public CommonError setCode(String code) {
        this.commonError.setCode(code);
        return this;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
