package com.longfor.csas.dto.func;

import lombok.*;

import java.util.List;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description IAM 功能列表
 * @date 2021/6/8 15:20
 */
@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IamFunction {
    /**
     * 页面菜单编码code
     */
    private String code;
    /**
     * 页面菜单-操作权限
     */
    private List<String> operations;
    /**
     * 页面菜单名称
     */
    private String name;
}
