package com.maple.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author 杨锋
 * @date 2022/11/19 10:21
 * desc:
 */

public class EventSource extends ApplicationEvent {

    public EventSource(Object source) {
        super(source);
    }
}
