package com.maple.app;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.util.List;
import java.util.Scanner;

/**
 * @author yangfeng
 * @date : 2023/8/1 12:59
 * desc: 动态加载agent的app
 */

public class AgentLoaderApp {
    public static void main(String[] args) throws Exception {
        List<VirtualMachineDescriptor> virtualMachineDescriptorList = VirtualMachine.list();
        System.out.println("请输入attach的进程id：");
        String pid = new Scanner(System.in).next();
        for (VirtualMachineDescriptor virtualMachineDescriptor : virtualMachineDescriptorList) {
            if (virtualMachineDescriptor.id().equals(pid)) {
                VirtualMachine virtualMachine = VirtualMachine.attach(virtualMachineDescriptor);
                virtualMachine.loadAgent("D:\\Program Dev\\Java\\05-Dev\\framework_reset\\agent\\target\\agent-1.0-SNAPSHOT.jar");
                break;
            }
        }


        System.out.println("run start");
        Thread.sleep(10000000L);
        System.out.println("run end");
    }

}
