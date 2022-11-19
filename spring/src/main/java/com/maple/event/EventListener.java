package com.maple.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author 杨锋
 * @date 2022/11/19 10:25
 * desc:
 */

@Component
public class EventListener implements ApplicationListener<ApplicationEvent>{
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println(event);
    }

}
