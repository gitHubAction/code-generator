package com.longfor.csas.interceptor;

import com.longfor.gaia.gfs.web.core.toolkit.LFHttp;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description  feign客户端请求头中添加traceID拦截器
 * @date 2020/12/4 16:42
 */
@Slf4j
public class TraceIdInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String traceId = MDC.get(LFHttp.TRACE_ID);
        log.debug("traceId:{}",traceId);
        requestTemplate.header(LFHttp.TRACE_ID,traceId);
    }
}
