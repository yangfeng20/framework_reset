package com.maple.myspring.core;

import com.maple.myspring.ApplicationContext;
import com.maple.myspring.util.XmlUtil;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Element;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author 杨锋
 * @date 2022/10/29 14:07
 * desc:
 */

@Slf4j
public class ClassPathXmlContext implements ApplicationContext {

    private Map<String, Object> singletonObjects = new HashMap<>();


    public ClassPathXmlContext(String path) {
        this(new String[]{path});
    }

    public ClassPathXmlContext(String... path) {
        if (path.length == 0) {
            throw new RuntimeException("参数异常,未指定配置文件");
        }
        InputStream resource = ClassLoader.getSystemClassLoader().getResourceAsStream(path[0]);
        if (resource == null) {
            throw new RuntimeException("无法找到配置文件：" + path[0]);
        }
        List<Element> elementList = null;
        try {
            elementList = XmlUtil.parse(resource);
        } catch (Exception e) {
            throw new RuntimeException("解析xml文件出错");
        }

        // 初始化对象
        elementList.forEach(element -> {
            try {
                String beanName = element.attributeValue("id");
                String clazz = element.attributeValue("class");
                log.info("beanName: " + beanName + " class: " + clazz);

                Class<?> aClass = Class.forName(clazz);
                Constructor<?> defaultConstructor = aClass.getDeclaredConstructor();
                Object instance = defaultConstructor.newInstance();
                singletonObjects.put(beanName, instance);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // 属性赋值
        elementList.forEach(element -> {
            try {
                String beanName = element.attributeValue("id");
                String clazz = element.attributeValue("class");
                Class<?> aClass = Class.forName(clazz);
                // 获取并遍历当前bean待赋值的属性
                List<Element> properties = element.elements("property");
                properties.forEach(property -> {
                    try {
                        String propertyName = property.attributeValue("name");
                        // 获取待赋值bean的字段
                        Field field = aClass.getDeclaredField(propertyName);
                        // 获取方法
                        String setMethodName = "set" + propertyName.toUpperCase(Locale.ROOT).charAt(0) + propertyName.substring(1);
                        Method setMethod = aClass.getDeclaredMethod(setMethodName, field.getType());

                        // 获取value或者ref
                        String propertyValue = property.attributeValue("value");
                        String ref = property.attributeValue("ref");

                        if (propertyValue != null) {
                            // 普通属性赋值
                            String fieldSimpleName = field.getType().getSimpleName();
                            Object value = null;
                            switch (fieldSimpleName) {
                                case "String":
                                    value = propertyValue;
                                    break;
                                case "Integer":
                                    value = Integer.valueOf(propertyValue);
                                    break;
                                case "Byte":
                                    value = Byte.valueOf(propertyValue);
                                    break;
                                case "Long":
                                    value = Long.valueOf(propertyValue);
                                    break;
                                case "Short":
                                    value = Short.valueOf(propertyValue);
                                    break;
                                case "Double":
                                    value = Double.valueOf(propertyValue);
                                    break;
                                case "Float":
                                    value = Float.valueOf(propertyValue);
                                    break;

                                default:
                                    throw new RuntimeException("暂时不支持此类型：" + fieldSimpleName);
                            }
                            setMethod.invoke(singletonObjects.get(beanName), value);
                        } else if (ref != null) {
                            // 引用数据类型赋值
                            setMethod.invoke(singletonObjects.get(beanName), singletonObjects.get(ref));
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public Object getBean(String name) {
        return singletonObjects.get(name);
    }

    @Override
    public <T> T getBean(String name, Class<T> clazz){
        return (T) getBean(name);
    }
}
