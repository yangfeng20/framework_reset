package com.maple.note.convert.frame;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author 杨锋
 * @date 2022/9/22 16:12
 * desc:
 */


@SuppressWarnings("unused")
public interface DataTransform<R, S> {

    /**
     * 转换数据
     *
     * @param source 数据源
     * @return 返回对象
     */
    R transform(S source);


    /**
     * 转换集合
     *
     * @param sourceList 数据源list
     * @return 返回结果list
     */
    List<R> transformList(List<S> sourceList);


    /**
     * 获取当前实现类的数据源Class
     *
     * @return class
     */
    default Class<?> getSourceClass() {
        return (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    /**
     * 获取当前实现类的结果Class
     *
     * @return class
     */
    default Class<?> getResultClass() {
        return (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

}
