package com.maple.note.base;

import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.maple.note.base.entity.ContainerMapEntity;
import com.maple.note.base.entity.Entity;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author yangfeng
 * @date : 2023/8/18 17:30
 * desc:
 */

public class QueueTest {

    public static void main(String[] args) throws Exception {
        List<Entity> entityList = buildData();

        String text = "update `fullfill_main_container` set unload_time = '";
        String t1 = "' where id = ";

        for (Entity entity : entityList) {
            String dateTime = DateUtil.formatDateTime(entity.getUnloadTime());
            String result = text + dateTime + t1 + entity.getId() + ";";
            System.out.println(result);
        }

        List<Long> orderIdList = entityList.stream().map(Entity::getOrderId).collect(Collectors.toList());
        System.out.println(orderIdList);


        //Map<Long, List<Entity>> orderIdContainerMap = entityList.stream().collect(Collectors.groupingBy(Entity::getOrderId));
        //System.out.println("--------------------------------------------");
        //for (Map.Entry<Long, List<Entity>> entry : orderIdContainerMap.entrySet()) {
        //    post(entry);
        //    break;
        //}

        System.out.println("结束");


    }

    public static List<Entity> buildData() throws Exception {


        List<Entity> entityList = new ArrayList<>();
        InputStream bookStream = Files.newInputStream(Paths.get("C:\\Users\\maple\\Downloads\\汇总表格.xlsx"));
        InputStream bookStream1 = Files.newInputStream(Paths.get("C:\\Users\\maple\\Downloads\\箱id映射关系.xlsx"));

        List<ContainerMapEntity> mapEntityList = new ArrayList<>();
        ExcelReaderBuilder readerBuilder1 = EasyExcel.read(bookStream1, ContainerMapEntity.class, new AnalysisEventListener<ContainerMapEntity>() {
            @Override
            public void invoke(ContainerMapEntity data, AnalysisContext context) {
                mapEntityList.add(data);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {

            }
        });
        ExcelReaderSheetBuilder sheet1 = readerBuilder1.sheet();
        sheet1.doRead();

        ExcelReaderBuilder readerBuilder = EasyExcel.read(bookStream, Entity.class, new AnalysisEventListener<Entity>() {
            @Override
            public void invoke(Entity data, AnalysisContext context) {
                entityList.add(data);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {

            }
        });
        ExcelReaderSheetBuilder sheet = readerBuilder.sheet();
        sheet.doRead();


        System.out.println(entityList);
        System.out.println(mapEntityList);

        Map<String, Entity> entityMap = entityList.stream().collect(Collectors.toMap(item -> item.getOrderId().toString() + item.getContainerNo() + item.getUnloadTime(), Function.identity(), (x, y) -> {
            System.out.println("重复对象：" + x);
            return y;
        }));

        for (ContainerMapEntity containerMapEntity : mapEntityList) {
            String key = containerMapEntity.getOrderId().toString() + containerMapEntity.getContainerNo() + containerMapEntity.getUnloadTime();
            Entity entity = entityMap.get(key);
            if (entity == null) {
                continue;
            }
            entity.setId(containerMapEntity.getId());
        }

        for (Entity entity : entityList) {
            if (entity.getId() == null) {
                System.out.println("未找到id" + entity);
            }
        }

        System.out.println(entityList);


        return entityList;
    }


    public static void post(Map.Entry<Long, List<Entity>> entry) {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            // 创建POST请求对象
            HttpPost httpPost = new HttpPost("https://pr-gw-work.yqn.com/api/40061/endpoint/v5/container_cargo/save");

            // 添加请求头
            httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
            httpPost.addHeader("Cookie", "pr_track_id=0fcdb2c729b20f4cd7b3e9433002a4f0; lk=0efc062d-0bbf-40f4-ad3e-2931ae9c65e5; fg=079cff06fcd3388942b9f35424eacc76; fgFlag=1; sta_track_id=6a09833539f22715ea538699eb953841; track_id=532c710e06c791b4c0147da9c45158d4; qa_track_id=54fb2c5cb9f4c0101fc7d9d1f2ff11a8; qa2_track_id=0538245ad000da4003924d1d30f75379; lang=zh; sta_cid=487a653741d844c7987e88ad96c2b474; pr_eid=b829e230ee0c16115956c8f9531bb5b5; sta_eid=2263611e0463eb789e03546ea229e97f; eid=a6cbe0f5c32535ede4e48d4b68348a80; ac=%E4%B8%AD%E5%9B%BD|%E4%B8%8A%E6%B5%B7|%E4%B8%8A%E6%B5%B7|CN|310000|106.15.37.67; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%221865944%22%2C%22first_id%22%3A%22187f06f00e0454-0578bb19ea82364-7e57547f-1327104-187f06f00e2b26%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg3ZjA2ZjAwZTA0NTQtMDU3OGJiMTllYTgyMzY0LTdlNTc1NDdmLTEzMjcxMDQtMTg3ZjA2ZjAwZTJiMjYiLCIkaWRlbnRpdHlfbG9naW5faWQiOiIxODY1OTQ0In0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%221865944%22%7D%2C%22%24device_id%22%3A%22187f06f00e0454-0578bb19ea82364-7e57547f-1327104-187f06f00e2b26%22%7D; acw_tc=17e2b01d2c88ec21f655219ccfdc9b4fec38b3f449de08f6b1a066e0cf1f8b0e");
            httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36 Edg/116.0.1938.62");
            httpPost.addHeader("Origin", "https://pr-work.yqn.com");
            httpPost.addHeader("Referer", "https://pr-work.yqn.com/");


            List<JSONObject> containerParam = new ArrayList<>();

            for (Entity entity : entry.getValue()) {
                JSONObject container = new JSONObject();
                container.put("id", entity.getId());
                container.put("unloadTime", entity.getRealUnloadTime());
                containerParam.add(container);
            }


            JSONObject params = new JSONObject();
            JSONObject model = new JSONObject();
            model.put("orderId", entry.getKey());
            model.put("operateType", 2);

            JSONObject appendObj = new JSONObject();
            appendObj.put("properties", Lists.newArrayList("unloadTime"));
            appendObj.put("reqModel", containerParam);
            model.put("containerParams", appendObj);

            params.put("model", model);
            String paramsJson = params.toJSONString();
            System.out.println("正在执行：" + paramsJson);
            StringEntity entityStr = new StringEntity(paramsJson);
            httpPost.setEntity(entityStr);
            // 执行请求并获取响应
            CloseableHttpResponse response = httpClient.execute(httpPost);

            try {
                // 处理响应
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    String responseContent = EntityUtils.toString(responseEntity);
                    System.out.println("请求响应 " + entry.getKey() + ": " + responseContent);
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


