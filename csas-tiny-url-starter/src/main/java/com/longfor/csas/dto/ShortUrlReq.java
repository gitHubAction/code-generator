package com.longfor.csas.dto;

import lombok.*;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description
 * @date 2021/11/30 18:07
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortUrlReq {
    /**
     * 原始链接，必须带上协议protocol https 或 http 如 https://news.sina.com 有效, www.sina.com.cn 无效
     */
    private String longUrl;

    /**
     * 有效期，单位秒，为空或者0,-1表示永久有效，最大90天, 可以多次修改某一链接的过期时间
     */
    private Integer expireTime;

    /**
     * 龙湖OA账号, huangxiaoming
     */
    private String modifierAccount;

    /**
     * 更多修改人联系信息,逗号分隔 如: 13683680908, huangxiaoming@163.com
     */
    private String more;

    /**
     * 短链用途，1:测试目的，2:生产目的
     */
    private Integer purpose;

}
