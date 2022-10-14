package com.longfor.codegenerator.core;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description
 * @date 2022/10/14 18:12
 */
@Slf4j
public class CodeHttp {

    public static final String TRACE_ID = "X-B3-TraceId";
    public static final String SPAN_ID = "X-B3-SpanId";
    public static final String PARENT_SPAN_ID = "X-B3-ParentSpanId";
    public static final String REQUEST_ID = "X-LF-RequestId";

    private CodeHttp() {
    }

    public static String getBody(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        try (
                InputStream inputStream = request.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            log.debug("ServletRequest getInputStream error!", e);
        }
        return sb.toString();
    }
}
