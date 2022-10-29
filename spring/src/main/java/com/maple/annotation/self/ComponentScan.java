package com.maple.annotation.self;

import java.io.File;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 杨锋
 * @date 2022/10/29 15:44
 * desc:
 */

public class ComponentScan {

    private Map<String, Object> objectMap = new HashMap<>();


    public ComponentScan(String path){
        init(path);
    }

    public ComponentScan(){
        this(null);
    }

    public void init(String path){
        if (path == null){
            StringBuilder sb = new StringBuilder();
            String[] classPathArr = getClass().getName().split("\\.");
            for (int i = 0; i < classPathArr.length; i++) {
                if (i == classPathArr.length-1){
                    break;
                }
                sb.append(classPathArr[i]+"/");
            }
            //sb.setLength(sb.length()-1);
            path = sb.toString();
        }
        // 转换为绝对路径
        path = ClassLoader.getSystemResource(path).getPath();

        try {
            path = URLDecoder.decode(path, "utf-8");
            File file = new File(path);
            File[] files = file.listFiles();
            assert files != null;
            System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());
            for (File file1 : files) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
