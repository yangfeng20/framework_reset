package com.maple.note.stream;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

/**
 * @author 杨锋
 * @date 2023/1/5 14:17
 * desc: note stream.forEach是可以修改数据源的(和引用有关，只要不改变对象引用就可以，map，filter，都可以【可以使用peek来做，符合语义】)
 */

public class StreamUpdateSourceTest {

    public static void main(String[] args) {
        ArrayList<Source> list = Lists.newArrayList(new Source(1), new Source(1), new Source(1), new Source(1), new Source(1));
        list.forEach(item -> {
            if (item.getAge().equals(1)) {
                item.setAge(2);
            }
        });

    }




    @Data
    @AllArgsConstructor
    static class Source {

        private Integer age;
    }
}
