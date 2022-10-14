package ${basePackage}.core;

import com.github.pagehelper.PageInfo;
import ${basePackage}.utils.BaseRequest;

import java.io.Serializable;
import java.util.List;

/**
 * @author ${author}
 * @description 通用Service
 * @date ${date}
 */
public interface Service<T, DTO extends T, PK extends Serializable>{
    void insert(T paramT);

    T get(PK paramPK);

    int update(T paramT);

    void update(T paramT, PK paramPK) throws IllegalAccessException, NoSuchFieldException;

    void remove(PK paramPK);

    List<T> selectAll(T paramT, OrderBy orderBy);

    PageInfo<T> selectByPage(BaseRequest<T> request);
}