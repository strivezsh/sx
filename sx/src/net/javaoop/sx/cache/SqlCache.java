package net.javaoop.sx.cache;

public interface SqlCache {
	public void init();

	public void put(String scheme, String key, String value);

	public String get(String scheme, String key);

	public void clear();

	public void destroy();
}
