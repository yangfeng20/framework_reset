package com.maple.condition;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

/**
 * @author 杨锋
 * @date 2022/11/10 9:35
 * desc:
 */



@Component
@Conditional(CustomCondition.class)
public class Student {


}
