package com.longfor.csas.dto.func;

import lombok.*;

import java.util.List;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description 功能相关请求参数
 * @date 2021/6/8 15:43
 */
@Setter
@Getter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IamAuthFuncReq extends IamAuthBaseReq {
    /**
     * 应用code集合  必填
     */
    private List<String> appCodes;

    /**
     * 组织编码 非必填
     */
    private String orgCode;

    /**
     * 功能菜单层级 非必填
     */
    private Integer level;

    /**
     * 所属功能菜单编码 非必填
     */
    private String funcCode;
}
