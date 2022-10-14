package com.longfor.csas.dto.role;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description
 * @date 2021/12/28 10:06
 */
@Data
@ToString
public class IamRole implements Serializable {

    /**
     * 角色code
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色标签化名称
     */
    private String labelName;

    /**
     * 角色类型
     */
    private String type;
}
