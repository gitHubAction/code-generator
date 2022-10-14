package ${basePackage}.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author ${author}
 * @description 分页请求基类
 * @date ${date}
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class BaseRequest<T> implements Serializable {
    private int pageSize = 10;

    private int pageNum = 1;

    // 排序字段
    private String sidx;

    // 排序方式 0/1  降序/升序
    private String sort;

    // 查询对象
    private T query;

    private static final long serialVersionUID = -1L;
}
