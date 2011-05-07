package br.com.andersoncalixto.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ClassUtil {
	public static Class<?> getClassParameter(Type superClass, Integer position) {
		Class<?> type = (Class<?>) ((ParameterizedType)superClass).getActualTypeArguments()[position];
		return type;
	}
}
