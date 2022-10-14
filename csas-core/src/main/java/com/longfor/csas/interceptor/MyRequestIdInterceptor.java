package com.longfor.csas.interceptor;

import com.longfor.gaia.gfs.web.core.toolkit.LFHttp;
import com.longfor.gaia.gfs.web.feign.interceptor.RequestIdInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.Collection;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description
 * @date 2020/12/4 17:40
 */
@Slf4j
public class MyRequestIdInterceptor extends RequestIdInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        Collection<String> headers = template.headers().get(LFHttp.REQUEST_ID);
        if (headers != null) {
            MDC.put("requestId",headers.iterator().next());
        }
    }
}
