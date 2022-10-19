package com.maple.note.format;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author 杨锋
 * @date 2022/10/18 15:59
 * desc:
 */

public class CopyTest {
    public static void main(String[] args) {
        String str = "{\"actualDeliveryDate\":\"null\",\"isSyncVoyage\":\"null\",\"closingCustomDateType\":\"2\",\"fromDock\":\"null\",\"voyageId\":\"null\",\"viaTerminalCodeState\":\"null\",\"closeCargoDate\":\"null\"}";
        Map map = JSONObject.parseObject(str, Map.class);
        System.out.println(map);
        System.out.println(JSON.toJSONString(map));
    }
}
