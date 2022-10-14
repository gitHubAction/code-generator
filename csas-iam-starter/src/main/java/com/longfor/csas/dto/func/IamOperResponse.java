package com.longfor.csas.dto.func;

import com.longfor.csas.dto.IamCode;
import lombok.*;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description
 * @date 2021/6/8 16:17
 */
@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IamOperResponse {
    /**
     * 响应码
     */
    private IamCode respCode;

    /**
     * true-有权限，false-无权限
     */
    private Boolean data;
}
