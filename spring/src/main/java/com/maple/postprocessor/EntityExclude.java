package com.maple.postprocessor;

import org.springframework.stereotype.Component;

/**
 * @author 杨锋
 * @date 2022/11/10 10:01
 * desc:
 */


@Component
@ExcludeComponents(Student.class)
public class EntityExclude {
}
