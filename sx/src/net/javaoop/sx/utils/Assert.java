package net.javaoop.sx.utils;

import java.util.Collection;
import java.util.Map;

public class Assert {

	public static void isTrue(boolean expression, String message) {
		if (!expression) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void isTrue(boolean expression) {
		isTrue(expression, "结果必须为True!!!");
	}

	public static void isNull(Object object, String message) {
		if (object != null) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void isNull(Object object) {
		isNull(object, "对象必须为Null!!!");
	}

	public static void notNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void notNull(Object object) {
		notNull(object, "对象不能为Null!!!");
	}

	public static void notBlank(String string, String message) {
		notNull(string);
		if (string.trim().length() < 1) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void notBlank(String string) {
		notBlank(string, "字符串不能为Null,且去除前后空格后不能为空!!!");
	}

	public static void notEmpty(Object[] array, String message) {
		notNull(array, message);
		if (array.length < 1) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void notEmpty(Object[] array) {
		notEmpty(array, "数组不能为空,且至少含有一个元素!!!");
	}

	public static void notEmpty(Collection collection, String message) {
		notNull(collection, message);
		if (collection.size() < 1) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void notEmpty(Collection collection) {
		notEmpty(collection, "集合不能为空,且至少含有一个元素!!!");
	}

	public static void notEmpty(Map map, String message) {
		notNull(map, message);
		if (map.isEmpty()) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void notEmpty(Map map) {
		notEmpty(map, "Map不能为空,且至少含有一个元素!!!");
	}

}
