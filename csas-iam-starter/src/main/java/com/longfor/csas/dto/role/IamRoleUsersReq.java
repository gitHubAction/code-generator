package com.longfor.csas.dto.role;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description 查询用户是否有角色请求体
 * @date 2021/12/28 10:17
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IamRoleUsersReq implements Serializable {

    /**
     * 新旧组架 0旧 1新
     */
    private Integer newDeptFlag;

    /**
     * 角色Code集合，不能超过100个
     */
    private List<String> roleCodes;

    /**
     * 角色类型，0：全部，1：标准角色，2：自定义角色，不传默认查询全部
     */
    private Integer roleType;

    /**
     * 人员OA账号集合，不超过100个
     */
    private List<String> userNames;

}
