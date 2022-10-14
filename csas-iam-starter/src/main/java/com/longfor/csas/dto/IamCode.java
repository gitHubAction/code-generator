package com.longfor.csas.dto;

import lombok.*;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description
 * @date 2021/6/8 15:17
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class IamCode {
    /**
     * 编码
     */
    private String code;

    /**
     * 消息
     */
    private String message;

    /**
     * 结果
     */
    private Boolean data;
}
