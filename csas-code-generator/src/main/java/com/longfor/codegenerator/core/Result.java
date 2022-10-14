package com.longfor.codegenerator.core;

import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gaia.gfs.web.core.toolkit.LFHttp;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.slf4j.MDC;

/**
 * 带有traceId的返回数据结果集合
 * @author zhangshihao01
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Result<T> extends BaseResponse<T> {
    private String traceId;

    public Result(String code, String message, String extraCode, String extraMessage, T data) {
        super(code, message, extraCode, extraMessage, data);
        this.setTraceId(MDC.get(LFHttp.TRACE_ID));
    }

    public Result(String code, String message, T data) {
        super(code, message, data);
        this.setTraceId(MDC.get(LFHttp.TRACE_ID));
    }

    public Result(T data) {
        super(data);
        this.setTraceId(MDC.get(LFHttp.TRACE_ID));
    }

    public Result(){
        super();
        this.setTraceId(MDC.get(LFHttp.TRACE_ID));
    }
}