package net.javaoop.sx.cache;


public interface SqlCache {
	public void init();

	public void put(String key, String value);

	public String get(String key);

	public void clear();

	public void destroy();
}
