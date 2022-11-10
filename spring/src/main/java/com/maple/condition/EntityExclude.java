package com.maple.condition;

import org.springframework.context.annotation.Conditional;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author 杨锋
 * @date 2022/11/10 10:01
 * desc:
 */

@Order
@Component
@ExcludeComponents(Student.class)
@Conditional(CustomCondition.class)
public class EntityExclude {
}
