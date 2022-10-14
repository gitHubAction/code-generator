package com.longfor.csas.service;

import com.longfor.csas.dto.ShortUrlReq;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description
 * @Date 2021/11/30 12:59
 */
public interface TinyUrlService {

    String createShortUrl(ShortUrlReq shortUrlReq);
}
