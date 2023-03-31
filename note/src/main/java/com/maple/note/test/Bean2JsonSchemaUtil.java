package com.maple.note.test;

import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;

/**
 * 2022/08/23 15:32
 *
 * @author ziker
 */
public class Bean2JsonSchemaUtil {

    public static final HashMap<String, String> bean2Package = new HashMap<String, String>() {{
        put("TerminalDTO", "com.yqn.cpf.dto.TerminalDTO");
        put("TerminalVO", "com.yqn.cpf.dto.TerminalDTO");
        put("TerminalDockVO", "com.yqn.cpf.dto.TerminalDockDTO");
        put("TerminalDockDTO", "com.yqn.cpf.dto.TerminalDockDTO");
        put("GeneralSettingItemDTO", "com.yqn.cpf.dto.GeneralSettingItemDTO");
        put("GeneralSettingItemVO", "com.yqn.cpf.dto.GeneralSettingItemDTO");
        put("CarrierDTO", "com.yqn.cpf.dto.CarrierDTO");
        put("CarrierVO", "com.yqn.cpf.dto.CarrierDTO");
        put("UserInfoDTO", "com.yqn.cpf.dto.UserInfoDTO");
        put("UserInfoVO", "com.yqn.cpf.dto.UserInfoDTO");
        put("EmployeeVO", "com.yqn.cpf.dto.EmployeeDTO");
        put("EmployeeDTO", "com.yqn.cpf.dto.EmployeeDTO");
    }};

    public static final JSONConfig jsonConfig = JSONConfig.create().setOrder(true);

    public static JSONObject parse(Class<?> obj) {
        if (obj == null) {
            return getJSONObj();
        }
        JSONObject o = getJSONObj();
        o.set("type", "object");
        o.set("properties", getChildNode(obj, new HashSet<Class<?>>() {{
            add(obj);
        }}));
        return o;
    }


    private static JSONObject getChildNode(Class<?> clazz, Set<Class<?>> parentsNode) {
        JSONObject node = getJSONObj();
        ReflectionUtils.doWithFields(clazz, field -> {
            String key = field.getName();
            // 基础类型
            if (isBaseType(field.getType())) {
                node.set(key, getBaseDescription(field, field.getType()));
                return;
            }
            // 集合
            if (field.getType().equals(List.class)) {
                JSONObject arr = getJSONObj();
                arr.set("type", "array");
                // 描述
                Optional.of(field)
                        .map(f -> f.getAnnotation(ApiModelProperty.class))
                        .map(ApiModelProperty::value)
                        .ifPresent(description -> arr.set("description", description));
                // 真实类型
                Type genericType = field.getGenericType();
                if (genericType instanceof ParameterizedType) {
                    ParameterizedType pt = (ParameterizedType) genericType;
                    // 集合里面的类型
                    Class<?> actualType = (Class<?>) pt.getActualTypeArguments()[0];
                    // 集合为基本类型
                    if (isBaseType(actualType)) {
                        arr.set("items", getBaseDescription(field, actualType));
                    } else {
                        // 层级已经嵌套了,不继续解析,避免出现环
                        if (parentsNode.contains(actualType)) {
                            arr.set("items", getObjDescription(field, null));
                            node.set(key, arr);
                            return;
                        }
                        // 集合为引用类型
                        parentsNode.add(actualType);
                        arr.set("items", getObjDescription(field, actualType, getChildNode(actualType, parentsNode)));
                        parentsNode.remove(actualType);
                    }
                } else {
                    // 没有真实类型
                    arr.set("items", getObjDescription(field, null));
                }
                node.set(key, arr);
                return;
            }
            // 层级已经嵌套了,不继续解析,避免出现环
            if (parentsNode.contains(field.getType())) {
                node.set(key, getObjDescription(field, null));
                return;
            }
            // 引用类型
            parentsNode.add(field.getType());
            node.set(key, getObjDescription(field, getChildNode(field.getType(), parentsNode)));
            parentsNode.remove(field.getType());
        });
        return node;
    }


    private static JSONObject getObjDescription(Field field, JSONObject properties) {
        return getObjDescription(field, field.getType(), properties);
    }

    private static JSONObject getObjDescription(Field field, Class<?> clazz, JSONObject properties) {
        JSONObject o = getJSONObj();
        o.set("type", properties == null ? "any" : "object");
        o.set("properties", properties);
        Optional.ofNullable(clazz)
                .map(Class::getSimpleName)
                .map(s -> bean2Package.getOrDefault(s, null))
                .ifPresent(typeName -> o.set("javaType", typeName));
        Optional.ofNullable(field)
                .map(f -> f.getAnnotation(ApiModelProperty.class))
                .map(ApiModelProperty::value)
                .ifPresent(description -> o.set("description", description));
        return o;
    }


    private static JSONObject getBaseDescription(Field field, Class<?> clazz) {
        JSONObject o = getJSONObj();
        String type = clazz.getSimpleName().toLowerCase(Locale.ROOT);
        o.set("type", type.equals("bigdecimal") ? "number" : type);
        o.set("javaType", clazz.getTypeName());
        Optional.ofNullable(field)
                .map(f -> f.getAnnotation(ApiModelProperty.class))
                .map(ApiModelProperty::value)
                .ifPresent(description -> o.set("description", description));
        return o;
    }

    private static boolean isBaseType(Class<?> actualType) {
        return actualType.isPrimitive()
                || actualType.equals(String.class) || ClassUtils.isPrimitiveWrapper(actualType)
                || actualType.equals(Date.class) || actualType.equals(BigDecimal.class);
    }


    private static JSONObject getJSONObj() {
        return new JSONObject(jsonConfig);
    }

    public static void main(String[] args) {
//		JSONObject x = parse(FullfillShipSubmitPreAdviceReqVO.class);
//		System.out.println(x);
    }

    public static class Test {
        @ApiModelProperty("名称")
        String name;
        @ApiModelProperty("子数组")
        List<Test> child;
    }
}
