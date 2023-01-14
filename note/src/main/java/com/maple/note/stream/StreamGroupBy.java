package com.maple.note.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yangfeng
 * @date : 2023/1/14 11:18
 * desc: 认真查看本案例就能明白
 * @see <a href='https://blog.csdn.net/u013314860/article/details/121926815?ops_request_misc=&request_id=&biz_id=102&utm_term=stream%20group%20by&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduweb~default-0-121926815.142^v71^one_line,201^v4^add_ask&spm=1018.2226.3001.4187'>
 */

public class StreamGroupBy {
    public static void main(String[] args) {
        Map<Integer, List<Integer>> mapList = Stream.of(1, 2, 4).collect(Collectors.groupingBy(Function.identity()));
        System.out.println(mapList);


        Map<Integer, Integer> map2 = Stream.of(1, 2, 3, 4)
                // note 【Collectors.summingInt】这里是对相同组的元素进行什么操作
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(item -> item)));
        System.out.println(map2);
    }
}
