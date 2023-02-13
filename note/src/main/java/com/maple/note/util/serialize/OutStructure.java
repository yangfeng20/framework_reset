package com.maple.note.util.serialize;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * @author yangfeng
 * @date : 2023/2/7 17:34
 * desc:
 */

public class OutStructure {

    public static void main(String[] args) {

    }

    public static void output(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isSynthetic() || !field.isAnnotationPresent(ApiModelProperty.class)) {
                continue;
            }

            ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);


        }
    }

    private static DescStruct buildDesc(Field field, ApiModelProperty propertyDesc) {
        DescStruct descStruct = new DescStruct();

        Class<?> fieldType = field.getType();
        String type = "";
        if (fieldType.isAssignableFrom(Integer.class)) {
            type = "integer";
        } else if (fieldType.isAssignableFrom(Long.class)) {
            type = "long";
        } else if (fieldType.isAssignableFrom(Number.class)) {
            type = "number";
        } else if (fieldType.isAssignableFrom(String.class)) {
            type = "string";
        } else if (fieldType.isAssignableFrom(Boolean.class)) {
            type = "boolean";
        } else if (fieldType.isAssignableFrom(Collection.class)) {
            type = "array";
        } else {
            //buildDesc()
            type = "object";
        }
        descStruct.setType(type);
        return descStruct;
    }


    @Data
    static class DescStruct {

        private String type;

        private String title;

        private String description;

    }
}
