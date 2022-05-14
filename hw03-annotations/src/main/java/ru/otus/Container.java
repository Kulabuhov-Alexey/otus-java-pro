package ru.otus;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Container {
    private static Map<String, Integer> statistic;
    private static Object object;
    private static Method[] methods;
    private static Class<?> clazz;

    public static Map<String, Integer> getStatistic() {
        return statistic;
    }

    public static void initStatistic() {
        Map<String, Integer> testsInfo = new HashMap<>();
        testsInfo.put("Passed test", 0);
        testsInfo.put("Failed test", 0);
        statistic = testsInfo;
    }

    public static Object getObject() {
        return object;
    }

    public static void setObject(Object object) {
        Container.object = object;
    }

    public static Method[] getMethods() {
        return methods;
    }

    public static void setMethods(Method[] methods) {
        Container.methods = methods;
    }

    public static Class<?> getClazz() {
        return clazz;
    }

    public static void setClazz(Class<?> clazz) {
        Container.clazz = clazz;
    }

    public static void printStatistic() {
        System.out.println("--------Statistics:--------");
        System.out.println("Tests runs:" + (Container.getStatistic().get("Passed test") + Container.getStatistic().get("Failed test")));
        System.out.println("Tests failed:" + Container.getStatistic().get("Failed test"));
        System.out.println("Tests passed:" + Container.getStatistic().get("Passed test"));
        System.out.println("---------------------------");
    }
}
