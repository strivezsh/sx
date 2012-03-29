package net.javaoop.sx.cache.impl;

import java.util.Hashtable;
import java.util.Map;

import net.javaoop.sx.cache.SqlCache;

/**
 * 默认内存SQL缓存类
 * 
 * @author 周少华
 */
public class MemorySqlCache implements SqlCache {
	private final Map<String, String> cache = new Hashtable<String, String>();

	public void put(String key, String value) {
		cache.put(key, value);
	}

	public String get(String key) {
		return cache.get(key);
	}

	public void init() {
	}

	public void destroy() {
	}

	public void clear() {
	}
}
