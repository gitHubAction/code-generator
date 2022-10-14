package com.longfor.csas.dto;

import lombok.*;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description
 * @date 2021/11/30 18:10
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortUrlResp {

    /**
     * 通过短链服务为原长链生成的短链
     */
    private String shortUrl;

    /**
     * 保留字段 为用户的系统对应的编号 用于关联paas短链信息
     */
    private String bizSeqNo;

    /**
     * 短链信息分表存储存放对应的数据库表编号
     */
    private String tableNo;
}
