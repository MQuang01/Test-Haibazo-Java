package com.example.haibazotest.until;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ObjectMapper {

    public static <S, T> void mapNonNullFields(S source, T target) {
        if (source == null || target == null)
            throw new IllegalArgumentException("Source and target must not be null");

        Map<String, Method> targetSetters = getSetters(target);

        for (Method getter : source.getClass().getDeclaredMethods()) {
            if (isGetter(getter)) {
                String fieldName = getter.getName().substring(3); // Lấy tên field (bỏ 'get')
                Method setter = targetSetters.get(fieldName);

                try {
                    if (setter != null) {
                        Object value = getter.invoke(source);
                        if (value != null)
                            setter.invoke(target, convertValue(value, setter.getParameterTypes()[0]));
                    }
                } catch (Exception e) {
                    System.err.println("Error mapping field: " + fieldName + " - " + e.getMessage());
                }
            }
        }
    }

    private static <T> Map<String, Method> getSetters(T target) {
        Map<String, Method> setters = new HashMap<>();
        for (Method method : target.getClass().getDeclaredMethods()) {
            if (method.getName().startsWith("set") && method.getParameterCount() == 1) {
                setters.put(method.getName().substring(3), method); // Lưu setter theo tên field
            }
        }
        return setters;
    }

    private static boolean isGetter(Method method) {
        return method.getName().startsWith("get") && method.getParameterCount() == 0;
    }

    private static Object convertValue(Object value, Class<?> targetType) {
        if (targetType == int.class || targetType == Integer.class) return Integer.valueOf(value.toString());
        if (targetType == double.class || targetType == Double.class) return Double.valueOf(value.toString());
        return value;
    }
}