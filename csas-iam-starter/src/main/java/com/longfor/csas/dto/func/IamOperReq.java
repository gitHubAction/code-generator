package com.longfor.csas.dto.func;

import lombok.*;

import java.util.List;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description
 * @date 2021/6/8 16:18
 */
@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IamOperReq {
    /**
     * 用户名 必填
     */
    private String userName;

    //----------------判断用户是否有组件操作权限-----------
    /**
     * 组件编码 必填
     */
    private String compCode;

    /**
     * 操作编码
     */
    private String operCode;


    //----------------账号+角色判断用户是否有该角色-----------
    /**
     * 维度编码 必填
     */
    private String bizCode;

    /**
     * 角色编码
     */
    private String roleCode;


    /**
     * 标签类型编码集合
     */
    private List<String> tagTypeCodes;
}
