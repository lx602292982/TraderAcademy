package com.traderacademy.http.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eric on 2016/1/23.
 */
public class RequestParamUtils {

    /**
     * 将类中的属性添加进网络请求参数中
     * @param model  需要传输的对象
     * @param isAddSuper  是否添加父类参数
     * @return
     */
    public static Map<String, String> loadRequestParam(Object model, boolean isAddSuper) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Map<String, String> params = new HashMap<String, String>();
        Field[] fields = model.getClass().getDeclaredFields();
        getFieldValue(fields, model, params);
        if (isAddSuper) {
            Field[] sFields = model.getClass().getSuperclass().getDeclaredFields();
            getFieldValue(sFields, model, params);
        }
        return params;
    }

    public static void getFieldValue(Field[] field, Object model, Map params) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        for (int j = 0; j < field.length; j++) { // 遍历所有属性
            String name = field[j].getName(); // 获取属性的名字

            String mName = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
            System.out.println("attribute name:" + name + "|Method name:get" + mName);
            String type = field[j].getGenericType().toString(); // 获取属性的类型
            if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class
                // "，后面跟类名
                Method m = model.getClass().getMethod("get" + mName);
                String value = (String) m.invoke(model); // 调用getter方法获取属性值
                if (value != null) {
                    addTuParams(params, name, value);
                    System.out.println("attribute value:" + value);
                }
            }
            if (type.equals("class java.lang.Integer")) {
                Method m = model.getClass().getMethod("get" + mName);
                Integer value = (Integer) m.invoke(model);
                if (value != null) {
                    addTuParams(params, name, value);
                    System.out.println("attribute value:" + value);
                }
            }
            if (type.equals("class java.lang.Short")) {
                Method m = model.getClass().getMethod("get" + mName);
                Short value = (Short) m.invoke(model);
                if (value != null) {
                    addTuParams(params, name, value);
                    System.out.println("attribute value:" + value);
                }
            }
            if (type.equals("class java.lang.Float")) {
                Method m = model.getClass().getMethod("get" + mName);
                Float value = (Float) m.invoke(model);
                if (value != null) {
                    addTuParams(params, name, value);
                    System.out.println("attribute value:" + value);
                }
            }
            if (type.equals("class java.lang.Double")) {
                Method m = model.getClass().getMethod("get" + mName);
                Double value = (Double) m.invoke(model);
                if (value != null) {
                    addTuParams(params, name, value);
                    System.out.println("attribute value:" + value);
                }
            }
            if (type.equals("class java.lang.Boolean")) {
                Method m = model.getClass().getMethod("get" + mName);
                Boolean value = (Boolean) m.invoke(model);
                if (value != null) {
                    addTuParams(params, name, value);
                    System.out.println("attribute value:" + value);
                }
            }
            if (type.equals("class java.util.Date")) {
                Method m = model.getClass().getMethod("get" + mName);
                Date value = (Date) m.invoke(model);
                if (value != null) {
                    addTuParams(params, name, value);
                    System.out.println("attribute value:" + value.toLocaleString());
                }
            }
        }
    }

    public static void addTuParams(Map<String, String> params, String name, Object value) {
        if (!StringUtils.isBlank(String.valueOf(value.toString())))
            params.put(name, String.valueOf(value.toString()));
    }
}
