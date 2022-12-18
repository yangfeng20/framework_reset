package com.maple.spring.spi.impl;

import com.maple.spring.spi.FormatInterface;

/**
 * @author 杨锋
 * @date 2022/12/18 16:20
 * desc:
 */

public class PrintFormat implements FormatInterface {
    @Override
    public void format(String format) {
        System.out.println(format);
    }
}
