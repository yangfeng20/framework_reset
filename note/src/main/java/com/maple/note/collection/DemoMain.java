package com.maple.note.collection;

/**
 * @author 杨锋
 * @date 2022/10/30 17:36
 * desc:
 */

public class DemoMain {
    public static void main(String[] args) throws Exception{

        DemoMain demoMain = new DemoMain();
        synchronized (demoMain){
            demoMain.wait();
        }
    }

}
