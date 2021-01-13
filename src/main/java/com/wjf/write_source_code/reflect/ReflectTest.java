package com.wjf.write_source_code.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import com.wjf.write_source_code.entity.Person;

/**
 * @ClassName ReflectTest
 * @Description todo
 * @Author wjf
 * @Date 2020/11/25 19:13
 * @Version V1.0
 **/
public class ReflectTest {

    /**
     * 以工具类 中将对象转换为Map的方法为例来说明
     * 通过传入的对象参数obj
     * 利用反射机制 动态获取该对象的所有属性和属性的值，并将属性名称和属性值封装成Map对象
     * @param obj
     * @return
     */
    private static Map<String,Object> convertObjToMap(Object obj){
        if(obj==null){
            return null;
        }
        Map<String,Object> map=new HashMap<>();
        try {
            Field[] fields=obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                //将成员变量设置为可获得的 当不设置的话 该属性为private时获取
                field.setAccessible(true);
                map.put(field.getName(),field.get(obj));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 直接通过get方式获取的field和method 包括所有自身和继承的public的方法
     *
     * 通过getDeclared方式获取的field和method包括自身的所有（private、public、protected）方法（不包括继承）
     * @param obj
     */
    private static void eat(Object obj){
        try {
            Method method=obj.getClass().getDeclaredMethod("eat",String.class);
            //在调用私有方法之前，需设置该方法为可访问的权限：——否则会报错
            method.setAccessible(true);
            Object resp=method.invoke(obj,"Lisi");
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Person person=new Person(18,"zhangSan",1.73d);
        System.out.println(convertObjToMap(person));
        eat(person);
    }
}
