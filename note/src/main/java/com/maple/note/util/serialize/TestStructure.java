package com.maple.note.util.serialize;

import cn.hutool.core.util.ReflectUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModelProperty;

import java.lang.reflect.Field;

/**
 * @author yangfeng
 * @date : 2023/2/15 11:03
 * desc:
 */

public class TestStructure {
    public static void main(String[] args) {
        generateModel("realShipper", "真实发货人");
        generateModel("realConsignee", "真实收货人");
        generateModel("realNotify", "真实通知人");
        generateModel("realSecondNotify", "真实第二通知人");

        System.out.println("---------------------------------");

        generateMap("realShipper");
        generateMap("realConsignee");
        generateMap("realNotify");
        generateMap("realSecondNotify");
    }


    public static void generateMap(String pre) {

        String extendKey = "$.extendMap.";

        Field[] fields = ReflectUtil.getFields(ShippingContractDTO.class);
        JSONObject jsonObject = new JSONObject();

        for (Field field : fields) {
            String name = field.getName();
            String realName = pre + (name.substring(0, 1).toUpperCase() + name.substring(1));
            
            String value = pre + "." + name;
            jsonObject.put(extendKey + realName, value);
        }

        System.out.println(jsonObject.toJSONString());
    }

    public static void generateModel(String pre, String descPre) {

        Field[] fields = ReflectUtil.getFields(ShippingContractDTO.class);
        JSONObject jsonObject = new JSONObject();

        for (Field field : fields) {
            field.setAccessible(Boolean.TRUE);
            ApiModelProperty annotation = field.getAnnotation(ApiModelProperty.class);
            String name = field.getName();
            String desc = descPre + annotation.value();
            String realName = pre + (name.substring(0, 1).toUpperCase() + name.substring(1));

            JSONObject value = new JSONObject();
            value.put("type", "string");
            value.put("title", desc);
            value.put("description", desc);
            jsonObject.put(realName, value);
        }

        System.out.println(jsonObject.toJSONString());
    }
}
