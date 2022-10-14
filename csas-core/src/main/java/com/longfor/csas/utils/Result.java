package com.longfor.csas.utils;

import com.longfor.csas.cons.ResultCodeEnum;
import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gaia.gfs.web.core.toolkit.LFHttp;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.slf4j.MDC;

/**
 * @author zhangshihao01
 * @description 统一返回结果集
 * @date 2021/12/03
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

    public Result(ResultCodeEnum codeEnum, T data) {
        super(codeEnum.getCode(), codeEnum.getMsg(), data);
        this.setTraceId(MDC.get(LFHttp.TRACE_ID));
    }

    public Result(ResultCodeEnum codeEnum, String extraCode, String extraMsg, T data) {
        super(codeEnum.getCode(), codeEnum.getMsg(), extraCode, extraMsg, data);
        this.setTraceId(MDC.get(LFHttp.TRACE_ID));
    }

    public Result(T data) {
        super(ResultCodeEnum.E10200.getCode(), ResultCodeEnum.E10200.getMsg(), data);
        this.setTraceId(MDC.get(LFHttp.TRACE_ID));
    }

    public Result(){
        super(ResultCodeEnum.E10200.getCode(), ResultCodeEnum.E10200.getMsg(), null);
        this.setTraceId(MDC.get(LFHttp.TRACE_ID));
    }
}