package com.longfor.csas.dto.role;

import lombok.*;

import java.io.Serializable;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description
 * @date 2021/12/28 10:10
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IamRoleReq implements Serializable {

    private String appCode;

    private Integer roleType;
}
