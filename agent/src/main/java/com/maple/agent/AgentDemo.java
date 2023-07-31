package com.maple.agent;

import java.lang.instrument.Instrumentation;

/**
 * @author yangfeng
 * @date : 2023/5/15 20:41
 * desc:
 */

public class AgentDemo {

    /**
     * 静态加载方式；-javaagent参数在在启动时manifest ：指定PerMainClass
     * @param args
     * @param instrumentation
     */
    public static void premain(String args, Instrumentation instrumentation) throws Exception {
        MyClassFileTransformer transformer = new MyClassFileTransformer(instrumentation);
        instrumentation.addTransformer(transformer, true);
        transformer.retransformClasses(instrumentation);
        System.out.println("-------------------permian------------------");
    }

    /**
     * 动态加载方式；在启动之后附加进去，manifest：指定AgentClass
     * @param args
     * @param instrumentation
     */
    public static void agentmain(String args, Instrumentation instrumentation) {

    }
}
