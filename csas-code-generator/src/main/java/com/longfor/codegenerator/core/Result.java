package com.longfor.codegenerator.core;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

/**
 * 带有traceId的返回数据结果集合
 * @author zhangshihao01
 */
@Data
@ToString(callSuper = true)
public class Result<T> {
    public static final String DEFAULT_CODE = "200";
    public static final String DEFAULT_MESSAGE = "ok";

    private String code;
    private String message;
    private String extraCode;
    private String extraMessage;
    private String tracestack;
    private String url;
    private T data;
    private String traceId;


    public Result(String code, String message, String extraCode, String extraMessage, T data) {
        this.code = StringUtils.defaultIfBlank(code, DEFAULT_CODE);
        this.message = StringUtils.defaultIfBlank(message, DEFAULT_MESSAGE);
        this.extraCode = StringUtils.trimToEmpty(extraCode);
        this.extraMessage = StringUtils.trimToEmpty(extraMessage);
        this.data = data;
        this.traceId = MDC.get(CodeHttp.TRACE_ID);
    }


    public Result(String code, String message, T data) {
        this(code, message, null, null, data);
    }

    public Result(T data) {
        this(null, null, data);
    }

    public Result() {
        this(null);
    }
}