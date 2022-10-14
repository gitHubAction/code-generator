package com.longfor.csas.utils;


import com.longfor.csas.cons.ResultCodeEnum;

/**
 * @author zhangshihao01
 * @description 返回结果工具类
 * @date 2021/12/03
 */
public class ResponseMsgUtil {
    public ResponseMsgUtil() {
    }

    public static <T> Result<T> builderResponse(ResultCodeEnum codeEnum, String extraMsg, T data) {
        return new Result(codeEnum, null, extraMsg, data);
    }

    public static <T> Result<T> builderResponse(ResultCodeEnum codeEnum, T data) {
        return new Result(codeEnum.getCode(),codeEnum.getMsg(),data);
    }

    public static <T> Result<T> builderResponse(String code, String msg, T data) {
        return new Result(code,msg,data);
    }

    public static <T> Result<T> success(T data) {
        return builderResponse(ResultCodeEnum.E10200, data);
    }

    public static <T> Result<T> failure() {
        return builderResponse(ResultCodeEnum.E10400, null);
    }
    public static <T> Result<T> failure(T data) {
        return builderResponse(ResultCodeEnum.E10400, data);
    }


    public static <T> Result<T> exception() {
        return builderResponse(ResultCodeEnum.E10500, (T) null);
    }

}

