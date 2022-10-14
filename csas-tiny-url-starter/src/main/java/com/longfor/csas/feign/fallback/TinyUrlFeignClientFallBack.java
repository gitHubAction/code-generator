package com.longfor.csas.feign.fallback;

import com.longfor.csas.dto.ShortUrlReq;
import com.longfor.csas.dto.ShortUrlResp;
import com.longfor.csas.feign.TinyUrlFeignClient;
import com.longfor.csas.utils.ResponseMsgUtil;
import com.longfor.csas.utils.Result;
import com.longfor.gaia.gfs.web.core.toolkit.LFHttp;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description
 * @date 2021/6/8 15:26
 */
@Slf4j
@Component
public class TinyUrlFeignClientFallBack implements TinyUrlFeignClient {

    @Override
    public Result<ShortUrlResp> createShortUrl(ShortUrlReq shortUrlReq, String apiKey, String tinyUrlKey) {
        log.warn("集团短链平台熔断降级 req:{}, apiKey:{}, tinyUrlKey:{}, traceId:{}", shortUrlReq, apiKey, tinyUrlKey, MDC.get(LFHttp.TRACE_ID));
        ShortUrlResp build = ShortUrlResp.builder().shortUrl(shortUrlReq.getLongUrl()).build();
        return ResponseMsgUtil.success(build);
    }
}
