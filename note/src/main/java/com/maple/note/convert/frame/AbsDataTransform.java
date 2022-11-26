package com.maple.note.convert.frame;

import cn.hutool.core.bean.BeanUtil;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author 杨锋
 * @date 2022/9/23 10:24
 * desc:
 */

public abstract class AbsDataTransform<R, S> implements DataTransform<R, S> {

    private static final Executor THREAD_POOL = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors() * 2 + 1,
            Runtime.getRuntime().availableProcessors() * 2 + 1,
            8,
            TimeUnit.HOURS,
            new ArrayBlockingQueue<>(100),
            Thread::new,
            new ThreadPoolExecutor.CallerRunsPolicy());


    @Override
    public R transform(S source) {
        List<R> resultList = transformList(Lists.newArrayList(source));
        if (CollectionUtils.isEmpty(resultList)) {
            return null;
        }
        return resultList.get(0);
    }

    @Override
    public List<R> transformList(List<S> sourceList) {
        if (CollectionUtils.isEmpty(sourceList) || sourceList.get(0) == null) {
            return Collections.emptyList();
        }

        // 执行前处理
        executeBefore(sourceList);
        List<R> resultList = sourceList.stream()
                .filter(Objects::nonNull)
                .map(source -> CompletableFuture.supplyAsync(() -> this.transformBase(source), THREAD_POOL))
                .collect(Collectors.toList())
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        // 执行后处理
        executeAfter();
        return resultList;
    }

    protected R transformBase(S source) {
        @SuppressWarnings("unchecked")
        R copyResult = (R) BeanUtil.copyProperties(source, getResultClass());
        executeIng(source, copyResult);
        return copyResult;
    }


    /**
     * 在执行前调用，用于收集数据调用服务获取数据源
     *
     * @param sourceList 源对象list
     */
    public abstract void executeBefore(List<S> sourceList);

    /**
     * 在执行转换中调用，处理特殊的赋值逻辑（普通字段已经copy，清楚重新赋值）
     *
     * @param source source
     * @param result result
     */
    public abstract void executeIng(S source, R result);


    /**
     * 在转换结束之后调用，可以用于清理
     */
    public abstract void executeAfter();


}
