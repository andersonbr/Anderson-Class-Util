package br.com.andersoncalixto.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class BeanAnnotationUtil {
	private static Map<String, Class<?>> cachedBeansTypeId;
	static {
		cachedBeansTypeId = new HashMap<String, Class<?>>();
	}
	public static Class<?> getTypeByAnnotationClass(Class<?> ob, Class<?> an) {
		String key = ob.getName();
		if (!cachedBeansTypeId.containsKey(key)) {
			Field fields[] = ob.getDeclaredFields();
			for (Field field : fields) {
				Annotation annotations[] = field.getAnnotations();
				for (Annotation annotation : annotations) {
					if (annotation.annotationType().equals(an)) {
						Class<?> value = field.getType();
						cachedBeansTypeId.put(key, value);
						return value;
					}
				}
			}
			Method methods[] = ob.getDeclaredMethods();
			for (Method method : methods) {
				Annotation annotations[] = method.getAnnotations();
				for (Annotation annotation : annotations) {
					if (annotation.annotationType().equals(an)) {
						Class<?> value = method.getReturnType();
						cachedBeansTypeId.put(key, value);
						return value;
					}
				}
			}
		} else {
			return cachedBeansTypeId.get(key);
		}
		
		return null;
	}
	public static Object getTypeInstanceByString(Class<?> cl, String value) {
		try {
			return cl.getDeclaredConstructor(String.class).newInstance(value);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
}
