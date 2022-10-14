package ${basePackage}.config;

import ${basePackage}.filter.ServiceFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author ${author}
 * @description SpringMVC配置
 * @date ${date}
 */
@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    // 当前激活的配置文件
    @Value("${springProfilesActive}")
    private String env;

    /**
     * 这个Filter 解决页面跨域访问问题
     */
    @Bean
    public FilterRegistrationBean omsFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ServiceFilter());
        registration.addUrlPatterns("/*");
        registration.setName("MainFilter");
        registration.setAsyncSupported(true);
        registration.setOrder(1);
        return registration;
    }

}