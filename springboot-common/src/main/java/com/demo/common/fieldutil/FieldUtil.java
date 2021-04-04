package com.demo.common.fieldutil;

import java.beans.Introspector;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * <p>
 * 基于lambda和反射的方式, 通过get|is方法获取成员属性 <br>
 * 成员变量的名称符合xxXxx或XXX的格式 <br>
 * (如xXxx,虽不会抛出异常,但结果不准确) <br>
 * get方法必须是标准的 getXxx 式的命名,否则抛出异常 <br>
 * </p>
 *
 * @author liuhoujie
 * @date 2020/8/14
 */
@SuppressWarnings({"unused"})
public class FieldUtil {

    /**
     * 匹配 getXxx , get忽略大小写
     */
    private static final Pattern GET_PATTERN = Pattern.compile("(?i)get[A-Z].*");
    /**
     * 匹配 isXxx , is忽略大小写
     */
    private static final Pattern IS_PATTERN = Pattern.compile("(?i)is[A-Z].*");

    /**
     * <p>
     * 获取成员变量名 <br>
     * 例: FieldUtil.toField(Class::getXxx) <br>
     *      或FieldUtil.toField(Class::isXxx)
     * </P>
     */
    public static <T> String toField(FieldFunction<T, ?> func) {
        return toField(func, false);
    }

    /**
     * <p>
     * 是否强制小写第一个字母 (命名为xXxx的情况下)
     * 例: FieldUtil.toField(Class::getXxx, true) <br>
     *      或FieldUtil.toField(Class::isXxx, true)
     * </p>
     */
    public static <T> String toField(FieldFunction<T, ?> func, boolean isLowerCaseToFirst) {
        return resolveFieldName(func, isLowerCaseToFirst);
    }

    /**
     * 解析 成员变量名称
     */
    private static <T> String resolveFieldName(FieldFunction<T, ?> func, boolean isLowerCaseToFirst) {
        try {
            // 通过获取对象方法，判断是否存在该方法
            Method method = func.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);

            // 利用jdk的SerializedLambda 解析方法引用
            SerializedLambda serializedLambda = (SerializedLambda) method.invoke(func);
            String methodName = serializedLambda.getImplMethodName();

            if (GET_PATTERN.matcher(methodName).matches()) {
                methodName = methodName.substring(3);
            } else if (IS_PATTERN.matcher(methodName).matches()) {
                methodName = methodName.substring(2);
            } else {
                throw new RuntimeException("不符合getXxx或isXxx式的命名");
            }

            // java类属性 缺省处理
            String fieldName = Introspector.decapitalize(methodName);

            return isLowerCaseToFirst ? lowerCaseToFirst(fieldName) : fieldName;

        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("解析成员变量名称异常", e);
        }
    }

    /**
     * 小写第一个字母
     */
    private static String lowerCaseToFirst(String fieldName) {
        return fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
    }

}
