package com.longfor.csas.config;

import com.longfor.csas.interceptor.MyRequestIdInterceptor;
import com.longfor.csas.interceptor.TraceIdInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangshihao01
 * @version 1.0
 * @Description feign客户端配置类
 * @Date 2020/12/4 16:40
 */
@Configuration
public class FeignConfig {

    @Bean
    public TraceIdInterceptor requestIdInterceptor() {
        return new TraceIdInterceptor();
    }

    @Bean
    public MyRequestIdInterceptor myRequestIdInterceptor() {
        return new MyRequestIdInterceptor();
    }
}
