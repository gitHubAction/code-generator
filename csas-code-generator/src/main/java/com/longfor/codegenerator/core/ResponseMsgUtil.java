package com.longfor.codegenerator.core;

/**
 * 返回数据结果集合
 * @author ChenBin
 */
public class ResponseMsgUtil {
    public ResponseMsgUtil() {
    }

    public static <T> Result<T> builderResponse(String code, String msg, T data) {
        return new Result(code,msg,data);
    }

    public static <T> Result<T> success(T data) {
        return builderResponse("200", "ok", data);
    }

    public static <T> Result<T> failure() {
        return builderResponse("-1", "Failure", null);
    }
    public static <T> Result<T> failure(T data) {
        return builderResponse("-1", "Failure", data);
    }


    public static <T> Result<T> exception() {
        return builderResponse("500", "request exception", (T) null);
    }

}

