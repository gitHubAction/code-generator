package com.longfor.csas.dto.func;

import lombok.*;

import java.util.List;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description
 * @date 2021/6/8 16:05
 */
@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IamAuthDataAccesReq extends IamAuthBaseReq {
    /**
     * 组件code 必填
     */
    private String funcCode;

    /**
     * 操作编码 必填
     */
    private List<String> operCodes;

    /**
     * 组织类型集合  必填
     * 新系统 orgTypeCodes：["01","05", "21", "09"]
     * 旧系统 orgTypeCodes：["01","05", "09"]
     */
    private List<String> orgTypeCodes;

    /**
     * 航道类型：C1/C2/C3/C4，不传默认查询全部
     */
    private List<String> productGroupList;

    /**
     * 角色code集合，限制必须拥有某些角色的用户，最大不超过100
     */
    private List<String> roleCodes;
}
