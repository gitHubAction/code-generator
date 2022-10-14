package com.longfor.csas.service.impl;

import com.longfor.csas.dto.ShortUrlReq;
import com.longfor.csas.dto.ShortUrlResp;
import com.longfor.csas.feign.TinyUrlFeignClient;
import com.longfor.csas.service.TinyUrlService;
import com.longfor.csas.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description
 * @date 2021/11/30 13:00
 */
@Slf4j
@Service
public class TinyUrlServiceImpl implements TinyUrlService {

    @Resource
    private TinyUrlFeignClient tinyUrlFeignClient;

    @Value("${longfor.web.feign.clients.tiny-url-feign.apikey}")
    private String tinyUrlApiKey;

    @Value("${longfor.web.feign.clients.tiny-url-feign.tinyUrlkey}")
    private String tinyUrley;

    @Override
    public String createShortUrl(ShortUrlReq shortUrlReq) {
        Result<ShortUrlResp> resp = tinyUrlFeignClient.createShortUrl(shortUrlReq, tinyUrlApiKey, tinyUrley);
        if("200".equals(resp.getCode())){
            return resp.getData().getShortUrl();
        }
        return shortUrlReq.getLongUrl();
    }
}
