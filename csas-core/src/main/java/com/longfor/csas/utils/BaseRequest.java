package com.longfor.csas.utils;

import lombok.Data;
import lombok.ToString;

/**
 * @author zhangshihao01
 * @description 分页请求基类
 * @date 2021/12/03
 */
@Data
@ToString
public class BaseRequest{

    private int pageSize = 10;

    private int pageNum = 1;

    // 排序字段
    private String sort;
}
