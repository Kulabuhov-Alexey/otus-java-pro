package ru.otus;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        for (String arg : args) {
            try {
                setContainer(arg);
                doBefore();
                doTest();
            } catch (RuntimeException ignore) {
            } finally {
                doAfter();
                Container.printStatistic();
            }
        }
    }

    private static void setContainer(String arg) throws ClassNotFoundException {
        Container.initStatistic();
        Container.setClazz(Class.forName(arg));
        Container.setObject(instantiate(Container.getClazz()));
        Container.setMethods(Container.getClazz().getDeclaredMethods());
    }

    private static void doBefore() {
        try {
            getMethods(Container.getMethods(), "ru.otus.annotations.Before").forEach(method -> callMethod(Container.getObject(), method.getName()));

        } catch (RuntimeException re) {
            System.out.println("Failed before method");
            System.exit(0);
        }
    }

    private static void doAfter() {
        getMethods(Container.getMethods(), "ru.otus.annotations.After").forEach(method -> callMethod(Container.getObject(), method.getName()));
    }

    private static void doTest() {
        for (Method method : getMethods(Container.getMethods(), "ru.otus.annotations.Test")) {
            try {
                callMethod(Container.getObject(), method.getName());
                Container.getStatistic().replace("Passed test", Container.getStatistic().get("Passed test") + 1);
            } catch (RuntimeException re) {
                Container.getStatistic().replace("Failed test", Container.getStatistic().get("Failed test") + 1);
            }
        }
    }

    private static List<Method> getMethods(Method[] methods, String annotationName) {
        return Arrays.stream(methods).filter(method -> Arrays.stream(method.getDeclaredAnnotations()).anyMatch(annotation -> annotation.annotationType().getName().equals(annotationName))).collect(Collectors.toList());
    }

    public static <T> T instantiate(Class<T> type, Object... args) {
        try {
            if (args.length == 0) {
                return type.getDeclaredConstructor().newInstance();
            } else {
                Class<?>[] classes = toClasses(args);
                return type.getDeclaredConstructor(classes).newInstance(args);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Class<?>[] toClasses(Object[] args) {
        return Arrays.stream(args).map(Object::getClass).toArray(Class<?>[]::new);
    }

    public static Object callMethod(Object object, String name, Object... args) {
        try {
            var method = object.getClass().getDeclaredMethod(name, toClasses(args));
            method.setAccessible(true);
            return method.invoke(object, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
