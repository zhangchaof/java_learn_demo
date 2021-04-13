package com.zhang.javase.introspection;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @ClassName Test
 * @Description: TODO
 * @Author clark
 * @Date 2021/2/19 11:27
 **/
public class Test {

    @SuppressWarnings("unchecked")
    public void reflect(String clazz, Map<String, Object> properties) throws Exception {
        //反射创建实例
        Class target = Class.forName(clazz);
        Object bean = target.newInstance();

        //初始化容器时，调用setter注入
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            String _setName = "set" + entry.getKey();
            if ("name".equalsIgnoreCase(entry.getKey())) {
                Method setMethod = target.getMethod(_setName, String.class);
                setMethod.invoke(bean, entry.getValue().toString());
            } else {
                Method setMethod = target.getMethod(_setName, int.class);
                setMethod.invoke(bean, Integer.parseInt(entry.getValue().toString()));
            }
        }

        // show
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            String _getName = "get" + entry.getKey();
            if ("name".equalsIgnoreCase(entry.getKey())) {
                Method setMethod = target.getMethod(_getName);
                System.out.println(setMethod.invoke(bean));
            } else {
                Method setMethod = target.getMethod(_getName);
                System.out.println(setMethod.invoke(bean));
            }
        }
    }

    public void introspector(String clazz, Map<String, Object> properties) throws Exception {
        //反射创建实例
        Class target = Class.forName(clazz);
        Object bean = target.newInstance();

        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());

        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();

        for (PropertyDescriptor pd : pds) {
            Method setMethod = pd.getWriteMethod();
            String fieldName = pd.getName();

            if ("name".equalsIgnoreCase(fieldName)) {
                setMethod.invoke(bean, properties.get(fieldName));
            } else if ("age".equalsIgnoreCase(fieldName)){
                setMethod.invoke(bean, properties.get(fieldName));
            }
        }


        // show
        for (PropertyDescriptor pd : pds) {
            System.out.println(pd.getReadMethod().invoke(bean));
        }
    }
}
