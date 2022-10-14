package com.longfor.csas.feign;

import com.longfor.csas.config.FeignConfig;
import com.longfor.csas.config.TinyUrlConfiguration;
import com.longfor.csas.dto.ShortUrlReq;
import com.longfor.csas.dto.ShortUrlResp;
import com.longfor.csas.feign.fallback.TinyUrlFeignClientFallBack;
import com.longfor.csas.utils.Result;
import com.longfor.gaia.gfs.web.feign.LFFeignClient;
import com.longfor.gaia.gfs.web.feign.config.LFFeignConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description
 * @Date 2021/6/8 15:24
 */
@LFFeignClient(name = TinyUrlConfiguration.TINY_URL_FEIGN_NAME,configuration = {FeignConfig.class, LFFeignConfiguration.class},fallback = TinyUrlFeignClientFallBack.class)
public interface TinyUrlFeignClient {

    @PostMapping(value = "/api/shorturl/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<ShortUrlResp> createShortUrl(@RequestBody ShortUrlReq shortUrlReq,
                                        @RequestHeader(value = "X-Gaia-Api-Key") String apiKey,
                                        @RequestHeader(value = "X-Gaia-Tinyurl-Key") String tinyUrlKey);


}
