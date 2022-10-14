package ${basePackage}.filter;

import com.longfor.gaia.gfs.web.core.toolkit.LFHttp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

/**
* @author ${author}
* @description 请求追踪traceId
* @date ${date}
*/
@Slf4j
@Order(-1)
@Component
@WebFilter(urlPatterns = "/*", filterName = "traceIdFilter")
public class TraceIdFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            insertMDC((HttpServletRequest)request);
            filterChain.doFilter(request, response);
        }finally {
            MDC.remove(LFHttp.TRACE_ID);
        }
    }

    private boolean insertMDC(HttpServletRequest request) {
        String traceId = request.getHeader(LFHttp.TRACE_ID);
        if(StringUtils.isEmpty(traceId)){
            traceId = UUID.randomUUID().toString();
        }
        log.debug("traceId:{}",traceId);
        MDC.put(LFHttp.TRACE_ID,traceId);
        return true;

    }
}