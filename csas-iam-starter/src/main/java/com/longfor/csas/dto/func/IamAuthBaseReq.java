package com.longfor.csas.dto.func;

import lombok.*;
import org.checkerframework.checker.units.qual.A;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description IAM-AUTH 权限请求基类(属性都为必填)
 * @date 2021/6/8 15:42
 */
@Setter
@Getter
@ToString
public class IamAuthBaseReq {
    /**
     * 0：旧组架；1：新组架
     */
    private Integer newDeptFlag;

    /**
     * 用户账号
     */
    private String uesrName;
}
