package com.zljin.javase.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ReflectTest {

    public static void main(String[] args) throws Exception {

        Class<?> aClass = Class.forName("java.util.ArrayList");
        Constructor<?> constructor = aClass.getConstructor(int.class);
        Object list = constructor.newInstance(3);
        Method addMethod = aClass.getMethod("add", Object.class);

        addMethod.setAccessible(true);//暴力访问
        addMethod.invoke(list,"hello");
        addMethod.invoke(list,"world");

        System.out.println(list);
    }
}
