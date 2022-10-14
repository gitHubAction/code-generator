package com.longfor.csas.dto.tag;

import com.longfor.csas.dto.IamCode;
import lombok.*;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description
 * @date 2021/6/9 17:34
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class IamTagResponse<T> {
    private IamCode respCode;

    private T data;
}
