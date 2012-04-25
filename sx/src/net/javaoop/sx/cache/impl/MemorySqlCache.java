package net.javaoop.sx.cache.impl;

import java.util.HashMap;
import java.util.Map;

import net.javaoop.sx.cache.SqlCache;

/**
 * 默认内存SQL缓存类
 * 
 * @author 周少华
 */
public class MemorySqlCache implements SqlCache {
	private final Map<String, Map<String, String>> cache = new HashMap<String, Map<String, String>>();

	public void put(String scheme, String key, String value) {
		Map<String, String> map = cache.get(scheme);
		if (map == null) {
			map = new HashMap<String, String>();
		}
		map.put(key, value);
	}

	public String get(String scheme, String key) {
		Map<String, String> map = cache.get(scheme);
		if (map == null) {
			return null;
		}
		return map.get(key);
	}

	public void init() {
	}

	public void destroy() {
	}

	public void clear() {
	}

	public Object getAll() {
		return cache;
	}
}
