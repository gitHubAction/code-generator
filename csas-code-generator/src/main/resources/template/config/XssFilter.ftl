package ${basePackage}.filter;

import ${basePackage}.wrapper.XssHttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author ${author}
 * @description 防Xss攻击过滤器
 * @date ${date}
 */
@Slf4j
@WebFilter(filterName = "xssFilter", urlPatterns = "/*")
public class XssFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Xss filter initialize ");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 1. 使用过滤器拦截所有参数
        HttpServletRequest req = (HttpServletRequest) request;
        // 2.重写getParameter方法
        XssHttpServletRequestWrapper xssHttpServletRequestWrapper = new XssHttpServletRequestWrapper(req);
        // 放行程序，继续往下执行
        chain.doFilter(xssHttpServletRequestWrapper, response);
    }

    @Override
    public void destroy() {
        log.info("Xss filter destroy ");
    }
}
