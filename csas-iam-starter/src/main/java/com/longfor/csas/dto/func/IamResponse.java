package com.longfor.csas.dto.func;

import com.longfor.csas.dto.IamCode;
import lombok.*;

import java.util.List;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description iam-auth feign 接口返回结果
 * @date 2021/6/8 15:17
 */
@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IamResponse {
    /**
     * 响应码
     */
    private IamCode respCode;

    /**
     * 响应数据
     */
    private List<IamResponseData> data;
}
