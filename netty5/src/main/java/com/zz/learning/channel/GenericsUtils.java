

package com.zz.learning.channel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;



public class GenericsUtils {
    /**    
        * 通过反射,获得定义Class时声明的父类的范型参数的类型.    
       * 如public BookManager extends GenricManager<Book>    
        *    
        * @param clazz The class to introspect    
        * @return the first generic declaration, or <code>Object.class</code> if cannot be determined    
        */
    public static Class getSuperClassGenricType(Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    /**    
     * 通过反射,获得定义Class时声明的父类的范型参数的类型.    
     * 如public BookManager extends GenricManager<Book>    
     *    
     * @param clazz clazz The class to introspect    
     * @param index the Index of the generic ddeclaration,start from 0.    
     */
    public static Class getSuperClassGenricType(Class clazz, int index) throws IndexOutOfBoundsException {

        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }

    /**
     * 通过反射获取类实现的接口
     * @param clazz
     * @param index
     * @return
     */
    public static Type getGenericInterfaces(Class clazz) {
        return getGenericInterfaces(clazz, 0);
    }

    /**
     * 通过反射获取类实现的接口
     * @param clazz
     * @param index
     * @return
     */
    public static Type getGenericInterfaces(Class clazz, int index) {
        if (clazz != null) {
            Type[] interfaces = clazz.getGenericInterfaces();
            return interfaces.length < 1 ? null : interfaces[index];
        }
        return null;
    }

    public static Class getGenericInterfaceGenricType(Class clazz, int paramIndex) {
        return getGenericInterfaceGenricType(clazz, 0, paramIndex);
    }

    public static Class getGenericInterfaceGenricType(Class clazz, int interfaceIndex, int paramIndex) {
        Type returnClass = (Type) getGenericInterfaces(clazz, interfaceIndex);
        if (!(returnClass instanceof ParameterizedType)) {
            return Object.class;
        }

        Type[] params = ((ParameterizedType) returnClass).getActualTypeArguments();
        if (paramIndex >= params.length || paramIndex < 0) {
            return Object.class;
        }
        if (!(params[paramIndex] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[paramIndex];
    }
}
