package ${basePackage}.wrapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author ${author}
 * @description 防xss攻击HttpServletRequest包装类
 * @date ${date}
 */
@Slf4j
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private HttpServletRequest request;

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.request = request;
    }
    @Override
    public String getParameter(String name) {
        log.info("get parameter request {}",request);
        // 过滤getParameter参数 检查是否有特殊字符
        String value = super.getParameter(name);
        if (!StringUtils.isEmpty(value)) {
            // 将中文转换为字符编码格式，将特殊字符变为html源代码保存
            value = StringEscapeUtils.escapeHtml(value);
        }
        return value;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] parameterValues = super.getParameterValues(name);
        log.info("batch get parameter request {}",request);
        if (parameterValues == null) {
            //return new String[0] 如果此处按Sonnar建议，用这个写法，会导致swagger报错
            return new String[]{null};
        }

        for (int i = 0; i < parameterValues.length; i++) {
            String value = parameterValues[i];
            parameterValues[i] = StringEscapeUtils.escapeHtml(value);
        }
        return parameterValues;
    }



}
