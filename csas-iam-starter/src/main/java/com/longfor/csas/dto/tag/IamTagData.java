package com.longfor.csas.dto.tag;

import lombok.*;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description
 * @date 2021/6/9 17:36
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class IamTagData {
    /**
     * id
     */
    private String id;

    /**
     * 数据标签名称或角色名称
     */
    private String name;

    /**
     * 数据标签类型（1：树型，2：列表型）
     */
    private Integer type;

    /**
     * 标签类型code
     */
    private String typeCode;

    /**
     * 标签所在维度code
     */
    private String bizCode;

    /**
     * 父标签id
     */
    private String parentId;

    /**
     * 扩展字段
     */
    private String extend;

    /**
     * 标签code或角色code
     */
    private String code;

    /**
     * 是否包含下级，true，false
     */
    private boolean isRecursive;

    /**
     * 数据标签状态 0-未启用；1-生效；2-禁用
     */
    private Integer status;

    /**
     * 标签类别code
     */
    private String tagTypeCode;

    /**
     * 父标签code，如果type=1，则必传；
     *  如果需要在根节点挂树结构标签，则parentTagCode传空即可;新增时必填，修改无须写
     */
    private String parentTagCode;

    /**
     * 标签描述
     */
    private String description;
}
