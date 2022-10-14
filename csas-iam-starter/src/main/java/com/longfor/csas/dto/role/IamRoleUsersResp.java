package com.longfor.csas.dto.role;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description
 * @date 2021/12/28 10:21
 */
@Data
@ToString
public class IamRoleUsersResp implements Serializable {

    /**
     * OA账号
     */
    private String userName;

    /**
     * 员工工号
     */
    private String employId;

    /**
     * 账号姓名
     */
    private String realName;

    /**
     * 账号类型
     */
    private String userType;

    /**
     * 账号状态 1在职 2离职
     */
    private Integer userStatus;

    /**
     * 角色code
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 标签化角色名称
     */
    private String roleLabelName;

    /**
     * 角色类型，1：标准角色，2：自定义角色
     */
    private String roleType;
}
