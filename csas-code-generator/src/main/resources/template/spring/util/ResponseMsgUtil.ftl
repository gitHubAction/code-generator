package  ${basePackage}.utils;


/**
 * @author ${author}
 * @description 返回结果工具类
 * @date ${date}
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

