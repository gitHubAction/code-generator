package ${basePackage}.core;


import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import ${basePackage}.mapper.MyBatisBaseDao;
import ${basePackage}.utils.BaseRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author ${author}
 * @description 通用抽象Service
 * @date ${date}
 */
public abstract class AbstractService<T, DTO extends T, PK extends Serializable> implements Service<T, DTO, PK>{

    @Autowired
    protected MyBatisBaseDao<T,PK> mapper;
    private Class<T> modelClass;

    public AbstractService() {
        ParameterizedType pt = (ParameterizedType)getClass().getGenericSuperclass();
        this.modelClass = ((Class)pt.getActualTypeArguments()[0]);
    }

    public void insert(T entity) {
        this.mapper.insertSelective(entity);
    }

    public T get(PK id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    public void remove(PK id) {
        this.mapper.deleteByPrimaryKey(id);
    }

    public int update(T model) {
        return this.mapper.updateByPrimaryKeySelective(model);
    }

    public void update(T model, PK id) throws IllegalAccessException, NoSuchFieldException {
        Field field = this.modelClass.getDeclaredField("id");
        field.setAccessible(true);
        field.set(model, id);
        this.mapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public List<T> selectAll(T paramT, OrderBy orderBy) {
        if(orderBy != null && orderBy.toString() != ""){
            PageMethod.orderBy(orderBy.toString());
        }
        return this.mapper.selectAll(paramT);
    }

    @Override
    public PageInfo<T> selectByPage(BaseRequest<T> request) {
        PageMethod.startPage(request.getPageNum(), request.getPageSize());
        OrderBy orderBy = new OrderBy();
        if(StringUtils.isNotEmpty(request.getSidx())){
            orderBy.add(request.getSidx(), request.getSort());
        }
        List<T> page = this.selectAll(request.getQuery(), orderBy);
        return new PageInfo<>(page);
    }
}