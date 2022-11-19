package com.maple.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 杨锋
 * @date 2022/11/19 10:43
 * desc:
 */

@Component
public class EventPublish {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;


    public void publisher(EventSource e){
        applicationEventPublisher.publishEvent(e);
    }
}
