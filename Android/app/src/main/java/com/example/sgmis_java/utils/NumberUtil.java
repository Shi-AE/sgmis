package com.example.sgmis_java.utils;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class NumberUtil {
    private NumberUtil() {
    }

    /**
     * 解决sql查询结果中可能出现的 BigDecimal 类型
     */
    public static <N extends Number> N OTN(Object t, Class<N> clazz) {

        if (t == null) {
            return clazz.cast(0);
        }

        try {
            Method method = clazz.getMethod("valueOf", String.class);
            return clazz.cast(method.invoke(null, String.valueOf(t)));
        } catch (NoSuchMethodException e) {
            Log.e("{} 数字类型未实现或无法解析", clazz.getName());
        } catch (InvocationTargetException | IllegalAccessException e) {
            Log.e("数字 {} 解析为 {} 错误", Objects.requireNonNull(e.getMessage()));
        }
        return null;
    }
}

