package net.javaoop.sx.parser;

import java.io.File;
import java.util.Map;

import net.javaoop.sx.cache.SqlCache;

public abstract class Parser {
	private String name;
	private Map<String, NodeParser> nodeParsers;
	private SqlCache sqlCache;

	public abstract void parseXmlFile(String scheme, String className, File file);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, NodeParser> getNodeParsers() {
		return nodeParsers;
	}

	public void setNodeParsers(Map<String, NodeParser> nodeParsers) {
		this.nodeParsers = nodeParsers;
	}

	public SqlCache getSqlCache() {
		return sqlCache;
	}

	public void setSqlCache(SqlCache sqlCache) {
		this.sqlCache = sqlCache;
	}
}
